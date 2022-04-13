package Payroll;

public class SalariedClassification implements PaymentClassification {
    double salary;

    public SalariedClassification(double salary) {
        this.salary = salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
}
