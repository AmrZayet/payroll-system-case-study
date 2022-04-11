package Payroll;

public class AddHourlyEmployee extends AddEmployeeTransaction {

    double transactionEmployeeHourlyRate;

    public AddHourlyEmployee(int employeeId, String name, String address, double transactionEmployeeHourlyRate) {
        super(employeeId, name, address);
        this.transactionEmployeeHourlyRate = transactionEmployeeHourlyRate;
    }

    @Override
    public PaymentClassification getClassification() {
        return new HourlyClassification(transactionEmployeeHourlyRate);
    }

    @Override
    public PaymentSchedule getSchedule() {
        return new WeeklySchedule();
    }
}
