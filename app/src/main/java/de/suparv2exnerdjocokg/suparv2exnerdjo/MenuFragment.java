package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Todo.TodoFragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageButton back;
    private TextView todo;
    private TextView basicData;
    private TextView log;
    private TextView vital;
    private TextView medicine;
    private TextView oldSelection;
    private OnMenuFragmentInteractionListener mListener;

    public MenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, container, false);

        back = (ImageButton) v.findViewById(R.id.backToClients);
        back.setColorFilter(getResources().getColor(R.color.colorAccent));
        todo = (TextView) v.findViewById(R.id.client_view);
        todo.setTextColor(getResources().getColor(R.color.colorAccent));
        oldSelection = todo;
        basicData = (TextView) v.findViewById(R.id.basic_data);
        log = (TextView) v.findViewById(R.id.log);
        vital = (TextView) v.findViewById(R.id.vital_values);
        medicine = (TextView) v.findViewById(R.id.medication);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onMenuFragmentInteraction(-1);
            }
        });

        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onMenuFragmentInteraction(0);

                clearSelection();
                oldSelection = todo;
                todo.setTextColor(getResources().getColor(R.color.colorAccent));
            }
        });
        basicData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onMenuFragmentInteraction(1);

                clearSelection();
                oldSelection = basicData;
                basicData.setTextColor(getResources().getColor(R.color.colorAccent));
            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onMenuFragmentInteraction(2);

                clearSelection();
                oldSelection = log;
                log.setTextColor(getResources().getColor(R.color.colorAccent));
            }
        });
        vital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onMenuFragmentInteraction(3);

                clearSelection();
                oldSelection = vital;
                vital.setTextColor(getResources().getColor(R.color.colorAccent));
            }
        });
        medicine.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mListener.onMenuFragmentInteraction(4);

                clearSelection();
                oldSelection = medicine;
                medicine.setTextColor(getResources().getColor(R.color.colorAccent));
            }
        });


        return v;
    }

    public void clearSelection() {
        if(oldSelection != null) {
            oldSelection.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TodoFragment.OnListFragmentInteractionListener) {
            mListener = (MenuFragment.OnMenuFragmentInteractionListener) context;
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
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnMenuFragmentInteractionListener {
        // TODO: Update argument type and name
        void onMenuFragmentInteraction(int position);
    }
}
