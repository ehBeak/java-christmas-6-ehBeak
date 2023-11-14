package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.util.Converter;
import christmas.util.validation.DateValidator;
import christmas.util.validation.OrderFormatValidator;
import java.time.LocalDate;
import java.util.Map;

public class InputView {

    private final DateValidator dateValidator;
    private final OrderFormatValidator orderFormatValidator;

    public InputView() {
        this.dateValidator = new DateValidator();
        this.orderFormatValidator = new OrderFormatValidator();
    }

    public LocalDate inputExpectedVisitDate() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String visitDate = Console.readLine();
        dateValidator.validateVisitDate(visitDate);
        return Converter.parseToDate(visitDate);
    }

    public Map<String, Integer> inputOrderDetails() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String orderDetails = Console.readLine();
        orderFormatValidator.validateDelimiterFormat(orderDetails);
        return Converter.parseOrderDetails(orderDetails);
    }

}
