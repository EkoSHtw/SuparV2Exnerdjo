package de.suparv2exnerdjocokg.suparv2exnerdjo.FloatingActionBar;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.suparv2exnerdjocokg.suparv2exnerdjo.GeneralTask;
import de.suparv2exnerdjocokg.suparv2exnerdjo.R;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Todo.ToDo;
import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyToDos;

/**
 * Created by v2 on 05.01.2017.
 */

public class DialogAddToDo extends DialogFragment implements DatePickerDialog.OnDateSetListener {


    private DatePicker datePicker;
    private NumberPicker numberPicker;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        final LinearLayout view = (LinearLayout) inflater.inflate(R.layout.dialog_add_todo, container, false);
        Button confirm = (Button) view.findViewById(R.id.dialog_todo_button_confirm);
        Button cancel = (Button) view.findViewById(R.id.dialog_todo_button_cancel);
        final AutoCompleteTextView tagEdittext = (AutoCompleteTextView) view.findViewById(R.id.dialog_add_todo_tag);

        String[] tags = getTags();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.auto_complete_item, tags);

        tagEdittext.setThreshold(1);
        tagEdittext.setAdapter(adapter);
        datePicker = (DatePicker) view.findViewById(R.id.date_picker);
        datePicker.setMinDate(System.currentTimeMillis() - 1000);

        Calendar calender = Calendar.getInstance();

        calender.add(Calendar.DATE, 30);

        datePicker.setMaxDate(calender.getTime().getTime());
        datePicker.setOnClickListener(null);
        numberPicker = (NumberPicker) view.findViewById(R.id.dialog_number_picker);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(2);

        String[] values = getActivity().getResources().getStringArray(R.array.weekdays);
        numberPicker.setDisplayedValues(values);

        Button switcher = (Button) view.findViewById(R.id.switch_picker);
        switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datePicker.getVisibility() == View.GONE) {
                    datePicker.setVisibility(View.VISIBLE);
                    numberPicker.setVisibility(View.GONE);
                } else {
                    datePicker.setVisibility(View.GONE);
                    numberPicker.setVisibility(View.VISIBLE);
                }
            }
        });


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EditText nameEditText = (EditText) view.findViewById(R.id.dialog_add_todo_name);
//                String name = nameEditText.getText().toString();

                EditText descriptionEditText = (EditText) view.findViewById(R.id.dialog_add_todo_description);
                String description = descriptionEditText.getText().toString().trim();

                String tag = tagEdittext.getText().toString().trim();


                if ("".equals(description) || "".equals(tag)) {

                    if ("".equals(description)) {
                        setHint(descriptionEditText);
                    }
                    if ("".equals(tag)) {
                        setHint(tagEdittext);
                    }

                } else {

                    int datePickerDayOfMonth = datePicker.getDayOfMonth();
                    int datePickerMonth = datePicker.getMonth();
                    int datePickerYear = datePicker.getYear();

                    if (dateIsToday(datePickerDayOfMonth, datePickerMonth)) {
                        GeneralTask generalTask = new GeneralTask(tag, new String[]{description, "" + datePickerDayOfMonth + " " + datePickerMonth + " " + datePickerYear}, tag);
                        ToDo toDo = new ToDo(new Timestamp(datePickerYear, datePickerMonth, datePickerDayOfMonth, 0, 0, 0, 0), generalTask);
                        DummyToDos.ITEMS.add(toDo);
                    }
                    dismissThis(true);
                }

                /**
                 * https://www.tutorialspoint.com/android/android_auto_complete.htm
                 *http://stackoverflow.com/questions/20989809/text-view-with-suggestion-list
                 */

                //was soll damit dann passieren
//                dismissThis(true);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissThis(false);
            }
        });

        datePicker = (DatePicker) view.findViewById(R.id.date_picker);
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePickerFragment = new FragmentDatePicker();
                datePickerFragment.show(getFragmentManager(), "Date Picker");
            }
        });

        return view;
    }

    private boolean dateIsToday(int datePickerDayOfMonth, int datePickerMonth) {

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        return (day == datePickerDayOfMonth) && (month == datePickerMonth);
    }

    private void setHint(EditText editText) {
        editText.setText("");
        editText.setHint(R.string.text_not_set);
        editText.setHintTextColor(getActivity().getResources().getColor(R.color.text_not_set_color));
    }

    private String[] getTags() {
        Set<String> tags = new HashSet<>();
//        DummyToDos.ITEMS
        List<ToDo> items = DummyToDos.ITEMS;
        for (int i = 0; i < items.size(); i++) {
            ToDo toDo = items.get(i);
            tags.add(toDo.getTask().getTag());
        }

        return tags.toArray(new String[tags.size()]);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {

//        String myFormat = "dd/MM/yy"; //In which you need put here
//        Locale current = getResources().getConfiguration().locale;
//        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, current);

//        datePicker.setText(sdf.toString());
//        datePicker.setText("" + day + "." + month + "." + year);
    }

    public void dismissThis(boolean added) {

        if (!added) {
            DialogAddNewNoteOrTask dialogAddNewNoteOrTask = new DialogAddNewNoteOrTask(getActivity());
            dialogAddNewNoteOrTask.show();
        }
        dismiss();
    }

}
