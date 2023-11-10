package christmas.constant;

public enum ErrorMessage {

    ONLY_DATE_ALLOWED("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    EMPTY_NOT_ALLOWED("빈 문자열을 입력할 수 없습니다. 다시 입력해 주세요."),
    BLANK_NOT_ALLOWED("공백을 입력할 수 없습니다. 다시 입력해 주세요."),
    ONLY_NUMBER_ALLOWED("숫자만 입력할 수 있습니다. 다시 입력해주세요."),
    INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ORDER_OVER_20_NOT_ALLOWED("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다."),
    ORDER_UNDER_ZERO_NOT_ALLOWED("메뉴는 0개 주문할 수 없습니다."),
    ONLY_BEVERAGE_NOT_ALLOWED("음료만 주문할 수 없습니다."),

    DELIMITER_BOTH_ENDS_NOT_ALLOWED("구분자는 문자열의 맨 앞 뒤에 올 수 없습니다. 다시 입력해 주세요."),
    CONSECUTIVE_DELIMITER_NOT_ALLOWED("구분자가 연속될 수 없습니다. 다시 입력해 주세요."),
    WRONG_ORDER_FORMAT("메뉴이름-메뉴개수의 형식으로 입력해야합니다. 다시 입력해 주세요.");

    ErrorMessage(String message) {
        this.message = message;
    }

    private final String message;

    @Override
    public String toString() {
        return "[ERROR] " + message;
    }
}
