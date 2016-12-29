package de.suparv2exnerdjocokg.suparv2exnerdjo.dummy;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Client;
import de.suparv2exnerdjocokg.suparv2exnerdjo.ClientMedicine;
import de.suparv2exnerdjocokg.suparv2exnerdjo.PhoneNumber;
import de.suparv2exnerdjocokg.suparv2exnerdjo.R;

/**
 * Created by dsfd on 19.12.2016.
 */

public class DummyClients {

    public static final List<Client> ITEMS = new ArrayList<>();

    static {
        ITEMS.add(createDummyClient());
        ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(new PhoneNumber("Holger", "01786857658"));
        ITEMS.add(new Client(1, R.drawable.woman_image, "Emma", "Liese", "11.01.1960", null, null, null, "Munter, fröhlich", 3, "Heinestraße 8, 16845 Obermauern", phoneNumbers));
    }

    private static Client createDummyClient() {
        List<ClientMedicine> medicineList = new ArrayList<ClientMedicine>();
        medicineList=null;
        PhoneNumber num1 = new PhoneNumber("Arnold Stein","015123456");
        PhoneNumber num2 = new PhoneNumber("Helene Stein","017123456");
        ArrayList<PhoneNumber> phoneNumber = new ArrayList<>();
        phoneNumber.add(num1);
        phoneNumber.add(num2);
        Client c = new Client(0,R.drawable.woman_image, "Helga","Stein","14.04.1958",null,null,null, "Schwerhörig. Trinkt gerne Tee. Redet gerne über Formel 1.",2,"Rotsteinweg 21A",phoneNumber);
        return c;
    }
}
