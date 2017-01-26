package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.content.Context;
import android.graphics.Color;
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
    private List<Date> bloodPressureDates;
    private LineGraphSeries<DataPoint> diastolicB;
    private HashMap<Date, Integer> diastolicValues;
    private LineGraphSeries<DataPoint> systolicB;
    private HashMap<Date, Integer> systolicValues;
    private List<Date> tempDates;
    private LineGraphSeries<DataPoint> temp;
    private HashMap<Date, Double> tempValues;
    private LineGraphSeries<DataPoint> bloodSugar;
    private HashMap<Date, Integer> bloodSugarValues;
    private List<Date> bloodSugarDates;
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

        bloodSugarDates = v.getBloodSugarDates();
        bloodSugarValues = v.getBloodSugarValues();
        bloodSugar = v.getBloodSugar();
        tempDates = v.getTempDates();
        temp = v.getTemp();
        tempValues = v.getTempValues();
        bloodPressureDates = v.getBloodPressureDates();
        diastolicB = v.getDiastolicB();
        diastolicValues = v.getDiastolicValues();
        systolicB = v.getSystolicB();
        systolicValues = v.getSystolicValues();


        graph = (GraphView) view.findViewById(R.id.graph);


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

        final TextView bloodSugar = (TextView) view.findViewById(R.id.blood_sugar);
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


        format = new SimpleDateFormat("dd.MM.yy, HH:mm", Locale.GERMAN);

        return view;
    }

    private void createGraph(GraphView graph, LineGraphSeries[] series) {

        graph.removeAllSeries();

        for (LineGraphSeries i : series) {
            graph.addSeries(i);
        }

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.GERMAN);
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity(), dateFormat));
        graph.getGridLabelRenderer().setNumHorizontalLabels(7);
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Datum");
        graph.getGridLabelRenderer().setHorizontalLabelsVisible(true);
        graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);

        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graph.getLegendRenderer().setBackgroundColor(Color.argb(0, 0, 0, 0));

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getGridLabelRenderer().setHumanRounding(false);
        graph.onDataChanged(true, true);
    }

    private LineGraphSeries[] setBloodPressureTable() {

        DataPoint[] diastolicData = new DataPoint[diastolicValues.size()];

        for (int i = 0; i < diastolicValues.size(); i++) {
            diastolicData[i] = new DataPoint(bloodPressureDates.get(i), diastolicValues.get(bloodPressureDates.get(i)));
        }

        diastolicB.resetData(diastolicData);

        diastolicB.setTitle("Diastolischer Blutdruck");
        diastolicB.setColor(Color.argb(255, 104, 159, 56));
        diastolicB.setDrawDataPoints(true);
        diastolicB.setBackgroundColor(R.color.transparent);
        diastolicB.setDrawBackground(false);
        diastolicB.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                LayoutInflater inflater = getLayoutInflater(getArguments());
                View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) view.findViewById(R.id.custom_toast_container));

                TextView title = (TextView) layout.findViewById(R.id.title);
                title.setText("Diastolischer Blutdruck: ");

                TextView value = (TextView) layout.findViewById(R.id.value);
                value.setText("Wert: " + dataPoint.getY() + " mmHg");

                setToast(dataPoint, layout);
            }
        });


        DataPoint[] systolicData = new DataPoint[systolicValues.size()];

        for (int i = 0; i < systolicValues.size(); i++) {
            systolicData[i] = new DataPoint(bloodPressureDates.get(i), systolicValues.get(bloodPressureDates.get(i)));
        }

        systolicB.resetData(systolicData);

        systolicB.setTitle("Systolischer Blutdruck");
        systolicB.setColor(Color.argb(255, 104, 125, 56));
        systolicB.setDrawDataPoints(true);
        systolicB.setBackgroundColor(R.color.colorAccent);
        systolicB.setDrawBackground(false);
        systolicB.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                LayoutInflater inflater = getLayoutInflater(getArguments());
                View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) view.findViewById(R.id.custom_toast_container));

                TextView title = (TextView) layout.findViewById(R.id.title);
                title.setText("Systolischer Blutdruck: ");

                TextView value = (TextView) layout.findViewById(R.id.value);
                value.setText("Wert: " + dataPoint.getY() + " mmHg");

                setToast(dataPoint, layout);
            }
        });

        return new LineGraphSeries[]{systolicB, diastolicB};


    }


    private void setBloodPressureValues(GraphView graph) {

        graph.getViewport().setMinX(bloodPressureDates.get(0).getTime());
        graph.getViewport().setMaxX(bloodPressureDates.get(bloodPressureDates.size() - 1).getTime());

        graph.getViewport().setMinY(65);
        graph.getViewport().setMaxY(190);
        graph.getViewport().setYAxisBoundsManual(true);

        graph.getGridLabelRenderer().setVerticalAxisTitle("mmHg");
        graph.getGridLabelRenderer().setVerticalLabelsVisible(true);
        graph.getGridLabelRenderer().setNumVerticalLabels(5);

    }

    private LineGraphSeries[] setBloodSugarTable() {

        DataPoint[] bloodSugarData = new DataPoint[bloodSugarValues.size()];

        for (int i = 0; i < bloodSugarValues.size(); i++) {
            bloodSugarData[i] = new DataPoint(bloodSugarDates.get(i), bloodSugarValues.get(bloodSugarDates.get(i)));
        }

        bloodSugar.resetData(bloodSugarData);

        bloodSugar.setTitle("Blutzucker");
        bloodSugar.setColor(getResources().getColor(R.color.colorAccent));
        bloodSugar.setDrawDataPoints(true);
        bloodSugar.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                LayoutInflater inflater = getLayoutInflater(getArguments());
                View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) view.findViewById(R.id.custom_toast_container));

                TextView title = (TextView) layout.findViewById(R.id.title);
                title.setText("Blutzucker: ");

                TextView value = (TextView) layout.findViewById(R.id.value);
                value.setText("Wert: " + dataPoint.getY() + " mg/dl");

                setToast(dataPoint, layout);
            }
        });

        return new LineGraphSeries[]{bloodSugar};
    }

    private void setBloodSugarValues(GraphView graph) {

        graph.getViewport().setMinX(bloodSugarDates.get(0).getTime());
        graph.getViewport().setMaxX(bloodSugarDates.get(bloodSugarDates.size() - 1).getTime());

        graph.getViewport().setMinY(60);
        graph.getViewport().setMaxY(200);
        graph.getViewport().setYAxisBoundsManual(true);

        graph.getGridLabelRenderer().setVerticalAxisTitle("mg/dl");
        graph.getGridLabelRenderer().setVerticalLabelsVisible(true);
        graph.getGridLabelRenderer().setNumVerticalLabels(5);

    }

    private LineGraphSeries[] setTempTable() {

        DataPoint[] tempData = new DataPoint[tempValues.size()];

        for (int i = 0; i < tempValues.size(); i++) {
            tempData[i] = new DataPoint(tempDates.get(i), tempValues.get(tempDates.get(i)));
        }

        temp.resetData(tempData);

        temp.setTitle("Körpertemperatur");
        temp.setColor(Color.argb(255, 104, 159, 56));
        temp.setDrawDataPoints(true);
        temp.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                LayoutInflater inflater = getLayoutInflater(getArguments());
                View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) view.findViewById(R.id.custom_toast_container));

                TextView title = (TextView) layout.findViewById(R.id.title);
                title.setText("Temperatur: ");

                TextView value = (TextView) layout.findViewById(R.id.value);
                value.setText("Wert: " + dataPoint.getY() + " °C");

                setToast(dataPoint, layout);
            }
        });

        return new LineGraphSeries[]{temp};

    }

    private void setTempValues(GraphView graph) {

        graph.getViewport().setMinX(tempDates.get(0).getTime());
        graph.getViewport().setMaxX(tempDates.get(tempDates.size() - 1).getTime());

        graph.getViewport().setMinY(34);
        graph.getViewport().setMaxY(42);
        graph.getViewport().setYAxisBoundsManual(true);

        graph.getGridLabelRenderer().setVerticalAxisTitle("°C");
        graph.getGridLabelRenderer().setVerticalLabelsVisible(true);
        graph.getGridLabelRenderer().setNumVerticalLabels(5);

    }

    private void clearSelection() {
        if (oldSelection != null) {
            oldSelection.setTextAppearance(context, R.style.AppTextNormal);
        }
    }

    public void addValue(final int value, final int secondValue, int id) {

        Calendar c = Calendar.getInstance();

        switch (id) {
            case R.id.temp:

                Date lastTime = tempDates.get(tempDates.size() - 1);
                Date time = c.getTime();

                if (lastTime.getDate() == time.getDate()) {
                    tempValues.remove(tempDates.get(tempDates.size() - 1));
                    Iterator<Date> iter = tempDates.iterator();
                    do {
                        iter.next();
                        if (!iter.hasNext()) {
                            iter.remove();
                        }
                    } while (iter.hasNext());
                }
                tempDates.add(time);
                tempValues.put(tempDates.get(tempDates.size() - 1), (double) value);

                createGraph(graph, setTempTable());
                setTempValues(graph);
                break;

            case R.id.blood_pressure:

                int last = bloodPressureDates.size() - 1;
                lastTime = bloodPressureDates.get(last);
                time = c.getTime();

                if (lastTime.getDate() == time.getDate()) {
                    systolicValues.remove(bloodPressureDates.get(last));
                    diastolicValues.remove(bloodPressureDates.get(last));
                    Iterator<Date> iter = bloodPressureDates.iterator();
                    do {
                        iter.next();
                        if (!iter.hasNext()) {
                            iter.remove();
                        }
                    } while (iter.hasNext());
                }

                bloodPressureDates.add(time);
                systolicValues.put(bloodPressureDates.get(last), value);
                diastolicValues.put(bloodPressureDates.get(last), secondValue);

                createGraph(graph, setBloodPressureTable());
                setBloodPressureValues(graph);

                break;
            case R.id.blood_sugar:
                int lastI = bloodSugarDates.size() - 1;
                lastTime = bloodSugarDates.get(lastI);
                time = c.getTime();

                if (lastTime.getDate() == time.getDate()) {
                    bloodSugarValues.remove(bloodSugarDates.get(lastI));
                    Iterator<Date> iter = bloodSugarDates.iterator();
                    do {
                        iter.next();
                        if (!iter.hasNext()) {
                            iter.remove();
                        }
                    } while (iter.hasNext());
                }

                bloodSugarDates.add(time);
                bloodSugarValues.put(bloodSugarDates.get(lastI), value);

                createGraph(graph, setBloodSugarTable());
                setBloodSugarValues(graph);

                break;
        }
    }

    private void setToast(DataPointInterface dataPoint, View layout) {

        Date currentTime = new Date(new Double(dataPoint.getX()).longValue());

        String dateString = format.format(currentTime);

        TextView time = (TextView) layout.findViewById(R.id.time);
        time.setText("Zeit: " + dateString + " Uhr");

        Toast toast = new Toast(getContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
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
        void newValueAdded(int value, int second, int id);
    }
}
