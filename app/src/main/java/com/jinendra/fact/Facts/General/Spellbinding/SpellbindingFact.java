package com.jinendra.fact.Facts.General.Spellbinding;

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


public class SpellbindingFact extends AppCompatActivity {


    ImageView vPreviousMenuImageViewSpellbindingFactActivity, vShareImageViewSpellbindingFactActivity;
    TextView vFactsTextViewSpellbindingFactActivity;
    ImageView vPreviousImageViewSpellbindingFactActivity;
    ImageView vPlayImageViewSpellbindingFactActivity, vPauseImageViewSpellbindingFactActivity;
    ImageView vNextImageViewSpellbindingFactActivity;
    private TextToSpeech vTextToSpeech;

    String[] SpellbindingFactsSpellbindingFactActivity = new String[999];
    int SpellbindingFactsCounter,j,k;

    private InterstitialAd vInterstitialAdSpellbindingFactActivity;
    AdView vBannerAdViewSpellbindingFactActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spellbinding_fact);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        vBannerAdViewSpellbindingFactActivity = findViewById(R.id.bannerAdViewSpellbindingFactActivity);
        AdRequest vBannerAdRequestSpellbindingFactActivity = new AdRequest.Builder().build();
        vBannerAdViewSpellbindingFactActivity.loadAd(vBannerAdRequestSpellbindingFactActivity);

        vInterstitialAdSpellbindingFactActivity = new InterstitialAd(this);
        vInterstitialAdSpellbindingFactActivity.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        vInterstitialAdSpellbindingFactActivity.loadAd(new AdRequest.Builder().build());

        vInterstitialAdSpellbindingFactActivity.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                vInterstitialAdSpellbindingFactActivity.loadAd(new AdRequest.Builder().build());
            }

        });

        vPreviousMenuImageViewSpellbindingFactActivity = findViewById(R.id.previousMenuImageViewSpellbindingFactActivity);
        vPreviousMenuImageViewSpellbindingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vPreviousMenuIntentSpellbindingFactActivity = new Intent(SpellbindingFact.this, SpellbindingFactList.class);
                MusicComponent.shouldPlaySpellbindingFactListActivity = false;
                MusicComponent.shouldPlaySpellbindingFactActivity = true;
                startActivity(vPreviousMenuIntentSpellbindingFactActivity);

                if(vTextToSpeech.isSpeaking()){
                    vTextToSpeech.stop();
                }
            }
        });

        vShareImageViewSpellbindingFactActivity = findViewById(R.id.shareImageViewSpellbindingFactActivity);
        vShareImageViewSpellbindingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "I found a amazing fact " + vFactsTextViewSpellbindingFactActivity.getText().toString();
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, s);
                startActivity(Intent.createChooser(shareIntent, "Share text via"));

                if (vInterstitialAdSpellbindingFactActivity.isLoaded()) {
                    vInterstitialAdSpellbindingFactActivity.show();
                } else {
                    Log.i("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        });

        vPreviousImageViewSpellbindingFactActivity = findViewById(R.id.previousImageViewSpellbindingFactActivity);
        vNextImageViewSpellbindingFactActivity = findViewById(R.id.nextImageViewSpellbindingFactActivity);
        vFactsTextViewSpellbindingFactActivity = findViewById(R.id.factsTextViewSpellbindingFactActivity);
        vPlayImageViewSpellbindingFactActivity = findViewById(R.id.playImageViewSpellbindingFactActivity);
        vPauseImageViewSpellbindingFactActivity = findViewById(R.id.pauseImageViewSpellbindingFactActivity);

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
                                    vPauseImageViewSpellbindingFactActivity.setVisibility(View.INVISIBLE);
                                    vPlayImageViewSpellbindingFactActivity.setVisibility(View.VISIBLE);
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
            vTextToSpeech.speak(vFactsTextViewSpellbindingFactActivity.getText().toString(),TextToSpeech.QUEUE_FLUSH,null,TextToSpeech.ACTION_TTS_QUEUE_PROCESSING_COMPLETED);
        }

        vPlayImageViewSpellbindingFactActivity.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                vPauseImageViewSpellbindingFactActivity.setVisibility(View.VISIBLE);
                vPlayImageViewSpellbindingFactActivity.setVisibility(View.INVISIBLE);
                mediaPlayer.pause();
                String s = vFactsTextViewSpellbindingFactActivity.getText().toString();
                vTextToSpeech.speak(s, TextToSpeech.QUEUE_ADD, null,"si");
            }
        });


        vPauseImageViewSpellbindingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vPlayImageViewSpellbindingFactActivity.setVisibility(View.VISIBLE);
                vPauseImageViewSpellbindingFactActivity.setVisibility(View.INVISIBLE);
                vTextToSpeech.stop();

                mediaPlayer.start();
            }
        });

        for (k = 0; k <= 998; k++){
            SpellbindingFactsSpellbindingFactActivity[k] = "factSpellbindingFactActivity" + k;
        }

        Intent fetchDataIntentSpellbindingFactActivity = getIntent();

        for(j = 0; j <= 998; j++){
            SpellbindingFactsSpellbindingFactActivity[j] = fetchDataIntentSpellbindingFactActivity.getStringExtra(SpellbindingFactList.extraTextSpellbindingFactListActivity[j]);
        }

        SpellbindingFactsCounter = fetchDataIntentSpellbindingFactActivity.getIntExtra(SpellbindingFactList.EXTRA_NUMBER_SPELLBINDING_FACT_LIST_ACTIVITY, 0);

        vFactsTextViewSpellbindingFactActivity.setText(SpellbindingFactsSpellbindingFactActivity[SpellbindingFactsCounter]);
        if (SpellbindingFactsCounter == 0) {
            vPreviousImageViewSpellbindingFactActivity.setImageResource(R.drawable.ic_previous_fade);
        }

        vPreviousImageViewSpellbindingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (SpellbindingFactsCounter == 0 ){
                    vFactsTextViewSpellbindingFactActivity.setText(SpellbindingFactsSpellbindingFactActivity[SpellbindingFactsCounter]);
                }else if (SpellbindingFactsCounter == 1){
                    SpellbindingFactsCounter--;
                    vPreviousImageViewSpellbindingFactActivity.setImageResource(R.drawable.ic_previous_fade);
                    vFactsTextViewSpellbindingFactActivity.setText(SpellbindingFactsSpellbindingFactActivity[SpellbindingFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewSpellbindingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewSpellbindingFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                }else if (SpellbindingFactsCounter % 7 == 6){
                    SpellbindingFactsCounter--;
                    vFactsTextViewSpellbindingFactActivity.setText(SpellbindingFactsSpellbindingFactActivity[SpellbindingFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewSpellbindingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewSpellbindingFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                    if (vInterstitialAdSpellbindingFactActivity.isLoaded()) {
                        vInterstitialAdSpellbindingFactActivity.show();
                    } else {
                        Log.i("TAG", "The interstitial wasn't loaded yet.");
                    }
                }else{
                    SpellbindingFactsCounter--;
                    vFactsTextViewSpellbindingFactActivity.setText(SpellbindingFactsSpellbindingFactActivity[SpellbindingFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewSpellbindingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewSpellbindingFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                }

            }
        });

        vNextImageViewSpellbindingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (SpellbindingFactsCounter == 0) {
                    SpellbindingFactsCounter++;
                    vPreviousImageViewSpellbindingFactActivity.setImageResource(R.drawable.ic_previous);
                    vFactsTextViewSpellbindingFactActivity.setText(SpellbindingFactsSpellbindingFactActivity[SpellbindingFactsCounter]);
                    if (vTextToSpeech.isSpeaking()) {
                        vTextToSpeech.stop();
                        vPlayImageViewSpellbindingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewSpellbindingFactActivity.setVisibility(View.INVISIBLE);
                        mediaPlayer.start();
                    }
                } else if (SpellbindingFactsCounter % 7 == 6) {
                    SpellbindingFactsCounter++;
                    vFactsTextViewSpellbindingFactActivity.setText(SpellbindingFactsSpellbindingFactActivity[SpellbindingFactsCounter]);
                    if (vTextToSpeech.isSpeaking()) {
                        vTextToSpeech.stop();
                        vPlayImageViewSpellbindingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewSpellbindingFactActivity.setVisibility(View.INVISIBLE);
                        mediaPlayer.start();
                    }
                    if (vInterstitialAdSpellbindingFactActivity.isLoaded()) {
                        vInterstitialAdSpellbindingFactActivity.show();
                    } else {
                        Log.i("TAG", "The interstitial wasn't loaded yet.");
                    }
                } else if (SpellbindingFactsCounter % 7 != 6 && SpellbindingFactsCounter <= 997) {
                    SpellbindingFactsCounter++;
                    vFactsTextViewSpellbindingFactActivity.setText(SpellbindingFactsSpellbindingFactActivity[SpellbindingFactsCounter]);
                    if (vTextToSpeech.isSpeaking()) {
                        vTextToSpeech.stop();
                        vPlayImageViewSpellbindingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewSpellbindingFactActivity.setVisibility(View.INVISIBLE);
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

            MusicComponent.BackgroundMusic(SpellbindingFact.this, R.raw.bg_music);

        }

        if(mediaPlayer.isPlaying()){

            mediaPlayer.pause();

        }

        if(MusicComponent.shouldPlaySpellbindingFactActivity){
            MusicComponent.shouldPlaySpellbindingFactActivity = false;
        }

        mediaPlayer.start();
        MusicComponent.onCompletion(mediaPlayer);

        SharedPreferences sharedPreferencesSpellbindingFactActivity = getSharedPreferences("Background", Context.MODE_PRIVATE);
        int vSelectedColorSpellbindingFactActivity = sharedPreferencesSpellbindingFactActivity.getInt("integerSelectedColor", 0);
        ConstraintLayout vConstraintLayoutSpellbindingFactActivity = findViewById(R.id.constraintLayoutSpellbindingFactActivity);
        if (sharedPreferencesSpellbindingFactActivity.getInt("integerSelectedColor", Color.WHITE) == vSelectedColorSpellbindingFactActivity)
        {vConstraintLayoutSpellbindingFactActivity.setBackgroundColor(vSelectedColorSpellbindingFactActivity);}
        else
        {vConstraintLayoutSpellbindingFactActivity.setBackgroundColor(Color.parseColor("#FDD935"));}

    }

    @Override
    protected void onPause() {
        super.onPause();

        if(vTextToSpeech.isSpeaking()){
            vTextToSpeech.stop();
            vPlayImageViewSpellbindingFactActivity.setVisibility(View.VISIBLE);
            vPauseImageViewSpellbindingFactActivity.setVisibility(View.INVISIBLE);

        }

        if (!MusicComponent.shouldPlaySpellbindingFactActivity){

            mediaPlayer.pause();
            MusicComponent.shouldPlaySpellbindingFactListActivity = false;
            MusicComponent.shouldPlaySpellbindingFactActivity = true;

        }
    }

}