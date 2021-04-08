package com.jinendra.fact.Facts.General.Fascinating;

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


public class FascinatingFact extends AppCompatActivity {


    ImageView vPreviousMenuImageViewFascinatingFactActivity, vShareImageViewFascinatingFactActivity;
    TextView vFactsTextViewFascinatingFactActivity;
    ImageView vPreviousImageViewFascinatingFactActivity;
    ImageView vPlayImageViewFascinatingFactActivity, vPauseImageViewFascinatingFactActivity;
    ImageView vNextImageViewFascinatingFactActivity;
    private TextToSpeech vTextToSpeech;

    String[] FascinatingFactsFascinatingFactActivity = new String[999];
    int FascinatingFactsCounter,j,k;

    private InterstitialAd vInterstitialAdFascinatingFactActivity;
    AdView vBannerAdViewFascinatingFactActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fascinating_fact);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        vBannerAdViewFascinatingFactActivity = findViewById(R.id.bannerAdViewFascinatingFactActivity);
        AdRequest vBannerAdRequestFascinatingFactActivity = new AdRequest.Builder().build();
        vBannerAdViewFascinatingFactActivity.loadAd(vBannerAdRequestFascinatingFactActivity);

        vInterstitialAdFascinatingFactActivity = new InterstitialAd(this);
        vInterstitialAdFascinatingFactActivity.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        vInterstitialAdFascinatingFactActivity.loadAd(new AdRequest.Builder().build());

        vInterstitialAdFascinatingFactActivity.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                vInterstitialAdFascinatingFactActivity.loadAd(new AdRequest.Builder().build());
            }

        });

        vPreviousMenuImageViewFascinatingFactActivity = findViewById(R.id.previousMenuImageViewFascinatingFactActivity);
        vPreviousMenuImageViewFascinatingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vPreviousMenuIntentFascinatingFactActivity = new Intent(FascinatingFact.this, FascinatingFactList.class);
                MusicComponent.shouldPlayFascinatingFactListActivity = false;
                MusicComponent.shouldPlayFascinatingFactActivity = true;
                startActivity(vPreviousMenuIntentFascinatingFactActivity);

                if(vTextToSpeech.isSpeaking()){
                    vTextToSpeech.stop();
                }
            }
        });

        vShareImageViewFascinatingFactActivity = findViewById(R.id.shareImageViewFascinatingFactActivity);
        vShareImageViewFascinatingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "I found a amazing fact " + vFactsTextViewFascinatingFactActivity.getText().toString();
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, s);
                startActivity(Intent.createChooser(shareIntent, "Share text via"));

                if (vInterstitialAdFascinatingFactActivity.isLoaded()) {
                    vInterstitialAdFascinatingFactActivity.show();
                } else {
                    Log.i("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        });

        vPreviousImageViewFascinatingFactActivity = findViewById(R.id.previousImageViewFascinatingFactActivity);
        vNextImageViewFascinatingFactActivity = findViewById(R.id.nextImageViewFascinatingFactActivity);
        vFactsTextViewFascinatingFactActivity = findViewById(R.id.factsTextViewFascinatingFactActivity);
        vPlayImageViewFascinatingFactActivity = findViewById(R.id.playImageViewFascinatingFactActivity);
        vPauseImageViewFascinatingFactActivity = findViewById(R.id.pauseImageViewFascinatingFactActivity);

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
                                    vPauseImageViewFascinatingFactActivity.setVisibility(View.INVISIBLE);
                                    vPlayImageViewFascinatingFactActivity.setVisibility(View.VISIBLE);
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
            vTextToSpeech.speak(vFactsTextViewFascinatingFactActivity.getText().toString(),TextToSpeech.QUEUE_FLUSH,null,TextToSpeech.ACTION_TTS_QUEUE_PROCESSING_COMPLETED);
        }

        vPlayImageViewFascinatingFactActivity.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                vPauseImageViewFascinatingFactActivity.setVisibility(View.VISIBLE);
                vPlayImageViewFascinatingFactActivity.setVisibility(View.INVISIBLE);
                mediaPlayer.pause();
                String s = vFactsTextViewFascinatingFactActivity.getText().toString();
                vTextToSpeech.speak(s, TextToSpeech.QUEUE_ADD, null,"si");
            }
        });


        vPauseImageViewFascinatingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vPlayImageViewFascinatingFactActivity.setVisibility(View.VISIBLE);
                vPauseImageViewFascinatingFactActivity.setVisibility(View.INVISIBLE);
                vTextToSpeech.stop();

                mediaPlayer.start();
            }
        });

        for (k = 0; k <= 998; k++){
            FascinatingFactsFascinatingFactActivity[k] = "factFascinatingFactActivity" + k;
        }

        Intent fetchDataIntentFascinatingFactActivity = getIntent();

        for(j = 0; j <= 998; j++){
            FascinatingFactsFascinatingFactActivity[j] = fetchDataIntentFascinatingFactActivity.getStringExtra(FascinatingFactList.extraTextFascinatingFactListActivity[j]);
        }

        FascinatingFactsCounter = fetchDataIntentFascinatingFactActivity.getIntExtra(FascinatingFactList.EXTRA_NUMBER_FASCINATING_FACT_LIST_ACTIVITY, 0);

        vFactsTextViewFascinatingFactActivity.setText(FascinatingFactsFascinatingFactActivity[FascinatingFactsCounter]);
        if (FascinatingFactsCounter == 0) {
            vPreviousImageViewFascinatingFactActivity.setImageResource(R.drawable.ic_previous_fade);
        }

        vPreviousImageViewFascinatingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (FascinatingFactsCounter == 0 ){
                    vFactsTextViewFascinatingFactActivity.setText(FascinatingFactsFascinatingFactActivity[FascinatingFactsCounter]);
                }else if (FascinatingFactsCounter == 1){
                    FascinatingFactsCounter--;
                    vPreviousImageViewFascinatingFactActivity.setImageResource(R.drawable.ic_previous_fade);
                    vFactsTextViewFascinatingFactActivity.setText(FascinatingFactsFascinatingFactActivity[FascinatingFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewFascinatingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewFascinatingFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                }else if (FascinatingFactsCounter % 7 == 6){
                    FascinatingFactsCounter--;
                    vFactsTextViewFascinatingFactActivity.setText(FascinatingFactsFascinatingFactActivity[FascinatingFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewFascinatingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewFascinatingFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                    if (vInterstitialAdFascinatingFactActivity.isLoaded()) {
                        vInterstitialAdFascinatingFactActivity.show();
                    } else {
                        Log.i("TAG", "The interstitial wasn't loaded yet.");
                    }
                }else{
                    FascinatingFactsCounter--;
                    vFactsTextViewFascinatingFactActivity.setText(FascinatingFactsFascinatingFactActivity[FascinatingFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewFascinatingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewFascinatingFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                }

            }
        });

        vNextImageViewFascinatingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (FascinatingFactsCounter == 0) {
                    FascinatingFactsCounter++;
                    vPreviousImageViewFascinatingFactActivity.setImageResource(R.drawable.ic_previous);
                    vFactsTextViewFascinatingFactActivity.setText(FascinatingFactsFascinatingFactActivity[FascinatingFactsCounter]);
                    if (vTextToSpeech.isSpeaking()) {
                        vTextToSpeech.stop();
                        vPlayImageViewFascinatingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewFascinatingFactActivity.setVisibility(View.INVISIBLE);
                        mediaPlayer.start();
                    }
                } else if (FascinatingFactsCounter % 7 == 6) {
                    FascinatingFactsCounter++;
                    vFactsTextViewFascinatingFactActivity.setText(FascinatingFactsFascinatingFactActivity[FascinatingFactsCounter]);
                    if (vTextToSpeech.isSpeaking()) {
                        vTextToSpeech.stop();
                        vPlayImageViewFascinatingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewFascinatingFactActivity.setVisibility(View.INVISIBLE);
                        mediaPlayer.start();
                    }
                    if (vInterstitialAdFascinatingFactActivity.isLoaded()) {
                        vInterstitialAdFascinatingFactActivity.show();
                    } else {
                        Log.i("TAG", "The interstitial wasn't loaded yet.");
                    }
                } else if (FascinatingFactsCounter % 7 != 6 && FascinatingFactsCounter <= 997) {
                    FascinatingFactsCounter++;
                    vFactsTextViewFascinatingFactActivity.setText(FascinatingFactsFascinatingFactActivity[FascinatingFactsCounter]);
                    if (vTextToSpeech.isSpeaking()) {
                        vTextToSpeech.stop();
                        vPlayImageViewFascinatingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewFascinatingFactActivity.setVisibility(View.INVISIBLE);
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

            MusicComponent.BackgroundMusic(FascinatingFact.this, R.raw.bg_music);

        }

        if(mediaPlayer.isPlaying()){

            mediaPlayer.pause();

        }

        if(MusicComponent.shouldPlayFascinatingFactActivity){
            MusicComponent.shouldPlayFascinatingFactActivity = false;
        }

        mediaPlayer.start();
        MusicComponent.onCompletion(mediaPlayer);

        SharedPreferences sharedPreferencesFascinatingFactActivity = getSharedPreferences("Background", Context.MODE_PRIVATE);
        int vSelectedColorFascinatingFactActivity = sharedPreferencesFascinatingFactActivity.getInt("integerSelectedColor", 0);
        ConstraintLayout vConstraintLayoutFascinatingFactActivity = findViewById(R.id.constraintLayoutFascinatingFactActivity);
        if (sharedPreferencesFascinatingFactActivity.getInt("integerSelectedColor", Color.WHITE) == vSelectedColorFascinatingFactActivity)
        {vConstraintLayoutFascinatingFactActivity.setBackgroundColor(vSelectedColorFascinatingFactActivity);}
        else
        {vConstraintLayoutFascinatingFactActivity.setBackgroundColor(Color.parseColor("#FDD935"));}

    }

    @Override
    protected void onPause() {
        super.onPause();

        if(vTextToSpeech.isSpeaking()){
            vTextToSpeech.stop();
            vPlayImageViewFascinatingFactActivity.setVisibility(View.VISIBLE);
            vPauseImageViewFascinatingFactActivity.setVisibility(View.INVISIBLE);

        }

        if (!MusicComponent.shouldPlayFascinatingFactActivity){

            mediaPlayer.pause();
            MusicComponent.shouldPlayFascinatingFactListActivity = false;
            MusicComponent.shouldPlayFascinatingFactActivity = true;

        }
    }

}