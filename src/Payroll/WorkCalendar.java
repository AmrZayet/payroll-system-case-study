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

}
