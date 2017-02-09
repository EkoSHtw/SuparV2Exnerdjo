package de.suparv2exnerdjocokg.suparv2exnerdjo.Route;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.FrameLayout.LayoutParams;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Timestamp;
import java.util.Date;
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
import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyPictograms;
import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyToDos;

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
        return mClients.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
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

    public Object getParent(int groupPosition){
        return mClients.get(groupPosition);
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
        List<ToDo> todos = client.getToDoList();


        TextView time = (TextView) convertView.findViewById(R.id.time_route);
        /*
        int count = mClients.size();
        double timePerClientWithDrive = 8.0/count;
        double twentyMintues = 20.0/60.0;
        double timePerClientWithoutDrive = timePerClientWithDrive-twentyMintues;
        double start = groupPosition*timePerClientWithDrive+8;
        double startMinutes = (start-(int)start)*60;
        double end = start+timePerClientWithoutDrive;
        double endMinutes = (end-(int)end)*60;
        time.setText((int)start +":"+ (int)startMinutes +" Uhr\n - \n"+(int)end+":"+ (int)endMinutes+" Uhr");*/

        switch (groupPosition){
            case 0:
                time.setText("08:15 Uhr\n - \n 08:55 Uhr");
                break;
            case 1:
                time.setText("09:15 Uhr\n - \n 09:55 Uhr");
                break;
            case 2:
                time.setText("10:15 Uhr\n - \n 10:55 Uhr");
                break;
            case 3:
                time.setText("11:15 Uhr\n - \n 11:55 Uhr");
                break;
            case 4:
                time.setText("12:45 Uhr\n - \n 13:25 Uhr");
                break;
            case 5:
                time.setText("13:45 Uhr\n - \n 14:25 Uhr");
                break;
            case 6:
                time.setText("14:45 Uhr\n - \n 16:15 Uhr");
                break;
        }

        TextView name = (TextView) convertView.findViewById(R.id.name);
        name.setText(client.getFirstName()+" "+client.getLastName());

        TextView address = (TextView) convertView.findViewById(R.id.address);
        address.setText(client.getAdress());

        ImageView image = (ImageView) convertView.findViewById(R.id.image);
        image.setImageDrawable(convertView.getResources().getDrawable(client.getImagePath()));

        LinearLayout pictogramContainer = (LinearLayout)  convertView.findViewById(R.id.pictrogram_container);
        pictogramContainer.removeAllViews();
        if(pictogramContainer.getChildCount() == 0) {
            ViewGroup.LayoutParams pictogramParams = new ViewGroup.LayoutParams(50, 50);
            for (int i = 0; i < client.getPictograms().size(); i++) {
                final int myI = i;
                ImageView pictogram = new ImageView(this.c);
                pictogram.setLayoutParams(pictogramParams);
                pictogram.setImageResource(client.getPictograms().get(i));
                pictogram.setColorFilter(c.getResources().getColor(R.color.colorPrimaryDark));
                pictogramContainer.addView(pictogram);

                pictogram.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String description = DummyPictograms.pictogramDescriptions.get(client.getPictograms().get(myI));
                        Toast toast = Toast.makeText(c, description, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
            }
        }

        ImageButton routeButton = (ImageButton) convertView.findViewById(R.id.route_button);

        ImageButton arrow = (ImageButton) convertView.findViewById(R.id.arrow);
        //arrow.setColorFilter(convertView.getResources().getColor(R.color.colorPrimaryDark));

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

        TextView taskStatus = (TextView) convertView.findViewById(R.id.task_status);
        int taskAmount = todos.size();
        int tasksDone = DummyToDos.getDone(todos).size();

        taskStatus.setText(tasksDone+"/"+taskAmount);

        ImageView checkmark = (ImageView) convertView.findViewById(R.id.checkmark);
        TextView fortschritt = (TextView) convertView.findViewById(R.id.forschritt);

        if(DummyToDos.getUndone(todos).size() == 0) {
                /*LinearLayout container = (LinearLayout) convertView.findViewById(R.id.container_for_checkmark);
                container.removeAllViews();

                ImageView checkmark = new ImageView(c);
                checkmark.setImageResource();
                container.addView(checkmark);*/
            checkmark.setColorFilter(c.getResources().getColor(R.color.colorAccent));
            checkmark.setVisibility(View.VISIBLE);
            taskStatus.setVisibility(View.GONE);
            fortschritt.setVisibility(View.GONE);
        }else{
            checkmark.setVisibility(View.GONE);
            taskStatus.setVisibility(View.VISIBLE);
            fortschritt.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater infalInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.todo_for_client_route, null);
        }

        TextView tView = (TextView) convertView.findViewById(R.id.todo);
        final ToDo todo = (ToDo) getChild(groupPosition, childPosition);
        tView.setText(todo.getTask().getName());

        CheckBox taskDone = (CheckBox) convertView.findViewById(R.id.check_box_task);
        if(todo.getTask().isDone()){
            //taskDone.setImageResource(android.R.drawable.checkbox_on_background);
            taskDone.setChecked(true);
        }else{
            //taskDone.setImageResource(android.R.drawable.checkbox_off_background);
            taskDone.setChecked(false);
        }

        taskDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(todo.getTask().isDone()){
                    todo.getTask().setDone(false);
                }else{
                    todo.getTask().setDone(true);
                }
                notifyDataSetChanged();
            }
        });



        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


}
