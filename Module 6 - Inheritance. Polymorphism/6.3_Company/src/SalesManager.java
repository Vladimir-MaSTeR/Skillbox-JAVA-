public class SalesManager implements Employee {

    private static int monthSalaryManager = 20000;
    private static int percentsIncome = 5;
    private int companyIncomeManager =  (int) (Math.random() * 100000); // ограничил специально для простоты


    @Override
    public int getMonthSalary() {
        return monthSalaryManager + (CompanyIncomeManager() * percentsIncome / 100);
    }

    @Override
    public int CompanyIncomeManager() {
        return  companyIncomeManager;
    }
    //    public int getCompanyIncomeManager() {
//        return companyIncomeManager;
//    }
}
