package christmas.model.menu;

public enum Appetizer {

    BUTTON_MUSHROOM_SOUP("양송이수프", 6000),
    TAPAS("타파스", 5500),
    CAESAR_SALAD("시저샐러드", 8000);

    private final String name;
    private final Integer price;

    Appetizer(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

}
