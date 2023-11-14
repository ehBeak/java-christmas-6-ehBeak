package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.ChristmasEventController;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        ChristmasEventController christmasEventController =
                new ChristmasEventController(new InputView(), new OutputView());
        christmasEventController.startOrder();
        Console.close();
    }
}
