package Payroll;

public class ChangeEmployeeName extends ChangeEmployeeTransaction {
    String transactionEmployeeName;

    public ChangeEmployeeName(int transactionEmployeeId, String transactionEmployeeName) {
        super(transactionEmployeeId);
        this.transactionEmployeeName = transactionEmployeeName;
    }


    @Override
    public void change() {
        Employee employee = PayrollDatabase.getEmployee(transactionEmployeeId);
        if (employee != null) {
            employee.setName(transactionEmployeeName);
        } else {
            System.err.println("Can't Change the name because here is no employee with this ID");
        }
    }
}
