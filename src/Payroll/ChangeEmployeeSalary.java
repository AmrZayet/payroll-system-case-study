package Payroll;

public class ChangeEmployeeSalary extends ChangeEmployeeTransaction {
    double transactionSalary;

    public ChangeEmployeeSalary(int transactionEmployeeId, double transactionSalary) {
        super(transactionEmployeeId);
        this.transactionSalary = transactionSalary;
    }

    @Override
    public void change(Employee employee) {
        PaymentClassification paymentClassification = employee.getPaymentClassification();
        if (paymentClassification instanceof SalariedClassification) {
            SalariedClassification salariedClassification = (SalariedClassification) paymentClassification;
            salariedClassification.setSalary(transactionSalary);
        } else if (paymentClassification instanceof CommissionedClassification) {
            CommissionedClassification commissionedClassification = (CommissionedClassification) paymentClassification;
            commissionedClassification.setSalary(transactionSalary);
        } else {
            System.err.println("Can't change salary for an employee that is neither salaried of commissioned!");
        }
    }
}
