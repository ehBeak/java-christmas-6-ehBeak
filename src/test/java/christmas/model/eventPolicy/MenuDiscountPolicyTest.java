package christmas.model.eventPolicy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import christmas.model.Orders;
import christmas.model.menu.Menu;
import christmas.model.menu.MenuCategory;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MenuDiscountPolicyTest {

    static Stream<Arguments> creatMenuCategoryAndBenefitPrice() {
        return Stream.of(
                arguments(MenuCategory.APPETIZER, 2023),
                arguments(MenuCategory.MAIN, 4046),
                arguments(MenuCategory.DESSERT, 6069),
                arguments(MenuCategory.BEVERAGE, 0)
        );
    }

    @DisplayName("주문을 제공하면, 지정된 메뉴에 따라 메뉴 별로 2023원씩 혜택 금액을 계산한다")
    @ParameterizedTest
    @MethodSource("creatMenuCategoryAndBenefitPrice")
    void calculateIncrementBenefitPrice(MenuCategory menuCategory, Integer expectedPrice) {
        Orders orders = createOrder(LocalDate.of(2023, 12, 1));
        MenuDiscountPolicy menuDiscountPolicy = new MenuDiscountPolicy(menuCategory);

        Integer BenefitPrice = menuDiscountPolicy.calculateBenefitPrice(orders);

        assertThat(BenefitPrice).isEqualTo(expectedPrice);
    }

    Orders createOrder(LocalDate orderDate) {
        EnumMap<Menu, Integer> menuCounts = new EnumMap<>(Menu.class);
        menuCounts.put(Menu.BUTTON_MUSHROOM_SOUP, 1);
        menuCounts.put(Menu.BARBECUE_PASTA, 2);
        menuCounts.put(Menu.CHOCOLATE_CAKE, 3);

        return new Orders(menuCounts, orderDate);
    }

}