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


    static {
        // Add some sample items.
        ITEMS.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask(R.string.ganzwaschung, R.string.ganzwaschungDescription, R.string.ganzwaschung)));
        ITEMS.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask(R.string.teilwaschung, R.string.teilwaschungDescription, R.string.teilwaschung)));
        ITEMS.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask(R.string.nahr, R.string.nahrDescription, R.string.nahr)));
        ITEMS.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask(R.string.hNahr, R.string.hNahrDesc, R.string.hNahr)));
        ITEMS.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask(R.string.lagBet, R.string.lagBetDesc, R.string.lagBet)));
        ITEMS.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask(R.string.ganzwaschung, R.string.ganzwaschungDescription, R.string.ganzwaschung)));
        ITEMS.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask(R.string.teilwaschung, R.string.teilwaschungDescription, R.string.teilwaschung)));
        ITEMS.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask(R.string.nahr, R.string.nahrDescription, R.string.nahr)));
        ITEMS.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask(R.string.hNahr, R.string.hNahrDesc, R.string.hNahr)));
        ITEMS.add(new ToDo(new Timestamp(System.currentTimeMillis()), new GeneralTask(R.string.lagBet, R.string.lagBetDesc, R.string.lagBet)));
    }

    public static void sortAlphabet(final Context c){
        Collections.sort(ITEMS, new Comparator<ToDo>() {
            @Override
            public int compare(ToDo first, ToDo second){
                return first.getTask().getName(c).compareTo(second.getTask().getName(c));
            }
        });
        List<ToDo> done = new ArrayList<>();
        for(int i = 0; i < ITEMS.size(); i++){
            if(ITEMS.get(i).getTask().isDone()){
                done.add(ITEMS.get(i));
                ITEMS.remove(i);
                i = i-1;
            }
        }
        for(int i = 0; i < done.size(); i++){
            ITEMS.add(done.get(i));
        }
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
