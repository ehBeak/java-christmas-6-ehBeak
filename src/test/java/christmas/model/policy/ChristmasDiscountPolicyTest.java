package christmas.model.policy;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.Orders;
import christmas.model.menu.Menu;
import java.time.LocalDate;
import java.util.EnumMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ChristmasDiscountPolicyTest {

    private ChristmasDiscountPolicy christmasDiscountPolicy;

    @BeforeEach
    void initChristmasDiscountPolicy() {
        this.christmasDiscountPolicy = ChristmasDiscountPolicy.getInstance();
    }

    @DisplayName("주문 일이 크리스마스 이벤트 기간 내에 없다면 0원을 반환한다.")
    @Test
    void orderDateNotInChristmasPeriodReturnZero() {
        Orders orders = createOrders(LocalDate.of(2023, 12, 26));

        Integer discountPrice = christmasDiscountPolicy.calculateDiscountPrice(orders);

        assertThat(discountPrice).isZero();
    }

    @DisplayName("주문일이 이벤트 기간 이내라면 1000원 + (12월 1일과의 날짜차이 * 100) 을 반환한다.")
    @ParameterizedTest
    @CsvSource(textBlock =
            """
            1,1000
            25,3400
            12,2100
            """)
    void orderDateInEventPeriodReturnDiscountPrice(int dayOfMonth, Integer expectedDiscountPrice) {
        Orders orders = createOrders(LocalDate.of(2023, 12, dayOfMonth));

        Integer discountPrice = christmasDiscountPolicy.calculateDiscountPrice(orders);

        assertThat(discountPrice).isEqualTo(expectedDiscountPrice);
    }

    Orders createOrders(LocalDate orderDate) {
        EnumMap<Menu, Integer> menuCounts = new EnumMap<>(Menu.class);
        menuCounts.put(Menu.BUTTON_MUSHROOM_SOUP, 1);
        menuCounts.put(Menu.BARBECUE_PASTA, 3);

        return new Orders(menuCounts, orderDate);
    }

}