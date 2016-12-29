package de.suparv2exnerdjocokg.suparv2exnerdjo.Route;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Timestamp;
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

public class MyClientRecyclerViewAdapter extends RecyclerView.Adapter<MyClientRecyclerViewAdapter.ViewHolder> {

    private final List<Client> mClients;
    private final ClientListFragment.OnListFragmentInteractionListener mListener;
    private View oldSelection = null;


    public MyClientRecyclerViewAdapter(List<Client> items, ClientListFragment.OnListFragmentInteractionListener listener) {
        mClients = items;
        mListener = listener;
    }


    @Override
    public MyClientRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_client, parent, false);
        return new MyClientRecyclerViewAdapter.ViewHolder(view);
    }


    // Called by RecyclerView to display the data at the specified position. This method should update the contents of the itemView to reflect the item at the given position.
    @Override
    public void onBindViewHolder(final MyClientRecyclerViewAdapter.ViewHolder holder, final int position) {
        holder.mClient = mClients.get(position);
        holder.mName.setText(holder.mClient.getFirstName()+" "+holder.mClient.getLastName());
        holder.mAddress.setText(holder.mClient.getAdress());
        holder.mImage.setImageDrawable(holder.mView.getResources().getDrawable(holder.mClient.getImagePath()));


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onListFragmentInteraction(position);
            }
        });

        holder.routeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Link to Google
            }
        });
    }

    @Override
    public int getItemCount() {
        return mClients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImage;
        public final TextView mName;
        public final TextView mAddress;
        public final ImageButton routeButton;
        public final ImageView arrow;
        public Client mClient;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImage = (ImageView) view.findViewById(R.id.image);
            mName = (TextView) view.findViewById(R.id.name);
            mAddress = (TextView) view.findViewById(R.id.address);
            routeButton = (ImageButton) view.findViewById(R.id.routeButton);
            arrow = (ImageView) view.findViewById(R.id.arrow);
        }
    }
}
