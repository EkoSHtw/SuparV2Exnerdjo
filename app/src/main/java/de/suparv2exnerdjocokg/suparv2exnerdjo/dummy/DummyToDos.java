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

    public static void sortAlphabet(){
        Collections.sort(ITEMS, new Comparator<ToDo>() {
            @Override
            public int compare(ToDo first, ToDo second){
                return first.getTask().getName().compareTo(second.getTask().getName());
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
