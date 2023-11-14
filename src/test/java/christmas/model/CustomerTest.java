package christmas.model;

import static christmas.model.menu.Menu.BUTTON_MUSHROOM_SOUP;
import static christmas.model.menu.Menu.RED_WINE;
import static christmas.model.menu.Menu.T_BONE_STEAK;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.menu.Menu;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CustomerTest {

    @DisplayName("사용자의 전체 혜택 금액을 반환한다.")
    @Test
    void getTotalBenefitPrice() {
        LocalDate orderDate = LocalDate.of(2023, 12, 3);
        Orders validOrders = createValidOrders(orderDate);
        Customer customer = new Customer(validOrders);

        Integer totalDiscountPrice = customer.getBenefitPrice();

        assertThat(totalDiscountPrice).isEqualTo(-27200);
    }

    @DisplayName("사용자의 전체 할인 금액을 반환한다.")
    @Test
    void getTotalDiscountPrice() {
        LocalDate orderDate = LocalDate.of(2023, 12, 3);
        Orders validOrders = createValidOrders(orderDate);
        Customer customer = new Customer(validOrders);

        Integer totalDiscountPrice = customer.getExpectedPayment();

        assertThat(totalDiscountPrice).isEqualTo(-2200);
    }

    @DisplayName("혜택을 받는 이벤트 명과 할인 금액을 반환한다.")
    @Test
    void getBenefitsDetails() {
        LocalDate orderDate = LocalDate.of(2023, 12, 3);
        Orders validOrders = createValidOrders(orderDate);
        Customer customer = new Customer(validOrders);

        Map<String, Integer> benefitDetails = customer.getBenefitDetails();

        assertThat(benefitDetails).containsOnly(
                Map.entry("크리스마스 디데이 할인", -1200),
                Map.entry("증정 이벤트", -25000),
                Map.entry("특별 할인", -1000));
    }

    @DisplayName("주문 금액이 10000원 이하면 할인 혜택을 받을 수 없다.")
    @Test
    void orderTotalPriceUnderTenThousandNoBenefits() {
        LocalDate orderDate = LocalDate.of(2023, 12, 3);
        Orders validOrders = createOrderTotalPriceUnderTenThousand(orderDate);
        Customer customer = new Customer(validOrders);

        Map<String, Integer> benefitDetails = customer.getBenefitDetails();

        assertThat(benefitDetails).isEmpty();
    }

    Orders createValidOrders(LocalDate orderDate) {
        Map<Menu, Integer> orderMenus = new EnumMap<>(Menu.class);
        orderMenus.put(BUTTON_MUSHROOM_SOUP, 2);
        orderMenus.put(RED_WINE, 1);
        orderMenus.put(T_BONE_STEAK, 1);
        return new Orders(orderMenus, orderDate);
    }

    Orders createOrderTotalPriceUnderTenThousand(LocalDate orderDate) {
        Map<Menu, Integer> orderMenus = new EnumMap<>(Menu.class);
        orderMenus.put(BUTTON_MUSHROOM_SOUP, 1);
        return new Orders(orderMenus, orderDate);
    }
}