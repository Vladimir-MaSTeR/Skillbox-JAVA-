public abstract class Clients {

   private double settlementAccount = 0;

   public void getSettlementAccount(){
       System.out.println("Баланс =" + settlementAccount + " р.");
   }
   public void enroll(double money) {
       settlementAccount += money;
       System.out.println("Баланс обновлен");
   }
   public void enroll(int money) {
       enroll(Double.valueOf(money));
   }

   public void withDrawal (double money) {
       if (money <= settlementAccount) {
           settlementAccount -= money;
           System.out.println("Списание средств выполненно успешно");
       } else {
           System.out.printf("Не достаточно средств");
       }
   }
   public void withDrawal (int money) {
       withDrawal(Double.valueOf(money));
   }
}
