package christmas.model.menu;

public enum Beverage {

    ZERO_COLA("제로콜라", 3000),
    RED_WINE("레드와인", 60000),
    CHAMPAGNE("샴페인", 25000);

    private final String name;
    private final Integer price;

    Beverage(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

}
