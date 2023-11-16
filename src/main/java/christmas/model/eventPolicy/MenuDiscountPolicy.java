package christmas.model.eventPolicy;

import christmas.model.Orders;
import christmas.model.menu.MenuCategory;

public class MenuDiscountPolicy implements DiscountPolicy {

    private static final Integer DISCOUNT_PRICE_PER_MENU = 2023;

    private final MenuCategory menuCategory;

    public MenuDiscountPolicy(MenuCategory menuCategory) {
        this.menuCategory = menuCategory;
    }

    @Override
    public Integer calculateBenefitPrice(Orders orders) {
        return orders.countMenuOnMenuCategory(menuCategory) * DISCOUNT_PRICE_PER_MENU;
    }

    @Override
    public Integer calculateDiscountPrice(Orders orders) {
        return calculateBenefitPrice(orders);
    }

}
