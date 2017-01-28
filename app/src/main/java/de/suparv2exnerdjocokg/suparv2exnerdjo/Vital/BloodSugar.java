package de.suparv2exnerdjocokg.suparv2exnerdjo.Vital;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * Created by dsfd on 28.01.2017.
 */

public class BloodSugar extends Fragment {

    private View view;
    private Context context;
    private LineGraphSeries<DataPoint> bloodSugar;
    private HashMap<Date, Integer> bloodSugarValues;
    private List<Date> bloodSugarDates;
    private GraphView graph;
    private SimpleDateFormat format;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.graph_layout, container, false);
        context = view.getContext();

        VitalValues v = ((ClientViewActivity) getActivity()).getClient().getVital();

        bloodSugarDates = v.getBloodSugarDates();
        bloodSugarValues = v.getBloodSugarValues();
        bloodSugar = v.getBloodSugar();

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

        graph = (GraphView) view.findViewById(R.id.graph);

        graph.addSeries(bloodSugar);

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

        graph.getViewport().setMinX(bloodSugarDates.get(0).getTime());
        graph.getViewport().setMaxX(bloodSugarDates.get(bloodSugarDates.size() - 1).getTime());

        graph.getViewport().setMinY(60);
        graph.getViewport().setMaxY(200);
        graph.getViewport().setYAxisBoundsManual(true);

        graph.getGridLabelRenderer().setVerticalAxisTitle("mg/dl");
        graph.getGridLabelRenderer().setVerticalLabelsVisible(true);
        graph.getGridLabelRenderer().setNumVerticalLabels(5);

        format = new SimpleDateFormat("dd.MM.yy, HH:mm", Locale.GERMAN);

        return view;
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

    public void valueChanged(int value){

        Calendar c = Calendar.getInstance();

        int lastI = bloodSugarDates.size() - 1;
        Date lastTime = bloodSugarDates.get(lastI);
        Date time = c.getTime();

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
        bloodSugarValues.put(bloodSugarDates.get(bloodSugarDates.size() - 1), value);
    }

}
