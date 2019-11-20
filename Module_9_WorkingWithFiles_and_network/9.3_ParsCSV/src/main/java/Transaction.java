import javax.xml.transform.sax.SAXResult;
import java.time.LocalDate;

public class Transaction {


    private String contractor;    // подрядчик
    private long sumParish;   // Общий приход
    private long sumExpense;   // Общий расход

    public Transaction(String contractor, long sumParish, long sumExpense) {
        this.contractor = contractor;
        this.sumParish = sumParish;
        this.sumExpense = sumExpense;
    }

    public String getContractor() {
        return contractor;
    }
    public long getSumParish() {
        return sumParish;
    }
    public long getSumExpense() {
        return sumExpense;
    }
}
