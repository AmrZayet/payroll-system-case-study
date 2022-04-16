package Payroll;

import java.util.HashMap;
import java.util.Map;

public class CommissionedClassification implements PaymentClassification {
    // map from date to sales receipt for the employee
    Map<Long, SalesReceipt> salesReceipts = new HashMap<Long, SalesReceipt>();
    double salary;
    double commissionRate;

    public CommissionedClassification(double salary, double commissionRate) {
        this.salary = salary;
        this.commissionRate = commissionRate;
    }

    public void addSalesReceipt(SalesReceipt salesReceipt) {
        salesReceipts.put(salesReceipt.getDate(), salesReceipt);
    }

    public SalesReceipt getSalesReceipt(long date) {
        return salesReceipts.get(date);
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
    }

    public double getSalary() {
        return salary;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    @Override
    public double calculatePay(WorkCalendar paymentPeriodStartDate, WorkCalendar payDate) {
        return 0;
    }
}
