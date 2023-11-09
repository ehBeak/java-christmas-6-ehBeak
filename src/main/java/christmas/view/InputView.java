package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.validation.DateValidator;
import java.time.LocalDate;

public class InputView {

    private final DateValidator dateValidator;

    public InputView() {
        this.dateValidator = new DateValidator();
    }

    public LocalDate inputExpectedVisitDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String visitDate = Console.readLine();
        dateValidator.validateVisitDate(visitDate);
        return LocalDate.of(2023, 12, Integer.parseInt(visitDate));
    }
}
