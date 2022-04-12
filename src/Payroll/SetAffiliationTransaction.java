package Payroll;

public class SetAffiliationTransaction implements Transaction {
    int transactionEmployeeId;
    double transactionDues;

    public SetAffiliationTransaction(int transactionEmployeeId, double transactionDues) {
        this.transactionEmployeeId = transactionEmployeeId;
        this.transactionDues = transactionDues;
    }


    @Override
    public void execute() {
        Employee employee = PayrollDatabase.getEmployee(transactionEmployeeId);

        if(employee != null) {
            employee.setAffiliation(new UnionAffiliation(transactionDues));
        } else {
            System.err.println("No such employee");
        }
    }
}
