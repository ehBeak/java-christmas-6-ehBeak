package christmas.validation;

import static christmas.constant.ErrorMessage.BLANK_NOT_ALLOWED;
import static christmas.constant.ErrorMessage.EMPTY_NOT_ALLOWED;
import static christmas.constant.ErrorMessage.ONLY_DATE_ALLOWED;
import static christmas.constant.ErrorMessage.ONLY_NUMBER_ALLOWED;

public class DateValidator {

    public void validateVisitDate(String visitDate) {
        validateEmpty(visitDate);
        validateBlank(visitDate);
        validateNumber(visitDate);
        validateDateRange(visitDate);
    }

    private void validateEmpty(String visitDate) {
        if (visitDate.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_NOT_ALLOWED.toString());
        }
    }

    private void validateBlank(String visitDate) {
        if (visitDate.isBlank()) {
            throw new IllegalArgumentException(BLANK_NOT_ALLOWED.toString());
        }
    }

    private void validateNumber(String visitDate) {
        try {
            Integer.parseInt(visitDate);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ONLY_NUMBER_ALLOWED.toString());
        }

    }

    private void validateDateRange(String visitDate) {
        int visitDateNumber = Integer.parseInt(visitDate);
        if (visitDateNumber < 1 || visitDateNumber > 31) {
            throw new IllegalArgumentException(ONLY_DATE_ALLOWED.toString());
        }
    }
}
