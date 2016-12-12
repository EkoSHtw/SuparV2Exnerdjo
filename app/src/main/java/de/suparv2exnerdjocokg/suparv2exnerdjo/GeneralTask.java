package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.content.Context;
import android.view.ContextThemeWrapper;

import java.util.Vector;

public class GeneralTask {

    private int name;
    private int description;
    private int tag;
    private boolean isDone;

    public GeneralTask(int name, int description, int tag) {
        this.name = name;
        this.description = description;
        this.tag = tag;
        this.isDone = false;
    }

    public int getName() {
        return name;
    }

    public String getName(Context context) {
        return context.getString(getName());
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getDescription() {
        return description;
    }

    public String getDescription(Context context) {
        return context.getString(getDescription());
    }

    public void setDescription(int description) {this.description = description;}

    public int getTag() {return tag;}

    public String getTag(Context context) {return context.getString(getTag());}

    public void setTag(int tag) {this.tag = tag;}

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}