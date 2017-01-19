package de.suparv2exnerdjocokg.suparv2exnerdjo.dummy;

import java.util.ArrayList;
import java.util.List;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Medication.Medicine;

/**
 * Created by dsfd on 19.01.2017.
 */

public class DummyMedicine2 {

    public static final List<Medicine> ITEMS_PRESCRIBED = new ArrayList<>();
    public static final List<Medicine> ITEMS_TEMPORARY = new ArrayList<>();
    public static final List<Medicine> ITEMS_SELF_ORDERED = new ArrayList<>();

    static {
        ITEMS_PRESCRIBED.add(new Medicine("Levothyroxin", "L-Thyrox HEXAL 100", "100 µg", "Tablette", 1, 0, 0, 0, "Stück", "morgens nüchtern, 30 Min. vor dem Frühstück", "Schilddrüse"));
        ITEMS_PRESCRIBED.add(new Medicine("Ramipril/Hydrochlorothiazid", "Ramipril comp Heumann", "5/25 mg", "Tablette", 1, 0, 0, 0, "Stück", "", "Bluthochdruck"));
        ITEMS_PRESCRIBED.add(new Medicine("Ramipril", "Ramipril beta", "5 mg", "Tablette", 0, 0, 1, 0, "Stück", "", "Bluthochdruck"));

        ITEMS_TEMPORARY.add(new Medicine("Clarithromycin", "Clarithromycin TEVA", "250 mg", "Tabletten", 0, 0, 0, 0, "Stück", "alle 12 Stunden eine, von 01.04.-06.04.", "Bronchitis"));

        ITEMS_SELF_ORDERED.add(new Medicine("Myrtol", "GeloMyrtol", "120 mg", "Kapsen", 2, 2, 2, 0, "Stück", "min. eine halbe Stunde vor dem Essen mit einem großen Glas Wasser", "Bronchitis"));
        ITEMS_SELF_ORDERED.add(new Medicine("Azelastin", "Allergodil akut Nasenspray", "0,14 mg", "Spray", 0, 0, 0, 0, "Hübe", "bei Pollenflug, morgens und abends je 1 Sprühstoß pro Nasenloch", "Heuschnupfen"));

    }
}
