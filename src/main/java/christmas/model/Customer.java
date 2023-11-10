package christmas.model;

import christmas.model.policy.EventPolicy;
import java.util.Set;

public class Customer {

    private final Orders orders;
    private final Set<EventPolicy> eventPolicies;

    public Customer(Orders orders, Set<EventPolicy> eventPolicies) {
        this.orders = orders;
        this.eventPolicies = eventPolicies;
    }

    public Integer getTotalDiscountPrice() {
        int totalDiscountPrice = 0;
        for (EventPolicy event : eventPolicies) {
            totalDiscountPrice += event.calculateDiscountPrice(orders);
        }
        return totalDiscountPrice;
    }
}
