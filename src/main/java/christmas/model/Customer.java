package christmas.model;


import christmas.model.menu.Menu;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Customer {

    private final Orders orders;
    private final Set<EventCategory> eventCategories;

    public Customer(Orders orders) {
        this.orders = orders;
        this.eventCategories = createBenefits(orders);
    }

    private Set<EventCategory> createBenefits(Orders orders) {
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
        return orders.calculateTotalPrice() - getDiscountPrice();
    }

    public Map<String, Integer> getFreebies() {
        Map<Menu, Integer> freebieMenus = addFreebieMenus();
        return freebieMenus.keySet()
                .stream()
                .collect(Collectors.toMap(Menu::getName, freebieMenus::get));
    }

    private Map<Menu, Integer> addFreebieMenus() {
        Map<Menu, Integer> menuIntegerMap = new HashMap<>();
        for (EventCategory eventCategory : eventCategories) {
            menuIntegerMap.putAll(eventCategory.getFreebies(orders));
        }
        return menuIntegerMap;
    }

    public Badge getEventBadge() {
        return Badge.valueOf(getBenefitPrice());
    }

    private Integer getDiscountPrice() {
        return eventCategories.stream()
                .mapToInt(event -> event.calculateDiscountPrice(orders))
                .sum();
    }
}
