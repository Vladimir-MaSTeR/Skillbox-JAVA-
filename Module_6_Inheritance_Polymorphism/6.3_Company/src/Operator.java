public class Operator implements Employee {

    private static int salaryOperator = 15000;

    @Override
    public int getMonthSalary() {
        return salaryOperator;
    }
}
