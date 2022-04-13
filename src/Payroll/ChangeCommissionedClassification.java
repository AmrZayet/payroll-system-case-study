package Payroll;

public class ChangeCommissionedClassification extends ChangeClassificationTransaction {
    double transactionSalary;
    double transactionCommissionRate;

    public ChangeCommissionedClassification(int transactionEmployeeId, double transactionSalary, double transactionCommissionRate) {
        super(transactionEmployeeId);
        this.transactionSalary = transactionSalary;
        this.transactionCommissionRate = transactionCommissionRate;
    }

    @Override
    public PaymentClassification getPaymentClassification() {
        return new CommissionedClassification(transactionSalary, transactionCommissionRate);
    }

    @Override
    public PaymentSchedule getPaymentSchedule() {
        return new BiweeklySchedule();
    }
}
