package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyToDos;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ShowInfo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowInfo extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_Position = "position";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int position;
    private String mParam2;


    public ShowInfo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment ShowInfo.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowInfo newInstance(int position) {
        ShowInfo fragment = new ShowInfo();
        Bundle args = new Bundle();
        args.putInt(ARG_Position, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_Position);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_info, container, false);

        TextView info = (TextView) view.findViewById(R.id.infoText);
        info.setText(DummyToDos.ITEMS.get(position).getTask().getDescription());

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    public void updateFragViewInfo(int position){

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
