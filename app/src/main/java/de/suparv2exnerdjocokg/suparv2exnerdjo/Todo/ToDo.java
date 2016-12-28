package de.suparv2exnerdjocokg.suparv2exnerdjo.Todo;

import java.sql.Timestamp;

import de.suparv2exnerdjocokg.suparv2exnerdjo.GeneralTask;

public class ToDo{

  private Timestamp timestamp;

  private GeneralTask task;

  public ToDo(Timestamp timestamp, GeneralTask task){
      this.timestamp = timestamp;
      this.task = task;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }

  public GeneralTask getTask() {
    return task;
  }

  public void setTask(GeneralTask task) {
    this.task = task;
  }


}