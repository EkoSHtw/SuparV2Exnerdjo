package de.suparv2exnerdjocokg.suparv2exnerdjo;
import java.util.Vector;

public class Medicine extends Task {

  private String usage;

  private String ingredience;

  private String manufactor;

  private String sideeffects;

  public String getUsage() {
    return usage;
  }

  public void setUsage(String usage) {
    this.usage = usage;
  }

  public String getSideeffects() {
    return sideeffects;
  }

  public void setSideeffects(String sideeffects) {
    this.sideeffects = sideeffects;
  }

  public String getManufactor() {
    return manufactor;
  }

  public void setManufactor(String manufactor) {
    this.manufactor = manufactor;
  }

  public String getIngredience() {
    return ingredience;
  }

  public void setIngredience(String ingredience) {
    this.ingredience = ingredience;
  }
}