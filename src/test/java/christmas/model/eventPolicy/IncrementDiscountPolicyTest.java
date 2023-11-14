package christmas.model.eventPolicy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import christmas.model.Orders;
import christmas.model.menu.Menu;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class IncrementDiscountPolicyTest {

    static Stream<Arguments> createOrderAndExpectedPrice() {
        return Stream.of(
                arguments(LocalDate.of(2023, 12, 1), 1000),
                arguments(LocalDate.of(2023, 12, 2), 1100),
                arguments(LocalDate.of(2023, 12, 25), 3400)
        );
    }

    @DisplayName("주문을 제공하면, 할인가가 1,000원으로 시작하여 날마다 할인 금액이 100원씩 증가한다")
    @ParameterizedTest
    @MethodSource("createOrderAndExpectedPrice")
    void calculateIncrementBenefitPrice(LocalDate orderDate, Integer expectedPrice) {
        Orders orders = createOrder(orderDate);
        IncrementDiscountPolicy incrementDiscountPolicy = new IncrementDiscountPolicy();

        Integer BenefitPrice = incrementDiscountPolicy.calculateBenefitPrice(orders);

        assertThat(BenefitPrice).isEqualTo(expectedPrice);
    }

    Orders createOrder(LocalDate orderDate) {
        EnumMap<Menu, Integer> menuCounts = new EnumMap<>(Menu.class);
        menuCounts.put(Menu.BUTTON_MUSHROOM_SOUP, 1);
        menuCounts.put(Menu.BARBECUE_PASTA, 3);
        menuCounts.put(Menu.CHOCOLATE_CAKE, 3);

        return new Orders(menuCounts, orderDate);
    }
}