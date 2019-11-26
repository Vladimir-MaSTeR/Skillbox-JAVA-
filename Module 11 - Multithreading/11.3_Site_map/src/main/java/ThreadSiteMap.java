import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArraySet;

public class ThreadSiteMap implements Runnable {

    private ConcurrentLinkedQueue<String> queue;
    private String writeFile;

    public ThreadSiteMap(ConcurrentLinkedQueue<String> queue, String writeFile) {
        this.queue = queue;
        this.writeFile = writeFile;
    }

    private static CopyOnWriteArraySet<String> allLinks = new CopyOnWriteArraySet<>();
    @Override
    public void run() {
        try {
            while (queue.size() > 0) {
                if ( queue.poll() !=  null) {
                    Document document = Jsoup.connect(String.valueOf(queue)).maxBodySize(0).get();
                    Elements elements = document.select("a");

                    elements.forEach(el -> {
                        String attr = el.attr("abs:href");
                        if (!attr.isEmpty() && attr.startsWith(String.valueOf(queue)) &&
                                !allLinks.contains(attr) && !attr.contains("#")) {
                            try {
                                Path path = Paths.get(writeFile);
                                Files.writeString(path, attr, StandardOpenOption.APPEND);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                          //  allLinks.add(attr);
                        }
                    });
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
