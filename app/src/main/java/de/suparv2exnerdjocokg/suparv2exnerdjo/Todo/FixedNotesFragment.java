package de.suparv2exnerdjocokg.suparv2exnerdjo.Todo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Client;
import de.suparv2exnerdjocokg.suparv2exnerdjo.ClientViewActivity;
import de.suparv2exnerdjocokg.suparv2exnerdjo.R;
import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyNotes;
import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyToDos;

/**
 * A fragment representing a list of Items.
 * <p/>

 * interface.
 */
public class FixedNotesFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private View view;
    private static final String ARG_POSITION = "position";
    private int position = -1;
    private List<Note> notes;
    private Client c;
    private List<ToDo> todos;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FixedNotesFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static FixedNotesFragment newInstance(int columnCount) {
        FixedNotesFragment fragment = new FixedNotesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            position = getArguments().getInt(ARG_POSITION);
        }
        Activity a = getActivity();
        if(a instanceof ClientViewActivity){
            c = ((ClientViewActivity)a).getClient();
            notes = c.getNotes();
            todos = c.getToDoList();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fixed_notes_list, container, false);

        if(position != -1){
            notes = new ArrayList<>();
            for(int i = 0; i < notes.size(); i++) {
                String note = notes.get(i).getTag();
                String todo = todos.get(position).getTask().getName();
                if(note.equals(todo)){
                    notes.add(notes.get(i));
                }
            }
        }

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyNoteRecyclerViewAdapter(notes));
        }
        return view;
    }

    public void updateFragView(int position, boolean done){
        List<ToDo> actualList;
        if(done){
            actualList = DummyToDos.getDone(todos);
        }else{
            actualList = DummyToDos.getUndone(todos);
        }
        List<Note> items = new ArrayList<>();
        for(int i = 0; i < notes.size(); i++) {
            String note = notes.get(i).getTag();
            String todo = actualList.get(position).getTask().getName();
            if(note.equals(todo)){
                items.add(DummyNotes.ITEMS.get(i));
            }
        }

        if(view != null && view instanceof RecyclerView) {
            RecyclerView rView = (RecyclerView) view;
            rView.setAdapter(new MyNoteRecyclerViewAdapter(items));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
