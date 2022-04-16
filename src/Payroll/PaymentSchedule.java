package Payroll;

public interface PaymentSchedule {
    boolean isPayDay(WorkCalendar payDate);

    WorkCalendar getPaymentPeriodStartDate(WorkCalendar payDate);
}
