package Payroll;

import java.util.HashMap;
import java.util.Map;

public class HourlyClassification implements PaymentClassification {
    // map from date to time card for the employee
    Map<Long, TimeCard> timeCards = new HashMap<Long, TimeCard>();
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

    public TimeCard getTimeCard(long date) {
        return timeCards.get(date);
    }

}
