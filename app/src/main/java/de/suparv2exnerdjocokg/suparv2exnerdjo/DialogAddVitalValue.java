package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import de.suparv2exnerdjocokg.suparv2exnerdjo.VitalFragment.OnFragmentInteractionListener;

/**
 * Created by dsfd on 12.01.2017.
 */

public class DialogAddVitalValue extends Dialog {

    OnFragmentInteractionListener mListener;
    int id;

    public DialogAddVitalValue(Context context, OnFragmentInteractionListener listener, int id) {
        super(context);
        mListener = listener;
        this.id = id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialgog_add_vital_value);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        final NumberPicker note = (NumberPicker) findViewById(R.id.edit);
        final NumberPicker second = (NumberPicker) findViewById(R.id.edit_second);

        final TextView first = (TextView) findViewById(R.id.first_header);

        final TextView secondHeader = (TextView) findViewById(R.id.second_header);

        final LinearLayout container = (LinearLayout) findViewById(R.id.container_second);
        container.setVisibility(View.GONE);

        switch (id){
            case R.id.temp:
                first.setText("Temperatur");

                note.setMinValue(34);
                note.setMaxValue(42);
                break;
            case R.id.blood_pressure:
                first.setText("Systolischer Blutdruck");

                note.setMinValue(105);
                note.setMaxValue(190);

                container.setVisibility(View.VISIBLE);

                secondHeader.setText("Diastolischer Blutdruck");

                second.setMinValue(65);
                second.setMaxValue(120);
                break;
            case R.id.blood_sugar:
                first.setText("Blutzucker");

                note.setMinValue(60);
                note.setMaxValue(200);
                break;
        }
        note.setWrapSelectorWheel(true);

        Button confirm = (Button) findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int text = note.getValue();
                int secondValue = second.getValue();
                mListener.newValueAdded(text, secondValue, id);
                dismiss();
            }
        });

        Button cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }
}
