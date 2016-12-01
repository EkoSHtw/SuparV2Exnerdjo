package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.graphics.Path;
import android.os.Environment;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.path;
import static de.suparv2exnerdjocokg.suparv2exnerdjo.R.id.gebdat;


public class BasicDataActivity extends AppCompatActivity{

    SimpleCursorAdapter mAdapter;


    private PhonenumberListAdapter adapter;
    private ListView numbers;
    private ListView documents;
    private ImageView img;
    private TextView name;
    private TextView adress;
    private TextView gebdate;
    private TextView carelevel;
    private TextView infodump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_data);

        img = (ImageView) findViewById(R.id.image);
        img.setImageResource(R.drawable.monkey);
        name = (TextView) findViewById(R.id.clientname);
        name.setText(R.string.clientfirstname + " " + R.string.clientlastname);
        adress = (TextView) findViewById(R.id.adress);
        adress.setText("Adresse: " + R.string.clientAddress);
        gebdate = (TextView) findViewById(R.id.gebdat);
        gebdate.setText(R.string.clientBirthdate);
        carelevel = (TextView) findViewById(R.id.care_level);
        carelevel.setText("Pflegestufe: " + 2);
        infodump = (TextView) findViewById(R.id.info);
        infodump.setText(R.string.concerns);
        numbers = (ListView) findViewById(R.id.telnumbers);
        adapter = new PhonenumberListAdapter(this);
        numbers.setAdapter(adapter);

        ArrayList<String> s = getLFile();
        documents = (ListView) findViewById(R.id.documents);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                s );

        documents.setAdapter(arrayAdapter);
    }

    public ArrayList<String> getLFile(){
        Field[] fields=R.raw.class.getFields();
        ArrayList<String> s = new ArrayList<>();
        for(int count=0; count < fields.length; count++){
            try {
                int resourceID = fields[count].getInt(fields[count]);
                s.add(fields[count].getName());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return s;
    }


    public ArrayList<File> getListFiles(File parentDir) {
        ArrayList<File> inFiles = new ArrayList<File>();
        File[] files = parentDir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                inFiles.addAll(getListFiles(file));
            } else {
                inFiles.add(file);
            }
        }
        return inFiles;
    }
}
