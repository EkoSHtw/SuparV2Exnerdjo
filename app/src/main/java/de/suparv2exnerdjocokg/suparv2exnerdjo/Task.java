package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.content.Context;

import java.util.Vector;

public class Task {

  private int name;

  private int description;

  public Task (int name, int description){
    this.name = name;
    this.description = description;
  }

  public int getName(){
    return name;
  }

  public String getName(Context context) {
    return context.getString(getName());
  }

  public void setName(int name) {
    this.name = name;
  }

  public int getDescription(){
    return description;
  }

  public String getDescription(Context context) {
    return context.getString(getDescription());
  }

  public void setDescription(int description) {this.description = description;  }
}