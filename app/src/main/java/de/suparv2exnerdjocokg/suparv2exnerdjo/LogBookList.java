package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static de.suparv2exnerdjocokg.suparv2exnerdjo.R.id.list;

/**
 * Created by v2 on 05.12.2016.
 */

public class LogBookList extends Fragment {

    private ArrayList<Note> notes;
    private RecyclerView mRecyclerView;
    private EditText inputSearch;
    MyLogBookRecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = (RecyclerView) inflater.inflate(R.layout.fragment_logbook_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        prepareList();

//        if (container==null){
//            Log.println(Log.INFO,"", "container ist null");
//        }
        EditText inputSearch = (EditText) getActivity().findViewById(R.id.logbook_search_bar);
        if (inputSearch == null) {
            Log.println(Log.INFO, "", "container ist null");
        }
//        EditText inputSearch = (EditText) getActivity().findViewById(R.id.logbook_search_bar);


        return mRecyclerView;
    }

    private void prepareList() {
        Note first = new Note("Zähne putzen", "Ich habe dem Clienten die Zähne geputzt", new Carer("Olaf"),
                new Timestamp(1L));
        Note second = new Note("Eine Aufgabe", "Ich habe dem Clienten die Zähne geputzt", new Carer("not Olaf")
                , new Timestamp(3L));
        Note third = new Note("Keks", "Ich habe dem Clienten die Zähne geputzt",
                new Carer("Keksi"), new Timestamp(2L));

        notes = new ArrayList<>();
        notes.add(first);
        notes.add(second);
        notes.add(third);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View myActivityView = (FrameLayout) getActivity().findViewById(R.id.todo);
//        if (myActivityView == null) {
//            Log.println(Log.INFO, "m", "view ist gleich null");
//        } else {
//            Log.println(Log.INFO, "m", "view ist odch nicht null");
//        }
//
//        if (inputSearch == null) {
//            Log.println(Log.INFO, "m", "search ist gleich null");
//        } else {
//            Log.println(Log.INFO, "m", "search ist odch nicht null");
//        }

        adapter = new MyLogBookRecyclerViewAdapter(notes);
        inputSearch = (EditText) myActivityView.findViewById(R.id.logbook_search_bar);

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
//                adapter.getFilter().filter(cs);
                String filterString = cs.toString().toLowerCase();

//                Filter.FilterResults results = new Filter.FilterResults();
                if (filterString.equals("")) {
//                    adapter.setFilteredData(notes);
//                    adapter.notifyDataSetChanged();
                    mRecyclerView.setAdapter(new MyLogBookRecyclerViewAdapter(notes));


                } else {
                    final List<Note> list = notes;

                    int count = list.size();
                    final ArrayList<Note> nlist = new ArrayList<Note>(count);

                    String filterableString;
                    Note filterNote;
                    for (int i = 0; i < count; i++) {
                        filterNote = list.get(i);
                        filterableString = filterNote.getTag();
                        if (filterableString.toLowerCase().contains(filterString)) {
                            nlist.add(filterNote);
                        }
                    }
//                    adapter.setFilteredData(nlist);
//                    adapter.notifyDataSetChanged();

                    mRecyclerView.setAdapter(new MyLogBookRecyclerViewAdapter(nlist));
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });
        mRecyclerView.setAdapter(adapter);

    }

}
