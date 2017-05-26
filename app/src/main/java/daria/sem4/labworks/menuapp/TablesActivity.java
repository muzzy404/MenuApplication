package daria.sem4.labworks.menuapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

import daria.sem4.labworks.menuapp.POJOs.Table;
import daria.sem4.labworks.menuapp.POJOs.Waiter;
import daria.sem4.labworks.menuapp.adapters.TablesAdapter;

public class TablesActivity extends AppCompatActivity implements View.OnClickListener {

    Waiter waiter;  // TODO: get from another activity
    ArrayList<Table> tables;
    TablesAdapter tablesAdapter;
    ListView listViewTables;

    EditText editTableNum, editPersonsNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

        findViewById(R.id.btnNewTable).setOnClickListener(this);

        editTableNum = (EditText) findViewById(R.id.editTableNum);
        editPersonsNum = (EditText) findViewById(R.id.editPersonsNum);

        waiter = new Waiter(1, "Ivan");

        // setting adapter
        tables = new ArrayList<Table>();
        tablesAdapter = new TablesAdapter(this, tables, this);
        listViewTables = (ListView) findViewById(R.id.listViewTables);
        listViewTables.setAdapter(tablesAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnNewTable:
                int tableNumber = Integer.valueOf(editTableNum.getText().toString());

                Table table = new Table(
                        tableNumber,
                        waiter,
                        Integer.valueOf(editPersonsNum.getText().toString()));
                // TODO: do not add if exists!
                tables.add(table);
                // TODO: adding to DB
                break;

            case R.id.btnDeleteTable:
                //TODO: remove from DB
                tables.remove((int) v.getTag());
                break;
        }

        tablesAdapter.notifyDataSetChanged();
    }

    //TODO: method to upload tables from DB

}
