package de.suparv2exnerdjocokg.suparv2exnerdjo.Todo;

import java.sql.Timestamp;

import de.suparv2exnerdjocokg.suparv2exnerdjo.GeneralTask;

public class ToDo implements Comparable{

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

  @Override
  public int compareTo(Object o) {
      ToDo t = (ToDo)o;

      long l2 = this.getTimestamp().getTime();
      long l1 = t.getTimestamp().getTime();
      if (l2 > l1)
          return 1;
      else if (l1 > l2)
          return -1;
      else
          return 0;
  }
}