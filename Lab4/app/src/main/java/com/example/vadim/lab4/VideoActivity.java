package com.example.vadim.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.net.Uri;


import android.widget.MediaController;



public class VideoActivity extends AppCompatActivity implements View.OnClickListener{

    public VideoView VideoF;
    public VideoView VideoS;

    RadioButton first,second;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_activity);

    }

    @Override
    public void onClick(View v) {

    }
    public void play(View view){
        VideoF =  (VideoView)findViewById(R.id.VideoF);
        Uri  myVideoUri = Uri.parse( "android.resource://" + getPackageName() + "/" + R.raw.fun);

        VideoF.setVideoURI(myVideoUri);
        MediaController mediaController = new MediaController(this);
        VideoF.setMediaController(mediaController);
        mediaController.setMediaPlayer(VideoF);

        VideoS =  (VideoView)findViewById(R.id.VideoS);
        VideoS.setVideoPath("http://video.ch9.ms/ch9/507d/71f4ef0f-3b81-4d2c-956f-c56c81f9507d/AndroidEmulatorWithMacEmulator.mp4");
        MediaController mediaController1 = new MediaController(this);
        VideoS.setMediaController(mediaController1);
        mediaController1.setMediaPlayer(VideoS);

        VideoF.start();
        VideoS.start();
    }
    public void pause(View view){
        VideoS.pause();
        VideoF.pause();
    }
    public void stop(View view){
        VideoF.stopPlayback();
        VideoF.resume();
        VideoS.stopPlayback();
        VideoS.resume();
    }


}
