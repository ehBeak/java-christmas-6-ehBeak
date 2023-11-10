package christmas.factory;

import christmas.model.Customer;
import christmas.model.Orders;
import christmas.model.policy.EventPolicy;
import christmas.model.policy.EventPolicyCategory;
import java.util.HashSet;
import java.util.Set;

public class CustomerFactory {

    public Customer createCustomer(Orders orders) {
        Set<EventPolicy> eventPolicies = new HashSet<>();
        for (EventPolicyCategory eventCategory: EventPolicyCategory.values()) {
            Integer discountPrice = eventCategory.getEventPolicy().calculateDiscountPrice(orders);
            if (discountPrice != 0) {
                eventPolicies.add(eventCategory.getEventPolicy());
            }
        }

        return new Customer(orders, eventPolicies);
    }
}
