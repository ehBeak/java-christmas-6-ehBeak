package christmas.model.policy;

import christmas.model.Orders;
import java.time.DayOfWeek;

public class WeekendDiscountPolicy implements EventPolicy {

    private static final Integer DISCOUNT_PRICE_PER_MAIN = 2023;

    @Override
    public Integer calculateDiscountPrice(Orders orders) {
        int totalDiscount = 0;
        if (isEventPeriod(orders) && isWeekend(orders)) {
            totalDiscount += orders.findMainCount() * DISCOUNT_PRICE_PER_MAIN;
        }
        return totalDiscount;
    }

    private Boolean isWeekend(Orders orders) {
        DayOfWeek dayOfWeek = orders.getDayOfWeek();
        return DayOfWeekCategory.isWeekendDays(dayOfWeek);
    }
}
