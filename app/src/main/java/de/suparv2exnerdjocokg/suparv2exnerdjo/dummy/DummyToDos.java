package de.suparv2exnerdjocokg.suparv2exnerdjo.dummy;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Display;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.suparv2exnerdjocokg.suparv2exnerdjo.GeneralTask;
import de.suparv2exnerdjocokg.suparv2exnerdjo.R;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Todo.ToDo;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyToDos {

    /**
     * An array of sample (dummy) items.
     */
    public static List<ToDo> ITEMS = new ArrayList<ToDo>();

    public static List<ToDo> ITEMS_Erna = new ArrayList<>();
    public static List<ToDo> ITEMS_Ernst = new ArrayList<>();
    public static List<ToDo> ITEMS_Helga = new ArrayList<>();
    public static List<ToDo> ITEMS_Emma = new ArrayList<>();
    public static List<ToDo> ITEMS_Hans = new ArrayList<>();
    public static List<ToDo> ITEMS_Karl = new ArrayList<>();
    public static List<ToDo> ITEMS_Irmgard = new ArrayList<>();


    static {
        // Add some sample items.
        fillErna();
        fillErnst();
        fillHelga();
        fillEmma();
        fillHans();
        fillKarl();
        fillIrmgard();

        String[] ganzWD = {"Waschen, Duschen, Baden","Mund-, Zahn- und Lippenpflege","Rasieren","Hautpflege","Haarpflege (Kämmen, ggf. Waschen)","Nagelpflege","An- und Auskleiden incl. An- und Ablegen von Körperersatzstücken","Vorbereiten/Aufräumen des Pflegebereiches"};
        ITEMS.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Ganzwaschung", ganzWD, "Ganzwaschung")));
        String[] teilWD = {"Teilwaschung (z.B. Intimbereich)","Mund-, Zahn- und Lippenpflege","Rasieren","Hautpflege","Haarpflege (Kämmen, ggf. Waschen)","Nagelpflege","An- und Auskleiden incl. An- und Ablegen von Körperersatzstücken","Vorbereiten/Aufräumen des Pflegebereiches"};
        ITEMS.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Teilwaschung", teilWD, "Teilwaschung")));
        String[] selbstNahrD = {"Mundgerechtes Vorbereiten der Nahrung (auch angelieferte Warmspeisen)","Lagern und Vorbereiten des Pflegebedürftigen","Entsorgen der benötigten Materialien","Säubern des Arbeitsbereiches","Kenntnisvermittlung (keine Ernährungsberatung) über richtige Ernährung (z.B. Diabetiker) ausreichende Flüssigkeitszufuhr incl. Beratung über Esshilfe"};
        ITEMS.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Selbstständige Nahrungsaufnahme", selbstNahrD, "Selbstständige Nahrungsaufnahme")));
        String[] hilfeNahrD = {"Mundgerechtes Vorbereiten der Nahrung (auch angelieferte Warmspeisen)","Lagern und Vorbereiten des Pflegebedürftigen","Darreichen der Nahrung","Entsorgen der benötigten Materialien","Säubern des Arbeitsbereiches (spülen)","Versorgung des Pflegebedürftigen (Hygiene im Zusammenhang mit der Nahrungsaufnahme)","Kenntnisvermittlung (keine Ernährungsberatung) über richtige Ernährung (z.B. Diabetiker) ausreichende Flüssigkeitszufuhr incl. Beratung über Esshilfe"};
        ITEMS.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Hilfe bei der Nahrungsaufnahme", hilfeNahrD, "Hilfe bei der Nahrungsaufnahme")));
        String[] lagBetD = {"Richten des Bettes","Wechseln der Bettwäsche","Körper- und situationsgerechtes Lagern","Vermittlung von Lagerungstechniken ggf. Einsatz von Lagerungshilfen"};
        ITEMS.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Lagern/Betten", lagBetD, "Lagern/Betten")));
        String[] ausD = {"Utensilien bereitstellen, anreichen","Zur Toilette führen","Unterstützung u. allgem. Hilfestellung (Urin, Stuhl, Schweiß, Sputum, Erbrochenes)","Überwachung der Ausscheidung","Entsorgen, Reinigen des Gerätes und des Bettes","Katheterpflege (insb. Wechseln von Urinbeuteln), Stomaversorgung bei Anus praeter (Wechsel u. Entleerung des Stomabeutels)","Empfehlung zum Kontinenztraining/Inkontinenzversorgung","Nachbereiten des Pflegebedürftigen ggf. Intimpflege"};
        ITEMS.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Ausscheidungen", ausD, "Ausscheidungen")));

    }

    private static void fillErna(){

        String[] setBloodSugar = {"Zusätzlich Körpertemperatur kontrollieren"};
        ITEMS_Erna.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Blutzucker bestimmen", setBloodSugar, "Blutzucker")));

        String[] setBloodPressure = {};
        ITEMS_Erna.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Blutdruck messen", setBloodPressure, "Blutdruck")));

        String[] drinking = {"Trinkmenge erfragen"};
        ITEMS_Erna.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Flüssigkeitsbilanzierung", drinking, "Trinken")));

        String[] skinCare = {};
        ITEMS_Erna.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Hautpflege", skinCare, "Haut")));

        String[] wounds = {"Nutzen Sie Gelegenheiten, wie z.B. die Ganzkörperwäsche um den Körper auf Verletzungen und/oder Druckstellen zu überprüfen."};
        ITEMS_Erna.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Auf Verletzungen/Druckstellen prüfen", wounds, "Wunden")));

        String[] medicaments = {"Ganz wichtig: Insulin", "Thyroxin: 150 µg", "Metropolol: 47,5 mg", "Protaphane: 20 IE"};
        ITEMS_Erna.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Medikamente verabreichen", medicaments, "Medikamente")));
    }

    private static void fillErnst(){

        String[] freshAir = {"Fenster für mindestens 10 Minuten öffnen."};
        ITEMS_Ernst.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Wohnung lüften", freshAir, "Wohnung")));

        // ANLEITUNG
        String[] breathing = {"Technik zum Ausatmen (Lippenbremse)", "Einatmen durch die Nase", "Atem Trainer Triflow für 2 Minuten"};
        ITEMS_Ernst.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Atemübungen durchführen", breathing, "Atem")));

        String[] drinking = {"Trinkmenge erfragen."};
        ITEMS_Ernst.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Flüssigkeitsbilanzierung", drinking, "Trinken")));

        String[] breath = {"Die Atemstimulierende Einreibung wird nicht AM Klienten, sondern MIT dem Klienten durchgeführt", "Die Einreibung wird OHNE Handschuhe durchgeführt.", "Die Lotion in den Händen anwärmen."
        , "Die Lotion auf den Rücken vom Nacken bis zum unteren Rippenbogen auftragen, auch die Thoraxseiten mit einbeziehen.", "Niemals den Kontakt zum Klienten verlieren, d.h. eine Hand verbleibt ständig am Körper des Klienten.",
        "Beginnen Sie ruhig und gleichmäßig zu atmen. Die Bewegungen werden dem eigenen Atem angepasst und beginnen im Nacken.", "Bei der Exspiration findet die Abwärts- und bei der Inspiration die Aufwärtsbewegung statt.",
         "Es werden mehrere spiegelsymetrische Kreise beschrieben, bei den inspiratorischen Bewegungen lagert der Druck auf Daumen und Zeigefinger, bei den exspiratorischen auf der gesamten Hand.",
                 "Hierbei lässt der Druck dann an den Thoraxseiten nach.", "Die Hand hierbei unbedingt geschlossen und flach halten.", "Der Patient sollte relativ schnell die eigene Atmung an die Bewegung der Einreibung anpassen.",
                "Während der Einreibung unbedingt das Sprechen vermeiden und das Befinden des Patienten bzgl. Atmung und/oder Schmerzen beobachten."};
        ITEMS_Ernst.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Atemstimulierende Einreibung", breath, "Atem")));

        String[] ganzWD = {"Waschen, Duschen, Baden","Mund-, Zahn- und Lippenpflege","Rasieren","Hautpflege","Haarpflege (Kämmen, ggf. Waschen)","Nagelpflege","An- und Auskleiden incl. An- und Ablegen von Körperersatzstücken","Vorbereiten/Aufräumen des Pflegebereiches"};
        ITEMS_Ernst.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Ganzkörperwaschung", ganzWD, "Ganzkörperwaschung")));

        String[] medicaments = {};
        ITEMS_Ernst.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Medikamente verabreichen", medicaments, "Medikamente")));
    }

    private static void fillHelga(){

        String[] freshAir = {"Fenster für mindestens 10 Minuten öffnen."};
        ITEMS_Helga.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Wohnung lüften", freshAir, "Wohnung")));

        String[] shit = {"Ausscheidungen erfragen", "Ausscheidungen dokumentieren"};
        ITEMS_Helga.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Ausscheidungen dokumentieren", shit, "Ausscheidungen")));

        // ANLEITUNG
        String[] wound = {"Wunde am Steißbein versorgen und dokumentieren"};
        ITEMS_Helga.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Wundversorgung durchführen", wound, "Wunden")));

        String[] drinking = {"Trinkmenge erfragen."};
        ITEMS_Helga.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Flüssigkeitsbilanzierung", drinking, "Trinken")));

        String[] ganzWD = {"Waschen, Duschen, Baden","Mund-, Zahn- und Lippenpflege","Rasieren","Hautpflege","Haarpflege (Kämmen, ggf. Waschen)","Nagelpflege","An- und Auskleiden incl. An- und Ablegen von Körperersatzstücken","Vorbereiten/Aufräumen des Pflegebereiches"};
        ITEMS_Helga.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Ganzkörperwaschung", ganzWD, "Ganzkörperwaschung")));

        String[] medicaments = {};
        ITEMS_Helga.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Medikamente verabreichen", medicaments, "Medikamente")));
    }

    private static void fillEmma(){

        String[] freshAir = {"Fenster für mindestens 10 Minuten öffnen."};
        ITEMS_Emma.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Wohnung lüften", freshAir, "Wohnung")));

        String[] setBloodPressure = {};
        ITEMS_Emma.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Blutdruck messen", setBloodPressure, "Blutdruck")));

        String[] pulse = {};
        ITEMS_Emma.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Puls messen", pulse, "Puls")));

        String[] wound = {"Wunde am Unterarm versorgen und dokumentieren"};
        ITEMS_Emma.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Wundversorgung durchführen", wound, "Wunden")));

        String[] drinking = {"Trinkmenge erfragen."};
        ITEMS_Emma.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Flüssigkeitsbilanzierung", drinking, "Trinken")));

        String[] medicaments = {"Ganz wichtig: Insulin"};
        ITEMS_Emma.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Medikamente verabreichen", medicaments, "Medikamente")));
    }

    private static void fillHans(){

        String[] ear = {"Batterie der Hörgeräte prüfen", "Gegebenenfalls neue Batterien einlegen", "Hörgeräte anlegen"};
        ITEMS_Hans.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Hörgeräte anlegen", ear, "Medikamente")));

        String[] freshAir = {"Fenster für mindestens 10 Minuten öffnen."};
        ITEMS_Hans.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Wohnung lüften", freshAir, "Wohnung")));

        String[] setBloodPressure = {};
        ITEMS_Hans.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Blutdruck messen", setBloodPressure, "Blutdruck")));

        String[] pulse = {};
        ITEMS_Hans.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Puls messen", pulse, "Puls")));

        String[] wound = {};
        ITEMS_Hans.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Gewicht messen", wound, "Gewicht")));

        String[] drinking = {"Trinkmenge erfragen."};
        ITEMS_Hans.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Flüssigkeitsbilanzierung", drinking, "Trinken")));

        String[] medicaments = {};
        ITEMS_Hans.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Medikamente verabreichen", medicaments, "Medikamente")));
    }

    private static void fillKarl(){

        String[] ganzWD = {"Waschen, Duschen, Baden","Mund-, Zahn- und Lippenpflege","Rasieren","Hautpflege","Haarpflege (Kämmen, ggf. Waschen)","Nagelpflege","An- und Auskleiden incl. An- und Ablegen von Körperersatzstücken","Vorbereiten/Aufräumen des Pflegebereiches"};
        ITEMS_Karl.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Unterstützung der Ehefrau bei der Ganzkörperwaschung", ganzWD, "Ganzkörperwaschung")));

        String[] wound = {"Wunde am Steißbein versorgen und dokumentieren"};
        ITEMS_Karl.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Wundversorgung durchführen", wound, "Wunden")));

        String[] setBloodPressure = {};
        ITEMS_Karl.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Blutdruck messen", setBloodPressure, "Blutdruck")));

        String[] pulse = {};
        ITEMS_Karl.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Puls messen", pulse, "Puls")));

        String[] weight = {};
        ITEMS_Karl.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Gewicht messen", weight, "Gewicht")));

        String[] drinking = {"Trinkmenge erfragen."};
        ITEMS_Karl.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Flüssigkeitsbilanzierung", drinking, "Trinken")));

        String[] medicaments = {};
        ITEMS_Karl.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Medikamente verabreichen", medicaments, "Medikamente")));

        String[] shit = {"Ausscheidungen erfragen", "Ausscheidungen dokumentieren"};
        ITEMS_Karl.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Ausscheidungen dokumentieren", shit, "Ausscheidungen")));
    }

    private static void fillIrmgard(){

        String[] freshAir = {"Fenster für mindestens 10 Minuten öffnen."};
        ITEMS_Irmgard.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Wohnung lüften", freshAir, "Wohnung")));

        // ANLEITUNG
        String[] breathing = {"Technik zum Ausatmen (Lippenbremse)", "Einatmen durch die Nase", "Atem Trainer Triflow für 2 Minuten"};
        ITEMS_Irmgard.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Atemübungen durschführen", breathing, "Atem")));

        String[] drinking = {"Trinkmenge erfragen."};
        ITEMS_Irmgard.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Flüssigkeitsbilanzierung", drinking, "Trinken")));

        String[] breath = {"Die Atemstimulierende Einreibung wird nicht AM Klienten, sondern MIT dem Klienten durchgeführt", "Die Einreibung wird OHNE Handschuhe durchgeführt.", "Die Lotion in den Händen anwärmen."
                , "Die Lotion auf den Rücken vom Nacken bis zum unteren Rippenbogen auftragen, auch die Thoraxseiten mit einbeziehen.", "Niemals den Kontakt zum Klienten verlieren, d.h. eine Hand verbleibt ständig am Körper des Klienten.",
                "Beginnen Sie ruhig und gleichmäßig zu atmen. Die Bewegungen werden dem eigenen Atem angepasst und beginnen im Nacken.", "Bei der Exspiration findet die Abwärts- und bei der Inspiration die Aufwärtsbewegung statt.",
                "Es werden mehrere spiegelsymetrische Kreise beschrieben, bei den inspiratorischen Bewegungen lagert der Druck auf Daumen und Zeigefinger, bei den exspiratorischen auf der gesamten Hand.",
                "Hierbei lässt der Druck dann an den Thoraxseiten nach.", "Die Hand hierbei unbedingt geschlossen und flach halten.", "Der Patient sollte relativ schnell die eigene Atmung an die Bewegung der Einreibung anpassen.",
                "Während der Einreibung unbedingt das Sprechen vermeiden und das Befinden des Patienten bzgl. Atmung und/oder Schmerzen beobachten."};
        ITEMS_Irmgard.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Atemstimulierende Einreibung", breath, "Atem")));

        String[] ganzWD = {"Waschen, Duschen, Baden","Mund-, Zahn- und Lippenpflege","Rasieren","Hautpflege","Haarpflege (Kämmen, ggf. Waschen)","Nagelpflege","An- und Auskleiden incl. An- und Ablegen von Körperersatzstücken","Vorbereiten/Aufräumen des Pflegebereiches"};
        ITEMS_Irmgard.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Ganzkörperwaschung", ganzWD, "Ganzkörperwaschung")));

        String[] medicaments = {};
        ITEMS_Irmgard.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Medikamente verabreichen", medicaments, "Medikamente")));

        String[] setBloodPressure = {};
        ITEMS_Irmgard.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Blutdruck messen", setBloodPressure, "Blutdruck")));

        String[] pulse = {};
        ITEMS_Irmgard.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Puls messen", pulse, "Puls")));

        String[] weight = {};
        ITEMS_Irmgard.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask("Gewicht messen", weight, "Gewicht")));

    }

    public static void sortAlphabet(List<ToDo> items){
        Collections.sort(items, new Comparator<ToDo>() {
            @Override
            public int compare(ToDo first, ToDo second){
                return first.getTask().getName().compareTo(second.getTask().getName());
            }
        });
        List<ToDo> done = new ArrayList<>();
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).getTask().isDone()){
                done.add(items.get(i));
                items.remove(i);
                i = i-1;
            }
        }
        for(int i = 0; i < done.size(); i++){
            items.add(done.get(i));
        }
    }

    public static List<ToDo> getDone(List<ToDo> items){
        List<ToDo> done = new ArrayList<>();
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).getTask().isDone()){
                done.add(items.get(i));
            }
        }
        return done;
    }

    public static List<ToDo> getUndone(List<ToDo> items){
        List<ToDo> undone = new ArrayList<>();
        for(int i = 0; i < items.size(); i++){
            if(!items.get(i).getTask().isDone()){
                undone.add(items.get(i));
            }
        }
        return undone;
    }

    public static void sortTime(){
        Collections.sort(ITEMS, new Comparator<ToDo>() {
            @Override
            public int compare(ToDo first, ToDo second){
                return first.getTimestamp().compareTo(second.getTimestamp());
            }
        });
    }

}
