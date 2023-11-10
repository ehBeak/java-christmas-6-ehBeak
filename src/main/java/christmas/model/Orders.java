package christmas.model;

import static christmas.constant.ErrorMessage.ONLY_BEVERAGE_NOT_ALLOWED;
import static christmas.constant.ErrorMessage.ORDER_OVER_20_NOT_ALLOWED;
import static christmas.constant.ErrorMessage.ORDER_UNDER_ZERO_NOT_ALLOWED;

import christmas.model.menu.Menu;
import christmas.model.menu.MenuCategory;
import java.time.LocalDate;
import java.time.Period;
import java.util.Map;

public class Orders {

    private final Map<Menu, Integer> orders;
    private final LocalDate orderDate;

    public Orders(Map<Menu, Integer> orders, LocalDate orderDate) {
        validateOrders(orders);
        this.orders = orders;
        this.orderDate = orderDate;
    }

    public Integer calculateTotalPrice() {
        Integer totalPrice = 0;
        for (Menu menu : orders.keySet()) {
            totalPrice += orders.get(menu) * menu.getPrice();
        }
        return totalPrice;
    }

    private void validateOrders(Map<Menu, Integer> orders) {
        validateZeroCount(orders);
        validateTotalCount(orders);
        validateOnlyBeverage(orders);
    }

    private void validateTotalCount(Map<Menu, Integer> orders) {
        Integer totalCount = countTotalOrder(orders);
        if (totalCount > 20) {
            throw new IllegalArgumentException(ORDER_OVER_20_NOT_ALLOWED.toString());
        }
    }

    private Integer countTotalOrder(Map<Menu, Integer> orders) {
        Integer totalCount = 0;
        for (Menu menu : orders.keySet()) {
            totalCount += orders.get(menu);
        }
        return totalCount;
    }

    private void validateZeroCount(Map<Menu, Integer> orders) {
        for (Menu menu : orders.keySet()) {
            countZeroThrowException(menu, orders);
        }
    }

    private void countZeroThrowException(Menu menu, Map<Menu, Integer> orders) {
        if (orders.get(menu).equals(0)) {
            throw new IllegalArgumentException(ORDER_UNDER_ZERO_NOT_ALLOWED.toString());
        }
    }

    private void validateOnlyBeverage(Map<Menu, Integer> orders) {
        for (Menu menu : orders.keySet()) {
            if (!MenuCategory.isBeverageCategory(menu)) {
                return;
            }
        }
        throw new IllegalArgumentException(ONLY_BEVERAGE_NOT_ALLOWED.toString());
    }

    public Boolean notBefore(LocalDate eventStartDate) {
        return orderDate.isAfter(eventStartDate) || orderDate.equals(eventStartDate);
    }

    public Boolean notAfter(LocalDate eventEndDate) {
        return orderDate.isBefore(eventEndDate) || orderDate.equals(eventEndDate);
    }

    public Integer getElapsedTime(LocalDate eventStartDate) {
        return Period.between(eventStartDate, orderDate).getDays();
    }

}