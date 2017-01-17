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
    private LineGraphSeries<DataPoint> diastolicB = new LineGraphSeries<>();
    private int[] diastolicValues;
    private LineGraphSeries<DataPoint> systolicB = new LineGraphSeries<>();
    private int[] systolicValues;
    private LineGraphSeries<DataPoint> temp = new LineGraphSeries<>();
    private double[] tempValues;
    private LineGraphSeries<DataPoint> bloodSugar = new LineGraphSeries<>();
    private int[][] bloodSugarValues;
    private Date[][] bloodSugarDates;
    private TextView oldSelection;
    private Date[] dates = new Date[7];
    private Calendar c;

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

        c = Calendar.getInstance();

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
        graph.onDataChanged(true, true);
    }

    private LineGraphSeries[] setBloodPressureTable() {

        if(diastolicValues == null){

            diastolicValues = new int[]{79, 81, 82, 78, 79, 77, 78};

        }

        DataPoint[] diastolicData = new DataPoint[]{
                new DataPoint(dates[0], diastolicValues[0]),
                new DataPoint(dates[1], diastolicValues[1]),
                new DataPoint(dates[2], diastolicValues[2]),
                new DataPoint(dates[3], diastolicValues[3]),
                new DataPoint(dates[4], diastolicValues[4]),
                new DataPoint(dates[5], diastolicValues[5]),
                new DataPoint(dates[6], diastolicValues[6])
        };

        diastolicB.resetData(diastolicData);

        diastolicB.setTitle("Diastolischer Blutdruck");
        diastolicB.setColor(Color.argb(255, 104, 159, 56));
        diastolicB.setDrawDataPoints(true);
        diastolicB.setBackgroundColor(R.color.transparent);
        diastolicB.setDrawBackground(false);

        if(systolicValues == null){

            systolicValues = new int[]{119, 122, 120, 119, 119, 115, 116};

        }

        DataPoint[] systolicData = new DataPoint[]{
                new DataPoint(dates[0], systolicValues[0]),
                new DataPoint(dates[1], systolicValues[1]),
                new DataPoint(dates[2], systolicValues[2]),
                new DataPoint(dates[3], systolicValues[3]),
                new DataPoint(dates[4], systolicValues[4]),
                new DataPoint(dates[5], systolicValues[5]),
                new DataPoint(dates[6], systolicValues[6])
        };

        systolicB .resetData(systolicData);

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

        if(bloodSugarValues == null) {

            bloodSugarValues = new int[][]{
                    {106, 100, 97, 99},
                    {136, 104, 142, 119},
                    {110, 68, 81, 86},
                    {114, 110, 91, 167},
                    {94, 83, 78, 98},
                    {103, 125, 130, 111},
                    {95, 98, 120, 130},
            };

            bloodSugarDates = new Date[dates.length][4];


        }

        for (int i = 0; i < dates.length; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dates[i]);
            calendar.add(Calendar.HOUR, 9);
            bloodSugarDates[i][0] = calendar.getTime();
            calendar.add(Calendar.HOUR, 3);
            bloodSugarDates[i][1] = calendar.getTime();
            calendar.add(Calendar.HOUR, 4);
            bloodSugarDates[i][2] = calendar.getTime();
            calendar.add(Calendar.HOUR, 4);
            bloodSugarDates[i][3] = calendar.getTime();
        }

        DataPoint[] bloodSugarData = new DataPoint[]{
                new DataPoint(bloodSugarDates[0][0], bloodSugarValues[0][0]),
                new DataPoint(bloodSugarDates[0][1], bloodSugarValues[0][1]),
                new DataPoint(bloodSugarDates[0][2], bloodSugarValues[0][2]),
                new DataPoint(bloodSugarDates[0][3], bloodSugarValues[0][3]),
                new DataPoint(bloodSugarDates[1][0], bloodSugarValues[1][0]),
                new DataPoint(bloodSugarDates[1][1], bloodSugarValues[1][1]),
                new DataPoint(bloodSugarDates[1][2], bloodSugarValues[1][2]),
                new DataPoint(bloodSugarDates[1][3], bloodSugarValues[1][3]),
                new DataPoint(bloodSugarDates[2][0], bloodSugarValues[2][0]),
                new DataPoint(bloodSugarDates[2][1], bloodSugarValues[2][1]),
                new DataPoint(bloodSugarDates[2][2], bloodSugarValues[2][2]),
                new DataPoint(bloodSugarDates[2][3], bloodSugarValues[2][3]),
                new DataPoint(bloodSugarDates[3][0], bloodSugarValues[3][0]),
                new DataPoint(bloodSugarDates[3][1], bloodSugarValues[3][1]),
                new DataPoint(bloodSugarDates[3][2], bloodSugarValues[3][2]),
                new DataPoint(bloodSugarDates[3][3], bloodSugarValues[3][3]),
                new DataPoint(bloodSugarDates[4][0], bloodSugarValues[4][0]),
                new DataPoint(bloodSugarDates[4][1], bloodSugarValues[4][1]),
                new DataPoint(bloodSugarDates[4][2], bloodSugarValues[4][2]),
                new DataPoint(bloodSugarDates[4][3], bloodSugarValues[4][3]),
                new DataPoint(bloodSugarDates[5][0], bloodSugarValues[5][0]),
                new DataPoint(bloodSugarDates[5][1], bloodSugarValues[5][1]),
                new DataPoint(bloodSugarDates[5][2], bloodSugarValues[5][2]),
                new DataPoint(bloodSugarDates[5][3], bloodSugarValues[5][3]),
                new DataPoint(bloodSugarDates[6][0], bloodSugarValues[6][0]),
                new DataPoint(bloodSugarDates[6][1], bloodSugarValues[6][1]),
                new DataPoint(bloodSugarDates[6][2], bloodSugarValues[6][2]),
                new DataPoint(bloodSugarDates[6][3], bloodSugarValues[6][3])
        };

        bloodSugar.resetData(bloodSugarData);

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

        if (tempValues == null) {

            tempValues = new double[]{36.5, 36.25, 37.5, 36.7, 35.8, 36.5, 36};

        }

            DataPoint[] tempData = new DataPoint[]{

                    new DataPoint(dates[0], tempValues[0]),
                    new DataPoint(dates[1], tempValues[1]),
                    new DataPoint(dates[2], tempValues[2]),
                    new DataPoint(dates[3], tempValues[3]),
                    new DataPoint(dates[4], tempValues[4]),
                    new DataPoint(dates[5], tempValues[5]),
                    new DataPoint(dates[6], tempValues[6])

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

    public void addValue(final int value, int id) {

        switch (id) {
            case R.id.temp:

                for(int i = 0; i < tempValues.length-1; i++){
                    dates[i] = dates[i+1];
                    tempValues[i] = tempValues[i+1];
                }

                c.add(Calendar.HOUR, 10);

                tempValues[tempValues.length-1] = value;
                dates[dates.length-1] = c.getTime();

                createGraph(graph, setTempTable());
                setTempValues(graph);


                break;
            case R.id.blood_pressure:
                setBloodPressureTable();
                break;
            case R.id.blood_sugar:

                Date lastTime = bloodSugarDates[bloodSugarDates.length-1][bloodSugarDates[0].length-1];
                Date time = c.getTime();
                if(lastTime.getDate()== time.getDate()){
                    if(lastTime.getHours() < time.getHours()){

                        for(int i = 0; i < dates.length-1; i++){
                            dates[i] = dates[i+1];
                            bloodSugarValues[i] = bloodSugarValues[i+1];
                        }
                        dates[dates.length-1] = time;
                        bloodSugarValues[bloodSugarValues.length-1][0] = value;
                    }
                }



                createGraph(graph, setBloodSugarTable());
                setBloodSugarValues(graph);

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
