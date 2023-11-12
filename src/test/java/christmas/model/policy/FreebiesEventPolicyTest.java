package christmas.model.policy;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.Orders;
import christmas.model.menu.Menu;
import java.time.LocalDate;
import java.util.EnumMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FreebiesEventPolicyTest {

    private FreebiesEventPolicy freebiesEventPolicy;

    @BeforeEach
    void initFreebiesEventPolicy() {
        this.freebiesEventPolicy = FreebiesEventPolicy.getInstance();
    }

    @DisplayName("이벤트 기간에 해당되지 않는 주문이면 0원 반환")
    @Test
    void calculateDiscountPriceNotEventPeriod() {
        Orders orders = createOverThresholdPrice(LocalDate.of(2023, 11, 30));

        Integer discountPrice = freebiesEventPolicy.calculateDiscountPrice(orders);

        assertThat(discountPrice).isZero();
    }

    @DisplayName("총 주문 금액이 12만원을 넘지 않으면 0원 반환")
    @Test
    void calculateDiscountPriceNotOverThresholdPrice() {
        Orders orders = createNotOverThresholdPrice(LocalDate.of(2023, 12, 12));

        Integer discountPrice = freebiesEventPolicy.calculateDiscountPrice(orders);

        assertThat(discountPrice).isZero();
    }

    @DisplayName("이벤트 기간이고 총 주문금액이 12만원을 넘는 경우 샴페인 가격 반환")
    @Test
    void calculateDiscountPrice() {
        Orders orders = createOverThresholdPrice(LocalDate.of(2023, 12, 12));

        Integer discountPrice = freebiesEventPolicy.calculateDiscountPrice(orders);

        assertThat(discountPrice).isEqualTo(Menu.CHAMPAGNE.getPrice());
    }

    Orders createNotOverThresholdPrice(LocalDate orderDate) {
        EnumMap<Menu, Integer> menuCounts = new EnumMap<>(Menu.class);
        menuCounts.put(Menu.BUTTON_MUSHROOM_SOUP, 1);
        menuCounts.put(Menu.BARBECUE_PASTA, 1);

        return new Orders(menuCounts, orderDate);
    }

    Orders createOverThresholdPrice(LocalDate orderDate) {
        EnumMap<Menu, Integer> menuCounts = new EnumMap<>(Menu.class);
        menuCounts.put(Menu.BUTTON_MUSHROOM_SOUP, 1);
        menuCounts.put(Menu.BARBECUE_PASTA, 3);

        return new Orders(menuCounts, orderDate);
    }

}