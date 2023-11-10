package christmas.model.policy;

import christmas.model.Orders;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class SpecialDiscountPolicy {

    private static final Integer DISCOUNT_PRICE = 1000;
    private static final LocalDate START_DATE = LocalDate.of(2023, 12, 1);
    private static final LocalDate END_DATE = LocalDate.of(2023, 12, 31);

    public Integer calculateDiscountPrice(Orders orders) {
        if (isEventPeriod(orders) && isEventPeriod(orders)) {
            return DISCOUNT_PRICE;
        }
        return 0;
    }

    private Boolean isEventPeriod(Orders orders) {
        return orders.notBefore(START_DATE) && orders.notAfter(END_DATE);
    }

    private Boolean isSpecialDay(Orders orders) {
        DayOfWeek dayOfWeek = orders.getDayOfWeek();
        return dayOfWeek.equals(DayOfWeek.SUNDAY) && orders.IsOrderDateChristmas();
    }
}
