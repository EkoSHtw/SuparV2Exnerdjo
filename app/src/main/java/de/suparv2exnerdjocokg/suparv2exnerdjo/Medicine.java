package de.suparv2exnerdjocokg.suparv2exnerdjo;
import android.content.Context;


public class Medicine {

  private int name;
  private int description;
  private int usage;
  private int ingredients;
  private int manufacturer;
  private int sideeffects;

    public Medicine(int name, int description, int usage, int ingredients, int manufacturer, int sideeffects){

        this.name = name;
        this.description = description;
        this.usage = usage;
        this.ingredients = ingredients;
        this.manufacturer = manufacturer;
        this.sideeffects = sideeffects;
    }

  public int getUsage() {
    return usage;
  }

  public String getUsage(Context context){return context.getString(getUsage());}

  public void setUsage(int usage) {
    this.usage = usage;
  }

  public int getSideeffects() {
    return sideeffects;
  }

  public String getSideeffects(Context context){return context.getString(getSideeffects());}

  public void setSideeffects(int sideeffects) {
    this.sideeffects = sideeffects;
  }

  public int getManufacturer() {
    return manufacturer;
  }

    public String getManufacturer(Context context){return context.getString(getManufacturer());}

  public void setManufactorint(int manufacturer) {this.manufacturer = manufacturer;}

  public int getIngredients() {
    return ingredients;
  }

    public String getIngredients(Context context){return context.getString(getIngredients());}

  public void setIngredients(int ingredients) {
    this.ingredients = ingredients;
  }
}