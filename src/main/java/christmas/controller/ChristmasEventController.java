package christmas.controller;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Badge;
import christmas.model.Customer;
import christmas.model.Orders;
import christmas.util.Retry;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.LocalDate;
import java.util.Map;

public class ChristmasEventController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChristmasEventController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void startOrder() {
        outputView.printStartOrder();
        LocalDate orderDate = Retry.retryOnException(inputView::inputExpectedVisitDate);
        Orders orders = Retry.retryOnException(() -> inputView.inputOrderDetails(orderDate));
        Console.close();
        printOrderDetails(orderDate, orders);

        Customer customer = new Customer(orders);
        printFreebiesEvent(customer);
        printBenefitDetails(customer);
        printTotalBenefitPrice(customer);
        printDiscountPrice(customer);
        printEventBadge(customer);
    }

    private void printOrderDetails(LocalDate orderDate, Orders orders) {
        outputView.printPreviewMessage(orderDate);
        outputView.printOrderMenu(orders.getOrders());
        outputView.printTotalPriceBeforeDiscount(orders.calculateTotalPrice());
    }

    private void printEventBadge(Customer customer) {
        Badge eventBadge = customer.getEventBadge();
        outputView.printBadgeName(eventBadge.getName());
    }

    private void printDiscountPrice(Customer customer) {
        Integer discountPrice = customer.getExpectedPayment();
        outputView.printExpectedPayment(discountPrice);
    }

    private void printTotalBenefitPrice(Customer customer) {
        Integer totalBenefitPrice = customer.getBenefitPrice();
        outputView.printTotalBenefitPrice(totalBenefitPrice);
    }

    private void printBenefitDetails(Customer customer) {
        Map<String, Integer> benefitDetails = customer.getBenefitDetails();
        outputView.printBenefits(benefitDetails);
    }

    private void printFreebiesEvent(Customer customer) {
        Map<String, Integer> freebies = customer.getFreebies();
        outputView.printFreebiesMenu(freebies);
    }
}
