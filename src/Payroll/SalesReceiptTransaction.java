package Payroll;

public class SalesReceiptTransaction implements Transaction {
    int transactionEmployeeId;
    long transactionDate;
    double transactionSalesAmount;

    public SalesReceiptTransaction(int transactionEmployeeId, long transactionDate, double transactionSalesAmount) {
        this.transactionEmployeeId = transactionEmployeeId;
        this.transactionDate = transactionDate;
        this.transactionSalesAmount = transactionSalesAmount;
    }


    @Override
    public void execute() {
        Employee employee = PayrollDatabase.getEmployee(transactionEmployeeId);

        if(employee != null) {
            try {
                CommissionedClassification commissionedClassification = (CommissionedClassification) employee.getPaymentClassification();
                commissionedClassification.addSalesReceipt(new SalesReceipt(transactionDate, transactionSalesAmount));
            } catch (ClassCastException e) {
                System.err.println("Tried to add sales receipt to non-commissioned employee");
            }
        } else {
            System.err.println("No such employee");
        }
    }
}
