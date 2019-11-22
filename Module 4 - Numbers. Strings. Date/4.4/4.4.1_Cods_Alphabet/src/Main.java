public class Main {

    public static void main(String[] args) {
        Main main = new Main();

        main.englishAlphabet();
        main.russianAlphabet();
    }

    public void englishAlphabet() {
        System.out.println("Английский алфавит:");
        for(char i = 'a'; i <= 'z'; i++){
            int codaz = i;
            System.out.print("|" + i + " : " + codaz);
        }
        System.out.print("\n");
        for (char i = 'A'; i <= 'Z'; i++) {
            int codAZ = i;
            System.out.print("|" +  i + " : " + codAZ);
        }
        System.out.print("\n");
    }

    public void russianAlphabet() {
        System.out.println("Русский алфавит:");
        for(char i = 'а'; i <= 'я'; i++){
            int codaya = i;
            System.out.print("|" + i + " : " + codaya);
        }
        System.out.print("\n");
        for (char i = 'А'; i <= 'Я'; i++) {
            int codAYA = i;
            System.out.print("|" +  i + " : " + codAYA);
        }
    }
}
