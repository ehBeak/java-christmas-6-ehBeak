package christmas.model;

import static christmas.model.DayOfWeekCategory.WEEKEND_DAYS;
import static christmas.model.DayOfWeekCategory.WEEK_DAYS;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.time.DayOfWeek;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DayOfWeekCategoryTest {

    static Stream<Arguments> createDayOfWeekAndCategory() {
        return Stream.of(
                arguments(SUNDAY, WEEK_DAYS, true),
                arguments(SUNDAY, WEEKEND_DAYS, false),
                arguments(FRIDAY, WEEKEND_DAYS, true),
                arguments(FRIDAY, WEEK_DAYS, false),
                arguments(SATURDAY, WEEKEND_DAYS, true)
        );
    }
    @DisplayName("요일을 주면 해당 요일이 주말인지 평일인지 반환한다")
    @ParameterizedTest
    @MethodSource("createDayOfWeekAndCategory")
    void isDayOfWeekInCategory(DayOfWeek dayOfWeek, DayOfWeekCategory category, Boolean expected) {
        Boolean isDayOfWeekInCategory = DayOfWeekCategory.isDayOfWeekInCategory(dayOfWeek, category);

        assertThat(isDayOfWeekInCategory).isEqualTo(expected);
    }
}