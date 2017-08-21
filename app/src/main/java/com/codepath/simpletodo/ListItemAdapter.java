package com.codepath.simpletodo;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.codepath.simpletodo.data.DataProvider;
import com.codepath.simpletodo.data.ListItemModel;

import java.util.List;

/**
 * Created by jan_spidlen on 8/21/17.
 */

public class ListItemAdapter extends ArrayAdapter<ListItemModel> {
    static interface EditItemListener {
        void onEditItem(int position);
    }

    protected EditItemListener editItemListener;

    public ListItemAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ListItemModel> objects, EditItemListener editItemListener) {
        super(context, resource, objects);
        this.editItemListener = editItemListener;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ListItemModel listItemModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        final CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
        final TextView dueDate = (TextView) convertView.findViewById(R.id.dueDate);
        final Button delete = (Button) convertView.findViewById(R.id.delete);
        final Button edit = (Button) convertView.findViewById(R.id.editButton);

        checkBox.setText(listItemModel.getText());
        checkBox.setChecked(listItemModel.isChecked());
        if (checkBox.isChecked()) {
            checkBox.setPaintFlags(checkBox.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            checkBox.setPaintFlags(checkBox.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        }
        dueDate.setText(listItemModel.getDueDate() != null ? DateFormat.format("MM/dd/yy", listItemModel.getDueDate()) : "");

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listItemModel.setChecked(checkBox.isChecked());
                DataProvider.getInstance(ListItemAdapter.this.getContext()).set(position, listItemModel);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataProvider.getInstance(ListItemAdapter.this.getContext()).remove(position);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editItemListener.onEditItem(position);
            }
        });

        return convertView;
    }

}
