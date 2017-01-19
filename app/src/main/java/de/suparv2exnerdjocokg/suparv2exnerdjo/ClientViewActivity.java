package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.sql.Timestamp;

import de.suparv2exnerdjocokg.suparv2exnerdjo.DocumentTools.ImageActivity;
import de.suparv2exnerdjocokg.suparv2exnerdjo.DocumentTools.ImageDisplayFragment;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Documents.DoctorialPrescription1;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Documents.DoctorialPrescription2;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Documents.DoctorialPrescription3;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Documents.MoblilisationBeddingFragment;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Documents.WoundDocumentationFragment;
import de.suparv2exnerdjocokg.suparv2exnerdjo.FloatingActionBar.DialogAddNewNoteOrTask;
import de.suparv2exnerdjocokg.suparv2exnerdjo.LogBook.LogBookFragment;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Medication.MedicineOverview;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Route.Route;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Todo.ClientView;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Todo.Note;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Todo.ToDo;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Todo.TodoFragment;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Calendar;
import java.util.List;

import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyClients;
import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyNotes;
import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyToDos;

public class ClientViewActivity extends AppCompatActivity implements VitalFragment.OnFragmentInteractionListener ,BasicDataBaseFragment.OnDocumentSelectedListener, MenuFragment.OnMenuFragmentInteractionListener, TodoFragment.OnListFragmentInteractionListener, TodoFragment.OnInfoClickedInteractionListener, BasicDataBaseFragment.OnClickCall {

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
        client = DummyClients.ITEMS.get(intent.getIntExtra("CLIENT", 0));

        File f = new File(getFilesDir(), this.getString(R.string.wounddocname));
        Log.println(Log.INFO, "ACT PAth", f.getPath());
        File f1 = new File(getFilesDir(), this.getString(R.string.mobdocname));
        File f2 = new File(getFilesDir(), this.getString(R.string.doctorialprescription1));
        File f3 = new File(getFilesDir(), this.getString(R.string.doctorialprescription2));
        File f4 = new File(getFilesDir(), this.getString(R.string.doctorialprescription3));

        String filename = "myfile";
        String string = "Hello world!";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(f1.getName(),Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
            Log.println(Log.INFO,"Stream", "Schreiben erfolgreich");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<File> b = new ArrayList<>();
        b.add(f);
        b.add(f1);
        b.add(f2);
        b.add(f3);
        b.add(f4);
        client.setDocumentation(b);

// Absoluter pfad gibt in wunddokument nur einen teilpfad zurück

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
                DialogAddNewNoteOrTask dialogAddNewNoteOrTask = new DialogAddNewNoteOrTask(activity);
                dialogAddNewNoteOrTask.show();
            }
        });
    }


    @Override
    public void onListFragmentInteraction(int position, boolean done) {
        if (position != -1) {
            ClientView newFrag = (ClientView) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (newFrag != null) {
                newFrag.updateClientView(position, done);
            } else {
                newFrag = new ClientView();
                Bundle args = new Bundle();
                args.putInt(ClientView.ARG_Position, position);
                args.putBoolean("done", done);
                newFrag.setArguments(args);

                FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

                trans.replace(R.id.fragment_container, newFrag);
                trans.addToBackStack(null);

                trans.commit();
            }
        } else {
            ClientView newFrag = new ClientView();
            Bundle args = new Bundle();
            newFrag.setArguments(args);

            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

            trans.replace(R.id.fragment_container, newFrag);
            trans.addToBackStack(null);

            trans.commit();
        }
    }


    @Override
    public void onInfoClickedListener(int position, boolean done) {
        ClientView newFrag = (ClientView) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (newFrag != null) {
            newFrag.updateClientViewInfo(position, done);
        } else {
            newFrag = new ClientView();
            Bundle args = new Bundle();
            args.putInt(ClientView.ARG_Position, position);
            args.putBoolean("done", done);
            newFrag.setArguments(args);

            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

            trans.replace(R.id.fragment_container, newFrag);
            trans.addToBackStack(null);

            trans.commit();
        }
    }

    @Override
    public void onMenuFragmentInteraction(int position) {
        switch (position) {
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
                VitalFragment vF = new VitalFragment();

                trans = getSupportFragmentManager().beginTransaction();

                trans.replace(R.id.fragment_container, vF);
                trans.addToBackStack(null);

                trans.commit();
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

    public void onDocumentSelected(String position) {
        // The user selected the headline of an article from the HeadlinesFragment
        // Do something here to display that article

        Fragment wFrag = new Fragment();

        ArrayList<String> doclist= new ArrayList<>();
        doclist.add(getString(R.string.wounddocname));
        doclist.add(getString(R.string.mobdocname));
        doclist.add(getString(R.string.doctorialprescription1));
        doclist.add(getString(R.string.doctorialprescription2));
        doclist.add(getString(R.string.doctorialprescription3));

        Log.println(Log.INFO, "Name pdf file ",position);
        ArrayList<Fragment> fragList = new ArrayList<>();
        fragList.add(new WoundDocumentationFragment());
        fragList.add(new MoblilisationBeddingFragment());
        fragList.add(new DoctorialPrescription1());
        fragList.add(new DoctorialPrescription2());
        fragList.add(new DoctorialPrescription3());
        for(int i =0; i < doclist.size(); i ++){
            Log.println(Log.INFO, "Name pdf file doclist ",doclist.get(i));
            if (position.equals( doclist.get(i))){
                Log.println(Log.INFO, "Name pdf file","I#m inside");
                wFrag = fragList.get(i);
                break;
            }
        }
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.fragment_container, wFrag);
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

        Fragment newFrag = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (newFrag instanceof LogBookFragment) {
            ((LogBookFragment) newFrag).update();
        }
    }

    @Override
    public void onClickCall(String number) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + number));

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(callIntent);
    }

    @Override
    public void onDatePickerInteraction(TodoFragment frag, GeneralTask task) {
        DialogShiftTask dialogShiftTask = new DialogShiftTask(frag, this, task);
        dialogShiftTask.show();
    }

    @Override
    public void newValueAdded(int value, int id) {

        VitalFragment newFrag = (VitalFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (newFrag != null) {
            newFrag.addValue(value, id);
        } else {
            newFrag = new VitalFragment();
            Bundle args = new Bundle();
            newFrag.setArguments(args);

            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

            trans.replace(R.id.fragment_container, newFrag);
            trans.addToBackStack(null);

            trans.commit();
        }
    }


}
