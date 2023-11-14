package christmas.model.eventperiod;

import christmas.model.Orders;
import java.time.LocalDate;

public class ChristmasPeriod implements EventPeriod {

    private static final LocalDate START_DATE = LocalDate.of(2023, 12, 1);
    private static final LocalDate END_DATE = LocalDate.of(2023, 12, 25);

    @Override
    public Boolean isEventPeriod(Orders orders) {
        return orders.notBefore(START_DATE) && orders.notAfter(END_DATE);
    }
}
