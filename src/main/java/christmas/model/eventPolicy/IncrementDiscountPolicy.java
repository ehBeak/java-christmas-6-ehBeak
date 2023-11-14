package christmas.model.eventPolicy;

import christmas.model.Orders;
import java.time.LocalDate;

public class IncrementDiscountPolicy implements EventPolicy {

    private static final Integer START_DISCOUNT_PRICE = 1000;
    private static final Integer PRICE_INCREMENT = 100;

    private final LocalDate START_DATE = LocalDate.of(2023, 12, 1);

    @Override
    public Integer calculateBenefitPrice(Orders orders) {
        return START_DISCOUNT_PRICE + orders.getElapsedTime(START_DATE) * PRICE_INCREMENT;
    }
}
