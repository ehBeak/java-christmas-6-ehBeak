package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.factory.OrderFactory;
import christmas.model.Orders;
import christmas.util.Converter;
import christmas.util.validation.DateValidator;
import christmas.util.validation.OrderFormatValidator;
import java.time.LocalDate;

public class InputView {

    private final DateValidator dateValidator;
    private final OrderFormatValidator orderFormatValidator;
    private final OrderFactory orderFactory;

    public InputView() {
        this.dateValidator = new DateValidator();
        this.orderFormatValidator = new OrderFormatValidator();
        this.orderFactory = new OrderFactory();
    }

    public LocalDate inputExpectedVisitDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String visitDate = Console.readLine();
        dateValidator.validateVisitDate(visitDate);
        return Converter.parseToDate(visitDate);
    }

    public Orders inputOrderDetails(LocalDate orderDate) {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String orderDetails = Console.readLine();
        orderFormatValidator.validateDelimiterFormat(orderDetails);
        return orderFactory.createOrders(Converter.parseOrderDetails(orderDetails), orderDate);
    }

}
