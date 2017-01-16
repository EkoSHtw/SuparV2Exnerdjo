package de.suparv2exnerdjocokg.suparv2exnerdjo.DocumentTools;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.net.URI;

import de.suparv2exnerdjocokg.suparv2exnerdjo.R;

public class ImageActivity extends AppCompatActivity {
    private ImageView image;
    private String s = getIntent().getStringExtra("PICTURE_ID");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        this.image = (ImageView) findViewById(R.id.woundimage);
        Intent intent = getIntent();
       // String message = intent.getStringExtra();

        Uri uri = Uri.parse(s);
        image.setImageURI(uri);

    }
}
