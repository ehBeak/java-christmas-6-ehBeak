package christmas.model.eventcondition;

import static christmas.model.DayOfWeekCategory.WEEKEND_DAYS;
import static christmas.model.DayOfWeekCategory.WEEK_DAYS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import christmas.model.DayOfWeekCategory;
import christmas.model.Orders;
import christmas.model.menu.Menu;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DayOfWeekConditionTest {

    static Stream<Arguments> createCondition() {
        return Stream.of(
                arguments(LocalDate.of(2023, 12, 2), WEEKEND_DAYS, true),
                arguments(LocalDate.of(2023, 12, 3), WEEKEND_DAYS, false),
                arguments(LocalDate.of(2023, 12, 4), WEEK_DAYS, true)
        );
    }

    @DisplayName("주문 내역을 제공하면, 매개변수 값에 따라 주문일이 평일인지 아닌지 판단한다")
    @ParameterizedTest
    @MethodSource("createCondition")
    void satisfyEventCondition(LocalDate orderDate, DayOfWeekCategory dayOfWeekCategory, Boolean expected) {
        Orders orders = createOrder(orderDate);
        DayOfWeekCondition dayOfWeekCondition = new DayOfWeekCondition(dayOfWeekCategory);

        Boolean satisfyEventCondition = dayOfWeekCondition.satisfyEventCondition(orders);

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