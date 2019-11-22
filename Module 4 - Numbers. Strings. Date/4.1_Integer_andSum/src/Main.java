public class Main
{
    public static void main(String[] args)
    {
        Container container = new Container();
        container.count = 7843;

        // Main main = new Main();
        // System.out.println(main.sumDigits(container.count));

    }

    public Integer sumDigits(Integer number)
    {
        //@TODO: write code here
        int sum = 0;
        for (int i = 0; i < number.toString().length(); i++) {
            sum = sum + Integer.parseInt(number.toString().charAt(i) + "");
        }
        return sum;
    }
}
