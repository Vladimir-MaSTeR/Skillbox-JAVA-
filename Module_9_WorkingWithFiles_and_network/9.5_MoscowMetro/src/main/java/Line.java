import java.util.List;
import java.util.Objects;

public class Line
{
    private String id;
    private String name;
   // private List<Station> stations;

    public Line(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

//    public List<Station> getStations() {
//        return stations;
//    }
//    public void addStation(Station station) {
//      stations.add(station);
//    }

//    @Override
//    public int compareTo(Line line) {
//        return Integer.compare(number, line.getNumber());
//    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Line line = (Line) obj;
        return Objects.equals(id, line.id) &&
                Objects.equals(name, line.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
