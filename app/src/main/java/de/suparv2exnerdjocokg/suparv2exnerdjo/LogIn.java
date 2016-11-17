package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

        description = (TextView) findViewById(R.id.logInDescription);
        userName = (EditText) findViewById(R.id.userNameField);
        password = (EditText) findViewById(R.id.passwordField);
        logInButton = (Button) findViewById(R.id.submitButton);

        description.setText(R.string.logInDescription);
        logInButton.setText(R.string.weiter);
        logInButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), ServiceCatalog.class);
                startActivity(intent);
            }
        });
    }


}
