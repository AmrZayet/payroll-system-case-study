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
}