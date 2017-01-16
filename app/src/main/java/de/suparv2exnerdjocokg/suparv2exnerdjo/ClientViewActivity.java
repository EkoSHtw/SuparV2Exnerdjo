package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.sql.Timestamp;

import de.suparv2exnerdjocokg.suparv2exnerdjo.DocumentTools.ImageActivity;
import de.suparv2exnerdjocokg.suparv2exnerdjo.DocumentTools.ImageDisplayFragment;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Documents.DoctorialPrescription1;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Documents.DoctorialPrescription2;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Documents.DoctorialPrescription3;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Documents.MoblilisationBeddingFragment;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Medication.MedicineOverview;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Route.Route;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Todo.ClientView;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Todo.Note;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Todo.TodoFragment;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Documents.WoundDocumentationFragment;
import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyClients;
import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyNotes;


public class ClientViewActivity extends AppCompatActivity implements BasicDataBaseFragment.OnDocumentSelectedListener, MenuFragment.OnMenuFragmentInteractionListener, TodoFragment.OnListFragmentInteractionListener, TodoFragment.OnInfoClickedInteractionListener {



    public Client client;

    private FloatingActionButton fab;
    private Activity activity = this;
    private Image takenPicture;
    private File imPic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_view);

        Intent intent = getIntent();
        client = (Client) DummyClients.ITEMS.get(intent.getIntExtra("CLIENT", 0));
        File f = new File(getFilesDir(), getString(R.string.wounddocname));
        File f1 = new File(getFilesDir(),getString(R.string.mobdocname));
        File f2 = new File(getFilesDir(),getString(R.string.doctorialprescription1));
        File f3 = new File(getFilesDir(),getString(R.string.doctorialprescription2));
        File f4 = new File(getFilesDir(),getString(R.string.doctorialprescription3));
        ArrayList<File> b = new ArrayList<>();
        b.add(f);
        b.add(f1);
        b.add(f2);
        b.add(f3);
        b.add(f4);
        client.setDocumentation(b);


//        setContentView(R.layout.dialog_add_note);

        if (findViewById(R.id.menu) != null) {
            MenuFragment firstFragment = new MenuFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.menu, firstFragment).commit();
        }
        if (findViewById(R.id.fragment_container) != null) {
            ClientView secondFragment = new ClientView();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, secondFragment).commit();
        }


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DialogAddNote dialogAddNote = new DialogAddNote(activity);
                dialogAddNote.show();
            }
        });
    }


    @Override
    public void onListFragmentInteraction(int position) {
        if (position != -1) {
            ClientView newFrag = (ClientView) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (newFrag != null) {
                newFrag.updateClientView(position);
            } else {
                newFrag = new ClientView();
                Bundle args = new Bundle();
                args.putInt(ClientView.ARG_Position, position);
                newFrag.setArguments(args);

                FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

                trans.replace(R.id.fragment_container, newFrag);
                trans.addToBackStack(null);

                trans.commit();
            }
        } else {
            ClientView newFrag = (ClientView) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            newFrag = new ClientView();//??
            Bundle args = new Bundle();
            args.putInt(ClientView.ARG_Position, position);
            newFrag.setArguments(args);

            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

            trans.replace(R.id.fragment_container, newFrag);
            trans.addToBackStack(null);

            trans.commit();
        }
    }

    @Override
    public void onInfoClickedListener(int position) {
        ClientView newFrag = (ClientView) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (newFrag != null) {
            newFrag.updateClientViewInfo(position);
        } else {
            newFrag = new ClientView();
            Bundle args = new Bundle();
            args.putInt(ClientView.ARG_Position, position);
            newFrag.setArguments(args);

            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

            trans.replace(R.id.fragment_container, newFrag);
            trans.addToBackStack(null);

            trans.commit();
        }
    }

    @Override
    public void onMenuFragmentInteraction(int position) {
        switch(position) {
            case -1:
                Intent route = new Intent(this, Route.class);
                startActivity(route);
                break;
            case 0:
                ClientView clientFragment = new ClientView();

                FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

                trans.replace(R.id.fragment_container, clientFragment);
                trans.addToBackStack(null);

                trans.commit();
                break;
            case 1:
                BasicDataBaseFragment basicFragment = new BasicDataBaseFragment();

                trans = getSupportFragmentManager().beginTransaction();

                trans.replace(R.id.fragment_container, basicFragment);
                trans.addToBackStack(null);

                trans.commit();
                break;
            case 2:
                LogBookFragment logFragment = new LogBookFragment();

                trans = getSupportFragmentManager().beginTransaction();

                trans.replace(R.id.fragment_container, logFragment);
                trans.addToBackStack(null);

                trans.commit();
                break;
            case 3:

                break;
            case 4:
                MedicineOverview medicineFrag = new MedicineOverview();

                trans = getSupportFragmentManager().beginTransaction();
                trans.replace(R.id.fragment_container, medicineFrag);
                trans.addToBackStack(null);

                trans.commit();

                break;
            case 5:
                Intent logOut = new Intent(this, LogIn.class);
                startActivity(logOut);
        }
    }

    public Client getClient() {
        return client;
    }

    public void onDocumentSelected(File position) {
        // The user selected the headline of an article from the HeadlinesFragment
        // Do something here to display that article
        Fragment wFrag = new Fragment();
        String name = position.getName();

        ArrayList<String> doclist= new ArrayList<>();
        doclist.add(getString(R.string.wounddocname));
        doclist.add(getString(R.string.mobdocname));
        doclist.add(getString(R.string.doctorialprescription1));
        doclist.add(getString(R.string.doctorialprescription2));
        doclist.add(getString(R.string.doctorialprescription3));

        ArrayList<Fragment> fragList = new ArrayList<>();
        fragList.add(new WoundDocumentationFragment());
        fragList.add(new MoblilisationBeddingFragment());
        fragList.add(new DoctorialPrescription1());
        fragList.add(new DoctorialPrescription2());
        fragList.add(new DoctorialPrescription3());
        Log.println(Log.INFO, "2", "Kommt Durch");
        Log.println(Log.INFO, "2", name);
        for(int i =0; i < doclist.size(); i ++){
            if (name == doclist.get(i)){
                wFrag = fragList.get(i);
                break;
            }
        }
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.fragment_container, wFrag);
        trans.addToBackStack(null);

        trans.commit();
    }



    String mCurrentPhotoPath;



    public void showImage(String s){
        Intent intent = new Intent (this, ImageActivity.class);
        intent.putExtra(s,0);
        startActivity(intent);
    }



    public Image getTakenPicture(){
        return this.takenPicture;
    }

    public void setPicture(String path){
        ImageDisplayFragment f = new ImageDisplayFragment();
        Bundle args = new Bundle();
        args.putString("path", path);
        f.setArguments(args);
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

        trans.replace(R.id.fragment_container, f);
        trans.addToBackStack(null);

        trans.commit();
    }


    public void addNote(String content, String tag) {
//        EditText editText = (EditText)findViewById(R.id.dialog_input_text);
//        String content = editText.getText().toString();
        DummyNotes.ITEMS.add(new Note(tag, content, new Carer("John"), new Timestamp(System.currentTimeMillis())));
        DummyNotes.sortList();
        //update fragments
        // buggy, neue notizen nicht in reihenfolge
    }


}
