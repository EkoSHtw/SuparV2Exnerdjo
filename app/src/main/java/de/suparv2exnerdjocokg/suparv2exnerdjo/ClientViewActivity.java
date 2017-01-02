package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Medication.MedicineOverview;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Route.Route;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Todo.ClientView;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Todo.TodoFragment;
import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyClients;

public class ClientViewActivity extends AppCompatActivity implements MenuFragment.OnMenuFragmentInteractionListener, TodoFragment.OnListFragmentInteractionListener, TodoFragment.OnInfoClickedInteractionListener{

    public Client client;

    private FloatingActionButton fab;
    private PopupWindow popupWindow;
    private TextView popUpMessage;
    private FrameLayout container;
    private boolean clicked = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_view);

        Intent intent = getIntent();
        client = (Client) DummyClients.ITEMS.get(intent.getIntExtra("CLIENT", 0));

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
}
