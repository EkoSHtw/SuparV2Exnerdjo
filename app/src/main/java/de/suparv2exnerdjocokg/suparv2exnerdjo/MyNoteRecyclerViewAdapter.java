package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.List;

/**
 *
 * TODO: Replace the implementation with code for your data type.
 */
public class MyNoteRecyclerViewAdapter extends RecyclerView.Adapter<MyNoteRecyclerViewAdapter.ViewHolder> {

    private final List<Note> mValues;

    public MyNoteRecyclerViewAdapter(List<Note> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTagView.setText(mValues.get(position).getTag());
        holder.mContentView.setText(mValues.get(position).getContent());
        holder.mTimestampView.setText(""+mValues.get(position).getTimestamp());
        holder.mCarerView.setText(mValues.get(position).getCarer().getName());

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTagView;
        public final TextView mContentView;
        public final TextView mCarerView;
        public final TextView mTimestampView;
        public Note mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTagView = (TextView) view.findViewById(R.id.tag);
            mContentView = (TextView) view.findViewById(R.id.content);
            mCarerView = (TextView) view.findViewById((R.id.carer));
            mTimestampView = (TextView) view.findViewById(R.id.time);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
