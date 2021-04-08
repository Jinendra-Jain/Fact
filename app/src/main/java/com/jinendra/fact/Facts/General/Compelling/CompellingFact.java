package com.jinendra.fact.Facts.General.Compelling;

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


public class CompellingFact extends AppCompatActivity {


    ImageView vPreviousMenuImageViewCompellingFactActivity, vShareImageViewCompellingFactActivity;
    TextView vFactsTextViewCompellingFactActivity;
    ImageView vPreviousImageViewCompellingFactActivity;
    ImageView vPlayImageViewCompellingFactActivity, vPauseImageViewCompellingFactActivity;
    ImageView vNextImageViewCompellingFactActivity;
    private TextToSpeech vTextToSpeech;

    String[] compellingFactsCompellingFactActivity = new String[999];
    int compellingFactsCounter,j,k;

    private InterstitialAd vInterstitialAdCompellingFactActivity;
    AdView vBannerAdViewCompellingFactActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compelling_fact);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        vBannerAdViewCompellingFactActivity = findViewById(R.id.bannerAdViewCompellingFactActivity);
        AdRequest vBannerAdRequestCompellingFactActivity = new AdRequest.Builder().build();
        vBannerAdViewCompellingFactActivity.loadAd(vBannerAdRequestCompellingFactActivity);

        vInterstitialAdCompellingFactActivity = new InterstitialAd(this);
        vInterstitialAdCompellingFactActivity.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        vInterstitialAdCompellingFactActivity.loadAd(new AdRequest.Builder().build());

        vInterstitialAdCompellingFactActivity.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                vInterstitialAdCompellingFactActivity.loadAd(new AdRequest.Builder().build());
            }

        });

        vPreviousMenuImageViewCompellingFactActivity = findViewById(R.id.previousMenuImageViewCompellingFactActivity);
        vPreviousMenuImageViewCompellingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vPreviousMenuIntentCompellingFactActivity = new Intent(CompellingFact.this, CompellingFactList.class);
                MusicComponent.shouldPlayCompellingFactListActivity = false;
                MusicComponent.shouldPlayCompellingFactActivity = true;
                startActivity(vPreviousMenuIntentCompellingFactActivity);

                if(vTextToSpeech.isSpeaking()){
                    vTextToSpeech.stop();
                }
            }
        });

        vShareImageViewCompellingFactActivity = findViewById(R.id.shareImageViewCompellingFactActivity);
        vShareImageViewCompellingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "I found a amazing fact " + vFactsTextViewCompellingFactActivity.getText().toString();
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, s);
                startActivity(Intent.createChooser(shareIntent, "Share text via"));

                if (vInterstitialAdCompellingFactActivity.isLoaded()) {
                    vInterstitialAdCompellingFactActivity.show();
                } else {
                    Log.i("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        });

        vPreviousImageViewCompellingFactActivity = findViewById(R.id.previousImageViewCompellingFactActivity);
        vNextImageViewCompellingFactActivity = findViewById(R.id.nextImageViewCompellingFactActivity);
        vFactsTextViewCompellingFactActivity = findViewById(R.id.factsTextViewCompellingFactActivity);
        vPlayImageViewCompellingFactActivity = findViewById(R.id.playImageViewCompellingFactActivity);
        vPauseImageViewCompellingFactActivity = findViewById(R.id.pauseImageViewCompellingFactActivity);

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
                                    vPauseImageViewCompellingFactActivity.setVisibility(View.INVISIBLE);
                                    vPlayImageViewCompellingFactActivity.setVisibility(View.VISIBLE);
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
            vTextToSpeech.speak(vFactsTextViewCompellingFactActivity.getText().toString(),TextToSpeech.QUEUE_FLUSH,null,TextToSpeech.ACTION_TTS_QUEUE_PROCESSING_COMPLETED);
        }

        vPlayImageViewCompellingFactActivity.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                vPauseImageViewCompellingFactActivity.setVisibility(View.VISIBLE);
                vPlayImageViewCompellingFactActivity.setVisibility(View.INVISIBLE);
                mediaPlayer.pause();
                String s = vFactsTextViewCompellingFactActivity.getText().toString();
                vTextToSpeech.speak(s, TextToSpeech.QUEUE_ADD, null,"si");
            }
        });


        vPauseImageViewCompellingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vPlayImageViewCompellingFactActivity.setVisibility(View.VISIBLE);
                vPauseImageViewCompellingFactActivity.setVisibility(View.INVISIBLE);
                vTextToSpeech.stop();

                mediaPlayer.start();
            }
        });

        for (k = 0; k <= 998; k++){
            compellingFactsCompellingFactActivity[k] = "factCompellingFactActivity" + k;
        }

        Intent fetchDataIntentCompellingFactActivity = getIntent();

        for(j = 0; j <= 998; j++){
            compellingFactsCompellingFactActivity[j] = fetchDataIntentCompellingFactActivity.getStringExtra(CompellingFactList.extraTextCompellingFactListActivity[j]);
        }

        compellingFactsCounter = fetchDataIntentCompellingFactActivity.getIntExtra(CompellingFactList.EXTRA_NUMBER_COMPELLING_FACT_LIST_ACTIVITY, 0);

        vFactsTextViewCompellingFactActivity.setText(compellingFactsCompellingFactActivity[compellingFactsCounter]);
        if (compellingFactsCounter == 0) {
            vPreviousImageViewCompellingFactActivity.setImageResource(R.drawable.ic_previous_fade);
        }

        vPreviousImageViewCompellingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (compellingFactsCounter == 0 ){
                    vFactsTextViewCompellingFactActivity.setText(compellingFactsCompellingFactActivity[compellingFactsCounter]);
                }else if (compellingFactsCounter == 1){
                    compellingFactsCounter--;
                    vPreviousImageViewCompellingFactActivity.setImageResource(R.drawable.ic_previous_fade);
                    vFactsTextViewCompellingFactActivity.setText(compellingFactsCompellingFactActivity[compellingFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewCompellingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewCompellingFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                }else if (compellingFactsCounter % 7 == 6){
                    compellingFactsCounter--;
                    vFactsTextViewCompellingFactActivity.setText(compellingFactsCompellingFactActivity[compellingFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewCompellingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewCompellingFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                    if (vInterstitialAdCompellingFactActivity.isLoaded()) {
                        vInterstitialAdCompellingFactActivity.show();
                    } else {
                        Log.i("TAG", "The interstitial wasn't loaded yet.");
                    }
                }else{
                    compellingFactsCounter--;
                    vFactsTextViewCompellingFactActivity.setText(compellingFactsCompellingFactActivity[compellingFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewCompellingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewCompellingFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                }

            }
        });

        vNextImageViewCompellingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (compellingFactsCounter == 0) {
                    compellingFactsCounter++;
                    vPreviousImageViewCompellingFactActivity.setImageResource(R.drawable.ic_previous);
                    vFactsTextViewCompellingFactActivity.setText(compellingFactsCompellingFactActivity[compellingFactsCounter]);
                    if (vTextToSpeech.isSpeaking()) {
                        vTextToSpeech.stop();
                        vPlayImageViewCompellingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewCompellingFactActivity.setVisibility(View.INVISIBLE);
                        mediaPlayer.start();
                    }
                } else if (compellingFactsCounter % 7 == 6) {
                    compellingFactsCounter++;
                    vFactsTextViewCompellingFactActivity.setText(compellingFactsCompellingFactActivity[compellingFactsCounter]);
                    if (vTextToSpeech.isSpeaking()) {
                        vTextToSpeech.stop();
                        vPlayImageViewCompellingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewCompellingFactActivity.setVisibility(View.INVISIBLE);
                        mediaPlayer.start();
                    }
                    if (vInterstitialAdCompellingFactActivity.isLoaded()) {
                        vInterstitialAdCompellingFactActivity.show();
                    } else {
                        Log.i("TAG", "The interstitial wasn't loaded yet.");
                    }
                } else if (compellingFactsCounter % 7 != 6 && compellingFactsCounter <= 997) {
                    compellingFactsCounter++;
                    vFactsTextViewCompellingFactActivity.setText(compellingFactsCompellingFactActivity[compellingFactsCounter]);
                    if (vTextToSpeech.isSpeaking()) {
                        vTextToSpeech.stop();
                        vPlayImageViewCompellingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewCompellingFactActivity.setVisibility(View.INVISIBLE);
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

            MusicComponent.BackgroundMusic(CompellingFact.this, R.raw.bg_music);

        }

        if(mediaPlayer.isPlaying()){

            mediaPlayer.pause();

        }

        if(MusicComponent.shouldPlayCompellingFactActivity){
            MusicComponent.shouldPlayCompellingFactActivity = false;
        }

        mediaPlayer.start();
        MusicComponent.onCompletion(mediaPlayer);

        SharedPreferences sharedPreferencesCompellingFactActivity = getSharedPreferences("Background", Context.MODE_PRIVATE);
        int vSelectedColorCompellingFactActivity = sharedPreferencesCompellingFactActivity.getInt("integerSelectedColor", 0);
        ConstraintLayout vConstraintLayoutCompellingFactActivity = findViewById(R.id.constraintLayoutCompellingFactActivity);
        if (sharedPreferencesCompellingFactActivity.getInt("integerSelectedColor", Color.WHITE) == vSelectedColorCompellingFactActivity)
        {vConstraintLayoutCompellingFactActivity.setBackgroundColor(vSelectedColorCompellingFactActivity);}
        else
        {vConstraintLayoutCompellingFactActivity.setBackgroundColor(Color.parseColor("#FDD935"));}

    }

    @Override
    protected void onPause() {
        super.onPause();

        if(vTextToSpeech.isSpeaking()){
            vTextToSpeech.stop();
            vPlayImageViewCompellingFactActivity.setVisibility(View.VISIBLE);
            vPauseImageViewCompellingFactActivity.setVisibility(View.INVISIBLE);

        }

        if (!MusicComponent.shouldPlayCompellingFactActivity){

            mediaPlayer.pause();
            MusicComponent.shouldPlayCompellingFactListActivity = false;
            MusicComponent.shouldPlayCompellingFactActivity = true;

        }
    }

}