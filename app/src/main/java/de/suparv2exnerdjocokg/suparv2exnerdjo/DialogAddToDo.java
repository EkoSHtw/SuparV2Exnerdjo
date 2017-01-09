package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by v2 on 05.01.2017.
 */

public class DialogAddToDo extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private int year;
    private int month;
    private int day;
    private TextView timePicker;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        final LinearLayout view = (LinearLayout) inflater.inflate(R.layout.dialog_add_todo, container, false);
        Button confirm = (Button) view.findViewById(R.id.dialog_todo_button_confirm);
        Button cancel = (Button) view.findViewById(R.id.dialog_todo_button_cancel);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameEditText = (EditText) view.findViewById(R.id.dialog_add_todo_name);
                String name = nameEditText.getText().toString();

                EditText descriptionEditText = (EditText) view.findViewById(R.id.dialog_add_todo_description);
                String description = descriptionEditText.getText().toString();

                EditText tagEdittext = (EditText) view.findViewById(R.id.dialog_add_todo_tag);
                String tag = tagEdittext.getText().toString();

                GeneralTask generalTask = new GeneralTask(name, new String[]{description}, tag);
//                ToDo toDo = new ToDo(new Timestamp(year, month, day, 0, 0, 0, 0), generalTask);
//                DummyToDos.ITEMS.add(toDo);
                //was soll damit dann passieren

                dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissThis();
            }
        });

        timePicker = (TextView) view.findViewById(R.id.time_picker);
        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new FragmentDatePicker();
                timePicker.show(getFragmentManager(), "Date Picker");
            }
        });

        return view;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        this.year = year;
        this.month = month+1;
        this.day = day;

//        String myFormat = "dd/MM/yy"; //In which you need put here
//        Locale current = getResources().getConfiguration().locale;
//        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, current);

//        timePicker.setText(sdf.toString());
        timePicker.setText("" + day + "." + month + "." + year);
    }

    public void dismissThis() {
        dismiss();
    }

}
