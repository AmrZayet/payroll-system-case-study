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
    public void TestSearchingNonexistentEmployee() {
        Employee employee = PayrollDatabase.getEmployee(2);
        Assertions.assertNull(employee);
    }

    @Test
    public void TestAddHourlyEmployee() {
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
    public void TestAddCommissionedEmployee() {
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
    public void TestDeleteEmployee() {
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

    @Test
    public void TestTimeCard() {
        int empId = 5;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Sarah", "Home5", 15.00);
        t.execute();

        TimeCardTransaction tct = new TimeCardTransaction(empId, 20220412, 8.0);
        tct.execute();

        Employee employee = PayrollDatabase.getEmployee(empId);
        Assertions.assertNotNull(employee);

        PaymentClassification paymentClassification = employee.getPaymentClassification();
        HourlyClassification hourlyClassification = (HourlyClassification) paymentClassification;

        TimeCard timeCard = hourlyClassification.getTimeCard(20220412);
        Assertions.assertNotNull(timeCard);
        Assertions.assertEquals(8.0, timeCard.getHoursCount());
    }

    @Test
    public void TestSalesReceiptTransaction() {
        int empId = 6;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Andy", "Home6", 2500.00, 5.00);
        t.execute();

        SalesReceiptTransaction tct = new SalesReceiptTransaction(empId, 20220412, 500.00);
        tct.execute();

        Employee employee = PayrollDatabase.getEmployee(empId);
        Assertions.assertNotNull(employee);

        CommissionedClassification commissionedClassification = (CommissionedClassification) employee.getPaymentClassification();

        SalesReceipt salesReceipt = commissionedClassification.getSalesReceipt(20220412);
        Assertions.assertNotNull(salesReceipt);
        Assertions.assertEquals(500.0, salesReceipt.getAmount());
    }

    @Test
    public void TestSetAffiliationTransaction() {
        int empId = 7;
        AddSalariedEmployee t = new AddSalariedEmployee(empId,"Robbin", "Home7", 2500.00);
        t.execute();

        int memberId = 87;
        SetAffiliationTransaction sat = new SetAffiliationTransaction(empId, memberId, 12.5);
        sat.execute();

        Employee employee = PayrollDatabase.getEmployee(empId);
        Employee employee1 = PayrollDatabase.getUnionMember(memberId);
        Assertions.assertEquals(employee, employee1);

        Affiliation affiliation = employee.getAffiliation();
        UnionAffiliation unionAffiliation = (UnionAffiliation) affiliation;

        Assertions.assertEquals(12.5, unionAffiliation.getDues());
    }


    @Test
    public void TestAddServiceCharge() {
        int empId = 8;
        AddHourlyEmployee t = new AddHourlyEmployee(empId,"Bill", "Home8", 15.25);
        t.execute();

        int memberId = 88;
        SetAffiliationTransaction sat = new SetAffiliationTransaction(empId, memberId, 12.0);
        sat.execute();

        ServiceChargeTransaction sct = new ServiceChargeTransaction(memberId, 20220412, 12.95);
        sct.execute();

        Employee employee = PayrollDatabase.getEmployee(empId);
        UnionAffiliation unionAffiliation = (UnionAffiliation) employee.getAffiliation();
        ServiceCharge serviceCharge = unionAffiliation.getServiceCharge(20220412);
        Assertions.assertNotNull(serviceCharge);
        Assertions.assertEquals(12.95, serviceCharge.getAmount());
    }

    @Test
    public void TestAddServiceChargeWithNonAffiliatedEmployee() {
        int memberId = 89;

        ServiceChargeTransaction sct = new ServiceChargeTransaction(memberId, 20220412, 12.95);
        sct.execute();

        Employee employee = PayrollDatabase.getUnionMember(memberId);
        Assertions.assertNull(employee);
    }

    @Test
    public void TestChangeEmployeeNameTransaction() {
        int empId = 20;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Ramy", "Home20", 2000.0);
        t.execute();

        ChangeEmployeeName cen = new ChangeEmployeeName(empId, "Bradly");
        cen.execute();

        Employee employee = PayrollDatabase.getEmployee(empId);
        Assertions.assertEquals("Bradly", employee.getName());
    }

    @Test
    public void TestChangeEmployeeAddressTransaction() {
        int empId = 21;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Lisa", "Home21", 16.00);
        t.execute();

        ChangeEmployeeAddress cea = new ChangeEmployeeAddress(empId, "newHome21");
        cea.execute();

        Employee employee = PayrollDatabase.getEmployee(empId);
        Assertions.assertEquals("newHome21", employee.getAddress());
    }
}
