package christmas.model.eventcondition;

import christmas.model.DayOfWeekCategory;
import christmas.model.Orders;
import java.time.DayOfWeek;

public class DayOfWeekCondition implements EventCondition {

    private final DayOfWeekCategory dayOfWeekCategory;

    public DayOfWeekCondition(DayOfWeekCategory dayOfWeekCategory) {
        this.dayOfWeekCategory = dayOfWeekCategory;
    }

    @Override
    public Boolean satisfyEventCondition(Orders orders) {
        DayOfWeek dayOfWeek = orders.getDayOfWeek();
        return DayOfWeekCategory.isDayOfWeekInCategory(dayOfWeek, dayOfWeekCategory);
    }
}