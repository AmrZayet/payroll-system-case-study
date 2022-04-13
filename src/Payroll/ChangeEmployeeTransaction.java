package Payroll;

public abstract class ChangeEmployeeTransaction implements Transaction {
    int transactionEmployeeId;

    public ChangeEmployeeTransaction(int transactionEmployeeId) {
        this.transactionEmployeeId = transactionEmployeeId;
    }

    @Override
    public void execute() {
        change();
    }

    public abstract void change();
}
