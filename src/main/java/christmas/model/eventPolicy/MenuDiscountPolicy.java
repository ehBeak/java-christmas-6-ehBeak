package christmas.model.eventPolicy;

import static christmas.model.menu.MenuCategory.DESSERT;

import christmas.model.Orders;
import christmas.model.menu.MenuCategory;

public class MenuDiscountPolicy implements EventPolicy {

    private static final Integer DISCOUNT_PRICE_PER_MENU = 2023;

    private final MenuCategory menuCategory;

    public MenuDiscountPolicy(MenuCategory menuCategory) {
        this.menuCategory = menuCategory;
    }


    @Override
    public Integer calculateBenefitPrice(Orders orders) {
        return orders.countMenuOnMenuCategory(menuCategory) * DISCOUNT_PRICE_PER_MENU;
    }
}
