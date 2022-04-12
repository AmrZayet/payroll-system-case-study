package Payroll;

public class ServiceCharge {
    long date;
    double amount;

    public ServiceCharge(long date, double amount) {
        this.date = date;
        this.amount = amount;
    }

    public long getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }
}
