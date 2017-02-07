package de.suparv2exnerdjocokg.suparv2exnerdjo.LogBook;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Client;
import de.suparv2exnerdjocokg.suparv2exnerdjo.ClientViewActivity;
import de.suparv2exnerdjocokg.suparv2exnerdjo.LogBook.NoteComparators.CarerComparator;
import de.suparv2exnerdjocokg.suparv2exnerdjo.LogBook.NoteComparators.DateComparator;
import de.suparv2exnerdjocokg.suparv2exnerdjo.LogBook.NoteComparators.TagComparator;
import de.suparv2exnerdjocokg.suparv2exnerdjo.R;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Todo.Note;
import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyNotes;

import static de.suparv2exnerdjocokg.suparv2exnerdjo.R.id.list;
import static de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyNotes.ITEMS;
import static java.util.Collections.sort;


public class LogBookFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    private Client c;
    private List<Note> notes;
    private RecyclerView mRecyclerView;
    private AutoCompleteTextView inputSearch;
    private MyLogBookRecyclerViewAdapter recyclerViewAdapter;
    //    private LinearLayout header;
    private boolean flag_date = true;
    private boolean flag_carer = true;
    private boolean flag_tag = true;

    private int currentSpinnerSelection;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_logbook, container, false);

        //        if (view.findViewById(R.id.header) != null) {
//            LogBookHeadline lHead = new LogBookHeadline();
//            getChildFragmentManager().beginTransaction().add(R.id.header, lHead).commit();
////           header =(LinearLayout) lHead.getView();
//            Log.i("if", "bin drinne");
//        }

        Spinner spinner = (Spinner) view.findViewById(R.id.search_bar_spinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.search_bar_choices, R.layout.spinner_item);

        arrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);


//        header = (FrameLayout) getView().findViewById(R.id.header);

        mRecyclerView = (RecyclerView) view.findViewById(list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        prepareList();
        recyclerViewAdapter = new MyLogBookRecyclerViewAdapter(notes);

        inputSearch = (AutoCompleteTextView) view.findViewById(R.id.logbook_search_bar);
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                filterList(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });
        update();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        FrameLayout header =
        LinearLayout header = (LinearLayout) getView().findViewById(R.id.header);

        TextView date = (TextView) header.findViewById(R.id.frag_logB_date);
        TextView tag = (TextView) header.findViewById(R.id.frag_logB_tag);
        TextView carer = (TextView) header.findViewById(R.id.frag_logB_carer);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag_date) {
                    sortBy(new DateComparator());
                    flag_date = false;
                } else {
                    sortBy(Collections.reverseOrder(new DateComparator()));
                    flag_date = true;
                }
            }
        });

        tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag_tag) {
                    sortBy(new TagComparator());
                    flag_tag = false;
                } else {
                    sortBy(Collections.reverseOrder(new TagComparator()));
                    flag_tag = true;
                }
            }
        });
        carer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag_carer) {
                    sortBy(new CarerComparator());
                    flag_carer = false;
                } else {
                    sortBy(Collections.reverseOrder(new CarerComparator()));
                    flag_carer = true;
                }
            }
        });


    }

    public void filterList(CharSequence cs) {
        String filterString = cs.toString().toLowerCase();

        if (filterString.equals("")) {
            mRecyclerView.swapAdapter(new MyLogBookRecyclerViewAdapter(notes), true);
//            mRecyclerView.swapAdapter();
        } else {
            final List<Note> list = notes;

            int count = list.size();
            final ArrayList<Note> nlist = new ArrayList(count);

            String filterableString;
            Note filterNote;
            for (int i = 0; i < count; i++) {
                filterNote = list.get(i);
                filterableString = filterNote.getInfoFromPosition(currentSpinnerSelection).toLowerCase().trim();
                if (filterableString.contains(filterString)) {
                    nlist.add(filterNote);
                }
            }

            mRecyclerView.swapAdapter(new MyLogBookRecyclerViewAdapter(nlist), true);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.currentSpinnerSelection = position;
        if (position == 0 || position == 3) {
            String[] autoCompleteItems = getAutoCompleteItems(position);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.auto_complete_item, autoCompleteItems);
            inputSearch.setAdapter(adapter);
        } else {
            inputSearch.setAdapter(null);
        }
        filterList(this.inputSearch.getText());
    }

    private String[] getAutoCompleteItems(int position) {
        Set<String> autoCompleteItems = new HashSet<>();
        for (int i = 0; i < notes.size(); i++) {
            Note note = notes.get(i);
            autoCompleteItems.add(note.getInfoFromPosition(position));
        }
        return autoCompleteItems.toArray(new String[autoCompleteItems.size()]);

    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private void prepareList() {
        Activity a = getActivity();
        if(a instanceof ClientViewActivity) {
            c = ((ClientViewActivity)a).getClient();
            notes = c.getNotes();
        }
    }

    public void update() {
//        notes = ((ClientViewActivity)getActivity()).client.
        //Collections.copy(this.notes, notes);
//        Collections.sort(notes);
//        Collections.sort(ITEMS, Collections.<Note>reverseOrder());
        sort(this.notes, Collections.reverseOrder(new DateComparator()));
        recyclerViewAdapter = new MyLogBookRecyclerViewAdapter(notes);
        mRecyclerView.swapAdapter(recyclerViewAdapter, true);
//        mRecyclerView.setAdapter(recyclerViewAdapter);
    }

    private void groupBy(int id) {
        Log.i("methode", "group by my side");
        switch (id) {
            case R.id.frag_logB_date:
                Log.i("date my ass", "hello");
                if (flag_date) {
                    Log.i("first ", "if");
                    sort(notes, new DateComparator());
                    flag_date = false;
                } else {
                    Log.i("second ", "if");
                    sort(notes, Collections.reverseOrder(new DateComparator()));
                    flag_date = true;
                }
                recyclerViewAdapter = new MyLogBookRecyclerViewAdapter(notes);
                mRecyclerView.swapAdapter(recyclerViewAdapter, true);
                break;
        }

    }

    private void sortBy(Comparator<Note> comparator) {
        sort(notes, comparator);
        recyclerViewAdapter = new MyLogBookRecyclerViewAdapter(notes);
        mRecyclerView.swapAdapter(recyclerViewAdapter, true);
    }
}
