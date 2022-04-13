package Payroll;

public class ChangeEmployeeAddress extends ChangeEmployeeTransaction{
    String transactionEmployeeAddress;

    public ChangeEmployeeAddress(int transactionEmployeeId, String transactionEmployeeAddress) {
        super(transactionEmployeeId);
        this.transactionEmployeeAddress = transactionEmployeeAddress;
    }


    @Override
    public void change(Employee employee) {
        employee.setAddress(transactionEmployeeAddress);
    }
}
