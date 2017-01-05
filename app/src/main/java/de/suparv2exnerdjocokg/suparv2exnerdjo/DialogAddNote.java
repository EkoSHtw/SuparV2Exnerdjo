package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by V2 on 25.12.2016.
 */

public class DialogAddNote extends Dialog implements View.OnClickListener {

    private ClientViewActivity activity;
    private Button confirm;
    private Button cancel;

    private Dialog dialog;


    public DialogAddNote(Activity activity) {
        super(activity);
        this.activity = (ClientViewActivity) activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_note);

        confirm = (Button) findViewById(R.id.dialog_button_positive);
        cancel = (Button) findViewById(R.id.dialog_button_negative);

        confirm.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_button_positive:
                EditText input = (EditText) findViewById(R.id.dialog_input_text);
                EditText tag = (EditText) findViewById(R.id.dialog_input_tag) ;
                activity.addNote(input.getText().toString().trim(), tag.getText().toString().trim());
                dismissDialog();
                break;
            case R.id.dialog_button_negative:
                dismissDialog();
        }
    }

    private void dismissDialog() {
        dismiss();
    }
}
