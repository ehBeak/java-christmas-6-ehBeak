package christmas.model.eventPolicy;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.Orders;
import christmas.model.menu.Menu;
import java.time.LocalDate;
import java.util.EnumMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FixDiscountPolicyTest {

    @DisplayName("주문내역을 제공하면 주어진 금액에 맞게 고정으로 할인가를 제공한다")
    @ParameterizedTest
    @ValueSource(ints = {10000, 1000, 12000})
    void calculateFixBenefitPrice(Integer fixDiscountPrice) {
        Orders orders = createOrder(LocalDate.of(2023, 12, 1));
        FixDiscountPolicy fixDiscountPolicy = new FixDiscountPolicy(fixDiscountPrice);

        Integer BenefitPrice = fixDiscountPolicy.calculateBenefitPrice(orders);

        assertThat(BenefitPrice).isEqualTo(fixDiscountPrice);
    }

    Orders createOrder(LocalDate orderDate) {
        EnumMap<Menu, Integer> menuCounts = new EnumMap<>(Menu.class);
        menuCounts.put(Menu.BUTTON_MUSHROOM_SOUP, 1);
        menuCounts.put(Menu.BARBECUE_PASTA, 3);
        menuCounts.put(Menu.CHOCOLATE_CAKE, 3);

        return new Orders(menuCounts, orderDate);
    }
}