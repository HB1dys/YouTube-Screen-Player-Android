package com.example.youtube;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddActivityYTSP extends AppCompatActivity {
    public static String url = "";
    public static ArrayList<String> urls = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Activity activity = this;
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.addurlactivityytsp);
        Button backButton = findViewById(R.id.BackButtonAdd);
        backButton.setOnClickListener(view -> finish());
        Button playButton = findViewById(R.id.PlayButtonYTSP);
        playButton.setOnClickListener(view -> {
            EditText urladress = findViewById(R.id.URLadressYTSP);
            url = urladress.getText().toString();
            if (!isYoutubeUrl(url)){
                Toast.makeText(activity, "False YouTube URL", Toast.LENGTH_LONG).show();
                url="";
            }
            else{
                urls.add(url);
                switchActivities();
            }
        });
    }
    public static String getVideoId(String URLlink) {
        String videoId = "";
        String regex = "http(?:s)?:\\/\\/(?:m.)?(?:www\\.)?youtu(?:\\.be\\/|be\\.com\\/(?:watch\\?(?:feature=youtu.be\\&)?v=|v\\/|embed\\/|user\\/(?:[\\w#]+\\/)+))([^&#?\\n]+)";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(URLlink);
        if(matcher.find()){
            videoId = matcher.group(1);
        }
        return videoId;
    }
    public static boolean isYoutubeUrl(String youTubeURl)
    {
        boolean success;
        String pattern = "^(http(s)?:\\/\\/)?((w){3}.)?youtu(be|.be)?(\\.com)?\\/.+";
        if (!youTubeURl.isEmpty() && youTubeURl.matches(pattern))
        {
            success = true;
        }
        else
        {
            // Not Valid youtube URL
            success = false;
        }
        return success;
    }
    private void switchActivities() {
        Intent switchActivityIntent = new Intent(this, YouTubeeActivityYTSP.class);
        startActivity(switchActivityIntent);
    }

}
