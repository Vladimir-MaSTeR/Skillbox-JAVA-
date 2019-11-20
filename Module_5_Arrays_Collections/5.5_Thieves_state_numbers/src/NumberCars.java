import org.w3c.dom.ls.LSOutput;

import java.sql.Array;
import java.util.*;

public class NumberCars {

//    private final static String REGEX_CAR_NUMBER = "\\D\\d\\d\\d\\D\\D\\d{1,3}";

    private static ArrayList<String> carNumberList = new ArrayList<>();
    private static HashSet<String> carNumberHashSet = new HashSet<>();
    private static TreeSet<String> carNumberTreeSet = new TreeSet<>();

    public static void inputPerson() {
        Scanner scanner = new Scanner(System.in);
        addList();

        for (; ; ) {
            System.out.println("Введите гос. номер (формат А111АА000)");
            String stringInput = scanner.nextLine();

            searchArrayList(stringInput);
            searchBinaryArrayList(stringInput);
            searchHashSet(stringInput);
            searchTreeSet(stringInput);

        }
    }

    public static void addList() {
        String[] chars = {"А", "В", "Е", "М", "Н", "О", "Р", "С", "Т", "У", "A", "V", "E", "M", "N", "O", "R", "S", "U", "Y"};


        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < 10; j++) {
                for (int y = 1; y <= 197; y++) {
                    String number = String.format("%s%d%d%d%s%s%d", chars[i], j, j, j, chars[i], chars[i], y);
                    carNumberList.add(number);
                }

            }
        }
         Collections.sort(carNumberList);
        carNumberHashSet.addAll(carNumberList);
        carNumberTreeSet.addAll(carNumberList);

    }

    public static void searchArrayList(String stringInput) {
        long startTime = System.currentTimeMillis();
       boolean search = carNumberList.contains(stringInput);
        long duration = System.currentTimeMillis() - startTime;

        if (search) {
            System.out.println("В ArrayList,е номер найден. " + "Время поиска => " + duration + " Милисекунд");
        } else {
            System.out.println("В ArrayList,е номер не найден " + "Время поиска => " + duration + " Милисекунд");
        }
    }

    public static void searchBinaryArrayList(String stringInput) {

        double sTime = System.currentTimeMillis();
        int c = Collections.binarySearch(carNumberList, stringInput);
        double dTime = System.currentTimeMillis() - sTime;

        if (c > 0) {
            System.out.println("Бинарным поиском, номер найден. " + "Время поиска => " + dTime + " Милисекунд");
        } else {
            System.out.println("Бинарным поиском, номер не найден. " + "Время поиска => " + dTime + " Милисекунд");
        }
        System.out.println(c);
    }

    public static void searchHashSet(String stringInput) {

        double startT = System.currentTimeMillis();
        boolean search = carNumberHashSet.contains(stringInput);
        double endTime = System.currentTimeMillis() - startT;

        if (search) {
            System.out.println("В HashSet,е номер найден. " + "Время поиска  " + endTime + " Милисекунд");
        } else {
            System.out.println("В HashSet,е номер не найден. " + "Время поиска  " + endTime + " Милисекунд");
        }

    }

    public static void searchTreeSet(String stringInput) {
        double sT = System.currentTimeMillis();
        boolean search = carNumberTreeSet.contains(stringInput);
        double eT = System.currentTimeMillis() - sT;

        if (search) {
            System.out.println("В TreeSet,е номер найден. " + "Время поиска " + eT + " Милисекунд");
        } else {
            System.out.println("В TreeSet,е номер не найден " + "Время поиска " + eT + " Милисекунд");
        }
        System.out.println("==============================");
    }


    public static void main(String[] args) {
        inputPerson();

    }

}