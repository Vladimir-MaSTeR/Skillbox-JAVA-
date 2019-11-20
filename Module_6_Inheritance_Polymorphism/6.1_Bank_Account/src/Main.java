import java.util.Calendar;

public class Main {
    public static void main(String[] args) {

     BankAccount bankAccount = new BankAccount();
       bankAccount.getMoney();
       bankAccount.enroll(600);
       bankAccount.getMoney();
       bankAccount.withDrawal(245);
       bankAccount.withDrawal(44123);
        System.out.println("-----------------");

     CardAccount cardAccount = new CardAccount();
        cardAccount.enroll(600.89);
        cardAccount.getMoney();
        cardAccount.withDrawal(43.12);
        System.out.println("-----------------");

     DepositoryAccount depositoryAccount = new DepositoryAccount();
        depositoryAccount.enroll(555);
        depositoryAccount.getMoney();
        depositoryAccount.withDrawal(123.50);
        depositoryAccount.getMoney();


    }
}
