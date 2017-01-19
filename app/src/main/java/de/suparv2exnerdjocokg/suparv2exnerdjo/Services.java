package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.content.Context;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by dsfd on 10.11.2016.
 */

public class Services extends ArrayList<Service>{

    private static final Services INSTANCE = new Services();

    private Services(){
        }

    public static Service getService(int complexNumber){
        //for(int index = 0; index < size(); index++){
          //  if(INSTANCE.get(index).getComplexity() == complexNumber){
            //    return INSTANCE.get(index);
            //}
        //}
        return INSTANCE.get(complexNumber);
    }

    public static int getSize(){
        return INSTANCE.size();
    }
}
