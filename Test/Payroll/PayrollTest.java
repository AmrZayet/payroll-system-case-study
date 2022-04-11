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
}
