package christmas.model;

import static christmas.model.policy.EventPolicyCategory.FREEBIES_EVENT;

import christmas.model.policy.EventPolicyCategory;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Customer {

    private final Orders orders;
    private final Set<EventPolicyCategory> benefitEvents;

    public Customer(Orders orders) {
        this.benefitEvents = createBenefits(orders);
        this.orders = orders;
    }

    private Set<EventPolicyCategory> createBenefits(Orders orders) {
        Set<EventPolicyCategory> categories = new HashSet<>();
        for (EventPolicyCategory eventCategory: EventPolicyCategory.values()) {
            Integer discountPrice = eventCategory.calculateDiscountPrice(orders);
            if (discountPrice != 0) {
                categories.add(eventCategory);
            }
        }
        return categories;
    }

    public Integer getBenefitPrice() {
        int totalDiscountPrice = 0;
        for (EventPolicyCategory event : benefitEvents) {
            totalDiscountPrice += event.calculateDiscountPrice(orders);
        }
        return totalDiscountPrice;
    }

    public Map<String, Integer> getBenefitDetails() {
        return benefitEvents.stream()
                .collect(Collectors.toMap(EventPolicyCategory::getEventName,
                        eventPolicyCategory -> eventPolicyCategory.calculateDiscountPrice(orders)));
    }

    public Integer getExpectedPayment() {
        return orders.calculateTotalPrice() + getDiscountPrice();
    }

    private Integer getDiscountPrice() {
        if (isEligibleEvent(FREEBIES_EVENT)) {
            return getBenefitPrice() - FREEBIES_EVENT.calculateDiscountPrice(orders);
        }
        return getBenefitPrice();
    }

    private Boolean isEligibleEvent(EventPolicyCategory eventPolicyCategory) {
        return benefitEvents.contains(eventPolicyCategory);
    }
}
