package christmas.model.eventPolicy;

import christmas.model.Orders;

public class FixDiscountPolicy implements DiscountPolicy {

    private final Integer discountPrice;

    public FixDiscountPolicy(Integer discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Override
    public Integer calculateBenefitPrice(Orders orders) {
        return discountPrice;
    }

    @Override
    public Integer calculateDiscountPrice(Orders orders) {
        return calculateBenefitPrice(orders);
    }
}
