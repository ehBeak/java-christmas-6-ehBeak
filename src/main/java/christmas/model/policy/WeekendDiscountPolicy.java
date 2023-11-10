package christmas.model.policy;

import christmas.model.Orders;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekendDiscountPolicy {

    private static final Integer DISCOUNT_PRICE_PER_MAIN = 2023;
    private static final LocalDate START_DATE = LocalDate.of(2023, 12, 1);
    private static final LocalDate END_DATE = LocalDate.of(2023, 12, 31);

    public Integer calculateDiscountPrice(Orders orders) {
        Integer totalDiscount = 0;
        if (isEventPeriod(orders) && isWeekend(orders)) {
            totalDiscount += orders.findMainCount() * DISCOUNT_PRICE_PER_MAIN;
        }
        return totalDiscount;
    }

    private Boolean isEventPeriod(Orders orders) {
        return orders.notBefore(START_DATE) && orders.notAfter(END_DATE);
    }

    private Boolean isWeekend(Orders orders) {
        DayOfWeek dayOfWeek = orders.getDayOfWeek();
        return DayOfWeekCategory.isWeekendDays(dayOfWeek);
    }
}
