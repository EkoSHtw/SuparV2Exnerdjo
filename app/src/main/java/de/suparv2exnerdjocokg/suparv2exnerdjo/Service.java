package de.suparv2exnerdjocokg.suparv2exnerdjo;

import java.util.Vector;

public class Service extends Task {

  private String content;

  private Integer complexity;

  private float cost;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getComplexity() {
    return complexity;
  }

  public void setComplexity(Integer complexity) {
    this.complexity = complexity;
  }

  public float getCost() {
    return cost;
  }

  public void setCost(float cost) {
    this.cost = cost;
  }
}