package de.suparv2exnerdjocokg.suparv2exnerdjo.Todo;

import android.app.Activity;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;


import java.sql.Timestamp;
import java.util.List;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Carer;
import de.suparv2exnerdjocokg.suparv2exnerdjo.GeneralTask;
import de.suparv2exnerdjocokg.suparv2exnerdjo.R;
import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyNotes;

/**

 * TODO: Replace the implementation with code for your data type.
 */
public class MyTodoRecyclerViewAdapter extends RecyclerView.Adapter<MyTodoRecyclerViewAdapter.ViewHolder> {

    private final List<ToDo> mValues;
    private final TodoFragment.OnListFragmentInteractionListener mListener;
    private final TodoFragment.OnInfoClickedInteractionListener infoListener;
    TodoFragment fragment;


    public MyTodoRecyclerViewAdapter(TodoFragment fragment, List<ToDo> items, TodoFragment.OnListFragmentInteractionListener listener, TodoFragment.OnInfoClickedInteractionListener newInfoListener) {
        this.fragment = fragment;
        mValues = items;
        mListener = listener;
        infoListener = newInfoListener;
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
        int note = position;
        final GeneralTask currentTask = holder.mItem.getTask();
        holder.mNameView.setText(mValues.get(position).getTask().getName());

        if(currentTask.isDone()){
            holder.mCheckBox.setChecked(true);
            //holder.mView.setBackgroundColor(holder.mView.getResources().getColor(R.color.colorPrimaryLight));
            holder.mInfo.clearColorFilter();
            holder.shiftTask.setVisibility(View.GONE);
            if(currentTask.getDaysShiftet() > 0){
                if(currentTask.getDaysShiftet() > 1) {
                    holder.shiftet.setText("um " + currentTask.getDaysShiftet() + " Tage verschoben");
                }else{
                    holder.shiftet.setText("um " + currentTask.getDaysShiftet() + " Tag verschoben");
                }
            }
        }else{
            holder.shiftTask.setColorFilter(holder.mView.getResources().getColor(R.color.grey));
            holder.shiftet.setVisibility(View.GONE);
        }


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    if(TodoFragment.getOldSelection() == holder.mView){
                        mListener.onListFragmentInteraction(-1, true);
                    }else {
                        clearSelection();
                        TodoFragment.setOldSelection(holder.mView);
                        mListener.onListFragmentInteraction(position, holder.mItem.getTask().isDone());
                        holder.mNameView.setTextColor(holder.mView.getResources().getColor(R.color.colorAccent));
                    }
                }
            }
        });

        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                holder.mItem.getTask().setDone(isChecked);
                mListener.onListFragmentInteraction(-2, true);
                if(isChecked) {
                    holder.mView.setBackgroundColor(holder.mView.getResources().getColor(R.color.grey));
                    DummyNotes.ITEMS.add(new Note(currentTask.getTag(), ""+ currentTask.getName()+" durchgeführt", new Carer("John"), new Timestamp(System.currentTimeMillis())));
                }else{
                    holder.mView.setBackgroundColor(holder.mView.getResources().getColor(R.color.transparent));
                    for(int i = 0; i < mValues.size(); i++){
                        if(mValues.get(i).getTask().getTag().toLowerCase().contains(currentTask.getTag().toLowerCase())){
                            long timestamp = holder.mItem.getTimestamp().getDay();
                            long secondTimestamp = mValues.get(i).getTimestamp().getDay();

                            if(timestamp==secondTimestamp){
                                mValues.remove(i);
                            }
                        }
                    }
                    currentTask.setShiftet(0);
                }
            }
        });

        holder.mInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                TodoFragment.setOldSelection(holder.mView);
                holder.mInfo.setColorFilter(holder.mView.getResources().getColor(R.color.colorAccent));
                if(null!=infoListener) {
                    infoListener.onInfoClickedListener(position, holder.mItem.getTask().isDone());
                }
            }
        });

        holder.shiftTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDatePickerInteraction(fragment, currentTask);

            }
        });
    }

    public void clearSelection() {
        if(TodoFragment.getOldSelection() != null) {
            TextView name = (TextView) TodoFragment.getOldSelection().findViewById(R.id.name);
            name.setTextColor(TodoFragment.getOldSelection().getResources().getColor(R.color.colorPrimaryDark));
            ImageButton info = (ImageButton) TodoFragment.getOldSelection().findViewById(R.id.info);
            info.setBackgroundColor(TodoFragment.getOldSelection().getResources().getColor(android.R.color.transparent));
            info.clearColorFilter();
        }
    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameView;
        public final TextView shiftet;
        public final ImageButton shiftTask;
        public final ImageButton mInfo;
        public final CheckBox mCheckBox;
        public ToDo mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = (TextView) view.findViewById(R.id.name);
            shiftet = (TextView) view.findViewById(R.id.shiftet);
            shiftTask = (ImageButton) view.findViewById(R.id.shift_task);
            mInfo = (ImageButton) view.findViewById(R.id.info);
            mCheckBox = (CheckBox) view.findViewById(R.id.checkBox);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }
    }
}
