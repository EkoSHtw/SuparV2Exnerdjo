package de.suparv2exnerdjocokg.suparv2exnerdjo.Route;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Client;
import de.suparv2exnerdjocokg.suparv2exnerdjo.ClientViewActivity;
import de.suparv2exnerdjocokg.suparv2exnerdjo.R;

public class Route extends AppCompatActivity implements ClientListFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        if(findViewById(R.id.clientList)!=null){
            ClientListFragment lF = new ClientListFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.clientList, lF).commit();
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
