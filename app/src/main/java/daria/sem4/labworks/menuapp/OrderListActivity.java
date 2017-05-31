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

import static daria.sem4.labworks.menuapp.TablesActivity.EDIT_TABLE_TAG;

public class OrderListActivity extends AppCompatActivity
                               implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private int tableId;

    private final int ITEM_NUM_DEFAULT = 1;
    private       int itemNum;

    private final String SPINNER_TITLE = "Menu";

    TextView txtItemsNum;
    Spinner itemsSpinner;

    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        tableId = getIntent().getIntExtra(EDIT_TABLE_TAG, 1);
        ((TextView) findViewById(R.id.txtTableNumberOrder)).setText(String.valueOf(tableId));

        itemNum = ITEM_NUM_DEFAULT;
        txtItemsNum = (TextView) findViewById(R.id.txtItemsNum);
        txtItemsNum.setText(String.valueOf(itemNum));

        findViewById(R.id.btnAddPortion).setOnClickListener(this);
        findViewById(R.id.btnSubPortion).setOnClickListener(this);

        // TODO: Strigs load
        String[] strings = {"Ice cream 200 rub", "Soup 300 rub", "Rise 30 rub"};

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, strings);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        itemsSpinner = (Spinner) findViewById(R.id.spSelectItemOrder);
        itemsSpinner.setAdapter(arrayAdapter);
        itemsSpinner.setPrompt(SPINNER_TITLE);
        itemsSpinner.setSelection(1);

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
        Toast.makeText(getApplicationContext(), "item " + position, Toast.LENGTH_SHORT);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
