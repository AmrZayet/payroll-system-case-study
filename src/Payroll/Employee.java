package Payroll;

public class Employee {
    int id;
    String name;
    String address;
    PaymentClassification paymentClassification;
    PaymentSchedule paymentSchedule;
    PaymentMethod paymentMethod;
    Affiliation affiliation;


    public Employee(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setPaymentClassification(PaymentClassification paymentClassification) {
        this.paymentClassification = paymentClassification;
    }

    public void setPaymentSchedule(PaymentSchedule paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentClassification getPaymentClassification() {
        return paymentClassification;
    }

    public PaymentSchedule getPaymentSchedule() {
        return paymentSchedule;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public Affiliation getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(Affiliation affiliation) {
        this.affiliation = affiliation;
    }
}
