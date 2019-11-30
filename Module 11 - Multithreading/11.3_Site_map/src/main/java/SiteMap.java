
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static java.lang.Thread.sleep;


public class SiteMap {


    public void parseLink(ConcurrentLinkedQueue<String> queue, String writeFile, String host, Integer threads) {

        ExecutorService executorService = Executors.newFixedThreadPool(threads);
        List<ThreadSiteMap> threadList = new ArrayList<>();

        for (int i = 0; i < threads; i++) {
            ThreadSiteMap threadSiteMap = new ThreadSiteMap(queue, writeFile, host);
            threadList.add(threadSiteMap);
            executorService.submit(threadSiteMap);
        }

        boolean allThreadWork = true;

        while (allThreadWork) {
            allThreadWork = true;
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (ThreadSiteMap tsm : threadList) {
                if (!tsm.isWork) {
                    allThreadWork = false;
                    return;
                }
            }
        }

        ThreadSiteMap.stopAll();
        executorService.shutdown();
    }
}
