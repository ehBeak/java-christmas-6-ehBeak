package christmas.model.policy;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;

import java.time.DayOfWeek;
import java.util.List;

public enum DayOfWeekCategory {

    WEEK_DAYS(List.of(SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY)),
    WEEKEND_DAYS(List.of(FRIDAY, SATURDAY));

    private final List<DayOfWeek> dayOfWeeks;

    DayOfWeekCategory(List<DayOfWeek> dayOfWeeks) {
        this.dayOfWeeks = dayOfWeeks;
    }

    public static Boolean isWeekDays(DayOfWeek dayOfWeek) {
        return DayOfWeekCategory.WEEK_DAYS.dayOfWeeks.stream()
                .anyMatch(day -> day.equals(dayOfWeek));
    }

    public static Boolean isWeekendDays(DayOfWeek dayOfWeek) {
        return DayOfWeekCategory.WEEKEND_DAYS.dayOfWeeks.stream()
                .anyMatch(day -> day.equals(dayOfWeek));
    }

}
