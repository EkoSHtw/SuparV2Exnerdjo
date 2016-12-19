package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class ClientViewActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private PopupWindow popupWindow;
    private TextView popUpMessage;
    private FrameLayout container;
    private boolean clicked = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_client_view);


        container = new FrameLayout(this);
        popupWindow = new PopupWindow(this);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clicked) {
                    clicked = false;
                    popupWindow.showAtLocation(findViewById(R.id.activity_client_view), Gravity.CENTER, 10, 10);
                    popupWindow.update(50, 50, 320, 90);
                } else {
                    clicked = true;
                    popupWindow.dismiss();
                }
            }
        });

        popUpMessage = new TextView(this);
        popUpMessage.setText("Hi, ich bin das Popup");

        container.addView(popUpMessage);

        popupWindow.setContentView(container);


    }
}
