package christmas.model.policy;

import christmas.model.Orders;
import christmas.model.menu.Menu;
import java.time.LocalDate;

public class FreebiesEventPolicy {

    private static final Integer discountThresholdPrice = 120000;
    private static final LocalDate startDate = LocalDate.of(2023, 12, 1);
    private static final LocalDate endDate = LocalDate.of(2023, 12, 31);

    public Integer calculateDiscountPrice(Orders orders) {
        if (isEventPeriod(orders) && satisfiesDiscountConditions(orders)) {
            return Menu.CHAMPAGNE.getPrice();
        }
        return 0;
    }

    private Boolean isEventPeriod(Orders orders) {
        return orders.notBefore(startDate) && orders.notAfter(endDate);
    }

    private Boolean satisfiesDiscountConditions(Orders orders) {
        return orders.calculateTotalPrice() >= discountThresholdPrice;
    }

}
