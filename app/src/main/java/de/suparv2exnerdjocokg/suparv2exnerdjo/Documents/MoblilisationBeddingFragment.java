package de.suparv2exnerdjocokg.suparv2exnerdjo.Documents;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Client;
import de.suparv2exnerdjocokg.suparv2exnerdjo.ClientViewActivity;
import de.suparv2exnerdjocokg.suparv2exnerdjo.DocumentTools.ImageDisplayFragment;
import de.suparv2exnerdjocokg.suparv2exnerdjo.DocumentTools.PictureButton;
import de.suparv2exnerdjocokg.suparv2exnerdjo.DocumentTools.TableGenerator;
import de.suparv2exnerdjocokg.suparv2exnerdjo.DocumentTools.TableRowExpand;
import de.suparv2exnerdjocokg.suparv2exnerdjo.R;
import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyClients;

public class MoblilisationBeddingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageDisplayFragment.OnFragmentInteractionListener mListener;

    public MoblilisationBeddingFragment() {
        // Required empty public constructor
    }
    private ScrollView layMain;
    private TableGenerator mTable;
    private Button addRow;
    private Button saveIt;
    private View view;
    private Client c;
    private File overwrite;
    private int index;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view= inflater.inflate(R.layout.fragment_document_wound, container, false);
        this.c = ((ClientViewActivity)getActivity()).getClient();
        showTable();

        return  view;
    }

    private void showTable() {
        mTable = new TableGenerator(getActivity());
        layMain = (ScrollView) view.findViewById(R.id.table);

        String[] firstRow = {view.getContext().getString(R.string.wounddate), view.getContext().getString(R.string.mobdestination),
                view.getContext().getString(R.string.mobMeasures), view.getContext().getString(R.string.mobcharacteristics),
                view.getContext().getString(R.string.mobtime),
                view.getContext().getString(R.string.hdz)};

        mTable.addHead(firstRow);
        for (int i =0; i < c.docsListLenghts(); i++){
            if(c.getDocumentation().get(i).getName() == getString(R.string.mobdocname)){
                index = i;
                this.overwrite =  c.getDocumentation().get(i);
                String ret = "";
                int rowCount =1;
                int fillCount = 0;
                String f = c.getDocumentation().get(i).getPath();
                mTable.addRow();
                try {

                    BufferedReader bufferedReader = new BufferedReader(
                            new FileReader("/data/user/0/de.suparv2exnerdjocokg.suparv2exnerdjo/files/"
                                    + f +".txt"));
                    if ( bufferedReader.readLine() != null ) {

                        String receiveString = "";

                        while ((receiveString = bufferedReader.readLine()) != null) {
                            View view = mTable.getTable().getChildAt(rowCount);
                            if (view instanceof TableRowExpand) {
                                TableRowExpand t = (TableRowExpand) view;
                                String s = receiveString.replace("/", "");

                                    EditText firstTextView = (EditText) t.getChildAt(fillCount);
                                    firstTextView.setText(s);

                                if (fillCount == mTable.getHeadLenght()) {
                                    mTable.addRow();
                                    fillCount =0;
                                    rowCount++;
                                }
                            }

                            bufferedReader.close();
                        }

                    }else{
                        for (int j = 0; j < 2; j++) {
                            mTable.addwRow();
                        }
                    }
                }catch (Exception e){
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
                        View view = mTable.getTable().getChildAt(i);
                        if (view instanceof TableRowExpand) {
                            TableRowExpand t = (TableRowExpand) view;
                            String textLine = "" + i;

                            for (int j = 0; j <= t.getChildCount(); j++) {
                                if(j == mTable.getHeadLenght()){
                                    PictureButton pButton = (PictureButton) t.getChildAt(j);
                                    textLine += " "  + pButton.getPicPath() + "/" + "\n";
                                }
                                EditText text= (EditText) t.getChildAt(j);
                                //if(firstTextView == null) break;
                                textLine += " "  + text.getText().toString() + "/" + "\n";

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
