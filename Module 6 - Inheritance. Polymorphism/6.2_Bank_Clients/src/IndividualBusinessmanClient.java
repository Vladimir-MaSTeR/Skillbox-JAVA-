public class IndividualBusinessmanClient extends Clients {

    private static double maxCommissionPercents = 1.0;
    private static double minCommissionPercents = 0.5;
    private static double sumIncreasePercents = 1000;

    @Override
    public void enroll(double money) {
        double commission = money * (money >= sumIncreasePercents ? minCommissionPercents : maxCommissionPercents ) / 100;
        super.enroll(money - commission);
    }
    @Override
    public void enroll(int money) {
        enroll(Double.valueOf(money));
    }
}
