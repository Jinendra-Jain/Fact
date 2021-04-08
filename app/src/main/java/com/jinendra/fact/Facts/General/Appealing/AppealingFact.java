package com.jinendra.fact.Facts.General.Appealing;

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


public class AppealingFact extends AppCompatActivity {


    ImageView vPreviousMenuImageViewAppealingFactActivity, vShareImageViewAppealingFactActivity;
    TextView vFactsTextViewAppealingFactActivity;
    ImageView vPreviousImageViewAppealingFactActivity;
    ImageView vPlayImageViewAppealingFactActivity, vPauseImageViewAppealingFactActivity;
    ImageView vNextImageViewAppealingFactActivity;
    private TextToSpeech vTextToSpeech;

    String[] appealingFactsAppealingFactActivity = new String[999];
    int appealingFactsCounter,j,k;

    private InterstitialAd vInterstitialAdAppealingFactActivity;
    AdView vBannerAdViewAppealingFactActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appealing_fact);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        vBannerAdViewAppealingFactActivity = findViewById(R.id.bannerAdViewAppealingFactActivity);
        AdRequest vBannerAdRequestAppealingFactActivity = new AdRequest.Builder().build();
        vBannerAdViewAppealingFactActivity.loadAd(vBannerAdRequestAppealingFactActivity);

        vInterstitialAdAppealingFactActivity = new InterstitialAd(this);
        vInterstitialAdAppealingFactActivity.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        vInterstitialAdAppealingFactActivity.loadAd(new AdRequest.Builder().build());

        vInterstitialAdAppealingFactActivity.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                vInterstitialAdAppealingFactActivity.loadAd(new AdRequest.Builder().build());
            }

        });

        vPreviousMenuImageViewAppealingFactActivity = findViewById(R.id.previousMenuImageViewAppealingFactActivity);
        vPreviousMenuImageViewAppealingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vPreviousMenuIntentAppealingFactActivity = new Intent(AppealingFact.this, AppealingFactList.class);
                MusicComponent.shouldPlayAppealingFactListActivity = false;
                MusicComponent.shouldPlayAppealingFactActivity = true;
                startActivity(vPreviousMenuIntentAppealingFactActivity);

                if(vTextToSpeech.isSpeaking()){
                    vTextToSpeech.stop();
                }
            }
        });

        vShareImageViewAppealingFactActivity = findViewById(R.id.shareImageViewAppealingFactActivity);
        vShareImageViewAppealingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "I found a amazing fact " + vFactsTextViewAppealingFactActivity.getText().toString();
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, s);
                startActivity(Intent.createChooser(shareIntent, "Share text via"));

                if (vInterstitialAdAppealingFactActivity.isLoaded()) {
                    vInterstitialAdAppealingFactActivity.show();
                } else {
                    Log.i("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        });

        vPreviousImageViewAppealingFactActivity = findViewById(R.id.previousImageViewAppealingFactActivity);
        vNextImageViewAppealingFactActivity = findViewById(R.id.nextImageViewAppealingFactActivity);
        vFactsTextViewAppealingFactActivity = findViewById(R.id.factsTextViewAppealingFactActivity);
        vPlayImageViewAppealingFactActivity = findViewById(R.id.playImageViewAppealingFactActivity);
        vPauseImageViewAppealingFactActivity = findViewById(R.id.pauseImageViewAppealingFactActivity);

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
                                    vPauseImageViewAppealingFactActivity.setVisibility(View.INVISIBLE);
                                    vPlayImageViewAppealingFactActivity.setVisibility(View.VISIBLE);
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
            vTextToSpeech.speak(vFactsTextViewAppealingFactActivity.getText().toString(),TextToSpeech.QUEUE_FLUSH,null,TextToSpeech.ACTION_TTS_QUEUE_PROCESSING_COMPLETED);
        }

        vPlayImageViewAppealingFactActivity.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                vPauseImageViewAppealingFactActivity.setVisibility(View.VISIBLE);
                vPlayImageViewAppealingFactActivity.setVisibility(View.INVISIBLE);
                mediaPlayer.pause();
                String s = vFactsTextViewAppealingFactActivity.getText().toString();
                vTextToSpeech.speak(s, TextToSpeech.QUEUE_ADD, null,"si");
            }
        });


        vPauseImageViewAppealingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vPlayImageViewAppealingFactActivity.setVisibility(View.VISIBLE);
                vPauseImageViewAppealingFactActivity.setVisibility(View.INVISIBLE);
                vTextToSpeech.stop();

                mediaPlayer.start();
            }
        });

        for (k = 0; k <= 998; k++){
            appealingFactsAppealingFactActivity[k] = "factAppealingFactActivity" + k;
        }

        Intent fetchDataIntentAppealingFactActivity = getIntent();

        for(j = 0; j <= 998; j++){
            appealingFactsAppealingFactActivity[j] = fetchDataIntentAppealingFactActivity.getStringExtra(AppealingFactList.extraTextAppealingFactListActivity[j]);
        }

        appealingFactsCounter = fetchDataIntentAppealingFactActivity.getIntExtra(AppealingFactList.EXTRA_NUMBER_APPEALING_FACT_LIST_ACTIVITY, 0);

        vFactsTextViewAppealingFactActivity.setText(appealingFactsAppealingFactActivity[appealingFactsCounter]);
        if (appealingFactsCounter == 0) {
            vPreviousImageViewAppealingFactActivity.setImageResource(R.drawable.ic_previous_fade);
        }

        vPreviousImageViewAppealingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (appealingFactsCounter == 0 ){
                    vFactsTextViewAppealingFactActivity.setText(appealingFactsAppealingFactActivity[appealingFactsCounter]);
                }else if (appealingFactsCounter == 1){
                    appealingFactsCounter--;
                    vPreviousImageViewAppealingFactActivity.setImageResource(R.drawable.ic_previous_fade);
                    vFactsTextViewAppealingFactActivity.setText(appealingFactsAppealingFactActivity[appealingFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewAppealingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewAppealingFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                }else if (appealingFactsCounter % 7 == 6){
                    appealingFactsCounter--;
                    vFactsTextViewAppealingFactActivity.setText(appealingFactsAppealingFactActivity[appealingFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewAppealingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewAppealingFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                    if (vInterstitialAdAppealingFactActivity.isLoaded()) {
                        vInterstitialAdAppealingFactActivity.show();
                    } else {
                        Log.i("TAG", "The interstitial wasn't loaded yet.");
                    }
                }else{
                    appealingFactsCounter--;
                    vFactsTextViewAppealingFactActivity.setText(appealingFactsAppealingFactActivity[appealingFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewAppealingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewAppealingFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                }

            }
        });

        vNextImageViewAppealingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (appealingFactsCounter == 0) {
                    appealingFactsCounter++;
                    vPreviousImageViewAppealingFactActivity.setImageResource(R.drawable.ic_previous);
                    vFactsTextViewAppealingFactActivity.setText(appealingFactsAppealingFactActivity[appealingFactsCounter]);
                    if (vTextToSpeech.isSpeaking()) {
                        vTextToSpeech.stop();
                        vPlayImageViewAppealingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewAppealingFactActivity.setVisibility(View.INVISIBLE);
                        mediaPlayer.start();
                    }
                } else if (appealingFactsCounter % 7 == 6) {
                    appealingFactsCounter++;
                    vFactsTextViewAppealingFactActivity.setText(appealingFactsAppealingFactActivity[appealingFactsCounter]);
                    if (vTextToSpeech.isSpeaking()) {
                        vTextToSpeech.stop();
                        vPlayImageViewAppealingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewAppealingFactActivity.setVisibility(View.INVISIBLE);
                        mediaPlayer.start();
                    }
                    if (vInterstitialAdAppealingFactActivity.isLoaded()) {
                        vInterstitialAdAppealingFactActivity.show();
                    } else {
                        Log.i("TAG", "The interstitial wasn't loaded yet.");
                    }
                } else if (appealingFactsCounter % 7 != 6 && appealingFactsCounter <= 997) {
                    appealingFactsCounter++;
                    vFactsTextViewAppealingFactActivity.setText(appealingFactsAppealingFactActivity[appealingFactsCounter]);
                    if (vTextToSpeech.isSpeaking()) {
                        vTextToSpeech.stop();
                        vPlayImageViewAppealingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewAppealingFactActivity.setVisibility(View.INVISIBLE);
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

            MusicComponent.BackgroundMusic(AppealingFact.this, R.raw.bg_music);

        }

        if(mediaPlayer.isPlaying()){

            mediaPlayer.pause();

        }

        if(MusicComponent.shouldPlayAppealingFactActivity){
            MusicComponent.shouldPlayAppealingFactActivity = false;
        }

        mediaPlayer.start();
        MusicComponent.onCompletion(mediaPlayer);

        SharedPreferences sharedPreferencesAppealingFactActivity = getSharedPreferences("Background", Context.MODE_PRIVATE);
        int vSelectedColorAppealingFactActivity = sharedPreferencesAppealingFactActivity.getInt("integerSelectedColor", 0);
        ConstraintLayout vConstraintLayoutAppealingFactActivity = findViewById(R.id.constraintLayoutAppealingFactActivity);
        if (sharedPreferencesAppealingFactActivity.getInt("integerSelectedColor", Color.WHITE) == vSelectedColorAppealingFactActivity)
        {vConstraintLayoutAppealingFactActivity.setBackgroundColor(vSelectedColorAppealingFactActivity);}
        else
        {vConstraintLayoutAppealingFactActivity.setBackgroundColor(Color.parseColor("#FDD935"));}

    }

    @Override
    protected void onPause() {
        super.onPause();

        if(vTextToSpeech.isSpeaking()){
            vTextToSpeech.stop();
            vPlayImageViewAppealingFactActivity.setVisibility(View.VISIBLE);
            vPauseImageViewAppealingFactActivity.setVisibility(View.INVISIBLE);

        }

        if (!MusicComponent.shouldPlayAppealingFactActivity){

            mediaPlayer.pause();
            MusicComponent.shouldPlayAppealingFactListActivity = false;
            MusicComponent.shouldPlayAppealingFactActivity = true;

        }
    }

}