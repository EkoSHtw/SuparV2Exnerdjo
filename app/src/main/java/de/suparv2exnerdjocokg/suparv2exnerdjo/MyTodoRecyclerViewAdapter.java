package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.HashMap;
import java.util.List;

/**

 * TODO: Replace the implementation with code for your data type.
 */
public class MyTodoRecyclerViewAdapter extends RecyclerView.Adapter<MyTodoRecyclerViewAdapter.ViewHolder> {

    private final List<ToDo> mValues;
    private final TodoFragment.OnListFragmentInteractionListener mListener;
    private View oldSelection = null;


    public MyTodoRecyclerViewAdapter(List<ToDo> items, TodoFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_todo, parent, false);
        return new ViewHolder(view);
    }

    // Called by RecyclerView to display the data at the specified position. This method should update the contents of the itemView to reflect the item at the given position.
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mNameView.setText(mValues.get(position).getTask().getName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    clearSelection();
                    oldSelection = holder.mView;
                    mListener.onListFragmentInteraction(position);
                    holder.mView.setBackgroundColor(holder.mView.getResources().getColor(R.color.colorAccent));
                }
            }
        });
    }

    public void clearSelection() {
        if(oldSelection != null) {
            oldSelection.setBackgroundColor(oldSelection.getResources().getColor(android.R.color.transparent));
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameView;
        public final ImageView mContentView;
        public ToDo mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = (TextView) view.findViewById(R.id.name);
            mContentView = (ImageView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }
    }
}
