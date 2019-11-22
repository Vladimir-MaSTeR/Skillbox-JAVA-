import java.util.HashMap;

public class Main {

    private static Integer TREADS_QUANTITY = 16; //кол-во потоков
    private static Integer ACCOUNTS_QUANTITY = 100; //количество сгенерированных аккаунтов
    private static Integer TRANSFERS_QUANTITY = 10; //кол-во транзакций каждого потока

    public static void main(String[] args) throws InterruptedException {

        Bank bank = new Bank();
        HashMap<Integer, Account> accountHashMap = new HashMap<>();

        //Генерируем аккаунты
        long money = 100000000 + (long) (Math.random() * 1000000000);            // получаем случайное число в диапозоне от 100 миллионов до 1000 милионов
        for (int i = 0; i < ACCOUNTS_QUANTITY; i++) {
              accountHashMap.put(i, (new Account(money, i)));                    // Генерируем аккаунт и заносим в мапу
        }

        System.out.println("Аккаунты сгенерированны в количестве | " + ACCOUNTS_QUANTITY);
        bank.setAccounts(accountHashMap);
       //----------------------------------

        for (int i = 1; i <= TREADS_QUANTITY; i++) {
              new Thread(() -> {
                  try {

                      System.out.println("\n Работает поток " + Thread.currentThread().getName() + " ");

                      for (int j = 0; j < TRANSFERS_QUANTITY; j++) {
                          int amount = 1 + (int) (Math.random() * 51000);
                          int fromAccount = (int) (Math.random() * ACCOUNTS_QUANTITY);
                          int toAccount = (int) (Math.random() * ACCOUNTS_QUANTITY);

                            if (fromAccount == toAccount) {
                                if (toAccount == ACCOUNTS_QUANTITY - 1 ) toAccount--;
                                else toAccount++;
                            }

                          System.out.print("\t Новая транзакция: \n" +
                                  "\t \t с аккаунта № " + fromAccount + "\n" +
                                  "\t \t на аккаунт № " + toAccount + "\n" +
                                  "\t \t Сумма перевода: " + amount);

                              bank.transfer(fromAccount, toAccount, amount);
//                             Thread.sleep(1000);
                      }
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }).start();
        }
    }
}


//                 private static Double BIG_TRANSFERS_PERCENT = 0.05; //процент транзакций для проверки
//                      int amount;
//                      if (TRANSFERS_QUANTITY % (TRANSFERS_QUANTITY * BIG_TRANSFERS_PERCENT) != 0) {
//                              amount = 60000;
//                              } else {
//                              amount = 1 + (int) (Math.random() * 1000);