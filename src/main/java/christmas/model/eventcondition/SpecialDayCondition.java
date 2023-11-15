package christmas.model.eventcondition;

import static java.time.DayOfWeek.SUNDAY;

import christmas.model.Orders;
import java.time.DayOfWeek;

public class SpecialDayCondition implements EventCondition {

    @Override
    public Boolean satisfyEventCondition(Orders orders) {
        DayOfWeek dayOfWeek = orders.getDayOfWeek();
        return dayOfWeek.equals(SUNDAY) || orders.isOrderDateChristmas();
    }
}
