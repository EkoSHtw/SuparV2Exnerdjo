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

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Client;
import de.suparv2exnerdjocokg.suparv2exnerdjo.ClientViewActivity;
import de.suparv2exnerdjocokg.suparv2exnerdjo.LogBook.NoteComparators.DateComparator;
import de.suparv2exnerdjocokg.suparv2exnerdjo.R;


/**
 * A simple {@link Fragment} subclass.
 * to handle interaction events.
 * Use the {@link NotesFromYesterdayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotesFromYesterdayFragment extends Fragment {
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private View view;
    private Client c;
    private List<Note> notes;
    private List<ToDo> todos;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NotesFromYesterdayFragment() {
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
        }
        Activity a = getActivity();
        if (a instanceof ClientViewActivity) {
            c = ((ClientViewActivity) a).getClient();
            notes = c.getNotes();
            todos = c.getToDoList();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notes_from_yesterday, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            try {
                recyclerView.setAdapter(new MyNoteRecyclerViewAdapter(getYesterday()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return view;
    }

    private List<Note> getYesterday() throws ParseException {
        ClientViewActivity clientViewActivity = (ClientViewActivity) getActivity();
        List<Note> list = clientViewActivity.getClient().getNotes();
        Collections.sort(list, new DateComparator());
        List<Note> returnList = new ArrayList<>();
//        for(int i = 0; i < notes.size(); i++){
//            if(equalsWithYesterday(notes.get(i).getTimestamp())){
//                list.add(notes.get(i));
//            }
//        }

        int i = list.size()-1;
        if (i >= 0) {
            int count;
            if (i+1 >= 7) {
                count = 7;
            } else {
                count = i;
            }

            while (count >= 0) {
                returnList.add(list.get(count));
                count--;
            }
        }
        return returnList;
    }

    public boolean equalsWithYesterday(Timestamp st) throws ParseException {
        Date date = new Date(st.getTime());
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd"); // Time part has discarded
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date yesterday = dateFormat.parse(dateFormat.format(cal.getTime())); // get yesterday's Date without time part
        Date srcDateWithoutTime = dateFormat.parse(dateFormat.format(date));
        return yesterday.equals(srcDateWithoutTime); // checks src date equals yesterday.
    }

    public void updateFragView(int position) {
        List<Note> items = new ArrayList<>();
        for (int i = 0; i < notes.size(); i++) {
            String note = notes.get(i).getTag();
            String todo = todos.get(position).getTask().getName();
            if (note.equals(todo)) {
                items.add(notes.get(i));
            }
        }

        if (view != null && view instanceof RecyclerView) {
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