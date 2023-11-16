package christmas.model;

import static christmas.exception.ErrorMessage.INVALID_ORDER;
import static christmas.exception.ErrorMessage.ONLY_BEVERAGE_NOT_ALLOWED;
import static christmas.exception.ErrorMessage.ORDER_OVER_20_NOT_ALLOWED;
import static christmas.model.menu.MenuCategory.BEVERAGE;

import christmas.exception.ExceptionWithMessage;
import christmas.model.menu.Menu;
import christmas.model.menu.MenuCategory;
import java.time.DayOfWeek;
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
        return orders.keySet().stream()
                .mapToInt(this::calculateOrderMenuPrice)
                .sum();
    }

    private Integer calculateOrderMenuPrice(Menu menu) {
        return orders.get(menu) * menu.getPrice();
    }

    public Integer countMenuOnMenuCategory(MenuCategory menuCategory) {
        return orders.keySet().stream()
                .filter(menu -> MenuCategory.findByMenu(menu) == menuCategory)
                .mapToInt(orders::get)
                .sum();
    }

    public Map<Menu, Integer> getOrders() {
        return orders;
    }

    private void validateOrders(Map<Menu, Integer> orders) {
        validateZeroCount(orders);
        validateTotalCount(orders);
        validateOnlyBeverage(orders);
    }

    private void validateTotalCount(Map<Menu, Integer> orders) {
        Integer totalCount = countTotalOrder(orders);
        if (totalCount > 20) {
            throw new ExceptionWithMessage(ORDER_OVER_20_NOT_ALLOWED.toString());
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
            throw new ExceptionWithMessage(INVALID_ORDER.toString());
        }
    }

    private void validateOnlyBeverage(Map<Menu, Integer> orders) {
        if (orders.keySet().stream().allMatch(menu -> MenuCategory.findByMenu(menu) == BEVERAGE)) {
            throw new ExceptionWithMessage(ONLY_BEVERAGE_NOT_ALLOWED.toString());
        }
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

    public DayOfWeek getDayOfWeek() {
        return orderDate.getDayOfWeek();
    }

    public Boolean isOrderDateChristmas() {
        return orderDate.isEqual(LocalDate.of(2023, 12, 25));
    }

}