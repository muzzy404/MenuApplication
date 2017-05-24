package daria.sem4.labworks.menuapp.POJOs;

/**
 * Created by Daria on 24.05.2017.
 */

public class Item {

    final private int id;
    final private String name;
    private float price;
    private int weight;

    public Item(int id, String name) {
        this.id = id;
        this.name = name;

        price = (float) 00.00;
        weight = 0;
    }

    public Item(int id, String name, float price, int weight) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    String getName() {
        return name;
    }

    void setPrice(float price) {
        this.price = price;
    }
    float getPrice() {
        return price;
    }

    void setWeight(int weight) {
        this.weight = weight;
    }
    int getWeight() {
        return weight;
    }

}
