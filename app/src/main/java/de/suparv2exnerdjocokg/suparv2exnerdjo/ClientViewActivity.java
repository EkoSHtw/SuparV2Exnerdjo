package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.io.File;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Documents.WoundDocumentationFragment;
import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyClients;

public class ClientViewActivity extends AppCompatActivity implements BasicDataBaseFragment.OnDocumentSelectedListener, MenuFragment.OnMenuFragmentInteractionListener, TodoFragment.OnListFragmentInteractionListener, TodoFragment.OnInfoClickedInteractionListener{

    private Client client;

    private FloatingActionButton fab;
    private PopupWindow popupWindow;
    private TextView popUpMessage;
    private FrameLayout container;
    private boolean clicked = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_view);

        if (findViewById(R.id.menu)!=null){
            MenuFragment firstFragment = new MenuFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.menu, firstFragment).commit();
        }
        if (findViewById(R.id.fragment_container)!=null){
            ClientView secondFragment = new ClientView();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, secondFragment).commit();
        }

        container = new FrameLayout(this);
        popupWindow = new PopupWindow(this);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clicked) {
                    clicked = false;
                    popupWindow.showAtLocation(findViewById(R.id.activity_client_view), Gravity.CENTER, 10, 10);
                    popupWindow.update(50, 50, 320, 90);
                } else {
                    clicked = true;
                    popupWindow.dismiss();
                }
            }
        });

        popUpMessage = new TextView(this);
        popUpMessage.setText("Hi, ich bin das Popup");

        container.addView(popUpMessage);

        popupWindow.setContentView(container);

    }


    @Override
    public void onListFragmentInteraction(int position) {
        if(position != -1) {
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
        }else{
            ClientView newFrag = (ClientView) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
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
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 1);
        }
    }
}
