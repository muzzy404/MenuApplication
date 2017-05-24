package daria.sem4.labworks.menuapp.POJOs;

import java.util.ArrayList;

/**
 * Created by Daria on 24.05.2017.
 */

public class Table {

    private final int id;
    private final Waiter waiter;
    private ArrayList<Order> orders;

    public Table(int id, Waiter waiter) {
        this.id = id;
        this.waiter = waiter;
        orders = new ArrayList<>();
    }

    void addOrder(Order order) {
        orders.add(order);
    }

}
