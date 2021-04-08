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


public class Wallpaper extends AppCompatActivity {

    ImageView vPreviousMenuImageViewWallpaperActivity;
    ConstraintLayout vConstraintLayoutWallpaperActivity;
    Button vSolidColorsActivityButtonWallpaperActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);

        vPreviousMenuImageViewWallpaperActivity = (ImageView) findViewById(R.id.previousMenuImageViewWallpaperActivity);
        vPreviousMenuImageViewWallpaperActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vPreviousMenuIntentWallpaperActivity = new Intent(Wallpaper.this, Customize.class);
                startActivity(vPreviousMenuIntentWallpaperActivity);
                MusicComponent.shouldPlayCustomizeActivity = false;
                MusicComponent.shouldPlayWallpaperActivity = true;
            }
        });


        vConstraintLayoutWallpaperActivity = findViewById(R.id.constraintLayoutWallpaperActivity);
        vSolidColorsActivityButtonWallpaperActivity = findViewById(R.id.solidColorsActivityButtonWallpaperActivity);
        vSolidColorsActivityButtonWallpaperActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent solidColorsActivityIntentWallpaperActivity = new Intent(Wallpaper.this, SolidColors.class);
                startActivity(solidColorsActivityIntentWallpaperActivity);
                MusicComponent.shouldPlayWallpaperActivity = true;
                MusicComponent.shouldPlaySolidColorsActivity = false;



            }
        });



    }



    @Override
    protected void onResume() {
        super.onResume();

        if(mediaPlayer == null) {

            MusicComponent.BackgroundMusic(Wallpaper.this, R.raw.bg_music);

        }

        if(mediaPlayer.isPlaying()){

            mediaPlayer.pause();

        }

        mediaPlayer.start();
        MusicComponent.onCompletion(mediaPlayer);

        SharedPreferences sharedPreferencesWallpaperActivity1 = getSharedPreferences("Background", Context.MODE_PRIVATE);
        int vSelectedColor1 = sharedPreferencesWallpaperActivity1.getInt("integerSelectedColor", 0);
        ConstraintLayout vConstraintLayoutWallpaperActivity = findViewById(R.id.constraintLayoutWallpaperActivity);
        if (sharedPreferencesWallpaperActivity1.getInt("integerSelectedColor", Color.WHITE) == vSelectedColor1)
        {vConstraintLayoutWallpaperActivity.setBackgroundColor(vSelectedColor1);}
        else
        {vConstraintLayoutWallpaperActivity.setBackgroundColor(Color.parseColor("#FDD935"));}

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!MusicComponent.shouldPlayAngerFactActivity) {

            mediaPlayer.pause();
            MusicComponent.shouldPlayCustomizeActivity = false;
            MusicComponent.shouldPlayWallpaperActivity = true;

        }
    }

}