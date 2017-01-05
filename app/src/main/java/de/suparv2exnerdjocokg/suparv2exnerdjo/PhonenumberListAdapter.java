package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.util.LogPrinter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eko on 22.11.2016.
 */

public class PhonenumberListAdapter extends BaseAdapter{


    private final ArrayList<PhoneNumber> numbers;
    private final LayoutInflater inflator;
    private static final String CLIENTKEY = "client_key";
    private static BasicDataBaseFragment.OnClickCall callListener;
    private Client c;


    public PhonenumberListAdapter(Context context, Client number, BasicDataBaseFragment.OnClickCall call){
        Log.println(Log.INFO, "test","2");
        inflator = LayoutInflater.from(context);

        this.numbers = new ArrayList<>();

        this.c = number;
        for(int index = 0; index<c.getPhoneNumberlenght(); index++){
            numbers.add(c.getPhoneNumber().get(index));


        }
        callListener = call;
    }

    @Override
    public int getCount(){
        return numbers.size();
    }


    @Override
    public Object getItem(int position) {return numbers.get(position);}


    @Override
    public long getItemId(int position){return position;}


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        PhonenumberListAdapter.ViewHolder holder;

        if (convertView == null){

            convertView = inflator.inflate(R.layout.telephone_number_presentation, parent, false);

            holder = new PhonenumberListAdapter.ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.numbername);

            holder.phoneNum = (TextView) convertView.findViewById(R.id.phonenumber);
            convertView.setTag(holder);

            holder.relation = (TextView) convertView.findViewById(R.id.relation);

            holder.call = (ImageButton) convertView.findViewById(R.id.call);
        }else{

            holder = (PhonenumberListAdapter.ViewHolder) convertView.getTag();
        }

        Context context = parent.getContext();
        c.getPhoneNumber().get(position);
        final PhoneNumber number = c.getPhoneNumber().get(position);
        holder.name.setText(number.getName());
        holder.phoneNum.setText(number.getNumber());
        holder.relation.setText(number.getRelation());

        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callListener.onClickCall(number.getNumber());
            }
        });

        return convertView;
    }

    // Holder mit den entsprechenden Views des Layouts
    static class ViewHolder{
        TextView name;
        //TextView description;
        TextView phoneNum;
        TextView relation;
        ImageButton call;
    }
}
