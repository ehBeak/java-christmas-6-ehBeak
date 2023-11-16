package christmas.controller;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.view.InputView;
import christmas.view.OutputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChristmasEventControllerTest extends NsTest {

    @DisplayName("주문 내역을 제공하면 이에 맞는 주문을 반환한다")
    @Test
    void printEventResult() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                    "<주문 메뉴>\n티본스테이크 1개\n바비큐립 1개\n초코케이크 2개\n제로콜라 1개",
                    "<할인 전 총주문 금액>\n142,000원",
                    "<증정 메뉴>\n샴페인 1개",
                    "<혜택 내역>\n증정 이벤트: -25,000원\n크리스마스 디데이 할인: -1,200원\n평일 할인: -4,046원\n특별 할인: -1,000원",
                    "<총혜택 금액>\n-31,246원",
                    "<할인 후 예상 결제 금액>\n135,754원",
                    "<12월 이벤트 배지>\n산타"
            );
        });
    }

    @DisplayName("주문 금액이 10000원을 넘지 않으면 혜택 대상이 아니다")
    @Test
    void printNoBenefit() {
        assertSimpleTest(() -> {
            run("3", "아이스크림-1");
            assertThat(output()).contains(
                    "<증정 메뉴>\n없음",
                    "<혜택 내역>\n없음",
                    "<총혜택 금액>\n0원",
                    "<12월 이벤트 배지>\n없음"
            );
        });
    }

    @DisplayName("주문 금액이 10000원이면 혜택 대상이다")
    @Test
    void printBenefit() {
        assertSimpleTest(() -> {
            run("3", "아이스크림-2");
            assertThat(output()).contains(
                    "<증정 메뉴>\n없음",
                    "<혜택 내역>",
                    "크리스마스 디데이 할인: -1,200원",
                    "평일 할인: -4,046원",
                    "특별 할인: -1,000원",
                    "<12월 이벤트 배지>\n별"
            );
        });
    }

    @Override
    protected void runMain() {
        ChristmasEventController christmasEventController =
                new ChristmasEventController(new InputView(), new OutputView());
        christmasEventController.startOrder();
    }
}