package Payroll;

public abstract class ChangePaymentMethodTransaction extends ChangeEmployeeTransaction {
    public ChangePaymentMethodTransaction(int transactionEmployeeId) {
        super(transactionEmployeeId);
    }

    @Override
    public void change(Employee employee) {
        employee.setPaymentMethod(getPaymentMethod());
    }

    public abstract PaymentMethod getPaymentMethod();
}
