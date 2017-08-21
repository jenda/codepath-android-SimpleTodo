package com.codepath.simpletodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.codepath.simpletodo.data.DataProvider;
import com.codepath.simpletodo.data.DataSource;
import com.codepath.simpletodo.data.FileDataSource;
import com.codepath.simpletodo.data.ListItemModel;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements ListItemAdapter.EditItemListener {

    ListItemAdapter aTodoAdapter;
    ListView lvItems;
    EditText editText;
    DataSource<ListItemModel> dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataSource = DataProvider.getInstance(this);
        aTodoAdapter = new ListItemAdapter(this, R.layout.list_item,
                dataSource.getItems(), this);
        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(aTodoAdapter);
        editText = (EditText) findViewById(R.id.etEditText);

        dataSource.setOnDataChangeListener(new DataSource.OnDataChangeListener() {
            @Override
            public void dataChanged() {
                aTodoAdapter.notifyDataSetChanged();
            }
        });
    }

    public void onEditItem(int position) {
        Intent intent = new Intent(MainActivity.this, EditItemActivity.class);
        intent.putExtra(Constants.POSITION, position);
        intent.putExtra(Constants.VALUE, dataSource.getItems().get(position));
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            int position = data.getIntExtra(Constants.POSITION, -1);
            ListItemModel newItemValue = (ListItemModel) data.getSerializableExtra(Constants.VALUE);
            dataSource.set(position, newItemValue);
        }
    }

    public void onAddItem(View view) {
        String text = editText.getText().toString();
        DataProvider.getInstance(this).add(new ListItemModel(text, false, null));
        editText.setText("");
    }

    private void test() {
        FileDataSource fds = new FileDataSource(this);
        ListItemModel xx = new ListItemModel("asd", false, new Date());
        fds.add(xx);
        fds.add(xx);


        FileDataSource fds2 = new FileDataSource(this);

        Log.d("jenda-1", fds.getItems().toString());
        Log.d("jenda-2", fds2.getItems().toString());
    }
}
