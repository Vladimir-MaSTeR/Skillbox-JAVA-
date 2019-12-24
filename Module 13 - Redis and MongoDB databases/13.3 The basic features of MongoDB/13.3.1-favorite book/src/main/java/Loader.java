import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.BsonDocument;
import org.bson.Document;

import java.util.function.Consumer;

import static utils.Constans.*;

public class Loader {

    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient(MONGO_HOST, MONGO_PORT);
        MongoDatabase database = mongoClient.getDatabase(DATA_BASE_NAME);

        printOldBook(createCollectionsBook(database));
        printFavoriteAuthor(createCollectionsBook(database));
    }

    private static MongoCollection<Document> createCollectionsBook(MongoDatabase database) {
        MongoCollection<Document> collection = database.getCollection("books");
        collection.drop();

        Document book1 = new Document()
                .append("Название книги", "Книга 1")
                .append("Автор", 1)
                .append("Год", 1901);
        Document book2 = new Document()
                .append("Название книги", "Книга 2")
                .append("Автор", 2)
                .append("Год", 1902);
        Document book3 = new Document()
                .append("Название книги", "Книга 3")
                .append("Автор", 3)
                .append("Год", 1903);
        Document book4 = new Document()
                .append("Название книги", "Книга 4")
                .append("Автор", 4)
                .append("Год", 1904);
        Document book5 = new Document()
                .append("Название книги", "Книга 5")
                .append("Автор", 2)
                .append("Год", 1905);

        collection.insertOne(book1);
        collection.insertOne(book2);
        collection.insertOne(book3);
        collection.insertOne(book4);
        collection.insertOne(book5);

        return collection;
    }

    private static void printOldBook(MongoCollection<Document> collection) {
        BsonDocument bsonDocument2 = BsonDocument.parse("{Год: 1}");
        collection.find().sort(bsonDocument2).limit(1).forEach((Consumer<Document>) document -> {
            System.out.println("Самая старая книга: \n" + document);
        });
    }

    private static void printFavoriteAuthor(MongoCollection<Document> collection) {
        System.out.println("Книги любимого автора: ");
        BsonDocument bsonDocument = BsonDocument.parse("{Автор: {$eq: 2}}");
        collection.find(bsonDocument).forEach((Consumer<Document>) System.out::println);
    }

}
