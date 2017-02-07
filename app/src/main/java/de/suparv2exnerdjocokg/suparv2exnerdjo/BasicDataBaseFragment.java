package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;

import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyClients;

import static de.suparv2exnerdjocokg.suparv2exnerdjo.R.id.document_list;
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

    private static final String CLIENTKEY = "client_key";

    private Client c;
    private Context con;

    private OnDocumentSelectedListener mCallback;
    private OnClickCall call;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_basic_data_base, container, false);

        this.con = getContext();
        if (savedInstanceState == null) {
            this.c = ((ClientViewActivity)getActivity()).getClient();
            if(c!=null) {

                img = (ImageView) rootView.findViewById(R.id.image);

                img.setImageResource(c.getImagePath());
                name = (TextView) rootView.findViewById(R.id.clientname);
                name.setText(c.getFirstName() + " " + c.getLastName());
                adress = (TextView) rootView.findViewById(R.id.adress);
                adress.setText(c.getAdress());
                gebdate = (TextView) rootView.findViewById(R.id.gebdat);
                gebdate.setText(c.getBirthDate().toString());
                carelevel = (TextView) rootView.findViewById(R.id.care_level);
                carelevel.setText(""+c.getCarelevel());
                infodump = (TextView) rootView.findViewById(R.id.info);
                infodump.setText(c.getConcerns());
                numbers = (ListView) rootView.findViewById(R.id.telnumbers);

                adapter = new PhonenumberListAdapter(rootView.getContext(), c, call);
                numbers.setAdapter(adapter);

                ArrayList<String> slist = new ArrayList<>();
                for(int i =0; i<c.getDocumentation().size(); i++){
                    String s = c.getDocumentation().get(i).getName().replace(" " + c.getFullName(), "");
                    slist.add(s);
                }
                documents = (ListView) rootView.findViewById(document_list);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(rootView.getContext(),
                        R.layout.simple_list_item,
                        slist);
                documents.setAdapter(arrayAdapter);
                documents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Object o = parent.getItemAtPosition(position);
                        String p = (String)o;
                        mCallback.onDocumentSelected(p);
                    }
                });
            }
        }  else{
        }

        return rootView;
    }


    public interface OnDocumentSelectedListener {
            void onDocumentSelected(String position);
    }

    public interface OnClickCall{
        void onClickCall(String number);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnDocumentSelectedListener) context;
            call = (OnClickCall) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }


}
