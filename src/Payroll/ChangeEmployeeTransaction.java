package Payroll;

public abstract class ChangeEmployeeTransaction implements Transaction {
    int transactionEmployeeId;

    public ChangeEmployeeTransaction(int transactionEmployeeId) {
        this.transactionEmployeeId = transactionEmployeeId;
    }

    @Override
    public void execute() {
        Employee employee = PayrollDatabase.getEmployee(transactionEmployeeId);
        if (employee != null) {
            change(employee);
        } else {
            System.err.println("Can't Change the employee because there is no employee with this ID");
        }
    }

    public abstract void change(Employee employee);
}
