package christmas.model;

import java.util.Arrays;
import java.util.function.Predicate;

public enum Badge {

    NONE("없음", (benefitPrice) -> (benefitPrice < 5000)),
    STAR("별", (benefitPrice) -> (benefitPrice >= 5000) && (benefitPrice < 10000)),
    TREE("트리", (benefitPrice) -> (benefitPrice >= 10000) && (benefitPrice < 20000)),
    SANTA("산타", (benefitPrice) -> (benefitPrice >= 20000));

    Badge(String name, Predicate<Integer> condition) {
        this.name = name;
        this.condition = condition;
    }

    private final String name;
    private final Predicate<Integer> condition;

    public static Badge valueOf(Integer benefitPrice) {
        return Arrays.stream(Badge.values())
                .filter(badge -> badge.condition.test(benefitPrice))
                .findAny()
                .orElse(NONE);
    }

    public String getName() {
        return name;
    }
}
