package de.suparv2exnerdjocokg.suparv2exnerdjo.Route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.FrameLayout.LayoutParams;

import org.w3c.dom.Text;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Carer;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Client;
import de.suparv2exnerdjocokg.suparv2exnerdjo.ClientViewActivity;
import de.suparv2exnerdjocokg.suparv2exnerdjo.GeneralTask;
import de.suparv2exnerdjocokg.suparv2exnerdjo.R;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Todo.MyTodoRecyclerViewAdapter;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Todo.Note;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Todo.ToDo;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Todo.TodoFragment;
import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyNotes;

/**
 * Created by dsfd on 29.12.2016.
 */

public class MyClientExpandableListViewAdapter extends BaseExpandableListAdapter {

    private final List<Client> mClients;
    private final ClientListFragment.OnListFragmentInteractionListener mListener;
    private View oldSelection = null;
    private Context c;

    public MyClientExpandableListViewAdapter(Context context, List<Client> listDataHeader, ClientListFragment.OnListFragmentInteractionListener listener){
        c = context;
        mClients = listDataHeader;
        mListener = listener;
    }

    @Override
    public int getGroupCount() {
        Log.i("", "groupCount: "+mClients.size());
        return mClients.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        Log.i("", "childCount: "+mClients.get(groupPosition).getToDoList().size());
        return mClients.get(groupPosition).getToDoList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mClients.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mClients.get(groupPosition).getToDoList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater infalInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.fragment_client, null);
        }

        final Client client = mClients.get(groupPosition);

        TextView time = (TextView) convertView.findViewById(R.id.timeRoute);
        int count = mClients.size();
        double timePerClient = 8.0/count;
        time.setText((int)(groupPosition*timePerClient+8)+":00 Uhr\n - \n"+(int)((groupPosition*timePerClient)+timePerClient+8)+":00 Uhr");

        TextView name = (TextView) convertView.findViewById(R.id.name);
        name.setText(client.getFirstName()+" "+client.getLastName());

        TextView address = (TextView) convertView.findViewById(R.id.address);
        address.setText(client.getAdress());

        ImageView image = (ImageView) convertView.findViewById(R.id.image);
        image.setImageDrawable(convertView.getResources().getDrawable(client.getImagePath()));

        ImageButton routeButton = (ImageButton) convertView.findViewById(R.id.routeButton);
        routeButton.setColorFilter(convertView.getResources().getColor(R.color.colorPrimary));

        ImageButton arrow = (ImageButton) convertView.findViewById(R.id.arrow);
        arrow.setColorFilter(convertView.getResources().getColor(R.color.colorPrimary));

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onListFragmentInteraction(groupPosition);
            }
        });
        arrow.setFocusable(false);

        routeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onRouteInteraction(client.getAdress());
            }
        });
        routeButton.setFocusable(false);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater infalInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.todo_for_client_route, null);
        }

        TextView tView = (TextView) convertView.findViewById(R.id.todo);
        ToDo todo = (ToDo) getChild(groupPosition, childPosition);
        tView.setText(todo.getTask().getName());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


}
