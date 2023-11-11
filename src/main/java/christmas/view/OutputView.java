package christmas.view;

import christmas.model.menu.Menu;
import java.time.LocalDate;
import java.util.Map;

public class OutputView {

    private static final String PREVIEW_MESSAGE = "%s월 %s일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU_MESSAGE = "<주문메뉴>";
    private static final String ORDER_MENU_FORMAT = "%s %s개";

    public void printPreviewMessage(LocalDate orderDate) {
        System.out.println(String.format(PREVIEW_MESSAGE, orderDate.getMonthValue(), orderDate.getDayOfMonth()));
    }

    public void printOrderMenu(Map<Menu, Integer> orders) {
        System.out.println();
        System.out.println(ORDER_MENU_MESSAGE);
        for (Menu menu : orders.keySet()) {
            System.out.println(String.format(ORDER_MENU_FORMAT, menu, orders.get(menu)));
        }
    }


}
