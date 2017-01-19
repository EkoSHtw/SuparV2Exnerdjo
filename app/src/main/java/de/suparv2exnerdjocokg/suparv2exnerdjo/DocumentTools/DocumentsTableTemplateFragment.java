package de.suparv2exnerdjocokg.suparv2exnerdjo.DocumentTools;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Client;
import de.suparv2exnerdjocokg.suparv2exnerdjo.ClientViewActivity;
import de.suparv2exnerdjocokg.suparv2exnerdjo.R;

import static de.suparv2exnerdjocokg.suparv2exnerdjo.R.string.addRow;


public class DocumentsTableTemplateFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public DocumentsTableTemplateFragment() {
        // Required empty public constructor
    }

    private ScrollView layMain;
    private TableGenerator mTable;
    private Button addRow;
    private Button saveIt;
    private View view;
    private String[] firstRow;

    private File overwrite;
    private int index;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_document_wound, container, false);
        final Client c = ((ClientViewActivity) getActivity()).getClient();
        String[] firstRow = {view.getContext().getString(R.string.wounddate), view.getContext().getString(R.string.mobdestination),
                view.getContext().getString(R.string.mobMeasures), view.getContext().getString(R.string.mobcharacteristics),
                view.getContext().getString(R.string.mobtime),
                view.getContext().getString(R.string.hdz)};
        showTable(firstRow, getString(R.string.mobdocname), this.view, c);

        return view;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void showTable(String[] firstRow, String filename, View view, final Client c) {
        mTable = new TableGenerator(getActivity());
        layMain = (ScrollView) view.findViewById(R.id.table);

        Log.println(Log.INFO, " ", "Vor For");
        mTable.addHead(firstRow);
        for (int i = 0; i < c.docsListLenghts(); i++) {
            Log.println(Log.INFO, " ", "Nach For");

            if (c.getDocumentation().get(i).getName() == filename) {
                Log.println(Log.INFO, " ", "Nach if");
                index = i;
                this.overwrite = c.getDocumentation().get(i);
                String ret = "";
                int rowCount = 1;
                int fillCount = 0;
                String f = getContext().getFilesDir()
                        + c.getDocumentation().get(i).getAbsolutePath();
                Log.println(Log.INFO, "", f);
                mTable.addRow();
                try {

                    BufferedReader bufferedReader = new BufferedReader(
                            new FileReader(f));
                    Log.println(Log.INFO,"","HI ich bin im reader");

                    if (bufferedReader.readLine() != null) {

                        String receiveString = "";

                        while ((receiveString = bufferedReader.readLine()) != null) {
                            View v = mTable.getTable().getChildAt(rowCount);
                            if (v instanceof TableRowExpand) {
                                TableRowExpand t = (TableRowExpand) v;
                                String s = receiveString.replace("/", "");

                                EditText firstTextView = (EditText) t.getChildAt(fillCount);
                                firstTextView.setText(s);

                                if (fillCount == t.getChildCount() - 1) {
                                    mTable.addRow();
                                    fillCount = 0;
                                    rowCount++;
                                }
                            }
                        }
                        bufferedReader.close();
                    } else {
                        for (int j = 0; j < 2; j++) {
                            mTable.addwRow();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        layMain.removeAllViews();
        layMain.addView(mTable.getTable());
        addRow = (Button) view.findViewById(R.id.addRowB);
        saveIt = (Button) view.findViewById(R.id.saveStuff);
        addRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTable.addRow();
            }
        });
        saveIt = (Button) view.findViewById(R.id.saveStuff);
        saveIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    FileOutputStream fops = new FileOutputStream(overwrite);
                    for (int i = 1; i < mTable.getIdCount(); i++) {
                        View vi = mTable.getTable().getChildAt(i);
                        if (vi instanceof TableRowExpand) {
                            TableRowExpand t = (TableRowExpand) vi;
                            String textLine = "" + i;

                            for (int j = 0; j <= t.getChildCount(); j++) {
                                if (j == t.getChildCount() - 1) {
                                    PictureButton pButton = (PictureButton) t.getChildAt(j);
                                    textLine += " " + pButton.getPicPath() + "/" + "\n";
                                }
                                EditText text = (EditText) t.getChildAt(j);
                                //if(firstTextView == null) break;
                                textLine += " " + text.getText().toString() + "/" + "\n";

                            }
                            fops.write(textLine.getBytes());

                        }
                    }
                    fops.flush();
                    fops.close();
                    c.getDocumentation().set(index, overwrite);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            ;
        });
    }


}
