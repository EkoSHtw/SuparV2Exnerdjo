package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class ServiceCatalog extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ServiceAdapter adapter;
    private ListView serviceList;
    private TextView headline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_catalog_around);

        // Suchen der Headline in der XML und Zuweisen des Textes
        headline = (TextView) findViewById(R.id.headline);
        headline.setText(R.string.serviceHeadline);

        // Finden des ListViews im Text
        serviceList = (ListView) findViewById(R.id.serviceList);
        // ein neuer ServiceAdapter wird erstellt und dem ListView zugewiesen
        adapter = new ServiceAdapter(this);
        serviceList.setAdapter(adapter);
        // ein OnItemClickListener wird dem ListView zugewiesen
        serviceList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        // Bestimmen des Services auf den geklickt wurde
        Service service = (Service) adapter.getItem(position);
        // Context bestimmen ?
        Context context = parent.getContext();
        // ein neues Intent wird erstellt und der Klasse, die gestartet wird zugewiesen
        Intent intent = new Intent(this, ServiceDetail.class);
        // wichtige Daten über den geklickten Service werden mitgeschickt
        intent.putExtra("name", service.getName(context));
        intent.putExtra("description", service.getDescription(context));
        intent.putExtra("cost", service.getCost());
        // Das Intent/Activity wird gestartet
        startActivity(intent);
    }
}
