package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by v2 on 05.01.2017.
 */

public class DialogAddToDo extends Dialog {

    public DialogAddToDo(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.dialog_add_todo);

        Button confirm = (Button) findViewById(R.id.dialog_todo_button_confirm);
        Button cancel = (Button) findViewById(R.id.dialog_todo_button_cancel);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameEditText = (EditText) linearLayout.findViewById(R.id.dialog_add_todo_name);
                String name = nameEditText.getText().toString();

                EditText descriptionEditText = (EditText) linearLayout.findViewById(R.id.dialog_add_todo_description);
                String description = descriptionEditText.getText().toString();

                EditText tagEdittext = (EditText)linearLayout.findViewById(R.id.dialog_add_todo_tag);
                String tag = tagEdittext.getText().toString();4
            }
        });

    }
}
