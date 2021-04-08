package com.jinendra.fact;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import static com.jinendra.fact.MusicComponent.mediaPlayer;

public class Customize extends AppCompatActivity {

    ImageView vPreviousMenuImageViewCustomizeActivity;
    Button vWallpaperButtonCustomizeActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);

        vPreviousMenuImageViewCustomizeActivity = (ImageView) findViewById(R.id.previousMenuImageViewCustomizeActivity);
        vPreviousMenuImageViewCustomizeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vPreviousMenuIntentCustomizeActivity = new Intent(Customize.this, Settings.class);
                startActivity(vPreviousMenuIntentCustomizeActivity);
                MusicComponent.shouldPlaySettingsActivity = false;
                MusicComponent.shouldPlayCustomizeActivity = true;
            }
        });

        vWallpaperButtonCustomizeActivity = (Button) findViewById(R.id.wallpaperButtonCustomizeActivity);
        vWallpaperButtonCustomizeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vWallpaperIntentCustomizeActivity = new Intent(Customize.this, Wallpaper.class);
                startActivity(vWallpaperIntentCustomizeActivity);
                MusicComponent.shouldPlayCustomizeActivity = true;
                MusicComponent.shouldPlayWallpaperActivity = false;
            }
        });


    }



    @Override
    protected void onResume() {
        super.onResume();

        if(mediaPlayer == null) {

            MusicComponent.BackgroundMusic(Customize.this, R.raw.bg_music);

        }

        if(mediaPlayer.isPlaying()){

            mediaPlayer.pause();

        }

        mediaPlayer.start();
        MusicComponent.onCompletion(mediaPlayer);

        SharedPreferences sharedPreferencesCustomizeActivity = getSharedPreferences("Background", Context.MODE_PRIVATE);
        int vSelectedColor = sharedPreferencesCustomizeActivity.getInt("integerSelectedColor", 0);
        ConstraintLayout vConstraintLayoutCustomizeActivity = findViewById(R.id.constraintLayoutCustomizeActivity);
        if (sharedPreferencesCustomizeActivity.getInt("integerSelectedColor", Color.WHITE) == vSelectedColor)
        {vConstraintLayoutCustomizeActivity.setBackgroundColor(vSelectedColor);}
        else
        {vConstraintLayoutCustomizeActivity.setBackgroundColor(Color.parseColor("#FDD935"));}

    }

    @Override
    protected void onPause() {
        super.onPause();

        if (!MusicComponent.shouldPlayCustomizeActivity) {

            mediaPlayer.pause();
            MusicComponent.shouldPlaySettingsActivity = false;
            MusicComponent.shouldPlayCustomizeActivity = true;

        }
    }

}