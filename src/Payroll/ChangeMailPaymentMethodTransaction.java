package Payroll;

public class ChangeMailPaymentMethodTransaction extends ChangePaymentMethodTransaction {
    String transactionEmployeeMailAddress;

    public ChangeMailPaymentMethodTransaction(int transactionEmployeeId, String transactionEmployeeMailAddress) {
        super(transactionEmployeeId);
        this.transactionEmployeeMailAddress = transactionEmployeeMailAddress;
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return new MailPaymentMethod(transactionEmployeeMailAddress);
    }
}
