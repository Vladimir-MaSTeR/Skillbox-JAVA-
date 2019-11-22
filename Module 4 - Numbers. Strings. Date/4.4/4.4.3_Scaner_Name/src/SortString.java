import java.util.Scanner;

public class SortString {

    public static void main(String[] args) {
       print();
    }

    public static String text;


   public static void  enterConsole() {
       Scanner scanner = new Scanner(System.in);
       while (true) {
           try {
               System.out.println("Введите фамилию, имя и отчество одной строкой (например, “Иванов Сергей Петрович”)");
               text = scanner.nextLine();
               if (text.matches("\\D*\\s\\D*\\s\\D*")) {
                   break;
               } else {
                   throw new Exception();
               }
           } catch (Exception e) {
               System.out.println("Неверный формат ввода. Попробуйте снова");
           }
       }
   }

   public static void print() {
        enterConsole();

        int indexSpase = text.indexOf(' ');
        int indexSpaseLast = text.lastIndexOf(' ');

       System.out.println("----------------------------------------");
       System.out.println("\t Фамилия: " + text.substring(0, indexSpase));
       System.out.println("\t Имя: " + text.substring(indexSpase, indexSpaseLast));
       System.out.println("\t Отчество: " + text.substring(indexSpaseLast));
       System.out.println("----------------------------------------");
   }
}

