package christmas.model;

import static christmas.model.EventCategory.CHRISTMAS_EVENT;
import static christmas.model.EventCategory.FREEBIES_EVENT;
import static christmas.model.EventCategory.SPECIAL_EVENT;
import static christmas.model.EventCategory.WEEKDAY_EVENT;
import static christmas.model.EventCategory.WEEKEND_EVENT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import christmas.exception.ExceptionWithMessage;
import christmas.model.menu.Menu;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;
import org.junit.jupiter.params.provider.MethodSource;

class EventCategoryTest {

    static Stream<Arguments> creatMenuCategoryAndBenefitPrice() {
        return Stream.of(
                arguments(createValidOrder(LocalDate.of(2023, 12, 1)), CHRISTMAS_EVENT, -1000),
                arguments(createValidOrder(LocalDate.of(2023, 12, 1)), WEEKEND_EVENT, -4046),
                arguments(createValidOrder(LocalDate.of(2023, 12, 3)), WEEKDAY_EVENT, -6069),
                arguments(createValidOrder(LocalDate.of(2023, 12, 25)), SPECIAL_EVENT, -1000),
                arguments(createValidOrder(LocalDate.of(2023, 12, 24)), SPECIAL_EVENT, -1000),
                arguments(createValidOrder(LocalDate.of(2023, 12, 25)), FREEBIES_EVENT, -25000),

                arguments(createValidOrder(LocalDate.of(2023, 12, 26)), CHRISTMAS_EVENT, 0),
                arguments(createValidOrder(LocalDate.of(2023, 12, 3)), WEEKEND_EVENT, 0),
                arguments(createValidOrder(LocalDate.of(2023, 12, 1)), WEEKDAY_EVENT, 0),
                arguments(createValidOrder(LocalDate.of(2023, 12, 26)), SPECIAL_EVENT, 0),
                arguments(createNotOverThresholdPrice(LocalDate.of(2023, 12, 25)), FREEBIES_EVENT, 0)
        );
    }

    @DisplayName("주문 내역이 이벤트 기간에 있고 조건에 따라 혜택 금액을 계산해서 반환한다")
    @ParameterizedTest
    @MethodSource("creatMenuCategoryAndBenefitPrice")
    void validOrderCalculateBenefitPrice(Orders orders, EventCategory eventCategory, Integer expectedPrice) {

        Integer discountPrice = eventCategory.calculateBenefitPrice(orders);

        assertThat(discountPrice).isEqualTo(expectedPrice);
    }

    static Stream<Arguments> creatMenuCategoryAndDiscountPrice() {
        return Stream.of(
                arguments(CHRISTMAS_EVENT, -3400),
                arguments(WEEKEND_EVENT, -4046),
                arguments(WEEKDAY_EVENT, -6069),
                arguments(SPECIAL_EVENT, -1000),
                arguments(FREEBIES_EVENT, 0)
        );
    }

    @DisplayName("주문 내역이 할인 이벤트이면 할인 금액을 반환하고 아니면 0원을 반환한다")
    @ParameterizedTest
    @MethodSource("creatMenuCategoryAndDiscountPrice")
    void discountPolicyReturnDiscountPrice(EventCategory eventCategory, Integer expectedPrice) {
        Orders validOrders = createValidOrder(LocalDate.of(2023, 12, 25));

        Integer discountPrice = eventCategory.calculateDiscountPrice(validOrders);

        assertThat(discountPrice).isEqualTo(expectedPrice);
    }

    @DisplayName("주문 내역이 증정 이벤트면 증정 내역을 반환한다")
    @ParameterizedTest
    @EnumSource(mode = Mode.INCLUDE, names = {"FREEBIES_EVENT"})
    void freebiesPolicyReturnFreebies(EventCategory category) {
        Orders orders = createNotOverThresholdPrice(LocalDate.of(2023, 12, 25));

        Map<Menu, Integer> freebies = category.getFreebies(orders);

        assertThat(freebies.get(Menu.CHAMPAGNE)).isEqualTo(1);

    }

    @DisplayName("주문 내역이 증정 이벤트가 아니면 예외를 발생시킨다")
    @ParameterizedTest
    @EnumSource(mode = Mode.EXCLUDE, names = {"FREEBIES_EVENT"})
    void freebiesPolicyThrowException(EventCategory category) {
        Orders orders = createNotOverThresholdPrice(LocalDate.of(2023, 12, 25));

        assertThatThrownBy(() -> category.getFreebies(orders))
                .isInstanceOf(ExceptionWithMessage.class)
                .hasMessage("[ERROR] 증정 할인 이벤트가 아닙니다.");
    }


    static Orders createValidOrder(LocalDate orderDate) {
        EnumMap<Menu, Integer> menuCounts = new EnumMap<>(Menu.class);
        menuCounts.put(Menu.BUTTON_MUSHROOM_SOUP, 1);
        menuCounts.put(Menu.BARBECUE_PASTA, 2);
        menuCounts.put(Menu.CHOCOLATE_CAKE, 3);

        return new Orders(menuCounts, orderDate);
    }

    static Orders createNotOverThresholdPrice(LocalDate orderDate) {
        EnumMap<Menu, Integer> menuCounts = new EnumMap<>(Menu.class);
        menuCounts.put(Menu.BUTTON_MUSHROOM_SOUP, 1); // 6000
        menuCounts.put(Menu.BARBECUE_PASTA, 1); // 54000

        return new Orders(menuCounts, orderDate);
    }

}