package de.suparv2exnerdjocokg.suparv2exnerdjo.DocumentTools;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import de.suparv2exnerdjocokg.suparv2exnerdjo.R;

public class ImageActivity extends AppCompatActivity {
    private ImageView image;
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        this.image = (ImageView) findViewById(R.id.woundimage);
        Intent intent = getIntent();
//        = getIntent().getStringExtra("PICTURE_ID")
       // String message = intent.getStringExtra();

        s = intent.getStringExtra("PICTURE_ID");

        Uri uri = Uri.parse(s);
        image.setImageURI(uri);

    }
}
