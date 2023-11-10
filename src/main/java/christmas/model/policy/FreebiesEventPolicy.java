package christmas.model.policy;

import christmas.model.Orders;
import christmas.model.menu.Menu;
import java.time.LocalDate;

public class FreebiesEventPolicy implements EventPolicy{

    private static final Integer DISCOUNT_THRESHOLD_PRICE = 120000;
    private static final LocalDate START_DATE = LocalDate.of(2023, 12, 1);
    private static final LocalDate END_DATE = LocalDate.of(2023, 12, 31);

    @Override
    public Integer calculateDiscountPrice(Orders orders) {
        if (isEventPeriod(orders) && satisfiesDiscountConditions(orders)) {
            return Menu.CHAMPAGNE.getPrice();
        }
        return 0;
    }

    private Boolean isEventPeriod(Orders orders) {
        return orders.notBefore(START_DATE) && orders.notAfter(END_DATE);
    }

    private Boolean satisfiesDiscountConditions(Orders orders) {
        return orders.calculateTotalPrice() >= DISCOUNT_THRESHOLD_PRICE;
    }

}
