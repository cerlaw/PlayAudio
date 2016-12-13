package com.example.dell.playaudio;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button play;

    private Button pause;

    private Button stop;

    private MediaPlayer player = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = (Button) findViewById(R.id.play);
        pause = (Button) findViewById(R.id.pause);
        stop = (Button) findViewById(R.id.stop);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
        initalMediaplayer();
    }

    private void initalMediaplayer() {
        File file = new File(Environment.getExternalStorageDirectory(),
                "music.mp3");
        //需要添加权限
        try {
            player.setDataSource(file.getPath());
            player.prepare();
        } catch (IOException e) {
            Log.d("boss","error");
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.play:
                if(!player.isPlaying()){
                    player.start();
                }
                break;
            case R.id.pause:
                if(player.isPlaying()){
                    player.pause();
                }
                break;
            case R.id.stop:
                if(player.isPlaying()){
                    player.reset();
                    initalMediaplayer();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(player != null){
            player.stop();
            player.release();
        }
    }
}
