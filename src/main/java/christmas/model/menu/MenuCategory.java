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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum MenuCategory {

    APPETIZER(List.of(BUTTON_MUSHROOM_SOUP, TAPAS, CAESAR_SALAD)),
    MAIN(List.of(T_BONE_STEAK, BARBECUE_PASTA, SEAFOOD_PASTA, CHRISTMAS_PASTA)),
    DESSERT(List.of(CHOCOLATE_CAKE, ICE_CREAM)),
    BEVERAGE(List.of(ZERO_COLA, RED_WINE, CHAMPAGNE)),
    NONE(Collections.EMPTY_LIST);

    private final List<Menu> menus;

    MenuCategory(List<Menu> menus) {
        this.menus = menus;
    }

    public static MenuCategory findByMenu(Menu menu) {
        return Arrays.stream(MenuCategory.values())
                .filter(menus -> menus.hasMenuCategory(menu))
                .findAny()
                .orElse(NONE);
    }

    public boolean hasMenuCategory(Menu menuType) {
        return menus.stream()
                .anyMatch(menu -> menu == menuType);
    }
}
