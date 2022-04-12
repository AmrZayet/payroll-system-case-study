package Payroll;

public class SalesReceipt {
    long date;
    double amount;

    public SalesReceipt(long date, double amount) {
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
