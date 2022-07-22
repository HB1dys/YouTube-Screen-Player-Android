package com.example.youtube;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YouTubeeActivityYTSP extends YouTubeBaseActivity {
    private YouTubePlayerView playeri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playwindowactivityytsp);
        Button backbutton = (Button) findViewById(R.id.BackButtonPlay);
        String myYouTubeVideoUrl = "https://www.youtube.com/embed/"+getVideoId(AddActivityYTSP.url);
        String dataUrl =
                "<html>" +
                        "<body>" +
                        "<h2>Video From YouTube</h2>" +
                        "<br>" +
                        "<iframe width=\"560\" height=\"315\" src=\""+myYouTubeVideoUrl+"\" frameborder=\"0\" allowfullscreen/>" +
                        "</body>" +
                        "</html>";
        playeri = findViewById(R.id.player);
        backbutton.setOnClickListener(view -> {
            switchActivities();
        });
        YouTubePlayer.OnInitializedListener listener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo(getVideoId(AddActivityYTSP.url));
                youTubePlayer.setFullscreen(true);
            }
            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(YouTubeeActivityYTSP.this, "Initialization Failed", Toast.LENGTH_LONG).show();
            }
        };
        playeri.initialize(AddActivityYTSP.url, listener);
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
    private void switchActivities() {
        Intent switchActivityIntent = new Intent(this, MainActivityYTSP.class);
        startActivity(switchActivityIntent);
    }
}
