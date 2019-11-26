
import java.util.concurrent.*;


public class SiteMap {


    public void parseLink(ConcurrentLinkedQueue<String> queue, String writeFile, Integer threads) {

        ExecutorService executorService = Executors.newFixedThreadPool(threads);

        for (int i = 0; i < threads; i++) {
            executorService.submit(new ThreadSiteMap(queue, writeFile));
        }

        executorService.shutdown();
    }
}
