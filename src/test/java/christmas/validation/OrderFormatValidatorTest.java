package christmas.validation;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderFormatValidatorTest {

    private OrderFormatValidator orderFormatValidator;

    @BeforeEach
    void initOrderFormatValidator() {
        this.orderFormatValidator = new OrderFormatValidator();
    }

    @DisplayName("구분자가 맨 앞이나 뒤에 존재하면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크-1,바비큐립-1,초코케이크-2,", ",티본스테이크-1,바비큐립-1,초코케이크-2,", ",티본스테이크-1,바비큐립-1,초코케이크-2"})
    void bothEndsDelimiterStringThrowException(String input) {
        assertThatThrownBy(() -> orderFormatValidator.validateDelimiterFormat(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구분자는 문자열의 맨 앞 뒤에 올 수 없습니다. 다시 입력해 주세요.");
    }

    @DisplayName("연속된 구분자가 존재하면 예외를 발생시킨다.")
    @Test
    void consecutiveDelimiterThrowException() {
        assertThatThrownBy(() -> orderFormatValidator.validateDelimiterFormat("티본스테이크-1,바비큐립-1,,초코케이크-2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구분자가 연속될 수 없습니다. 다시 입력해 주세요.");
    }

    @DisplayName("문자열-숫자 형식의 메뉴 구성이 아니면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크1", "티본스테이크,1", "티본스테이크:1", "티본스테이크:1,바비큐립:1,초코케이크:2"})
    void wrongOrderFormatThrowException(String input) {
        assertThatThrownBy(() -> orderFormatValidator.validateDelimiterFormat(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 메뉴이름-메뉴개수의 형식으로 입력해야합니다. 다시 입력해 주세요.");
    }

    @DisplayName("문자열-숫자 형식의 메뉴 구성이면 예외를 발생시키지 않는다.")
    @Test
    void collectOrderFormatDoseNotThrowException() {
        assertThatNoException()
                .isThrownBy(() -> orderFormatValidator.validateDelimiterFormat("티본스테이크-1"));
    }

}