package Payroll;

import java.util.Iterator;
import java.util.Map;

public class PayDayTransaction implements Transaction {
    WorkCalendar transactionPayDate;
    double totalPay = 0;

    public PayDayTransaction(WorkCalendar transactionPayDate) {
        this.transactionPayDate = transactionPayDate;
    }

    @Override
    public void execute() {
        Iterator<Map.Entry<Integer, Employee>> employeesIterator = PayrollDatabase.getEmployeesIterator();

        Map.Entry<Integer, Employee> mapElement;
        Employee employee;
        while (employeesIterator.hasNext())
        {
            mapElement = employeesIterator.next();
            employee = mapElement.getValue();
            totalPay += employee.calculatePay(transactionPayDate);
        }
    }

    public double getTotalPay() {
        return totalPay;
    }
}