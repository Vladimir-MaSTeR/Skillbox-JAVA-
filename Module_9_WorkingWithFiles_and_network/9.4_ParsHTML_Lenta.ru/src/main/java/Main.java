import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Main {
    private static String url = "https://lenta.ru/";
    private static String destinationFolder = "src/main/pictures/";
    private static String attributeKey = "src";
    private static final String REGEX_PICTURES_FORMAT = "\\S+[.](jpg|png)";
    private static  final String REGEX_HTTP = "https://.+";

    public static void main(String[] args) throws IOException {

        Document document = Jsoup.connect(url).get();         // подключаемся к сайту
        Elements element = document.select("img");  // делаем css запрос

        System.out.println("Начал поиск и копирование...");
        element.stream().filter(el -> el.attr(attributeKey).matches(REGEX_HTTP))
                        .filter(el -> el.attr(attributeKey).matches(REGEX_PICTURES_FORMAT)).forEach(Main::copy);
        System.out.println("Копирование завершино");
    }

    private static void copy (Element element) {

        String[] nameList = element.attr(attributeKey).split("/");
        try {
            URL url = new URL(element.attr(attributeKey));

            InputStream inputStream = url.openStream();
            Files.copy(inputStream, Paths.get(destinationFolder  + nameList[nameList.length - 1]),
                    StandardCopyOption.REPLACE_EXISTING);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
//        element.stream().filter(el -> el.attr(attributeKey).startsWith("https://.+"))
//                        .filter(el -> el.attr(attributeKey).endsWith("\\S+[.](jpg|png)")).forEach(Main::copy); не работает
//
//        element.stream().filter(el -> el.attr(attributeKey).startsWith("http://.+"))
//                        .filter(el -> el.attr(attributeKey).endsWith("\\S+[.](jpg|png)")).forEach(Main::copy); не работает

//        element.forEach(System.out::println);
//        element.forEach(el -> {
//                              if (el.attr(attributeKey).contains("http")
//                              && (el.attr(attributeKey).contains("jpg")
//                              || el.attr(attributeKey).contains("png"))) {
//                           }
//                         } );