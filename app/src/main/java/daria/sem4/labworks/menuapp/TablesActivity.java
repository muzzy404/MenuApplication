package daria.sem4.labworks.menuapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import daria.sem4.labworks.menuapp.POJOs.Table;
import daria.sem4.labworks.menuapp.POJOs.Waiter;
import daria.sem4.labworks.menuapp.adapters.TablesAdapter;

public class TablesActivity extends AppCompatActivity implements View.OnClickListener {

    Waiter waiter;
    ArrayList<Table> tables;
    TablesAdapter tablesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);
        findViewById(R.id.checkTablesButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkTablesButton:
                test();
                break;
        }
    }

    private void test() {
        waiter = new Waiter(1, "Ivan");

        // create few tables
        Table table1 = new Table(1, waiter, 5);
        Table table2 = new Table(2, waiter, 6);
        Table table3 = new Table(3, waiter, 1);

        tables = new ArrayList<Table>();
        tables.add(table1);
        Toast.makeText(getApplicationContext(),
                "table -> " + table1.getId(), Toast.LENGTH_SHORT).show();
        tables.add(table2);
        tables.add(table3);

        tablesAdapter = new TablesAdapter(this, tables);

        ListView listViewTables = (ListView) findViewById(R.id.listViewTables);
        listViewTables.setAdapter(tablesAdapter);
    }
}
