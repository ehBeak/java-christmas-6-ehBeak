package christmas.model.eventPolicy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import christmas.model.Orders;
import christmas.model.menu.Menu;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FreebiesEventPolicyTest {

    static Stream<Arguments> createFreebiesAndExpectedPrice() {
        return Stream.of(
                arguments(Map.of(), 0),
                arguments(Map.of(Menu.CHAMPAGNE, 1), 25000),
                arguments(Map.of(Menu.CHOCOLATE_CAKE, 2), 30000)
        );
    }

    @DisplayName("주문을 제공하면 증정 제품의 금액만큼 혜택 금액을 계산한다")
    @ParameterizedTest
    @MethodSource("createFreebiesAndExpectedPrice")
    void calculateIncrementBenefitPrice(Map<Menu, Integer> freebies, Integer expectedPrice) {
        Orders orders = createOrder(LocalDate.of(2023, 12, 1));
        FreebiesEventPolicy incrementDiscountPolicy = new FreebiesEventPolicy(freebies);

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