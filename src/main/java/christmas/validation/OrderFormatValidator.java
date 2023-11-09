package christmas.validation;

import static christmas.constant.ErrorMessage.CONSECUTIVE_DELIMITER_NOT_ALLOWED;
import static christmas.constant.ErrorMessage.DELIMITER_BOTH_ENDS_NOT_ALLOWED;
import static christmas.constant.ErrorMessage.WRONG_ORDER_FORMAT;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderFormatValidator {

    private static final String DELIMITER = ",";
    private static final String ORDER_FORMAT = "^[가-힣]+-[0-9]+$";

    public void validateDelimiterFormat(String orders) {
        validateBothEndsDelimiter(orders);
        validateConsecutiveDelimiter(orders);
        Arrays.stream(orders.split(DELIMITER))
                .forEach(this::validateOrderFormat);
    }

    private void validateBothEndsDelimiter(String orders) {
        if (orders.startsWith(DELIMITER) || orders.endsWith(DELIMITER)) {
            throw new IllegalArgumentException(DELIMITER_BOTH_ENDS_NOT_ALLOWED.toString());
        }
    }

    private void validateConsecutiveDelimiter(String orders) {
        if (orders.contains(DELIMITER + DELIMITER)) {
            throw new IllegalArgumentException(CONSECUTIVE_DELIMITER_NOT_ALLOWED.toString());
        }

    }

    private void validateOrderFormat(String order) {
        Pattern pattern = Pattern.compile(ORDER_FORMAT);
        Matcher matcher = pattern.matcher(order);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(WRONG_ORDER_FORMAT.toString());
        }
    }

}