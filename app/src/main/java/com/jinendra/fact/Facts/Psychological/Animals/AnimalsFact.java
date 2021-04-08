package com.jinendra.fact.Facts.Psychological.Animals;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.jinendra.fact.MusicComponent;
import com.jinendra.fact.R;

import java.util.Locale;

import static com.jinendra.fact.MusicComponent.mediaPlayer;


public class AnimalsFact extends AppCompatActivity {


    ImageView vPreviousMenuImageViewAnimalsFactActivity, vShareImageViewAnimalsFactActivity;
    TextView vFactsTextViewAnimalsFactActivity;
    ImageView vPreviousImageViewAnimalsFactActivity;
    ImageView vPlayImageViewAnimalsFactActivity, vPauseImageViewAnimalsFactActivity;
    ImageView vNextImageViewAnimalsFactActivity;
    private TextToSpeech vTextToSpeech;

    String[] animalsFactsAnimalsFactActivity = new String[515];
    int animalsFactsCounter,j,k;

    private InterstitialAd vInterstitialAdAnimalsFactActivity;
    AdView vBannerAdViewAnimalsFactActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals_fact);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        vBannerAdViewAnimalsFactActivity = findViewById(R.id.bannerAdViewAnimalsFactActivity);
        AdRequest vBannerAdRequestAnimalsFactActivity = new AdRequest.Builder().build();
        vBannerAdViewAnimalsFactActivity.loadAd(vBannerAdRequestAnimalsFactActivity);

        vInterstitialAdAnimalsFactActivity = new InterstitialAd(this);
        vInterstitialAdAnimalsFactActivity.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        vInterstitialAdAnimalsFactActivity.loadAd(new AdRequest.Builder().build());

        vInterstitialAdAnimalsFactActivity.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                vInterstitialAdAnimalsFactActivity.loadAd(new AdRequest.Builder().build());
            }

        });

        vPreviousMenuImageViewAnimalsFactActivity = findViewById(R.id.previousMenuImageViewAnimalsFactActivity);
        vPreviousMenuImageViewAnimalsFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vPreviousMenuIntentAnimalsFactActivity = new Intent(AnimalsFact.this, AnimalsFactList.class);
                MusicComponent.shouldPlayAnimalsFactListActivity = false;
                MusicComponent.shouldPlayAnimalsFactActivity = true;
                startActivity(vPreviousMenuIntentAnimalsFactActivity);

                if(vTextToSpeech.isSpeaking()){
                    vTextToSpeech.stop();
                }
            }
        });

        vShareImageViewAnimalsFactActivity = findViewById(R.id.shareImageViewAnimalsFactActivity);
        vShareImageViewAnimalsFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "I found a amazing fact " + vFactsTextViewAnimalsFactActivity.getText().toString();
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, s);
                startActivity(Intent.createChooser(shareIntent, "Share text via"));

                if (vInterstitialAdAnimalsFactActivity.isLoaded()) {
                    vInterstitialAdAnimalsFactActivity.show();
                } else {
                    Log.i("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        });

        vPreviousImageViewAnimalsFactActivity = findViewById(R.id.previousImageViewAnimalsFactActivity);
        vNextImageViewAnimalsFactActivity = findViewById(R.id.nextImageViewAnimalsFactActivity);
        vFactsTextViewAnimalsFactActivity = findViewById(R.id.factsTextViewAnimalsFactActivity);
        vPlayImageViewAnimalsFactActivity = findViewById(R.id.playImageViewAnimalsFactActivity);
        vPauseImageViewAnimalsFactActivity = findViewById(R.id.pauseImageViewAnimalsFactActivity);

        vTextToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status==TextToSpeech.SUCCESS){
                    int result = vTextToSpeech.setLanguage(Locale.US);

                    if (result==TextToSpeech.LANG_MISSING_DATA||result==TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.i("TextToSpeech","Language Not Supported");
                    }

                    vTextToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                        @Override
                        public void onStart(String utteranceId) {
                            Log.i("TextToSpeech","On Start");
                        }

                        @Override
                        public void onDone(String utteranceId) {
                            Log.i("TextToSpeech","On Done");
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {

                                    // Stuff that updates the User Interface
                                    vPauseImageViewAnimalsFactActivity.setVisibility(View.INVISIBLE);
                                    vPlayImageViewAnimalsFactActivity.setVisibility(View.VISIBLE);
                                    mediaPlayer.start();
                                }
                            });
                        }

                        @Override
                        public void onError(String utteranceId) {
                            Log.i("TextToSpeech","On Error");
                        }
                    });

                }else {
                    Log.i("TextToSpeech","Initialization Failed");
                }
            }
        });


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            vTextToSpeech.speak(vFactsTextViewAnimalsFactActivity.getText().toString(),TextToSpeech.QUEUE_FLUSH,null,TextToSpeech.ACTION_TTS_QUEUE_PROCESSING_COMPLETED);
        }

        vPlayImageViewAnimalsFactActivity.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                vPauseImageViewAnimalsFactActivity.setVisibility(View.VISIBLE);
                vPlayImageViewAnimalsFactActivity.setVisibility(View.INVISIBLE);
                mediaPlayer.pause();
                String s = vFactsTextViewAnimalsFactActivity.getText().toString();
                vTextToSpeech.speak(s, TextToSpeech.QUEUE_ADD, null,"si");
            }
        });


        vPauseImageViewAnimalsFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vPlayImageViewAnimalsFactActivity.setVisibility(View.VISIBLE);
                vPauseImageViewAnimalsFactActivity.setVisibility(View.INVISIBLE);
                vTextToSpeech.stop();

                mediaPlayer.start();
            }
        });

        for (k = 0; k <= 514; k++){
            animalsFactsAnimalsFactActivity[k] = "factAnimalsFactActivity" + k;
        }

        Intent fetchDataIntentAnimalsFactActivity = getIntent();

        for(j = 0; j <= 514; j++){
            animalsFactsAnimalsFactActivity[j] = fetchDataIntentAnimalsFactActivity.getStringExtra(AnimalsFactList.extraTextAnimalsFactListActivity[j]);
        }

        animalsFactsCounter = fetchDataIntentAnimalsFactActivity.getIntExtra(AnimalsFactList.EXTRA_NUMBER_ANIMALS_FACT_LIST_ACTIVITY, 0);

        vFactsTextViewAnimalsFactActivity.setText(animalsFactsAnimalsFactActivity[animalsFactsCounter]);
        if (animalsFactsCounter == 0) {
            vPreviousImageViewAnimalsFactActivity.setImageResource(R.drawable.ic_previous_fade);
        }

        vPreviousImageViewAnimalsFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (animalsFactsCounter == 0 ){
                    vFactsTextViewAnimalsFactActivity.setText(animalsFactsAnimalsFactActivity[animalsFactsCounter]);
                }else if (animalsFactsCounter == 1){
                    animalsFactsCounter--;
                    vPreviousImageViewAnimalsFactActivity.setImageResource(R.drawable.ic_previous_fade);
                    vFactsTextViewAnimalsFactActivity.setText(animalsFactsAnimalsFactActivity[animalsFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewAnimalsFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewAnimalsFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                }else if (animalsFactsCounter % 7 == 6){
                    animalsFactsCounter--;
                        vFactsTextViewAnimalsFactActivity.setText(animalsFactsAnimalsFactActivity[animalsFactsCounter]);
                        if(vTextToSpeech.isSpeaking()){
                            vPlayImageViewAnimalsFactActivity.setVisibility(View.VISIBLE);
                            vPauseImageViewAnimalsFactActivity.setVisibility(View.INVISIBLE);
                            vTextToSpeech.stop();
                            mediaPlayer.start();
                        }
                    if (vInterstitialAdAnimalsFactActivity.isLoaded()) {
                        vInterstitialAdAnimalsFactActivity.show();
                    } else {
                        Log.i("TAG", "The interstitial wasn't loaded yet.");
                    }
                }else{
                    animalsFactsCounter--;
                    vFactsTextViewAnimalsFactActivity.setText(animalsFactsAnimalsFactActivity[animalsFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewAnimalsFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewAnimalsFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                }

            }
        });

        vNextImageViewAnimalsFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (animalsFactsCounter == 0) {
                    animalsFactsCounter++;
                    vPreviousImageViewAnimalsFactActivity.setImageResource(R.drawable.ic_previous);
                    vFactsTextViewAnimalsFactActivity.setText(animalsFactsAnimalsFactActivity[animalsFactsCounter]);
                    if (vTextToSpeech.isSpeaking()) {
                        vTextToSpeech.stop();
                        vPlayImageViewAnimalsFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewAnimalsFactActivity.setVisibility(View.INVISIBLE);
                        mediaPlayer.start();
                    }
                } else if (animalsFactsCounter % 7 == 6) {
                        animalsFactsCounter++;
                        vFactsTextViewAnimalsFactActivity.setText(animalsFactsAnimalsFactActivity[animalsFactsCounter]);
                        if (vTextToSpeech.isSpeaking()) {
                            vTextToSpeech.stop();
                            vPlayImageViewAnimalsFactActivity.setVisibility(View.VISIBLE);
                            vPauseImageViewAnimalsFactActivity.setVisibility(View.INVISIBLE);
                            mediaPlayer.start();
                        }
                        if (vInterstitialAdAnimalsFactActivity.isLoaded()) {
                            vInterstitialAdAnimalsFactActivity.show();
                        } else {
                            Log.i("TAG", "The interstitial wasn't loaded yet.");
                        }
                } else if (animalsFactsCounter % 7 != 6 && animalsFactsCounter <= 513) {
                        animalsFactsCounter++;
                        vFactsTextViewAnimalsFactActivity.setText(animalsFactsAnimalsFactActivity[animalsFactsCounter]);
                        if (vTextToSpeech.isSpeaking()) {
                            vTextToSpeech.stop();
                            vPlayImageViewAnimalsFactActivity.setVisibility(View.VISIBLE);
                            vPauseImageViewAnimalsFactActivity.setVisibility(View.INVISIBLE);
                            mediaPlayer.start();
                        }
                    }
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();

        if(mediaPlayer == null) {

            MusicComponent.BackgroundMusic(AnimalsFact.this, R.raw.bg_music);

        }

        if(mediaPlayer.isPlaying()){

            mediaPlayer.pause();

        }

        if(MusicComponent.shouldPlayAnimalsFactActivity){
            MusicComponent.shouldPlayAnimalsFactActivity = false;
        }

        mediaPlayer.start();
        MusicComponent.onCompletion(mediaPlayer);

        SharedPreferences sharedPreferencesAnimalsFactActivity = getSharedPreferences("Background", Context.MODE_PRIVATE);
        int vSelectedColorAnimalsFactActivity = sharedPreferencesAnimalsFactActivity.getInt("integerSelectedColor", 0);
        ConstraintLayout vConstraintLayoutAnimalsFactActivity = findViewById(R.id.constraintLayoutAnimalsFactActivity);
        if (sharedPreferencesAnimalsFactActivity.getInt("integerSelectedColor", Color.WHITE) == vSelectedColorAnimalsFactActivity)
        {vConstraintLayoutAnimalsFactActivity.setBackgroundColor(vSelectedColorAnimalsFactActivity);}
        else
        {vConstraintLayoutAnimalsFactActivity.setBackgroundColor(Color.parseColor("#FDD935"));}

    }

    @Override
    protected void onPause() {
        super.onPause();

        if(vTextToSpeech.isSpeaking()){
            vTextToSpeech.stop();
            vPlayImageViewAnimalsFactActivity.setVisibility(View.VISIBLE);
            vPauseImageViewAnimalsFactActivity.setVisibility(View.INVISIBLE);

        }

        if (!MusicComponent.shouldPlayAnimalsFactActivity){

            mediaPlayer.pause();
            MusicComponent.shouldPlayAnimalsFactListActivity = false;
            MusicComponent.shouldPlayAnimalsFactActivity = true;

        }
    }

}