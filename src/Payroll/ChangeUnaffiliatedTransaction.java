package Payroll;

public class ChangeUnaffiliatedTransaction extends ChangeAffiliationTransaction {
    public ChangeUnaffiliatedTransaction(int transactionEmployeeId) {
        super(transactionEmployeeId);
    }

    @Override
    public Affiliation getNewAffiliation() {
        return new NoAffiliation();
    }

    @Override
    public void recordUnionMembership(Employee employee) {
        Affiliation affiliation = employee.getAffiliation();
        if (affiliation instanceof UnionAffiliation) {
            UnionAffiliation unionAffiliation = (UnionAffiliation) affiliation;
            int memberId = unionAffiliation.getMemberId();
            PayrollDatabase.deleteUnionMember(memberId);
        }
    }
}
