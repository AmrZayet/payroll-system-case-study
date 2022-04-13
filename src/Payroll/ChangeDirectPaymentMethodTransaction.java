package Payroll;

public class ChangeDirectPaymentMethodTransaction extends ChangePaymentMethodTransaction {
    public ChangeDirectPaymentMethodTransaction(int transactionEmployeeId) {
        super(transactionEmployeeId);
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return new DirectPaymentMethod();
    }
}
