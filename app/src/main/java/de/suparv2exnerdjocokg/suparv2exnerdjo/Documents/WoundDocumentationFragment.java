package de.suparv2exnerdjocokg.suparv2exnerdjo.Documents;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Client;
import de.suparv2exnerdjocokg.suparv2exnerdjo.ClientViewActivity;
import de.suparv2exnerdjocokg.suparv2exnerdjo.DocumentTools.PictureButton;
import de.suparv2exnerdjocokg.suparv2exnerdjo.DocumentTools.TableGenerator;
import de.suparv2exnerdjocokg.suparv2exnerdjo.DocumentTools.TableRowExpand;
import de.suparv2exnerdjocokg.suparv2exnerdjo.R;
import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyClients;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Eko on 12.12.2016.
 */

public class WoundDocumentationFragment extends Fragment{
    private ScrollView layMain;
    private TableGenerator mTable;
    private Button addRow;
    private Button saveIt;
    private View view;
    private Client c;
    private File overwrite;
    private int index;
    private String mCurrentPhotoPath;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_TAKE_PHOTO = 0;


    public WoundDocumentationFragment(){}
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

        String[] firstRow = {view.getContext().getString(R.string.wounddate), view.getContext().getString(R.string.woundphase),
                view.getContext().getString(R.string.woundsizel), view.getContext().getString(R.string.woundsizew),
                view.getContext().getString(R.string.woundsized), view.getContext().getString(R.string.woundsized),
                view.getContext().getString(R.string.wounddescription),
                view.getContext().getString(R.string.woundkindfrequency), view.getContext().getString(R.string.wounddescription),
                view.getContext().getString(R.string.hdz), view.getContext().getString(R.string.picture)};

        mTable.addHead(firstRow);
        for (int i =0; i < c.docsListLenghts(); i++){
            if(c.getDocumentation().get(i).getName().equals( getString(R.string.wounddocname) + " " + c.getFullName())){
                this.index = i;
                this.overwrite =  c.getDocumentation().get(i);
                int rowCount =1;
                int fillCount = 0;

                try {
                    String empty = "";
                    String f = c.getDocumentation().get(i).getPath();

                    BufferedReader bufferedReader = new BufferedReader(
                            new FileReader(f ));
                    if ( bufferedReader.readLine() != null ) {
                        mTable.addwRow();

                        String receiveString = "";

                                while ((receiveString = bufferedReader.readLine()) != null) {
                                    View view = mTable.getTable().getChildAt(rowCount);
                                    if (view instanceof TableRowExpand) {
                                        TableRowExpand t = (TableRowExpand) view;
                                        String s = receiveString.replace("/", "");
                                        if (fillCount == mTable.getHeadLenght()) {
                                            PictureButton pb = (PictureButton) t.getChildAt(fillCount);
                                            pb.setPicPath(s);
                                            pb.setText(getString(R.string.showpicture));
                                            fillCount++;
                                        }else {
                                            EditText firstTextView = (EditText) t.getChildAt(fillCount);
                                            firstTextView.setText(s);
                                            fillCount++;
                                        }

                                    if (fillCount == t.getChildCount()) {
                                        mTable.addwRow();
                                        fillCount =0;
                                        rowCount++;
                                    }
                                }
                            }
                        bufferedReader.close();
                    }else{
                        bufferedReader.close();
                            mTable.addwRow();
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
                mTable.addwRow();
            }
        });

            saveIt = (Button) view.findViewById(R.id.saveStuff);
            saveIt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FileOutputStream outputStream = null;

                    try {

                        outputStream = new FileOutputStream(overwrite);
                        OutputStreamWriter myOutWriter = new OutputStreamWriter(outputStream);
                        for (int i = 1; i < mTable.getHeadLenght(); i++) {
                            View view = mTable.getTable().getChildAt(i);
                            if (view instanceof TableRowExpand) {
                                TableRowExpand t = (TableRowExpand) view;
                                String textLine = "";
                                myOutWriter.write(" " + "/" + "\n");
                                for (int j = 0; j < mTable.getHeadLenght(); j++) {
                                    if(j == mTable.getHeadLenght()){
                                        PictureButton pButton = (PictureButton) t.getChildAt(j);
                                        textLine += " "  + pButton.getPicPath() + "/" + "\n";
                                    }else {
                                        EditText text = (EditText) t.getChildAt(j);
                                        //if(firstTextView == null) break;
                                        textLine += " " + text.getText().toString() + "/" + "\n";
                                    }
                                }
                                myOutWriter.write(textLine);
                                myOutWriter.flush();
                                outputStream.flush();
                            }
                        }
                        myOutWriter.close();
                        outputStream.close();
                        Context context = getContext();
                        CharSequence text = getString(R.string.saved);
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            });
        }

}