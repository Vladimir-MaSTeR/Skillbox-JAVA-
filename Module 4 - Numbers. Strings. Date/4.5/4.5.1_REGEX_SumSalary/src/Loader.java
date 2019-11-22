
public class Loader
{
    public static void main(String[] args) {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";

        String[] prise = text.split("\\,\\s+");

        int vasyaSalary = Integer.parseInt(prise[0].replaceAll("[^0-9]", ""));
        int petyaSalary = Integer.parseInt(prise[1].replaceAll("[^0-9]", ""));
        int mashaSalary = Integer.parseInt(prise[2].replaceAll("[^0-9]", ""));

        System.out.println("------------------------------");
        System.out.print("\t" + "Salary Vasya: " + vasyaSalary + "\n \t" + "Salary Petr: " + petyaSalary + "\n \t" + "Salary Masha: " + mashaSalary + "\n");
        System.out.println("------------------------------");
        System.out.print("\t" + "Amount: " + (vasyaSalary + petyaSalary + mashaSalary));
    }
}