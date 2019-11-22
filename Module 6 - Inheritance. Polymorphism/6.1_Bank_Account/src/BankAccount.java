import java.time.LocalDate;
import java.util.Calendar;

public class BankAccount {

        private double money = 0;

   public void getMoney() {
        System.out.println("На Вашем счету = " + money + " р.");
    }

   public void enroll(double money) {
       this.money += money;
       System.out.println("Баланс пополнен.");

   }
   public void enroll(int money) {
       enroll(Double.valueOf(money));
    }            //  метод перегрузил специально что бы можно было вводить целое число. Для удобства в общем.

   public void withDrawal(double money) {
        if (this.money > money) {
            this.money -= money;
            System.out.println("Со счета списанно " + money + " р. Баланс = " + this.money);
        } else {
            System.out.println("Не достаточно маны :(");
        }

    }
   public void withDrawal(int money) {
       withDrawal(Double.valueOf(money));
    }       //  метод перегрузил специально что бы можно было вводить целое число. Для удобства в общем.
}
