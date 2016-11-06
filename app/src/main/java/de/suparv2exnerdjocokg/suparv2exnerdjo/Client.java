package de.suparv2exnerdjocokg.suparv2exnerdjo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class Client {
  private String firstName;

  private String lastName;

  private Date birthDate;

  private ArrayList<ClientMedicine> medicineList;

  private ArrayList<Service> erbrachteLeistung;

  private String concerns;

  private ArrayList<ToDo> toDoList;

public Client(){

  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public ArrayList<ToDo> getToDoList() {
    return toDoList;
  }

  public void setToDoList(ArrayList<ToDo> toDoList) {
    this.toDoList = toDoList;
  }

  public String getConcerns() {
    return concerns;
  }

  public void setConcerns(String concerns) {
    this.concerns = concerns;
  }

  public ArrayList<Service> getErbrachteLeistung() {
    return erbrachteLeistung;
  }

  public void setErbrachteLeistung(ArrayList<Service> erbrachteLeistung) {
    this.erbrachteLeistung = erbrachteLeistung;
  }

  public ArrayList<ClientMedicine> getMedicineList() {
    return medicineList;
  }

  public void setMedicineList(ArrayList<ClientMedicine> medicineList) {
    this.medicineList = medicineList;
  }


}