package com.example.youtube;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class LoadingActivityYTSP extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadingactivityytsp);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                switchActivities1();
                finish();
            }
        },1500);
    }
    private void switchActivities1() {
        Intent switchActivityIntent = new Intent(this, MainActivityYTSP.class);
        startActivity(switchActivityIntent);
    }
}
