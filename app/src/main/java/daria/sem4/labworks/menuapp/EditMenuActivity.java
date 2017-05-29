package daria.sem4.labworks.menuapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class EditMenuActivity extends AppCompatActivity {

    private EditText editItemName,
                     editItemWeight,
                     editItemPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu);

        editItemName = (EditText) findViewById(R.id.editItemName);
        editItemWeight = (EditText) findViewById(R.id.editItemWeight);
        editItemPrice = (EditText) findViewById(R.id.editItemPrice);

        editItemName.setSelection(editItemName.getText().length());

    }
}
