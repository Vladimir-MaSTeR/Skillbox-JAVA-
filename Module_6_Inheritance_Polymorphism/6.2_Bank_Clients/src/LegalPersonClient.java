public class LegalPersonClient extends Clients {

    private static double commissionPercents = 1.0;

    @Override
    public void withDrawal(double money) {
        double commission = money * commissionPercents / 100;
        super.withDrawal(money + commission);
    }
    @Override
    public void withDrawal(int money) {
        withDrawal(Double.valueOf(money));
    }
}
