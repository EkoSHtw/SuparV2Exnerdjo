package de.suparv2exnerdjocokg.suparv2exnerdjo;

import java.util.Date;
import java.util.Vector;

public class ToDo {

  private Date timestamp;

  private Task task;

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public Task getTask() {
    return task;
  }

  public void setTask(Task task) {
    this.task = task;
  }
}