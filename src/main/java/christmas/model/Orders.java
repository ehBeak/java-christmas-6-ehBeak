package christmas.model;

import christmas.model.menu.Menu;
import java.time.LocalDate;
import java.util.Map;

public class Orders {

    private final Map<Menu, Integer> orders;
    private final LocalDate orderDate;

    public Orders(Map<Menu, Integer> orders, LocalDate orderDate) {
        this.orders = orders;
        this.orderDate = orderDate;
    }

}