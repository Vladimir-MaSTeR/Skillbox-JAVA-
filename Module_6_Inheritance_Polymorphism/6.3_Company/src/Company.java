import java.util.*;
import java.util.ArrayList;

public class Company {

    private int monthIncomeCompany;
    private ArrayList<Employee> companyEmployee = new ArrayList<>();

    public void hiringEmployee(Employee employee) {
        companyEmployee.add(employee);
        this.monthIncomeCompany += employee.CompanyIncomeManager();

    }
    public void dismissalEmployee(Employee employee) {
        companyEmployee.remove(employee);
        this.monthIncomeCompany -= employee.CompanyIncomeManager();
    }

    public TreeSet<Employee> getTopSalaryStaff(int count) {
          TreeSet<Employee> topSalary = new TreeSet<>(new CompanyComparatorOne().reversed());
          for (int i = 0; i < count; i++) {
              topSalary.add(companyEmployee.get(i));
          }

          return topSalary;
    }

    public TreeSet<Employee> getLowestSalaryStaff(int count){
        TreeSet<Employee> lowestSalary = new TreeSet<>(new CompanyComparatorOne());
        for (int i = 0; i < count; i++) {
            lowestSalary.add(companyEmployee.get(i));
        }
        return lowestSalary;
    }


    public int getMonthIncomeCompany() {
        return monthIncomeCompany;
    }

    public ArrayList<Employee> getCompanyEmployee() {
        return companyEmployee;
    }
}
