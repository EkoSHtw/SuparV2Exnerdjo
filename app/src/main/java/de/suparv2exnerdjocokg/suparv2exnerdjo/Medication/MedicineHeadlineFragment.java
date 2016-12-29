package de.suparv2exnerdjocokg.suparv2exnerdjo.Medication;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.suparv2exnerdjocokg.suparv2exnerdjo.R;
import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyMedicine;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class MedicineHeadlineFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    private TextView substance;
    private TextView tradeName;
    private TextView intensity;
    private TextView form;
    private TextView morning;
    private TextView noon;
    private TextView afternoon;
    private TextView night;
    private TextView unit;
    private TextView information;
    private TextView reason;
    //private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MedicineHeadlineFragment() {

    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MedicineListFragment newInstance(int columnCount) {
        MedicineListFragment fragment = new MedicineListFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medication, container, false);

        TextView[] array = new TextView[11];

        array[0] = substance = (TextView) view.findViewById(R.id.substance);
        array[1] = tradeName = (TextView) view.findViewById(R.id.trade_name);
        array[2] = intensity = (TextView) view.findViewById(R.id.intensity);
        array[3] = form = (TextView) view.findViewById(R.id.form);
        array[4] = morning = (TextView) view.findViewById(R.id.morning);
        array[5] = noon = (TextView) view.findViewById(R.id.noon);
        array[6] = afternoon = (TextView) view.findViewById(R.id.afternoon);
        array[7] = night = (TextView) view.findViewById(R.id.night);
        array[8] = unit = (TextView) view.findViewById(R.id.unit);
        array[9] = information = (TextView) view.findViewById(R.id.information);
        array[10] = reason = (TextView) view.findViewById(R.id.reason);

        substance.setText("Wirkstoff");
        substance.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
        tradeName.setText("Handelsname");
        tradeName.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
        intensity.setText("Stärke");
        intensity.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
        form.setText("Form");
        form.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
        morning.setText("Morgens");
        morning.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
        morning.setTextSize(10);
        noon.setText("Mittags");
        noon.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
        noon.setTextSize(10);
        afternoon.setText("Abends");
        afternoon.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
        afternoon.setTextSize(10);
        night.setText("Zur Nacht");
        night.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
        night.setTextSize(10);
        unit.setText("Einheit");
        unit.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
        information.setText("Hinweise");
        information.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
        reason.setText("Grund");
        reason.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
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
    /*public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Medicine item);
    }*/
}
