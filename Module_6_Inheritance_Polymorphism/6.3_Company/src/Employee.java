public interface Employee {

    int getMonthSalary();

    public default int CompanyIncomeManager () {
        return 0;
    }
}