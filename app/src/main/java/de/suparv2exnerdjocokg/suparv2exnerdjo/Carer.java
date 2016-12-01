package de.suparv2exnerdjocokg.suparv2exnerdjo;
import java.util.ArrayList;

public class Carer implements Actor {


  private String name ;

  public Carer(String name){
    this.name = name;
  }

  public String getName() {
    return name;
  }

  private ArrayList<Client> clientList;

  public ArrayList<Client> getClientList() {
    return clientList;
  }

  public void setClientList(ArrayList<Client> clientList) {
    this.clientList = clientList;
  }
}