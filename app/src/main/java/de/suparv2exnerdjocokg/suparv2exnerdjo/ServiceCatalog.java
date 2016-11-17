package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ServiceCatalog extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ServiceAdapter adapter;
    private ListView serviceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_catalog_around);

        serviceList = (ListView) findViewById(R.id.serviceList);
        // hier werden die Service gespeichert
        adapter = new ServiceAdapter(this);
        serviceList.setAdapter(adapter);
        // auf das Antippen von Listelementen reagieren
        serviceList.setOnItemClickListener(this);
        //setContentView(R.layout.activity_service_catalog);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        Service service = (Service) adapter.getItem(position);
        // define what happens onClick
        Context context = parent.getContext();
        Intent intent = new Intent(this, ServiceDetail.class);
        intent.putExtra("name", service.getName(context));
        intent.putExtra("description", service.getDescription(context));
        intent.putExtra("cost", service.getCost());
        startActivity(intent);
    }
}
