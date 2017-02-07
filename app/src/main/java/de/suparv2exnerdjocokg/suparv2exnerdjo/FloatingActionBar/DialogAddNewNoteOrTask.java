package de.suparv2exnerdjocokg.suparv2exnerdjo.FloatingActionBar;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Client;
import de.suparv2exnerdjocokg.suparv2exnerdjo.ClientViewActivity;
import de.suparv2exnerdjocokg.suparv2exnerdjo.R;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Todo.Note;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Todo.ToDo;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Todo.TodoFragment;

/**
 * Created by v2 on 05.01.2017.
 */

public class DialogAddNewNoteOrTask extends Dialog implements View.OnClickListener {

    private ClientViewActivity activity;
    private List<ToDo> toDos;
    private List<Note> notes;

    public DialogAddNewNoteOrTask(Activity activity) {
        super(activity);

        if(activity instanceof ClientViewActivity) {
            this.activity = (ClientViewActivity)activity;
        }
            Client c = this.activity.getClient();
            toDos  = c.getToDoList();
            notes = c.getFixedNotes();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_new_note_todo);

        TextView note = (TextView) findViewById(R.id.dialog_add_note);
        note.setOnClickListener(this);
        TextView task = (TextView) findViewById(R.id.dialog_add_todo);
        task.setOnClickListener(this);
        TextView fixedNote = (TextView) findViewById(R.id.dialog_add_sticky);
        fixedNote.setOnClickListener(this);
    }

    public void dismissThis() {
        activity.updateRightView();
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
            case R.id.dialog_add_sticky:

                Log.i("", "Ok klick detected");
                createAlert();

                break;
        }
    }

    private void createAlert(){

        Log.i("", "Ok method openend");

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.new_note);

        LinearLayout layout = new LinearLayout(activity);

        final Spinner spinner = new Spinner(activity);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, getTags()); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);

        final EditText editText = new EditText(activity);

        layout.addView(spinner);
        layout.addView(editText);

        builder.setView(layout);

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String input = editText.getText().toString();
                String tag = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();

                notes.add(new Note(tag, input, activity.getCarer(), new Timestamp(System.currentTimeMillis())));

                dialog.dismiss();
                dismissThis();
            }
        });

        builder.show();
    }

    private List<String> getTags() {
        List<String> tags = new ArrayList<>();
//        DummyToDos.ITEMS
//        List<ToDo> items = DummyToDos.ITEMS;

        tags.add("Kein Stichwort");
        for (int i = 0; i < toDos.size(); i++) {
            ToDo toDo = toDos.get(i);
            tags.add(toDo.getTask().getTag());
        }

        return tags;
    }
}
