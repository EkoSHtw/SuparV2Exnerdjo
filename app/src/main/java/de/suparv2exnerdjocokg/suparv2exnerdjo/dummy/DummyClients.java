package de.suparv2exnerdjocokg.suparv2exnerdjo.dummy;

import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Client;
import de.suparv2exnerdjocokg.suparv2exnerdjo.ClientMedicine;
import de.suparv2exnerdjocokg.suparv2exnerdjo.PhoneNumber;
import de.suparv2exnerdjocokg.suparv2exnerdjo.R;
import de.suparv2exnerdjocokg.suparv2exnerdjo.VitalValues;

/**
 * Created by dsfd on 19.12.2016.
 */

public class DummyClients {

    public static final List<Client> ITEMS = new ArrayList<>();


    static {
        ITEMS.add(createDummyClient());
        ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(new PhoneNumber("Holger", "01786857658", "Sohn"));


        DummyVitalValues vital = new DummyVitalValues();
        DummyClientMedicine medicineList = new DummyClientMedicine(DummyMedicine.ITEMS_PRESCRIBED, DummyMedicine.ITEMS_TEMPORARY, DummyMedicine.ITEMS_SELF_ORDERED);

        ArrayList<Integer> pictograms2 = new ArrayList<>();
        pictograms2.add(R.drawable.blizzard_);
        pictograms2.add(R.drawable.eye_);

        ITEMS.add(new Client(1, R.drawable.woman_image, "Emma", "Liese", "11.01.1960", medicineList, null, DummyToDos.ITEMS, vital.vitalValues, "Munter, fröhlich", 3, "Heinestraße 8, 16845 Obermauern", phoneNumbers, pictograms2));


    }



    private static Client createDummyClient() {
        PhoneNumber num1 = new PhoneNumber("Arnold Stein","015123456", "Ehemann");
        PhoneNumber num2 = new PhoneNumber("Helene Stein","017123456", "Tochter");
        ArrayList<PhoneNumber> phoneNumber = new ArrayList<>();
        phoneNumber.add(num1);
        phoneNumber.add(num2);
        DummyVitalValues vital = new DummyVitalValues();

        ArrayList<Integer> pictograms1 = new ArrayList<>();
        pictograms1.add(R.drawable.mouth__);
        pictograms1.add(R.drawable.ear_);

        DummyClientMedicine medicine = new DummyClientMedicine(DummyMedicine2.ITEMS_PRESCRIBED, DummyMedicine2.ITEMS_TEMPORARY, DummyMedicine2.ITEMS_SELF_ORDERED);
        Client c = new Client(0,R.drawable.woman_image, "Helga","Stein","14.04.1958",medicine,null,DummyToDos.ITEMS, vital.vitalValues, "Schwerhörig. Trinkt gerne Tee. Redet gerne über Formel 1.",2,"Rotsteinweg 21A",phoneNumber, pictograms1);

        return c;
    }
}
