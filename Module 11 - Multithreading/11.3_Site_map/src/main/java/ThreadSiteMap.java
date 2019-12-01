import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentLinkedQueue;

import static java.lang.Thread.sleep;

public class ThreadSiteMap implements Runnable {

    private ConcurrentLinkedQueue<String> queue;
    private String writeFile;
    private String host;

    public Boolean isWork = true;
    private static boolean isAllWork = true;

    public ThreadSiteMap(ConcurrentLinkedQueue<String> queue, String writeFile, String host) {
        this.queue = queue;
        this.writeFile = writeFile;
        this.host = host;
    }


   private static Collection<String> linksDone = Collections.synchronizedCollection(new TreeSet<>());

    @Override
    public void run() {
                isWork = true;
                System.out.println(Thread.currentThread().getName() + " start work");

                while (isAllWork) {
                    String url = queue.poll();
                    if (url != null) {
                        isWork = true;
                        linksDone.add(url);
                        parseUrl(url);

                    } else {
                        isWork = false;
                        if (sleepParser(200)) return;
                    }
                }
            write(linksDone);
    }

    public void write(Collection<String> linksDone) {


        for (String link : linksDone) {
            try {
                Path path = Paths.get(writeFile);
                Files.write(path, Collections.singleton(link));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Запись в файл закончена");
    }

    public void parseUrl(String url) {
        try {
            Document document = Jsoup.connect(url).maxBodySize(0).get();
            Elements elements = document.select("a");

            elements.forEach(el -> {
                String attr = el.attr("abs:href");
                if (!attr.isEmpty() && attr.startsWith(String.valueOf(host)) &&
                        !linksDone.contains(attr) && !attr.contains("#")) {
                    queue.add(attr);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void stopAll(){
        isAllWork = false;
    }

    private boolean sleepParser(int ms) {
        try {
            sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }
}
