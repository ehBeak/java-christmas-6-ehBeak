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
    private static final String EXPECTED_PAYMENT_MESSAGE = "<할인 후 예상 결제 금액>";
    private static final String FREEBIES_MESSAGE = "<증정 메뉴>";

    private static final String PRICE_FORMAT = "%,d원";
    private static final String MENU_PRICE_FORMAT = "%s: %,d원";
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

    public void printFreebiesMenu(Map<String, Integer> freebies) {
        System.out.println();
        System.out.println(FREEBIES_MESSAGE);
        if (freebies.isEmpty()) {
            System.out.println("없음");
            return;
        }
        for (String benefitsDetail : freebies.keySet()) {
            System.out.println(String.format(ORDER_MENU_FORMAT, benefitsDetail, freebies.get(benefitsDetail)));
        }
    }

    public void printBenefits(Map<String, Integer> benefitsDetails) {
        System.out.println();
        System.out.println(BENEFITS_MESSAGE);
        if (benefitsDetails.isEmpty()) {
            System.out.println("없음");
            return;
        }
        for (String benefitsDetail : benefitsDetails.keySet()) {
            System.out.println(String.format(MENU_PRICE_FORMAT, benefitsDetail, benefitsDetails.get(benefitsDetail)));
        }
    }

    public void printTotalBenefitPrice(int totalBenefitPrice) {
        System.out.println();
        System.out.println(BENEFITS_PRICE_MESSAGE);
        System.out.println(String.format(PRICE_FORMAT, totalBenefitPrice));
    }

    public void printExpectedPayment(int totalDiscountPrice) {
        System.out.println();
        System.out.println(EXPECTED_PAYMENT_MESSAGE);
        System.out.println(String.format(PRICE_FORMAT, totalDiscountPrice));
    }
}
