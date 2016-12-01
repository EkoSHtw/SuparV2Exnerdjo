package de.suparv2exnerdjocokg.suparv2exnerdjo;

import java.util.Date;

public class ToDo {

  private Date timestamp;

  private GeneralTask task;

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public GeneralTask getTask() {
    return task;
  }

  public void setTask(GeneralTask task) {
    this.task = task;
  }
}