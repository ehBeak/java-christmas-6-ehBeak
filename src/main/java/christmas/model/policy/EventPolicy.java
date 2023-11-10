package christmas.model.policy;

import christmas.model.Orders;

public interface EventPolicy {
    Integer calculateDiscountPrice(Orders orders);
}
