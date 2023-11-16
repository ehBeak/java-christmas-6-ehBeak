package christmas.util.validation;

import static christmas.exception.ErrorMessage.INVALID_ORDER;

import christmas.exception.ExceptionWithMessage;
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
            throw new ExceptionWithMessage(INVALID_ORDER.toString());
        }
    }

    private void validateConsecutiveDelimiter(String orders) {
        if (orders.contains(DELIMITER + DELIMITER)) {
            throw new ExceptionWithMessage(INVALID_ORDER.toString());
        }

    }

    private void validateOrderFormat(String order) {
        Pattern pattern = Pattern.compile(ORDER_FORMAT);
        Matcher matcher = pattern.matcher(order);
        if (!matcher.matches()) {
            throw new ExceptionWithMessage(INVALID_ORDER.toString());
        }
    }

}