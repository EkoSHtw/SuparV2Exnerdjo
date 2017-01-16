package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.BaseSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    private OnFragmentInteractionListener mListener;
    private final Handler mHandler = new Handler();
    private View view;
    private Context context;
    private GraphView graph;
    private LineGraphSeries<DataPoint> diastolicB;
    private DataPoint[] diastolicData;
    private LineGraphSeries<DataPoint> systolicB;
    private DataPoint[] systolicData;
    private LineGraphSeries<DataPoint> temp = new LineGraphSeries<>();
    private static DataPoint[] tempData;
    private LineGraphSeries<DataPoint> bloodSugar;
    private DataPoint[] bloodSugarData;
    private TextView oldSelection;
    private Date[] dates = new Date[7];


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
        view = inflater.inflate(R.layout.fragment_vital, container, false);
        context = view.getContext();

        graph = (GraphView) view.findViewById(R.id.graph);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        dates[6] = calendar.getTime();
        calendar.add(Calendar.DATE, -1);
        dates[5] = calendar.getTime();
        calendar.add(Calendar.DATE, -1);
        dates[4] = calendar.getTime();
        calendar.add(Calendar.DATE, -1);
        dates[3] = calendar.getTime();
        calendar.add(Calendar.DATE, -1);
        dates[2] = calendar.getTime();
        calendar.add(Calendar.DATE, -1);
        dates[1] = calendar.getTime();
        calendar.add(Calendar.DATE, -1);
        dates[0] = calendar.getTime();

        final TextView bloodPressure = (TextView) view.findViewById(R.id.blood_pressure);
        bloodPressure.setText("Blutdruck");

        oldSelection = bloodPressure;
        bloodPressure.setTextAppearance(context, R.style.AppTextHeadline);
        createGraph(graph, setBloodPressureTable());
        setBloodPressureValues(graph);

        bloodPressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                oldSelection = bloodPressure;
                Log.i("", "click detected");
                bloodPressure.setTextAppearance(context, R.style.AppTextHeadline);
                createGraph(graph, setBloodPressureTable());
                setBloodPressureValues(graph);
            }
        });

        final TextView bloodSugar  = (TextView) view.findViewById(R.id.blood_sugar);
        bloodSugar.setText("Blutzucker");

        bloodSugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                oldSelection = bloodSugar;
                bloodSugar.setTextAppearance(context, R.style.AppTextHeadline);
                createGraph(graph, setBloodSugarTable());
                setBloodSugarValues(graph);
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
                createGraph(graph, setTempTable());
                setTempValues(graph);
            }
        });

        ImageButton plus = (ImageButton) view.findViewById(R.id.plus_button);
        plus.setColorFilter(R.color.colorAccent);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAddVitalValue dialog = new DialogAddVitalValue(context, mListener, oldSelection.getId());
                dialog.show();
            }
        });



        return view;
    }

    private void createGraph(GraphView graph, LineGraphSeries[] series){

        graph.removeAllSeries();

        for(int i = 0; i < series.length; i++) {
            graph.addSeries(series[i]);
        }

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.GERMAN);
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity(), dateFormat));
        graph.getGridLabelRenderer().setNumHorizontalLabels(7);
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Datum");
        graph.getGridLabelRenderer().setHorizontalLabelsVisible(true);
        graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);
        graph.getGridLabelRenderer().setNumVerticalLabels(5);

        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graph.getLegendRenderer().setBackgroundColor(Color.argb(0,0,0,0));

        graph.getViewport().setMinX(dates[0].getTime());
        graph.getViewport().setMaxX(dates[6].getTime());
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getGridLabelRenderer().setHumanRounding(false);
    }

    private LineGraphSeries[] setBloodPressureTable() {

        diastolicData = new DataPoint[]{
                new DataPoint(dates[0], 79),
                new DataPoint(dates[1], 81),
                new DataPoint(dates[2], 82),
                new DataPoint(dates[3], 78),
                new DataPoint(dates[4], 79),
                new DataPoint(dates[5], 77),
                new DataPoint(dates[6], 78)
        };

        diastolicB = new LineGraphSeries<>(diastolicData);

        diastolicB.setTitle("Diastolischer Blutdruck");
        diastolicB.setColor(Color.argb(255, 104, 159, 56));
        diastolicB.setDrawDataPoints(true);
        diastolicB.setBackgroundColor(R.color.transparent);
        diastolicB.setDrawBackground(false);

        systolicData = new DataPoint[]{
                new DataPoint(dates[0], 119),
                new DataPoint(dates[1], 122),
                new DataPoint(dates[2], 120),
                new DataPoint(dates[3], 119),
                new DataPoint(dates[4], 119),
                new DataPoint(dates[5], 115),
                new DataPoint(dates[6], 116)
        };

        systolicB = new LineGraphSeries<>(systolicData);

        systolicB.setTitle("Systolischer Blutdruck");
        systolicB.setColor(Color.argb(255, 104, 125, 56));
        systolicB.setDrawDataPoints(true);
        systolicB.setBackgroundColor(R.color.colorAccent);
        systolicB.setDrawBackground(false);

        return new LineGraphSeries[]{systolicB, diastolicB};
    }

    private void setBloodPressureValues(GraphView graph){

        graph.getViewport().setMinY(70);
        graph.getViewport().setMaxY(150);
        graph.getViewport().setYAxisBoundsManual(true);

        graph.getGridLabelRenderer().setVerticalAxisTitle("mmHg");
        graph.getGridLabelRenderer().setVerticalLabelsVisible(true);

    }

    private LineGraphSeries[] setBloodSugarTable() {

        final Date[][] dates1 = new Date[7][4];

        for (int i = 0; i < dates.length; i++) {

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dates[i]);
            calendar.add(Calendar.HOUR, 9);
            dates1[i][0] = calendar.getTime();
            calendar.add(Calendar.HOUR, 3);
            dates1[i][1] = calendar.getTime();
            calendar.add(Calendar.HOUR, 4);
            dates1[i][2] = calendar.getTime();
            calendar.add(Calendar.HOUR, 4);
            dates1[i][3] = calendar.getTime();
        }

        bloodSugarData = new DataPoint[]{
                new DataPoint(dates1[0][0], 106),
                new DataPoint(dates1[0][1], 100),
                new DataPoint(dates1[0][2], 97),
                new DataPoint(dates1[0][3], 99),
                new DataPoint(dates1[1][0], 136),
                new DataPoint(dates1[1][1], 104),
                new DataPoint(dates1[1][2], 142),
                new DataPoint(dates1[1][3], 119),
                new DataPoint(dates1[2][0], 110),
                new DataPoint(dates1[2][1], 68),
                new DataPoint(dates1[2][2], 81),
                new DataPoint(dates1[2][3], 86),
                new DataPoint(dates1[3][0], 114),
                new DataPoint(dates1[3][1], 110),
                new DataPoint(dates1[3][2], 91),
                new DataPoint(dates1[3][3], 167),
                new DataPoint(dates1[4][0], 94),
                new DataPoint(dates1[4][1], 83),
                new DataPoint(dates1[4][2], 78),
                new DataPoint(dates1[4][3], 98),
                new DataPoint(dates1[5][0], 103),
                new DataPoint(dates1[5][1], 125),
                new DataPoint(dates1[5][2], 130),
                new DataPoint(dates1[5][3], 111),
                new DataPoint(dates1[6][0], 95),
                new DataPoint(dates1[6][1], 98),
                new DataPoint(dates1[6][2], 120),
                new DataPoint(dates1[6][3], 130)
        };

        bloodSugar = new LineGraphSeries<>(bloodSugarData);

        /*
        final DataPoint newD = new DataPoint(new Date(), newValue);

        if (newValue != -1) {
            Runnable mTimer1 = new Runnable() {
                @Override
                public void run() {

                    bloodSugar.appendData(newD, true, dates.length * dates.length);
                }
            } ;
            mHandler.postDelayed(mTimer1, 300);
        }


        if (newValue != -1) {
            bloodSugar.appendData(newD, true, dates.length*dates.length);
        }

        Log.i("", "X: " + newD.getX() +", Y: "+ newD.getY());

        */

        bloodSugar.setTitle("Blutzucker");
        bloodSugar.setColor(getResources().getColor(R.color.colorAccent));
        bloodSugar.setDrawDataPoints(true);

        return new LineGraphSeries[]{bloodSugar};
    }

    private void setBloodSugarValues(GraphView graph){

        graph.getViewport().setMinY(60);
        graph.getViewport().setMaxY(200);
        graph.getViewport().setYAxisBoundsManual(true);

        graph.getGridLabelRenderer().setVerticalAxisTitle("mg/dl");
        graph.getGridLabelRenderer().setVerticalLabelsVisible(true);

    }

    private LineGraphSeries[] setTempTable(){

        tempData = new DataPoint[]{

                new DataPoint(dates[0], 36.5),
                new DataPoint(dates[1], 36.25),
                new DataPoint(dates[2], 37.5),
                new DataPoint(dates[3], 36.7),
                new DataPoint(dates[4], 35.8),
                new DataPoint(dates[5], 36.5),
                new DataPoint(dates[6], 36)

        };

        temp.resetData(tempData);

        temp.setTitle("Körpertemperatur");
        temp.setColor(Color.argb(255, 104, 159, 56));
        temp.setDrawDataPoints(true);

        return new LineGraphSeries[]{temp};

    }

    private  void setTempValues(GraphView graph){

        graph.getViewport().setMinY(34);
        graph.getViewport().setMaxY(42);
        graph.getViewport().setYAxisBoundsManual(true);

        graph.getGridLabelRenderer().setVerticalAxisTitle("°C");
        graph.getGridLabelRenderer().setVerticalLabelsVisible(true);

    }

    private void clearSelection(){
        if(oldSelection != null) {
            oldSelection.setTextAppearance(context, R.style.AppTextNormal);
        }
    }

    public void addValue(int value, int id){

        switch (id){
            case R.id.temp:

                for(int i = 0; i < tempData.length-1; i++){
                    dates[i] = dates[i+1];
                    tempData[i] = tempData[i+1];
                }

                tempData[tempData.length-1] = new DataPoint(new Date(), value);
                dates[dates.length-1] = new Date();
                temp.resetData(tempData);

                createGraph(graph, new LineGraphSeries[]{temp});
                setTempValues(graph);

                break;
            case R.id.blood_pressure:
                setBloodPressureTable();
                break;
            case R.id.blood_sugar:
                setBloodSugarTable();
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
        void newValueAdded(int value, int id);
    }
}
