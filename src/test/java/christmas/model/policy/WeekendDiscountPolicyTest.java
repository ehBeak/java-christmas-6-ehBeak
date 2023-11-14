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

class WeekendDiscountPolicyTest {

//    private WeekendDiscountPolicy weekendDiscountPolicy;
//
//    @BeforeEach
//    void initWeekDayDiscountPolicy() {
//        this.weekendDiscountPolicy = null;
//    }
//
//    @DisplayName("주문 날짜가 주말이 아니거나 이벤트 기간이 아니면 0원을 반환한다")
//    @ParameterizedTest
//    @CsvSource(value = {"11,30", "12,7"})
//    void orderDateNotWeekDayReturnZero(int month, int day) {
//        Orders orders = createOrdersIncludeMain(LocalDate.of(2023, month, day));
//
//        Integer discountPrice = weekendDiscountPolicy.calculateBenefitPrice(orders);
//
//        assertThat(discountPrice).isZero();
//    }
//
//    @DisplayName("주문 날짜가 주말이고 이벤트 기간이면 각 디저트에 대한 총 할인 금액을 반환한다")
//    @Test
//    void orderDateInEventPeriodReturnDiscountPrice() {
//        Orders orders = createOrdersIncludeMain(LocalDate.of(2023, 12, 8));
//
//        Integer discountPrice = weekendDiscountPolicy.calculateBenefitPrice(orders);
//
//        assertThat(discountPrice).isEqualTo(2 * 2023);
//    }
//
//    @DisplayName("주문 날짜가 평일이고 이벤트 기간이지만 디저트가 없으면 0원을 반환한다")
//    @Test
//    void orderDateInEventPeriodNotDessertReturnZero() {
//        Orders orders = createOrdersNotIncludeMain(LocalDate.of(2023, 12, 5));
//
//        Integer discountPrice = weekendDiscountPolicy.calculateBenefitPrice(orders);
//
//        assertThat(discountPrice).isZero();
//
//    }
//
//    Orders createOrdersIncludeMain(LocalDate orderDate) {
//        EnumMap<Menu, Integer> menuCounts = new EnumMap<>(Menu.class);
//        menuCounts.put(Menu.BUTTON_MUSHROOM_SOUP, 1);
//        menuCounts.put(Menu.BARBECUE_PASTA, 2);
//        menuCounts.put(Menu.CHOCOLATE_CAKE, 3);
//
//        return new Orders(menuCounts, orderDate);
//    }
//
//    Orders createOrdersNotIncludeMain(LocalDate orderDate) {
//        EnumMap<Menu, Integer> menuCounts = new EnumMap<>(Menu.class);
//        menuCounts.put(Menu.BUTTON_MUSHROOM_SOUP, 1);
//        menuCounts.put(Menu.CHOCOLATE_CAKE, 3);
//
//        return new Orders(menuCounts, orderDate);
//    }

}