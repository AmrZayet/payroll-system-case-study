package Payroll;

public interface PaymentClassification {
    double calculatePay(WorkCalendar paymentPeriodStartDate, WorkCalendar payDate);
}
