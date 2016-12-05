package de.suparv2exnerdjocokg.suparv2exnerdjo.dummy;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Carer;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Notiz;

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
    public static final List<Notiz> ITEMS = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Notiz> ITEM_MAP = new HashMap<>();


    static {
       ITEMS.add(new Notiz("Tag1", "Hi, ich bin eine  seeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeehr lange Notiz", new Carer("Jimmy"), new Timestamp(System.currentTimeMillis())));
        ITEMS.add(new Notiz("Tag2", "Hi, ich bin noch eine Notiz", new Carer("Timmy"), new Timestamp(System.currentTimeMillis())));
    }

}
