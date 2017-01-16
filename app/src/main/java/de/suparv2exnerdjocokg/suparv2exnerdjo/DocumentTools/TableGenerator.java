package de.suparv2exnerdjocokg.suparv2exnerdjo.DocumentTools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.suparv2exnerdjocokg.suparv2exnerdjo.ClientViewActivity;
import de.suparv2exnerdjocokg.suparv2exnerdjo.R;

import static android.app.Activity.RESULT_OK;
import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static de.suparv2exnerdjocokg.suparv2exnerdjo.DocumentTools.PictureButton.REQUEST_IMAGE_CAPTURE;

/**
 * Created by Eko on 08.12.2016.
 */

public class TableGenerator  {
    private final Context mContext;
    private TableLayout mTable;
    private FrameLayout mFrame;
    private TableLayout.LayoutParams rowParams = new TableLayout.LayoutParams();
    private TableRow.LayoutParams colParams = new TableRow.LayoutParams();
    private Activity act;
    private String mCurrentPhotoPath;
    private Image currentPhoto;
    private static final int REQUEST_TAKE_PHOTO = 1;
    private PictureButton currentButton;



    public int getHeadLenght() {
        return headLenght;
    }

    private int headLenght;

    public int getIdCount() {
        return idCount;
    }

    private int idCount = 0;
    public TableGenerator(Activity context) {
        mContext = context.getApplicationContext();
        act = context;
        mTable = new TableLayout(context);
        mFrame = new FrameLayout(context);
        rowParams.setMargins(0, 0, 0, 1);
        colParams.setMargins(0, 0, 1, 0);

        TableLayout.LayoutParams lptable = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT);
        FrameLayout.LayoutParams frlay= new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);
        mTable.setLayoutParams(lptable);
        mFrame.setLayoutParams(frlay);
        mTable.setStretchAllColumns(true);
        mTable.setBackgroundColor(mContext.getResources().getColor(
                R.color.table_background));
    }

    public void addwRow() {
        TableRowExpand tr = new TableRowExpand(mContext);
        tr.setId(idCount);

        tr.setBackgroundColor(mContext.getResources().getColor(
                R.color.table_background));
        tr.setLayoutParams(rowParams);

        for (int iCol = 0; iCol < headLenght; iCol++) {
            if(iCol == headLenght-1){
                final PictureButton pb = new PictureButton(mContext);
                pb.setGravity(Gravity.CENTER | Gravity.CENTER);
                pb.setPadding(3, 3, 3, 3);
                pb.setTextColor(mContext.getResources().getColor(
                        R.color.black));
                pb.setMaxWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                pb.setMaxHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                pb.setLayoutParams(colParams);
                pb.setBackgroundColor(mContext.getResources().getColor(
                        R.color.row_background));
                pb.setPicPath("");
               if (pb.getPicPath() != ""){
                   pb.setText("Bild anzeigen");
               }else{
                    pb.setText("Bild hinzufügen");
                }

                pb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(pb.getPicPath() == ""){
                        currentButton = pb;

                            // Bild wird aufgenommen
                       dispatchTakePictureIntent();

                        }
                        else{

                            Intent intent = new Intent(mContext,
                                    ImageActivity.class);
                            String message = pb.getPicPath();
                            intent.putExtra(EXTRA_MESSAGE, message);
                            mContext.startActivity(intent);

                        }
                    }
                });
                tr.addView(pb);
            }else {
            EditText tvCol = new EditText(mContext);
            tvCol.setGravity(Gravity.CENTER | Gravity.CENTER);
            tvCol.setPadding(3, 3, 3, 3);
            tvCol.setTextColor(mContext.getResources().getColor(
                    R.color.black));
            tvCol.setMaxWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            tvCol.setLayoutParams(colParams);
            tvCol.setBackgroundColor(mContext.getResources().getColor(
                    R.color.row_background));
            tr.addView(tvCol);
        }
        }

        mTable.addView(tr);
        idCount++;
    }

    public void addRow() {
        TableRowExpand tr = new TableRowExpand(mContext);
        tr.setId(idCount);

        tr.setBackgroundColor(mContext.getResources().getColor(
                R.color.table_background));
        tr.setLayoutParams(rowParams);
        for (int iCol = 0; iCol < headLenght; iCol++) {
                EditText tvCol = new EditText(mContext);
            tvCol.setGravity(Gravity.CENTER | Gravity.CENTER);
            tvCol.setPadding(3, 3, 3, 3);
            tvCol.setTextColor(mContext.getResources().getColor(
                    R.color.black));
            tvCol.setLayoutParams(colParams);
            tvCol.setBackgroundColor(mContext.getResources().getColor(
                    R.color.row_background));
            tr.addView(tvCol);
        }

        mTable.addView(tr);
    }


    public void addHead(String[] data) {
        TableRow tr = new TableRow(mContext);
        tr.setBackgroundColor(mContext.getResources().getColor(
                R.color.table_background));
        headLenght= data.length;
        tr.setLayoutParams(rowParams);

        for (int iCol = 0; iCol < data.length; iCol++) {
            TextView tvCol = new TextView(mContext);
            tvCol.setText(data[iCol]);
            tvCol.setGravity(Gravity.CENTER | Gravity.CENTER);
            tvCol.setPadding(3, 3, 3, 3);
            tvCol.setTextColor(mContext.getResources().getColor(
                    R.color.black));
            tvCol.setLayoutParams(colParams);
            tvCol.setBackgroundColor(mContext.getResources().getColor(
                    R.color.row_background));
            tr.addView(tvCol);
        }

        mTable.addView(tr);
    }


    public TableLayout getTable() {
        return mTable;
    }



    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(act.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
              ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(mContext,
                        "de.suparv2exnerdjo.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                act.startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = act.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        currentButton.setPicPath(mCurrentPhotoPath);
        return image;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            currentPhoto = (Image) extras.get("data");
        }
    }


}


