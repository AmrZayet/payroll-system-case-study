package Payroll;

public class ChangeEmployeeAddress extends ChangeEmployeeTransaction{
    String transactionEmployeeAddress;

    public ChangeEmployeeAddress(int transactionEmployeeId, String transactionEmployeeAddress) {
        super(transactionEmployeeId);
        this.transactionEmployeeAddress = transactionEmployeeAddress;
    }


    @Override
    public void change() {
        Employee employee = PayrollDatabase.getEmployee(transactionEmployeeId);
        if (employee != null) {
            employee.setAddress(transactionEmployeeAddress);
        } else {
            System.err.println("\"Can't Change the address because here is no employee with this ID\"");
        }
    }
}
