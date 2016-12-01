package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.content.Context;

import java.sql.Timestamp;

public class Note {

    private String tag;
    private String content;
    private Timestamp timestamp;
    private Carer carer;

    public Note(String tag, String content, Carer carer, Timestamp timestamp) {
        this.tag = tag;
        this.content = content;
        this.carer = carer;
        this.timestamp = timestamp;
    }

    ;

    public Carer getCarer() {
        return carer;
    }

    public void setCarer(Carer carer) {
        this.carer = carer;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getContent() {
        return content;
    }

    public String getContent(Context context) {
        return context.getString(getContent());
    }

    ;

    public void setContent(int content) {
        this.content = content;
    }

}