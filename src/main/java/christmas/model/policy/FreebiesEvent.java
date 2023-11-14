package christmas.model.policy;

import christmas.model.Orders;
import christmas.model.menu.Menu;
import java.util.Map;

public interface FreebiesEvent extends EventPolicy {

    Map<Menu, Integer> getFreebies(Orders orders);
}