package Payroll;

public class BiweeklySchedule implements PaymentSchedule {
    @Override
    public boolean isPayDay(WorkCalendar payDate) {
        return false;
    }

    @Override
    public WorkCalendar getPaymentPeriodStartDate(WorkCalendar payDate) {
        return payDate.getStartOfTwoWeeks();
    }
}
