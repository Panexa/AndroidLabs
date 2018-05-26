package com.example.vadim.lab4;

import android.app.Activity;
import android.content.ContentUris;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.io.IOException;

public class AudioActivity extends Activity implements MediaPlayer.OnPreparedListener,
        MediaPlayer.OnCompletionListener {

    final String LOG_TAG = "myLogs";

    final String DATA_HTTP = "https://www.dropbox.com/s/6t6ok0hre9ttkcy/Sum41.mp3?dl=0";
    final String DATA_SD = Environment
            .getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)
            + "/music.mp3";
    final Uri DATA_URI = ContentUris
            .withAppendedId(
                    android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    13359);

    MediaPlayer mediaPlayer;
    AudioManager am;
    CheckBox Loop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_activity);
        am = (AudioManager) getSystemService(AUDIO_SERVICE);
        Loop = (CheckBox) findViewById(R.id.Loop);
        Loop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (mediaPlayer != null)
                    mediaPlayer.setLooping(isChecked);
            }
        });
    }

    public void onClickStart(View view) {
        releaseMP();

        try {
            switch (view.getId()) {
                case R.id.StartHttpB:
                    Log.d(LOG_TAG, "start HTTP");
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(DATA_HTTP);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    Log.d(LOG_TAG, "prepareAsync");
                    mediaPlayer.setOnPreparedListener(this);
                    mediaPlayer.prepareAsync();
                    //mediaPlayer.prepare();
                    //mediaPlayer.start();
                    break;
                case R.id.StartB:
                    Log.d(LOG_TAG, "start Raw");
                    mediaPlayer = MediaPlayer.create(this, R.raw.mm);
                    mediaPlayer.start();
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mediaPlayer == null)
            return;

        mediaPlayer.setLooping(Loop.isChecked());
        mediaPlayer.setOnCompletionListener(this);
    }

    private void releaseMP() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
                mediaPlayer = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onClick(View view) {
        if (mediaPlayer == null)
            return;
        switch (view.getId()) {
            case R.id.PauseB:
                if (mediaPlayer.isPlaying())
                    mediaPlayer.pause();
                break;
            case R.id.ResumeB:
                if (!mediaPlayer.isPlaying())
                    mediaPlayer.start();
                break;
            case R.id.StopB:
                if(mediaPlayer.isPlaying())
                    mediaPlayer.stop();
                break;
            case R.id.BackwardB:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 3000);
                break;
            case R.id.ForwardB:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 3000);
                break;
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.d(LOG_TAG, "onPrepared");
        mp.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.d(LOG_TAG, "onCompletion");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMP();
    }
}

