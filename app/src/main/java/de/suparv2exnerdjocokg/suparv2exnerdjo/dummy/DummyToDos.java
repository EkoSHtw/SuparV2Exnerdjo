package de.suparv2exnerdjocokg.suparv2exnerdjo.dummy;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.suparv2exnerdjocokg.suparv2exnerdjo.GeneralTask;
import de.suparv2exnerdjocokg.suparv2exnerdjo.R;
import de.suparv2exnerdjocokg.suparv2exnerdjo.ToDo;

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
    public static final List<ToDo> ITEMS = new ArrayList<ToDo>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<Integer, ToDo> ITEM_MAP = new HashMap<>();


    static {
        // Add some sample items.
        ITEMS.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask(R.string.ganzwaschung, R.string.ganzwaschungDescription, R.string.ganzwaschung)));
        ITEMS.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask(R.string.teilwaschung, R.string.teilwaschungDescription, R.string.teilwaschung)));
        ITEMS.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask(R.string.nahr, R.string.nahrDescription, R.string.nahr)));
        ITEMS.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask(R.string.hNahr, R.string.hNahrDesc, R.string.hNahr)));
        ITEMS.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask(R.string.lagBet, R.string.lagBetDesc, R.string.lagBet)));ITEMS.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask(R.string.ganzwaschung, R.string.ganzwaschungDescription, R.string.ganzwaschung)));
        ITEMS.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask(R.string.teilwaschung, R.string.teilwaschungDescription, R.string.teilwaschung)));
        ITEMS.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask(R.string.nahr, R.string.nahrDescription, R.string.nahr)));
        ITEMS.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask(R.string.hNahr, R.string.hNahrDesc, R.string.hNahr)));
        ITEMS.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask(R.string.lagBet, R.string.lagBetDesc, R.string.lagBet)));
        sortTime();
    }

    public void sortAlphabet(){
    }

    public static void sortTime(){
        Collections.sort(ITEMS);
    }

}
