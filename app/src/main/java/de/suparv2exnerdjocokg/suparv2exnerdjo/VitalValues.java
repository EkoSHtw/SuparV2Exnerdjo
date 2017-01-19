package de.suparv2exnerdjocokg.suparv2exnerdjo;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dsfd on 19.01.2017.
 */

public class VitalValues {

    private List<Date> tempDates;
    private List<Date> bloodPressureDates;
    private LineGraphSeries<DataPoint> diastolicB;
    private int[] diastolicValues;
    private LineGraphSeries<DataPoint> systolicB;
    private int[] systolicValues;
    private LineGraphSeries<DataPoint> temp;
    private HashMap<Date, Double> tempValues;
    private LineGraphSeries<DataPoint> bloodSugar;
    private HashMap<Date, Integer> bloodSugarValues;
    private List<Date> bloodSugarDates;

    public VitalValues(List<Date> tempDates, List<Date> bloodPressureDates, LineGraphSeries<DataPoint> diastolicB, int[] diastolicValues, LineGraphSeries<DataPoint> systolicB, int[] systolicValues, LineGraphSeries<DataPoint> temp, HashMap<Date, Double> tempValues, LineGraphSeries<DataPoint> bloodSugar, HashMap<Date, Integer> bloodSugarValues, List<Date> bloodSugarDates) {
        this.tempDates = tempDates;
        this.bloodPressureDates = bloodPressureDates;
        this.diastolicB = diastolicB;
        this.diastolicValues = diastolicValues;
        this.systolicB = systolicB;
        this.systolicValues = systolicValues;
        this.temp = temp;
        this.tempValues = tempValues;
        this.bloodSugar = bloodSugar;
        this.bloodSugarValues = bloodSugarValues;
        this.bloodSugarDates = bloodSugarDates;
    }

    public List<Date> getBloodSugarDates() {
        return bloodSugarDates;
    }

    public void setBloodSugarDates(List<Date> bloodSugarDates) {
        this.bloodSugarDates = bloodSugarDates;
    }

    public List<Date> getTempDates() {
        return tempDates;
    }

    public void setTempDates(List<Date> tempDates) {
        this.tempDates = tempDates;
    }

    public List<Date> getBloodPressureDates() {
        return bloodPressureDates;
    }

    public void setBloodPressureDates(List<Date> bloodPressureDates) {
        this.bloodPressureDates = bloodPressureDates;
    }

    public LineGraphSeries<DataPoint> getDiastolicB() {
        return diastolicB;
    }

    public void setDiastolicB(LineGraphSeries<DataPoint> diastolicB) {
        this.diastolicB = diastolicB;
    }

    public int[] getDiastolicValues() {
        return diastolicValues;
    }

    public void setDiastolicValues(int[] diastolicValues) {
        this.diastolicValues = diastolicValues;
    }

    public LineGraphSeries<DataPoint> getSystolicB() {
        return systolicB;
    }

    public void setSystolicB(LineGraphSeries<DataPoint> systolicB) {
        this.systolicB = systolicB;
    }

    public int[] getSystolicValues() {
        return systolicValues;
    }

    public void setSystolicValues(int[] systolicValues) {
        this.systolicValues = systolicValues;
    }

    public LineGraphSeries<DataPoint> getTemp() {
        return temp;
    }

    public void setTemp(LineGraphSeries<DataPoint> temp) {
        this.temp = temp;
    }

    public HashMap<Date, Double> getTempValues() {
        return tempValues;
    }

    public void setTempValues(HashMap<Date, Double> tempValues) {
        this.tempValues = tempValues;
    }

    public LineGraphSeries<DataPoint> getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(LineGraphSeries<DataPoint> bloodSugar) {
        this.bloodSugar = bloodSugar;
    }

    public HashMap<Date, Integer> getBloodSugarValues() {
        return bloodSugarValues;
    }

    public void setBloodSugarValues(HashMap<Date, Integer> bloodSugarValues) {
       this.bloodSugarValues = bloodSugarValues;
    }



}
