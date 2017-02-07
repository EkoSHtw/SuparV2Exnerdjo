package de.suparv2exnerdjocokg.suparv2exnerdjo.dummy;

import java.sql.Timestamp;
import java.util.ArrayList;
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
        ITEMS_Erna.add(new Note("Blutdruck", "test1", new Carer("John"), new Timestamp(System.currentTimeMillis())));



    };

    public static void fillErnst(){};
    public static void fillHelga(){};
    public static void fillEmma(){};
    public static void fillHans(){};
    public static void fillKarl(){};
    public static void fillIrmgard(){};

}
