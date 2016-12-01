package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.content.Context;

import java.sql.Timestamp;
import java.util.Date;

public class Notiz {

      private Timestamp timestamp;
  private Carer carer;
  private String content;
    private String tag;

    public Notiz(String tag, String content, Carer carer, Timestamp timestamp){
      this.tag = tag;
    this.carer = carer;
    this.content = content;
    this.timestamp = timestamp;
  };

  public Carer getCarer() {
    return carer;
  }

  public void setCarer(Carer carer) {
    this.carer = carer;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }

  public String getContent(){return content;};

  public void setContent(String content){this.content = content;}

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}