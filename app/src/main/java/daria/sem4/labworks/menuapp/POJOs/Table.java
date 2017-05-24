package daria.sem4.labworks.menuapp.POJOs;

import java.util.ArrayList;

/**
 * Created by Daria on 24.05.2017.
 */

public class Table {

    private final int id;
    private final Waiter waiter;
    private int personsNum;
    private ArrayList<Order> orders;

    public Table(int id, Waiter waiter, int personsNum) {
        this.id = id;
        this.waiter = waiter;
        this.personsNum = personsNum;
        orders = new ArrayList<>();
    }

    void addPerson() {
        ++personsNum;
    }

    void removePerson() {
        --personsNum;
    }

    int getPersonsNum() {
        return personsNum;
    }

    void addOrder(Order order) {
        orders.add(order);
    }

    int ordersNum() {
        return orders.size();
    }

}
