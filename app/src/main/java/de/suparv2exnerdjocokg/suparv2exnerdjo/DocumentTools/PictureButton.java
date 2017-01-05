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


    private boolean addedpicAdded = false;
    private File image;



    private String picPath;
    private Context context;

    public PictureButton(Context context){
        super(context);
        this.context = context;
    }
    public boolean isPicAdded() {
        return addedpicAdded;
    }

    public void setpicAdded(boolean addedpicAdded) {
        this.addedpicAdded = addedpicAdded;
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

}
