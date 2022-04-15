package Payroll;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

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
        HoldPaymentMethod holdPaymentMethod = (HoldPaymentMethod) paymentMethod;
        Assertions.assertNotNull(holdPaymentMethod);
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
        HoldPaymentMethod holdPaymentMethod = (HoldPaymentMethod) paymentMethod;
        Assertions.assertNotNull(holdPaymentMethod);
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
        HoldPaymentMethod holdPaymentMethod = (HoldPaymentMethod) paymentMethod;
        Assertions.assertNotNull(holdPaymentMethod);
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
        ChangeMemberTransaction sat = new ChangeMemberTransaction(empId, memberId, 12.5);
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
        ChangeMemberTransaction sat = new ChangeMemberTransaction(empId, memberId, 12.0);
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

    @Test
    public void TestChangeSalaryForSalariedEmployee() {
        int empId = 22;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Sally", "Home22", 4000.00);
        t.execute();

        ChangeEmployeeSalary ces = new ChangeEmployeeSalary(empId, 5000.00);
        ces.execute();

        Employee employee = PayrollDatabase.getEmployee(empId);
        SalariedClassification salariedClassification = (SalariedClassification) employee.getPaymentClassification();
        Assertions.assertEquals(5000.00, salariedClassification.getSalary());
    }

    @Test
    public void TestChangeSalaryForCommissionedEmployee() {
        int empId = 23;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Josh", "Home23", 4200.00, 5.00);
        t.execute();

        ChangeEmployeeSalary ces = new ChangeEmployeeSalary(empId, 6000.00);
        ces.execute();

        Employee employee = PayrollDatabase.getEmployee(empId);
        CommissionedClassification commissionedClassification = (CommissionedClassification) employee.getPaymentClassification();
        Assertions.assertEquals(6000.00, commissionedClassification.getSalary());
    }

    @Test
    public void TestChangeSalaryForWrongTypeEmployee() {
        int empId = 24;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Marly", "Home24", 16.0);
        t.execute();

        ChangeEmployeeSalary ces = new ChangeEmployeeSalary(empId, 3000.0);
        ces.execute();
    }

    @Test
    public void TestChangeCommissionForCommissionedEmployee() {
        int empId = 25;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Rafael", "Home25", 3800, 5.00);
        t.execute();

        ChangeEmployeeCommissionRate cec = new ChangeEmployeeCommissionRate(empId, 8.00);
        cec.execute();

        Employee employee = PayrollDatabase.getEmployee(empId);
        CommissionedClassification commissionedClassification = (CommissionedClassification) employee.getPaymentClassification();
        Assertions.assertEquals(8.00, commissionedClassification.getCommissionRate());
    }

    @Test
    public void TestChangeCommissionForWrongTypeEmployees() {
        int empId1 = 26;
        AddSalariedEmployee t1 = new AddSalariedEmployee(empId1, "Sally", "Home26", 4000.00);
        t1.execute();

        ChangeEmployeeCommissionRate cec1 = new ChangeEmployeeCommissionRate(empId1, 8.00);
        cec1.execute();

        int empId2 = 27;
        AddHourlyEmployee t2 = new AddHourlyEmployee(empId2, "Mark", "Home27", 16.0);
        t2.execute();

        ChangeEmployeeCommissionRate cec2 = new ChangeEmployeeCommissionRate(empId2, 8.00);
        cec2.execute();
    }

    @Test
    public void TestChangeHourlyRateForHourLyRatedEmployee() {
        int empId = 28;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "John", "Home28", 20.0);
        t.execute();

        ChangeEmployeeHourlyRate cehr = new ChangeEmployeeHourlyRate(empId, 30.00);
        cehr.execute();

        Employee employee = PayrollDatabase.getEmployee(empId);
        HourlyClassification hourlyClassification = (HourlyClassification) employee.getPaymentClassification();
        Assertions.assertEquals(30.00, hourlyClassification.getHourlyRate());
    }

    @Test
    public void TestChangeEmployeeCommissionForForWrongTypeEmployees() {
        int empId1 = 29;
        AddSalariedEmployee t1 = new AddSalariedEmployee(empId1, "Black", "Home29", 4000.00);
        t1.execute();

        ChangeEmployeeHourlyRate cehr1 = new ChangeEmployeeHourlyRate(empId1, 25.00);
        cehr1.execute();

        int empId2 = 30;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId2, "Jack", "Home30", 5800, 8.00);
        t.execute();

        ChangeEmployeeHourlyRate cehr2 = new ChangeEmployeeHourlyRate(empId2, 35.00);
        cehr2.execute();
    }

    @Test
    public void TestChangeSalariedClassificationTransaction() {
        int empId = 40;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Steven", "Home40", 20.0);
        t.execute();

        ChangeSalariedClassification csc = new ChangeSalariedClassification(empId, 5000.00);
        csc.execute();

        Employee employee = PayrollDatabase.getEmployee(empId);
        SalariedClassification salariedClassification = (SalariedClassification) employee.getPaymentClassification();
        Assertions.assertEquals(5000.00, salariedClassification.getSalary());

        PaymentSchedule paymentSchedule = employee.getPaymentSchedule();
        Assertions.assertTrue((paymentSchedule instanceof MonthlySchedule));
    }

    @Test
    public void TestChangeHourlyClassificationTransaction() {
        int empId = 41;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "David", "Home41", 6000.00);
        t.execute();

        ChangeHourlyClassification chc = new ChangeHourlyClassification(empId, 60.00);
        chc.execute();

        Employee employee = PayrollDatabase.getEmployee(empId);
        HourlyClassification hourlyClassification = (HourlyClassification) employee.getPaymentClassification();
        Assertions.assertEquals(60.00, hourlyClassification.getHourlyRate());

        PaymentSchedule paymentSchedule = employee.getPaymentSchedule();
        Assertions.assertTrue((paymentSchedule instanceof WeeklySchedule));
    }

    @Test
    public void TestChangeCommissionedClassificationTransaction() {
        int empId = 42;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Hunter", "Home42", 5500.00);
        t.execute();

        ChangeCommissionedClassification ccc = new ChangeCommissionedClassification(empId, 4500.00, 10.00);
        ccc.execute();

        Employee employee = PayrollDatabase.getEmployee(empId);
        CommissionedClassification commissionedClassification = (CommissionedClassification) employee.getPaymentClassification();
        Assertions.assertEquals(4500.00, commissionedClassification.getSalary());
        Assertions.assertEquals(10.00, commissionedClassification.getCommissionRate());

        PaymentSchedule paymentSchedule = employee.getPaymentSchedule();
        Assertions.assertTrue((paymentSchedule instanceof BiweeklySchedule));
    }

    @Test
    public void TestChangePaymentMethodToDirectMethod() {
        int empId = 50;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Harry", "Home50", 2000.00);
        t.execute();

        ChangeDirectPaymentMethodTransaction cdpm = new ChangeDirectPaymentMethodTransaction(empId, "Bank of America", "50505050");
        cdpm.execute();

        Employee employee = PayrollDatabase.getEmployee(empId);
        PaymentMethod paymentMethod = employee.getPaymentMethod();
        Assertions.assertTrue(paymentMethod instanceof DirectPaymentMethod);

        DirectPaymentMethod directPaymentMethod = (DirectPaymentMethod) paymentMethod;
        Assertions.assertEquals("Bank of America", directPaymentMethod.getEmployeeBank());
        Assertions.assertEquals("50505050", directPaymentMethod.getEmployeeAccountNumber());
    }

    @Test
    public void TestChangePaymentMethodToMailMethod() {
        int empId = 51;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Garry", "Home51", 8000.00);
        t.execute();

        ChangeMailPaymentMethodTransaction cmpm = new ChangeMailPaymentMethodTransaction(empId, "Home51 MailBox");
        cmpm.execute();

        Employee employee = PayrollDatabase.getEmployee(empId);
        PaymentMethod paymentMethod = employee.getPaymentMethod();
        Assertions.assertTrue(paymentMethod instanceof MailPaymentMethod);

        MailPaymentMethod mailPaymentMethod = (MailPaymentMethod) paymentMethod;
        Assertions.assertEquals("Home51 MailBox", mailPaymentMethod.getEmployeeMailAddress());
    }

    @Test
    public void TestChangePaymentMethodToHoldMethod() {
        int empId = 52;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Gerard", "Home52", 70.00);
        t.execute();

        ChangeMailPaymentMethodTransaction cmpm = new ChangeMailPaymentMethodTransaction(empId, "Home52 MailBox");
        cmpm.execute();

        Employee employee = PayrollDatabase.getEmployee(empId);
        PaymentMethod paymentMethod = employee.getPaymentMethod();
        Assertions.assertTrue(paymentMethod instanceof MailPaymentMethod);

        ChangeHoldPaymentMethodTransaction chpm = new ChangeHoldPaymentMethodTransaction(empId);
        chpm.execute();
        paymentMethod = employee.getPaymentMethod();
        Assertions.assertTrue(paymentMethod instanceof HoldPaymentMethod);
    }

    @Test
    public void TestChangeMemberToAffiliatedTransaction() {
        int empId = 53;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Nathan", "Home53", 60.00);
        t.execute();

        int memberId = 153;
        ChangeMemberTransaction sat = new ChangeMemberTransaction(empId, memberId, 12.5);
        sat.execute();

        Employee employee = PayrollDatabase.getEmployee(empId);
        Employee employee1 = PayrollDatabase.getUnionMember(memberId);
        Assertions.assertEquals(employee, employee1);

        Affiliation affiliation = employee.getAffiliation();
        UnionAffiliation unionAffiliation = (UnionAffiliation) affiliation;
        Assertions.assertEquals(12.5, unionAffiliation.getDues());
        Assertions.assertEquals(memberId, unionAffiliation.getMemberId());
    }

    @Test
    public void TestChangeMemberFromUnionToNonAffiliated() {
        int empId = 54;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Hannah", "Home54", 60.00);
        t.execute();

        int memberId = 154;
        ChangeMemberTransaction sat = new ChangeMemberTransaction(empId, memberId, 12.5);
        sat.execute();

        ChangeUnaffiliatedTransaction suat = new ChangeUnaffiliatedTransaction(empId);
        suat.execute();

        Employee employee = PayrollDatabase.getEmployee(empId);
        Affiliation affiliation = employee.getAffiliation();
        Assertions.assertTrue(affiliation instanceof NoAffiliation);

        Employee employee1 = PayrollDatabase.getUnionMember(memberId);
        Assertions.assertNull(employee1);
    }

    @Test
    public void TestChangeMemberFromNonAffiliatedToNonAffiliatedTransaction() {
        int empId = 55;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Carla", "Home55", 100.00);
        t.execute();

        int memberId = 155;
        ChangeUnaffiliatedTransaction sat = new ChangeUnaffiliatedTransaction(empId);
        sat.execute();

        Employee employee = PayrollDatabase.getEmployee(empId);
        Affiliation affiliation = employee.getAffiliation();
        Assertions.assertTrue(affiliation instanceof NoAffiliation);

        Employee employee1 = PayrollDatabase.getUnionMember(memberId);
        Assertions.assertNull(employee1);
    }

    @Test
    public void TestDefaultNoAffiliationForNewlyAddedEmployee() {
        int empId = 56;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Carla", "Home56", 8000.00);
        t.execute();

        Employee employee = PayrollDatabase.getEmployee(empId);
        Affiliation affiliation = employee.getAffiliation();
        Assertions.assertTrue(affiliation instanceof NoAffiliation);
    }

    @Test
    public void TestIsPayDayWithMonthlySchedule() {
        WorkCalendar payDate = new WorkCalendar(2022, Calendar.MARCH, 30);
        MonthlySchedule monthlySchedule = new MonthlySchedule();

        Assertions.assertFalse(monthlySchedule.isPayDay(payDate));
    }

    @Test
    public void TestIsPayDayWithMonthlyScheduleOnWrongDate() {
        WorkCalendar payDate = new WorkCalendar(2022, Calendar.MARCH, 31);
        MonthlySchedule monthlySchedule = new MonthlySchedule();

        Assertions.assertTrue(monthlySchedule.isPayDay(payDate));
    }

    @Test
    public void TestPaySingleSalariedEmployee() {
        PayrollDatabase.clearDatabase();

        int empId = 100;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Sandy", "Home100", 9000.00);
        t.execute();

        WorkCalendar payDate = new WorkCalendar(2022, Calendar.FEBRUARY, 28);
        PayDayTransaction pdt = new PayDayTransaction(payDate);
        pdt.execute();
        Assertions.assertEquals(9000.00, pdt.getTotalPay());

//        PayCheck payCheck = pdt.getPayCheck(empId);
//        Assertions.assertNotNull(payCheck);
//        Assertions.assertEquals(payDate, payCheck.getPayDate());
//        Assertions.assertEquals(9000.00, payCheck.getGrossPay(), 0.001);
//        Assertions.assertEquals("Hold", payCheck.getField("Disposition"));
//        Assertions.assertEquals(0.00, payCheck.getDeductions(), 0.001);
//        Assertions.assertEquals(9000.00, getNetPay(), 0.001);
    }

    @Test
    public void TestPaySingleSalariedEmployeeOnWrongDate() {
        PayrollDatabase.clearDatabase();

        int empId = 101;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Sarah", "Home101", 9500.00);
        t.execute();

        WorkCalendar payDate = new WorkCalendar(2022, Calendar.FEBRUARY, 20);
        PayDayTransaction pdt = new PayDayTransaction(payDate);
        pdt.execute();
        Assertions.assertEquals(0.00, pdt.getTotalPay(), 0.001);

//        PayCheck payCheck = pdt.getPayCheck(empId);
//        Assertions.assertNull(payCheck);
    }

    @Test
    public void TestPayMultipleSalariedEmployees() {
        PayrollDatabase.clearDatabase();

        int empId1 = 102;
        AddSalariedEmployee t1 = new AddSalariedEmployee(empId1, "Sandy", "Home102", 9000.00);
        t1.execute();

        int empId2 = 103;
        AddSalariedEmployee t2 = new AddSalariedEmployee(empId2, "Mark", "Home103", 8000.00);
        t2.execute();

        WorkCalendar payDate = new WorkCalendar(2022, Calendar.APRIL, 30);
        PayDayTransaction pdt = new PayDayTransaction(payDate);
        pdt.execute();
        Assertions.assertEquals(17000.00, pdt.getTotalPay());
    }
}
