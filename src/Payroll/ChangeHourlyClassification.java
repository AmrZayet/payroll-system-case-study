package Payroll;

public class ChangeHourlyClassification extends ChangeClassificationTransaction {
    double transactionHourlyRate;

    public ChangeHourlyClassification(int transactionEmployeeId, double transactionHourlyRate) {
        super(transactionEmployeeId);
        this.transactionHourlyRate = transactionHourlyRate;
    }

    @Override
    public PaymentClassification getPaymentClassification() {
        return new HourlyClassification(transactionHourlyRate);
    }

    @Override
    public PaymentSchedule getPaymentSchedule() {
        return new WeeklySchedule();
    }
}
