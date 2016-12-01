package de.suparv2exnerdjocokg.suparv2exnerdjo;
import java.util.Vector;
import java.util.ArrayList;

public class Carer implements Actor {

  private String name;
  private ArrayList<Client> clientList;

  public Carer(String name){
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ArrayList<Client> getClientList() {
    return clientList;
  }

  public void setClientList(ArrayList<Client> clientList) {
    this.clientList = clientList;
  }
}