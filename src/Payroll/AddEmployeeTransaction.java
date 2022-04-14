package Payroll;

public abstract class AddEmployeeTransaction implements Transaction {
    int transactionEmployeeId;
    String transactionEmployeeName;
    String transactionEmployeeAddress;

    AddEmployeeTransaction(int employeeId, String name, String address) {
        this.transactionEmployeeId = employeeId;
        this.transactionEmployeeName = name;
        this.transactionEmployeeAddress = address;
    }

    @Override
    public void execute() {
        PaymentClassification paymentClassification = getClassification();
        PaymentSchedule paymentSchedule = getSchedule();
        PaymentMethod paymentMethod = new HoldPaymentMethod();

        Employee newEmployee = new Employee(transactionEmployeeId, transactionEmployeeName, transactionEmployeeAddress);
        newEmployee.setPaymentClassification(paymentClassification);
        newEmployee.setPaymentSchedule(paymentSchedule);
        newEmployee.setPaymentMethod(paymentMethod);
        newEmployee.setAffiliation(getDefaultAffiliation());

        PayrollDatabase.addEmployee(transactionEmployeeId, newEmployee);
    }

    public Affiliation getDefaultAffiliation() {
        return new NoAffiliation();
    }

    public abstract PaymentClassification getClassification();
    public abstract PaymentSchedule getSchedule();
}
