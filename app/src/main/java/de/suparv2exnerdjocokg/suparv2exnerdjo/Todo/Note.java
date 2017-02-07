package de.suparv2exnerdjocokg.suparv2exnerdjo.Todo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Carer;

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

    public String getInfoFromPosition(int position) {
        if (position == 0) {
            return tag;
        } else if (position == 1) {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy, HH:mm", Locale.GERMAN);
            String dateString = format.format(timestamp);
            return dateString;
        } else if (position == 2) {
            return content;
        } else if (position == 3) {
            return carer.getName();
        }
        return "";
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTag() {
        return tag;
    }

}