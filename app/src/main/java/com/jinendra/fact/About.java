package com.jinendra.fact;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
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

public class About extends AppCompatActivity {

    ImageView vPreviousMenuImageViewAboutActivity;
    Dialog vDeveloperDialogAboutActivity;
    AdView vBannerAdViewAboutActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        vPreviousMenuImageViewAboutActivity = findViewById(R.id.previousMenuImageViewAboutActivity);
        vPreviousMenuImageViewAboutActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vPreviousMenuIntentAboutActivity = new Intent(About.this,Settings.class);
                startActivity(vPreviousMenuIntentAboutActivity);
                MusicComponent.shouldPlaySettingsActivity = false;
                MusicComponent.shouldPlayAboutActivity = true;
            }
        });

        vDeveloperDialogAboutActivity = new Dialog(About.this);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        vBannerAdViewAboutActivity = findViewById(R.id.bannerAdViewAboutActivity);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        vBannerAdViewAboutActivity.loadAd(adRequest1);



    }

    public void ShowPopup1(View v){
        TextView txtClose;
        ImageButton vFacebookImageButtonAboutActivity,vTwitterImageButtonAboutActivity;
        vDeveloperDialogAboutActivity.setContentView(R.layout.custompopup_about_activity);

        vFacebookImageButtonAboutActivity = vDeveloperDialogAboutActivity.findViewById(R.id.facebookImageButtonAboutActivity);
        vFacebookImageButtonAboutActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://www.facebook.com/profile.php?id=100030471471654";
                Intent google=new Intent(Intent.ACTION_VIEW);
                google.setData(Uri.parse(url));
                startActivity(google);
                mediaPlayer.pause();
            }
        });

        vTwitterImageButtonAboutActivity = vDeveloperDialogAboutActivity.findViewById(R.id.twitterImageButtonAboutActivity);
        vTwitterImageButtonAboutActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url1="https://twitter.com/jainjinendra225";
                Intent google1=new Intent(Intent.ACTION_VIEW);
                google1.setData(Uri.parse(url1));
                startActivity(google1);
                mediaPlayer.pause();
            }
        });

        txtClose = vDeveloperDialogAboutActivity.findViewById(R.id.textClose);
        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vDeveloperDialogAboutActivity.dismiss();
            }
        });
        vDeveloperDialogAboutActivity.show();
        Objects.requireNonNull(vDeveloperDialogAboutActivity.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(mediaPlayer == null) {

            MusicComponent.BackgroundMusic(About.this, R.raw.bg_music);

        }

        if(mediaPlayer.isPlaying()){

            mediaPlayer.pause();

        }

        mediaPlayer.start();
        MusicComponent.onCompletion(mediaPlayer);

        SharedPreferences sharedPreferencesAboutActivity = getSharedPreferences("Background", Context.MODE_PRIVATE);
        int vSelectedColor = sharedPreferencesAboutActivity.getInt("integerSelectedColor", 0);
        ConstraintLayout vConstraintLayoutAboutActivity = findViewById(R.id.constraintLayoutAboutActivity);
        if (sharedPreferencesAboutActivity.getInt("integerSelectedColor", Color.WHITE) == vSelectedColor)
        {vConstraintLayoutAboutActivity.setBackgroundColor(vSelectedColor);}
        else
        {vConstraintLayoutAboutActivity.setBackgroundColor(Color.parseColor("#FDD935"));}

    }

    @Override
    protected void onPause() {
        super.onPause();

        if (!MusicComponent.shouldPlayAboutActivity) {

            mediaPlayer.pause();
            MusicComponent.shouldPlaySettingsActivity = false;
            MusicComponent.shouldPlayAboutActivity = true;

        }
    }

}