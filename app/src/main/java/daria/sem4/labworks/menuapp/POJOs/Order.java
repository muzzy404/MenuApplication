package daria.sem4.labworks.menuapp.POJOs;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Daria on 24.05.2017.
 */

public class Order {

    private class IdNumPair {
        private final long id;
        private final int num;

        IdNumPair(long id, int num) {
            this.id = id;
            this.num = num;
        }
    }

    private ArrayList<IdNumPair> itemsIdNum;     // list of order
    private HashMap<Long, Integer> positionById; // positions of ArrayList items

    ArrayList<Item> items;

    private double total;
    private final long id;

    public Order(long id, ArrayList<Item> items) {
        this.id = id;
        this.items = items;

        positionById = new HashMap<>();

        itemsIdNum = new ArrayList<>();
        for(int i = 0; i < items.size(); ++i) {
            positionById.put(items.get(i).getId(), i);
        }

        total = 0.0;
    }

    public long getId() {
        return id;
    }

    public double getTotal() {
        return total;
    }

    public void addItemById(long id, int num) {
        itemsIdNum.add(new IdNumPair(id, num));
        updateTotal();
    }

    public Item getItemByPosition(int position) {
        long itemId = itemsIdNum.get(position).id;
        return items.get(positionById.get(itemId));
    }

    public int getNumByPosition(int position) {
        return itemsIdNum.get(position).num;
    }

    public long getIdByPosition(int position) {
        return itemsIdNum.get(position).id;
    }

    private void updateTotal() {
        total = 0.0;
        for(IdNumPair idNumPair : itemsIdNum) {
            //                     [position in items <-- item id] --> Item item.getPrice()
            float price = items.get(positionById.get(idNumPair.id)).getPrice();
            total += idNumPair.num * price;
        }
    }

    public int size() {
        return itemsIdNum.size();
    }

    // TODO: increase/decrease several item

}
