package com.codepath.simpletodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    Button saveButton;
    Integer position;
    String itemValue;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_item_activity);
        saveButton = (Button) findViewById(R.id.saveButton);
        editText = (EditText) findViewById(R.id.editItemText);

        position = getIntent().getIntExtra(Constants.POSITION, -1);
        itemValue = getIntent().getStringExtra(Constants.VALUE);

        editText.setText(itemValue);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra(Constants.VALUE, editText.getText().toString());
                data.putExtra(Constants.POSITION, position);
                EditItemActivity.this.setResult(RESULT_OK, data);
                EditItemActivity.this.finish();
            }
        });
    }
}
