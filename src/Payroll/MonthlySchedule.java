package Payroll;

public class MonthlySchedule implements PaymentSchedule {
    @Override
    public boolean isPayDay(WorkCalendar workCalendar) {
        return workCalendar.isLastDayOfMonth();
    }
}
