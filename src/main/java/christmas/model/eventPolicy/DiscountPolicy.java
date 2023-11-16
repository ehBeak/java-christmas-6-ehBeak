package christmas.model.eventPolicy;

import christmas.model.Orders;

public interface DiscountPolicy extends EventPolicy {

    Integer calculateDiscountPrice(Orders orders);
}
