package Payroll;

public class BiweeklySchedule implements PaymentSchedule {
    @Override
    public boolean isPayDay(WorkCalendar workCalendar) {
        return false;
    }
}
