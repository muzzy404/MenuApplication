package daria.sem4.labworks.menuapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import daria.sem4.labworks.menuapp.POJOs.Table;
import daria.sem4.labworks.menuapp.POJOs.Waiter;
import daria.sem4.labworks.menuapp.adapters.TablesAdapter;

public class TablesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

        // TEST ----------
        Waiter waiter = new Waiter(1, "Ivan");

        // create few tables
        Table table1 = new Table(1, waiter, 5);
        Table table2 = new Table(2, waiter, 6);
        Table table3 = new Table(3, waiter, 1);

        ArrayList<Table> tables = new ArrayList<Table>();
        tables.add(table1);
        tables.add(table2);
        tables.add(table3);

        TablesAdapter tablesAdapter = new TablesAdapter(this, tables);

        ListView listViewTables = (ListView) findViewById(R.id.listViewTables);
        listViewTables.setAdapter(tablesAdapter);
        //----------------
    }

}
