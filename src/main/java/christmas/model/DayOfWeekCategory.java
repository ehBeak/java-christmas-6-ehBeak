package christmas.model;

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

    public static Boolean isDayOfWeekInCategory(DayOfWeek day, DayOfWeekCategory category) {
        return category.dayOfWeeks.stream()
                .anyMatch(dayOfWeek -> dayOfWeek.equals(day));
    }
}
