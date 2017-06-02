package daria.sem4.labworks.menuapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import daria.sem4.labworks.menuapp.POJOs.Table;
import daria.sem4.labworks.menuapp.adapters.TablesAdapter;
import daria.sem4.labworks.menuapp.data.MenuContract;
import daria.sem4.labworks.menuapp.data.MenuDbHelper;

public class TablesActivity extends AppCompatActivity implements View.OnClickListener {

    //Waiter waiter;
    ArrayList<Table> tables;
    TablesAdapter tablesAdapter;
    ListView listViewTables;

    EditText editTableNum, editPersonsNum;

    public final static String EDIT_TABLE_TAG = "editTableId";
    public final static String EDIT_ORDER_TAG = "editOrderId";

    private MenuDbHelper menuDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

        Log.d("Test", "Activity onCreate method");

        findViewById(R.id.btnNewTable).setOnClickListener(this);

        menuDbHelper = new MenuDbHelper(this);

        editTableNum = (EditText) findViewById(R.id.editTableNum);
        editPersonsNum = (EditText) findViewById(R.id.editPersonsNum);

        // setting adapter
        tables = new ArrayList<>();
        menuDbHelper.uploadTables(tables);

        tablesAdapter = new TablesAdapter(this, tables, this);
        listViewTables = (ListView) findViewById(R.id.listViewTables);
        listViewTables.setAdapter(tablesAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnNewTable: // add new table
                try {
                    int tableNumber = Integer.valueOf(editTableNum.getText().toString());
                    int persons = Integer.valueOf(editPersonsNum.getText().toString());

                    if (menuDbHelper.addNewTable(tableNumber, persons) == -1) {
                        Toast.makeText(getApplicationContext(), "this table is already in your list",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                    tables.add(new Table(tableNumber, /*waiter,*/ persons, 0));
                } catch (Exception e) {
                    Toast.makeText(getApplication(),
                            "Please, fill all fields", Toast.LENGTH_SHORT).show();
                }

                editTableNum.setText("");
                editPersonsNum.setText("");

                break;
            case R.id.btnDeleteTable: // delete  table
                int deleteIndex = (int) v.getTag();
                long deleteId = tables.get(deleteIndex).getId();

                menuDbHelper.deleteTable(deleteId);
                tables.remove(deleteIndex);

                break;
            case R.id.btnEditTable: // edit table -> go to OrdersActivity
                int position = (int) v.getTag();
                long editTableId = tables.get(position).getId();
                long oderId = tables.get(position).getOpenOrders();

                // TODO: add with helper new order

                Intent intent = new Intent(TablesActivity.this, OrdersActivity.class);

                intent.putExtra(EDIT_TABLE_TAG, editTableId);
                intent.putExtra(EDIT_ORDER_TAG, oderId);

                startActivity(intent);

                break;
        }

        tablesAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();
        tables.clear();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        menuDbHelper.uploadTables(tables);
    }

}
