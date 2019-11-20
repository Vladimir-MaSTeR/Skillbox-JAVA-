import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Company company = new Company();

         company.hiringEmployee(new Operator());
         company.hiringEmployee(new Operator());

        for (int i = 0; i < 168; i++) {
            SalesManager salesManager = new SalesManager();
            company.hiringEmployee(salesManager);
        }
        for (int j = 0; j < 100; j++) {
             TopManager topManager = new TopManager(company);
             company.hiringEmployee(topManager);
         }

        System.out.println("Нанято " + company.getCompanyEmployee().size() + " сотрудников");
        System.out.println("Месячный доход компании = " + company.getMonthIncomeCompany() + " р.");

        company.getTopSalaryStaff(8);
        company.getLowestSalaryStaff(10);


    }
}
