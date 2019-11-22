import java.util.HashMap;
import java.util.Random;

public class Bank
{
    private HashMap<Integer, Account> accounts;
    private final Random random = new Random();

    public synchronized boolean isFraud(int fromAccountNum, int toAccountNum, long amount) throws InterruptedException
    {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public void  transfer(int fromAccountNum, int toAccountNum, long amount) throws InterruptedException
    {

      Account from = accounts.get(fromAccountNum);
      Account to   = accounts.get(toAccountNum);

      Account lowSyncAccount;
      Account topSyncAccount;

        if (from.hashCode() < to.hashCode()) {
            lowSyncAccount = from;
            topSyncAccount = to;
        } else {
            lowSyncAccount = to;
            topSyncAccount = from;
        }
    synchronized (lowSyncAccount) {
     synchronized (topSyncAccount) {
                    if (from.isBloc() || to.isBloc()) {
                        System.out.println(" Счета заблокированы");
                        return;
                    }
                    if (from.getMoney() < amount) {
                        System.out.println(" Не достаточно средств для перевода");
                        return;
                    }

                    from.setMoney(from.getMoney() - amount);
                    to.setMoney(to.getMoney() + amount);

                    if (amount > 50000) {
                        System.out.println(" Транзакция отправленна на проверку.");

                        if (isFraud(from.getAccNumber(), to.getAccNumber(), amount)) {
                            from.setBloc(true);
                            to.setBloc(true);
                            System.out.println(" ВНИМАНИЕ: Проверка не пройдена! Счета заблокированны.");
                        } else {
                            System.out.println(" Проверка пройдена. Транзакция успешна");
                        }

                    } else {
                        System.out.println(" Транзакция успешна");
                    }
                }
            }

    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(int accountNum)
    {
        Account account = accounts.get(accountNum);
        return  account.getMoney();
    }

    public HashMap<Integer, Account> getAccounts() {
        return accounts;
    }
    public void setAccounts(HashMap<Integer, Account> accounts) {
        this.accounts = accounts;
    }
}
