package daria.sem4.labworks.menuapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        // set OnClickListener to all buttons of menu
        findViewById(R.id.btnStartWithoutAuthorization).setOnClickListener(this);
        findViewById(R.id.btnAuthorization).setOnClickListener(this);
        findViewById(R.id.btnEditMenu).setOnClickListener(this);
        findViewById(R.id.btnAddWaiter).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnStartWithoutAuthorization: {
                // TODO: create default waiter
                Intent intent = new Intent(MainActivity.this, TablesActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btnAuthorization:
                // TODO: authorization of waiter
                Toast.makeText(getApplicationContext(),
                        "Not available", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnEditMenu: {
                Intent intent = new Intent(MainActivity.this, EditMenuActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btnAddWaiter:
                // TODO: add new waiter
                Toast.makeText(getApplicationContext(),
                        "Not available", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
