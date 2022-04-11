package Payroll;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PayrollTest {

    PayrollDatabase payrollDatabase = new PayrollDatabase();
    @Test
    public void TestAddSalariedEmployee() {
        int empId = 1;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
        t.execute();
        Employee employee = PayrollDatabase.getEmployee(empId);
        Assertions.assertNotNull(employee);
        Assertions.assertEquals("Bob", employee.getName());

        PaymentClassification paymentClassification = employee.getPaymentClassification();
        SalariedClassification salariedClassification = (SalariedClassification) paymentClassification;
        Assertions.assertEquals(1000.0, salariedClassification.getSalary(), 0.001);

        PaymentSchedule paymentSchedule = employee.getPaymentSchedule();
        MonthlySchedule monthlySchedule = (MonthlySchedule) paymentSchedule;
        Assertions.assertNotNull(monthlySchedule);

        PaymentMethod paymentMethod = employee.getPaymentMethod();
        HoldMethod holdMethod = (HoldMethod) paymentMethod;
        Assertions.assertNotNull(holdMethod);
    }

    @Test
    public void TestSearchingNonexistentEmployee()
    {
        Employee employee = PayrollDatabase.getEmployee(2);
        Assertions.assertNull(employee);
    }

    @Test
    public void TestAddHourlyEmployee()
    {
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Mark", "Home2", 15.00);
        t.execute();

        Employee employee = PayrollDatabase.getEmployee(empId);
        Assertions.assertNotNull(employee);

        Assertions.assertEquals("Mark", employee.getName());
        Assertions.assertEquals("Home2", employee.getAddress());

        PaymentClassification paymentClassification = employee.getPaymentClassification();
        HourlyClassification hourlyClassification = (HourlyClassification) paymentClassification;
        Assertions.assertNotNull(hourlyClassification);
        Assertions.assertEquals(15.0, hourlyClassification.getHourlyRate(), 0.001);

        PaymentSchedule paymentSchedule = employee.getPaymentSchedule();
        WeeklySchedule weeklySchedule = (WeeklySchedule) paymentSchedule;
        Assertions.assertNotNull(weeklySchedule);

        PaymentMethod paymentMethod = employee.getPaymentMethod();
        HoldMethod holdMethod = (HoldMethod) paymentMethod;
        Assertions.assertNotNull(holdMethod);
    }

    @Test
    public void TestAddCommissionedEmployee()
    {
        int empId = 3;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Brian", "Home3", 1500.00, 5.00);
        t.execute();

        Employee employee = PayrollDatabase.getEmployee(empId);
        Assertions.assertNotNull(employee);
        Assertions.assertEquals("Brian", employee.getName());
        Assertions.assertEquals("Home3", employee.getAddress());

        PaymentClassification paymentClassification = employee.getPaymentClassification();
        CommissionedClassification commissionedClassification = (CommissionedClassification) paymentClassification;
        Assertions.assertNotNull(commissionedClassification);
        Assertions.assertEquals(1500.0, commissionedClassification.getSalary());
        Assertions.assertEquals(5.00, commissionedClassification.getCommissionRate());

        PaymentSchedule paymentSchedule = employee.getPaymentSchedule();
        BiweeklySchedule biweeklySchedule = (BiweeklySchedule) paymentSchedule;
        Assertions.assertNotNull(biweeklySchedule);

        PaymentMethod paymentMethod = employee.getPaymentMethod();
        HoldMethod holdMethod = (HoldMethod) paymentMethod;
        Assertions.assertNotNull(holdMethod);
    }

    @Test
    public void TestDeleteEmployee()
    {
        int empId = 4;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Martin", "Home4", 2000.00);
        t.execute();

        Employee employee = PayrollDatabase.getEmployee(empId);
        Assertions.assertNotNull(employee);

        DeleteEmployeeTransaction dt = new DeleteEmployeeTransaction(empId);
        dt.execute();

        Employee employee1 = PayrollDatabase.getEmployee(empId);
        Assertions.assertNull(employee1);
    }
}
