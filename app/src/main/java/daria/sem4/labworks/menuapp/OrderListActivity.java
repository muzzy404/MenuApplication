package daria.sem4.labworks.menuapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import daria.sem4.labworks.menuapp.POJOs.Item;
import daria.sem4.labworks.menuapp.POJOs.Order;
import daria.sem4.labworks.menuapp.adapters.OrderItemsAdapter;
import daria.sem4.labworks.menuapp.data.MenuDbHelper;

import static daria.sem4.labworks.menuapp.TablesActivity.EDIT_TABLE_TAG;

public class OrderListActivity extends AppCompatActivity
                               implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private int tableId;

    private final int ITEM_NUM_DEFAULT = 1;
    private int itemNum;
    private int position = 0;
    private double total = 0.0;

    private final String SPINNER_TITLE = "Menu";

    ListView listViewItems;
    TextView txtItemsNum, txtTotalSum;
    Spinner itemsSpinner;

    ArrayAdapter<String> arrayAdapter;
    MenuDbHelper menuDbHelper;

    OrderItemsAdapter orderItemsAdapter;

    ArrayList<Item> items;
    String[] itemString;

    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        menuDbHelper = new MenuDbHelper(this);

        // TODO: ++number of opened orders

        // list of items for spinner
        items = new ArrayList<>();
        menuDbHelper.uploadItems(items);

        order = new Order(1, items);
        //menuDbHelper.uploadOrder(order); TODO: upload from DB items of this order + add new field to table!!!

        tableId = getIntent().getIntExtra(EDIT_TABLE_TAG, 1);
        ((TextView) findViewById(R.id.txtTableNumberOrder)).setText(String.valueOf(tableId));

        // current number for new item
        itemNum = ITEM_NUM_DEFAULT;
        txtItemsNum = (TextView) findViewById(R.id.txtItemsNum);
        txtItemsNum.setText(String.valueOf(itemNum));

        // total sum of order
        txtTotalSum = (TextView) findViewById(R.id.txtTotalSum);
        txtTotalSum.setText(String.format("%.2f", order.getTotal()));

        // set OnClickListener
        findViewById(R.id.btnAddPortion).setOnClickListener(this);
        findViewById(R.id.btnSubPortion).setOnClickListener(this);
        findViewById(R.id.btnAddNewItemToOrder).setOnClickListener(this);

        // silly loop, refactor this later some smarter way please
        itemString = new String[items.size()]; int i = 0;
        for(Item item : items) {
            itemString[i] = String.format(Locale.US, "%s (portion %d) - %.2f",
                    item.getName(), item.getWeight(), item.getPrice());
            ++i;
        }

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, itemString);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        itemsSpinner = (Spinner) findViewById(R.id.spSelectItemOrder);
        itemsSpinner.setAdapter(arrayAdapter);
        itemsSpinner.setPrompt(SPINNER_TITLE);
        itemsSpinner.setSelection(position);

        itemsSpinner.setOnItemSelectedListener(this);

        // adapter
        orderItemsAdapter = new OrderItemsAdapter(this, order);
        listViewItems = (ListView) findViewById(R.id.listViewOrderItems);
        listViewItems.setAdapter(orderItemsAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnAddPortion: // ++portions
                ++itemNum;
                //txtItemsNum.setText(String.valueOf(itemNum));
                break;

            case R.id.btnSubPortion: // --portions
                if (itemNum == ITEM_NUM_DEFAULT) {
                    Toast.makeText(getApplicationContext(), "You can't add less than 1 portion",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                --itemNum;
                //txtItemsNum.setText(String.valueOf(itemNum));
                break;

            case R.id.btnAddNewItemToOrder: // add new item

                // TODO: add to database

                order.addItemById(items.get(position).getId(), itemNum);
                txtTotalSum.setText(String.format("%.2f", order.getTotal()));

                itemNum = ITEM_NUM_DEFAULT;
                break;
        }
        txtItemsNum.setText(String.valueOf(itemNum));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.position = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Test", "onDestroy for list");
        // TODO: if size of Order == 0 then --number of orders
    }
}
