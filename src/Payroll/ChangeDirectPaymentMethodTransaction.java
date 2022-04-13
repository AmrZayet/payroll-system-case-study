package Payroll;

public class ChangeDirectPaymentMethodTransaction extends ChangePaymentMethodTransaction {
    String transactionEmployeeBank;
    String transactionEmployeeAccountNumber;

    public ChangeDirectPaymentMethodTransaction(int transactionEmployeeId, String transactionEmployeeBank, String transactionEmployeeAccountNumber) {
        super(transactionEmployeeId);
        this.transactionEmployeeBank = transactionEmployeeBank;
        this.transactionEmployeeAccountNumber = transactionEmployeeAccountNumber;
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return new DirectPaymentMethod(transactionEmployeeBank, transactionEmployeeAccountNumber);
    }
}
