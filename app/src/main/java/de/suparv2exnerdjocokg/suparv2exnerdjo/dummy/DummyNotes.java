package de.suparv2exnerdjocokg.suparv2exnerdjo.dummy;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Carer;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Notiz;
import de.suparv2exnerdjocokg.suparv2exnerdjo.R;

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
        ITEMS.add(new Notiz("Ganzwaschung", "Bitte immer den roten (weichen) Lappen benutzen.", new Carer("Jimmy"), new Timestamp(System.currentTimeMillis())));
        ITEMS.add(new Notiz("Teilwaschung", "Bitte immer den roten (weichen) Lappen benutzen.", new Carer("Jimmy"), new Timestamp(System.currentTimeMillis())));
        ITEMS.add(new Notiz("Hilfe bei der Nahrungsaufnahme", "Harte Speisen bitte zerkleinern", new Carer("Jimmy"), new Timestamp(System.currentTimeMillis())));
        ITEMS.add(new Notiz("Selbstständige Nahrungsaufnahme", "Harte Speisen bitte zerkleinern", new Carer("Jimmy"), new Timestamp(System.currentTimeMillis())));
        ITEMS.add(new Notiz("Nahrungsaufnahme", "Zahnschmerzen bei dem Kauen", new Carer("Timmy"), new Timestamp(getYesterday())));
        ITEMS.add(new Notiz("Haut", "Hautfarbe auffällig blass", new Carer("Timmy"), new Timestamp(getYesterday())));
        ITEMS.add(new Notiz("Wunden", "Wunde XY eitert stark", new Carer("Timmy"), new Timestamp(getYesterday())));
        ITEMS.add(new Notiz("Stimmung", "Sehr schlechte Laune", new Carer("Timmy"), new Timestamp(getYesterday())));
        ITEMS.add(new Notiz("Medikamenteneinnahme", "Problemlos", new Carer("Timmy"), new Timestamp(getYesterday())));
    }

    public static long getYesterday() {
        GregorianCalendar yesterday = new GregorianCalendar();
        yesterday.add(Calendar.DATE, -1);
        return yesterday.getTime().getTime();
    }
}
