package christmas.model.eventcondition;

import christmas.model.Orders;

public class NoEventCondition implements EventCondition{

    @Override
    public Boolean satisfyEventCondition(Orders orders) {
        return true;
    }
}
