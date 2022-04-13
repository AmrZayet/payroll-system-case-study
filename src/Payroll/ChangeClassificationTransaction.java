package Payroll;

public abstract class ChangeClassificationTransaction extends ChangeEmployeeTransaction {
    public ChangeClassificationTransaction(int transactionEmployeeId) {
        super(transactionEmployeeId);
    }

    @Override
    public void change(Employee employee) {
        employee.setPaymentClassification(getPaymentClassification());
        employee.setPaymentSchedule(getPaymentSchedule());
    }

    public abstract PaymentClassification getPaymentClassification();
    public abstract PaymentSchedule getPaymentSchedule();
}
