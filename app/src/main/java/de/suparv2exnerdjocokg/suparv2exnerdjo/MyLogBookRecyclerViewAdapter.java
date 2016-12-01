package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.suparv2exnerdjocokg.suparv2exnerdjo.ItemFragment.OnListFragmentInteractionListener;
import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyContent.DummyItem;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyLogBookRecyclerViewAdapter extends RecyclerView.Adapter<MyLogBookRecyclerViewAdapter.ViewHolder> {

    private final List<Note> mValues;
//    private final OnListFragmentInteractionListener mListener;

    Activity context;

//    public MyLogBookRecyclerViewAdapter(List<Note> items, OnListFragmentInteractionListener listener) {
//        mValues = items;
//        mListener = listener;
//    }

    public MyLogBookRecyclerViewAdapter(List<Note> notes){
        this.mValues = notes;
    }

    public MyLogBookRecyclerViewAdapter(Activity context, List<Note> values){
        this.context = context;
        this.mValues = values;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_logbook_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);

//        holder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != mListener) {
//                    // Notify the active callbacks interface (the activity, if the
//                    // fragment is attached to one) that an item has been selected.
//                    mListener.onListFragmentInteraction(holder.mItem);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView date;
        public final TextView tag;
        public final TextView content;
        public final TextView carer;

        public ViewHolder(View view) {
            super(view);
            date = (TextView) view.findViewById(R.id.frag_logB_date);
            tag = (TextView) view.findViewById(R.id.frag_logB_tag);
            content = (TextView) view.findViewById(R.id.frag_logB_content);
            carer = (TextView) view.findViewById(R.id.frag_logB_carer);
        }

      
    }
}
