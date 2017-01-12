package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
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


        TextView note = (TextView) findViewById(R.id.edit);

    }
}
