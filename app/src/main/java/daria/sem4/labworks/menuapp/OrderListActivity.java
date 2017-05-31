package daria.sem4.labworks.menuapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static daria.sem4.labworks.menuapp.TablesActivity.EDIT_TABLE_TAG;

public class OrderListActivity extends AppCompatActivity implements View.OnClickListener {

    private int tableId;

    private final int ITEM_NUM_DEFAULT = 1;
    private       int itemNum;

    TextView txtItemsNum;

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
}
