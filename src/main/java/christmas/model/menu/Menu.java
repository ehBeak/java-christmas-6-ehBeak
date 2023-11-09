package christmas.model.menu;

import static christmas.constant.ErrorMessage.INVALID_ORDER;

import java.util.Arrays;

public enum Menu {

    BUTTON_MUSHROOM_SOUP("양송이수프", 6000),
    TAPAS("타파스", 5500),
    CAESAR_SALAD("시저샐러드", 8000),

    T_BONE_STEAK("티본스테이크", 55000),
    BARBECUE_PASTA("바베큐립", 54000),
    SEAFOOD_PASTA("해산물파스타", 35000),
    CHRISTMAS_PASTA("크리스마스파스타", 25000),

    CHOCOLATE_CAKE("초코케이크", 15000),
    ICE_CREAM("아이스크림", 5000),

    ZERO_COLA("제로콜라", 3000),
    RED_WINE("레드와인", 60000),
    CHAMPAGNE("샴페인", 25000);

    private final String name;
    private final Integer price;

    Menu(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public static Menu Of(String menuName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.name.equals(menuName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_ORDER.toString()));
    }
}