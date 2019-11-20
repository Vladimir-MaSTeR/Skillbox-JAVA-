public class TopManager implements Employee {

    private static int monthSalaryTopManager = 50000;
    private static int bonus = 25000;
    private Company incomeCompany;
    // private int companyIncomeTopManager = 50000 + (int) (Math.random() * 100000); // ограничил специально для простоты

    public TopManager(Company incomeCompany) {
        this.incomeCompany = incomeCompany;
    }

    @Override
    public int getMonthSalary() {

        return incomeCompany.getMonthIncomeCompany() > 10000000 ? monthSalaryTopManager + bonus : monthSalaryTopManager;
    }

//    public int getCompanyIncomeTopManager() {
//        return companyIncomeTopManager;
//    }
}
