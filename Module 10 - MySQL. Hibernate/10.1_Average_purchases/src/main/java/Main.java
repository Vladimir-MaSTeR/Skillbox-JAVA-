
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    private final static String URL = "jdbc:mysql://localhost:3306/skillbox" +
            "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&useSSL=false&serverTimezone=UTC";
    private final static String USER = "root";
    private final static String PASSWORD = "corsar87";
    private final static String SQL_REQUEST = "SELECT course_name, COUNT(*) / (MIN(MONTH(subscription_date)) + MAX(MONTH(subscription_date)))" +
                                              "FROM purchaselist GROUP BY course_name";

    public static void main(String[] args) {
        ;

        ;
//       statement.execute("SELECT * FROM Courses");  // Ещё 1 варинт писать запросы

        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();

            try(ResultSet resultSet = statement.executeQuery(SQL_REQUEST)) {

                while (resultSet.next()) {
                    System.out.println("Название курса        |  " + resultSet.getString(1));
                    System.out.println("Сред. кол. студентов  |  " + resultSet.getString(2));
                    System.out.println("******************************************************");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
