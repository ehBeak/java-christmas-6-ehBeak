package christmas.model;

import christmas.exception.ExceptionWithMessage;
import christmas.model.menu.Menu;
import christmas.model.policy.ChristmasDiscountPolicy;
import christmas.model.policy.DiscountPolicy;
import christmas.model.policy.FreebiesEvent;
import christmas.model.policy.FreebiesEventPolicy;
import christmas.model.policy.SpecialDiscountPolicy;
import christmas.model.policy.WeekdayDiscountPolicy;
import christmas.model.policy.WeekendDiscountPolicy;
import java.util.Map;

public enum EventPolicyCategory {

    CHRISTMAS_EVENT("크리스마스 디데이 할인", ChristmasDiscountPolicy.getInstance()),
    WEEKDAY_EVENT("평일 할인", WeekdayDiscountPolicy.getInstance()),
    WEEKEND_EVENT("주말 할인", WeekendDiscountPolicy.getInstance()),
    SPECIAL_EVENT("특별 할인", SpecialDiscountPolicy.getInstance()),
    FREEBIES_EVENT("증정 이벤트", FreebiesEventPolicy.getInstance());

    private final String eventName;
    private final DiscountPolicy discountPolicy;

    EventPolicyCategory(String eventName, DiscountPolicy eventPolicy) {
        this.eventName = eventName;
        this.discountPolicy = eventPolicy;
    }

    public Integer calculateDiscountPrice(Orders orders) {
        return discountPolicy.calculateDiscountPrice(orders) * -1;
    }

    public String getEventName() {
        return eventName;
    }

    public Map<Menu, Integer> getFreebies(Orders orders) {
        FreebiesEvent freebiesPolicy = getFreebiesPolicy();
        return freebiesPolicy.getFreebies(orders);
    }

    private FreebiesEvent getFreebiesPolicy() {
        try {
            return (FreebiesEvent) discountPolicy;
        } catch (ClassCastException exception) {
            throw new ExceptionWithMessage("[ERROR] 증정 할인 이벤트가 아닙니다.");
        }
    }
}
