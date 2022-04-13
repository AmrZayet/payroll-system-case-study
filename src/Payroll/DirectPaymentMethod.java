package Payroll;

public class DirectPaymentMethod implements PaymentMethod {
    String employeeBank;
    String employeeAccountNumber;

    public DirectPaymentMethod(String employeeBank, String employeeAccountNumber) {
        this.employeeBank = employeeBank;
        this.employeeAccountNumber = employeeAccountNumber;
    }

    public void setEmployeeBank(String employeeBank) {
        this.employeeBank = employeeBank;
    }

    public void setEmployeeAccountNumber(String employeeAccountNumber) {
        this.employeeAccountNumber = employeeAccountNumber;
    }

    public String getEmployeeBank() {
        return employeeBank;
    }

    public String getEmployeeAccountNumber() {
        return employeeAccountNumber;
    }
}
