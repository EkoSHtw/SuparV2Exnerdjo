package de.suparv2exnerdjocokg.suparv2exnerdjo.dummy;

import android.provider.Settings;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Carer;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Todo.Note;

/**
 * Created by dsfd on 07.02.2017.
 */

public class DummyFixedNotes {

    public static final List<Note> ITEMS_Erna = new ArrayList<>();
    public static final List<Note> ITEMS_Ernst = new ArrayList<>();
    public static final List<Note> ITEMS_Helga = new ArrayList<>();
    public static final List<Note> ITEMS_Emma = new ArrayList<>();
    public static final List<Note> ITEMS_Hans = new ArrayList<>();
    public static final List<Note> ITEMS_Karl = new ArrayList<>();
    public static final List<Note> ITEMS_Irmgard = new ArrayList<>();

    static {

        fillErna();
        fillErnst();
        fillHelga();
        fillEmma();
        fillHans();
        fillKarl();
        fillIrmgard();
    }

    public static void fillErna(){
        Carer c1 = new Carer("John");
        Carer c2 = new Carer ("Hilde");
        Calendar cal = Calendar.getInstance();
        final int year = 2017;
        final int month = cal.FEBRUARY;
        int date = 6;
        cal.set(year, month, date);

        ITEMS_Erna.add(new Note("Blutdruck", "Beim Messen den linken Arm verwenden", c1, new Timestamp(cal.getTimeInMillis())));
        cal.set(year, month, 7);
        ITEMS_Erna.add(new Note("Ganzkörperwäsche", "Bitte einen Weichen Lappen zum Waschen verwenden", c2, new Timestamp(cal.getTimeInMillis())));
        cal.set(year, month, 8);
        ITEMS_Erna.add(new Note ("Flüssigkeitsbilanz", "Bei niedriger Bilanz einen kamillen Tee vorbereiten", c1, new Timestamp(cal.getTimeInMillis())));
        cal.set(year, month, 8);
        ITEMS_Erna.add(new Note("Wunden", "Verbände befinden sich oben im weißen Schrank in der Küche", c1, new Timestamp(cal.getTimeInMillis())));
        cal.set(year, month, 9);
        ITEMS_Erna.add(new Note("Hautpflege", "Für das Autragen der Haut Seni Care Feuchtigkeitsspender verwenden", c2, new Timestamp(cal.getTimeInMillis())));
    };

    public static void fillErnst(){
        Carer c1 = new Carer("John");
        Carer c2 = new Carer ("Hilde");
        Calendar cal = Calendar.getInstance();
        final int year = 2017;
        final int month = cal.FEBRUARY;
        int date = 6;
        cal.set(year, month, date);

        ITEMS_Ernst.add(new Note("Blutdruck", "Beim Messen den linken Arm verwenden", c1, new Timestamp(cal.getTimeInMillis())));
        cal.set(year, month, 7);
        ITEMS_Ernst.add(new Note("Ganzkörperwäsche", "Bitte einen Weichen Lappen zum Waschen verwenden", c2, new Timestamp(cal.getTimeInMillis())));
        cal.set(year, month, 8);
        ITEMS_Ernst.add(new Note ("Flüssigkeitsbilanz", "Bei niedriger Bilanz einen kamillen Tee vorbereiten", c1, new Timestamp(cal.getTimeInMillis())));
        cal.set(year, month, 8);
        ITEMS_Ernst.add(new Note("Wunden", "Verbände befinden sich oben im weißen Schrank in der Küche", c1, new Timestamp(cal.getTimeInMillis())));
        cal.set(year, month, 9);
        ITEMS_Ernst.add(new Note("Hautpflege", "Für das Autragen der Haut Seni Care Feuchtigkeitsspender verwenden", c2, new Timestamp(cal.getTimeInMillis())));
    };
    public static void fillHelga(){
        Carer c1 = new Carer("John");
        Carer c2 = new Carer ("Hilde");
        Calendar cal = Calendar.getInstance();
        final int year = 2017;
        final int month = cal.FEBRUARY;
        int date = 6;
        cal.set(year, month, date);

        ITEMS_Helga.add(new Note("Blutdruck", "Beim Messen den linken Arm verwenden", c1, new Timestamp(cal.getTimeInMillis())));
        cal.set(year, month, 7);
        ITEMS_Helga.add(new Note("Ganzkörperwäsche", "Bitte einen Weichen Lappen zum Waschen verwenden", c2, new Timestamp(cal.getTimeInMillis())));
        cal.set(year, month, 8);
        ITEMS_Helga.add(new Note ("Flüssigkeitsbilanz", "Bei niedriger Bilanz einen kamillen Tee vorbereiten", c1, new Timestamp(cal.getTimeInMillis())));
        cal.set(year, month, 8);
        ITEMS_Helga.add(new Note("Wunden", "Verbände befinden sich oben im weißen Schrank in der Küche", c1, new Timestamp(cal.getTimeInMillis())));
        cal.set(year, month, 9);
        ITEMS_Helga.add(new Note("Hautpflege", "Für das Autragen der Haut Seni Care Feuchtigkeitsspender verwenden", c2, new Timestamp(cal.getTimeInMillis())));
    };
    public static void fillEmma(){
        Carer c1 = new Carer("John");
        Carer c2 = new Carer ("Hilde");
        Calendar cal = Calendar.getInstance();
        final int year = 2017;
        final int month = cal.FEBRUARY;
        int date = 6;
        cal.set(year, month, date);

        ITEMS_Emma.add(new Note("Blutdruck", "Beim Messen den linken Arm verwenden", c1, new Timestamp(cal.getTimeInMillis())));
        cal.set(year, month, 7);
        ITEMS_Emma.add(new Note("Ganzkörperwäsche", "Bitte einen Weichen Lappen zum Waschen verwenden", c2, new Timestamp(cal.getTimeInMillis())));
        cal.set(year, month, 8);
        ITEMS_Emma.add(new Note ("Flüssigkeitsbilanz", "Bei niedriger Bilanz einen kamillen Tee vorbereiten", c1, new Timestamp(cal.getTimeInMillis())));
        cal.set(year, month, 8);
        ITEMS_Emma.add(new Note("Wunden", "Verbände befinden sich oben im weißen Schrank in der Küche", c1, new Timestamp(cal.getTimeInMillis())));
        cal.set(year, month, 9);
        ITEMS_Emma.add(new Note("Hautpflege", "Für das Autragen der Haut Seni Care Feuchtigkeitsspender verwenden", c2, new Timestamp(cal.getTimeInMillis())));

    };
    public static void fillHans(){
        Carer c1 = new Carer("John");
        Carer c2 = new Carer ("Hilde");
        Calendar cal = Calendar.getInstance();
        final int year = 2017;
        final int month = cal.FEBRUARY;
        int date = 6;
        cal.set(year, month, date);

        ITEMS_Hans.add(new Note("Blutdruck", "Beim Messen den linken Arm verwenden", c1, new Timestamp(cal.getTimeInMillis())));
        cal.set(year, month, 7);
        ITEMS_Hans.add(new Note("Ganzkörperwäsche", "Bitte einen Weichen Lappen zum Waschen verwenden", c2, new Timestamp(cal.getTimeInMillis())));
        cal.set(year, month, 8);
        ITEMS_Hans.add(new Note ("Flüssigkeitsbilanz", "Bei niedriger Bilanz einen kamillen Tee vorbereiten", c1, new Timestamp(cal.getTimeInMillis())));
        cal.set(year, month, 8);
        ITEMS_Hans.add(new Note("Wunden", "Verbände befinden sich oben im weißen Schrank in der Küche", c1, new Timestamp(cal.getTimeInMillis())));
        cal.set(year, month, 9);
        ITEMS_Hans.add(new Note("Hautpflege", "Für das Autragen der Haut Seni Care Feuchtigkeitsspender verwenden", c2, new Timestamp(cal.getTimeInMillis())));

    };
    public static void fillKarl(){
        Carer c1 = new Carer("John");
        Carer c2 = new Carer ("Hilde");
        Calendar cal = Calendar.getInstance();
        final int year = 2017;
        final int month = cal.FEBRUARY;
        int date = 6;
        cal.set(year, month, date);

        ITEMS_Karl.add(new Note("Blutdruck", "Beim Messen den linken Arm verwenden", c1, new Timestamp(cal.getTimeInMillis())));
        cal.set(year, month, 7);
        ITEMS_Karl.add(new Note("Ganzkörperwäsche", "Bitte einen Weichen Lappen zum Waschen verwenden", c2, new Timestamp(cal.getTimeInMillis())));
        cal.set(year, month, 8);
        ITEMS_Karl.add(new Note ("Flüssigkeitsbilanz", "Bei niedriger Bilanz einen kamillen Tee vorbereiten", c1, new Timestamp(cal.getTimeInMillis())));
        cal.set(year, month, 8);
        ITEMS_Karl.add(new Note("Wunden", "Verbände befinden sich oben im weißen Schrank in der Küche", c1, new Timestamp(cal.getTimeInMillis())));
        cal.set(year, month, 9);
        ITEMS_Karl.add(new Note("Hautpflege", "Für das Autragen der Haut Seni Care Feuchtigkeitsspender verwenden", c2, new Timestamp(cal.getTimeInMillis())));

    };
    public static void fillIrmgard(){
        Carer c1 = new Carer("John");
        Carer c2 = new Carer ("Hilde");
        Calendar cal = Calendar.getInstance();
        final int year = 2017;
        final int month = cal.FEBRUARY;
        int date = 6;
        cal.set(year, month, date);

        ITEMS_Irmgard.add(new Note("Blutdruck", "Beim Messen den linken Arm verwenden", c1, new Timestamp(cal.getTimeInMillis())));
        cal.set(year, month, 7);
        ITEMS_Irmgard.add(new Note("Ganzkörperwäsche", "Bitte einen Weichen Lappen zum Waschen verwenden", c2, new Timestamp(cal.getTimeInMillis())));
        cal.set(year, month, 8);
        ITEMS_Irmgard.add(new Note ("Flüssigkeitsbilanz", "Bei niedriger Bilanz einen kamillen Tee vorbereiten", c1, new Timestamp(cal.getTimeInMillis())));
        cal.set(year, month, 8);
        ITEMS_Irmgard.add(new Note("Wunden", "Verbände befinden sich oben im weißen Schrank in der Küche", c1, new Timestamp(cal.getTimeInMillis())));
        cal.set(year, month, 9);
        ITEMS_Irmgard.add(new Note("Hautpflege", "Für das Autragen der Haut Seni Care Feuchtigkeitsspender verwenden", c2, new Timestamp(cal.getTimeInMillis())));

    };

}
