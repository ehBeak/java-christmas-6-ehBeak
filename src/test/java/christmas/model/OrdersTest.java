package christmas.model;

import static christmas.model.menu.Menu.BUTTON_MUSHROOM_SOUP;
import static christmas.model.menu.Menu.CHOCOLATE_CAKE;
import static christmas.model.menu.Menu.RED_WINE;
import static christmas.model.menu.Menu.ZERO_COLA;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.model.menu.Menu;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrdersTest {

    @DisplayName("메뉴당 구매 개수가 0개면 예외가 발생한다.")
    @Test
    void ZeroOrLessCountThrowException() {
        LocalDate orderDate = LocalDate.of(2023, 12, 11);
        Map<Menu, Integer> orderMenus = new EnumMap<>(Menu.class);
        orderMenus.put(BUTTON_MUSHROOM_SOUP, 0);

        assertThatThrownBy(() -> new Orders(orderMenus, orderDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 메뉴는 0개 주문할 수 없습니다.");
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
                .hasMessage("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");
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
                .hasMessage("[ERROR] 음료만 주문할 수 없습니다.");
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

}