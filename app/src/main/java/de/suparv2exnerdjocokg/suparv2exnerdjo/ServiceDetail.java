package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ServiceDetail extends AppCompatActivity {

    private TextView name;
    private TextView description;
    private TextView cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_detail);

        // Suchen der Views im XML
        name = (TextView) findViewById(R.id.serviceName);
        description = (TextView) findViewById(R.id.serviceDescription);
        cost = (TextView) findViewById(R.id.serviceCost);

        // die Details der Leistung werden aus den mitgeschickten Daten ausgelesen und dann den Views zugewiesen
        Bundle extras = getIntent().getExtras();
        name.setText(extras.getString("name"));
        description.setText(extras.getString("description"));
        cost.setText(extras.getString("cost"));
    }
}
