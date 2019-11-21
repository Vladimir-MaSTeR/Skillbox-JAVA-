import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;



public class Main {

    private static final String PATH_SITE = " http://www.lenta.ru/";
    private static final String PATH_WRITE = "srs/main/resources/map.txt";
    private static final int THREADS = 10;

    public static void main(String[] args) throws IOException {
        Path url = Paths.get(PATH_SITE);

        SiteMap siteMap = new SiteMap(url.toString(), url.toString());
        String map = THREADS == 0 ?  new ForkJoinPool().invoke(siteMap) : new ForkJoinPool(THREADS).invoke(siteMap);

        writeFiles(map);
    }

    private static void writeFiles(String map) throws IOException {
        Path path = Paths.get(PATH_WRITE);
        Files.writeString(path, map, StandardOpenOption.APPEND);
    }
}
