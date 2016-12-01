package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.content.Context;

import java.util.Date;

public class Notiz extends GeneralTask {

  private Date timestamp;
  private Carer carer;
  private int content;

  public Notiz(int name, int description, Carer carer, int content, Date timestamp){
    super(name, description);
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

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public int getContent(){return content;};

  public String getContent(Context context){return context.getString(getContent());};

  public void setContent(int content){this.content = content;}

}