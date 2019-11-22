import lombok.AllArgsConstructor;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.RecursiveTask;

@Data
@AllArgsConstructor
public class SiteMap extends RecursiveTask<String> {

    private String url;
    private static String startUrl;
    private static CopyOnWriteArraySet<String> allLinks = new CopyOnWriteArraySet<>();

    public SiteMap(String url, String startUrl) {
        this.url = url.trim();
        SiteMap.startUrl = startUrl.trim();
    }

    @Override
    protected String compute() {
        StringBuffer sb = new StringBuffer(url + "\n");
        Set<SiteMap> subTask = getChildren();
        subTask.forEach(e -> sb.append(e.join()));
        return sb.toString();
    }

    private Set<SiteMap> getChildren() {
        Document doc;
        Elements elements;
        HashSet<SiteMap> subTask = new HashSet<>();
        try {
            Thread.sleep(200);
            doc = Jsoup.connect(url).maxBodySize(0).get();
            elements = doc.select("a");
            elements.forEach(el -> {
                String attr = el.attr("abs:href");
                if (!attr.isEmpty() && attr.startsWith(startUrl) &&
                        !allLinks.contains(attr) && !attr.contains("#")) {
                    SiteMap siteMap = new SiteMap(attr);
                    siteMap.fork();
                    subTask.add(siteMap);
                    allLinks.add(attr);
                }
            });
        } catch (InterruptedException  | IOException ex ) {
            throw new RuntimeException(ex);
        }
        return subTask;
    }
}
