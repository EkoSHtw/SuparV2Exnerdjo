package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static de.suparv2exnerdjocokg.suparv2exnerdjo.R.string.clientlastname;

/**
 * Created by Eko on 01.12.2016.
 */

public class BasicDataFragment extends Fragment {


    private PhonenumberListAdapter adapter;
    private ListView numbers;
    private ImageView img;
    private TextView name;
    private TextView adress;
    private TextView gebdate;
    private TextView carelevel;
    private TextView infodump;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_basic_data, container, false);
    }


    @Override
    public void onActivityCreated(Bundle bundle){

    super.onActivityCreated(bundle);
    img = (ImageView) getView().findViewById(R.id.image);
    img.setImageResource(R.drawable.monkey);
    name = (TextView)  getView().findViewById(R.id.clientname);
    name.setText( getView().getContext().getString(R.string.clientfirstname) + " " + getView().getContext().getString(R.string.clientlastname));
    adress = (TextView)  getView().findViewById(R.id.adress);
    adress.setText("Adresse: " + R.string.clientAddress);
    gebdate = (TextView) getView().findViewById(R.id.gebdat);
    gebdate.setText(R.string.clientBirthdate);
    carelevel = (TextView) getView().findViewById(R.id.care_level);
    carelevel.setText("Pflegestufe: " + 2);
    infodump = (TextView) getView().findViewById(R.id.info);
    infodump.setText(R.string.concerns);
    numbers = (ListView) getView().findViewById(R.id.telnumbers);

}

}
