package de.suparv2exnerdjocokg.suparv2exnerdjo.dummy;

import java.util.ArrayList;
import java.util.List;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Medication.Medicine;

/**
 * Created by dsfd on 29.12.2016.
 */

public class DummyMedicine {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Medicine> ITEMS_PRESCRIBED = new ArrayList<>();
    public static final List<Medicine> ITEMS_TEMPORARY = new ArrayList<>();
    public static final List<Medicine> ITEMS_SELF_ORDERED = new ArrayList<>();

    static {
        ITEMS_PRESCRIBED.add(new Medicine("Levothyroxin", "L-Thyrox HEXAL 100", "100 µg", "Tablette", 1, 0, 0, 0, "Stück", "morgens nüchtern, 30 Min. vor dem Frühstück", "Schilddrüse"));
        ITEMS_PRESCRIBED.add(new Medicine("Ramipril/Hydrochlorothiazid", "Ramipril comp Heumann", "5/25 mg", "Tablette", 1, 0, 0, 0, "Stück", "", "Bluthochdruck"));
        ITEMS_PRESCRIBED.add(new Medicine("Ramipril", "Ramipril beta", "5 mg", "Tablette", 0, 0, 1, 0, "Stück", "", "Bluthochdruck"));
        ITEMS_PRESCRIBED.add(new Medicine("Phenprocoumon", "Marcumar", "3 mg", "Tablette", 0, 0, 0, 0, "Stück", "nach Marcumarplan", "Blutverdünnung"));
        ITEMS_PRESCRIBED.add(new Medicine("Metamizol", "Novaminsulfon Lichtenstein", "500 mg", "Tablette", 0, 0, 0, 0, "Stück", "max. drei Tabletten am Tag", "Schmerzen"));
        ITEMS_PRESCRIBED.add(new Medicine("Tiotropium", "Spiriva", "18 µg", "Spray", 1, 0, 0, 0, "Hübe", "", "Chronische Lungenerkrankung"));
        ITEMS_PRESCRIBED.add(new Medicine("Macrogol", "Movicol", "13,12 g", "Pulver", 1, 0, 0, 0, "Btl.", "ausreichend dazu trinken", "Stuhlgang"));

        ITEMS_TEMPORARY.add(new Medicine("Clarithromycin", "Clarithromycin TEVA", "250 mg", "Tabletten", 0, 0, 0, 0, "Stück", "alle 12 Stunden eine, von 01.04.-06.04.", "Bronchitis"));

        ITEMS_SELF_ORDERED.add(new Medicine("Myrtol", "GeloMyrtol", "120 mg", "Kapsen", 2, 2, 2, 0, "Stück", "min. eine halbe Stunde vor dem Essen mit einem großen Glas Wasser", "Bronchitis"));
        ITEMS_SELF_ORDERED.add(new Medicine("Johanniskraut", "Life Balance", "900mg", "Tabletten", 1, 0, 0, 0, "Stück", "nach dem Frühstück", "Stimmung"));
        ITEMS_SELF_ORDERED.add(new Medicine("Cetirizin", "Cetirizin STADA", "10 mg", "Tabletten", 0, 0, 0, 0, "Stück", "1 Tablette morgens", "Heuschnupfen"));
        ITEMS_SELF_ORDERED.add(new Medicine("Azelastin", "Allergodil akut Nasenspray", "0,14 mg", "Spray", 0, 0, 0, 0, "Hübe", "bei Pollenflug, morgens und abends je 1 Sprühstoß pro Nasenloch", "Heuschnupfen"));



    }

}
