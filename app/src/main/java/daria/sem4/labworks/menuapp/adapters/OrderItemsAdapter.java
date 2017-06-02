package daria.sem4.labworks.menuapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import daria.sem4.labworks.menuapp.POJOs.Item;
import daria.sem4.labworks.menuapp.POJOs.Order;
import daria.sem4.labworks.menuapp.R;

/**
 * Created by Daria on 02.06.2017.
 */

public class OrderItemsAdapter extends BaseAdapter {

    Order order;
    LayoutInflater layoutInflater;

    public OrderItemsAdapter(Context context, Order order) {
        layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.order = order;
    }

    @Override
    public int getCount() {
        return order.size();
    }

    @Override
    public Object getItem(int position) {
        return order.getItemByPosition(position);
    }

    @Override
    public long getItemId(int position) {
        return order.getIdByPosition(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.fooditem_for_order, parent, false);
        }

        Item item = (Item) getItem(position);

        final int num = order.getNumByPosition(position);

        ((TextView) view.findViewById(R.id.txtItemNameOrder)).setText(item.getName());
        ((TextView) view.findViewById(R.id.txtItemPriceOrder)).setText
                (String.format("%.2f", item.getPrice() * num));
        ((TextView) view.findViewById(R.id.txtItemNumOrder)).setText
                (String.valueOf(num));

        return view;
    }
}
