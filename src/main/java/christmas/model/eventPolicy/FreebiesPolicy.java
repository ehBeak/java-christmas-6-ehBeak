package christmas.model.eventPolicy;

import christmas.model.Orders;
import christmas.model.menu.Menu;
import java.util.Map;

public interface FreebiesPolicy extends EventPolicy {

    Map<Menu, Integer> getFreebies(Orders orders);
}
