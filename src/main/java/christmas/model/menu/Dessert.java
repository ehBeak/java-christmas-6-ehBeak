package christmas.model.menu;

public enum Dessert {

    CHOCOLATE_CAKE("초코케이크", 15000),
    ICE_CREAM("아이스크림", 5000);

    private final String name;
    private final Integer price;

    Dessert(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
}
