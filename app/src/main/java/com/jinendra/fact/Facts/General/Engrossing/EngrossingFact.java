package com.jinendra.fact.Facts.General.Engrossing;

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


public class EngrossingFact extends AppCompatActivity {


    ImageView vPreviousMenuImageViewEngrossingFactActivity, vShareImageViewEngrossingFactActivity;
    TextView vFactsTextViewEngrossingFactActivity;
    ImageView vPreviousImageViewEngrossingFactActivity;
    ImageView vPlayImageViewEngrossingFactActivity, vPauseImageViewEngrossingFactActivity;
    ImageView vNextImageViewEngrossingFactActivity;
    private TextToSpeech vTextToSpeech;

    String[] EngrossingFactsEngrossingFactActivity = new String[999];
    int EngrossingFactsCounter,j,k;

    private InterstitialAd vInterstitialAdEngrossingFactActivity;
    AdView vBannerAdViewEngrossingFactActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engrossing_fact);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        vBannerAdViewEngrossingFactActivity = findViewById(R.id.bannerAdViewEngrossingFactActivity);
        AdRequest vBannerAdRequestEngrossingFactActivity = new AdRequest.Builder().build();
        vBannerAdViewEngrossingFactActivity.loadAd(vBannerAdRequestEngrossingFactActivity);

        vInterstitialAdEngrossingFactActivity = new InterstitialAd(this);
        vInterstitialAdEngrossingFactActivity.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        vInterstitialAdEngrossingFactActivity.loadAd(new AdRequest.Builder().build());

        vInterstitialAdEngrossingFactActivity.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                vInterstitialAdEngrossingFactActivity.loadAd(new AdRequest.Builder().build());
            }

        });

        vPreviousMenuImageViewEngrossingFactActivity = findViewById(R.id.previousMenuImageViewEngrossingFactActivity);
        vPreviousMenuImageViewEngrossingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vPreviousMenuIntentEngrossingFactActivity = new Intent(EngrossingFact.this, EngrossingFactList.class);
                MusicComponent.shouldPlayEngrossingFactListActivity = false;
                MusicComponent.shouldPlayEngrossingFactActivity = true;
                startActivity(vPreviousMenuIntentEngrossingFactActivity);

                if(vTextToSpeech.isSpeaking()){
                    vTextToSpeech.stop();
                }
            }
        });

        vShareImageViewEngrossingFactActivity = findViewById(R.id.shareImageViewEngrossingFactActivity);
        vShareImageViewEngrossingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "I found a amazing fact " + vFactsTextViewEngrossingFactActivity.getText().toString();
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, s);
                startActivity(Intent.createChooser(shareIntent, "Share text via"));

                if (vInterstitialAdEngrossingFactActivity.isLoaded()) {
                    vInterstitialAdEngrossingFactActivity.show();
                } else {
                    Log.i("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        });

        vPreviousImageViewEngrossingFactActivity = findViewById(R.id.previousImageViewEngrossingFactActivity);
        vNextImageViewEngrossingFactActivity = findViewById(R.id.nextImageViewEngrossingFactActivity);
        vFactsTextViewEngrossingFactActivity = findViewById(R.id.factsTextViewEngrossingFactActivity);
        vPlayImageViewEngrossingFactActivity = findViewById(R.id.playImageViewEngrossingFactActivity);
        vPauseImageViewEngrossingFactActivity = findViewById(R.id.pauseImageViewEngrossingFactActivity);

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
                                    vPauseImageViewEngrossingFactActivity.setVisibility(View.INVISIBLE);
                                    vPlayImageViewEngrossingFactActivity.setVisibility(View.VISIBLE);
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
            vTextToSpeech.speak(vFactsTextViewEngrossingFactActivity.getText().toString(),TextToSpeech.QUEUE_FLUSH,null,TextToSpeech.ACTION_TTS_QUEUE_PROCESSING_COMPLETED);
        }

        vPlayImageViewEngrossingFactActivity.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                vPauseImageViewEngrossingFactActivity.setVisibility(View.VISIBLE);
                vPlayImageViewEngrossingFactActivity.setVisibility(View.INVISIBLE);
                mediaPlayer.pause();
                String s = vFactsTextViewEngrossingFactActivity.getText().toString();
                vTextToSpeech.speak(s, TextToSpeech.QUEUE_ADD, null,"si");
            }
        });


        vPauseImageViewEngrossingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vPlayImageViewEngrossingFactActivity.setVisibility(View.VISIBLE);
                vPauseImageViewEngrossingFactActivity.setVisibility(View.INVISIBLE);
                vTextToSpeech.stop();

                mediaPlayer.start();
            }
        });

        for (k = 0; k <= 998; k++){
            EngrossingFactsEngrossingFactActivity[k] = "factEngrossingFactActivity" + k;
        }

        Intent fetchDataIntentEngrossingFactActivity = getIntent();

        for(j = 0; j <= 998; j++){
            EngrossingFactsEngrossingFactActivity[j] = fetchDataIntentEngrossingFactActivity.getStringExtra(EngrossingFactList.extraTextEngrossingFactListActivity[j]);
        }

        EngrossingFactsCounter = fetchDataIntentEngrossingFactActivity.getIntExtra(EngrossingFactList.EXTRA_NUMBER_ENGROSSING_FACT_LIST_ACTIVITY, 0);

        vFactsTextViewEngrossingFactActivity.setText(EngrossingFactsEngrossingFactActivity[EngrossingFactsCounter]);
        if (EngrossingFactsCounter == 0) {
            vPreviousImageViewEngrossingFactActivity.setImageResource(R.drawable.ic_previous_fade);
        }

        vPreviousImageViewEngrossingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (EngrossingFactsCounter == 0 ){
                    vFactsTextViewEngrossingFactActivity.setText(EngrossingFactsEngrossingFactActivity[EngrossingFactsCounter]);
                }else if (EngrossingFactsCounter == 1){
                    EngrossingFactsCounter--;
                    vPreviousImageViewEngrossingFactActivity.setImageResource(R.drawable.ic_previous_fade);
                    vFactsTextViewEngrossingFactActivity.setText(EngrossingFactsEngrossingFactActivity[EngrossingFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewEngrossingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewEngrossingFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                }else if (EngrossingFactsCounter % 7 == 6){
                    EngrossingFactsCounter--;
                    vFactsTextViewEngrossingFactActivity.setText(EngrossingFactsEngrossingFactActivity[EngrossingFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewEngrossingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewEngrossingFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                    if (vInterstitialAdEngrossingFactActivity.isLoaded()) {
                        vInterstitialAdEngrossingFactActivity.show();
                    } else {
                        Log.i("TAG", "The interstitial wasn't loaded yet.");
                    }
                }else{
                    EngrossingFactsCounter--;
                    vFactsTextViewEngrossingFactActivity.setText(EngrossingFactsEngrossingFactActivity[EngrossingFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewEngrossingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewEngrossingFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                }

            }
        });

        vNextImageViewEngrossingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (EngrossingFactsCounter == 0) {
                    EngrossingFactsCounter++;
                    vPreviousImageViewEngrossingFactActivity.setImageResource(R.drawable.ic_previous);
                    vFactsTextViewEngrossingFactActivity.setText(EngrossingFactsEngrossingFactActivity[EngrossingFactsCounter]);
                    if (vTextToSpeech.isSpeaking()) {
                        vTextToSpeech.stop();
                        vPlayImageViewEngrossingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewEngrossingFactActivity.setVisibility(View.INVISIBLE);
                        mediaPlayer.start();
                    }
                } else if (EngrossingFactsCounter % 7 == 6) {
                    EngrossingFactsCounter++;
                    vFactsTextViewEngrossingFactActivity.setText(EngrossingFactsEngrossingFactActivity[EngrossingFactsCounter]);
                    if (vTextToSpeech.isSpeaking()) {
                        vTextToSpeech.stop();
                        vPlayImageViewEngrossingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewEngrossingFactActivity.setVisibility(View.INVISIBLE);
                        mediaPlayer.start();
                    }
                    if (vInterstitialAdEngrossingFactActivity.isLoaded()) {
                        vInterstitialAdEngrossingFactActivity.show();
                    } else {
                        Log.i("TAG", "The interstitial wasn't loaded yet.");
                    }
                } else if (EngrossingFactsCounter % 7 != 6 && EngrossingFactsCounter <= 997) {
                    EngrossingFactsCounter++;
                    vFactsTextViewEngrossingFactActivity.setText(EngrossingFactsEngrossingFactActivity[EngrossingFactsCounter]);
                    if (vTextToSpeech.isSpeaking()) {
                        vTextToSpeech.stop();
                        vPlayImageViewEngrossingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewEngrossingFactActivity.setVisibility(View.INVISIBLE);
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

            MusicComponent.BackgroundMusic(EngrossingFact.this, R.raw.bg_music);

        }

        if(mediaPlayer.isPlaying()){

            mediaPlayer.pause();

        }

        if(MusicComponent.shouldPlayEngrossingFactActivity){
            MusicComponent.shouldPlayEngrossingFactActivity = false;
        }

        mediaPlayer.start();
        MusicComponent.onCompletion(mediaPlayer);

        SharedPreferences sharedPreferencesEngrossingFactActivity = getSharedPreferences("Background", Context.MODE_PRIVATE);
        int vSelectedColorEngrossingFactActivity = sharedPreferencesEngrossingFactActivity.getInt("integerSelectedColor", 0);
        ConstraintLayout vConstraintLayoutEngrossingFactActivity = findViewById(R.id.constraintLayoutEngrossingFactActivity);
        if (sharedPreferencesEngrossingFactActivity.getInt("integerSelectedColor", Color.WHITE) == vSelectedColorEngrossingFactActivity)
        {vConstraintLayoutEngrossingFactActivity.setBackgroundColor(vSelectedColorEngrossingFactActivity);}
        else
        {vConstraintLayoutEngrossingFactActivity.setBackgroundColor(Color.parseColor("#FDD935"));}

    }

    @Override
    protected void onPause() {
        super.onPause();

        if(vTextToSpeech.isSpeaking()){
            vTextToSpeech.stop();
            vPlayImageViewEngrossingFactActivity.setVisibility(View.VISIBLE);
            vPauseImageViewEngrossingFactActivity.setVisibility(View.INVISIBLE);

        }

        if (!MusicComponent.shouldPlayEngrossingFactActivity){

            mediaPlayer.pause();
            MusicComponent.shouldPlayEngrossingFactListActivity = false;
            MusicComponent.shouldPlayEngrossingFactActivity = true;

        }
    }

}