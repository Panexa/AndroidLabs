package com.example.vadim.lab4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {

    }

    public void audioCick(View v){
        Intent intent = new Intent(this, AudioActivity.class);
        startActivity(intent);
    }
    public void videoCick(View v){

        Intent intent = new Intent(this, VideoActivity.class);
        startActivity(intent);
    }


}