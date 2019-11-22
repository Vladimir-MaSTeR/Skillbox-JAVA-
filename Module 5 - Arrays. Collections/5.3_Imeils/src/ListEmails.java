import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;

public class ListEmails {

    private static final String REGEX_EMAIL = "^([a-z0-9_-]+ \\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    private static final String REGEX_LIST = "LIST";
    private static final String REGEX_ADD = "ADD";
    private static final String REGEX_EXIT = "EXIT";

    public static HashSet<String> emailsList = new HashSet<>();


    public static void userInput() {
        Scanner scanner = new Scanner(System.in);
         String[] stringInput;

        while (true) {
            System.out.println("Введите одну из команд (ADD, LIST), для завершения работы введите (EXIT)");

                stringInput = scanner.nextLine().split(" ");


            if (chekInpet(stringInput[0], REGEX_ADD) && stringInput.length > 1) {
                addStringInput(stringInput[1]);
            } else if (chekInpet(stringInput[0], REGEX_LIST)) {
                sizeStringInput();
            } else if (chekInpet(stringInput[0], REGEX_EXIT)) {
                System.out.println("Досвидания");
                break;
            } else {
                System.out.println("Команда ненайдена. Попробуйте ещё раз");
            }
        }
    }
    private static boolean chekInpet(String positionStringInput, String regex){
        return positionStringInput.equalsIgnoreCase(regex);
    }

    public static void addStringInput(String positionNumber) {
        if (positionNumber.matches(REGEX_EMAIL)) {
            emailsList.add(positionNumber);
            System.out.println("добавленно");
        } else System.out.println("Строка не соответствует EMAIL адресу");
    }

    public static void sizeStringInput() {
        int count = 1;

         if (emailsList.size() == 0) {
             System.out.println("Список пуст");
         }
         for (String s : emailsList) {
             System.out.println(count + " - " + s);
             count++;
         }
    }

    public static void main(String[] args) {
        userInput();
    }

}
