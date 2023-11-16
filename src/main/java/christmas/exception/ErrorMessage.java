package christmas.exception;

public enum ErrorMessage {

    ONLY_DATE_ALLOWED("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ORDER_OVER_20_NOT_ALLOWED("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다."),
    ORDER_UNDER_ZERO_NOT_ALLOWED("메뉴는 0개 주문할 수 없습니다."),
    ONLY_BEVERAGE_NOT_ALLOWED("음료만 주문할 수 없습니다."),
    ERROR_MESSAGE("[ERROR]");

    ErrorMessage(String message) {
        this.message = message;
    }

    private final String message;

    @Override
    public String toString() {
        return String.join(" ", ERROR_MESSAGE.message, message);
    }
}
