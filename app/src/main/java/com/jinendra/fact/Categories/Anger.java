package com.jinendra.fact.Categories;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.jinendra.fact.Category;
import com.jinendra.fact.Facts.Psychological.Anger.AngerFactList;
import com.jinendra.fact.MusicComponent;
import com.jinendra.fact.R;

import static com.jinendra.fact.MusicComponent.mediaPlayer;

public class Anger extends AppCompatActivity {

    ImageView vPreviousMenuImageViewAngerActivity,vInfoImageViewAngerActivity;
    Button vAngerFactListButtonAngerActivity;
    AdView vBannerAdViewAngerActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anger);

        vPreviousMenuImageViewAngerActivity = findViewById(R.id.previousMenuImageViewAngerActivity);
        vPreviousMenuImageViewAngerActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent categoryIntentAngerActivity = new Intent(Anger.this, Category.class);
                MusicComponent.shouldPlayCategoryActivity = false;
                MusicComponent.shouldPlayAngerActivity = true;
                startActivity(categoryIntentAngerActivity);
            }
        });

        vInfoImageViewAngerActivity = findViewById(R.id.infoImageViewAngerActivity);
        vInfoImageViewAngerActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String infoUrlAngerActivity = "https://en.wikipedia.org/wiki/Anger";
                Intent infoIntentAngerActivity = new Intent(Intent.ACTION_VIEW);
                infoIntentAngerActivity.setData(Uri.parse(infoUrlAngerActivity));
                startActivity(infoIntentAngerActivity);
            }
        });

        vAngerFactListButtonAngerActivity = findViewById(R.id.angerFactListButtonAngerActivity);
        vAngerFactListButtonAngerActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent angerFactListIntentAngerActivity = new Intent(Anger.this, AngerFactList.class);
                MusicComponent.shouldPlayAngerActivity = true;
                MusicComponent.shouldPlayAngerFactListActivity = false;
                startActivity(angerFactListIntentAngerActivity);
            }
        });

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        vBannerAdViewAngerActivity = findViewById(R.id.bannerAdViewAngerActivity);
        AdRequest vBannerAdRequestAngerActivity = new AdRequest.Builder().build();
        vBannerAdViewAngerActivity.loadAd(vBannerAdRequestAngerActivity);



    }



    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferencesAngerActivity = getSharedPreferences("Background", Context.MODE_PRIVATE);
        int vSelectedColorAngerActivity = sharedPreferencesAngerActivity.getInt("integerSelectedColor", 0);
        ConstraintLayout vConstraintLayoutAngerActivity = findViewById(R.id.constraintLayoutAngerActivity);
        if (sharedPreferencesAngerActivity.getInt("integerSelectedColor", Color.WHITE) == vSelectedColorAngerActivity)
        {vConstraintLayoutAngerActivity.setBackgroundColor(vSelectedColorAngerActivity);}
        else
        {vConstraintLayoutAngerActivity.setBackgroundColor(Color.parseColor("#FDD935"));}


        if(mediaPlayer == null) {

            MusicComponent.BackgroundMusic(Anger.this, R.raw.bg_music);

        }

        if(mediaPlayer.isPlaying()){

            mediaPlayer.pause();

        }

        mediaPlayer.start();
        MusicComponent.onCompletion(mediaPlayer);


    }

    @Override
    protected void onPause() {
        super.onPause();

        if (!MusicComponent.shouldPlayAngerActivity){

            mediaPlayer.pause();
            MusicComponent.shouldPlayCategoryActivity = false;
            MusicComponent.shouldPlayAngerActivity = true;

        }
    }


}