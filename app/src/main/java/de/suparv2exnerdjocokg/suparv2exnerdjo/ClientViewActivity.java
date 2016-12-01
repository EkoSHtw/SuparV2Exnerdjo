package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ClientViewActivity extends AppCompatActivity implements ClientView.OnFragmentInteractionListener, TodoFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_view);
    }

    @Override
    public void onFragmentInteraction(ToDo toDo) {

    }

    @Override
    public void onListFragmentInteraction(ToDo toDo) {

    }
}
