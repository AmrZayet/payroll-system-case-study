package Payroll;

public class SetAffiliationTransaction implements Transaction {
    int transactionEmployeeId;
    int transactionMemberId;
    double transactionDues;

    public SetAffiliationTransaction(int transactionEmployeeId, int transactionMemberId, double transactionDues) {
        this.transactionEmployeeId = transactionEmployeeId;
        this.transactionMemberId = transactionMemberId;
        this.transactionDues = transactionDues;
    }


    @Override
    public void execute() {
        Employee employee = PayrollDatabase.getEmployee(transactionEmployeeId);

        if(employee != null) {
            employee.setAffiliation(new UnionAffiliation(transactionDues));
            PayrollDatabase.addUnionMember(transactionMemberId, employee);
        } else {
            System.err.println("No such employee");
        }
    }
}
