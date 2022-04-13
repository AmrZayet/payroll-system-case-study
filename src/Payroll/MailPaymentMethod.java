package Payroll;

public class MailPaymentMethod implements PaymentMethod {
    String employeeMailAddress;

    public MailPaymentMethod(String employeeMailAddress) {
        this.employeeMailAddress = employeeMailAddress;
    }

    public void setEmployeeMailAddress(String employeeMailAddress) {
        this.employeeMailAddress = employeeMailAddress;
    }

    public String getEmployeeMailAddress() {
        return employeeMailAddress;
    }
}
