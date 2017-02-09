package de.suparv2exnerdjocokg.suparv2exnerdjo.dummy;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Carer;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Todo.Note;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyNotes {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Note> ITEMS = new ArrayList<>();
    public static final List<Note> ITEMS_Erna = new ArrayList<>();
    public static final List<Note> ITEMS_Ernst = new ArrayList<>();
    public static final List<Note> ITEMS_Helga = new ArrayList<>();
    public static final List<Note> ITEMS_Emma = new ArrayList<>();
    public static final List<Note> ITEMS_Hans = new ArrayList<>();
    public static final List<Note> ITEMS_Karl = new ArrayList<>();
    public static final List<Note> ITEMS_Irmgard = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Note> ITEM_MAP = new HashMap<>();


    static {

        fillErna();
        fillErnst();
        fillHelga();
        fillEmma();
        fillHans();
        fillKarl();
        fillIrmgard();

        ITEMS.add(new Note("Ganzwaschung", "Bitte immer den roten (weichen) Lappen benutzen.", new Carer("Jimmy"), new Timestamp(System.currentTimeMillis())));
        ITEMS.add(new Note("Teilwaschung", "Bitte immer den roten (weichen) Lappen benutzen.", new Carer("Jimmy"), new Timestamp(System.currentTimeMillis())));
        ITEMS.add(new Note("Hilfe bei der Nahrungsaufnahme", "Harte Speisen bitte zerkleinern", new Carer("Jimmy"), new Timestamp(System.currentTimeMillis())));
        ITEMS.add(new Note("Selbstständige Nahrungsaufnahme", "Harte Speisen bitte zerkleinern", new Carer("Jimmy"), new Timestamp(System.currentTimeMillis())));
        ITEMS.add(new Note("Nahrungsaufnahme", "Zahnschmerzen bei dem Kauen", new Carer("Timmy"), new Timestamp(getYesterday())));
        ITEMS.add(new Note("Haut", "Hautfarbe auffällig blass", new Carer("Timmy"), new Timestamp(getYesterday())));
        ITEMS.add(new Note("Wunden", "Wunde XY eitert stark", new Carer("Timmy"), new Timestamp(getYesterday())));
        ITEMS.add(new Note("Stimmung", "Sehr schlechte Laune", new Carer("Timmy"), new Timestamp(getYesterday())));
        ITEMS.add(new Note("Medikamenteneinnahme", "Problemlos", new Carer("Timmy"), new Timestamp(getYesterday())));
        Note first = new Note("Zähne putzen", "Ich habe dem Clienten die Zähne geputzt", new Carer("Olaf"),
                new Timestamp(1L));
        Note second = new Note("Eine Aufgabe", "Ich habe dem Clienten die Zähne geputzt", new Carer("not Olaf")
                , new Timestamp(3L));
        Note third = new Note("Keks", "Ich habe dem Clienten die Zähne geputzt",
                new Carer("Keksi"), new Timestamp(2L));
        ITEMS.add(first);
        ITEMS.add(second);
        ITEMS.add(third);
    }

    public static void fillErna(){
        Carer c1 = new Carer("John") ;
        Carer c2 = new Carer ("Hilde");
        Calendar cal = Calendar.getInstance();
        final int year = 2017;
        final int month = cal.FEBRUARY;
        cal.set(year, month, 7, 8, 30);
        Note first = new Note("Zähne putzen", "Ich habe dem Clienten die Zähne geputzt", c1,
                new Timestamp(cal.getTimeInMillis()));
        cal.set(year, month, 8, 8, 28);
        Note second = new Note("Bluthochdruck","Arm leicht angeschwollen",c1, new Timestamp(cal.getTimeInMillis()));
        ITEMS_Erna.add(second);
        ITEMS_Erna.add(first);
        ITEMS_Erna.add(new Note("Nahrungsaufnahme", "Zahnschmerzen bei dem Kauen", c2, new Timestamp(getYesterday())));
        ITEMS_Erna.add(new Note("Wunden", "Wunde Arm eitert stark", c2, new Timestamp(getYesterday())));


    };


    public static void fillErnst(){
        Carer c1 = new Carer("John") ;
        Carer c2 = new Carer ("Hilde");
        Calendar cal = Calendar.getInstance();
        final int year = 2017;
        final int month = cal.FEBRUARY;
        cal.set(year, month, 7, 8, 30);
        Note first = new Note("Zähne putzen", "Ich habe dem Clienten die Zähne geputzt", c1,
                new Timestamp(cal.getTimeInMillis()));
        cal.set(year, month, 8, 8, 28);
        Note second = new Note("Bluthochdruck","Arm leicht angeschwollen",c1, new Timestamp(cal.getTimeInMillis()));
        ITEMS_Ernst.add(second);
        ITEMS_Ernst.add(first);
        ITEMS_Ernst.add(new Note("Nahrungsaufnahme", "Zahnschmerzen bei dem Kauen", c2, new Timestamp(getYesterday())));
        ITEMS_Ernst.add(new Note("Wunden", "Wunde Arm eitert stark", c2, new Timestamp(getYesterday())));

    };
    public static void fillHelga(){
        Carer c1 = new Carer("John") ;
        Carer c2 = new Carer ("Hilde");
        Calendar cal = Calendar.getInstance();
        final int year = 2017;
        final int month = cal.FEBRUARY;
        cal.set(year, month, 7, 8, 30);
        Note first = new Note("Zähne putzen", "Ich habe dem Clienten die Zähne geputzt", c1,
                new Timestamp(cal.getTimeInMillis()));
        cal.set(year, month, 8, 8, 28);
        Note second = new Note("Bluthochdruck","Arm leicht angeschwollen",c1, new Timestamp(cal.getTimeInMillis()));
        ITEMS_Helga.add(second);
        ITEMS_Helga.add(first);
        ITEMS_Helga.add(new Note("Nahrungsaufnahme", "Zahnschmerzen bei dem Kauen", c2, new Timestamp(getYesterday())));
        ITEMS_Helga.add(new Note("Wunden", "Wunde Arm eitert stark", c2, new Timestamp(getYesterday())));

    };
    public static void fillEmma(){
        Carer c1 = new Carer("John") ;
        Carer c2 = new Carer ("Hilde");
        Calendar cal = Calendar.getInstance();
        final int year = 2017;
        final int month = cal.FEBRUARY;
        cal.set(year, month, 7, 8, 30);
        Note first = new Note("Zähne putzen", "Ich habe dem Clienten die Zähne geputzt", c1,
                new Timestamp(cal.getTimeInMillis()));
        cal.set(year, month, 8, 8, 28);
        Note second = new Note("Bluthochdruck","Arm leicht angeschwollen",c1, new Timestamp(cal.getTimeInMillis()));
        ITEMS_Emma.add(second);
        ITEMS_Emma.add(first);
        ITEMS_Emma.add(new Note("Nahrungsaufnahme", "Zahnschmerzen bei dem Kauen", c2, new Timestamp(getYesterday())));
        ITEMS_Emma.add(new Note("Wunden", "Wunde Arm eitert stark", c2, new Timestamp(getYesterday())));
    };
    public static void fillHans(){
        Carer c1 = new Carer("John") ;
        Carer c2 = new Carer ("Hilde");
        Calendar cal = Calendar.getInstance();
        final int year = 2017;
        final int month = cal.FEBRUARY;
        cal.set(year, month, 7, 8, 30);
        Note first = new Note("Zähne putzen", "Ich habe dem Clienten die Zähne geputzt", c1,
                new Timestamp(cal.getTimeInMillis()));
        cal.set(year, month, 8, 8, 28);
        Note second = new Note("Bluthochdruck","Arm leicht angeschwollen",c1, new Timestamp(cal.getTimeInMillis()));
        ITEMS_Hans.add(second);
        ITEMS_Hans.add(first);
        ITEMS_Hans.add(new Note("Nahrungsaufnahme", "Zahnschmerzen bei dem Kauen", c2, new Timestamp(getYesterday())));
        ITEMS_Hans.add(new Note("Wunden", "Wunde Arm eitert stark", c2, new Timestamp(getYesterday())));
    };
    public static void fillKarl(){
        Carer c1 = new Carer("John") ;
        Carer c2 = new Carer ("Hilde");
        Calendar cal = Calendar.getInstance();
        final int year = 2017;
        final int month = cal.FEBRUARY;
        cal.set(year, month, 7, 8, 30);
        Note first = new Note("Zähne putzen", "Ich habe dem Clienten die Zähne geputzt", c1,
                new Timestamp(cal.getTimeInMillis()));
        cal.set(year, month, 8, 8, 28);
        Note second = new Note("Bluthochdruck","Arm leicht angeschwollen",c1, new Timestamp(cal.getTimeInMillis()));
        ITEMS_Karl.add(second);
        ITEMS_Karl.add(first);
        ITEMS_Karl.add(new Note("Nahrungsaufnahme", "Zahnschmerzen bei dem Kauen", c2, new Timestamp(getYesterday())));
        ITEMS_Karl.add(new Note("Wunden", "Wunde Arm eitert stark", c2, new Timestamp(getYesterday())));
    };
    public static void fillIrmgard(){
        Carer c1 = new Carer("John") ;
        Carer c2 = new Carer ("Hilde");
        Calendar cal = Calendar.getInstance();
        final int year = 2017;
        final int month = cal.FEBRUARY;
        cal.set(year, month, 7, 8, 30);
        Note first = new Note("Zähne putzen", "Ich habe dem Clienten die Zähne geputzt", c1,
                new Timestamp(cal.getTimeInMillis()));
        cal.set(year, month, 8, 8, 28);
        Note second = new Note("Bluthochdruck","Arm leicht angeschwollen",c1, new Timestamp(cal.getTimeInMillis()));
        ITEMS_Irmgard.add(second);
        ITEMS_Irmgard.add(first);
        ITEMS_Irmgard.add(new Note("Nahrungsaufnahme", "Zahnschmerzen bei dem Kauen", c2, new Timestamp(getYesterday())));
        ITEMS_Irmgard.add(new Note("Wunden", "Wunde Arm eitert stark", c2, new Timestamp(getYesterday())));
    };

    public static long getYesterday() {
        GregorianCalendar yesterday = new GregorianCalendar();
        yesterday.add(Calendar.DATE, -1);
        return yesterday.getTime().getTime();
    }

    public static void sortList(List<Note> items){
        Collections.sort(items,Collections.<Note>reverseOrder());
    }

}
