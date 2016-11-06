package de.suparv2exnerdjocokg.suparv2exnerdjo;
import java.util.Vector;
import java.util.ArrayList;

public class Carer implements Actor {

  private ArrayList<Client> clientList;

  public ArrayList<Client> getClientList() {
    return clientList;
  }

  public void setClientList(ArrayList<Client> clientList) {
    this.clientList = clientList;
  }
}