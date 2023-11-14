package christmas.model.eventcondition;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.Orders;
import christmas.model.menu.Menu;
import java.time.LocalDate;
import java.util.EnumMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ThresholdPriceConditionTest {

    @DisplayName("주문 내역을 제공하면 주문의 총합이 특정 금액을 넘으면 true를 반환한다")
    @ParameterizedTest
    @ValueSource(ints = {21000, 2000})
    void satisfyOverTotalPriceCondition(Integer thresholdPrice) {
        Orders orders = createOrder(LocalDate.of(2023, 12, 3));
        ThresholdPriceCondition thresholdPriceCondition = new ThresholdPriceCondition(thresholdPrice);

        Boolean satisfyEventCondition = thresholdPriceCondition.satisfyEventCondition(orders);

        assertThat(satisfyEventCondition).isTrue();
    }

    @DisplayName("주문 내역을 제공하면 주문의 총합이 특정 금액 미만이면 false를 반환한다")
    @ParameterizedTest
    @ValueSource(ints = {21100, 22000})
    void satisfyNotOverTotalPriceCondition(Integer thresholdPrice) {
        Orders orders = createOrder(LocalDate.of(2023, 12, 3));
        ThresholdPriceCondition thresholdPriceCondition = new ThresholdPriceCondition(thresholdPrice);

        Boolean satisfyEventCondition = thresholdPriceCondition.satisfyEventCondition(orders);

        assertThat(satisfyEventCondition).isFalse();
    }

    Orders createOrder(LocalDate orderDate) {
        EnumMap<Menu, Integer> menuCounts = new EnumMap<>(Menu.class);
        menuCounts.put(Menu.BUTTON_MUSHROOM_SOUP, 1);
        menuCounts.put(Menu.CHOCOLATE_CAKE, 1);

        return new Orders(menuCounts, orderDate);
    }
}