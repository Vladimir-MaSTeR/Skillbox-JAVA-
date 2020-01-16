
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;


public class Loader {

    public static void main(String[] args) {
        String fileName = "res/data-1572M.xml";
        long start = System.currentTimeMillis();

        try {
            parseFileSax(fileName);
            System.out.println((System.currentTimeMillis() - start)/1000F + " секунд - выполнялся запрос");

        DBConnection.printVoterCounts();
        DBConnection.createIndex();
        DBConnection.customSelect("Карпушенков Элизбар");
        DBConnection.connectionClose();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void parseFileSax(String fileName)  {

        try {
            DBConnection.getConnection();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLHandler handler = new XMLHandler();
            parser.parse(new File(fileName), handler);
            DBConnection.executeBatch();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}