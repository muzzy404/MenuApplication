package daria.sem4.labworks.menuapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import daria.sem4.labworks.menuapp.POJOs.Order;

/**
 * Created by Daria on 02.06.2017.
 */

public class ListItemsAdapter extends BaseAdapter {

    Order order;
    LayoutInflater layoutInflater;

    public ListItemsAdapter(Context context, Order order) {
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
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
