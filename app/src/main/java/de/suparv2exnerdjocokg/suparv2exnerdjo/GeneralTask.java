package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.content.Context;
import android.view.ContextThemeWrapper;

import java.util.Vector;

public class GeneralTask {

    private String name;
    private String[] description;
    private String tag;
    private boolean isDone;
    private int shiftetDays;



    public GeneralTask(String name, String[] description, String tag) {
        this.name = name;
        this.description = description;
        this.tag = tag;
        this.isDone = false;
        this.shiftetDays = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getDescription() {
        return description;
    }

    public void setDescription(String[] description) {this.description = description;}

    public String getTag() {return tag;}

    public void setTag(String tag) {this.tag = tag;}

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getDaysShiftet() {
        return shiftetDays;
    }

    public void setShiftet(int shiftet) {
        shiftetDays = shiftet;
    }
}