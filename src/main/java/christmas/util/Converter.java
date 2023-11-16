package christmas.util;

import static christmas.exception.ErrorMessage.INVALID_ORDER;

import christmas.exception.ExceptionWithMessage;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Converter {

    private static final String ORDERS_DELIMITER = ",";
    private static final String ORDER_DELIMITER = "-";

    public static Map<String, Integer> parseOrderDetails(String orderDetails) {
        List<String> orders = Arrays.stream(orderDetails.split(ORDERS_DELIMITER)).toList();
        return convertOrdersToOrderCount(orders);
    }

    private static Map<String, Integer> convertOrdersToOrderCount(List<String> orders) {
        try {
            return orders.stream()
                    .map(item -> item.split(ORDER_DELIMITER))
                    .collect(Collectors.toMap(arr -> arr[0], arr -> Integer.parseInt(arr[1])));
        } catch (IllegalStateException exception) {
            throw new ExceptionWithMessage(INVALID_ORDER.toString());
        }
    }

    public static LocalDate parseToDate(String visitDate) {
        return LocalDate.of(2023, 12, Integer.parseInt(visitDate));
    }
}
