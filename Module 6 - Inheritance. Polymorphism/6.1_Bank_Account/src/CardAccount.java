public class CardAccount extends BankAccount {

    private static double commissionPercents = 1.0;

    @Override
    public void withDrawal(double money) {
        double commision =  money * commissionPercents / 100;
        double moneyWithCommision = money + commision;
        super.withDrawal(moneyWithCommision);
    }
    @Override
    public void withDrawal(int money) {
       withDrawal(Double.valueOf(money));
    }
}
