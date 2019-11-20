import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
public class Parser {



    private static long fragmentExpense;
    final static int INDEX_CONTRACTOR = 5;
    final static int POSITION_CONTRACTOR_START = 21;
    final static int POSITION_CONTRACTOR_FINISH = 70;
    final static int INDEX_PARISH = 6;
    final static int INDEX_EXPENSE = 7;
    final static String REGEX_EXPENSE_REPLACE = "[^([0-9]*.[0-9]*)]";
    final static String REGEX_EXPENSE_MATCHES = "\\d+";

    private static ArrayList<Transaction> ParseFile(String pathFile) {
        ;
        ArrayList<Transaction> transactions = new ArrayList<>();
        try {
            List<String> string = Files.readAllLines(Paths.get(pathFile));

            for (int i = 1; i < string.size(); i++) {
                String[] fragments = string.get(i).split(",",8);
                if (fragments.length != 8) {
                    System.out.println("Ошибка длинны строки.");
                    continue;
                }

                fragments[INDEX_CONTRACTOR] = fragments[INDEX_CONTRACTOR].substring(POSITION_CONTRACTOR_START, POSITION_CONTRACTOR_FINISH);
                if (fragments[INDEX_EXPENSE].matches(REGEX_EXPENSE_MATCHES)) {
                    fragmentExpense = Long.parseLong(fragments[INDEX_EXPENSE]) * 100;
                } else {
                    fragments[INDEX_EXPENSE] = fragments[INDEX_EXPENSE].replaceAll(",", "").trim();
                    fragments[INDEX_EXPENSE] = fragments[INDEX_EXPENSE].replaceAll(REGEX_EXPENSE_REPLACE, "").trim();
                    fragmentExpense = Long.parseLong(fragments[INDEX_EXPENSE]);
                }

                transactions.add(new Transaction(fragments[INDEX_CONTRACTOR],
                        Long.parseLong(fragments[INDEX_PARISH]) * 100,
                        fragmentExpense));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



        return transactions;
    }

    public static void print(String pathFile) {
        ArrayList<Transaction> transactions = ParseFile(pathFile);

        System.out.println("------------------------------");
        System.out.print("\t Общий приход = ");
        System.out.println(transactions.stream().mapToLong(Transaction::getSumParish).sum());
//        transactions.stream()
//                    .filter(e -> e.getSumParish() > 0)
//                    .map(e -> e.getSumParish())
//                    .reduce((s1, s2) -> s1 + s2)
//                    .ifPresent(System.out::print);
        System.out.println("\t    ---------------");
        System.out.print("\t Общий расход = ");
        System.out.println(transactions.stream().mapToLong(Transaction::getSumExpense).sum());
//        transactions.stream()
//                    .map(e -> e.getSumExpense())
//                    .reduce((s1, s2) -> s1 + s2)
//                    .ifPresent(System.out::print);
        System.out.println("------------------------------");

        System.out.println("Виды расходов: \t");
        Map<String, Long> typeExpenditure = transactions.stream()
                .filter(e -> e.getSumExpense() > 0)
                .collect(Collectors
                        .groupingBy(Transaction::getContractor, Collectors
                                .summingLong(Transaction::getSumExpense)));

        typeExpenditure.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed()).forEach(System.out::println);

    }

}