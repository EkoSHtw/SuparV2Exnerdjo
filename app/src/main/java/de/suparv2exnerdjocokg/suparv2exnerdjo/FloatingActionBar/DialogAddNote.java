package de.suparv2exnerdjocokg.suparv2exnerdjo.FloatingActionBar;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.suparv2exnerdjocokg.suparv2exnerdjo.ClientViewActivity;
import de.suparv2exnerdjocokg.suparv2exnerdjo.R;
import de.suparv2exnerdjocokg.suparv2exnerdjo.Todo.ToDo;
import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyToDos;

/**
 * Created by V2 on 25.12.2016.
 */

public class DialogAddNote extends Dialog implements View.OnClickListener {

    private ClientViewActivity activity;
    private Button confirm;
    private Button cancel;
    private AutoCompleteTextView tag;


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
        tag = (AutoCompleteTextView) findViewById(R.id.dialog_input_tag);
        String[] tags = getTags();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, R.layout.auto_complete_item, tags);

        tag.setAdapter(adapter);

        confirm.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    private String[] getTags() {
        Set<String> tags = new HashSet<>();
//        DummyToDos.ITEMS
        List<ToDo> items = DummyToDos.ITEMS;
        for (int i = 0; i < items.size(); i++) {
            ToDo toDo = items.get(i);
            tags.add(toDo.getTask().getTag());
        }

        return tags.toArray(new String[tags.size()]);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_button_positive:
                EditText input = (EditText) findViewById(R.id.dialog_input_text);



                String sInput = input.getText().toString().trim();
                String sTag = tag.getText().toString();

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
        editText.setHintTextColor(activity.getResources().getColor(R.color.colorAccentRed));
    }

    private void dismissDialog(boolean added) {
        if (!added) {
            DialogAddNewNoteOrTask dialogAddNewNoteOrTask = new DialogAddNewNoteOrTask(activity);
            dialogAddNewNoteOrTask.show();
        }
        dismiss();
    }
}
