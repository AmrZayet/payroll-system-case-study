package Payroll;

public class ChangeHoldPaymentMethodTransaction extends ChangePaymentMethodTransaction {
    public ChangeHoldPaymentMethodTransaction(int transactionEmployeeId) {
        super(transactionEmployeeId);
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return new HoldPaymentMethod();
    }
}
