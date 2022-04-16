package Payroll;

public class MonthlySchedule implements PaymentSchedule {
    @Override
    public boolean isPayDay(WorkCalendar payDate) {
        return payDate.isLastDayOfMonth();
    }

    @Override
    public WorkCalendar getPaymentPeriodStartDate(WorkCalendar payDate) {
        return payDate.getStartOfMonth();
    }
}
