package de.suparv2exnerdjocokg.suparv2exnerdjo.Vital;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.suparv2exnerdjocokg.suparv2exnerdjo.R;

public class DialogChangeValue extends Dialog {

    private Context c;
    private VitalFragment.OnFragmentInteractionListener listener;
    private int id;

    protected DialogChangeValue(Context context, VitalFragment.OnFragmentInteractionListener listener, int id) {
        super(context);
        this.c = context;
        this.listener = listener;
        this.id = id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_change_value);

        TextView warning = (TextView) findViewById(R.id.warning);
        warning.setText("Der Wert wurde bereits vor kurzem eingegeben.\n Möchtest du den letzten Wert überschreiben?");

        Button cancel = (Button) findViewById(R.id.cancel);
        cancel.setText(R.string.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        Button confirm = (Button) findViewById(R.id.confirm);
        confirm.setText(R.string.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAddVitalValue newV = new DialogAddVitalValue(c, listener, id);
                newV.show();
                dismiss();
            }
        });



    }
}
