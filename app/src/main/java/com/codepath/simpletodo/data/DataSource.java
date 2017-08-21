package com.codepath.simpletodo.data;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by jan_spidlen on 8/21/17.
 */

public abstract class DataSource<V> {

    public static interface OnDataChangeListener {
        void dataChanged();
    }

    public abstract void persist();
    public abstract void reload();

    protected ArrayList<V> items = new ArrayList<>();
    protected Context context;
    protected OnDataChangeListener onDataChangeListener;

    public DataSource(Context context) {
        this.context = context;
        reload();
        notifyChange();
    }

    public void add(V value) {
        items.add(value);
        persist();
        notifyChange();
    }

    public void remove(int index) {
        items.remove(index);
        persist();
        notifyChange();
    }

    public void set(int index, V value) {
        items.set(index, value);
        persist();
        notifyChange();
    }

    public ArrayList<V> getItems() {
        return items;
    }

    public void setOnDataChangeListener(OnDataChangeListener onDataChangeListener) {
        this.onDataChangeListener = onDataChangeListener;
    }

    private void notifyChange() {
        if (onDataChangeListener != null) {
            onDataChangeListener.dataChanged();
        }
    }
}
