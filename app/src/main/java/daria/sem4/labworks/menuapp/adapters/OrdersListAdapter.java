package daria.sem4.labworks.menuapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import daria.sem4.labworks.menuapp.R;

/**
 * Created by Daria on 03.06.2017.
 */

public class OrdersListAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;

    ArrayList<Long> ordersList;

    public OrdersListAdapter(Context context, ArrayList<Long> ordersList) {
        layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.ordersList = ordersList;
    }

    @Override
    public int getCount() {
        return ordersList.size();
    }

    @Override
    public Object getItem(int position) {
        return ordersList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ordersList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.order_item, parent, false);
        }

        Long orderId = (Long) getItem(position);

        ((TextView) view.findViewById(R.id.txtOrderId)).setText(
                String.valueOf(orderId + 1));
        ((TextView) view.findViewById(R.id.txtFirstItemName)).setText("edit order");

        return view;
    }
}
