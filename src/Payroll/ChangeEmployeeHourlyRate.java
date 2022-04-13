package Payroll;

public class ChangeEmployeeHourlyRate extends ChangeEmployeeTransaction {
    double transactionHourlyRate;

    public ChangeEmployeeHourlyRate(int transactionEmployeeId, double transactionHourlyRate) {
        super(transactionEmployeeId);
        this.transactionHourlyRate = transactionHourlyRate;
    }

    @Override
    public void change(Employee employee) {
        PaymentClassification paymentClassification = employee.getPaymentClassification();
        if (paymentClassification instanceof HourlyClassification) {
            HourlyClassification hourlyClassification = (HourlyClassification) paymentClassification;
            hourlyClassification.setHourlyRate(transactionHourlyRate);
        } else {
            System.err.println("Can't change commissionRate for an employee that is not commissioned!");
        }
    }
}
