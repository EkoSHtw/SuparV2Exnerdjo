package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyToDos;

/**
 * Created by V2 on 25.12.2016.
 */

public class DialogShiftTask extends Dialog implements View.OnClickListener {

    private ClientViewActivity activity;
    private Button confirm;
    private Button cancel;
    private int position;



    public DialogShiftTask(Activity activity, int position) {
        super(activity);
        this.activity = (ClientViewActivity) activity;
        this.position = position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_shift_task);

        NumberPicker numberPicker = (NumberPicker) findViewById(R.id.number_picker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(3);

        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
        confirm = (Button) findViewById(R.id.select_shift);
        confirm.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_shift:
                NumberPicker input = (NumberPicker) findViewById(R.id.number_picker);
                DummyToDos.ITEMS.remove(position);

                dismissDialog();
                break;
            case R.id.cancel:
                dismissDialog();
        }
    }

    private void dismissDialog() {
        dismiss();
    }
}
