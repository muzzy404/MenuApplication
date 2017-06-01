package daria.sem4.labworks.menuapp.POJOs;

import java.util.HashMap;

/**
 * Created by Daria on 24.05.2017.
 */

public class Order {

    private HashMap<Item, Integer> orderList;
    private double total;

    public Order() {
        orderList = new HashMap<>();
        total = 0.0;
    }

    public double getTotal() {
        return total;
    }

    HashMap<Item, Integer> getOrderList() {
        return orderList;
    }

    void addItem(Item item, Integer num) {
        orderList.put(item, num);
        updateTotal();
    }

    private void updateTotal() {
        total = 0.0;
        for(Item key : orderList.keySet()) {
            total += orderList.get(key) * key.getPrice();
        }
    }

    // TODO: increase/decrease several item

}
