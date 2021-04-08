package com.jinendra.fact.Facts.General.Enthralling;

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


public class EnthrallingFact extends AppCompatActivity {


    ImageView vPreviousMenuImageViewEnthrallingFactActivity, vShareImageViewEnthrallingFactActivity;
    TextView vFactsTextViewEnthrallingFactActivity;
    ImageView vPreviousImageViewEnthrallingFactActivity;
    ImageView vPlayImageViewEnthrallingFactActivity, vPauseImageViewEnthrallingFactActivity;
    ImageView vNextImageViewEnthrallingFactActivity;
    private TextToSpeech vTextToSpeech;

    String[] EnthrallingFactsEnthrallingFactActivity = new String[999];
    int EnthrallingFactsCounter,j,k;

    private InterstitialAd vInterstitialAdEnthrallingFactActivity;
    AdView vBannerAdViewEnthrallingFactActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enthralling_fact);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        vBannerAdViewEnthrallingFactActivity = findViewById(R.id.bannerAdViewEnthrallingFactActivity);
        AdRequest vBannerAdRequestEnthrallingFactActivity = new AdRequest.Builder().build();
        vBannerAdViewEnthrallingFactActivity.loadAd(vBannerAdRequestEnthrallingFactActivity);

        vInterstitialAdEnthrallingFactActivity = new InterstitialAd(this);
        vInterstitialAdEnthrallingFactActivity.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        vInterstitialAdEnthrallingFactActivity.loadAd(new AdRequest.Builder().build());

        vInterstitialAdEnthrallingFactActivity.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                vInterstitialAdEnthrallingFactActivity.loadAd(new AdRequest.Builder().build());
            }

        });

        vPreviousMenuImageViewEnthrallingFactActivity = findViewById(R.id.previousMenuImageViewEnthrallingFactActivity);
        vPreviousMenuImageViewEnthrallingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vPreviousMenuIntentEnthrallingFactActivity = new Intent(EnthrallingFact.this, EnthrallingFactList.class);
                MusicComponent.shouldPlayEnthrallingFactListActivity = false;
                MusicComponent.shouldPlayEnthrallingFactActivity = true;
                startActivity(vPreviousMenuIntentEnthrallingFactActivity);

                if(vTextToSpeech.isSpeaking()){
                    vTextToSpeech.stop();
                }
            }
        });

        vShareImageViewEnthrallingFactActivity = findViewById(R.id.shareImageViewEnthrallingFactActivity);
        vShareImageViewEnthrallingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "I found a amazing fact " + vFactsTextViewEnthrallingFactActivity.getText().toString();
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, s);
                startActivity(Intent.createChooser(shareIntent, "Share text via"));

                if (vInterstitialAdEnthrallingFactActivity.isLoaded()) {
                    vInterstitialAdEnthrallingFactActivity.show();
                } else {
                    Log.i("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        });

        vPreviousImageViewEnthrallingFactActivity = findViewById(R.id.previousImageViewEnthrallingFactActivity);
        vNextImageViewEnthrallingFactActivity = findViewById(R.id.nextImageViewEnthrallingFactActivity);
        vFactsTextViewEnthrallingFactActivity = findViewById(R.id.factsTextViewEnthrallingFactActivity);
        vPlayImageViewEnthrallingFactActivity = findViewById(R.id.playImageViewEnthrallingFactActivity);
        vPauseImageViewEnthrallingFactActivity = findViewById(R.id.pauseImageViewEnthrallingFactActivity);

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
                                    vPauseImageViewEnthrallingFactActivity.setVisibility(View.INVISIBLE);
                                    vPlayImageViewEnthrallingFactActivity.setVisibility(View.VISIBLE);
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
            vTextToSpeech.speak(vFactsTextViewEnthrallingFactActivity.getText().toString(),TextToSpeech.QUEUE_FLUSH,null,TextToSpeech.ACTION_TTS_QUEUE_PROCESSING_COMPLETED);
        }

        vPlayImageViewEnthrallingFactActivity.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                vPauseImageViewEnthrallingFactActivity.setVisibility(View.VISIBLE);
                vPlayImageViewEnthrallingFactActivity.setVisibility(View.INVISIBLE);
                mediaPlayer.pause();
                String s = vFactsTextViewEnthrallingFactActivity.getText().toString();
                vTextToSpeech.speak(s, TextToSpeech.QUEUE_ADD, null,"si");
            }
        });


        vPauseImageViewEnthrallingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vPlayImageViewEnthrallingFactActivity.setVisibility(View.VISIBLE);
                vPauseImageViewEnthrallingFactActivity.setVisibility(View.INVISIBLE);
                vTextToSpeech.stop();

                mediaPlayer.start();
            }
        });

        for (k = 0; k <= 998; k++){
            EnthrallingFactsEnthrallingFactActivity[k] = "factEnthrallingFactActivity" + k;
        }

        Intent fetchDataIntentEnthrallingFactActivity = getIntent();

        for(j = 0; j <= 998; j++){
            EnthrallingFactsEnthrallingFactActivity[j] = fetchDataIntentEnthrallingFactActivity.getStringExtra(EnthrallingFactList.extraTextEnthrallingFactListActivity[j]);
        }

        EnthrallingFactsCounter = fetchDataIntentEnthrallingFactActivity.getIntExtra(EnthrallingFactList.EXTRA_NUMBER_ENTHRALLING_FACT_LIST_ACTIVITY, 0);

        vFactsTextViewEnthrallingFactActivity.setText(EnthrallingFactsEnthrallingFactActivity[EnthrallingFactsCounter]);
        if (EnthrallingFactsCounter == 0) {
            vPreviousImageViewEnthrallingFactActivity.setImageResource(R.drawable.ic_previous_fade);
        }

        vPreviousImageViewEnthrallingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (EnthrallingFactsCounter == 0 ){
                    vFactsTextViewEnthrallingFactActivity.setText(EnthrallingFactsEnthrallingFactActivity[EnthrallingFactsCounter]);
                }else if (EnthrallingFactsCounter == 1){
                    EnthrallingFactsCounter--;
                    vPreviousImageViewEnthrallingFactActivity.setImageResource(R.drawable.ic_previous_fade);
                    vFactsTextViewEnthrallingFactActivity.setText(EnthrallingFactsEnthrallingFactActivity[EnthrallingFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewEnthrallingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewEnthrallingFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                }else if (EnthrallingFactsCounter % 7 == 6){
                    EnthrallingFactsCounter--;
                    vFactsTextViewEnthrallingFactActivity.setText(EnthrallingFactsEnthrallingFactActivity[EnthrallingFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewEnthrallingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewEnthrallingFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                    if (vInterstitialAdEnthrallingFactActivity.isLoaded()) {
                        vInterstitialAdEnthrallingFactActivity.show();
                    } else {
                        Log.i("TAG", "The interstitial wasn't loaded yet.");
                    }
                }else{
                    EnthrallingFactsCounter--;
                    vFactsTextViewEnthrallingFactActivity.setText(EnthrallingFactsEnthrallingFactActivity[EnthrallingFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewEnthrallingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewEnthrallingFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                }

            }
        });

        vNextImageViewEnthrallingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (EnthrallingFactsCounter == 0) {
                    EnthrallingFactsCounter++;
                    vPreviousImageViewEnthrallingFactActivity.setImageResource(R.drawable.ic_previous);
                    vFactsTextViewEnthrallingFactActivity.setText(EnthrallingFactsEnthrallingFactActivity[EnthrallingFactsCounter]);
                    if (vTextToSpeech.isSpeaking()) {
                        vTextToSpeech.stop();
                        vPlayImageViewEnthrallingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewEnthrallingFactActivity.setVisibility(View.INVISIBLE);
                        mediaPlayer.start();
                    }
                } else if (EnthrallingFactsCounter % 7 == 6) {
                    EnthrallingFactsCounter++;
                    vFactsTextViewEnthrallingFactActivity.setText(EnthrallingFactsEnthrallingFactActivity[EnthrallingFactsCounter]);
                    if (vTextToSpeech.isSpeaking()) {
                        vTextToSpeech.stop();
                        vPlayImageViewEnthrallingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewEnthrallingFactActivity.setVisibility(View.INVISIBLE);
                        mediaPlayer.start();
                    }
                    if (vInterstitialAdEnthrallingFactActivity.isLoaded()) {
                        vInterstitialAdEnthrallingFactActivity.show();
                    } else {
                        Log.i("TAG", "The interstitial wasn't loaded yet.");
                    }
                } else if (EnthrallingFactsCounter % 7 != 6 && EnthrallingFactsCounter <= 997) {
                    EnthrallingFactsCounter++;
                    vFactsTextViewEnthrallingFactActivity.setText(EnthrallingFactsEnthrallingFactActivity[EnthrallingFactsCounter]);
                    if (vTextToSpeech.isSpeaking()) {
                        vTextToSpeech.stop();
                        vPlayImageViewEnthrallingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewEnthrallingFactActivity.setVisibility(View.INVISIBLE);
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

            MusicComponent.BackgroundMusic(EnthrallingFact.this, R.raw.bg_music);

        }

        if(mediaPlayer.isPlaying()){

            mediaPlayer.pause();

        }

        if(MusicComponent.shouldPlayEnthrallingFactActivity){
            MusicComponent.shouldPlayEnthrallingFactActivity = false;
        }

        mediaPlayer.start();
        MusicComponent.onCompletion(mediaPlayer);

        SharedPreferences sharedPreferencesEnthrallingFactActivity = getSharedPreferences("Background", Context.MODE_PRIVATE);
        int vSelectedColorEnthrallingFactActivity = sharedPreferencesEnthrallingFactActivity.getInt("integerSelectedColor", 0);
        ConstraintLayout vConstraintLayoutEnthrallingFactActivity = findViewById(R.id.constraintLayoutEnthrallingFactActivity);
        if (sharedPreferencesEnthrallingFactActivity.getInt("integerSelectedColor", Color.WHITE) == vSelectedColorEnthrallingFactActivity)
        {vConstraintLayoutEnthrallingFactActivity.setBackgroundColor(vSelectedColorEnthrallingFactActivity);}
        else
        {vConstraintLayoutEnthrallingFactActivity.setBackgroundColor(Color.parseColor("#FDD935"));}

    }

    @Override
    protected void onPause() {
        super.onPause();

        if(vTextToSpeech.isSpeaking()){
            vTextToSpeech.stop();
            vPlayImageViewEnthrallingFactActivity.setVisibility(View.VISIBLE);
            vPauseImageViewEnthrallingFactActivity.setVisibility(View.INVISIBLE);

        }

        if (!MusicComponent.shouldPlayEnthrallingFactActivity){

            mediaPlayer.pause();
            MusicComponent.shouldPlayEnthrallingFactListActivity = false;
            MusicComponent.shouldPlayEnthrallingFactActivity = true;

        }
    }

}