import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        Airport airport = Airport.getInstance();

           airport.getTerminals().stream()
                .flatMap(t -> t.getFlights().stream())
                .filter(flight -> flight.getDate().getHours() == new Date().getHours()
                        && flight.getDate().getHours() <= new Date().getHours() +  1
                        && flight.getType().equals(Flight.Type.DEPARTURE))
                .forEach(System.out::println);


    }
}
