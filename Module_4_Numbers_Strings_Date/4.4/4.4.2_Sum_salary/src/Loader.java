
public class Loader
{
    public static void main(String[] args)
    {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";

        int indexVasya = text.indexOf('5');
        int salaryVasya = Integer.parseInt(text.substring(indexVasya, indexVasya + 4));

        int indexMasha = text.lastIndexOf('3');
        int salaryMasha = Integer.parseInt(text.substring(indexMasha, indexMasha + 5));

        int  sum = salaryVasya + salaryMasha;

        System.out.print("Зарплата Васи: " + salaryVasya + "\n" + "Зарплата Маши: " + salaryMasha + "\n" + "Сумма: " + sum);
    }
}