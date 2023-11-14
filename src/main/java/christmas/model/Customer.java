package christmas.model;


import christmas.model.menu.Menu;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Customer {

    private final Orders orders;
    private Set<EventCategory> eventCategories;

    public Customer(Orders orders) {
        this.orders = orders;
        this.eventCategories = createBenefits1(orders);
    }

    private Set<EventCategory> createBenefits1(Orders orders) {
        if (orders.calculateTotalPrice() < 10000) {
            return new HashSet<>();
        }
        return Arrays.stream(EventCategory.values())
                .filter(eventCategory -> eventCategory.calculateBenefitPrice(orders) != 0)
                .collect(Collectors.toSet());
    }

    public Integer getBenefitPrice() {
        return eventCategories.stream()
                .mapToInt(event -> event.calculateBenefitPrice(orders))
                .sum();
    }

    public Map<String, Integer> getBenefitDetails() {
        return eventCategories.stream()
                .collect(Collectors.toMap(EventCategory::getEventName,
                        eventPolicyCategory -> eventPolicyCategory.calculateBenefitPrice(orders)));
    }

    public Integer getExpectedPayment() {
        return orders.calculateTotalPrice() + getDiscountPrice();
    }

    public Map<String, Integer> getFreebies() {
        Map<Menu, Integer> freebies = EventCategory.FREEBIES_EVENT.getFreebies(orders);
        if (isEligibleEvent(EventCategory.FREEBIES_EVENT)) {
            return freebies.keySet().stream()
                    .collect(Collectors.toMap(Menu::getName, Menu::getPrice));
        }
        return Map.of();
    }

    public Badge getEventBadge() {
        return Badge.valueOf(getDiscountPrice());
    }

    private Integer getDiscountPrice() {
        if (isEligibleEvent(EventCategory.FREEBIES_EVENT)) {
            return getBenefitPrice() - EventCategory.FREEBIES_EVENT.calculateBenefitPrice(orders);
        }
        return getBenefitPrice();
    }

    private Boolean isEligibleEvent(EventCategory eventPolicyCategory) {
        return eventCategories.contains(eventPolicyCategory);
    }
}
