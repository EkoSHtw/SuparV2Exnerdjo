package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_basic_data_base, container, false);


        return rootView;
    }

    public ArrayList<String> getLFile() {
        Field[] fields = R.raw.class.getFields();
        ArrayList<String> s = new ArrayList<>();
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
    public void onActivityCreated(Bundle bundle) {
       Bundle args = getArguments();
       Client c = (Client) args.getSerializable(ARG_PARAM1);
       img = (ImageView) getView().findViewById(R.id.image);
       img.setImageResource(R.drawable.monkey);
       name = (TextView)  getView().findViewById(R.id.clientname);
       name.setText(R.string.clientfirstname + " " + R.string.clientlastname);
       adress = (TextView)  getView().findViewById(R.id.adress);
       adress.setText("Adresse: " + R.string.clientAddress);
       gebdate = (TextView) getView().findViewById(R.id.gebdat);
       gebdate.setText(R.string.clientBirthdate);
       carelevel = (TextView) getView().findViewById(R.id.care_level);
       carelevel.setText("Pflegestufe: " + 2);
       infodump = (TextView) getView().findViewById(R.id.info);
       infodump.setText(R.string.concerns);
       numbers = (ListView) getView().findViewById(R.id.telnumbers);

       adapter = new PhonenumberListAdapter(getView().getContext(), bundle);
       numbers.setAdapter(adapter);


       super.onActivityCreated(bundle);
       ArrayList<String> s = getLFile();
       documents = (ListView) getView().findViewById(document_list);
       ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getView().getContext(),
               android.R.layout.simple_list_item_1,
               s );
       documents.setAdapter(arrayAdapter);
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
