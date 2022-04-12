package Payroll;

public class TimeCard {
    long date;
    double hoursCount;

    public TimeCard(long date, double hoursCount) {
        this.date = date;
        this.hoursCount = hoursCount;
    }

    public long getDate() {
        return date;
    }

    public double getHoursCount() {
        return hoursCount;
    }
}
