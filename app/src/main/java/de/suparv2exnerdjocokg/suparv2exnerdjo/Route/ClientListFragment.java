package de.suparv2exnerdjocokg.suparv2exnerdjo.Route;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Client;
import de.suparv2exnerdjocokg.suparv2exnerdjo.R;
import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyClients;



public class ClientListFragment extends Fragment{

    //    private EditText editText;

    private OnListFragmentInteractionListener mListener;

    private int currentSpinnerSelection;
    private RecyclerView mRecyclerView;


    public ClientListFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        Log.println(Log.INFO,"m", "HALLO");
//        View searchBar = inflater.inflate(R.layout.logbook_searchbar,container,false);
        View view = inflater.inflate(R.layout.client_list, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.clientListRecycler);
        mRecyclerView.setAdapter(new MyClientRecyclerViewAdapter(DummyClients.ITEMS, mListener));


        return view;
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(int position);
    }
}
