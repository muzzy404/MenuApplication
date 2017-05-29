package daria.sem4.labworks.menuapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static daria.sem4.labworks.menuapp.TablesActivity.EDIT_TABLE_TAG;

public class OrdersActivity extends AppCompatActivity implements View.OnClickListener {

    private int tableId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        tableId = getIntent().getIntExtra(EDIT_TABLE_TAG, 1);

        ((TextView) findViewById(R.id.txtTableNumberOrders)).setText(String.valueOf(tableId));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddOrder:

                // TODO: go to new activity

                break;
        }
    }
}
