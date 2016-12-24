package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.support.v4.app.Fragment;
import android.util.Log;

import java.io.Serializable;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Client implements Serializable {
  private static final long serialVersionUID = -2163051469151804394L;
  private int id;
  private int imagePath;
  private String firstName;
  private Fragment frag;
  private String lastName;

  private String adress;

  private String birthDate;

  private int carelevel;

  private ArrayList<ClientMedicine> medicineList;

  private ArrayList<Service> erbrachteLeistung;

  private String concerns;

  private ArrayList<ToDo> toDoList;

  private ArrayList<PhoneNumber> phoneNumber;

  public ArrayList<Fragment> getDocumentation() {
    return documentation;
  }



  public void addDocumentation(Fragment documentation) {
    this.documentation.add(documentation);
  }

  private ArrayList<Fragment> documentation;

  public int getImagePath() {
    return imagePath;
  }

  public Client(int id, int ImagePath, String firstName, String lastName, String dateString, ArrayList<ClientMedicine> medicineList,
                ArrayList<Service> erbrachteLeistung, ArrayList<ToDo> toDoList, String infodump, int carelevel, String adress, ArrayList<PhoneNumber> phoneNumber){
    this.firstName = firstName;
    this.lastName = lastName;
    this.id = id;

    String dateFormat = "dd/mm/yyyy";
   // this.birthDate = stringToDate(dateString, dateFormat);
    this.birthDate = dateString;
    this.medicineList = new ArrayList<ClientMedicine>();
    this.medicineList = medicineList;
    this.erbrachteLeistung = new ArrayList<Service>();
    this.erbrachteLeistung = erbrachteLeistung;
    this.toDoList = new ArrayList<ToDo>();
    this.toDoList = toDoList;
    this.concerns= infodump;
    this.carelevel = carelevel;
    this.adress = adress;


  /*  PhoneNumber num1 = new PhoneNumber(context.getString(R.string.fam1),
            (context.getString(R.string.telephonenumber)));

    PhoneNumber num2 = new PhoneNumber(context.getString(R.string.fam2),
           (context.getString(R.string.telephonenumber2))); */
    this.phoneNumber = new ArrayList<>();
    this.phoneNumber = phoneNumber;
    String a = "" + phoneNumber.size();
    Log.println(Log.INFO, "test",a);
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

  public void addPhoneNumber(PhoneNumber phoneNumber){this.phoneNumber.add(phoneNumber);}

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

  public String getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(String birthDate) {
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

  public void addMedicineList(ClientMedicine medicine) {
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