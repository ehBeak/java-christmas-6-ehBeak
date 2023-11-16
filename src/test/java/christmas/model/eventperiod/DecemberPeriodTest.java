package christmas.model.eventperiod;

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

class DecemberPeriodTest {

    static Stream<Arguments> createPeriod() {
        return Stream.of(
                arguments(LocalDate.of(2023, 11, 30), false),
                arguments(LocalDate.of(2023, 12, 1), true),
                arguments(LocalDate.of(2023, 12, 25), true),
                arguments(LocalDate.of(2023, 12, 26), true),
                arguments(LocalDate.of(2024, 12, 26), false)
        );
    }

    @DisplayName("주문 내역의 주문일이 12월이면 참을 반환한다")
    @ParameterizedTest
    @MethodSource("createPeriod")
    void isChristmasPeriod(LocalDate orderDate, Boolean expected) {
        Orders orders = createOrder(orderDate);
        DecemberPeriod decemberPeriod = new DecemberPeriod();

        Boolean eventPeriod = decemberPeriod.isEventPeriod(orders);

        assertThat(eventPeriod).isEqualTo(expected);
    }

    Orders createOrder(LocalDate orderDate) {
        EnumMap<Menu, Integer> menuCounts = new EnumMap<>(Menu.class);
        menuCounts.put(Menu.BUTTON_MUSHROOM_SOUP, 1);
        menuCounts.put(Menu.BARBECUE_PASTA, 3);
        menuCounts.put(Menu.CHOCOLATE_CAKE, 3);

        return new Orders(menuCounts, orderDate);
    }

}