package de.suparv2exnerdjocokg.suparv2exnerdjo.dummy;

import java.util.ArrayList;
import java.util.List;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Client;
import de.suparv2exnerdjocokg.suparv2exnerdjo.PhoneNumber;
import de.suparv2exnerdjocokg.suparv2exnerdjo.R;

/**
 * Created by dsfd on 19.12.2016.
 */

public class DummyClients {

    public static final List<Client> ITEMS = new ArrayList<>();


    static {
        ITEMS.add(createDummyClient());

        // Client 2 -> Ernst Meier

        ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(new PhoneNumber("Holger Meier", "01786857658", "Sohn"));


        DummyVitalValues vital = new DummyVitalValues();
        DummyClientMedicine medicineList = new DummyClientMedicine(DummyMedicine.ITEMS_PRESCRIBED_Ernst, DummyMedicine.ITEMS_TEMPORARY, DummyMedicine.ITEMS_SELF_ORDERED);

        ArrayList<Integer> pictograms2 = new ArrayList<>();
        pictograms2.add(R.drawable.blizzard_);
        pictograms2.add(R.drawable.eye_);

        ITEMS.add(new Client(1, R.drawable.ernst, "Ernst", "Meier", "25.11.1951", medicineList, null, DummyToDos.ITEMS_Ernst, vital.vitalValues, "COPD: chronisch obstruktive Lungenerkrankung, Bluthochdruck", 2, "Heinestraße 8, 16845 Obermauern", phoneNumbers, pictograms2));

        // Client 3 ->  Helga Stern

        ArrayList<PhoneNumber> phoneNumbers1 = new ArrayList<>();
        phoneNumbers.add(new PhoneNumber("Olaf Stern", "017855557658", "Sohn"));


        DummyVitalValues vital1 = new DummyVitalValues();
        DummyClientMedicine medicineList1 = new DummyClientMedicine(DummyMedicine.ITEMS_PRESCRIBED_Helga, DummyMedicine.ITEMS_TEMPORARY, DummyMedicine.ITEMS_SELF_ORDERED);

        ArrayList<Integer> pictograms3 = new ArrayList<>();
        pictograms2.add(R.drawable.ear);

        ITEMS.add(new Client(1, R.drawable.helga, "Helga", "Stern", "14.04.1958", medicineList1, null, DummyToDos.ITEMS_Helga, vital1.vitalValues, "Schwerhörig, Herzinsuffizienz, Bluthochdruck, Chronische Obstipation (Verstopfung), Schilddrüsenunterfunktion, Chronische Lungenentzündung", 3, "Heinestraße 8, 16845 Obermauern", phoneNumbers1, pictograms3));

        // Client 4 ->  Emma Liesegang

        ArrayList<PhoneNumber> phoneNumbers2 = new ArrayList<>();
        phoneNumbers.add(new PhoneNumber("Olaf Stern", "017855557658", "Sohn"));


        DummyVitalValues vital2 = new DummyVitalValues();
        DummyClientMedicine medicineList2 = new DummyClientMedicine(DummyMedicine.ITEMS_PRESCRIBED_Emma, DummyMedicine.ITEMS_TEMPORARY, DummyMedicine.ITEMS_SELF_ORDERED);

        ArrayList<Integer> pictograms4 = new ArrayList<>();
        pictograms2.add(R.drawable.ear);

        ITEMS.add(new Client(1, R.drawable.emma, "Emma", "Liesegang", "13.03.1957", medicineList2, null, DummyToDos.ITEMS_Emma, vital2.vitalValues, "Diabetis mellitus, Wunde am rechter Unterarm, Demenz, Herzinsuffizienz, Grundpflege durch die Tochter", 3, "Heinestraße 8, 16845 Obermauern", phoneNumbers2, pictograms4));

        // Client 5 -> Hans Rüdiger Schmidt

        ArrayList<PhoneNumber> phoneNumbers3 = new ArrayList<>();
        phoneNumbers.add(new PhoneNumber("Olaf Stern", "017855557658", "Sohn"));


        DummyVitalValues vital3 = new DummyVitalValues();
        DummyClientMedicine medicineList3 = new DummyClientMedicine(DummyMedicine.ITEMS_PRESCRIBED_Hans, DummyMedicine.ITEMS_TEMPORARY, DummyMedicine.ITEMS_SELF_ORDERED);

        ArrayList<Integer> pictograms5 = new ArrayList<>();
        pictograms2.add(R.drawable.ear);

        ITEMS.add(new Client(1, R.drawable.hans, "Hans Rüdiger", "Schmidt", "12.07.1949", medicineList3, null, DummyToDos.ITEMS_Hans, vital3.vitalValues, "Bluthochdruck, Schwerhörigkeit (Hörgerät beidseitig) -> langsam und deutlich reden!, Herzinsuffizienz", 2, "Heinestraße 8, 16845 Obermauern", phoneNumbers3, pictograms5));

        // Client 6 -> Karl Heinz Hufschmied

        ArrayList<PhoneNumber> phoneNumbers4 = new ArrayList<>();
        phoneNumbers.add(new PhoneNumber("Hilde Hufschmied", "01656567658", "Ehefrau"));


        DummyVitalValues vital4 = new DummyVitalValues();
        DummyClientMedicine medicineList4 = new DummyClientMedicine(DummyMedicine.ITEMS_PRESCRIBED_Karl, DummyMedicine.ITEMS_TEMPORARY, DummyMedicine.ITEMS_SELF_ORDERED);

        ArrayList<Integer> pictograms6 = new ArrayList<>();
        pictograms2.add(R.drawable.ear);

        ITEMS.add(new Client(1, R.drawable.karl, "Karl Heinz", "Hufschmied", "14.12.1939", medicineList4, null, DummyToDos.ITEMS_Karl, vital4.vitalValues, "Demenz, Bettlägerig, Herzinsuffizienz, Inkontinenz, Karls Frau wohnt im selben Haushalt und übernimmt die Grundpflege", 3, "Heinestraße 8, 16845 Obermauern", phoneNumbers4, pictograms6));

        // Client 7 -> Irmgard Weber

        ArrayList<PhoneNumber> phoneNumbers5 = new ArrayList<>();
        phoneNumbers.add(new PhoneNumber("Hilde Hufschmied", "01656567658", "Ehefrau"));


        DummyVitalValues vital5 = new DummyVitalValues();
        DummyClientMedicine medicineList5 = new DummyClientMedicine(DummyMedicine.ITEMS_PRESCRIBED_Irmgard, DummyMedicine.ITEMS_TEMPORARY, DummyMedicine.ITEMS_SELF_ORDERED);

        ArrayList<Integer> pictograms7 = new ArrayList<>();
        pictograms2.add(R.drawable.ear);

        ITEMS.add(new Client(1, R.drawable.irmgard, "Irmgard", "Weber", "31.08.1927", medicineList5, null, DummyToDos.ITEMS_Irmgard, vital5.vitalValues, "COPD, Herzinsuffizienz", 2, "Heinestraße 8, 16845 Obermauern", phoneNumbers5, pictograms7));

    }



    private static Client createDummyClient() {

        // Client 1 -> Erna Schubert

        PhoneNumber num1 = new PhoneNumber("Arnold Schubert","015123456", "Ehemann");
        PhoneNumber num2 = new PhoneNumber("Helene Schuhert","017123456", "Tochter");
        ArrayList<PhoneNumber> phoneNumber = new ArrayList<>();
        phoneNumber.add(num1);
        phoneNumber.add(num2);
        DummyVitalValues vital = new DummyVitalValues();

        ArrayList<Integer> pictograms1 = new ArrayList<>();
        pictograms1.add(R.drawable.mouth__);
        pictograms1.add(R.drawable.ear_);

        DummyClientMedicine medicine = new DummyClientMedicine(DummyMedicine.ITEMS_PRESCRIBED_Erna, DummyMedicine.ITEMS_TEMPORARY, DummyMedicine.ITEMS_SELF_ORDERED);

        Client c = new Client(0,R.drawable.erna, "Erna","Schubert","17.08.1943",medicine,null,DummyToDos.ITEMS_Erna, vital.vitalValues, "Diabetes mellitus, Adipositas, Schilddrüsenunterfuntion",1,"Rotsteinweg 21A",phoneNumber, pictograms1);

        return c;
    }
}
