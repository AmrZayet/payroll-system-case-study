package Payroll;

public class WeeklySchedule implements PaymentSchedule {
    @Override
    public boolean isPayDay(WorkCalendar payDate) {
        return payDate.isLastDayOfWeek();
    }

    @Override
    public WorkCalendar getPaymentPeriodStartDate(WorkCalendar payDate) {
        return payDate.getStartOfWeek();
    }
}
