package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.app.Activity;
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
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String CLIENTKEY = "client_key";

    private String mParam1;
    private String mParam2;
    private Client c;

    private OnDocumentSelectedListener mCallback;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_basic_data_base, container, false);

        if (savedInstanceState == null) {
            this.c = ((ClientViewActivity)getActivity()).getClient();
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

                adapter = new PhonenumberListAdapter(rootView.getContext(), c);
                numbers.setAdapter(adapter);
                File f = new File(getString(R.string.wounddocname));
                File f1 = new File(getString(R.string.mobdocname));
                File f2 = new File(getString(R.string.doctorialprescription1));
                File f3 = new File(getString(R.string.doctorialprescription2));
                File f4 = new File(getString(R.string.doctorialprescription3));
                ArrayList<File> b = new ArrayList<>();
                b.add(f);
                b.add(f1);
                b.add(f2);
                b.add(f3);
                b.add(f4);
                c.setDocumentation(b);

                documents = (ListView) rootView.findViewById(document_list);
                ArrayAdapter<File> arrayAdapter = new ArrayAdapter<File>(rootView.getContext(),
                        android.R.layout.simple_list_item_1,
                        c.getDocumentation());
                documents.setAdapter(arrayAdapter);
                documents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        File p = (File) parent.getItemAtPosition(position);
                        mCallback.onDocumentSelected(p);
                    }
                });
/*
                ArrayList<String> docPic = new ArrayList<>();
                String s1 = "Pfelgeplanung";
                String s2 = "Pflegeplanungskontrolle";
                String s3 = "Nachweiß Pflegevisited";
                int a1 =(R.drawable.pflegeplanung);
                int a2 = (R.drawable.pflegeplanungskontrolle);
                int a3 = (R.drawable.nachweis_pflegevisite);
*/
            }else{

            }
        }  else{
        }

        return rootView;
    }

    public ArrayList<File> getLFile() {
        Field[] fields = R.raw.class.getFields();
        ArrayList<File> s = new ArrayList<>();
        for (int count = 0; count < fields.length; count++) {
            try {
                copyFile(getResources().openRawResource(R.raw.test)
                , new FileOutputStream(new File(getContext().getFilesDir(), "download/test.pdf")));

                File pdfFile = new File(getContext().getFilesDir(), "download/test.pdf");

              s.add(pdfFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return s;
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }


    public interface OnDocumentSelectedListener {
        public void onDocumentSelected(File position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnDocumentSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
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
