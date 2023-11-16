package christmas.model;

import static christmas.exception.ErrorMessage.INVALID_ORDER;
import static christmas.exception.ErrorMessage.ONLY_BEVERAGE_NOT_ALLOWED;
import static christmas.exception.ErrorMessage.ORDER_OVER_20_NOT_ALLOWED;
import static christmas.model.menu.Menu.BUTTON_MUSHROOM_SOUP;
import static christmas.model.menu.Menu.CHOCOLATE_CAKE;
import static christmas.model.menu.Menu.RED_WINE;
import static christmas.model.menu.Menu.T_BONE_STEAK;
import static christmas.model.menu.Menu.ZERO_COLA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import christmas.model.menu.Menu;
import christmas.model.menu.MenuCategory;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class OrdersTest {

    Orders createValidOrders(LocalDate orderDate) {
        Map<Menu, Integer> orderMenus = new EnumMap<>(Menu.class);
        orderMenus.put(BUTTON_MUSHROOM_SOUP, 2);
        orderMenus.put(RED_WINE, 1);
        orderMenus.put(T_BONE_STEAK, 1);
        return new Orders(orderMenus, orderDate);
    }

    @DisplayName("메뉴당 구매 개수가 0개면 예외가 발생한다.")
    @Test
    void ZeroOrLessCountThrowException() {
        LocalDate orderDate = LocalDate.of(2023, 12, 11);
        Map<Menu, Integer> orderMenus = new EnumMap<>(Menu.class);
        orderMenus.put(BUTTON_MUSHROOM_SOUP, 0);

        assertThatThrownBy(() -> new Orders(orderMenus, orderDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER.toString());
    }

    @DisplayName("구매한 메뉴 총 개수가 20개가 넘어가면 예외가 발생한다.")
    @Test
    void OverTwentyTotalCountThrowException() {
        LocalDate orderDate = LocalDate.of(2023, 12, 11);
        Map<Menu, Integer> orderMenus = new EnumMap<>(Menu.class);
        orderMenus.put(BUTTON_MUSHROOM_SOUP, 10);
        orderMenus.put(CHOCOLATE_CAKE, 5);
        orderMenus.put(RED_WINE, 6);

        assertThatThrownBy(() -> new Orders(orderMenus, orderDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ORDER_OVER_20_NOT_ALLOWED.toString());
    }

    @DisplayName("구매한 메뉴가 음료 밖에 없으면 예외가 발생한다.")
    @Test
    void orderOnlyBeverageThrowException() {
        LocalDate orderDate = LocalDate.of(2023, 12, 11);
        Map<Menu, Integer> orderMenus = new EnumMap<>(Menu.class);
        orderMenus.put(ZERO_COLA, 1);
        orderMenus.put(RED_WINE, 1);

        assertThatThrownBy(() -> new Orders(orderMenus, orderDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ONLY_BEVERAGE_NOT_ALLOWED.toString());
    }

    @DisplayName("총 구매 개수가 20개 이하고 메뉴당 1개 이상 주문하며 음료 외의 메뉴도 주문하면 예외가 발생하지 않는다.")
    @Test
    void validOrderNotThrowException() {
        LocalDate orderDate = LocalDate.of(2023, 12, 11);
        Map<Menu, Integer> orderMenus = new EnumMap<>(Menu.class);
        orderMenus.put(ZERO_COLA, 1);
        orderMenus.put(RED_WINE, 1);
        orderMenus.put(CHOCOLATE_CAKE, 2);

        assertThatNoException().isThrownBy(() -> new Orders(orderMenus, orderDate));
    }

    @DisplayName("주문한 메뉴들의 총 가격을 구한다.")
    @Test
    void calculateTotalPrice() {
        LocalDate orderDate = LocalDate.of(2023, 12, 11);
        Map<Menu, Integer> orderMenus = new EnumMap<>(Menu.class);
        orderMenus.put(BUTTON_MUSHROOM_SOUP, 2);
        orderMenus.put(RED_WINE, 1);
        orderMenus.put(T_BONE_STEAK, 1);
        Orders orders = new Orders(orderMenus, orderDate);

        Integer totalPrice = orders.calculateTotalPrice();

        assertThat(totalPrice).isEqualTo(127000);
    }

    static Stream<Arguments> menuCategoryCount() {
        return Stream.of(
                arguments(MenuCategory.APPETIZER, 2),
                arguments(MenuCategory.MAIN, 1),
                arguments(MenuCategory.BEVERAGE, 1),
                arguments(MenuCategory.DESSERT, 0)
        );
    }

    @DisplayName("주어진 메뉴 종류에 해당하는 주문 메뉴 개수를 반환한다.")
    @ParameterizedTest
    @MethodSource("menuCategoryCount")
    void calculate(MenuCategory menuCategory, Integer menuCount) {
        LocalDate orderDate = LocalDate.of(2023, 12, 11);
        Map<Menu, Integer> orderMenus = new EnumMap<>(Menu.class);
        orderMenus.put(BUTTON_MUSHROOM_SOUP, 2);
        orderMenus.put(RED_WINE, 1);
        orderMenus.put(T_BONE_STEAK, 1);
        Orders orders = new Orders(orderMenus, orderDate);

        Integer totalPrice = orders.countMenuOnMenuCategory(menuCategory);

        assertThat(totalPrice).isEqualTo(menuCount);
    }

    @DisplayName("주문일이 주어진 날짜 이전이면 거짓을 반환한다.")
    @Test
    void orderDateBeforeDateReturnFalse() {
        Orders validOrders = createValidOrders(LocalDate.of(2023, 11, 1));
        LocalDate date = LocalDate.of(2023, 12, 1);

        Boolean isBeforeDate = validOrders.notBefore(date);

        assertThat(isBeforeDate).isFalse();
    }

    static Stream<LocalDate> createOrderDate() {
        return Stream.of(
                LocalDate.of(2023, 12, 11),
                LocalDate.of(2023, 12, 1),
                LocalDate.of(2023, 12, 31)
        );
    }

    @DisplayName("주문일이 주어진 날짜 이후거나 같으면 참을 반환한다.")
    @ParameterizedTest
    @MethodSource("createOrderDate")
    void orderDateNotBeforeDateReturnTrue(LocalDate orderDate) {
        Orders validOrders = createValidOrders(orderDate);
        LocalDate date = LocalDate.of(2023, 12, 1);

        Boolean isBeforeDate = validOrders.notBefore(date);

        assertThat(isBeforeDate).isTrue();
    }

    @DisplayName("주문일이 주어진 날짜 이후면 거짓을 반환한다.")
    @Test
    void orderDateAfterDateReturnFalse() {
        Orders validOrders = createValidOrders(LocalDate.of(2024, 1, 1));
        LocalDate date = LocalDate.of(2023, 12, 31);

        Boolean isBeforeDate = validOrders.notAfter(date);

        assertThat(isBeforeDate).isFalse();
    }

    @DisplayName("주문일이 주어진 날짜 이전이나 같으면 참을 반환한다.")
    @ParameterizedTest
    @MethodSource("createOrderDate")
    void orderDateNotAfterDateReturnTrue(LocalDate orderDate) {
        Orders validOrders = createValidOrders(orderDate);
        LocalDate date = LocalDate.of(2023, 12, 31);

        Boolean isBeforeDate = validOrders.notAfter(date);

        assertThat(isBeforeDate).isTrue();
    }
}