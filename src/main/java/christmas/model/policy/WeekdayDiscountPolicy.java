package christmas.model.policy;

import christmas.model.Orders;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekdayDiscountPolicy implements EventPolicy{

    private static final Integer DISCOUNT_PRICE_PER_DESSERT = 2023;
    private static final LocalDate START_DATE = LocalDate.of(2023, 12, 1);
    private static final LocalDate END_DATE = LocalDate.of(2023, 12, 31);

    @Override
    public Integer calculateDiscountPrice(Orders orders) {
        Integer totalDiscount = 0;
        if (isEventPeriod(orders) && isWeekDays(orders)) {
            totalDiscount += orders.findDessertCount() * DISCOUNT_PRICE_PER_DESSERT;
        }
        return totalDiscount;
    }

    private Boolean isEventPeriod(Orders orders) {
        return orders.notBefore(START_DATE) && orders.notAfter(END_DATE);
    }

    private Boolean isWeekDays(Orders orders) {
        DayOfWeek dayOfWeek = orders.getDayOfWeek();
        return DayOfWeekCategory.isWeekDays(dayOfWeek);
    }
}
