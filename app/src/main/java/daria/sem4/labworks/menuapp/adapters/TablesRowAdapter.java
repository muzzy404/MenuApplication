package daria.sem4.labworks.menuapp.adapters;
//http://startandroid.ru/ru/uroki/vse-uroki-spiskom/113-urok-54-kastomizatsija-spiska-sozdaem-svoj-adapter.html

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import daria.sem4.labworks.menuapp.POJOs.Table;
import daria.sem4.labworks.menuapp.R;

/**
 * Created by Daria on 24.05.2017.
 */

public class TablesRowAdapter extends BaseAdapter {

    ArrayList<Table> tables;
    Context context;
    LayoutInflater layoutInflater;

    TablesRowAdapter(Context context, ArrayList<Table> tables) {
        this.context = context;
        layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.tables = tables;
    }

    @Override
    public int getCount() {
        return tables.size();
    }

    @Override
    public Object getItem(int position) {
        return tables.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tables.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.table_item, parent, false);
        }

        Table table = (Table) getItem(position);

        // create new view
        ((TextView) view.findViewById(R.id.tableNumberItem)).setText(table.getId());
        ((TextView) view.findViewById(R.id.personsNumItem)).setText(table.getPersonsNum());
        ((TextView) view.findViewById(R.id.ordersNumItem)).setText(table.ordersNum());

        return view;
    }
}
