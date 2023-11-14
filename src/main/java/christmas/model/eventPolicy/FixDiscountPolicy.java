package christmas.model.eventPolicy;

import christmas.model.Orders;
import christmas.model.eventPolicy.EventPolicy;

public class FixDiscountPolicy implements EventPolicy {

    private static final Integer DISCOUNT_PRICE = 1000;

    @Override
    public Integer calculateBenefitPrice(Orders orders) {
        return DISCOUNT_PRICE;
    }

}
