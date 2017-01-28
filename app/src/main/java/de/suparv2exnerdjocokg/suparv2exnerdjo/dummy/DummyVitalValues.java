package de.suparv2exnerdjocokg.suparv2exnerdjo.dummy;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Vital.VitalValues;

/**
 * Created by dsfd on 19.01.2017.
 */

public class DummyVitalValues {

    public VitalValues vitalValues;

    public DummyVitalValues(){

        List<Date> tempDates = new ArrayList<>();
        List<Date> bloodPressureDates = new ArrayList<>();
        LineGraphSeries<DataPoint> diastolicB = new LineGraphSeries<>();
        HashMap<Date, Integer> diastolicValues = new HashMap<>();
        LineGraphSeries<DataPoint> systolicB = new LineGraphSeries<>();
        HashMap<Date, Integer> systolicValues = new HashMap<>();
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
        tempDates.add(date[0]);
        tempDates.add(date[1]);
        tempDates.add(date[2]);
        tempDates.add(date[3]);
        tempDates.add(date[4]);
        tempDates.add(date[5]);
        tempDates.add(date[6]);

        bloodPressureDates.add(date[0]);
        bloodPressureDates.add(date[1]);
        bloodPressureDates.add(date[2]);
        bloodPressureDates.add(date[3]);
        bloodPressureDates.add(date[4]);
        bloodPressureDates.add(date[5]);
        bloodPressureDates.add(date[6]);


        // BLUTDRUCK

        int[] diastolicValues1 = new int[]{79, 81, 82, 78, 79, 77, 78};

        for(int i = 0; i < diastolicValues1.length; i++){
            diastolicValues.put(bloodPressureDates.get(i), diastolicValues1[i]);
        }

        int[] systolicValues1 = new int[]{119, 122, 120, 119, 119, 115, 116};

        for(int i = 0; i < systolicValues1.length; i++){
            systolicValues.put(bloodPressureDates.get(i), systolicValues1[i]);
        }


        // BLUTZUCKER

        for (int i = 0; i < tempDates.size(); i++) {
            Calendar c = Calendar.getInstance();
            c.setTime(tempDates.get(i));
            c.add(Calendar.HOUR, 9);
            bloodSugarDates.add(c.getTime());
            c.add(Calendar.HOUR, 3);
            bloodSugarDates.add(c.getTime());
            c.add(Calendar.HOUR, 4);
            bloodSugarDates.add(c.getTime());
            c.add(Calendar.HOUR, 4);
            bloodSugarDates.add(c.getTime());
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
            tempValues.put(tempDates.get(i), tempValues1[i]);
        }


        vitalValues = new VitalValues(tempDates, bloodPressureDates, diastolicB, diastolicValues, systolicB, systolicValues, temp, tempValues, bloodSugar, bloodSugarValues, bloodSugarDates);
    }

}
