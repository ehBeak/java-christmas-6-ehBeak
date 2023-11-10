package christmas.model.policy;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.Orders;
import christmas.model.menu.Menu;
import java.time.LocalDate;
import java.util.EnumMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SpecialDiscountPolicyTest {

    private SpecialDiscountPolicy specialDiscountPolicy;

    @BeforeEach
    void initWeekDayDiscountPolicy() {
        this.specialDiscountPolicy = new SpecialDiscountPolicy();
    }

    @DisplayName("주문 날짜가 이벤트 기간이 아니고 일요일이나 크리스마스가 아니면 0원을 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"11,30", "12,9", "12,11"})
    void orderDateNotWeekDayReturnZero(int month, int day) {
        Orders orders = createOrder(LocalDate.of(2023, month, day));

        Integer discountPrice = specialDiscountPolicy.calculateDiscountPrice(orders);

        assertThat(discountPrice).isZero();
    }

    @DisplayName("주문 날짜가 이벤트 기간 내의 일요일이고 크리스마스면 1000원을 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"12,10", "12,17", "12,25"})
    void orderDateInEventPeriodReturnDiscountPrice(int month, int day) {
        Orders orders = createOrder(LocalDate.of(2023, month, day));

        Integer discountPrice = specialDiscountPolicy.calculateDiscountPrice(orders);

        assertThat(discountPrice).isEqualTo(1000);
    }

    Orders createOrder(LocalDate orderDate) {
        EnumMap<Menu, Integer> menuCounts = new EnumMap<>(Menu.class);
        menuCounts.put(Menu.BUTTON_MUSHROOM_SOUP, 1);
        menuCounts.put(Menu.BARBECUE_PASTA, 3);
        menuCounts.put(Menu.CHOCOLATE_CAKE, 3);

        return new Orders(menuCounts, orderDate);
    }

}