import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DepositoryAccount extends BankAccount {

    private LocalDate dateLastDraw;

    @Override
    public void enroll(double money) {
        dateLastDraw = LocalDate.now();
        super.enroll(money);

    }
    @Override
    public void enroll(int money) {
    enroll(Double.valueOf(money));
    }

    @Override
    public void withDrawal(double money) {
        LocalDate date = LocalDate.now();
        if (date.isAfter(dateLastDraw.plusMonths(1))){
            super.withDrawal(money);
        } else {
            System.out.println("Снятие не возможно. Должен пройти 1 месяц");
        }

    }
    @Override
    public void withDrawal(int money) {
     withDrawal(Double.valueOf(money));
    }
}
