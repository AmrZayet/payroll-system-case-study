package Payroll;

public class TimeCardTransaction implements Transaction {
    int transactionEmployeeId;
    WorkCalendar transactionDate;
    double transactionHoursCount;

    public TimeCardTransaction(int transactionEmployeeId, WorkCalendar transactionDate, double transactionHoursCount) {
        this.transactionEmployeeId = transactionEmployeeId;
        this.transactionDate = transactionDate;
        this.transactionHoursCount = transactionHoursCount;
    }


    @Override
    public void execute() {
        Employee employee = PayrollDatabase.getEmployee(transactionEmployeeId);

        if(employee != null) {
            try {
                HourlyClassification hourlyClassification = (HourlyClassification) employee.getPaymentClassification();
                hourlyClassification.addTimeCard(new TimeCard(transactionDate, transactionHoursCount));
            } catch (ClassCastException e) {
                System.err.println("Tried to add timecard to non-hourly employee");
            }
        } else {
            System.err.println("No such employee");
        }
    }
}
