package com.example.achintya.youtubeapp;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final String TAG = "YoutubeActivity";
    static final String GOOGLE_API_KEY = "AIzaSyBTqXBKKf8huN0fWxDOzi9sUTP7-I4YaWc";
    final String YOUTUBE_FAV_SONG = "Jg1cQs53NjE";
    static final String YOUTUBE_VIDEO_ID = "vm_GroJMOVM";
    static final String YOUTUBE_PLAYLIST = "RDQMrQvK-XepOY4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_youtube);
        //RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.activity_youtube);

        RelativeLayout layout = (RelativeLayout)getLayoutInflater().inflate(R.layout.activity_youtube, null);
        setContentView(layout);

        //Button button1 = new Button(this);
        //button1.setLayoutParams(new RelativeLayout.LayoutParams(300,80));
        //button1.setText("Button Added");
        //layout.addView(button1);

        YouTubePlayerView playerView = new YouTubePlayerView(this);
        playerView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.addView(playerView);

        playerView.initialize(GOOGLE_API_KEY, this);



    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored ) {

        Log.d(TAG, "onInitilizationSuccess : provider is" + provider.getClass().toString());
        Toast.makeText(this, "Initilized YoutubePlayer Sucessfully", Toast.LENGTH_LONG).show();

        youTubePlayer.setPlaybackEventListener(playbackEventListener);
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);

        if(!wasRestored){
            youTubePlayer.cueVideo(YOUTUBE_FAV_SONG);
        }



    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

        final int REQUEST_CODE = 1;

        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show();
        }else{
            String errorMessage = String.format("There was an error initilizing the YoutubePlayer (%1$s)", youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }

    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {

            Toast.makeText(YoutubeActivity.this, "Good Video is Playing Okay", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onPaused() {

            Toast.makeText(YoutubeActivity.this, "Video Has Paused", Toast.LENGTH_LONG).show() ;

        }

        @Override
        public void onStopped() {

            Toast.makeText(YoutubeActivity.this, "Video Has Stopped", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {



        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {
            Toast.makeText(YoutubeActivity.this, "Click Ad Now, make the Video Creator Rich!!", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onVideoStarted() {
            Toast.makeText(YoutubeActivity.this, "Video Has Started", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onVideoEnded() {

            Toast.makeText(YoutubeActivity.this, "Congurations You Completed Another Video", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };
}
