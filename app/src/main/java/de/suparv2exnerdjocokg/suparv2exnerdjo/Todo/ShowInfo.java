package de.suparv2exnerdjocokg.suparv2exnerdjo.Todo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Dimension;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.util.List;

import de.suparv2exnerdjocokg.suparv2exnerdjo.R;
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
    private boolean done;


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
            if(getArguments().getBoolean("done")){
                done = getArguments().getBoolean("done");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_info, container, false);

        List<ToDo> actualList;
        if(done){
            actualList = DummyToDos.getDone();
        }else{
            actualList = DummyToDos.getUndone();
        }

        TextView info = (TextView) view.findViewById(R.id.info_headline);
        info.setText(actualList.get(position).getTask().getName());
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.show_info);


        for(int i = 0; i < actualList.get(position).getTask().getDescription().length; i++){
            LinearLayout bigLayout = new LinearLayout(view.getContext());
            LayoutParams lparams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            lparams.setMargins(0,0,0,5);
            bigLayout.setLayoutParams(lparams);
            bigLayout.setOrientation(LinearLayout.HORIZONTAL);
            layout.addView(bigLayout);

            TextView number = new TextView(view.getContext());
            LayoutParams lparams2 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
            lparams2.setMargins(0,0,10,0);
            number.setLayoutParams(lparams2);
            number.setText(i+1+".");
            number.setTextAppearance(view.getContext(), R.style.AppTextHeadline);
            number.setTextSize(16);
            bigLayout.addView(number);



            TextView newT = new TextView(view.getContext());
            newT.setLayoutParams(lparams2);
            newT.setText(actualList.get(position).getTask().getDescription()[i]);
            newT.setTextAppearance(view.getContext(), R.style.AppTextNormal);
            newT.setGravity(Gravity.CENTER_VERTICAL);
            bigLayout.addView(newT);
        }

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
