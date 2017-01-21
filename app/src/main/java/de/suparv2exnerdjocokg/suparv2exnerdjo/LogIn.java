package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LogIn extends AppCompatActivity {

    TextView description;
    EditText userName;
    EditText password;
    Button logInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        // Views aus der XML heraussuchen
        description = (TextView) findViewById(R.id.logInDescription);
        userName = (EditText) findViewById(R.id.userNameField);
        password = (EditText) findViewById(R.id.passwordField);
        logInButton = (Button) findViewById(R.id.submitButton);

        // den Views ihren Text zuweisen
        description.setText(R.string.logInDescription);
        logInButton.setText(R.string.weiter);
        // OnClickListener erstellen, der eine neue Activity startet
        logInButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Splash.class);
                startActivity(intent);
            }
        });
    }


}
