package Payroll;

public class WeeklySchedule implements PaymentSchedule {
    @Override
    public boolean isPayDay(WorkCalendar workCalendar) {
        return false;
    }
}
