package com.jinendra.fact.Facts.General.Amusing;

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


public class AmusingFact extends AppCompatActivity {


    ImageView vPreviousMenuImageViewAmusingFactActivity, vShareImageViewAmusingFactActivity;
    TextView vFactsTextViewAmusingFactActivity;
    ImageView vPreviousImageViewAmusingFactActivity;
    ImageView vPlayImageViewAmusingFactActivity, vPauseImageViewAmusingFactActivity;
    ImageView vNextImageViewAmusingFactActivity;
    private TextToSpeech vTextToSpeech;

    String[] appealingFactsAmusingFactActivity = new String[999];
    int appealingFactsCounter,j,k;

    private InterstitialAd vInterstitialAdAmusingFactActivity;
    AdView vBannerAdViewAmusingFactActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amusing_fact);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        vBannerAdViewAmusingFactActivity = findViewById(R.id.bannerAdViewAmusingFactActivity);
        AdRequest vBannerAdRequestAmusingFactActivity = new AdRequest.Builder().build();
        vBannerAdViewAmusingFactActivity.loadAd(vBannerAdRequestAmusingFactActivity);

        vInterstitialAdAmusingFactActivity = new InterstitialAd(this);
        vInterstitialAdAmusingFactActivity.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        vInterstitialAdAmusingFactActivity.loadAd(new AdRequest.Builder().build());

        vInterstitialAdAmusingFactActivity.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                vInterstitialAdAmusingFactActivity.loadAd(new AdRequest.Builder().build());
            }

        });

        vPreviousMenuImageViewAmusingFactActivity = findViewById(R.id.previousMenuImageViewAmusingFactActivity);
        vPreviousMenuImageViewAmusingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vPreviousMenuIntentAmusingFactActivity = new Intent(AmusingFact.this, AmusingFactList.class);
                MusicComponent.shouldPlayAmusingFactListActivity = false;
                MusicComponent.shouldPlayAmusingFactActivity = true;
                startActivity(vPreviousMenuIntentAmusingFactActivity);

                if(vTextToSpeech.isSpeaking()){
                    vTextToSpeech.stop();
                }
            }
        });

        vShareImageViewAmusingFactActivity = findViewById(R.id.shareImageViewAmusingFactActivity);
        vShareImageViewAmusingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "I found a amazing fact " + vFactsTextViewAmusingFactActivity.getText().toString();
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, s);
                startActivity(Intent.createChooser(shareIntent, "Share text via"));

                if (vInterstitialAdAmusingFactActivity.isLoaded()) {
                    vInterstitialAdAmusingFactActivity.show();
                } else {
                    Log.i("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        });

        vPreviousImageViewAmusingFactActivity = findViewById(R.id.previousImageViewAmusingFactActivity);
        vNextImageViewAmusingFactActivity = findViewById(R.id.nextImageViewAmusingFactActivity);
        vFactsTextViewAmusingFactActivity = findViewById(R.id.factsTextViewAmusingFactActivity);
        vPlayImageViewAmusingFactActivity = findViewById(R.id.playImageViewAmusingFactActivity);
        vPauseImageViewAmusingFactActivity = findViewById(R.id.pauseImageViewAmusingFactActivity);

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
                                    vPauseImageViewAmusingFactActivity.setVisibility(View.INVISIBLE);
                                    vPlayImageViewAmusingFactActivity.setVisibility(View.VISIBLE);
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
            vTextToSpeech.speak(vFactsTextViewAmusingFactActivity.getText().toString(),TextToSpeech.QUEUE_FLUSH,null,TextToSpeech.ACTION_TTS_QUEUE_PROCESSING_COMPLETED);
        }

        vPlayImageViewAmusingFactActivity.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                vPauseImageViewAmusingFactActivity.setVisibility(View.VISIBLE);
                vPlayImageViewAmusingFactActivity.setVisibility(View.INVISIBLE);
                mediaPlayer.pause();
                String s = vFactsTextViewAmusingFactActivity.getText().toString();
                vTextToSpeech.speak(s, TextToSpeech.QUEUE_ADD, null,"si");
            }
        });


        vPauseImageViewAmusingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vPlayImageViewAmusingFactActivity.setVisibility(View.VISIBLE);
                vPauseImageViewAmusingFactActivity.setVisibility(View.INVISIBLE);
                vTextToSpeech.stop();

                mediaPlayer.start();
            }
        });

        for (k = 0; k <= 998; k++){
            appealingFactsAmusingFactActivity[k] = "factAmusingFactActivity" + k;
        }

        Intent fetchDataIntentAmusingFactActivity = getIntent();

        for(j = 0; j <= 998; j++){
            appealingFactsAmusingFactActivity[j] = fetchDataIntentAmusingFactActivity.getStringExtra(AmusingFactList.extraTextAmusingFactListActivity[j]);
        }

        appealingFactsCounter = fetchDataIntentAmusingFactActivity.getIntExtra(AmusingFactList.EXTRA_NUMBER_AMUSING_FACT_LIST_ACTIVITY, 0);

        vFactsTextViewAmusingFactActivity.setText(appealingFactsAmusingFactActivity[appealingFactsCounter]);
        if (appealingFactsCounter == 0) {
            vPreviousImageViewAmusingFactActivity.setImageResource(R.drawable.ic_previous_fade);
        }

        vPreviousImageViewAmusingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (appealingFactsCounter == 0 ){
                    vFactsTextViewAmusingFactActivity.setText(appealingFactsAmusingFactActivity[appealingFactsCounter]);
                }else if (appealingFactsCounter == 1){
                    appealingFactsCounter--;
                    vPreviousImageViewAmusingFactActivity.setImageResource(R.drawable.ic_previous_fade);
                    vFactsTextViewAmusingFactActivity.setText(appealingFactsAmusingFactActivity[appealingFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewAmusingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewAmusingFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                }else if (appealingFactsCounter % 7 == 6){
                    appealingFactsCounter--;
                    vFactsTextViewAmusingFactActivity.setText(appealingFactsAmusingFactActivity[appealingFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewAmusingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewAmusingFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                    if (vInterstitialAdAmusingFactActivity.isLoaded()) {
                        vInterstitialAdAmusingFactActivity.show();
                    } else {
                        Log.i("TAG", "The interstitial wasn't loaded yet.");
                    }
                }else{
                    appealingFactsCounter--;
                    vFactsTextViewAmusingFactActivity.setText(appealingFactsAmusingFactActivity[appealingFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewAmusingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewAmusingFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                }

            }
        });

        vNextImageViewAmusingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (appealingFactsCounter == 0) {
                    appealingFactsCounter++;
                    vPreviousImageViewAmusingFactActivity.setImageResource(R.drawable.ic_previous);
                    vFactsTextViewAmusingFactActivity.setText(appealingFactsAmusingFactActivity[appealingFactsCounter]);
                    if (vTextToSpeech.isSpeaking()) {
                        vTextToSpeech.stop();
                        vPlayImageViewAmusingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewAmusingFactActivity.setVisibility(View.INVISIBLE);
                        mediaPlayer.start();
                    }
                } else if (appealingFactsCounter % 7 == 6) {
                    appealingFactsCounter++;
                    vFactsTextViewAmusingFactActivity.setText(appealingFactsAmusingFactActivity[appealingFactsCounter]);
                    if (vTextToSpeech.isSpeaking()) {
                        vTextToSpeech.stop();
                        vPlayImageViewAmusingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewAmusingFactActivity.setVisibility(View.INVISIBLE);
                        mediaPlayer.start();
                    }
                    if (vInterstitialAdAmusingFactActivity.isLoaded()) {
                        vInterstitialAdAmusingFactActivity.show();
                    } else {
                        Log.i("TAG", "The interstitial wasn't loaded yet.");
                    }
                } else if (appealingFactsCounter % 7 != 6 && appealingFactsCounter <= 997) {
                    appealingFactsCounter++;
                    vFactsTextViewAmusingFactActivity.setText(appealingFactsAmusingFactActivity[appealingFactsCounter]);
                    if (vTextToSpeech.isSpeaking()) {
                        vTextToSpeech.stop();
                        vPlayImageViewAmusingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewAmusingFactActivity.setVisibility(View.INVISIBLE);
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

            MusicComponent.BackgroundMusic(AmusingFact.this, R.raw.bg_music);

        }

        if(mediaPlayer.isPlaying()){

            mediaPlayer.pause();

        }

        if(MusicComponent.shouldPlayAmusingFactActivity){
            MusicComponent.shouldPlayAmusingFactActivity = false;
        }

        mediaPlayer.start();
        MusicComponent.onCompletion(mediaPlayer);

        SharedPreferences sharedPreferencesAmusingFactActivity = getSharedPreferences("Background", Context.MODE_PRIVATE);
        int vSelectedColorAmusingFactActivity = sharedPreferencesAmusingFactActivity.getInt("integerSelectedColor", 0);
        ConstraintLayout vConstraintLayoutAmusingFactActivity = findViewById(R.id.constraintLayoutAmusingFactActivity);
        if (sharedPreferencesAmusingFactActivity.getInt("integerSelectedColor", Color.WHITE) == vSelectedColorAmusingFactActivity)
        {vConstraintLayoutAmusingFactActivity.setBackgroundColor(vSelectedColorAmusingFactActivity);}
        else
        {vConstraintLayoutAmusingFactActivity.setBackgroundColor(Color.parseColor("#FDD935"));}

    }

    @Override
    protected void onPause() {
        super.onPause();

        if(vTextToSpeech.isSpeaking()){
            vTextToSpeech.stop();
            vPlayImageViewAmusingFactActivity.setVisibility(View.VISIBLE);
            vPauseImageViewAmusingFactActivity.setVisibility(View.INVISIBLE);

        }

        if (!MusicComponent.shouldPlayAmusingFactActivity){

            mediaPlayer.pause();
            MusicComponent.shouldPlayAmusingFactListActivity = false;
            MusicComponent.shouldPlayAmusingFactActivity = true;

        }
    }

}