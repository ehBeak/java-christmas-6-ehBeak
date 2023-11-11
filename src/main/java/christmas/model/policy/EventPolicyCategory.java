package christmas.model.policy;

public enum EventPolicyCategory {

    CHRISTMAS_EVENT("크리스마스 디데이 할인", new ChristmasDiscountPolicy()),
    WEEKDAY_EVENT("평일 할인", new WeekdayDiscountPolicy()),
    WEEKEND_EVENT("주말 할인", new WeekendDiscountPolicy()),
    SPECIAL_EVENT("특별 할인", new SpecialDiscountPolicy()),
    FREEBIES_EVENT("증정 이벤트", new FreebiesEventPolicy()),
    ;

    private final String eventName;
    private final EventPolicy eventPolicy;

    EventPolicyCategory(String eventName, EventPolicy eventPolicy) {
        this.eventName = eventName;
        this.eventPolicy = eventPolicy;
    }

    public EventPolicy getEventPolicy() {
        return eventPolicy;
    }

    public String getEventName() {
        return eventName;
    }

}
