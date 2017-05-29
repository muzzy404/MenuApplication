package daria.sem4.labworks.menuapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    public final static String EDIT_TABLE_TAG = "editId";

    private MenuDbHelper menuDbHelper;
    private String[] projection = {
            MenuContract.TableEntry._ID,
            MenuContract.TableEntry.COLUMN_PERSONS,
            //MenuContract.TableEntry.COLUMN_WAITER,
    };

    private void uploadFromDB() {
        SQLiteDatabase db = menuDbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                MenuContract.TableEntry.TABLE_NAME,
                projection,
                null, null, null, null, null);

        int idColumnIndex = cursor.getColumnIndex(MenuContract.TableEntry._ID);
        int personsColumnIndex = cursor.getColumnIndex(MenuContract.TableEntry.COLUMN_PERSONS);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(idColumnIndex);
            int persons = cursor.getInt(personsColumnIndex);
            Table table = new Table(id, persons);
            tables.add(table);
        }

        cursor.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

        findViewById(R.id.btnNewTable).setOnClickListener(this);

        menuDbHelper = new MenuDbHelper(this);

        editTableNum = (EditText) findViewById(R.id.editTableNum);
        editPersonsNum = (EditText) findViewById(R.id.editPersonsNum);

        /*waiter = new Waiter(1, "Ivan");*/

        // setting adapter
        tables = new ArrayList<>();
        uploadFromDB();

        tablesAdapter = new TablesAdapter(this, tables, this);
        listViewTables = (ListView) findViewById(R.id.listViewTables);
        listViewTables.setAdapter(tablesAdapter);
    }

    @Override
    public void onClick(View v) {
        SQLiteDatabase db = menuDbHelper.getWritableDatabase();
        switch (v.getId()) {

            case R.id.btnNewTable:
                int tableNumber = Integer.valueOf(editTableNum.getText().toString());
                int persons = Integer.valueOf(editPersonsNum.getText().toString());

                if (!menuDbHelper.addNewTable(tableNumber, persons)) {
                    Toast.makeText(getApplicationContext(), "this table is already in your list",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                Table table = new Table(tableNumber, /*waiter,*/ persons);
                tables.add(table);

                break;
            case R.id.btnDeleteTable:
                int deleteIndex = (int) v.getTag();
                int deleteId = tables.get(deleteIndex).getId();

                menuDbHelper.deleteTable(deleteId);
                tables.remove(deleteIndex);

                break;
            case R.id.btnEditTable:
                int editTableId = tables.get((int) v.getTag()).getId();

                Intent intent = new Intent(TablesActivity.this, OrdersActivity.class);
                intent.putExtra(EDIT_TABLE_TAG, editTableId);
                startActivity(intent);

                break;
        }

        tablesAdapter.notifyDataSetChanged();
    }

}
