import com.sun.source.tree.Tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class BookFhone {

    private static final String REGEX_FON_NUMBER = "^(\\+)?\\d{9,14}";
    private static final String REGEX_NAME_PERSON = "\\D+";
    private static final String REGEX_EXIT = "EXIT";
    private static final String REGEX_LIST = "LIST";

    public static TreeMap<String, String> numberFon2namePerson = new TreeMap<>();

    public static void userInput() {
        Scanner scanner = new Scanner(System.in);

        for (;;) {
            System.out.println("Введите номер телефона без пробелов или Имя | LIST - весь список | EXIT - закончить |");
            String stringInput = scanner.nextLine();

            if (stringInput.equalsIgnoreCase(REGEX_LIST)) {
                printMap();
                continue;
            }
            if (stringInput.equalsIgnoreCase(REGEX_EXIT)) {
                System.out.println("Досвидния");
                break;
            }
            if (numberFon2namePerson.containsKey(stringInput) || numberFon2namePerson.containsValue(stringInput)) {
                printOnePerson(stringInput);
            } else if (stringInput.matches(REGEX_FON_NUMBER) && numberFon2namePerson.containsKey(stringInput) == false
                    || stringInput.matches(REGEX_NAME_PERSON) && numberFon2namePerson.containsValue(stringInput) == false){
                addMap(stringInput);
            } else {
                System.out.println("Неверный формат воода");
                continue;
            }

        }
    }


    private static void addMap (String stringInput) {
        Scanner scanner = new Scanner(System.in);
        while (true) {

            if (stringInput.matches(REGEX_FON_NUMBER)) {
                System.out.println("номер получен \n Введите Имя");
                String stringInput2 = scanner.nextLine();
                numberFon2namePerson.put(stringInput, stringInput2);
                System.out.println("Список контактов обнавлен");
                break;
            } else if (stringInput.matches(REGEX_NAME_PERSON)) {
                System.out.println("Имя получено \n Введите телефон без пробелов");
                String stringInput2 = scanner.nextLine();

                if (stringInput2.matches(REGEX_FON_NUMBER)) {
                    numberFon2namePerson.put(stringInput2, stringInput);
                    System.out.println("Список контактов обнавлен");
                    break;
                } else System.out.println("Неверный формат телефона");
            }
        }

    }

    private static void printMap(){
        if (numberFon2namePerson.size()!= 0){
            int count = 1;
            for (String key : numberFon2namePerson.keySet()) {
                System.out.println(count + ": " + key + " => " + numberFon2namePerson.get(key));
                count++;
            }
        } else {
            System.out.println("Список пуст");
        }

    }

    private static void printOnePerson(String string) {
        if (string.matches(REGEX_FON_NUMBER)) {
            System.out.println("------------------------------");
            System.out.println("Контакт существует: \n"
                    + "\t  Имя: " + numberFon2namePerson.get(string)
                    + "\n \t  Телефон: " + string);
            System.out.println("------------------------------");
        }
        if (numberFon2namePerson.containsValue(string)){
            for(String entry : numberFon2namePerson.keySet()) {
                String value = numberFon2namePerson.get(entry);
                if (value.equalsIgnoreCase(string)) {
                    System.out.println("------------------------------");
                    System.out.println("Контакт существует: \n"
                            + "\t  Имя: " + value
                            + "\n \t  Телефон: " + entry);
                    System.out.println("------------------------------");
                }
            }
        }

    }

    public static void main(String[] args) {
        userInput();
    }

}