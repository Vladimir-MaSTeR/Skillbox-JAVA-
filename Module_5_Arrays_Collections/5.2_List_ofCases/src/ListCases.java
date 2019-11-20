import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ListCases {

    public static ArrayList<String> casesList = new ArrayList<>();
    public static String[] userEnter;
    private static boolean startEnterUser = true;



    public static void enterUser() {
        Scanner scanner = new Scanner(System.in);
        startEnterUser = false;

        while (true) {
            System.out.println("Введите одну из команд (LIST, ADD, EDIT, DELETE). " + "Для завершения работы введите (EXIT)");
            try {
                userEnter = scanner.nextLine().split(" ");
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (isСorrespondInput("ADD", 1))  {
                addCasesList();
            } else if (isCorrespondInput("LIST")) {
                sizeCasesList();
            } else if (isСorrespondInput("EDIT", 1)) {
                editCasesList();
            } else if (isСorrespondInput("DELETE", 1)) {
                deleteCasesList();
            } else if (isCorrespondInput("EXIT")) {
                System.out.println("Досвидания");
                break;
            }
            else {
                System.out.println("Команда не найдена, попробуйте еще раз");
            }
        }
    }

    private static boolean isСorrespondInput(String regex, int minArraySize) {
        return userEnter[0].equalsIgnoreCase(regex) && userEnter.length > minArraySize;
    }
    private static boolean isCorrespondInput(String regex) {
        return isСorrespondInput(regex, 0);
    }

    public static void addCasesList() {
        String s = "";
        int numberAdd = 0;

        if (userEnter[1].matches("\\d+")) {

            numberAdd = Integer.parseInt(userEnter[1]);
               if (casesList.size() == 0){
                numberAdd = 0;
               } else if (numberAdd >= casesList.size()) {
                numberAdd = casesList.size();
               }
            for (int j = 2; j < userEnter.length; j++) {
                s += userEnter[j] + " ";
            }

            casesList.add(numberAdd, s);
            System.out.println("Добавленно");
            numberAdd = 0;
            startEnterUser = true;
        } else {
            for (int i = 1; i < userEnter.length; i++) {
                s += userEnter[i] + " ";
            }

            numberAdd = casesList.size();

            casesList.add(numberAdd, s);
            System.out.println("Добавленно");
            numberAdd = 0;
            startEnterUser = true;
        }
    }

    public static void sizeCasesList() {
        if (casesList.size() == 0) {
            System.out.println("Список пока пуст");
            startEnterUser = true;
        }

        for (int i = 0; i < casesList.size(); i++) {
            System.out.println(i + " - " + casesList.get(i));
        }


    }

    public static void editCasesList() {
      String s = "";
      int numberEdit = 0;

      if (userEnter[1].matches("\\d+")) {
          numberEdit = Integer.parseInt(userEnter[1]);

          if (numberEdit >= casesList.size()) {
              System.out.println("Элемента под таким номером не существует");
              startEnterUser = true;
          }

          for (int i = 2; i < userEnter.length; i++) {
              s += userEnter[i] + " ";
          }
          casesList.remove(numberEdit);
          casesList.add(numberEdit, s);
          System.out.println("Замена произведена");
          startEnterUser = true;
      } else {
          System.out.println("Без индекса элемента замена не возможна");
         startEnterUser = true;
      }
    }

    public static void deleteCasesList() {
        int nuberDelete = 0;

        if (userEnter[1].matches("\\d+")) {
            nuberDelete = Integer.parseInt(userEnter[1]);

            if (nuberDelete >= casesList.size()) {
                System.out.println("Элемента под таким номером несуществует");
               startEnterUser = true;
            }

            casesList.remove(nuberDelete);
            System.out.println("Элемент удален");
           startEnterUser = true;


        } else  {
            System.out.println("Без указания номера элемента, удаление невозможно");
            startEnterUser = true;
        }
    }

    public static void run() {
       if (startEnterUser == true)  enterUser();
    }


    public static void main(String[] args) {
     run();
    }
}
