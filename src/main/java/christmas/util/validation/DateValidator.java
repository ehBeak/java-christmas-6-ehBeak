package christmas.util.validation;

import static christmas.exception.ErrorMessage.ONLY_DATE_ALLOWED;

import christmas.exception.ExceptionWithMessage;

public class DateValidator {

    public void validateVisitDate(String visitDate) {
        validateEmpty(visitDate);
        validateBlank(visitDate);
        validateNumber(visitDate);
        validateDateRange(visitDate);
    }

    private void validateEmpty(String visitDate) {
        if (visitDate.isEmpty()) {
            throw new ExceptionWithMessage(ONLY_DATE_ALLOWED.toString());
        }
    }

    private void validateBlank(String visitDate) {
        if (visitDate.isBlank()) {
            throw new ExceptionWithMessage(ONLY_DATE_ALLOWED.toString());
        }
    }

    private void validateNumber(String visitDate) {
        try {
            Integer.parseInt(visitDate);
        } catch (NumberFormatException exception) {
            throw new ExceptionWithMessage(ONLY_DATE_ALLOWED.toString());
        }

    }

    private void validateDateRange(String visitDate) {
        int visitDateNumber = Integer.parseInt(visitDate);
        if (visitDateNumber < 1 || visitDateNumber > 31) {
            throw new ExceptionWithMessage(ONLY_DATE_ALLOWED.toString());
        }
    }
}
