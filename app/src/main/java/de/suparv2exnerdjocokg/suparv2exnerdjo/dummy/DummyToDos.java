package de.suparv2exnerdjocokg.suparv2exnerdjo.dummy;

import java.sql.Timestamp;
import java.util.ArrayList;
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
    public static final Map<String, ToDo> ITEM_MAP = new HashMap<String, ToDo>();


    static {
        // Add some sample items.
        ITEMS.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask(R.string.ganzwaschung, R.string.ganzwaschungDescription, R.string.ganzwaschung)));
        ITEMS.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask(R.string.teilwaschung, R.string.teilwaschungDescription, R.string.teilwaschung)));
    }


}
