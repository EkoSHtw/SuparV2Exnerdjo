package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by v2 on 05.12.2016.
 */

public class LogBookList extends Fragment {

    private ArrayList<Note> notes;
    private RecyclerView mRecyclerView;
//    private EditText inputSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = (RecyclerView) inflater.inflate(R.layout.fragment_logbook_list,container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        prepareList();
        final MyLogBookRecyclerViewAdapter adapter = new MyLogBookRecyclerViewAdapter(notes);

        final EditText inputSearch = (EditText) container.findViewById(R.id.logbook_search_bar);
//        inputSearch = (EditText) container.findViewById(R.id.logbook_search_bar);
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });
        mRecyclerView.setAdapter(adapter);



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
}
