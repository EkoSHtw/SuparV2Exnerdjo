package de.suparv2exnerdjocokg.suparv2exnerdjo.DocumentTools;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;

import java.io.File;
import java.io.Serializable;
import java.net.URI;

/**
 * Created by Eko on 29.12.2016.
 */

public class PictureButton extends Button  implements Serializable{

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private Image image;
    private String picPath;
    private Context context;

    public PictureButton(Context context){
        super(context);
        this.context = context;
    }

    /*public Image getImage() {
        return image;
    }*/

    public void setImage(Image image) {
        this.image = image;
    }

    public String getImagePath() {
        return image.toString();
    }

    public String getPicPath(){
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }


   public void getPicture(){

   }

}
