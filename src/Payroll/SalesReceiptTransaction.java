package Payroll;

public class SalesReceiptTransaction implements Transaction {
    int transactionEmployeeId;
    long transactionDate;
    double transactionAmount;

    public SalesReceiptTransaction(int transactionEmployeeId, long transactionDate, double transactionAmount) {
        this.transactionEmployeeId = transactionEmployeeId;
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount;
    }


    @Override
    public void execute() {
        Employee employee = PayrollDatabase.getEmployee(transactionEmployeeId);

        if(employee != null) {
            try {
                CommissionedClassification commissionedClassification = (CommissionedClassification) employee.getPaymentClassification();
                commissionedClassification.addSalesReceipt(new SalesReceipt(transactionDate, transactionAmount));
            } catch (ClassCastException e) {
                System.err.println("Tried to add sales receipt to non-commissioned employee");
            }
        } else {
            System.err.println("No such employee");
        }
    }
}
