package christmas.model.eventcondition;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.Orders;
import christmas.model.menu.Menu;
import java.time.LocalDate;
import java.util.EnumMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NoEventConditionTest {

    @DisplayName("주문 내역을 제공하면 항상 참을 반환한다.")
    @Test
    void satisfyEventCondition() {
        Orders orders = createOrder(LocalDate.of(2023, 12, 1));
        NoEventCondition noEventCondition = new NoEventCondition();

        Boolean satisfyEventCondition = noEventCondition.satisfyEventCondition(orders);

        assertThat(satisfyEventCondition).isTrue();
    }

    Orders createOrder(LocalDate orderDate) {
        EnumMap<Menu, Integer> menuCounts = new EnumMap<>(Menu.class);
        menuCounts.put(Menu.BUTTON_MUSHROOM_SOUP, 1);
        menuCounts.put(Menu.BARBECUE_PASTA, 3);
        menuCounts.put(Menu.CHOCOLATE_CAKE, 3);

        return new Orders(menuCounts, orderDate);
    }
}