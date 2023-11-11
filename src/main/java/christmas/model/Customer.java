package christmas.model;

import christmas.model.policy.EventPolicyCategory;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Customer {

    private final Orders orders;
    private final Set<EventPolicyCategory> eventPolicies;

    public Customer(Orders orders, Set<EventPolicyCategory> eventPolicies) {
        this.eventPolicies = eventPolicies;
        this.orders = orders;
    }

    public Integer getTotalDiscountPrice() {
        int totalDiscountPrice = 0;
        for (EventPolicyCategory event : eventPolicies) {
            totalDiscountPrice += event.calculateDiscountPrice(orders);
        }
        return totalDiscountPrice;
    }

    public Map<String, Integer> getBenefitDetails() {
        return eventPolicies.stream()
                .collect(Collectors.toMap(EventPolicyCategory::getEventName,
                        eventPolicyCategory -> eventPolicyCategory.calculateDiscountPrice(orders)));
    }
}
