package Payroll;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class WorkCalendarTest {

    @Test
    public void TestIsLastDayOfMonthCorrect() {
        WorkCalendar payDate = new WorkCalendar(2022, Calendar.FEBRUARY, 28);
        Assertions.assertTrue(payDate.isLastDayOfMonth());

        WorkCalendar payDate2 = new WorkCalendar(2022, Calendar.MARCH, 31);
        Assertions.assertTrue(payDate2.isLastDayOfMonth());

        WorkCalendar payDate3 = new WorkCalendar(2022, Calendar.APRIL, 30);
        Assertions.assertTrue(payDate3.isLastDayOfMonth());
    }

    @Test
    public void TestIsLastDayOfMonthWhenFalse() {
        WorkCalendar payDate = new WorkCalendar(2022, Calendar.FEBRUARY, 26);
        Assertions.assertFalse(payDate.isLastDayOfMonth());

        WorkCalendar payDate2 = new WorkCalendar(2022, Calendar.MARCH, 30);
        Assertions.assertFalse(payDate2.isLastDayOfMonth());

        WorkCalendar payDate3 = new WorkCalendar(2022, Calendar.APRIL, 10);
        Assertions.assertFalse(payDate3.isLastDayOfMonth());
    }

    @Test
    public void TestIsLastDayOfMonthInLeapYear() {
        WorkCalendar payDate = new WorkCalendar(2020, Calendar.FEBRUARY, 28);
        Assertions.assertFalse(payDate.isLastDayOfMonth());

        WorkCalendar payDate2 = new WorkCalendar(2020, Calendar.FEBRUARY, 29);
        Assertions.assertTrue(payDate2.isLastDayOfMonth());
    }

    @Test
    public void TestIsLastDayOfWeekCorrect() {
        WorkCalendar payDate = new WorkCalendar(2022, Calendar.APRIL, 15);
        Assertions.assertTrue(payDate.isLastDayOfWeek());

        WorkCalendar payDate2 = new WorkCalendar(2022, Calendar.JANUARY, 7);
        Assertions.assertTrue(payDate2.isLastDayOfWeek());

        WorkCalendar payDate3 = new WorkCalendar(2022, Calendar.APRIL, 29);
        Assertions.assertTrue(payDate3.isLastDayOfWeek());
    }

    @Test
    public void TestIsLastDayOfWeekWhenFalse() {
        WorkCalendar payDate = new WorkCalendar(2022, Calendar.APRIL, 17);
        Assertions.assertFalse(payDate.isLastDayOfWeek());

        WorkCalendar payDate2 = new WorkCalendar(2022, Calendar.JANUARY, 9);
        Assertions.assertFalse(payDate2.isLastDayOfWeek());

        WorkCalendar payDate3 = new WorkCalendar(2022, Calendar.APRIL, 25);
        Assertions.assertFalse(payDate3.isLastDayOfWeek());
    }

    @Test
    public void TestGetStartOfMonth() {
        WorkCalendar workCalendar = new WorkCalendar(2022, Calendar.APRIL, 22);
        WorkCalendar startOfMonthDate = workCalendar.getStartOfMonth();
        Assertions.assertEquals(2022, startOfMonthDate.get(Calendar.YEAR));
        Assertions.assertEquals(Calendar.APRIL, startOfMonthDate.get(Calendar.MONTH));
        Assertions.assertEquals(1, startOfMonthDate.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void TestGetStartOfWeekWithEndOfWeekInput() {
        WorkCalendar workCalendar = new WorkCalendar(2022, Calendar.APRIL, 22);
        WorkCalendar startOfWeek = workCalendar.getStartOfWeek();
        Assertions.assertEquals(2022, startOfWeek.get(Calendar.YEAR));
        Assertions.assertEquals(Calendar.APRIL, startOfWeek.get(Calendar.MONTH));
        Assertions.assertEquals(16, startOfWeek.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void TestGetStartOfWeekWithMiddleOfWeekInput() {
        WorkCalendar workCalendar = new WorkCalendar(2022, Calendar.APRIL, 17);
        WorkCalendar startOfWeek = workCalendar.getStartOfWeek();
        Assertions.assertEquals(2022, startOfWeek.get(Calendar.YEAR));
        Assertions.assertEquals(Calendar.APRIL, startOfWeek.get(Calendar.MONTH));
        Assertions.assertEquals(16, startOfWeek.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void TestGetStartOfWeekWithStartOfWeekInput() {
        WorkCalendar workCalendar = new WorkCalendar(2022, Calendar.APRIL, 16);
        WorkCalendar startOfWeek = workCalendar.getStartOfWeek();
        Assertions.assertEquals(2022, startOfWeek.get(Calendar.YEAR));
        Assertions.assertEquals(Calendar.APRIL, startOfWeek.get(Calendar.MONTH));
        Assertions.assertEquals(16, startOfWeek.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void TestIsBetweenDateCorrectSameMonth() {
        WorkCalendar startDate = new WorkCalendar(2022, Calendar.APRIL, 10);
        WorkCalendar endDate = new WorkCalendar(2022, Calendar.APRIL, 26);

        WorkCalendar targetDateLowEdge = new WorkCalendar(2022, Calendar.APRIL, 10);
        WorkCalendar targetDateBetween = new WorkCalendar(2022, Calendar.APRIL, 26);
        WorkCalendar targetDateHighEdge = new WorkCalendar(2022, Calendar.APRIL, 15);

        Assertions.assertTrue(targetDateLowEdge.isBetween(startDate, endDate));
        Assertions.assertTrue(targetDateBetween.isBetween(startDate, endDate));
        Assertions.assertTrue(targetDateHighEdge.isBetween(startDate, endDate));
    }

    @Test
    public void TestIsBetweenDateCorrectDifferentMonths() {
        WorkCalendar startDate = new WorkCalendar(2022, Calendar.APRIL, 20);
        WorkCalendar endDate = new WorkCalendar(2022, Calendar.MAY, 15);

        WorkCalendar targetDateLowEdge = new WorkCalendar(2022, Calendar.APRIL, 20);
        WorkCalendar targetDateBetween = new WorkCalendar(2022, Calendar.MAY, 1);
        WorkCalendar targetDateHighEdge = new WorkCalendar(2022, Calendar.MAY, 15);

        Assertions.assertTrue(targetDateLowEdge.isBetween(startDate, endDate));
        Assertions.assertTrue(targetDateBetween.isBetween(startDate, endDate));
        Assertions.assertTrue(targetDateHighEdge.isBetween(startDate, endDate));
    }

    @Test
    public void TestIsBetweenDateCorrectDifferentYears() {
        WorkCalendar startDate = new WorkCalendar(2022, Calendar.OCTOBER, 20);
        WorkCalendar endDate = new WorkCalendar(2023, Calendar.APRIL, 15);

        WorkCalendar targetDateLowEdge = new WorkCalendar(2022, Calendar.OCTOBER, 20);
        WorkCalendar targetDateBetween = new WorkCalendar(2022, Calendar.DECEMBER, 1);
        WorkCalendar targetDateHighEdge = new WorkCalendar(2023, Calendar.APRIL, 15);

        Assertions.assertTrue(targetDateLowEdge.isBetween(startDate, endDate));
        Assertions.assertTrue(targetDateBetween.isBetween(startDate, endDate));
        Assertions.assertTrue(targetDateHighEdge.isBetween(startDate, endDate));
    }

    @Test
    public void TestIsBetweenDateFalseSameMonth() {
        WorkCalendar startDate = new WorkCalendar(2022, Calendar.APRIL, 10);
        WorkCalendar endDate = new WorkCalendar(2022, Calendar.APRIL, 26);

        WorkCalendar targetDateBefore = new WorkCalendar(2022, Calendar.APRIL, 9);
        WorkCalendar targetDateAfter = new WorkCalendar(2022, Calendar.APRIL, 27);

        Assertions.assertFalse(targetDateBefore.isBetween(startDate, endDate));
        Assertions.assertFalse(targetDateAfter.isBetween(startDate, endDate));
    }

    @Test
    public void TestIsBetweenDateFalseDifferentMonths() {
        WorkCalendar startDate = new WorkCalendar(2022, Calendar.APRIL, 20);
        WorkCalendar endDate = new WorkCalendar(2022, Calendar.MAY, 15);

        WorkCalendar targetDateBefore = new WorkCalendar(2022, Calendar.APRIL, 19);
        WorkCalendar targetDateAfter = new WorkCalendar(2022, Calendar.MAY, 16);

        Assertions.assertFalse(targetDateBefore.isBetween(startDate, endDate));
        Assertions.assertFalse(targetDateAfter.isBetween(startDate, endDate));
    }

    @Test
    public void TestIsBetweenDateFalseDifferentYears() {
        WorkCalendar startDate = new WorkCalendar(2022, Calendar.OCTOBER, 20);
        WorkCalendar endDate = new WorkCalendar(2023, Calendar.APRIL, 15);

        WorkCalendar targetDateBefore = new WorkCalendar(2022, Calendar.APRIL, 19);
        WorkCalendar targetDateAfter = new WorkCalendar(2023, Calendar.MAY, 16);

        Assertions.assertFalse(targetDateBefore.isBetween(startDate, endDate));
        Assertions.assertFalse(targetDateAfter.isBetween(startDate, endDate));
    }

    @Test
    public void TestIsBetweenDateFalseSamePeriodDifferentYear() {
        WorkCalendar startDate = new WorkCalendar(2022, Calendar.APRIL, 20);
        WorkCalendar endDate = new WorkCalendar(2022, Calendar.MAY, 15);

        WorkCalendar targetDateBefore = new WorkCalendar(2021, Calendar.APRIL, 25);
        WorkCalendar targetDateAfter = new WorkCalendar(2023, Calendar.MAY, 11);

        Assertions.assertFalse(targetDateBefore.isBetween(startDate, endDate));
        Assertions.assertFalse(targetDateAfter.isBetween(startDate, endDate));
    }


}