public class Account
{
    private long money;
    private int accNumber;
    private volatile boolean isBloc = false;

    public Account(long money, int accNumber) {
        this.money = money;
        this.accNumber = accNumber;
    }

    public long getMoney() {
        return money;
    }
    public void setMoney(long money) {
        this.money = money;
    }

    public int getAccNumber() {
        return accNumber;
    }
    public void setAccNumber(int accNumber) {
        this.accNumber = accNumber;
    }

    public boolean isBloc() {
        return isBloc;
    }
    public void setBloc(boolean bloc) {
        isBloc = bloc;
    }
}
