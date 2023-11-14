package christmas.validation;

import static christmas.exception.ErrorMessage.ONLY_DATE_ALLOWED;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.util.validation.DateValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DateValidatorTest {

    private DateValidator dateValidator;

    @BeforeEach
    void initDateValidator() {
        this.dateValidator = new DateValidator();
    }

    @DisplayName("빈 문자열이면 예외가 발생한다.")
    @Test
    void IsEmptyThrowException() {
        assertThatThrownBy(() -> dateValidator.validateVisitDate(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ONLY_DATE_ALLOWED.toString());
    }

    @DisplayName("공백으로만 구성된 문자열이면 예외가 발생한다.")
    @Test
    void isBlankThrowException() {
        assertThatThrownBy(() -> dateValidator.validateVisitDate("  "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ONLY_DATE_ALLOWED.toString());
    }

    @DisplayName("숫자가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"12일", "삼일", "십이"})
    void IsNotNumberThrowException(String input) {
        assertThatThrownBy(() -> dateValidator.validateVisitDate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ONLY_DATE_ALLOWED.toString());
    }

    @DisplayName("1과 31사이의 숫자가 아니라면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "32", "-1"})
    void isNotInRangeThrowException(String input) {
        assertThatThrownBy(() -> dateValidator.validateVisitDate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ONLY_DATE_ALLOWED.toString());
    }

}