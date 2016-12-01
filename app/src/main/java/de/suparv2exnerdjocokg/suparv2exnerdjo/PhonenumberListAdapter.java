package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eko on 22.11.2016.
 */

public class PhonenumberListAdapter extends BaseAdapter{


    private final ArrayList<PhoneNumber> numbers;
    private final LayoutInflater inflator;

    public PhonenumberListAdapter(Context context){

        inflator = LayoutInflater.from(context);

        this.numbers = new ArrayList<>();
        Client number = new Client(context);

        for(int index = 0; index<number.getPhoneNumberlenght(); index++){
            numbers.add(number.getPhoneNumber().get(index));
        }
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
        }else{

            holder = (PhonenumberListAdapter.ViewHolder) convertView.getTag();
        }

        Context context = parent.getContext();
        Client c = new Client(context);
        c.getPhoneNumber().get(position);
        PhoneNumber number = c.getPhoneNumber().get(position);;
        holder.name.setText(number.getName());

        holder.phoneNum.setText(number.getNumber());
        return convertView;
    }

    // Holder mit den entsprechenden Views des Layouts
    static class ViewHolder{
        TextView name;
        //TextView description;
        TextView phoneNum;
    }
}
