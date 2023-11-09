package christmas.constant;

public enum ErrorMessage {

    ONLY_DATE_ALLOWED("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    EMPTY_NOT_ALLOWED("빈 문자열을 입력할 수 없습니다. 다시 입력해 주세요."),
    BLANK_NOT_ALLOWED("공백을 입력할 수 없습니다. 다시 입력해 주세요."),
    ONLY_NUMBER_ALLOWED("숫자만 입력할 수 있습니다. 다시 입력해주세요.");

    ErrorMessage(String message) {
        this.message = message;
    }

    private final String message;

    @Override
    public String toString() {
        return "[ERROR] " + message;
    }
}
