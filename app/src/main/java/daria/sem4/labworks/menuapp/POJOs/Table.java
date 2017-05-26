package daria.sem4.labworks.menuapp.POJOs;

import java.util.ArrayList;

/**
 * Created by Daria on 24.05.2017.
 */

public class Table {

    private final int id;
    //private final Waiter waiter;
    private int personsNum;
    private ArrayList<Order> orders;

    public Table(int id, /*Waiter waiter,*/ int personsNum) {
        this.id = id;
        //this.waiter = waiter;
        this.personsNum = personsNum;
        orders = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void addPerson() {
        ++personsNum;
    }

    public void removePerson() {
        --personsNum;
    }

    public int getPersonsNum() {
        return personsNum;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public int ordersNum() {
        return orders.size();
    }

}
