package Payroll;

public class DeleteEmployeeTransaction implements Transaction {
    int transactionEmployeeId;

    public DeleteEmployeeTransaction(int transactionEmployeeId) {
        this.transactionEmployeeId = transactionEmployeeId;
    }

    @Override
    public void execute() {
        PayrollDatabase.deleteEmployee(transactionEmployeeId);
    }
}
