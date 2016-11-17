package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.content.Context;

import java.util.Vector;

public class Service extends Task {

  private int content;

  private int complexNumber;

  private float cost;

  public Service (int name, int description, int content, int complexNumber, float cost){

      super(name, description);
      this.content = content;
      this.complexNumber = complexNumber;
      this.cost = cost;
  }

  public int getContent() {
    return content;
  }

  public String getContent(Context context){
        return context.getString(getContent());
    }

  public void setContent(int content) {
    this.content = content;
  }

  public Integer getComplexity() {
    return complexNumber;
  }

  public void setComplexity(int complexNumber) {
    this.complexNumber = complexNumber;
  }

  public String getCost() {return ""+cost;   }

  public void setCost(float cost) {
    this.cost = cost;
  }
}