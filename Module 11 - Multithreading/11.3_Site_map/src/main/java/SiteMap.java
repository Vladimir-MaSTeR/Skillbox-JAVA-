
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

            int countThread = 0;
            for (ThreadSiteMap tsm : threadList) {
                if (tsm.isWork) countThread++;
//                else countThread--;
            }
            if (countThread == 0) {
                allThreadWork = false;
                System.out.println(Thread.currentThread().getName() + "Stop work thread");
            }
        }
        ThreadSiteMap.stopAll();
        executorService.shutdown();
    }
}


//
// for (ThreadSiteMap tsm : threadList) {
//
//                   countThread++;
//                   allThreadWork = false;
//                   return;
//         }
//         }