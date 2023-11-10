package christmas.model.menu;

import static christmas.model.menu.Menu.BARBECUE_PASTA;
import static christmas.model.menu.Menu.BUTTON_MUSHROOM_SOUP;
import static christmas.model.menu.Menu.CAESAR_SALAD;
import static christmas.model.menu.Menu.CHAMPAGNE;
import static christmas.model.menu.Menu.CHOCOLATE_CAKE;
import static christmas.model.menu.Menu.CHRISTMAS_PASTA;
import static christmas.model.menu.Menu.ICE_CREAM;
import static christmas.model.menu.Menu.RED_WINE;
import static christmas.model.menu.Menu.SEAFOOD_PASTA;
import static christmas.model.menu.Menu.TAPAS;
import static christmas.model.menu.Menu.T_BONE_STEAK;
import static christmas.model.menu.Menu.ZERO_COLA;

import java.util.List;

public enum MenuCategory {

    APPETIZER("appetizer", List.of(BUTTON_MUSHROOM_SOUP, TAPAS, CAESAR_SALAD)),
    MAIN("main", List.of(T_BONE_STEAK, BARBECUE_PASTA, SEAFOOD_PASTA, CHRISTMAS_PASTA)),
    DESSERT("dessert", List.of(CHOCOLATE_CAKE, ICE_CREAM)),
    BEVERAGE("beverage", List.of(ZERO_COLA, RED_WINE, CHAMPAGNE));

    private final String category;
    private final List<Menu> menus;

    MenuCategory(String category, List<Menu> menus) {
        this.category = category;
        this.menus = menus;
    }

    public static Boolean isBeverageCategory(Menu orderMenu) {
        return MenuCategory.BEVERAGE.menus.stream()
                .anyMatch(menu -> menu.equals(orderMenu));
    }

    public static Boolean isDessertCategory(Menu orderMenu) {
        return MenuCategory.DESSERT.menus.stream()
                .anyMatch(menu -> menu.equals(orderMenu));
    }

}
