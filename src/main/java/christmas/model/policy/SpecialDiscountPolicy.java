package christmas.model.policy;

import christmas.model.Orders;
import java.time.DayOfWeek;

public class SpecialDiscountPolicy implements EventPolicy {

    private static SpecialDiscountPolicy eventPolicy;
    private static final Integer DISCOUNT_PRICE = 1000;

    private SpecialDiscountPolicy() {
    }

    public static SpecialDiscountPolicy getInstance() {
        if (eventPolicy == null) eventPolicy = new SpecialDiscountPolicy();
        return eventPolicy;
    }

    @Override
    public Integer calculateDiscountPrice(Orders orders) {
        if (isEventPeriod(orders) && isSpecialDay(orders)) {
            return DISCOUNT_PRICE;
        }
        return 0;
    }

    private Boolean isSpecialDay(Orders orders) {
        DayOfWeek dayOfWeek = orders.getDayOfWeek();
        return dayOfWeek.equals(DayOfWeek.SUNDAY) || orders.IsOrderDateChristmas();
    }
}
