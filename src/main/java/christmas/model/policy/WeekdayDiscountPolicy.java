package christmas.model.policy;

import static christmas.model.DayOfWeekCategory.WEEK_DAYS;
import static christmas.model.menu.MenuCategory.DESSERT;

import christmas.model.DayOfWeekCategory;
import christmas.model.Orders;
import java.time.DayOfWeek;

public class WeekdayDiscountPolicy implements EventPolicy {

    private static WeekdayDiscountPolicy eventPolicy;

    private static final Integer DISCOUNT_PRICE_PER_DESSERT = 2023;

    private WeekdayDiscountPolicy() {
    }

    public static WeekdayDiscountPolicy getInstance() {
        if (eventPolicy == null) {
            eventPolicy = new WeekdayDiscountPolicy();
        }
        return eventPolicy;
    }

    @Override
    public Integer calculateDiscountPrice(Orders orders) {
        int totalDiscount = 0;
        if (isEventPeriod(orders) && isWeekDays(orders)) {
            totalDiscount += orders.countMenuOnMenuCategory(DESSERT) * DISCOUNT_PRICE_PER_DESSERT;
        }
        return totalDiscount;
    }

    private Boolean isWeekDays(Orders orders) {
        DayOfWeek dayOfWeek = orders.getDayOfWeek();
        return DayOfWeekCategory.isDayOfWeekInCategory(dayOfWeek, WEEK_DAYS);
    }
}
