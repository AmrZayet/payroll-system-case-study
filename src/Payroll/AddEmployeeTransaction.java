package Payroll;

public abstract class AddEmployeeTransaction implements Transaction {
//    PaymentClassification paymentClassification;
//    PaymentSchedule paymentSchedule;

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
        PaymentMethod paymentMethod = new HoldMethod();

        Employee newEmployee = new Employee(transactionEmployeeId, transactionEmployeeName, transactionEmployeeAddress);
        newEmployee.setPaymentClassification(paymentClassification);
        newEmployee.setPaymentSchedule(paymentSchedule);
        newEmployee.setPaymentMethod(paymentMethod);

        PayrollDatabase.addEmployee(transactionEmployeeId, newEmployee);
    }

    public abstract PaymentClassification getClassification();
    public abstract PaymentSchedule getSchedule();
}
