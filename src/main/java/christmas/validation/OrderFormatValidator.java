package christmas.validation;

import java.util.Arrays;
import java.util.regex.Pattern;

public class OrderFormatValidator {

    private static final String DELIMITER = ",";
    private static final String ORDER_FORMAT = "%s-%d";

    public void validateDelimiterFormat(String orders) {
        validateBothEndsDelimiter(orders);
        validateConsecutiveDelimiter(orders);
        Arrays.stream(orders.split(DELIMITER))
                .forEach(this::validateOrderFormat);
    }

    private void validateBothEndsDelimiter(String orders) {
        if (orders.startsWith(DELIMITER) || orders.endsWith(DELIMITER)) {
            throw new IllegalArgumentException("");
        }
    }

    private void validateConsecutiveDelimiter(String orders) {
        if (orders.contains(DELIMITER + DELIMITER)) {
            throw new IllegalArgumentException();
        }

    }

    private void validateOrderFormat(String order) {
        if (!Pattern.matches(order, ORDER_FORMAT)) {
            throw new IllegalArgumentException();
        }
    }

}