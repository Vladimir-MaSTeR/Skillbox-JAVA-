import com.skillbox.airport.Airport;

public class lesson8 {

    public static void main(String[] args) {
        Airport airport = Airport.getInstance();

        System.out.println(airport.getAllAircrafts().size());
    }

}
