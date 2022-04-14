package Payroll;

public class ChangeMemberTransaction extends ChangeAffiliationTransaction {
    int transactionMemberId;
    double transactionDues;

    public ChangeMemberTransaction(int transactionEmployeeId, int transactionMemberId, double transactionDues) {
        super(transactionEmployeeId);
        this.transactionMemberId = transactionMemberId;
        this.transactionDues = transactionDues;
    }

    @Override
    public Affiliation getNewAffiliation() {
        return new UnionAffiliation(transactionDues);
    }

    @Override
    public void recordUnionMembership(Employee employee) {
        PayrollDatabase.addUnionMember(transactionMemberId, employee);
    }
}
