package de.suparv2exnerdjocokg.suparv2exnerdjo.DocumentTools;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;

import java.io.File;

/**
 * Created by Eko on 29.12.2016.
 */

public class PictureButton extends Button {

    static final int REQUEST_IMAGE_CAPTURE = 1;



    private File image;
    private String picPath;
    private Context context;

    public PictureButton(Context context){
        super(context);
        this.context = context;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
        this.picPath = image.getPath();
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public void openButten(){
        if (picPath == null){
            Intent intent = new Intent(context,CallCamera.class)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
        else{
            Intent intent = new Intent(context,ImageActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(picPath);

            intent.putExtra("PICTURE_ID", picPath);
            context.startActivity(intent);
        }
    }

}
