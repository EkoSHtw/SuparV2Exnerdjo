package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


import de.suparv2exnerdjocokg.suparv2exnerdjo.Todo.Note;
import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyNotes;

import static de.suparv2exnerdjocokg.suparv2exnerdjo.R.id.list;


public class LogBookFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    private ArrayList<Note> notes;
    private RecyclerView mRecyclerView;
    private EditText inputSearch;
    private MyLogBookRecyclerViewAdapter recyclerViewAdapter;

    private int currentSpinnerSelection;


//    private OnListFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_logbook, container, false);

        if(view.findViewById(R.id.header)!=null){
            LogBookHeadline lHead = new LogBookHeadline();
            getChildFragmentManager().beginTransaction().add(R.id.header, lHead).commit();
        }

        Spinner spinner = (Spinner) view.findViewById(R.id.search_bar_spinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.search_bar_choices, android.R.layout.simple_spinner_item);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);




//        View view = (RecyclerView) inflater.inflate(R.layout.fragment_logbook_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        prepareList();
        recyclerViewAdapter = new MyLogBookRecyclerViewAdapter(notes);

        inputSearch = (EditText) view.findViewById(R.id.logbook_search_bar);
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
//                adapter.getFilter().filter(cs);
                filterList(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });
        mRecyclerView.setAdapter(recyclerViewAdapter);

        return view;
    }

    public void filterList(CharSequence cs) {
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
                filterableString = filterNote.getInfoFromPosition(currentSpinnerSelection);
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.currentSpinnerSelection = position;
        filterList(this.inputSearch.getText());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { }

    private void prepareList() {



        //notes = new ArrayList<>();
//        notes = (ArrayList) DummyNotes.ITEMS;
        notes = (ArrayList) DummyNotes.ITEMS;
//        Collections.reverse(notes);
    }
}
