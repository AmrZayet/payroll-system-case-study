package Payroll;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HourlyClassification implements PaymentClassification {
    // map from date to time card for the employee
    Map<WorkCalendar, TimeCard> timeCards = new HashMap<WorkCalendar, TimeCard>();
    double hourlyRate;

    public HourlyClassification(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void addTimeCard(TimeCard timeCard) {
        timeCards.put(timeCard.getDate(), timeCard);
    }

    public TimeCard getTimeCard(WorkCalendar date) {
        return timeCards.get(date);
    }

    @Override
    public double calculatePay(WorkCalendar paymentPeriodStartDate, WorkCalendar payDate) {
        double totalPay = 0.00;
        double maximumNormalDayHours = 8.00;

        Iterator<Map.Entry<WorkCalendar, TimeCard>> timeCardsIterator = timeCards.entrySet().iterator();

        Map.Entry<WorkCalendar, TimeCard> mapElement;
        TimeCard timeCard;
        WorkCalendar timeCardDate;
        while (timeCardsIterator.hasNext()) {
            mapElement = timeCardsIterator.next();
            timeCardDate = mapElement.getKey();
            timeCard = mapElement.getValue();

            if (timeCardDate.isBetween(paymentPeriodStartDate, payDate)) {
                double hoursCount = timeCard.getHoursCount();

                if (hoursCount > maximumNormalDayHours) {
                    totalPay += maximumNormalDayHours * hourlyRate;
                    totalPay += (hoursCount - maximumNormalDayHours) * 1.50 * hourlyRate;
                } else {
                    totalPay += hoursCount * hourlyRate;
                }
            }
        }
        return totalPay;
    }
}
