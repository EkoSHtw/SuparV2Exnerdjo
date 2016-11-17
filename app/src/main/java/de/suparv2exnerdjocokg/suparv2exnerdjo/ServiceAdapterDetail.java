package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dsfd on 10.11.2016.
 */

public class ServiceAdapterDetail extends BaseAdapter {

    private final List<Service> services;
    private final LayoutInflater inflator;

    public ServiceAdapterDetail(Context context){
        // wird für das Aufblasend der XML-Datei benötigt
        inflator = LayoutInflater.from(context);
        // alle Services aus der INSTANCE ermitteln
        services = new ArrayList<>();
        for(int index = 0; index<Services.getSize(); index++){
            Service service = Services.getService(index);
            services.add(service);
            System.out.println(services.size());
        }
    }

    // liefert die Länge der Liste und Anzahl der anzuzeigenden Zeilen
    @Override
    public int getCount(){
        return services.size();
    }

    // liefert das Element an einer bestimmten Position
    @Override
    public Object getItem(int position) {return services.get(position);}

    @Override
    public long getItemId(int position){return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        // falls nötig, convertView bauen
        if (convertView == null){
            // Layout entfalten, zu nutzende XML-Datei wird zugewiesen
            convertView = inflator.inflate(R.layout.activity_service_catalog, parent, false);
            // Holder erzeugen, die XML-View-Id´s dem Holder zuweisen
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.serviceName);
            //holder.description = (TextView) convertView.findViewById(R.id.serviceDescription);
            holder.cost = (TextView) convertView.findViewById(R.id.serviceCost);
            convertView.setTag(holder);
        }else{
            // Holder bereits vorhanden
            holder = (ViewHolder) convertView.getTag();
        }

        Context context = parent.getContext();
        Service service = (Service) getItem(position);
        holder.name.setText(service.getName(context));
        //holder.description.setText(service.getDescription());
        holder.cost.setText(service.getCost());
        return convertView;
    }

    // Holder mit den entsprechenden Views des Layouts
    static class ViewHolder{
        TextView name;
        //TextView description;
        TextView cost;
    }
}