package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.sql.Timestamp;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Medication.MedicineOverview;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Route.Route;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Todo.ClientView;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Todo.Note;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Todo.TodoFragment;
import java.io.File;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Documents.WoundDocumentationFragment;
import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyClients;
import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyNotes;

public class ClientViewActivity extends AppCompatActivity implements BasicDataBaseFragment.OnDocumentSelectedListener, MenuFragment.OnMenuFragmentInteractionListener, TodoFragment.OnListFragmentInteractionListener, TodoFragment.OnInfoClickedInteractionListener, BasicDataBaseFragment.OnClickCall {

    public Client client;
    static final int REQUEST_IMAGE_CAPTURE = 1;


    private FloatingActionButton fab;
    private Context context;
    private Activity activity = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_view);

        Intent intent = getIntent();
        client = DummyClients.ITEMS.get(intent.getIntExtra("CLIENT", 0));

        context = this;

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

    public void onDocumentSelected(File position) {
        // The user selected the headline of an article from the HeadlinesFragment
        // Do something here to display that article
        if(position.getName() == "Wunddokumentation") {
            WoundDocumentationFragment wFrag = new WoundDocumentationFragment();

            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

            trans.replace(R.id.fragment_container, wFrag);
            trans.addToBackStack(null);

            trans.commit();
        }
    }
    public void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void addNote(String content, String tag) {
//        EditText editText = (EditText)findViewById(R.id.dialog_input_text);
//        String content = editText.getText().toString();
        DummyNotes.ITEMS.add(new Note(tag, content, new Carer("John"), new Timestamp(System.currentTimeMillis())));
        DummyNotes.sortList();
        //update fragments
        // buggy, neue notizen nicht in reihenfolge
    }

    @Override
    public void onClickCall(String number) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:"+number));

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(callIntent);
    }
}
