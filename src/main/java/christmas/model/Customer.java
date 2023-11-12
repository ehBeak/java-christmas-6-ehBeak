package christmas.model;

import static christmas.model.EventPolicyCategory.FREEBIES_EVENT;

import christmas.model.menu.Menu;
import java.util.Arrays;
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
        if (orders.calculateTotalPrice() < 10000) {
            return new HashSet<>();
        }
        return Arrays.stream(EventPolicyCategory.values())
                .filter(eventCategory -> eventCategory.calculateDiscountPrice(orders) != 0)
                .collect(Collectors.toSet());
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

    public Map<String, Integer> getFreebies() {
        if (isEligibleEvent(FREEBIES_EVENT)) {
            return Map.of(Menu.CHAMPAGNE.getName(), 1);
        }
        return Map.of();
    }

    public Badge getEventBadge() {
        return Badge.valueOf(getDiscountPrice());
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
