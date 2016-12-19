package de.suparv2exnerdjocokg.suparv2exnerdjo.DocumentTools;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

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
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_document_wound);

        showTable();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void showTable() {
        mTable = new TableGenerator(getApplicationContext());
        layMain = (ScrollView) findViewById(R.id.table);

        String[] firstRow = {getString(R.string.wounddate),getString(R.string.woundphase), getString(R.string.woundsizel),
                getString(R.string.woundsizew),
                getString(R.string.woundsized), getString(R.string.woundsized),
                getString(R.string.wounddescription),
                getString(R.string.woundkindfrequency), getString(R.string.wounddescription),
                getString(R.string.hdz)};

        mTable.addHead(firstRow);
        for (int j=0; j < 20; j++) {
            mTable.addRow();
        }


        layMain.removeAllViews();
        layMain.addView(mTable.getTable());

        addRow= (Button) findViewById(R.id.addRowB);
        addRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTable.addRow();
            }
        });
       /* saveIt = (Button) findViewById(R.id.saveStuff);
        saveIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               for()

                TableRow t = (TableRow) v;
                TextView firstTextView = (TextView) t.getChildAt(0);
                TextView secondTextView = (TextView) t.getChildAt(1);
                String firstText = firstTextView.getText().toString();
                String secondText = secondTextView.getText().toString();
            }
        });*/
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Test Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

}
