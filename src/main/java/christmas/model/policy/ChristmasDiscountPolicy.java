package christmas.model.policy;

import christmas.model.Orders;
import java.time.LocalDate;

public class ChristmasDiscountPolicy implements EventPolicy {

    private static ChristmasDiscountPolicy eventPolicy;

    private static final Integer START_DISCOUNT_PRICE = 1000;
    private static final Integer PRICE_INCREMENT = 100;
    private static final LocalDate END_DATE = LocalDate.of(2023, 12, 25);

    private ChristmasDiscountPolicy() {
    }

    public static ChristmasDiscountPolicy getInstance() {
        if (eventPolicy == null) {
            eventPolicy = new ChristmasDiscountPolicy();
        }
        return eventPolicy;
    }

    @Override
    public Integer calculateDiscountPrice(Orders orders) {
        if (isEventPeriod(orders)) {
            return START_DISCOUNT_PRICE + orders.getElapsedTime(START_DATE) * PRICE_INCREMENT;
        }
        return 0;
    }

    @Override
    public Boolean isEventPeriod(Orders orders) {
        return orders.notBefore(START_DATE) && orders.notAfter(END_DATE);
    }
}
