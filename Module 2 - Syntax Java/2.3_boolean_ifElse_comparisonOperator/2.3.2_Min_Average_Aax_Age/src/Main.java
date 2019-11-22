public class Main {
    public static void main(String[] args) {

        int vasyaAge = 25;
        int katyaAge = 22;
        int mishaAge = 35;

        int min = -1;
        int middle = -1;
        int max = -1;

        // ниходим минимум
            if (vasyaAge < katyaAge && vasyaAge < mishaAge) {
                min = vasyaAge;
            } else if (katyaAge < mishaAge && katyaAge < vasyaAge) {
                min = katyaAge;
            } else if (mishaAge < vasyaAge && mishaAge < katyaAge) {
                min = mishaAge;
            }

        // ниходим максимум
            if (vasyaAge > katyaAge && vasyaAge > mishaAge) {
                max = vasyaAge;
            } else if (katyaAge > mishaAge && katyaAge > vasyaAge) {
                max = katyaAge;
            } else if (mishaAge > vasyaAge && mishaAge > katyaAge) {
                max = mishaAge;
            }

        // ниходим середину
            if (vasyaAge != min && vasyaAge != max) {
                middle = vasyaAge;
            } else if (katyaAge != min && katyaAge != max) {
                middle = katyaAge;
            } else if (mishaAge != vasyaAge && mishaAge != katyaAge) {
                middle = mishaAge;
            }

        // Вывод в консоль
        System.out.println("Min = " + min);
        System.out.println("Middle = " + middle);
        System.out.println("Max = " + max);

    }
}
