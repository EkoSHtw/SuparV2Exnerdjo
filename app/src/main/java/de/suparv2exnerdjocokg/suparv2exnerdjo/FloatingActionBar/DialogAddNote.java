package de.suparv2exnerdjocokg.suparv2exnerdjo.FloatingActionBar;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import de.suparv2exnerdjocokg.suparv2exnerdjo.ClientViewActivity;
import de.suparv2exnerdjocokg.suparv2exnerdjo.R;

/**
 * Created by V2 on 25.12.2016.
 */

public class DialogAddNote extends Dialog implements View.OnClickListener {

    private ClientViewActivity activity;
    private Button confirm;
    private Button cancel;


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
                EditText tag = (EditText) findViewById(R.id.dialog_input_tag);

                String sInput = input.getText().toString().trim();
                String sTag = tag.getText().toString().trim();

                if ("".equals(sInput) || "".equals(sTag)) {

                    if ("".equals(sInput)) {
                        setHint(input);
                    }
                    if ("".equals(sTag)) {
                        setHint(tag);
                    }

                } else {
                    activity.addNote(sInput, sTag);
                    dismissDialog(true);
                }
                break;
            case R.id.dialog_button_negative:
                dismissDialog(false);
                break;
        }
    }

    private void setHint(EditText editText) {
        editText.setText("");
        editText.setHint(R.string.text_not_set);
        editText.setHintTextColor(activity.getResources().getColor(R.color.text_not_set_color));
    }

    private void dismissDialog(boolean added) {
        if (!added) {
            DialogAddNewNoteOrTask dialogAddNewNoteOrTask = new DialogAddNewNoteOrTask(activity);
            dialogAddNewNoteOrTask.show();
        }
        dismiss();
    }
}
