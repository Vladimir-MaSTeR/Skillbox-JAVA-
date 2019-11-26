import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.ConcurrentLinkedQueue;


public class Main {

    private static final String URL = "http://www.lenta.ru";
    private static final String WRITE_FILE = "srs/main/resources/map.txt";
    private static final int THREADS = 10;

    public static void main(String[] args) throws IOException {

        SiteMap siteMap = new SiteMap();
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>(Collections.singletonList(URL));
        siteMap.parseLink(queue, WRITE_FILE, THREADS);

    }


}
