import java.util.Scanner;

public class fonNumber {

    public static String numberFon;
    public static String  str;

    public static void enterPlayer() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("Введите номер телефона: ");
                str = scanner.nextLine();
            } catch (Exception e) {
                System.out.println("ОШИБКА ВВОДА ДАННЫХ");
            }
            numberFon = str.replaceAll("[^0-9]", "");

            if (numberFon.matches("\\d{11}")) {
                scanner.close();
                break;
            } else {
                System.out.println("НЕВЕРНЫЙ ФОРМАТ НОМЕРА");
            }
        }
    }

    public static void print () {
        enterPlayer();

        System.out.println("-----------------------------");
        System.out.println("Номер телефона: " + numberFon);
        System.out.println("-----------------------------");
    }


    public static void main(String[] args) {
        print();
    }
}
