package com.example.youtube;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
public class MainActivityYTSP extends AppCompatActivity {

    ArrayList<String> urlcheck;
    String urlchstr="";
    int heightScreen = 0;
    int widthScreen = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        heightScreen = displayMetrics.heightPixels;
        widthScreen = displayMetrics.widthPixels;
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_mainytsp);
        Button addbutton = findViewById(R.id.AddButtonYTSP);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivities();
            }
        });
        Button exitbutton = findViewById(R.id.backbuttonYTSP);
        urlcheck=Common.DeserializeAL(Common.getPreferenceString(MainActivityYTSP.this,"urlch"));
        LinearLayout l=findViewById(R.id.ImagesMainYTSP);
        if (urlcheck != null){
            for(int i=urlcheck.size()-1;i>=0;i--){
                ImageView thumbnails = new ImageView(MainActivityYTSP.this);
                thumbnails.setScaleType(ImageView.ScaleType.FIT_START);
                thumbnails.setMinimumHeight(heightScreen/2 - 50);
                thumbnails.setMaxHeight(heightScreen/2 - 50);
                thumbnails.setMinimumWidth(widthScreen/4 + 50);
                thumbnails.setMaxWidth(widthScreen/4+ 50);
                String url = "https://img.youtube.com/vi/" + YouTubeeActivityYTSP.getVideoId(urlcheck.get(i)) + "/0.jpg";
                Glide.with(this).load(url).into(thumbnails);
                l.addView(thumbnails);
                if (thumbnails == null){
                    urlcheck = null;
                }
                TextView title = new TextView(MainActivityYTSP.this);
                title.setText(urlcheck.get(i));
                title.setTextSize(21);
                l.addView(title);
                thumbnails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddActivityYTSP.url = title.getText().toString();
                        switchActivities1();
                    }
                });
            }
        }

        exitbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.exit(1);
            }
        });
    }

    private void switchActivities() {
        Intent switchActivityIntent = new Intent(this, AddActivityYTSP.class);
        startActivity(switchActivityIntent);
    }

    private void switchActivities1() {
        Intent switchActivityIntent = new Intent(this, YouTubeeActivityYTSP.class);
        startActivity(switchActivityIntent);
    }
    @SuppressLint("ResourceType")
    protected void onResume() {
        Button clearbutton = findViewById(R.id.clearbuttonRYTSP);
        if(urlcheck == null){
            clearbutton.setVisibility(View.GONE);
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        heightScreen = displayMetrics.heightPixels;
        widthScreen = displayMetrics.widthPixels;
        LinearLayout l = findViewById(R.id.ImagesMainYTSP);
        super.onResume();
        if (AddActivityYTSP.url != "") {
            if (urlcheck != null){
                if (!urlcheck.contains(AddActivityYTSP.url)) {
                    urlcheck.add(AddActivityYTSP.url);
                }
            }
            else{
                urlcheck = new ArrayList<>();
                urlcheck.add(AddActivityYTSP.url);
            }
        }

        l.removeAllViews();
        if (urlcheck != null){
            for(int i=urlcheck.size()-1;i>=0;i--){
                ImageView thumbnails = new ImageView(MainActivityYTSP.this);
                thumbnails.setScaleType(ImageView.ScaleType.FIT_START);
                thumbnails.setMinimumHeight(heightScreen/2);
                thumbnails.setMaxHeight(heightScreen/2);
                thumbnails.setMinimumWidth(widthScreen/4);
                thumbnails.setMaxWidth(widthScreen/4);
                String url = "https://img.youtube.com/vi/" + YouTubeeActivityYTSP.getVideoId(urlcheck.get(i)) + "/0.jpg";
                Glide.with(this).load(url).into(thumbnails);
                l.addView(thumbnails);
                TextView title = new TextView(MainActivityYTSP.this);
                title.setText(urlcheck.get(i));
                title.setTextSize(21);
                l.addView(title);
                thumbnails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddActivityYTSP.url = title.getText().toString();
                        switchActivities1();
                    }
                });
            }
        }
        if (urlcheck != null){
            clearbutton.setVisibility(View.VISIBLE);
            clearbutton.setOnClickListener(view -> {
                l.removeAllViews();
                Common.removeSharedPreference(MainActivityYTSP.this,"urlch");
                urlcheck.removeAll(urlcheck);
                urlcheck.removeAll(urlcheck);
                urlcheck = null;
                clearbutton.setVisibility(View.GONE);
                AddActivityYTSP.url = "";
            });
        }
        urlchstr = Common.SerializeClass(urlcheck);
        Common.EditPreferenceString(MainActivityYTSP.this, "urlch", urlchstr);
    }
}


