package Payroll;

public class ServiceChargeTransaction implements Transaction {
    int transactionMemberId;
    long transactionDate;
    double transactionServiceAmount;

    public ServiceChargeTransaction(int transactionMemberId, long transactionDate, double transactionServiceAmount) {
        this.transactionMemberId = transactionMemberId;
        this.transactionDate = transactionDate;
        this.transactionServiceAmount = transactionServiceAmount;
    }


    @Override
    public void execute() {
        Employee employee = PayrollDatabase.getUnionMember(transactionMemberId);
        if(employee != null) {
            try {
                UnionAffiliation unionAffiliation = (UnionAffiliation) employee.getAffiliation();
                unionAffiliation.addServiceCharge(new ServiceCharge(transactionDate, transactionServiceAmount));
            } catch (ClassCastException e) {
                System.err.println("Tried to add service charge to non-Affiliated employee");
            }
        } else {
            System.err.println("No such employee with this memberId.");
        }


    }
}
