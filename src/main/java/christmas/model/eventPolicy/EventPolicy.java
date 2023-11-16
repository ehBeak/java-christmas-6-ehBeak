package christmas.model.eventPolicy;

import christmas.model.Orders;

public interface EventPolicy {

    Integer calculateBenefitPrice(Orders orders);
}
