package Payroll;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PayrollTest {

    @Test
    public void TestAddSalariedEmployee() {
        int empId = 1;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
        t.executue();
        Employee employee = GpayrollDatabase.getEmployee(empId);
        Assertions.assertEquals("Bob", e.getName());

        PaymentClassification salariedClassification = employee.getClassification();
        Assertions.assertEquals(1000.0, salariedClassification.getSalary(), 0.001);

        PaymentSchedule monthlySchedule = employee.getSchedule();

        PaymentMethod holdMethod = employee.getPaymentMethod();


    }
}
