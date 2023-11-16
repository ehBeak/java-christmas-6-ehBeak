package christmas.model.eventcondition;

import christmas.model.Orders;

public interface EventCondition {

    Boolean satisfyEventCondition(Orders orders);
}