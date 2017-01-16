package de.suparv2exnerdjocokg.suparv2exnerdjo.Todo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import de.suparv2exnerdjocokg.suparv2exnerdjo.GeneralTask;
import de.suparv2exnerdjocokg.suparv2exnerdjo.R;
import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyToDos;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class TodoFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    public static String ARG_Position;
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private OnInfoClickedInteractionListener infoListener;
    private View view;
    private static View oldSelection;
    private Context context;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TodoFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static TodoFragment newInstance(int columnCount) {
        TodoFragment fragment = new TodoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    // Called to do initial creation of a fragment. This is called after onAttach(Activity) and before onCreateView(LayoutInflater, ViewGroup, Bundle)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    // Called to have the fragment instantiate its user interface view. This is optional, and non-graphical fragments can return null (which is the default implementation). This will be called between onCreate(Bundle) and onActivityCreated(Bundle).
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_todo_list, container, false);


        context = view.getContext();
        createView();

        return view;
    }

    private void createView() {
        DummyToDos.sortAlphabet();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.todolist);
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        recyclerView.setAdapter(new MyTodoRecyclerViewAdapter(this, DummyToDos.getUndone(), mListener, infoListener));


        RecyclerView recyclerViewDone = (RecyclerView) view.findViewById(R.id.todolist_done);
        if (mColumnCount <= 1) {
            recyclerViewDone.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerViewDone.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        recyclerViewDone.setAdapter(new MyTodoRecyclerViewAdapter(this, DummyToDos.getDone(), mListener, infoListener));

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 0.5f);
        recyclerView.setLayoutParams(layoutParams);
        recyclerViewDone.setLayoutParams(layoutParams);

        TextView doneHeader = (TextView) view.findViewById(R.id.done_headline);
        if(DummyToDos.getDone().size()==0){

            doneHeader.setVisibility(View.INVISIBLE);
            recyclerViewDone.setVisibility(View.INVISIBLE);

            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0,0f);
            recyclerViewDone.setLayoutParams(layoutParams2);
        }else{
            doneHeader.setVisibility(View.VISIBLE);
            recyclerViewDone.setVisibility(View.VISIBLE);
        }

        TextView undoneHeader = (TextView) view.findViewById(R.id.undone_headline);
        if(DummyToDos.getUndone().size()==0){
            recyclerView.setVisibility(View.INVISIBLE);


            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0,0f);
            recyclerView.setLayoutParams(layoutParams2);

            undoneHeader.setText("Sehr gut. Alle Aufgaben für heute sind erledigt.");
            undoneHeader.setTextSize(16);
        }else{
            recyclerView.setVisibility(View.VISIBLE);
            undoneHeader.setVisibility(View.VISIBLE);
            undoneHeader.setText("Deine Aufgaben für heute:");
            undoneHeader.setTextAppearance(getContext(), R.style.AppTextSmall);
            undoneHeader.setTextColor(getResources().getColor(R.color.colorAccent));
        }

        recyclerView.requestLayout();
        recyclerViewDone.requestLayout();
    }

    public void updateList(){
        createView();
    }

    // Called when a fragment is first attached to its context. onCreate(Bundle) will be called after this.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
            infoListener = (OnInfoClickedInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener and OnInfoClickedInteractionListener");
        }
    }

    //Called when the fragment is no longer attached to its activity. This is called after onDestroy(), except in the cases where the fragment instance is retained across Activity re-creation (see setRetainInstance(boolean)), in which case it is called after onStop().
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        infoListener = null;
    }

    public static View getOldSelection(){
        return oldSelection;
    }

    public static void setOldSelection(View newSelection){
        oldSelection = newSelection;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(int position, boolean done);
        void onDatePickerInteraction (TodoFragment frag, GeneralTask task);
    }
    public interface OnInfoClickedInteractionListener{
        void onInfoClickedListener(int position, boolean done);
    }
}
