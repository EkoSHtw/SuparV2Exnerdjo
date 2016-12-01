package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.content.Context;
import android.provider.ContactsContract;
import android.content.res.Resources;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class Client {
  private String firstName;

  private String lastName;

  private String adress;

  private Date birthDate;

  private int carelevel;

  private ArrayList<ClientMedicine> medicineList;

  private ArrayList<Service> erbrachteLeistung;

  private String concerns;

  private ArrayList<ToDo> toDoList;

  private ArrayList<PhoneNumber> phoneNumber;

  public Client(Context context){
    this.firstName = context.getString(R.string.clientfirstname);
    this.lastName = context.getString(R.string.clientlastname);

    String dateString = context.getString(R.string.clientBirthdate);
    String dateFormat = "dd/mm/yyyy";
    this.birthDate = stringToDate(dateString, dateFormat);
    this.medicineList =null;
    this.erbrachteLeistung = null;
    this.toDoList = null;
    this.concerns= "test";
    this.carelevel = 2;
    this.adress = context.getString(R.string.clientAddress);

    PhoneNumber num1 = new PhoneNumber(context.getString(R.string.fam1),
            (context.getString(R.string.telephonenumber)));

    PhoneNumber num2 = new PhoneNumber(context.getString(R.string.fam2),
           (context.getString(R.string.telephonenumber2)));
    phoneNumber = new ArrayList<>();
    this.phoneNumber.add(num1);
    this.phoneNumber.add(num2);

  }
  public int getCarelevel() {
    return carelevel;
  }

  public void setCarelevel(int carelevel) {
    this.carelevel = carelevel;
  }
  private Date stringToDate(String aDate, String aFormat) {

    if(aDate==null) return null;
    ParsePosition pos = new ParsePosition(0);
    SimpleDateFormat simpledateformat = new SimpleDateFormat(aFormat);
    Date stringDate = simpledateformat.parse(aDate, pos);
    return stringDate;
  }

  public String getAdress() {
    return adress;
  }

  public void setAdress(String adress) {
    this.adress = adress;
  }

  public int getPhoneNumberlenght(){
    return phoneNumber.size();
  }

  public ArrayList<PhoneNumber> getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(PhoneNumber phoneNumber){this.phoneNumber.add(phoneNumber);}

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

  public void addErbrachteLeistung(Service erbrachteLeistung) {
    this.erbrachteLeistung.add(erbrachteLeistung);
  }

  public ArrayList<ClientMedicine> getMedicineList() {
    return medicineList;
  }

  public void setMedicineList(ClientMedicine medicine) {
    medicineList.add(medicine);
  }
/*
  public void deleteMedicine(String medicine){
    for (ClientMedicine med : medicineList) {
      if(med.getMedicine().getName() == medicine){
        medicineList.remove(med);
        break;
      }
    }
  }

  public deleteToDo(ToDo toDelete){

  }
*/

}