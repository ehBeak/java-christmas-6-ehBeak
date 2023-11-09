package christmas.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ConverterTest {

    @DisplayName("주문내역 문자열을 입력하면 메뉴이름을 키로 갖고 개수가 값인 맵으로 변환한다.")
    @Test
    void convertOrderDetailsToMap() {
        String orderDetails = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";

        Map<String, Integer> menuCounts = Converter.parseOrderDetails(orderDetails);

        assertThat(menuCounts)
                .containsOnly(
                        Map.entry("티본스테이크", 1),
                        Map.entry("바비큐립", 1),
                        Map.entry("초코케이크", 2),
                        Map.entry("제로콜라", 1))
                .isInstanceOf(Map.class);


    }

    @DisplayName("중복인 메뉴를 입력하면 예외가 발생한다.")
    @Test
    void convertDuplicatedOrderDetailsToMap() {
        assertThatThrownBy(() -> Converter.parseOrderDetails("티본스테이크-1,티본스테이크-2,초코케이크-2,제로콜라-1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("날짜 문자열을 입력하면 날짜 타입으로 변환한다.")
    @Test
    void convertStringToLocalDate() {
        String visitDate = "1";
        LocalDate expectedDate = LocalDate.of(2023, 12, 1);

        LocalDate localDate = Converter.parseToDate(visitDate);

        assertThat(localDate)
                .isInstanceOf(LocalDate.class)
                .isEqualTo(expectedDate);
    }
}