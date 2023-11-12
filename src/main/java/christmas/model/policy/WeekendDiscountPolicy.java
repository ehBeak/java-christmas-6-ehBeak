package christmas.model.policy;

import static christmas.model.menu.MenuCategory.MAIN;

import christmas.model.DayOfWeekCategory;
import christmas.model.Orders;
import java.time.DayOfWeek;

public class WeekendDiscountPolicy implements EventPolicy {

    private static WeekendDiscountPolicy eventPolicy;

    private static final Integer DISCOUNT_PRICE_PER_MAIN = 2023;

    private WeekendDiscountPolicy() {
    }

    public static WeekendDiscountPolicy getInstance() {
        if (eventPolicy == null) eventPolicy = new WeekendDiscountPolicy();
        return eventPolicy;
    }

    @Override
    public Integer calculateDiscountPrice(Orders orders) {
        int totalDiscount = 0;
        if (isEventPeriod(orders) && isWeekend(orders)) {
            totalDiscount += orders.countMenuOnMenuCategory(MAIN) * DISCOUNT_PRICE_PER_MAIN;
        }
        return totalDiscount;
    }

    private Boolean isWeekend(Orders orders) {
        DayOfWeek dayOfWeek = orders.getDayOfWeek();
        return DayOfWeekCategory.isWeekendDays(dayOfWeek);
    }
}
