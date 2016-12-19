package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ToDoNotes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ToDoNotes extends Fragment {

    public ToDoNotes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment ToDoNotes.
     */
    // TODO: Rename and change types and number of parameters
    public static ToDoNotes newInstance(String param1, String param2) {
        ToDoNotes fragment = new ToDoNotes();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_to_do_notes, container, false);
        if (view.findViewById(R.id.fixedNotes) != null) {
            // Create a new Fragment to be placed in the activity layout
            FixedNotesFragment firstFragment = new FixedNotesFragment();
            // Add the fragment to the 'fragment_container' FrameLayout
            getChildFragmentManager().beginTransaction()
                    .add(R.id.fixedNotes, firstFragment).commit();
        }

        if (view.findViewById(R.id.olderNotes)!=null){
            NotesFromYesterdayFragment secondFragment = new NotesFromYesterdayFragment();
            getChildFragmentManager().beginTransaction().add(R.id.olderNotes, secondFragment).commit();
        }
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(ToDo toDo) {

    }

    public void updateFragView(int position){
        if(getChildFragmentManager().findFragmentById(R.id.fixedNotes) instanceof FixedNotesFragment){
            FixedNotesFragment fnFrag = (FixedNotesFragment) getChildFragmentManager().findFragmentById(R.id.fixedNotes);
            fnFrag.updateFragView(position);
        }else if(getChildFragmentManager().findFragmentById(R.id.fixedNotes) instanceof ShowInfo){
            FixedNotesFragment fnFrag = new FixedNotesFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            fnFrag.setArguments(args);

            FragmentTransaction trans = getChildFragmentManager().beginTransaction();

            trans.replace(R.id.fixedNotes, fnFrag);
            trans.addToBackStack(null);

            trans.commit();
        }
    }

    public void updateFragViewInfo(int position){

        ShowInfo tFrag = new ShowInfo();
        Bundle args = new Bundle();
        args.putInt("position", position);
        tFrag.setArguments(args);

        FragmentTransaction trans = getChildFragmentManager().beginTransaction();

        trans.replace(R.id.fixedNotes, tFrag);
        trans.addToBackStack(null);

        trans.commit();
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
