package com.codepath.simpletodo.data;

import android.content.Context;

/**
 * Created by jan_spidlen on 8/21/17.
 */

public class DataProvider {
    private static DataSource<ListItemModel> instance;
    public static DataSource<ListItemModel> getInstance(Context context) {
        if (instance == null) {
            instance = new FileDataSource(context);
        }
        return instance;
    }
}
