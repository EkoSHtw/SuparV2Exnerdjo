package de.suparv2exnerdjocokg.suparv2exnerdjo.FloatingActionBar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Client;
import de.suparv2exnerdjocokg.suparv2exnerdjo.ClientViewActivity;
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
    List<ToDo> toDos;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        Activity a = getActivity();
        if (a instanceof ClientViewActivity){
            Client c = ((ClientViewActivity)a).getClient();
            toDos = c.getToDoList();
        }

        LinearLayout rootView = (LinearLayout) inflater.inflate(R.layout.dialog_add_todo, container, false);
        Button confirm = (Button) rootView.findViewById(R.id.dialog_todo_button_confirm);
        Button cancel = (Button) rootView.findViewById(R.id.dialog_todo_button_cancel);
        final AutoCompleteTextView tagEdittext = (AutoCompleteTextView) rootView.findViewById(R.id.dialog_add_todo_tag);

        final EditText descriptionEditText = (EditText) rootView.findViewById(R.id.dialog_add_todo_description);


        String[] tags = getTags();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.auto_complete_item, tags);

        tagEdittext.setThreshold(1);
        tagEdittext.setAdapter(adapter);
        tagEdittext.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.auto_complete_text_view);
                String name = textView.getText().toString().trim();
                for (int i = 0; i < toDos.size(); i++) {
                    ToDo toDo = toDos.get(i);
                    if (toDo.getTask().getName().equals(name)) {
                        String result = "";
                        for (String line : toDo.getTask().getDescription()) {
                            result += line;
                            result += "\n";
                        }
                        descriptionEditText.setText(result);

                        break;
                    }
                }

            }
        });


        datePicker = (DatePicker) rootView.findViewById(R.id.date_picker);
        datePicker.setMinDate(System.currentTimeMillis() - 1000);

        Calendar calender = Calendar.getInstance();

        calender.add(Calendar.DATE, 30);

        datePicker.setMaxDate(calender.getTime().getTime()); // später überprüfen wegen febrauar (<30 tage)
        datePicker.setOnClickListener(null);
        numberPicker = (NumberPicker) rootView.findViewById(R.id.dialog_number_picker);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(2);

        String[] values = getActivity().getResources().getStringArray(R.array.weekdays);
        numberPicker.setDisplayedValues(values);

        Button switcher = (Button) rootView.findViewById(R.id.switch_picker);
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
                    Calendar calendar = Calendar.getInstance();

                    //alles was in der zukunft passiert wird irgnoriert


                    if (datePicker.getVisibility() == View.VISIBLE) {

                        int datePickerDayOfMonth = datePicker.getDayOfMonth();
                        int datePickerMonth = datePicker.getMonth();

                        if (dateIsToday(datePickerDayOfMonth, datePickerMonth)) {
                            GeneralTask generalTask = new GeneralTask(tag, new String[]{description}, tag);
                            ToDo toDo = new ToDo(new Timestamp(calendar.getTimeInMillis()), generalTask);
                            toDos.add(toDo);
                        }
                    } else {
                        int val = numberPicker.getValue();

                        if (val == 0) {


                            GeneralTask generalTask = new GeneralTask(tag, new String[]{description}, tag);
                            ToDo toDo = new ToDo(new Timestamp(calendar.getTimeInMillis()), generalTask);
                            toDos.add(toDo);
                        }
                    }
                    dismissThis(true);

                }

                /**
                 * https://www.tutorialspoint.com/android/android_auto_complete.htm
                 *http://stackoverflow.com/questions/20989809/text-view-with-suggestion-list
                 */

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissThis(false);
            }
        });

        datePicker = (DatePicker) rootView.findViewById(R.id.date_picker);
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePickerFragment = new FragmentDatePicker();
                datePickerFragment.show(getFragmentManager(), "Date Picker");
            }
        });
        return rootView;
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
        editText.setHintTextColor(getActivity().getResources().getColor(R.color.colorAccentRed));
    }

    private String[] getTags() {
        Set<String> tags = new HashSet<>();
//        DummyToDos.ITEMS
//        List<ToDo> items = DummyToDos.ITEMS;
        for (int i = 0; i < toDos.size(); i++) {
            ToDo toDo = toDos.get(i);
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
        ClientViewActivity ac = (ClientViewActivity) getActivity();
        ac.updateView();
        dismiss();

    }


}
