package de.suparv2exnerdjocokg.suparv2exnerdjo.Documents;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.FileOutputStream;

import de.suparv2exnerdjocokg.suparv2exnerdjo.DocumentTools.TableGenerator;
import de.suparv2exnerdjocokg.suparv2exnerdjo.DocumentTools.TableRowExpand;
import de.suparv2exnerdjocokg.suparv2exnerdjo.R;

/**
 * Created by Eko on 12.12.2016.
 */

public class WoundDocumentationFragment extends Fragment {
    private ScrollView layMain;
    private TableGenerator mTable;
    private GoogleApiClient client;
    private Button addRow;
    private Button saveIt;



    private String name = getView().getContext().getString(R.string.wounddocname);

    public WoundDocumentationFragment(){}
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_document_wound, container, false);
        showTable();
        return  view;
    }

    public String getName() {
        return name;
    }

    private void showTable() {
        mTable = new TableGenerator(getView().getContext());
        layMain = (ScrollView) getView().findViewById(R.id.table);

        String[] firstRow = {getView().getContext().getString(R.string.wounddate), getView().getContext().getString(R.string.woundphase),
                getView().getContext().getString(R.string.woundsizel), getView().getContext().getString(R.string.woundsizew),
                getView().getContext().getString(R.string.woundsized), getView().getContext().getString(R.string.woundsized),
                getView().getContext().getString(R.string.wounddescription),
                getView().getContext().getString(R.string.woundkindfrequency),getView().getContext().getString(R.string.wounddescription),
                getView().getContext().getString(R.string.hdz)};

        mTable.addHead(firstRow);

        for (int j=0; j < 20; j++) {
            mTable.addRow();
        }

        layMain.removeAllViews();
        layMain.addView(mTable.getTable());
        addRow = (Button) getView().findViewById(R.id.addRowB);
        saveIt = (Button) getView().findViewById(R.id.saveStuff);
        addRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTable.addRow();
            }
        });
        saveIt = (Button) getView().findViewById(R.id.saveStuff);
        saveIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = "Wounddocumentation";
                try {

                    FileOutputStream fops = getContext().openFileOutput(fileName, Context.MODE_PRIVATE);
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
