package Payroll;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class WorkCalendar extends GregorianCalendar{

    public WorkCalendar() {
    }

    public WorkCalendar(TimeZone zone) {
        super(zone);
    }

    public WorkCalendar(Locale aLocale) {
        super(aLocale);
    }

    public WorkCalendar(TimeZone zone, Locale aLocale) {
        super(zone, aLocale);
    }

    public WorkCalendar(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    public WorkCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute) {
        super(year, month, dayOfMonth, hourOfDay, minute);
    }

    public WorkCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second) {
        super(year, month, dayOfMonth, hourOfDay, minute, second);
    }

    public boolean isLastDayOfMonth() {
        int currentDay = this.get(Calendar.DAY_OF_MONTH);
        int lastDay = this.getActualMaximum(Calendar.DAY_OF_MONTH);

        return (currentDay == lastDay);
    }

    public boolean isLastDayOfWeek() {
        int currentDay = this.get(Calendar.DAY_OF_WEEK);
        return (currentDay == Calendar.FRIDAY);
    }

    public WorkCalendar getStartOfMonth() {
        int day = 1;
        int month = get(Calendar.MONTH);
        int year = get(Calendar.YEAR);
        return new WorkCalendar(year, month, day);
    }

    public WorkCalendar getStartOfWeek() {
        int day = get(Calendar.DAY_OF_MONTH);
        int month = get(Calendar.MONTH);
        int year = get(Calendar.YEAR);
        WorkCalendar newWorkCalendar = new WorkCalendar(year, month, day);

        int daysFromStartOfWeek = get(Calendar.DAY_OF_WEEK) % 7; //as Saturday is 7 in the calendar class (we want it 0)
        newWorkCalendar.add(Calendar.DAY_OF_YEAR, -daysFromStartOfWeek);
        return newWorkCalendar;
    }

    public WorkCalendar getStartOfTwoWeeks() {
        return null;
    }

    public boolean isBetween(WorkCalendar startDate, WorkCalendar endDate) {
        //compareTo is like (caller date - argument date)
        return (this.compareTo(startDate) >= 0 && this.compareTo(endDate) <= 0);
    }
}
