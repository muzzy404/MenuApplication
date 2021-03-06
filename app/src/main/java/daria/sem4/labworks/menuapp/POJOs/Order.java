package daria.sem4.labworks.menuapp.POJOs;

import java.util.HashMap;

/**
 * Created by Daria on 24.05.2017.
 */

public class Order {

    private final int id;
    private HashMap<Item, Integer> orderList;
    private float total;

    public Order(int id) {
        this.id = id;
        orderList = new HashMap<>();
        total = (float) 0.0;
    }

    float getTotal() {
        return total;
    }

    int getId() {
        return id;
    }

    HashMap<Item, Integer> getOrderList() {
        return orderList;
    }

    void addItem(Item item, Integer num) {
        orderList.put(item, num);
        updateTotal();
    }

    private void updateTotal() {
        total = (float) 0.0;
        for(Item key : orderList.keySet()) {
            total += orderList.get(key) * key.getPrice();
        }
    }

    // TODO: increase/decrease several item

}
