package com.example.sushant.udacityproject4_musicalapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bluejamesbond.text.DocumentView;
import com.bluejamesbond.text.style.TextAlignment;

import java.io.IOException;

/**
 * Created by sushant on 22/8/16.
 */
public class SongsManager extends MainActivity implements View.OnClickListener,View.OnLongClickListener {

    ImageButton button_play;
    ImageButton button_stop;
    ImageButton button_forward;
    ImageButton button_backward;
    ImageView scrollImage;
    String selected_song;
    boolean paused=false;
    boolean newsong=true;
    private int seekForwardTime = 5000;
    private int seekBackwardTime = 5000;
    TextView viewAbout;
    Button button_buy;
    Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_layout);

        button_play=(ImageButton)findViewById(R.id.btn_play);
        button_play.setOnClickListener(this);
        button_stop=(ImageButton)findViewById(R.id.btn_stop);
        button_stop.setOnClickListener(this);

        button_forward=(ImageButton)findViewById(R.id.btn_forward);
        button_forward.setOnLongClickListener(this);
        button_backward=(ImageButton)findViewById(R.id.btn_backward);
        button_backward.setOnLongClickListener(this);

        button_buy=(Button)findViewById(R.id.button_buy);
        button_buy.setOnClickListener(this);


        scrollImage=(ImageView)findViewById(R.id.scrollimage);

        Intent intent=getIntent();
        selected_song=intent.getStringExtra("songselected");

        final Toolbar toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);
        collapsingToolbar.setTitle(selected_song.toUpperCase());

        viewAbout=(TextView)findViewById(R.id.viewAbout);

    }

    @Override
    public void onClick(View v) {
        setScrollImage(selected_song);
         switch (v.getId())
            {
                case R.id.btn_play:
                    if(newsong==true) {
                        playSong(selected_song);
                        newsong=false;
                        button_play.setBackground(getDrawable(R.drawable.img_btn_pause));
                    }
                    else {
                        playAndPauseCurrentSong();
                    }
                    break;

                case R.id.btn_stop:
                    stopSong();
                    break;

                case R.id.button_buy:
                    Intent intent=new Intent(this,BuyOnline.class);
                    startActivity(intent);
                    break;

            }

    }

    @Override
    public boolean onLongClick(View v) {

        switch (v.getId())
        {
            case R.id.btn_forward:
                forwardSong();
                break;
            case R.id.btn_backward:
                backwardSong();
                break;
        }

        return false;
    }

    public void playSong(String selected_song)
    {

        Log.e("Select:",""+selected_song);

                    try {


                            AssetFileDescriptor assetFileDescriptor = getAssets().openFd(selected_song + ".mp3");
                            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                            mediaPlayer.prepare();
                            mediaPlayer.start();
                    }
                    catch (Exception e){Log.e("Exception:",""+e);}

    }

    public void playAndPauseCurrentSong()
    {
        if(paused==true)
        {
            mediaPlayer.start();
            paused=false;
            button_play.setBackground(getDrawable(R.drawable.img_btn_pause));
        }
                        else
                        {
                            Log.e("Here","!!");
                            mediaPlayer.pause();
                            paused=true;
                            button_play.setBackground(getDrawable(R.drawable.img_btn_play));
                        }
    }

    public void stopSong(){
        if(mediaPlayer.isPlaying() ||paused==true) {
            Log.e("TRUE:", "!");
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer=null;
            mediaPlayer=new MediaPlayer();
            newsong=true;
            button_play.setBackground(getDrawable(R.drawable.img_btn_play));

        }
    }

    public void forwardSong(){
        int currentposition=mediaPlayer.getCurrentPosition();
        if(currentposition+seekForwardTime<=mediaPlayer.getDuration())
        {
            mediaPlayer.seekTo(currentposition+seekForwardTime);
        }
        else {
            mediaPlayer.seekTo(mediaPlayer.getDuration());
        }
    }

    public void backwardSong(){
        int currentposition=mediaPlayer.getCurrentPosition();
        if(currentposition-seekBackwardTime>=0)
        {
            mediaPlayer.seekTo(currentposition-seekBackwardTime);
        }
        else {
            mediaPlayer.seekTo(0);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSong();
    }


    public void setScrollImage(String selected_song){
        Log.e("ScrollImage:",""+selected_song);
        switch (selected_song)
        {
            case "eminem":
                scrollImage.setImageResource(R.drawable.thumbnail_eminem);
                   viewAbout.setText(R.string.about_eminem);
                break;
            case "coldplay":
                scrollImage.setImageResource(R.drawable.thumbnail_coldplay);
                viewAbout.setText(R.string.about_coldplay);
                break;
            case "dj snake":
                scrollImage.setImageResource(R.drawable.thumbnail_djsnake);
                viewAbout.setText(R.string.about_djsnake);
                break;
            case "imagine dragons":
                scrollImage.setImageResource(R.drawable.thumbnail_imaginedragons);
                viewAbout.setText(R.string.about_imaginedragons);
                break;
        }
    }
}
