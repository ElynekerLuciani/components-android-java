package com.elyneker.componentes;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import static com.elyneker.componentes.R.raw.beach;

public class PlayActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        mediaPlayer = null;

    }

    public void tocarSom(View view) {
        if(mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this,beach);
            mediaPlayer.start();
        }


    }

    public void pararSom(View view) {
        mediaPlayer.stop();
        mediaPlayer = null;

    }


}