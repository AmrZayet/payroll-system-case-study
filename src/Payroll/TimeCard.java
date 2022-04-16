package Payroll;

public class TimeCard {
    WorkCalendar date;
    double hoursCount;

    public TimeCard(WorkCalendar date, double hoursCount) {
        this.date = date;
        this.hoursCount = hoursCount;
    }

    public WorkCalendar getDate() {
        return date;
    }

    public double getHoursCount() {
        return hoursCount;
    }
}
