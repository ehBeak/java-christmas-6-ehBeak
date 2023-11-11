package christmas.view;

import christmas.model.menu.Menu;
import java.time.LocalDate;
import java.util.Map;

public class OutputView {

    private static final String PREVIEW_MESSAGE = "%s월 %s일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU_MESSAGE = "<주문메뉴>";
    private static final String ORDER_TOTAL_PRICE_MESSAGE = "<할인 전 총주문 금액>";
    private static final String BENEFITS_MESSAGE = "<혜택 내역>";
    private static final String BENEFITS_PRICE_MESSAGE = "<총혜택 금액>";
    private static final String PRICE_FORMAT = "%,d원";
    private static final String MENU_PRICE_FORMAT = "%s : %,d원";
    private static final String ORDER_MENU_FORMAT = "%s %s개";

    public void printPreviewMessage(LocalDate orderDate) {
        System.out.println(String.format(PREVIEW_MESSAGE, orderDate.getMonthValue(), orderDate.getDayOfMonth()));
    }

    public void printOrderMenu(Map<Menu, Integer> orders) {
        System.out.println();
        System.out.println(ORDER_MENU_MESSAGE);
        for (Menu menu : orders.keySet()) {
            System.out.println(String.format(ORDER_MENU_FORMAT, menu.getName(), orders.get(menu)));
        }
    }

    public void printTotalPriceBeforeDiscount(int totalPrice) {
        System.out.println();
        System.out.println(ORDER_TOTAL_PRICE_MESSAGE);
        System.out.println(String.format(PRICE_FORMAT, totalPrice));
    }

    public void printBenefits(Map<String, Integer> benefitsDetails) {
        System.out.println();
        System.out.println(BENEFITS_MESSAGE);
        for (String benefit : benefitsDetails.keySet()) {
            System.out.println(String.format(MENU_PRICE_FORMAT, benefit, benefitsDetails.get(benefit)));
        }
    }

    public void printTotalBenefitPrice(int totalBenefitPrice) {
        System.out.println();
        System.out.println(BENEFITS_PRICE_MESSAGE);
        System.out.println(String.format(PRICE_FORMAT, totalBenefitPrice));
    }
}
