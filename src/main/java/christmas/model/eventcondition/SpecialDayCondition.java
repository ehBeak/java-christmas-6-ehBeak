package christmas.model.eventcondition;

import christmas.model.Orders;
import java.time.DayOfWeek;

public class SpecialDayCondition implements EventCondition {

    @Override
    public Boolean satisfyEventCondition(Orders orders) {
        DayOfWeek dayOfWeek = orders.getDayOfWeek();
        return dayOfWeek.equals(DayOfWeek.SUNDAY) || orders.isOrderDateChristmas();
    }
}
