package christmas.model.policy;

import christmas.model.Orders;
import java.time.DayOfWeek;

public class WeekdayDiscountPolicy implements EventPolicy{

    private static final Integer DISCOUNT_PRICE_PER_DESSERT = 2023;

    @Override
    public Integer calculateDiscountPrice(Orders orders) {
        int totalDiscount = 0;
        if (isEventPeriod(orders) && isWeekDays(orders)) {
            totalDiscount += orders.findDessertCount() * DISCOUNT_PRICE_PER_DESSERT;
        }
        return totalDiscount;
    }

    private Boolean isWeekDays(Orders orders) {
        DayOfWeek dayOfWeek = orders.getDayOfWeek();
        return DayOfWeekCategory.isWeekDays(dayOfWeek);
    }
}
