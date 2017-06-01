package daria.sem4.labworks.menuapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import daria.sem4.labworks.menuapp.POJOs.Item;
import daria.sem4.labworks.menuapp.POJOs.Order;
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

    TextView txtItemsNum, txtTotalSum;
    Spinner itemsSpinner;

    ArrayAdapter<String> arrayAdapter;
    MenuDbHelper menuDbHelper;

    ArrayList<Item> items;
    String[] itemString;

    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        menuDbHelper = new MenuDbHelper(this);

        // list of items for spinner
        items = new ArrayList<>();
        menuDbHelper.uploadItems(items);

        order = new Order();
        //menuDbHelper.uploadOrder(order); TODO: upload from DB items of this order + add new field to table!!!

        tableId = getIntent().getIntExtra(EDIT_TABLE_TAG, 1);
        ((TextView) findViewById(R.id.txtTableNumberOrder)).setText(String.valueOf(tableId));

        // current number for new item
        itemNum = ITEM_NUM_DEFAULT;
        txtItemsNum = (TextView) findViewById(R.id.txtItemsNum);
        txtItemsNum.setText(String.valueOf(itemNum));

        // total sum of order
        txtTotalSum = (TextView) findViewById(R.id.txtTotalSum);
        txtTotalSum.setText(String.valueOf(order.getTotal()));

        findViewById(R.id.btnAddPortion).setOnClickListener(this);
        findViewById(R.id.btnSubPortion).setOnClickListener(this);

        // silly loop, refactor this later some smarter way please
        itemString = new String[items.size()]; int i = 0;
        for(Item item : items) {
            itemString[i] = String.format("%s (portion %d) - %.2f",
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnAddPortion:
                ++itemNum;
                txtItemsNum.setText(String.valueOf(itemNum));
                break;

            case R.id.btnSubPortion:
                if (itemNum == ITEM_NUM_DEFAULT) {
                    Toast.makeText(getApplicationContext(), "You can't add less than 1 portion",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                --itemNum;
                txtItemsNum.setText(String.valueOf(itemNum));
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),
                "item " + position + "-> " + items.get(position).getName(),
                Toast.LENGTH_SHORT).show();

        this.position = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
