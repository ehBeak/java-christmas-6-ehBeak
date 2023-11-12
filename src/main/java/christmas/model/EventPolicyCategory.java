package christmas.model;

import christmas.model.policy.ChristmasDiscountPolicy;
import christmas.model.policy.EventPolicy;
import christmas.model.policy.FreebiesEventPolicy;
import christmas.model.policy.SpecialDiscountPolicy;
import christmas.model.policy.WeekdayDiscountPolicy;
import christmas.model.policy.WeekendDiscountPolicy;

public enum EventPolicyCategory {

    CHRISTMAS_EVENT("크리스마스 디데이 할인", ChristmasDiscountPolicy.getInstance()),
    WEEKDAY_EVENT("평일 할인", WeekdayDiscountPolicy.getInstance()),
    WEEKEND_EVENT("주말 할인", WeekendDiscountPolicy.getInstance()),
    SPECIAL_EVENT("특별 할인", SpecialDiscountPolicy.getInstance()),
    FREEBIES_EVENT("증정 이벤트", FreebiesEventPolicy.getInstance());

    private final String eventName;
    private final EventPolicy eventPolicy;

    EventPolicyCategory(String eventName, EventPolicy eventPolicy) {
        this.eventName = eventName;
        this.eventPolicy = eventPolicy;
    }

    public Integer calculateDiscountPrice(Orders orders) {
        return eventPolicy.calculateDiscountPrice(orders) * -1;
    }

    public String getEventName() {
        return eventName;
    }

}
