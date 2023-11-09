package christmas.factory;

import christmas.model.Orders;
import christmas.model.menu.Menu;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;

public class OrderFactory {

    public Orders createOrders(Map<String, Integer> orderFormats, LocalDate orderDate) {
        Map<Menu, Integer> orders = new EnumMap<>(Menu.class);
        for (String menuName : orderFormats.keySet()) {
            orders.put(Menu.Of(menuName), orderFormats.get(menuName));
        }

        return new Orders(orders, orderDate);
    }
}
