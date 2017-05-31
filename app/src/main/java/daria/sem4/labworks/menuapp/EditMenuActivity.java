package daria.sem4.labworks.menuapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import daria.sem4.labworks.menuapp.POJOs.Item;
import daria.sem4.labworks.menuapp.adapters.MenuItemsAdapter;
import daria.sem4.labworks.menuapp.data.MenuDbHelper;

public class EditMenuActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Item> items;
    MenuItemsAdapter menuItemsAdapter;
    ListView listViewItems;

    private EditText editItemName,
                     editItemWeight,
                     editItemPrice;

    private MenuDbHelper menuDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu);

        findViewById(R.id.btnAddItemToMenuList).setOnClickListener(this);

        menuDbHelper = new MenuDbHelper(this);

        editItemName = (EditText) findViewById(R.id.editItemName);
        editItemWeight = (EditText) findViewById(R.id.editItemWeight);
        editItemPrice = (EditText) findViewById(R.id.editItemPrice);
        editItemName.setSelection(editItemName.getText().length());

        items = new ArrayList<>();
        menuDbHelper.uploadItems(items);

        menuItemsAdapter = new MenuItemsAdapter(this, items);
        listViewItems = (ListView) findViewById(R.id.listViewAllItems);
        listViewItems.setAdapter(menuItemsAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddItemToMenuList:
                try {

                    String name = editItemName.getText().toString();
                    int weight = Integer.valueOf(editItemWeight.getText().toString());
                    float price = Float.valueOf(editItemPrice.getText().toString());

                    long id = menuDbHelper.addNewItemToList(name, weight, price);
                    Log.d("SQLite", "Id of item: " + String.valueOf(id));

                    items.add(new Item(id, name, price, weight));

                } catch (Exception e) {
                    Log.d("EditMenu", e.getMessage());
                    Toast.makeText(getApplicationContext(),
                            "Please, fill all fields", Toast.LENGTH_SHORT).show();
                }

                editItemName.setText("");
                editItemPrice.setText("");
                editItemWeight.setText("");

                break;
        }

    }
}
