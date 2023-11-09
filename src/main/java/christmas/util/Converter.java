package christmas.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Converter {

    private static final String ORDERS_DELIMITER = ",";
    private static final String ORDER_DELIMITER = "-";

    public static Map<String, Integer> parseOrderDetails(String orderDetails) {
        return Arrays.stream(orderDetails.split(ORDERS_DELIMITER))
                .map(item -> item.split(ORDER_DELIMITER))
                .collect(Collectors.toMap(
                        arr -> arr[0],
                        arr -> Integer.parseInt(arr[1])));
    }

    public static LocalDate parseToDate(String visitDate) {
        return LocalDate.of(2023, 12, Integer.parseInt(visitDate));
    }
}
