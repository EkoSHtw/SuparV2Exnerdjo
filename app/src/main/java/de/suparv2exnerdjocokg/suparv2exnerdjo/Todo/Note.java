package de.suparv2exnerdjocokg.suparv2exnerdjo;

import java.sql.Timestamp;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Carer;

public class Note implements Comparable<Note> {

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

//    public Note(String tag, String content, Carer carer, Timestamp timestamp) {
//        this.tag = tag;
//        this.carer = carer;
//        this.content = content;
//        this.timestamp = timestamp;
//    }

    public String getInfoFromPosition(int position) {
        if (position == 0) {
            return tag;
        } else if (position == 1) {
            return timestamp.toString();
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

    @Override
    public int compareTo(Note o) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;

//        if(this == o){
//            return 0;
//        }
//        return this.timestamp.compareTo(o.getTimestamp());
        int comparison = this.timestamp.compareTo(o.getTimestamp());
        if (comparison != EQUAL) return comparison;

        comparison = this.tag.compareTo(o.getTag());
        if (comparison != EQUAL) return comparison;

        return EQUAL;
    }
}