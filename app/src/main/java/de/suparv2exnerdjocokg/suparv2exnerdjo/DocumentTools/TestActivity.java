package de.suparv2exnerdjocokg.suparv2exnerdjo.DocumentTools;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import de.suparv2exnerdjocokg.suparv2exnerdjo.R;

public class TestActivity extends AppCompatActivity {

    private ScrollView layMain;
    private TableGenerator mTable;
    private Button addRow;
    private Button saveIt;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private OutputStream fops;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_document_wound);


        showTable();

    }

    private void showTable() {
        mTable = new TableGenerator(getApplicationContext());
        layMain = (ScrollView) findViewById(R.id.table);

        String[] firstRow = {getString(R.string.wounddate), getString(R.string.woundphase), getString(R.string.woundsizel),
                getString(R.string.woundsizew),
                getString(R.string.woundsized), getString(R.string.woundsized),
                getString(R.string.wounddescription),
                getString(R.string.woundkindfrequency), getString(R.string.wounddescription),
                getString(R.string.hdz)};

        mTable.addHead(firstRow);
        for (int j = 0; j < 1; j++) {
            mTable.addRow();
        }


        layMain.removeAllViews();
        layMain.addView(mTable.getTable());

        addRow = (Button) findViewById(R.id.addRowB);
        addRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTable.addRow();
            }
        });


        saveIt = (Button) findViewById(R.id.saveStuff);
        saveIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = "Wounddocumentation";
                try {

                    FileOutputStream fops = openFileOutput(fileName, Context.MODE_PRIVATE);
                    Log.println(Log.INFO, "test", "Klappts???");
                    for (int i = 0; i < mTable.getIdCount(); i++) {
                        View view = mTable.getTable().getChildAt(i);
                        if (view instanceof TableRowExpand) {
                            TableRowExpand t = (TableRowExpand) view;
                            String textLine = ""+ i;

                            for (int j = 0; j <= t.getChildCount(); j++) {
                                EditText firstTextView = (EditText) t.getChildAt(j);
                                //if(firstTextView == null) break;
                                textLine += " " + "\"" + firstTextView.getText().toString() + "\"";
                                Log.println(Log.INFO, "test", "Klappts???");
                            }
                            textLine += "\n";
                            fops.write(textLine.getBytes());

                        }
                    }
                    fops.flush();
                    fops.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            ;
        });
    }

}
