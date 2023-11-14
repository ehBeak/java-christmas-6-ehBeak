package christmas.model;

import static christmas.model.DayOfWeekCategory.WEEKEND_DAYS;
import static christmas.model.DayOfWeekCategory.WEEK_DAYS;
import static christmas.model.menu.MenuCategory.*;

import christmas.exception.ExceptionWithMessage;
import christmas.model.eventPolicy.DiscountPolicy;
import christmas.model.eventPolicy.EventPolicy;
import christmas.model.eventPolicy.FreebiesPolicy;
import christmas.model.eventPolicy.MenuDiscountPolicy;
import christmas.model.eventcondition.DayOfWeekCondition;
import christmas.model.eventcondition.EventCondition;
import christmas.model.eventcondition.NoEventCondition;
import christmas.model.eventcondition.ThresholdPriceCondition;
import christmas.model.eventcondition.SpecialDayCondition;
import christmas.model.eventperiod.ChristmasPeriod;
import christmas.model.eventperiod.DecemberPeriod;
import christmas.model.eventperiod.EventPeriod;
import christmas.model.menu.Menu;
import christmas.model.eventPolicy.IncrementDiscountPolicy;
import christmas.model.eventPolicy.FreebiesEventPolicy;
import christmas.model.eventPolicy.FixDiscountPolicy;
import java.util.Map;

public enum EventCategory {

    CHRISTMAS_EVENT("크리스마스 디데이 할인", new ChristmasPeriod(), new IncrementDiscountPolicy(), new NoEventCondition()),
    WEEKDAY_EVENT("평일 할인", new DecemberPeriod(), new MenuDiscountPolicy(DESSERT), new DayOfWeekCondition(WEEK_DAYS)),
    WEEKEND_EVENT("주말 할인", new DecemberPeriod(), new MenuDiscountPolicy(MAIN), new DayOfWeekCondition(WEEKEND_DAYS)),
    SPECIAL_EVENT("특별 할인", new DecemberPeriod(), new FixDiscountPolicy(), new SpecialDayCondition()),
    FREEBIES_EVENT("증정 이벤트", new DecemberPeriod(), new FreebiesEventPolicy(Map.of(Menu.CHAMPAGNE, 1)),
            new ThresholdPriceCondition(12000));


    private final String eventName;
    private final EventPeriod eventPeriod;
    private final EventPolicy benefitPolicy;
    private final EventCondition eventCondition;

    EventCategory(String eventName, EventPeriod eventPeriod, EventPolicy benefitPolicy, EventCondition eventCondition) {
        this.eventName = eventName;
        this.eventPeriod = eventPeriod;
        this.benefitPolicy = benefitPolicy;
        this.eventCondition = eventCondition;
    }

    public String getEventName() {
        return eventName;
    }

    public Integer calculateBenefitPrice(Orders orders) {
        if (eventPeriod.isEventPeriod(orders) && eventCondition.satisfyEventCondition(orders)) {
            return benefitPolicy.calculateBenefitPrice(orders) * -1;
        }
        return 0;
    }

    public Integer calculateDiscountPrice(Orders orders) {
        if (benefitPolicy instanceof DiscountPolicy) {
            return ((DiscountPolicy) benefitPolicy).calculateDiscountPrice(orders) * -1;
        }
        return 0;
    }

    public Map<Menu, Integer> getFreebies(Orders orders) {
        FreebiesPolicy freebiesPolicy = getFreebiesPolicy();
        return freebiesPolicy.getFreebies(orders);
    }

    private FreebiesPolicy getFreebiesPolicy() {
        try {
            return (FreebiesPolicy) benefitPolicy;
        } catch (ClassCastException exception) {
            throw new ExceptionWithMessage("[ERROR] 증정 할인 이벤트가 아닙니다.");
        }
    }
}
