import java.io.IOException;

public class Main {

    private static String pathFile = "src/main/resources/movementList.csv";

    public static void main(String[] args) throws IOException {
       Parser.print(pathFile);
    }
}
