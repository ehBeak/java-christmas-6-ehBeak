package christmas.model.policy;

import christmas.model.Orders;
import java.time.LocalDate;

@FunctionalInterface
public interface DiscountPolicy {

    LocalDate START_DATE = LocalDate.of(2023, 12, 1);
    LocalDate END_DATE = LocalDate.of(2023, 12, 31);

    Integer calculateBenefitPrice(Orders orders);

    default Boolean isEventPeriod(Orders orders) {
        return orders.notBefore(START_DATE) && orders.notAfter(END_DATE);
    }
}
