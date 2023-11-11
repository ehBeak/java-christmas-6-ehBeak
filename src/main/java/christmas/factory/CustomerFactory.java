package christmas.factory;

import christmas.model.Customer;
import christmas.model.Orders;
import christmas.model.policy.EventPolicyCategory;
import java.util.HashSet;
import java.util.Set;

public class CustomerFactory {

    public Customer createCustomer(Orders orders) {
        Set<EventPolicyCategory> categories = new HashSet<>();
        for (EventPolicyCategory eventCategory: EventPolicyCategory.values()) {
            Integer discountPrice = eventCategory.calculateDiscountPrice(orders);
            if (discountPrice != 0) {
                categories.add(eventCategory);
            }
        }
        return new Customer(orders, categories);
    }
}
