import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TrucksContainersBox {

    public static void main(String[] args) {
        print();
    }

    public static int box;

    public static int containers = getContainers();
    public static int trucks = getTrucks();


    public static int getContainers () {
        containers = box / 27;
        if (box % 27 > 0) {
            containers += 1;
        }
        return containers;
    }

    public static int getTrucks() {
        trucks = containers / 12;
        if (containers % 12 > 0){
            trucks += 1;
        }
        return trucks;
    }

    public static void userInput () {
        System.out.println("Введите число ящиков");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            box = Integer.parseInt(reader.readLine());
            reader.close();
        } catch (Exception e) {
            System.out.println("| ОШИБКА | ЧИСЛО ДОЛЖНО БЫТЬ ЦЕЛЫМ");
        }

        getContainers();
        getTrucks();
    }

    public static void print() {
         int numberBox = 0;
         int numberContainers = 0;
         int numberTrucks = 0;

         userInput();

        System.out.println("===========================================================================");
        System.out.println(" В наличии | Ящики = " + box + " | Контейнеры = " + containers + " | Грузовики = " + trucks + " |");
        System.out.println("---------------------------------------------------------------------------");
        System.out.println(" Содержимое:");

        while (numberTrucks < trucks) {
            if (numberTrucks < trucks) {
                System.out.println("   Грузовик " + (numberTrucks + 1) + ":");
            }
            for (int i = 0; i < 12; i++) {
                System.out.println("       Контейнер " + (numberContainers + 1) + ":");
                   for (int j = 0; j < 27; j++) {
                    System.out.println("            Ящик " + (numberBox + 1) + ":");
                    numberBox++;
                    if (numberBox == box) break;
                   }
                numberContainers++;
                if (numberContainers == containers) break;
            }


            numberTrucks++;
        }

        System.out.println("---------------------------------------------------------------------------");
        System.out.println(" В наличии | Ящики = " + box + " | Контейнеры = " + containers + " | Грузовики = " + trucks + " |");
        System.out.println("===========================================================================");

    }



}
