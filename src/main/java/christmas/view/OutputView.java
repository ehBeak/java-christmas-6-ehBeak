package christmas.view;

import christmas.model.menu.Menu;
import java.time.LocalDate;
import java.util.Map;

public class OutputView {

    private static final String PREVIEW_MESSAGE = "%s월 %s일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU_MESSAGE = "\n<주문 메뉴>";
    private static final String ORDER_TOTAL_PRICE_MESSAGE = "\n<할인 전 총주문 금액>";
    private static final String BENEFITS_MESSAGE = "\n<혜택 내역>";
    private static final String BENEFITS_PRICE_MESSAGE = "\n<총혜택 금액>";
    private static final String EXPECTED_PAYMENT_MESSAGE = "\n<할인 후 예상 결제 금액>";
    private static final String FREEBIES_MESSAGE = "\n<증정 메뉴>";
    private static final String BADGE_MESSAGE = "\n<12월 이벤트 배지>";

    private static final String PRICE_FORMAT = "%,d원";
    private static final String DISCOUNT_PRICE_FORMAT = "%,d원";
    private static final String BENEFIT_EVENT_FORMAT = "%s: %,d원";
    private static final String ORDER_MENU_FORMAT = "%s %s개";
    private static final Integer NEGATIVE_INTEGER = -1;

    public void printPreviewMessage(LocalDate orderDate) {
        System.out.println(String.format(PREVIEW_MESSAGE, orderDate.getMonthValue(), orderDate.getDayOfMonth()));
    }

    public void printStartOrder() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printOrderMenu(Map<Menu, Integer> orders) {
        System.out.println(ORDER_MENU_MESSAGE);
        for (Menu menu : orders.keySet()) {
            System.out.println(String.format(ORDER_MENU_FORMAT, menu.getName(), orders.get(menu)));
        }
    }

    public void printTotalPriceBeforeDiscount(int totalPrice) {
        System.out.println(ORDER_TOTAL_PRICE_MESSAGE);
        System.out.println(String.format(PRICE_FORMAT, totalPrice));
    }

    public void printFreebiesMenu(Map<String, Integer> freebies) {
        System.out.println(FREEBIES_MESSAGE);
        if (isEmpty(freebies)) {
            return;
        }
        for (String benefitsDetail : freebies.keySet()) {
            System.out.println(String.format(ORDER_MENU_FORMAT, benefitsDetail, freebies.get(benefitsDetail)));
        }
    }

    public void printBenefits(Map<String, Integer> benefitsDetails) {
        System.out.println(BENEFITS_MESSAGE);
        if (isEmpty(benefitsDetails)) {
            return;
        }
        for (String benefitsDetail : benefitsDetails.keySet()) {
            System.out.println(String.format(
                    BENEFIT_EVENT_FORMAT, benefitsDetail, benefitsDetails.get(benefitsDetail) * NEGATIVE_INTEGER));
        }
    }

    public void printTotalBenefitPrice(int totalBenefitPrice) {
        System.out.println(BENEFITS_PRICE_MESSAGE);
        System.out.println(String.format(DISCOUNT_PRICE_FORMAT, totalBenefitPrice * NEGATIVE_INTEGER));
    }

    public void printExpectedPayment(int totalDiscountPrice) {
        System.out.println(EXPECTED_PAYMENT_MESSAGE);
        System.out.println(String.format(PRICE_FORMAT, totalDiscountPrice));
    }

    public void printBadgeName(String badgeName) {
        System.out.println(BADGE_MESSAGE);
        System.out.println(badgeName);
    }

    private Boolean isEmpty(Map<String, Integer> freebies) {
        if (freebies.isEmpty()) {
            System.out.println("없음");
            return true;
        }
        return false;
    }

}
