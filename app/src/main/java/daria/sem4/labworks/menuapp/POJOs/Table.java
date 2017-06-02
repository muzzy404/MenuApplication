package daria.sem4.labworks.menuapp.POJOs;

/**
 * Created by Daria on 24.05.2017.
 */

public class Table {

    private final long id;
    //private final Waiter waiter;
    private int personsNum;
    private int openOrders;

    public Table(long id, /*Waiter waiter,*/ int personsNum, int openOrders) {
        this.id = id;
        //this.waiter = waiter;
        this.personsNum = personsNum;
        this.openOrders = openOrders;
    }

    public long getId() {
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

    public void setOpenOrders(int openOrders) {
        this.openOrders = openOrders;
    }
    public int getOpenOrders() {
        return openOrders;
    }

}
