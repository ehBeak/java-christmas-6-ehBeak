package christmas.model.eventPolicy;

import christmas.model.Orders;
import christmas.model.menu.Menu;
import java.util.Map;

public class FreebiesEventPolicy implements FreebiesPolicy {

    private final Map<Menu, Integer> freebies;

    public FreebiesEventPolicy(Map<Menu, Integer> freebies) {
        this.freebies = freebies;
    }

    @Override
    public Integer calculateBenefitPrice(Orders orders) {
        return freebies.keySet().stream()
                .mapToInt(menu -> menu.getPrice() * freebies.get(menu))
                .sum();
    }

    @Override
    public Map<Menu, Integer> getFreebies(Orders orders) {
        return freebies;
    }
}
