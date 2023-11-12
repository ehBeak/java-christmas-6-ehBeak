package christmas.model.policy;

import christmas.model.Orders;
import christmas.model.menu.Menu;

public class FreebiesEventPolicy implements EventPolicy {

    private static final Integer DISCOUNT_THRESHOLD_PRICE = 120000;

    @Override
    public Integer calculateDiscountPrice(Orders orders) {
        if (isEventPeriod(orders) && satisfiesDiscountConditions(orders)) {
            return Menu.CHAMPAGNE.getPrice();
        }
        return 0;
    }

    private Boolean satisfiesDiscountConditions(Orders orders) {
        return orders.calculateTotalPrice() >= DISCOUNT_THRESHOLD_PRICE;
    }

}
