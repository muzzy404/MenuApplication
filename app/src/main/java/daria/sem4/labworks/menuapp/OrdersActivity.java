package daria.sem4.labworks.menuapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import daria.sem4.labworks.menuapp.adapters.OrdersListAdapter;
import daria.sem4.labworks.menuapp.data.MenuDbHelper;

import static daria.sem4.labworks.menuapp.TablesActivity.EDIT_TABLE_TAG;

public class OrdersActivity extends AppCompatActivity implements View.OnClickListener {

    private int tableId;
    private MenuDbHelper menuDbHelper;

    private ArrayList<Long> ordersList;
    private OrdersListAdapter ordersListAdapter;
    private ListView listViewOrders;

    public final static String ORDER_ID_TAG = "newOrderId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        tableId = getIntent().getIntExtra(EDIT_TABLE_TAG, 1);

        ((TextView) findViewById(R.id.txtTableNumberOrders)).setText(String.valueOf(tableId));
        findViewById(R.id.btnAddOrder).setOnClickListener(this);

        menuDbHelper = new MenuDbHelper(this);

        ordersList = new ArrayList<>();
        //menuDbHelper.uploadOrdersList(ordersList);

        ordersListAdapter = new OrdersListAdapter(this, ordersList);
        listViewOrders = (ListView) findViewById(R.id.listViewOrders);
        listViewOrders.setAdapter(ordersListAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddOrder:
                Intent intent = new Intent(OrdersActivity.this, OrderListActivity.class);

                int openOrders = menuDbHelper.getOpenOrders(tableId); // TODO: get know how many opened orders this table have
                menuDbHelper.setOpenOrders(tableId, openOrders + 1);

                intent.putExtra(EDIT_TABLE_TAG, tableId);
                intent.putExtra(ORDER_ID_TAG, openOrders); // TODO: putExtra new id of order

                startActivity(intent);
                break;
        }
    }
}
