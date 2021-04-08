package com.jinendra.fact;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Objects;

import static com.jinendra.fact.MusicComponent.mediaPlayer;

public class Settings extends AppCompatActivity {

    AdView vBannerAdViewSettingsActivity;
    Dialog myDialog;

    ImageView vPreviousMenuImageViewSettingsActivity;
    Button vCustomizeButtonSettingsActivity,vAboutButtonSettingsActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        vBannerAdViewSettingsActivity = findViewById(R.id.bannerAdViewSettingsActivity);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        vBannerAdViewSettingsActivity.loadAd(adRequest1);

        vPreviousMenuImageViewSettingsActivity = (ImageView) findViewById(R.id.previousMenuImageViewSettingsActivity);
        vPreviousMenuImageViewSettingsActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent angerFactList = new Intent(Settings.this, Category.class);
                startActivity(angerFactList);
                MusicComponent.shouldPlayCategoryActivity = false;
                MusicComponent.shouldPlaySettingsActivity = true;
            }
        });

        vCustomizeButtonSettingsActivity = (Button) findViewById(R.id.customizeButtonSettingsActivity);
        vCustomizeButtonSettingsActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vCustomizeIntentSettingsActivity = new Intent(Settings.this, Customize.class);
                startActivity(vCustomizeIntentSettingsActivity);
                MusicComponent.shouldPlaySettingsActivity = true;
                MusicComponent.shouldPlayCustomizeActivity = false;
            }
        });

        myDialog = new Dialog(Settings.this);

        vAboutButtonSettingsActivity = (Button) findViewById(R.id.aboutButtonSettingsActivity);
        vAboutButtonSettingsActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutActivityIntentSettingsActivity = new Intent(Settings.this, About.class);
                startActivity(aboutActivityIntentSettingsActivity);
                MusicComponent.shouldPlaySettingsActivity = true;
                MusicComponent.shouldPlayAboutActivity = false;
            }
        });



    }

    public void ShowPopup(View v){
        TextView txtClose;
        myDialog.setContentView(R.layout.custompopup);



        txtClose = (TextView) myDialog.findViewById(R.id.textClose);
        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
        Objects.requireNonNull(myDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferencesSettingsActivity = getSharedPreferences("Background", Context.MODE_PRIVATE);
        int vSelectedColor = sharedPreferencesSettingsActivity.getInt("integerSelectedColor", 0);
        ConstraintLayout vConstraintLayoutSettingsActivity = findViewById(R.id.constraintLayoutSettingsActivity);
        if (sharedPreferencesSettingsActivity.getInt("integerSelectedColor", Color.WHITE) == vSelectedColor)
        {vConstraintLayoutSettingsActivity.setBackgroundColor(vSelectedColor);}
        else
        {vConstraintLayoutSettingsActivity.setBackgroundColor(Color.parseColor("#FDD935"));}

        if(mediaPlayer == null) {

            MusicComponent.BackgroundMusic(Settings.this, R.raw.bg_music);

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

        if (!MusicComponent.shouldPlaySettingsActivity){

            mediaPlayer.pause();
            MusicComponent.shouldPlayCategoryActivity = false;
            MusicComponent.shouldPlaySettingsActivity = true;

        }
    }

}

