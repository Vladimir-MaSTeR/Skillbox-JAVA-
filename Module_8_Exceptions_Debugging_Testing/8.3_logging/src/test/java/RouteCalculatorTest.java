import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {

    List<Station> stations;
    List<Station> crossingStations1_3;
    List<Station> crossingStations3_2;
    RouteCalculator routeCalculator;
    StationIndex stationIndex;

    public RouteCalculatorTest () {
    }
    @Override
    protected void setUp() throws Exception {
        stations = new ArrayList<>();
        crossingStations1_3 = new ArrayList<>();
        crossingStations3_2 = new ArrayList<>();
        stationIndex = new StationIndex();
        routeCalculator = new RouteCalculator(stationIndex);

        Line line1 = new Line(1, "Первая");
        Line line2 = new Line(2, "Вторая");
        Line line3 = new Line(3, "Третья");

        stations.add(new Station("Красная", line1));
        stations.add(new Station("Черная-Переходная", line1));
        stations.add(new Station("Белая", line1));

        stations.add(new Station("Яблочая", line2));
        stations.add(new Station("Сливовая-Переходная", line2));
        stations.add(new Station("Арбузная", line2));

        stations.add(new Station("Квадратная", line3));
        stations.add(new Station("Угольная-Переходная", line3));
        stations.add(new Station("Прямая", line3));
        stations.add(new Station("Ромбовая-Переходная", line3));
        stations.add(new Station("Круглая", line3));

        crossingStations1_3.add(stations.get(1));
        crossingStations1_3.add(stations.get(7));
        crossingStations3_2.add(stations.get(9));
        crossingStations3_2.add(stations.get(4));

        line1.addStation(stations.get(0));
        line1.addStation(stations.get(1));
        line1.addStation(stations.get(2));

        line2.addStation(stations.get(3));
        line2.addStation(stations.get(4));
        line2.addStation(stations.get(5));

        line3.addStation(stations.get(6));
        line3.addStation(stations.get(7));
        line3.addStation(stations.get(8));
        line3.addStation(stations.get(9));
        line3.addStation(stations.get(10));
        //--------------------------------------
        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);

        stationIndex.addStation(stations.get(0));
        stationIndex.addStation(stations.get(1));
        stationIndex.addStation(stations.get(2));
        stationIndex.addStation(stations.get(3));
        stationIndex.addStation(stations.get(4));
        stationIndex.addStation(stations.get(5));
        stationIndex.addStation(stations.get(6));
        stationIndex.addStation(stations.get(7));
        stationIndex.addStation(stations.get(8));
        stationIndex.addStation(stations.get(9));
        stationIndex.addStation(stations.get(10));

        stationIndex.addConnection(crossingStations1_3);
        stationIndex.addConnection(crossingStations3_2);
    }

    @Test
    public void testTime_first_station_to_last_one_branch() {
        List<Station> listOneBranch = new ArrayList<>();
        listOneBranch.add(stations.get(0));
        listOneBranch.add(stations.get(1));
        listOneBranch.add(stations.get(2));

        double actual = RouteCalculator.calculateDuration(listOneBranch);
        double expected = 5.0;
        assertEquals(expected, actual);
    }

    @Test
    public void testTravel_time_with_one_stopover() {
        List<Station> listOneStopover = new ArrayList<>();
        listOneStopover.add(stations.get(0));
        listOneStopover.add(stations.get(1));
        listOneStopover.add(stations.get(2));
        listOneStopover.add(stations.get(3));
        listOneStopover.add(stations.get(4));
        listOneStopover.add(stations.get(5));

        double actual = RouteCalculator.calculateDuration(listOneStopover);
        double expected = 13.5;
        assertEquals(expected, actual);
    }

    @Test
    public void testTravel_time_with_two_transfers() {
        double actual = RouteCalculator.calculateDuration(stations);
        double expected = 27.0;
        assertEquals(expected, actual);
    }
    @Test
    public void testTravel_time_start_and_end_stations () {
        List<Station> list = new ArrayList<>();
        list.add(stations.get(0));

        double actual = RouteCalculator.calculateDuration(list);
        double expected = 0;
        assertEquals(expected, actual);

    }

    //-----------------------------------

    @Test
    public void testStations_one_Line() {

        List<Station> actualList = routeCalculator.getShortestRoute(stationIndex.getStation("Красная"), stationIndex.getStation("Белая"));
        List<Station> expected = new ArrayList<>();
        expected.add(stationIndex.getStation("Красная"));
        expected.add(stationIndex.getStation("Черная-Переходная"));
        expected.add(stationIndex.getStation("Белая"));

        assertEquals(expected, actualList);

    }

    @Test
    public void testOne_transfers () {

        List<Station> actualList = routeCalculator.getShortestRoute(stationIndex.getStation("Красная"), stationIndex.getStation("Круглая"));
        List<Station> expected = new ArrayList<>();
        expected.add(stationIndex.getStation("Красная"));
        expected.add(stationIndex.getStation("Черная-Переходная"));
        expected.add(stationIndex.getStation("Угольная-Переходная"));
        expected.add(stationIndex.getStation("Прямая"));
        expected.add(stationIndex.getStation("Ромбовая-Переходная"));
        expected.add(stationIndex.getStation("Круглая"));

        assertEquals(expected, actualList);

    }

    @Test
    public void testTwo_transfers()  {

        List<Station> actualList = routeCalculator.getShortestRoute(stationIndex.getStation("Красная"), stationIndex.getStation("Арбузная"));
        List<Station> expected = new ArrayList<>();
        expected.add(stationIndex.getStation("Красная"));
        expected.add(stationIndex.getStation("Черная-Переходная"));
        expected.add(stationIndex.getStation("Угольная-Переходная"));
        expected.add(stationIndex.getStation("Прямая"));
        expected.add(stationIndex.getStation("Ромбовая-Переходная"));
        expected.add(stationIndex.getStation("Сливовая-Переходная"));
        expected.add(stationIndex.getStation("Арбузная"));

        assertEquals(expected, actualList);
    }

    @Override
    protected void tearDown() throws Exception {

    }
}
