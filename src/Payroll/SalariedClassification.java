package Payroll;

public class SalariedClassification implements PaymentClassification {
    double employeeSalary;

    public SalariedClassification(double employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public double getSalary() {
        return employeeSalary;
    }
}
