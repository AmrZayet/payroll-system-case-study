package Payroll;

public class AddSalariedEmployee extends AddEmployeeTransaction{
    double transactionEmployeeSalary;

    public AddSalariedEmployee(int employeeId, String name, String address, double transactionEmployeeSalary) {
        super(employeeId, name, address);
        this.transactionEmployeeSalary = transactionEmployeeSalary;
    }

    @Override
    public PaymentClassification getClassification() {
        return new SalariedClassification(transactionEmployeeSalary);
    }

    @Override
    public PaymentSchedule getSchedule() {
        return new MonthlySchedule();
    }
}
