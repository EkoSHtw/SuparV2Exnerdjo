package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyClients;

import static de.suparv2exnerdjocokg.suparv2exnerdjo.R.id.document_list;
import static de.suparv2exnerdjocokg.suparv2exnerdjo.R.id.documents;
import static de.suparv2exnerdjocokg.suparv2exnerdjo.R.string.client;

/**
 * Created by Eko on 01.12.2016.
 */

public class BasicDataBaseFragment extends Fragment {

    private View rootView;
    private PhonenumberListAdapter adapter;
    private ListView numbers;
    private ImageView img;
    private TextView name;
    private TextView adress;
    private TextView gebdate;
    private TextView carelevel;
    private TextView infodump;
    private ListView documents;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String CLIENTKEY = "client_key";

    private String mParam1;
    private String mParam2;
    private Client c;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_basic_data_base, container, false);

        if (savedInstanceState == null) {
            Client c = DummyClients.ITEMS.get(0);
            this.c = c;
            if(c!=null) {
                img = (ImageView) rootView.findViewById(R.id.image);
                img.setImageResource(R.drawable.woman_image);
                name = (TextView) rootView.findViewById(R.id.clientname);
                name.setText(c.getFirstName() + " " + c.getLastName());
                adress = (TextView) rootView.findViewById(R.id.adress);
                adress.setText("Adresse: " + c.getAdress());
                gebdate = (TextView) rootView.findViewById(R.id.gebdat);
                gebdate.setText(c.getBirthDate().toString());
                carelevel = (TextView) rootView.findViewById(R.id.care_level);
                int a = c.getCarelevel();
                carelevel.setText("Pflegestufe: " + a);
                infodump = (TextView) rootView.findViewById(R.id.info);
                infodump.setText(c.getConcerns());
                numbers = (ListView) rootView.findViewById(R.id.telnumbers);

                String b = "" + c.getPhoneNumberlenght();
                adapter = new PhonenumberListAdapter(rootView.getContext(), c);
                numbers.setAdapter(adapter);
                ArrayList<String> s = getLFile();
                documents = (ListView) rootView.findViewById(document_list);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(rootView.getContext(),
                        android.R.layout.simple_list_item_1,
                        s);
                documents.setAdapter(arrayAdapter);

                Log.println(Log.INFO, "text", "useless log");
            }else{
                Log.println(Log.INFO, "text", "C ist null");
            }
        }  else{
            Log.println(Log.INFO, "text", "args ist null");
        }

        return rootView;
    }

    public ArrayList<String> getLFile() {
        Field[] fields = R.raw.class.getFields();
        ArrayList<String> s = new ArrayList<>();
        Log.println(Log.INFO, "Länge", ""+fields.length);
        for (int count = 0; count < fields.length; count++) {
            try {

                s.add(fields[count].getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return s;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }



}
