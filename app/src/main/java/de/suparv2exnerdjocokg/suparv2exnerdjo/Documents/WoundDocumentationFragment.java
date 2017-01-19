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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
           /* Bundle extras = data.getExtras();
            Image image = (Image) extras.get("data");
            File  im = (File) extras.get("data");
            this.takenPicture = image;*/
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
        }
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
            if(c.getDocumentation().get(i).getName() == getString(R.string.wounddocname)){

                this.index = i;
                this.overwrite =  c.getDocumentation().get(i);
                int rowCount =1;
                int fillCount = 0;
                mTable.addwRow();
                try {
                    String empty = "";
                    String f = c.getDocumentation().get(i).getPath();


                 //   InputStream inputStream = getContext().openFileInput("/data/user/0/de.suparv2exnerdjocokg.suparv2exnerdjo/files/"+ c.getDocumentation().get(i).getPath());

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
                                        if (fillCount == t.getChildCount() -1) {
                                            PictureButton pb = (PictureButton) t.getChildAt(fillCount);

                                        }else {
                                            EditText firstTextView = (EditText) t.getChildAt(fillCount);
                                            firstTextView.setText(s);
                                        }

                                    if (fillCount == mTable.getHeadLenght()) {
                                        mTable.addwRow();
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
                mTable.addwRow();
            }
        });

            saveIt = (Button) view.findViewById(R.id.saveStuff);
            saveIt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FileOutputStream fops = null;

                    try {

                        fops = new FileOutputStream(
                                "/data/user/0/de.suparv2exnerdjocokg.suparv2exnerdjo/files/"
                                        + overwrite.getName() +".txt", false);

                        for (int i = 1; i < mTable.getIdCount(); i++) {
                            View view = mTable.getTable().getChildAt(i);
                            if (view instanceof TableRowExpand) {
                                TableRowExpand t = (TableRowExpand) view;
                                String textLine = "" + i;

                                for (int j = 0; j < t.getChildCount(); j++) {
                                    if(j == t.getChildCount() -1){
                                        PictureButton pButton = (PictureButton) t.getChildAt(j);
                                        textLine += " "  + pButton.getPicPath() + "/" + "\n";
                                    }else {
                                        EditText text = (EditText) t.getChildAt(j);
                                        //if(firstTextView == null) break;
                                        textLine += " " + text.getText().toString() + "/" + "\n";
                                    }
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
