package Payroll;

public abstract class ChangeAffiliationTransaction extends ChangeEmployeeTransaction {
    public ChangeAffiliationTransaction(int transactionEmployeeId) {
        super(transactionEmployeeId);
    }

    @Override
    public void change(Employee employee) {
        employee.setAffiliation(getNewAffiliation());
        recordUnionMembership(employee);
    }

    public abstract Affiliation getNewAffiliation();
    public abstract void recordUnionMembership(Employee employee);
}
