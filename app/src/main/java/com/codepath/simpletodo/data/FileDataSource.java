package com.codepath.simpletodo.data;

import android.app.Application;
import android.content.Context;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by jan_spidlen on 8/21/17.
 */

public class FileDataSource extends DataSource<ListItemModel> {

    private final static String FILE_NAME = "test.txt";
    public FileDataSource(Context context) {
        super(context);
    }

    @Override
    public void persist() {
//        File filesDir = context.getFilesDir();
//        File file = new File(filesDir, FILE_NAME);
        try {
            FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(items);
            os.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void reload() {
//        File filesDir = context.getFilesDir();
//        File file = new File(filesDir, FILE_NAME);
        try {
            FileInputStream fis = context.openFileInput(FILE_NAME);
            ObjectInputStream is = new ObjectInputStream(fis);
            items = (ArrayList<ListItemModel>) is.readObject();
            is.close();
            fis.close();
        } catch(IOException e) {

            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void readItems() {
    }

    private void writeItems() {
    }
}
