package de.suparv2exnerdjocokg.suparv2exnerdjo.Vital;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import de.suparv2exnerdjocokg.suparv2exnerdjo.ClientViewActivity;
import de.suparv2exnerdjocokg.suparv2exnerdjo.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * <p>
 * to handle interaction events.
 * Use the {@link VitalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VitalFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    private View view;
    private Context context;
    private GraphView graph;
    private BloodPressure bloodPressureFragment;
    private Temperature temperatureFragment;
    private BloodSugar bloodSugarFragment;


    private TextView oldSelection;
    SimpleDateFormat format;

    //private OnFragmentInteractionListener mListener;

    public VitalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VitalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VitalFragment newInstance(String param1, String param2) {
        VitalFragment fragment = new VitalFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_vital, container, false);
        context = view.getContext();

        VitalValues v = ((ClientViewActivity) getActivity()).getClient().getVital();

        bloodPressureFragment = new BloodPressure();
        temperatureFragment = new Temperature();
        bloodSugarFragment = new BloodSugar();


        final TextView bloodPressure = (TextView) view.findViewById(R.id.blood_pressure);
        bloodPressure.setText("Blutdruck");

        oldSelection = bloodPressure;
        bloodPressure.setTextAppearance(context, R.style.AppTextHeadline);
        getChildFragmentManager().beginTransaction().add(R.id.graph_container, bloodPressureFragment).commit();

        bloodPressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                oldSelection = bloodPressure;
                Log.i("", "click detected");
                bloodPressure.setTextAppearance(context, R.style.AppTextHeadline);
                getChildFragmentManager().beginTransaction().replace(R.id.graph_container, bloodPressureFragment).commit();
            }
        });

        final TextView bloodSugar = (TextView) view.findViewById(R.id.blood_sugar);
        bloodSugar.setText("Blutzucker");

        bloodSugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                oldSelection = bloodSugar;
                bloodSugar.setTextAppearance(context, R.style.AppTextHeadline);
                getChildFragmentManager().beginTransaction().replace(R.id.graph_container, bloodSugarFragment).commit();
            }
        });


        final TextView temp = (TextView) view.findViewById(R.id.temp);
        temp.setText("Körpertemperatur");

        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                oldSelection = temp;
                temp.setTextAppearance(context, R.style.AppTextHeadline);
                getChildFragmentManager().beginTransaction().replace(R.id.graph_container, temperatureFragment).commit();
            }
        });

        ImageButton plus = (ImageButton) view.findViewById(R.id.plus_button);
        plus.setColorFilter(R.color.colorAccent);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogChangeValue dialog = new DialogChangeValue(context, mListener, oldSelection.getId());
                dialog.show();
            }
        });




        return view;
    }



    private void clearSelection() {
        if (oldSelection != null) {
            oldSelection.setTextAppearance(context, R.style.AppTextNormal);
        }
    }

    public void addValue(final double value, final int secondValue, int id) throws InterruptedException {

        Calendar c = Calendar.getInstance();

        switch (id) {
            case R.id.temp:
                temperatureFragment.valueChanged(value);
                temperatureFragment = new Temperature();
                getChildFragmentManager().beginTransaction().replace(R.id.graph_container, temperatureFragment).commit();
                break;
            case R.id.blood_pressure:
                bloodPressureFragment.valueChanged((int)value, secondValue);
                bloodPressureFragment = new BloodPressure();
                getChildFragmentManager().beginTransaction().replace(R.id.graph_container, bloodPressureFragment).commit();
                break;
            case R.id.blood_sugar:
                bloodSugarFragment.valueChanged((int)value);
                bloodSugarFragment = new BloodSugar();
                getChildFragmentManager().beginTransaction().replace(R.id.graph_container, bloodSugarFragment).commit();
                break;
        }
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

/*
 * This interface must be implemented by activities that contain this
 * fragment to allow an interaction in this fragment to be communicated
 * to the activity and potentially other fragments contained in that
 * activity.
 * <p>
 * See the Android Training lesson <a href=
 * "http://developer.android.com/training/basics/fragments/communicating.html"
 * >Communicating with Other Fragments</a> for more information.
*/
public interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
    void newValueAdded(int value, int second, int id) throws InterruptedException;
}
}
