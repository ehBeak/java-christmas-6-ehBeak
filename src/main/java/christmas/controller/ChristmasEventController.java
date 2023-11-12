package christmas.controller;

import christmas.factory.OrderFactory;
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
    private final OrderFactory orderFactory;

    public ChristmasEventController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.orderFactory = new OrderFactory();
    }

    public void startOrder() {
        LocalDate orderDate = Retry.retryOnException(inputView::inputExpectedVisitDate);
        Map<String, Integer> orderDetails = Retry.retryOnException(inputView::inputOrderDetails);
        Orders orders = orderFactory.createOrders(orderDetails, orderDate);

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
