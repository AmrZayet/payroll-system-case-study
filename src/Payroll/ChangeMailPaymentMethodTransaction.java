package Payroll;

public class ChangeMailPaymentMethodTransaction extends ChangePaymentMethodTransaction {
    public ChangeMailPaymentMethodTransaction(int transactionEmployeeId) {
        super(transactionEmployeeId);
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return new MailPaymentMethod();
    }
}
