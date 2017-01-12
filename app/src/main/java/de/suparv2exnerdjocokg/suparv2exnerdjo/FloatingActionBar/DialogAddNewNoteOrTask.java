package de.suparv2exnerdjocokg.suparv2exnerdjo.FloatingActionBar;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import de.suparv2exnerdjocokg.suparv2exnerdjo.R;

/**
 * Created by v2 on 05.01.2017.
 */

public class DialogAddNewNoteOrTask extends Dialog implements View.OnClickListener {

    private Activity activity;

    public DialogAddNewNoteOrTask(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_new_note_todo);

        TextView note = (TextView) findViewById(R.id.dialog_add_note);
        note.setOnClickListener(this);
        TextView task = (TextView) findViewById(R.id.dialog_add_todo);
        task.setOnClickListener(this);
    }


    public void dismissThis() {
        dismiss();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_add_note:
                DialogAddNote dialogAddNote = new DialogAddNote(activity);
                dialogAddNote.show();
                dismissThis();
                break;
            case R.id.dialog_add_todo:
                DialogAddToDo dialogAddToDo = new DialogAddToDo();
//                dialogAddToDo.show();
                dialogAddToDo.show(activity.getFragmentManager(),"Add todo");
                dismissThis();
                break;
        }
    }
}
