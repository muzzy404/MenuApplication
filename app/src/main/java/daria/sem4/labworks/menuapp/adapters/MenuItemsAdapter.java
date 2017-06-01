package daria.sem4.labworks.menuapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import daria.sem4.labworks.menuapp.POJOs.Item;
import daria.sem4.labworks.menuapp.R;

/**
 * Created by Daria on 30.05.2017.
 */

public class MenuItemsAdapter extends BaseAdapter {

    ArrayList<Item> items;
    LayoutInflater layoutInflater;
    // TODO: OnClickListener for deletion of items

    public MenuItemsAdapter(Context context, ArrayList<Item> items) {
        layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.menu_item_for_list, parent, false);
        }

        Item item = (Item) getItem(position);

        ((TextView) view.findViewById(R.id.txtItemNameInList)).setText(item.getName());
        ((TextView) view.findViewById(R.id.txtItemTypeInList)).setText("none category"); // TODO: add item type
        ((TextView) view.findViewById(R.id.txtItemWeightInList)).setText(
                String.valueOf(item.getWeight()));
        ((TextView) view.findViewById(R.id.txtItemPriceInList)).setText(
                //String.valueOf(item.getPrice()));
                String.format("%.2f", item.getPrice()));

        return view;
    }
}
