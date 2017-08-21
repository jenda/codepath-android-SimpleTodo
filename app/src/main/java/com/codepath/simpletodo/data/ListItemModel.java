package com.codepath.simpletodo.data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jan_spidlen on 8/21/17.
 */
public class ListItemModel implements Serializable {
    private static final long serialVersionUID = 5177222050535318633L;
    private String text;
    //    private int position;
    private boolean isChecked;
    private Date dueDate;

    public ListItemModel(String text, boolean isChecked, Date dueDate) {
        this.text = text;
        this.isChecked = isChecked;
        this.dueDate = dueDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String toString() {
        return "text: " + text + ", isChecked: " + isChecked + ", dueDate: " +
                (dueDate == null ? "null" : dueDate.toString());
    }
}
