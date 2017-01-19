package de.suparv2exnerdjocokg.suparv2exnerdjo.dummy;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import de.suparv2exnerdjocokg.suparv2exnerdjo.VitalValues;

/**
 * Created by dsfd on 19.01.2017.
 */

public class DummyVitalValues {

    public VitalValues vitalValues;

    public DummyVitalValues(){

        List<Date> dates = new ArrayList<>();
        LineGraphSeries<DataPoint> diastolicB = new LineGraphSeries<>();
        int[] diastolicValues = new int[7];
        LineGraphSeries<DataPoint> systolicB = new LineGraphSeries<>();
        int[] systolicValues = new int[7];
        LineGraphSeries<DataPoint> temp = new LineGraphSeries<>();
        HashMap<Date, Double> tempValues = new HashMap<>();
        LineGraphSeries<DataPoint> bloodSugar = new LineGraphSeries<>();
        HashMap<Date, Integer> bloodSugarValues = new HashMap<>();
        List<Date> bloodSugarDates = new ArrayList<>();


        // ZEITEN

        Date[] date = new Date[7];
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        date[6] = calendar.getTime();
        calendar.add(Calendar.DATE, -1);
        date[5] = calendar.getTime();
        calendar.add(Calendar.DATE, -1);
        date[4] = calendar.getTime();
        calendar.add(Calendar.DATE, -1);
        date[3] = calendar.getTime();
        calendar.add(Calendar.DATE, -1);
        date[2] = calendar.getTime();
        calendar.add(Calendar.DATE, -1);
        date[1] = calendar.getTime();
        calendar.add(Calendar.DATE, -1);
        date[0] = calendar.getTime();
        dates.add(date[0]);
        dates.add(date[1]);
        dates.add(date[2]);
        dates.add(date[3]);
        dates.add(date[4]);
        dates.add(date[5]);
        dates.add(date[6]);


        // BLUTDRUCK

        diastolicValues = new int[]{79, 81, 82, 78, 79, 77, 78};

        systolicValues = new int[]{119, 122, 120, 119, 119, 115, 116};


        // BLUTZUCKER

        for (int i = 0; i < dates.size(); i++) {
            Calendar c = Calendar.getInstance();
            c.setTime(dates.get(i));
            c.add(Calendar.HOUR, 9);
            bloodSugarDates.add(calendar.getTime());
            c.add(Calendar.HOUR, 3);
            bloodSugarDates.add(calendar.getTime());
            c.add(Calendar.HOUR, 4);
            bloodSugarDates.add(calendar.getTime());
            c.add(Calendar.HOUR, 4);
            bloodSugarDates.add(calendar.getTime());
        }

        int[] bloodSugarValues1 = new int[]{
                106, 100, 97, 99,
                136, 104, 142, 119,
                110, 68, 81, 86,
                114, 110, 91, 167,
                94, 83, 78, 98,
                103, 125, 130, 111,
                95, 98, 120, 130,
        };

        for (int i = 0; i < bloodSugarValues1.length; i++) {
            bloodSugarValues.put(bloodSugarDates.get(i), bloodSugarValues1[i]);
        }



        // TEMPERATUR

        double[] tempValues1 = new double[]{36.5, 36.25, 37.5, 36.7, 35.8, 36.5, 36};

        for (int i = 0; i < tempValues1.length; i++) {
            tempValues.put(dates.get(i), tempValues1[i]);
        }




        vitalValues = new VitalValues(dates, dates, diastolicB, diastolicValues, systolicB, systolicValues, temp, tempValues, bloodSugar, bloodSugarValues, bloodSugarDates);
    }

}
