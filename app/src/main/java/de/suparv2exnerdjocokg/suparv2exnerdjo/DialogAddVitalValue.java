package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by dsfd on 12.01.2017.
 */

public class DialogAddVitalValue extends Dialog {

    public DialogAddVitalValue(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialgog_add_vital_value);


        final EditText note = (EditText) findViewById(R.id.edit);

        Button confirm = (Button) findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = note.getText().toString();
                dismiss();
            }
        });

    }
}
