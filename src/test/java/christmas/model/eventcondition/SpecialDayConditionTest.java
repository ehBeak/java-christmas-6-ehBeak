package christmas.model.eventcondition;

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

class SpecialDayConditionTest {

    static Stream<Arguments> createCondition() {
        return Stream.of(
                arguments(LocalDate.of(2023, 12, 2), false),
                arguments(LocalDate.of(2023, 12, 3), true),
                arguments(LocalDate.of(2023, 12, 4), false),
                arguments(LocalDate.of(2023, 12, 25), true)
        );
    }

    @DisplayName("주문을 제공하면 주문일이 일요일이거나 크리스마스인지 확인한다")
    @ParameterizedTest
    @MethodSource("createCondition")
    void satisfySpecialDayCondition(LocalDate orderDate, Boolean expected) {
        Orders orders = createOrder(orderDate);
        SpecialDayCondition specialDayCondition = new SpecialDayCondition();

        Boolean satisfyEventCondition = specialDayCondition.satisfyEventCondition(orders);

        assertThat(satisfyEventCondition).isEqualTo(expected);
    }

    Orders createOrder(LocalDate orderDate) {
        EnumMap<Menu, Integer> menuCounts = new EnumMap<>(Menu.class);
        menuCounts.put(Menu.BUTTON_MUSHROOM_SOUP, 1);
        menuCounts.put(Menu.BARBECUE_PASTA, 3);
        menuCounts.put(Menu.CHOCOLATE_CAKE, 3);

        return new Orders(menuCounts, orderDate);
    }

}