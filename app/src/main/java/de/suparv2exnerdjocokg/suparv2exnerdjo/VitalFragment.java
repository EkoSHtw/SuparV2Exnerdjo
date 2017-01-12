package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 * Use the {@link VitalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VitalFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context context;
    private TextView oldSelection;
    private Date d0;
    private Date d1;
    private Date d2;
    private Date d3;
    private Date d4;
    private Date d5;
    private Date d6;


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
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_vital, container, false);
        context = view.getContext();

        final GraphView graph = (GraphView) view.findViewById(R.id.graph);

        Calendar calendar = Calendar.getInstance();
        d6 = calendar.getTime();
        calendar.add(Calendar.DATE, -1);
        d5 = calendar.getTime();
        calendar.add(Calendar.DATE, -1);
        d4 = calendar.getTime();
        calendar.add(Calendar.DATE, -1);
        d3 = calendar.getTime();
        calendar.add(Calendar.DATE, -1);
        d2 = calendar.getTime();
        calendar.add(Calendar.DATE, -1);
        d1 = calendar.getTime();
        calendar.add(Calendar.DATE, -1);
        d0 = calendar.getTime();

        final TextView bloodPressure = (TextView) view.findViewById(R.id.blood_pressure);
        bloodPressure.setText("Blutdruck");

        oldSelection = bloodPressure;
        bloodPressure.setTextAppearance(context, R.style.AppTextHeadline);
        setBloodPressureTable(graph);

        bloodPressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                oldSelection = bloodPressure;
                Log.i("", "click detected");
                bloodPressure.setTextAppearance(context, R.style.AppTextHeadline);
                setBloodPressureTable(graph);
            }
        });

        final TextView bloodSugar  = (TextView) view.findViewById(R.id.blood_sugar);
        bloodSugar.setText("Blutzucker");

        bloodSugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                oldSelection = bloodSugar;
                setBloodSugarTable(graph);
                bloodSugar.setTextAppearance(context, R.style.AppTextHeadline);
            }
        });


        final TextView temp = (TextView) view.findViewById(R.id.temp);
        temp.setText("Körpertemperatur");

        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                oldSelection = temp;
                setTempTable(graph);
                temp.setTextAppearance(context, R.style.AppTextHeadline);
            }
        });

        ImageButton plus = (ImageButton) view.findViewById(R.id.plus_button);
        plus.setColorFilter(R.color.colorAccent);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAddVitalValue dialog = new DialogAddVitalValue(context);
                dialog.show();
            }
        });

        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
        graph.getGridLabelRenderer().setNumHorizontalLabels(7);

        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graph.getLegendRenderer().setBackgroundColor(Color.argb(0,0,0,0));

        graph.getViewport().setMinX(d0.getTime());
        graph.getViewport().setMaxX(d6.getTime());
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getGridLabelRenderer().setHumanRounding(false);

        return view;
    }

    private void setBloodPressureTable(GraphView graph) {
        graph.removeAllSeries();
        LineGraphSeries<DataPoint> diastolicB = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(d0, 79),
                new DataPoint(d1, 81),
                new DataPoint(d2, 82),
                new DataPoint(d3, 78),
                new DataPoint(d4, 79),
                new DataPoint(d5, 77),
                new DataPoint(d6, 78)
        });
        diastolicB.setTitle("Diastolischer Blutdruck");
        diastolicB.setColor(Color.argb(255, 104, 159, 56));
        diastolicB.setDrawDataPoints(true);
        diastolicB.setBackgroundColor(R.color.transparent);
        diastolicB.setDrawBackground(false);
        LineGraphSeries<DataPoint> systolicB = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(d0, 119),
                new DataPoint(d1, 122),
                new DataPoint(d2, 120),
                new DataPoint(d3, 119),
                new DataPoint(d4, 119),
                new DataPoint(d5, 115),
                new DataPoint(d6, 116)
        });
        systolicB.setTitle("Systolischer Blutdruck");
        systolicB.setColor(Color.argb(255, 104, 125, 56));
        systolicB.setDrawDataPoints(true);
        systolicB.setBackgroundColor(R.color.colorAccent);
        systolicB.setDrawBackground(false);
        graph.addSeries(systolicB);
        graph.addSeries(diastolicB);

        graph.getViewport().setMinY(70);
        graph.getViewport().setMaxY(150);
        graph.getViewport().setYAxisBoundsManual(true);
    }

    private void setBloodSugarTable(GraphView graph){
        graph.removeAllSeries();
        LineGraphSeries<DataPoint> morning = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(d0, 106),
                new DataPoint(d1, 136),
                new DataPoint(d2, 110),
                new DataPoint(d3, 114),
                new DataPoint(d4, 94),
                new DataPoint(d5, 103),
                new DataPoint(d6, 95)
        });
        morning.setTitle("Morgens");
        morning.setColor(Color.argb(255, 104, 125, 56));
        morning.setDrawDataPoints(true);
        LineGraphSeries<DataPoint> noon = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(d0, 100),
                new DataPoint(d1, 104),
                new DataPoint(d2, 68),
                new DataPoint(d3, 110),
                new DataPoint(d4, 83),
                new DataPoint(d5, 125),
                new DataPoint(d6, 98)
        });
        noon.setTitle("Mittags");
        noon.setColor(Color.argb(255, 80, 159, 90));
        noon.setDrawDataPoints(true);
        LineGraphSeries<DataPoint> afternoon = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(d0, 97),
                new DataPoint(d1, 142),
                new DataPoint(d2, 81),
                new DataPoint(d3, 91),
                new DataPoint(d4, 78),
                new DataPoint(d5, 130),
                new DataPoint(d6, 120)
        });
        afternoon.setTitle("Abends");
        afternoon.setColor(Color.argb(255, 140, 159, 56));
        afternoon.setDrawDataPoints(true);
        LineGraphSeries<DataPoint> night = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(d0, 99),
                new DataPoint(d1, 119),
                new DataPoint(d2, 86),
                new DataPoint(d3, 167),
                new DataPoint(d4, 98),
                new DataPoint(d5, 111),
                new DataPoint(d6, 130)
        });
        night.setTitle("Nachts");
        night.setColor(Color.argb(255, 104, 159, 56));
        night.setDrawDataPoints(true);
        graph.addSeries(morning);
        graph.addSeries(noon);
        graph.addSeries(afternoon);
        graph.addSeries(night);

        graph.getViewport().setMinY(60);
        graph.getViewport().setMaxY(200);
        graph.getViewport().setYAxisBoundsManual(true);
    }

    private void setTempTable(GraphView graph){
        graph.removeAllSeries();
        LineGraphSeries<DataPoint> temp = new LineGraphSeries<>(new DataPoint[] {

                new DataPoint(d0,36.5),
                new DataPoint(d1,36.25),
                new DataPoint(d2, 37.5),
                new DataPoint(d3, 36.7),
                new DataPoint(d4, 35.8),
                new DataPoint(d5, 36.5),
                new DataPoint(d6, 36)

        });
        temp.setTitle("Körpertemperatur");
        temp.setColor(Color.argb(255, 104, 159, 56));
        temp.setDrawDataPoints(true);
        graph.addSeries(temp);

        graph.getViewport().setMinY(30);
        graph.getViewport().setMaxY(50);
        graph.getViewport().setYAxisBoundsManual(true);
    }

    private void clearSelection(){
        if(oldSelection != null) {
            oldSelection.setTextAppearance(context, R.style.AppTextNormal);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        /*if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }*/
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       /* if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
