package christmas.model.eventPolicy;

import christmas.model.Orders;

public class FixDiscountPolicy implements DiscountPolicy {

    private static final Integer DISCOUNT_PRICE = 1000;

    @Override
    public Integer calculateBenefitPrice(Orders orders) {
        return DISCOUNT_PRICE;
    }

    @Override
    public Integer calculateDiscountPrice(Orders orders) {
        return calculateBenefitPrice(orders);
    }
}
