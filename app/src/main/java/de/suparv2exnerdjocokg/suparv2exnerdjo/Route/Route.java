package de.suparv2exnerdjocokg.suparv2exnerdjo.Route;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Date;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Client;
import de.suparv2exnerdjocokg.suparv2exnerdjo.ClientViewActivity;
import de.suparv2exnerdjocokg.suparv2exnerdjo.R;
import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyClients;

public class Route extends AppCompatActivity implements ClientListFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar1);

        Date currentTime = new Date();
        //currentTime.setHours(10);
        int hour = currentTime.getHours() - 8;
        int minute = currentTime.getMinutes();

        double progress = hour+((double)minute/60);

        progressBar.setMax(800);
        progressBar.setProgress((int)(progress*100));

        if(findViewById(R.id.client_list)!=null){
            ClientListFragment lF = new ClientListFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.client_list, lF).commit();
        }
    }

    @Override
    public void onListFragmentInteraction(int position) {
        Intent intent = new Intent(this, ClientViewActivity.class);
        intent.putExtra("CLIENT", position);
        startActivity(intent);
    }

    @Override
    public void onRouteInteraction(String address){
        Uri mapsLink = Uri.parse("google.navigation:q="+address);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapsLink);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}
