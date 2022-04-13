package Payroll;

public class ChangeSalariedClassification extends ChangeClassificationTransaction {
    double transactionSalary;

    public ChangeSalariedClassification(int transactionEmployeeId, double transactionSalary) {
        super(transactionEmployeeId);
        this.transactionSalary = transactionSalary;
    }

    @Override
    public PaymentClassification getPaymentClassification() {
        return new SalariedClassification(transactionSalary);
    }

    @Override
    public PaymentSchedule getPaymentSchedule() {
        return new MonthlySchedule();
    }
}
