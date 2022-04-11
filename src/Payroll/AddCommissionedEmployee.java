package Payroll;

public class AddCommissionedEmployee extends AddEmployeeTransaction {
    double transactionEmployeeSalary;
    double transactionEmployeeCommissionRate;

    public AddCommissionedEmployee(int employeeId, String name, String address, double transactionEmployeeSalary, double transactionEmployeeCommissionRate) {
        super(employeeId, name, address);
        this.transactionEmployeeSalary = transactionEmployeeSalary;
        this.transactionEmployeeCommissionRate = transactionEmployeeCommissionRate;
    }


    @Override
    public PaymentClassification getClassification() {
        return new CommissionedClassification(transactionEmployeeSalary, transactionEmployeeCommissionRate);
    }

    @Override
    public PaymentSchedule getSchedule() {
        return new BiweeklySchedule();
    }
}
