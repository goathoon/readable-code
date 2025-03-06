package cleancode.mission.day4;

import java.util.List;
import java.util.Map;


public class Order {
    private List<String> items;
    private Integer totalPrice;
    private Map<String,String> customerInfo;

    public boolean doesNotIncludeItem() {
        return this.items.isEmpty();
    }

    public boolean isNotValidTotalPrice() {
        return this.totalPrice <= 0;
    }

    public boolean hasNotAnyCustomerInfo() {
        return this.customerInfo.isEmpty();
    }
}
