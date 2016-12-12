package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ClientViewActivity extends AppCompatActivity implements TodoFragment.OnListFragmentInteractionListener, TodoFragment.OnInfoClickedInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_view);
    }


    @Override
    public void onListFragmentInteraction(int position) {
        if(position != -1) {
            Log.i("", "List clicked");
            ClientView newFrag = (ClientView) getSupportFragmentManager().findFragmentById(R.id.todo);
            if (newFrag != null) {
                newFrag.updateClientView(position);
            } else {
                newFrag = new ClientView();
                Bundle args = new Bundle();
                args.putInt(ClientView.ARG_Position, position);
                newFrag.setArguments(args);

                FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

                trans.replace(R.id.todo, newFrag);
                trans.addToBackStack(null);

                trans.commit();
            }
        }else{
            ClientView newFrag = (ClientView) getSupportFragmentManager().findFragmentById(R.id.todo);
            newFrag = new ClientView();
            Bundle args = new Bundle();
            args.putInt(ClientView.ARG_Position, position);
            newFrag.setArguments(args);

            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

            trans.replace(R.id.todo, newFrag);
            trans.addToBackStack(null);

            trans.commit();
        }
    }

    @Override
    public void onInfoClickedListener(int position) {

    }
}
