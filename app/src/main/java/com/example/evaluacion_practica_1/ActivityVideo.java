package com.example.evaluacion_practica_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.net.URI;

public class ActivityVideo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        VideoView vidView = (VideoView) findViewById(R.id.videoView);
        vidView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.clouds));
        MediaController vidControl = new MediaController(this);
        vidControl.setAnchorView(vidView);
        vidView.setMediaController(vidControl);


        VideoView vidView2 = (VideoView) findViewById(R.id.videoView2);
        vidView2.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.meme1));
        MediaController vidControl1 = new MediaController(this);
        vidControl.setAnchorView(vidView2);
        vidView2.setMediaController(vidControl1);

        VideoView vidView3 = (VideoView) findViewById(R.id.videoView3);
        vidView3.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.trees));
        MediaController vidControl2 = new MediaController(this);
        vidControl.setAnchorView(vidView3);
        vidView3.setMediaController(vidControl2);

        Button ret = findViewById(R.id.btnRegresar);

        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ActivityBienvenida.class);
                startActivity(intent);
            }
        });
    }
}