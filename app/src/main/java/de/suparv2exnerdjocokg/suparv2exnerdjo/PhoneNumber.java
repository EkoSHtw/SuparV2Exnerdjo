package de.suparv2exnerdjocokg.suparv2exnerdjo;

/**
 * Created by Eko on 22.11.2016.
 */

public class PhoneNumber {

    private String name;
    private String number;



    public PhoneNumber(String name, String number){
        this.name= name;
        this.number = number;

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
