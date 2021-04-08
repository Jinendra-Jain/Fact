package com.jinendra.fact;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import static com.jinendra.fact.MusicComponent.mediaPlayer;

public class SolidColors extends AppCompatActivity {

    ImageView vPreviousMenuImageViewSolidColorsActivity;

    // Creating variable for Button to pick color
    Button vColorPickerButtonSolidColorsActivity;

    // Creating variable for RewardedAd
    RewardedAd rewardedAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solid_colors);

        vPreviousMenuImageViewSolidColorsActivity = findViewById(R.id.previousMenuImageViewSolidColorsActivity);
        vPreviousMenuImageViewSolidColorsActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vPreviousMenuIntentSolidColorsActivity = new Intent(SolidColors.this, Wallpaper.class);
                startActivity(vPreviousMenuIntentSolidColorsActivity);
                MusicComponent.shouldPlayWallpaperActivity = false;
                MusicComponent.shouldPlaySolidColorsActivity = true;
            }
        });

        //Instantiating color picker Button
        vColorPickerButtonSolidColorsActivity = (Button) findViewById(R.id.colorPickerButtonSolidColorsActivity);
        Toast.makeText(SolidColors.this, "Rewarded Ad is loading please wait..", Toast.LENGTH_LONG).show();
        vColorPickerButtonSolidColorsActivity.setEnabled(false);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        rewardedAd = new RewardedAd(this, "ca-app-pub-3940256099942544/5224354917");

        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                // Ad successfully loaded.
                Toast.makeText(SolidColors.this, "Rewarded Ad is loaded click color picker button to change Background Color",Toast.LENGTH_LONG).show();
                vColorPickerButtonSolidColorsActivity.setEnabled(true);
            }

            @Override
            public void onRewardedAdFailedToLoad(LoadAdError adError) {
                // Ad failed to load.
                Toast.makeText(SolidColors.this, "Rewarded Ad failed to load. Try again later.",Toast.LENGTH_LONG).show();
            }
        };
        rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);

        ConstraintLayout vConstraintLayoutSolidColorsActivity = (ConstraintLayout) findViewById(R.id.constraintLayoutSolidColorsActivity);
        vConstraintLayoutSolidColorsActivity.setBackgroundColor(Color.parseColor("#FDD935"));

        //Setting On Click Listener for color picker Button
        vColorPickerButtonSolidColorsActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (rewardedAd.isLoaded()) {
                    Activity activityContext = SolidColors.this;
                    RewardedAdCallback adCallback = new RewardedAdCallback() {
                        @Override
                        public void onRewardedAdOpened() {
                            // Ad opened.
                            mediaPlayer.pause();
                        }

                        @Override
                        public void onRewardedAdClosed() {
                            // Ad closed.
                            mediaPlayer.start();
                        }

                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem reward) {
                            // User earned reward.
                            ColorPickerDialogBuilder
                                    .with(SolidColors.this)
                                    .setTitle("Choose color")
                                    .initialColor(Color.parseColor("#DDDDDD"))
                                    .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                                    .density(12)
                                    .setOnColorSelectedListener(new OnColorSelectedListener() {
                                        @Override
                                        public void onColorSelected(int selectedColor) {
                                            //Creating a Toast Message on selection of a color
                                            Toast.makeText(SolidColors.this, "onColorSelected: 0x" + Integer.toHexString(selectedColor), Toast.LENGTH_LONG).show();
                                        }
                                    })
                                    //
                                    .setPositiveButton("ok", new ColorPickerClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                                            ConstraintLayout vConstraintLayoutSolidColorsActivity = (ConstraintLayout) findViewById(R.id.constraintLayoutSolidColorsActivity);
                                            vConstraintLayoutSolidColorsActivity.setBackgroundColor(selectedColor);
                                            SharedPreferences sharedPreferencesSolidColorsActivity = getSharedPreferences("Background", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor sharedPreferencesEditorSolidColorsActivity = sharedPreferencesSolidColorsActivity.edit();
                                            sharedPreferencesEditorSolidColorsActivity.putInt("integerSelectedColor", selectedColor);
                                            sharedPreferencesEditorSolidColorsActivity.apply();

                                            Intent wallpaperActivityIntentSolidColorsActivity = new Intent(SolidColors.this, Wallpaper.class);
                                            startActivity(wallpaperActivityIntentSolidColorsActivity);
                                        }
                                    })
                                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    })
                                    .build()
                                    .show();

                        }

                        @Override
                        public void onRewardedAdFailedToShow(AdError adError) {
                            // Ad failed to display.
                        }
                    };
                    rewardedAd.show(activityContext, adCallback);
                } else {
                    Log.d("TAG", "The rewarded ad wasn't loaded yet.");
                }

            }
        });



    }



    @Override
    protected void onResume() {
        super.onResume();

        if(mediaPlayer == null) {

            MusicComponent.BackgroundMusic(SolidColors.this, R.raw.bg_music);

        }

        if(mediaPlayer.isPlaying()){

            mediaPlayer.pause();

        }

        mediaPlayer.start();
        MusicComponent.onCompletion(mediaPlayer);

        ConstraintLayout vConstraintLayoutSolidColorsActivity = (ConstraintLayout) findViewById(R.id.constraintLayoutSolidColorsActivity);
        SharedPreferences sharedPreferencesSolidColorsActivity = getSharedPreferences("Background", Context.MODE_PRIVATE);
        int vSelectedColor = sharedPreferencesSolidColorsActivity.getInt("integerSelectedColor", 0);
        if (sharedPreferencesSolidColorsActivity.getInt("integerSelectedColor", Color.WHITE) == vSelectedColor)
        {vConstraintLayoutSolidColorsActivity.setBackgroundColor(vSelectedColor);}
        else
        {vConstraintLayoutSolidColorsActivity.setBackgroundColor(Color.parseColor("#FDD935"));}
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (!MusicComponent.shouldPlaySolidColorsActivity){

            mediaPlayer.pause();
            MusicComponent.shouldPlayWallpaperActivity = false;
            MusicComponent.shouldPlaySolidColorsActivity = true;

        }
    }

}