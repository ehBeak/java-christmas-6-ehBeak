package christmas.model.eventcondition;

import christmas.model.Orders;

public class ThresholdPriceCondition implements EventCondition {

    private final Integer discountThresholdPrice;

    public ThresholdPriceCondition(Integer discountThresholdPrice) {
        this.discountThresholdPrice = discountThresholdPrice;
    }

    @Override
    public Boolean satisfyEventCondition(Orders orders) {
        return orders.calculateTotalPrice() >= discountThresholdPrice;
    }
}