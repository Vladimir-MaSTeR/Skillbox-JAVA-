import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static utils.Constants.*;


public class Loader {

    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient(MONGO_HOST, MONGO_PORT);
        MongoDatabase database = mongoClient.getDatabase(DATA_BASE_NAME);

        printInfo(database);
    }

    public static void printInfo(MongoDatabase database) {
        String data = "src/main/data/mongo.csv";
        String format = "%-50s%20s%n";


        MongoCollection<Document> collection = database.getCollection("students");
        collection.drop();

        try {
            collection.insertMany(parseCSV(data));

            System.out.printf(format,
                    "Общее количество студентов в базе: ", collection.countDocuments());

            BsonDocument query = BsonDocument.parse("{age: {$gt: 40}}");
            System.out.printf(format,
                    "Количество студентов старше 40 лет: ", collection.countDocuments(query));

            query = BsonDocument.parse("{age: 1}");
            System.out.printf(format,
                    "Имя самого молодого студента: ", collection.find()
                            .sort(query).limit(1).first().get("name")
            );

            query = BsonDocument.parse("{age: -1}");
            Document oldestOne = collection.find()
                    .sort(query).limit(1).first();
            System.out.printf("Список курсов самого старого студента: %s%n   Курсы: ", oldestOne.get("name"));
            oldestOne.values().stream().skip(3).forEach(System.out::println);

        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + data);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    static List<Document> parseCSV(String f) throws IOException {
        List<Document> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String delimiter = ",";
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(delimiter, 3);
                String[] courses = columns[2].split(delimiter);
                students.add(new Document()
                        .append("name", columns[0])
                        .append("age", Integer.valueOf(columns[1]))
                        .append("courses", Arrays.asList(courses))
                );
            }
        }
        return students;
    }
}
