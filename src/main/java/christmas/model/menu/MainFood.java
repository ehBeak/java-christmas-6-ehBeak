package christmas.model.menu;

public enum MainFood {

    T_BONE_STEAK("티본스테이크", 55000),
    BARBECUE_PASTA("바베큐립", 54000),
    SEAFOOD_PASTA("해산물파스타", 35000),
    CHRISTMAS_PASTA("크리스마스파스타", 25000);

    private final String name;
    private final Integer price;

    MainFood(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
}
