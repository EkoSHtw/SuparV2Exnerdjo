package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Documents.DoctorialPrescription1;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Documents.DoctorialPrescription2;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Documents.DoctorialPrescription3;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Documents.MobilisationBeddingFragment;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Documents.WoundDocumentationFragment;
import de.suparv2exnerdjocokg.suparv2exnerdjo.FloatingActionButton.DialogAddNewNoteOrTask;
import de.suparv2exnerdjocokg.suparv2exnerdjo.LogBook.LogBookFragment;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Medication.MedicineOverview;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Route.Route;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Todo.ClientView;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Todo.Note;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Todo.TodoFragment;
import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyClients;
import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyNotes;

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

        File f = new File(getFilesDir(), this.getString(R.string.wounddocname)+ " "  + client.getFullName());
        File f1 = new File(getFilesDir(), this.getString(R.string.mobdocname) + " " + client.getFullName());

        File f2 = new File(getFilesDir(), this.getString(R.string.doctorialprescription1) + " " +client.getFullName());
        File f3 = new File(getFilesDir(), this.getString(R.string.doctorialprescription2) + " " + client.getFullName());
        File f4 = new File(getFilesDir(), this.getString(R.string.doctorialprescription3) + " " + client.getFullName());

        ArrayList<File> b = new ArrayList<>();
        b.add(f);
        b.add(f1);
        b.add(f2);
        b.add(f3);
        b.add(f4);

        try{
            for(int i =0; i < b.size(); i++){
                if(b.get(i).exists() != true){
                    b.get(i).createNewFile();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        client.setDocumentation(b);

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

                trans.commit();
            }
        } else {
            ClientView newFrag = new ClientView();
            Bundle args = new Bundle();
            newFrag.setArguments(args);

            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

            trans.replace(R.id.fragment_container, newFrag);

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

                trans.commit();
                break;
            case 1:
                BasicDataBaseFragment basicFragment = new BasicDataBaseFragment();

                trans = getSupportFragmentManager().beginTransaction();

                trans.replace(R.id.fragment_container, basicFragment);

                trans.commit();
                break;
            case 2:
                LogBookFragment logFragment = new LogBookFragment();

                trans = getSupportFragmentManager().beginTransaction();

                trans.replace(R.id.fragment_container, logFragment);

                trans.commit();
                break;
            case 3:
                VitalFragment vF = new VitalFragment();

                trans = getSupportFragmentManager().beginTransaction();

                trans.replace(R.id.fragment_container, vF);

                trans.commit();
                break;
            case 4:
                MedicineOverview medicineFrag = new MedicineOverview();

                trans = getSupportFragmentManager().beginTransaction();
                trans.replace(R.id.fragment_container, medicineFrag);

                trans.commit();

                break;
            case 5:
                Intent logOut = new Intent(this, LogIn.class);
                logOut.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(logOut);
                this.finish();

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

        ArrayList<Fragment> fragList = new ArrayList<>();
        fragList.add(new WoundDocumentationFragment());
        fragList.add(new MobilisationBeddingFragment());
        fragList.add(new DoctorialPrescription1());
        fragList.add(new DoctorialPrescription2());
        fragList.add(new DoctorialPrescription3());
        for(int i =0; i < doclist.size(); i ++){
            if (position.equals(doclist.get(i))){
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
    public void newValueAdded(int value, int second, int id) {

        VitalFragment newFrag = (VitalFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (newFrag != null) {
            newFrag.addValue(value, second, id);
        } else {
            newFrag = new VitalFragment();
            Bundle args = new Bundle();
            newFrag.setArguments(args);

            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

            trans.replace(R.id.fragment_container, newFrag);

            trans.commit();
        }
    }


}
