package christmas.model.policy;

public enum EventPolicyCategory {

    CHRISTMAS_EVENT(new ChristmasDiscountPolicy()),
    FREEBIES_EVENT(new FreebiesEventPolicy()),
    SPECIAL_EVENT(new SpecialDiscountPolicy()),
    WEEKDAY_EVENT(new WeekdayDiscountPolicy()),
    WEEKEND_EVENT(new WeekendDiscountPolicy());

    private final EventPolicy eventPolicy;

    EventPolicyCategory(EventPolicy eventPolicy) {
        this.eventPolicy = eventPolicy;
    }

    public EventPolicy getEventPolicy() {
        return eventPolicy;
    }
}
