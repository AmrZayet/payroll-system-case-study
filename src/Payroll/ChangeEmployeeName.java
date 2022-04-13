package Payroll;

public class ChangeEmployeeName extends ChangeEmployeeTransaction {
    String transactionEmployeeName;

    public ChangeEmployeeName(int transactionEmployeeId, String transactionEmployeeName) {
        super(transactionEmployeeId);
        this.transactionEmployeeName = transactionEmployeeName;
    }


    @Override
    public void change(Employee employee) {
        employee.setName(transactionEmployeeName);
    }
}
