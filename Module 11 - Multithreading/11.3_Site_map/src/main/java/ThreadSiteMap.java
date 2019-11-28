import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ThreadSiteMap implements Runnable {

    private ConcurrentLinkedQueue<String> queue;
    private String writeFile;
    private String host;

    private Boolean isWork = false;

    public ThreadSiteMap(ConcurrentLinkedQueue<String> queue, String writeFile, String host) {
        this.queue = queue;
        this.writeFile = writeFile;
        this.host = host;
    }

//   private static CopyOnWriteArraySet<String> allLinks = new CopyOnWriteArraySet<>();
   private Collection<String> linksDone = Collections.synchronizedCollection(new TreeSet<>());
    @Override
    public void run() {
        try {
            if(isWork) {
                try {
                    Thread.sleep(200);
                    System.out.println("Thread sleep..");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
                isWork = true;
                System.out.println("Thread start work");
                while (queue.size() > 0) {
//                    isWork = true;
                    String url = queue.poll();
                    if (url != null) {
                        Document document = Jsoup.connect(url).maxBodySize(0).get();
                        Elements elements = document.select("a");

                        elements.forEach(el -> {
                            String attr = el.attr("abs:href");
                            if (!attr.isEmpty() && attr.startsWith(String.valueOf(host)) &&
                                    !linksDone.contains(attr) && !attr.contains("#")) {
                                linksDone.add(attr);
//                                isWork = false;
                                //  allLinks.add(attr);
                            }
//                            isWork = false;
                        });
//                        isWork = false;
                    }
                  //  isWork = false;
                }
            write(linksDone);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void write(Collection<String> linksDone) {
        for (String link : linksDone) {
            try {
                Path path = Paths.get(writeFile);
                //Files.writeString(path, link, StandardOpenOption.APPEND);
                Files.write(path, Collections.singleton(link));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
