package utils;

import static com.mongodb.client.model.Accumulators.avg;
import static com.mongodb.client.model.Accumulators.max;
import static com.mongodb.client.model.Accumulators.min;
import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.count;
import static com.mongodb.client.model.Aggregates.group;
import static com.mongodb.client.model.Aggregates.lookup;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Aggregates.unwind;
import static com.mongodb.client.model.Filters.*;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.BsonField;
import org.bson.BasicBSONObject;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.io.BsonOutput;
import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Consumer;

public class MongoDB {

    private final String HOST_MONGO_CLIENT = "127.0.0.1";
    private final int PORT_MONGO_CLIENT = 27017;
    private final  String DATABASE_NAME = "local";

    private MongoClient mongoClient = new MongoClient(HOST_MONGO_CLIENT, PORT_MONGO_CLIENT);
    private MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);

    private MongoCollection<Document> shops = database.getCollection("shop");
    private MongoCollection<Document> products  = database.getCollection("products");

    //------------------------------------------------------------------------------------------

    public void start() {
         String commandExample = "Примеры команд:"
                + "\n - ДОБАВИТЬ_МАГАЗИН Девяточка"
                + "\n - ДОБАВИТЬ_ТОВАР Вафли 54"
                + "\n - ВЫСТАВИТЬ_ТОВАР Вафли Девяточка"
                + "\n - СТАТИСТИКА_ТОВАРОВ"
                + "\n - EXIT - завершает работу программы";

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(commandExample);

        for (;;) {
            System.out.println("Введите команду:");
            try {
                String[] input = reader.readLine().split(" ");

                String instruction = input[0];
                String stringInput = parseString(input).toLowerCase();


                if (instruction.equalsIgnoreCase("ДОБАВИТЬ_МАГАЗИН")) {
                    addShops(stringInput);
                } else if (instruction.equalsIgnoreCase("ДОБАВИТЬ_ТОВАР")) {
                    addProducts(stringInput);
                } else if (instruction.equalsIgnoreCase("ВЫСТАВИТЬ_ТОВАР")) {
                    displayProduct(stringInput);
                } else if (instruction.equalsIgnoreCase("СТАТИСТИКА_ТОВАРОВ")) {
                    printStatisticsAndAggregate(products);
                } else if (instruction.equalsIgnoreCase("EXIT")) {
                    exitDB();
                    break;
                } else System.out.println("Неверный ввод! \n" + commandExample);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String parseString (String [] strings) {
        String  fString = "";

        if (strings.length == 3 ) fString = strings[1] + " " + strings[2];      // + " " + strings[2];
        else if (strings.length == 2) fString = strings[1];                     // + " " + strings[1];
        else  fString = "";
        return fString;
    }

    private void addShops(String nameShop) {
        Document shop = new Document("name", nameShop);
        shop.append("products", new ArrayList<String>());

        if (getShop(nameShop) == null) {
            shops.insertOne(shop);
            System.out.println("Магазин " + nameShop.toUpperCase() + " добавлен.");

        } else System.out.println("ВНИМАНИЕ - Магазин " + nameShop.toUpperCase() + " уже был добавлен ранее");
    }

    private void addProducts(String nameAndPriceProduct) {
        String[] namePriceProduct = nameAndPriceProduct.split(" ");
        String nameProduct = namePriceProduct[0];
        int priceProduct = Integer.parseInt(namePriceProduct[1]);

        Document product = new Document("name", nameProduct);
        product.append("price", priceProduct);

        if (getProduct(nameProduct) == null) {
            products.insertOne(product);
            System.out.println("Продукт " + nameProduct.toUpperCase() + " добавлен по цене " +  priceProduct);

        } else System.out.println("Продукт " + nameProduct.toUpperCase() + " уже был добавлен");
    }

    private void displayProduct(String nameProductAndNameShop) {
        String[] namePrAndNameSh = nameProductAndNameShop.split(" ");
        String nameProduct = namePrAndNameSh[0];
        String nameShop = namePrAndNameSh[1];

            shops.updateOne(eq(getShop(nameShop)), new Document("$addToSet",
                    new Document("products", getProduct(nameProduct).get("name"))));

            System.out.println("Продукт " + nameProduct.toUpperCase() + " добавлен в магазин " + nameShop.toUpperCase());
    }

    private void printStatisticsAndAggregate (MongoCollection<Document> collection ) {

        System.out.println("В базе:" + "\n\t Магазинов - " + shops.countDocuments() +
                                       "\n\t Товаров - " + products.countDocuments());

        System.out.println("Самый дорогой товар " + Objects.requireNonNull(collection.find()
                                                           .sort(BsonDocument.parse("{price:-1}"))
                                                           .first())
                                                           .values());
        System.out.println("Самый дешевый товар " + Objects.requireNonNull(collection.find()
                                                           .sort(BsonDocument.parse("{price: 1}"))
                                                           .first())
                                                           .values());
        Object outputAvg = Objects.requireNonNull(collection.aggregate(Collections.singletonList(
                group("_id", new BsonField("AverageAge",
                                 new BsonDocument("$avg",
                                 new BsonString("$price"))))))
                .first())
                .values();
        System.out.println("Средняя цена товара " + outputAvg);

        Object query = new BasicDBObject("price", new BasicDBObject("$lt", 100));
        System.out.print("Продукты c ценой меньше 100 ");
        collection.find((Bson) query).forEach((Consumer<Document>) doc -> System.out.println(doc));

    }

    private void exitDB() {
        database.drop();
        mongoClient.close();
        System.out.println("Работа завершена!");
    }

    private Document getShop(String name) {
        return shops.find(new Document("name", name)).first();
    }

    private Document getProduct(String name) {
        return products.find(new Document("name", name)).first();
    }


}
