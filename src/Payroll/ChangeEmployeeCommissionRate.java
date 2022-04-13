package Payroll;

public class ChangeEmployeeCommissionRate extends ChangeEmployeeTransaction {
    double transactionCommissionRate;

    public ChangeEmployeeCommissionRate(int transactionEmployeeId, double transactionCommissionRate) {
        super(transactionEmployeeId);
        this.transactionCommissionRate = transactionCommissionRate;
    }

    @Override
    public void change(Employee employee) {
        PaymentClassification paymentClassification = employee.getPaymentClassification();
        if (paymentClassification instanceof CommissionedClassification) {
            CommissionedClassification commissionedClassification = (CommissionedClassification) paymentClassification;
            commissionedClassification.setCommissionRate(transactionCommissionRate);
        } else {
            System.err.println("Can't change commissionRate for an employee that is not commissioned!");
        }
    }
}
