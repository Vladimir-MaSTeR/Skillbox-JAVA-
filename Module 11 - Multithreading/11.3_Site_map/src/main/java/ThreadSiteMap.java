import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.Collections;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArraySet;

public class ThreadSiteMap implements Runnable {

    private ConcurrentLinkedQueue<String> queue;
    private String writeFile;

    public ThreadSiteMap(ConcurrentLinkedQueue<String> queue, String writeFile) {
        this.queue = queue;
        this.writeFile = writeFile;
    }

//   private static CopyOnWriteArraySet<String> allLinks = new CopyOnWriteArraySet<>();
   private Collection<String> linksDone = Collections.synchronizedCollection(new TreeSet<>());
    @Override
    public void run() {
        try {
            while (queue.size() > 0) {
                String url = queue.poll();
                if ( url !=  null) {
                    Document document = Jsoup.connect(url).maxBodySize(0).get();
                    Elements elements = document.select("a");

                    elements.forEach(el -> {
                        String attr = el.attr("abs:href");
                        if (!attr.isEmpty() && attr.startsWith(String.valueOf(queue)) &&
                                !linksDone.contains(attr) && !attr.contains("#")) {
                            linksDone.add(attr);
                          //  allLinks.add(attr);
                        }
                    });
                }
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
                Files.writeString(path, link, StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
