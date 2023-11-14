package christmas.model;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public enum DayOfWeekCategory {



    WEEK_DAYS(List.of(SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY)),
    WEEKEND_DAYS(List.of(FRIDAY, SATURDAY));
//    WEEKEND_DAYS(List.of(FRIDAY, SATURDAY), (date -> date.getDayOfWeek().equals(SUNDAY) || date.equals(LocalDate.of(2023, 12, 25))));

    private final List<DayOfWeek> dayOfWeeks; // 이걸 차라리, 일자로 묶으면 어때?
//    private List<LocalDate> localDates;
//    private final Predicate<LocalDate> isEventDay;

    private static final LocalDate CHRISTMAS_DAY = LocalDate.of(2023, 12, 25);

    DayOfWeekCategory(List<DayOfWeek> dayOfWeeks) {
        this.dayOfWeeks = dayOfWeeks;
//        this.isEventDay = isEventDay;
    }

    public static Boolean isDayOfWeekInCategory(DayOfWeek day, DayOfWeekCategory category) {
        return category.dayOfWeeks.stream()
                .anyMatch(dayOfWeek -> dayOfWeek.equals(day));
    }


}
