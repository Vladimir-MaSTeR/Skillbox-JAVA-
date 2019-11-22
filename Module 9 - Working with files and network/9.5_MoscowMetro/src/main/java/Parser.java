import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

final class Parser {

    private final int FIRST_ELEMENT = 0;
    private final int TWO_ELEMENT = 1;
    private final int FOUR_ELEMENT = 3;

    private List<Line> lines = new LinkedList<>();
    private Map<String, List<String>> stations = new TreeMap<>();
    private List<List<Station>> connection = new ArrayList<>();

    public String parseFile(String path) {
        StringBuilder sb = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(line -> sb.append(line).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void parserJsoupSite(String file) {
           Document document = Jsoup.parse(parseFile(file));
           Element table = document.select("table").get(FOUR_ELEMENT);
           Elements elements = table.select("tr");

           elements.stream().skip(1).forEach((element) -> {
               Elements td = element.select("td");
               String nameStation = td.get(TWO_ELEMENT).text();
               String nameLine = td.get(FIRST_ELEMENT).child(TWO_ELEMENT).attr("title");
               List<String> numbersLine = td.get(FIRST_ELEMENT).children().eachText();
               List<String> connectionsLineName = td.get(FIRST_ELEMENT).children().eachAttr("title");
               List<String> connectionsNumber = td.get(FOUR_ELEMENT).children().eachText();

               parseStation(nameStation, numbersLine, connectionsLineName);
               parseLines(nameLine, numbersLine);

               if (!connectionsNumber.isEmpty()) {
                   parseConnections(td, nameStation);
               }


           });
   }


    private void parseLines(String nameLine, List<String> numbersLine) {
        Line line = new Line(numbersLine.get(0), nameLine);

        if (!lines.contains(line)) lines.add(line);
    }

    private void parseStation(String nameStation, List<String> numbersLine, List<String> connectionsLineName) {
        String lineId = numbersLine.get(0);

        if (!stations.containsKey(lineId)) {
            stations.put(lineId, new ArrayList<>());
            stations.get(lineId).add(nameStation);
        } else {
            stations.get(lineId).add(nameStation);
        }

        if (connectionsLineName.size() == 2) {

            if (!stations.containsKey(numbersLine.get(1)))
                stations.put(numbersLine.get(1), new ArrayList<>());
            else {
                stations.get(numbersLine.get(1)).add(nameStation);
            }
        }
    }

    private void parseConnections(Elements td, String nameStation) {
        List<String> connectionsLine = td.get(FOUR_ELEMENT).children().eachAttr("title");
        List<String> connectionsNumber = td.get(FOUR_ELEMENT).children().eachText();
        List<String> lineNumbers = td.get(FIRST_ELEMENT).children().eachText();
        String lineId = lineNumbers.get(TWO_ELEMENT);

        if (connectionsNumber.size() != 0) {
            List<Station> temp = new ArrayList<>();
            temp.add(new Station(lineId, nameStation));
            for (int i = 0; i < connectionsNumber.size(); i++) {
                temp.add(new Station(connectionsNumber.get(i), connectionsLine.get(i)));
            }
            connection.add(temp);
        }
    }

    public List<Line> getLines() {
        return lines;
    }
    public Map<String, List<String>> getStations() {
        return stations;
    }
    public List<List<Station>> getConnection() {
        return connection;
    }
}
