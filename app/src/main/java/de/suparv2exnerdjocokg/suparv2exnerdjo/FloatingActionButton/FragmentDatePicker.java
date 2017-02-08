package de.suparv2exnerdjocokg.suparv2exnerdjo.FloatingActionButton;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import java.util.Calendar;


public class FragmentDatePicker extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
//        c.getTime().af

        // Create a new instance of DatePickerDialog and return it
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), (DialogAddToDo) getFragmentManager().findFragmentByTag("Add todo"), year, month, day);
//        datePickerDialog.

        return datePickerDialog;

    }


}
