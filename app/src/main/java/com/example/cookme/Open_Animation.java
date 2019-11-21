package com.example.cookme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.VideoView;

public class Open_Animation extends AppCompatActivity {

    VideoView view;
    Boolean h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open__animation);

        view = (VideoView)findViewById(R.id.Burner);


        String path = "android.resource://" + getPackageName() + "/" + R.raw.burner;

        view.setVideoURI(Uri.parse(path));
        view.start();

        h =  new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Open_Animation.this, Login.class);
                startActivity(i);
            }
        }, 5000);
    }
}

