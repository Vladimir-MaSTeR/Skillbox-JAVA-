import java.util.Scanner;

public class SortString {


    public static String text;


    public static void  enterConsole() {

        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("Введите фамилию, имя и отчество одной строкой (например, “Иванов Сергей Петрович”)");
                text = scanner.nextLine();
            } catch (Exception e) {
                System.out.println("ОШИБКА ВВОДА");
            }
            if (text.matches("\\D+\\s\\D+\\s\\D+")) {
                scanner.close();
                break;
            } else System.out.println("Неверный формат. Попробуйте снова");
        }
    }

    public static void print() {
        enterConsole();

        String[] familyName = text.split("\\s+");

        System.out.println("----------------------------------------");
        System.out.println("\t Фамилия: " + familyName[0]);
        System.out.println("\t Имя: " + familyName[1]);
        System.out.println("\t Отчество: " + familyName[2]);
        System.out.println("----------------------------------------");
    }


    public static void main(String[] args) {
       print();
    }
}

