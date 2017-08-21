package com.codepath.simpletodo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.codepath.simpletodo.data.ListItemModel;

import android.text.format.DateFormat;

import java.util.Date;

public class EditItemActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Button saveButton;
    Integer position;
    ListItemModel itemValue;
    EditText editText;
    EditText dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_item_activity);
        saveButton = (Button) findViewById(R.id.saveButton);
        editText = (EditText) findViewById(R.id.editItemText);
        dateText = (EditText) findViewById(R.id.dateEdit);

        position = getIntent().getIntExtra(Constants.POSITION, -1);
        itemValue = (ListItemModel) getIntent().getSerializableExtra(Constants.VALUE);

        editText.setText(itemValue.getText());
        if (itemValue.getDueDate() != null) {
            dateText.setText(DateFormat.format("MM/dd/yy", itemValue.getDueDate()));
        }

        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                itemValue.setText(editText.getText().toString());
                data.putExtra(Constants.VALUE, itemValue);
                data.putExtra(Constants.POSITION, position);
                EditItemActivity.this.setResult(RESULT_OK, data);
                EditItemActivity.this.finish();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Date d = new Date(year, month, dayOfMonth);
        itemValue.setDueDate(d);
        dateText.setText(DateFormat.format("MM/dd/yy", itemValue.getDueDate()));
    }
}
