package com.jinendra.fact.Facts.General.Engaging;

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


public class EngagingFact extends AppCompatActivity {


    ImageView vPreviousMenuImageViewEngagingFactActivity, vShareImageViewEngagingFactActivity;
    TextView vFactsTextViewEngagingFactActivity;
    ImageView vPreviousImageViewEngagingFactActivity;
    ImageView vPlayImageViewEngagingFactActivity, vPauseImageViewEngagingFactActivity;
    ImageView vNextImageViewEngagingFactActivity;
    private TextToSpeech vTextToSpeech;

    String[] EngagingFactsEngagingFactActivity = new String[999];
    int EngagingFactsCounter,j,k;

    private InterstitialAd vInterstitialAdEngagingFactActivity;
    AdView vBannerAdViewEngagingFactActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engaging_fact);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        vBannerAdViewEngagingFactActivity = findViewById(R.id.bannerAdViewEngagingFactActivity);
        AdRequest vBannerAdRequestEngagingFactActivity = new AdRequest.Builder().build();
        vBannerAdViewEngagingFactActivity.loadAd(vBannerAdRequestEngagingFactActivity);

        vInterstitialAdEngagingFactActivity = new InterstitialAd(this);
        vInterstitialAdEngagingFactActivity.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        vInterstitialAdEngagingFactActivity.loadAd(new AdRequest.Builder().build());

        vInterstitialAdEngagingFactActivity.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                vInterstitialAdEngagingFactActivity.loadAd(new AdRequest.Builder().build());
            }

        });

        vPreviousMenuImageViewEngagingFactActivity = findViewById(R.id.previousMenuImageViewEngagingFactActivity);
        vPreviousMenuImageViewEngagingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vPreviousMenuIntentEngagingFactActivity = new Intent(EngagingFact.this, EngagingFactList.class);
                MusicComponent.shouldPlayEngagingFactListActivity = false;
                MusicComponent.shouldPlayEngagingFactActivity = true;
                startActivity(vPreviousMenuIntentEngagingFactActivity);

                if(vTextToSpeech.isSpeaking()){
                    vTextToSpeech.stop();
                }
            }
        });

        vShareImageViewEngagingFactActivity = findViewById(R.id.shareImageViewEngagingFactActivity);
        vShareImageViewEngagingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "I found a amazing fact " + vFactsTextViewEngagingFactActivity.getText().toString();
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, s);
                startActivity(Intent.createChooser(shareIntent, "Share text via"));

                if (vInterstitialAdEngagingFactActivity.isLoaded()) {
                    vInterstitialAdEngagingFactActivity.show();
                } else {
                    Log.i("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        });

        vPreviousImageViewEngagingFactActivity = findViewById(R.id.previousImageViewEngagingFactActivity);
        vNextImageViewEngagingFactActivity = findViewById(R.id.nextImageViewEngagingFactActivity);
        vFactsTextViewEngagingFactActivity = findViewById(R.id.factsTextViewEngagingFactActivity);
        vPlayImageViewEngagingFactActivity = findViewById(R.id.playImageViewEngagingFactActivity);
        vPauseImageViewEngagingFactActivity = findViewById(R.id.pauseImageViewEngagingFactActivity);

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
                                    vPauseImageViewEngagingFactActivity.setVisibility(View.INVISIBLE);
                                    vPlayImageViewEngagingFactActivity.setVisibility(View.VISIBLE);
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
            vTextToSpeech.speak(vFactsTextViewEngagingFactActivity.getText().toString(),TextToSpeech.QUEUE_FLUSH,null,TextToSpeech.ACTION_TTS_QUEUE_PROCESSING_COMPLETED);
        }

        vPlayImageViewEngagingFactActivity.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                vPauseImageViewEngagingFactActivity.setVisibility(View.VISIBLE);
                vPlayImageViewEngagingFactActivity.setVisibility(View.INVISIBLE);
                mediaPlayer.pause();
                String s = vFactsTextViewEngagingFactActivity.getText().toString();
                vTextToSpeech.speak(s, TextToSpeech.QUEUE_ADD, null,"si");
            }
        });


        vPauseImageViewEngagingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vPlayImageViewEngagingFactActivity.setVisibility(View.VISIBLE);
                vPauseImageViewEngagingFactActivity.setVisibility(View.INVISIBLE);
                vTextToSpeech.stop();

                mediaPlayer.start();
            }
        });

        for (k = 0; k <= 998; k++){
            EngagingFactsEngagingFactActivity[k] = "factEngagingFactActivity" + k;
        }

        Intent fetchDataIntentEngagingFactActivity = getIntent();

        for(j = 0; j <= 998; j++){
            EngagingFactsEngagingFactActivity[j] = fetchDataIntentEngagingFactActivity.getStringExtra(EngagingFactList.extraTextEngagingFactListActivity[j]);
        }

        EngagingFactsCounter = fetchDataIntentEngagingFactActivity.getIntExtra(EngagingFactList.EXTRA_NUMBER_ENGAGING_FACT_LIST_ACTIVITY, 0);

        vFactsTextViewEngagingFactActivity.setText(EngagingFactsEngagingFactActivity[EngagingFactsCounter]);
        if (EngagingFactsCounter == 0) {
            vPreviousImageViewEngagingFactActivity.setImageResource(R.drawable.ic_previous_fade);
        }

        vPreviousImageViewEngagingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (EngagingFactsCounter == 0 ){
                    vFactsTextViewEngagingFactActivity.setText(EngagingFactsEngagingFactActivity[EngagingFactsCounter]);
                }else if (EngagingFactsCounter == 1){
                    EngagingFactsCounter--;
                    vPreviousImageViewEngagingFactActivity.setImageResource(R.drawable.ic_previous_fade);
                    vFactsTextViewEngagingFactActivity.setText(EngagingFactsEngagingFactActivity[EngagingFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewEngagingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewEngagingFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                }else if (EngagingFactsCounter % 7 == 6){
                    EngagingFactsCounter--;
                    vFactsTextViewEngagingFactActivity.setText(EngagingFactsEngagingFactActivity[EngagingFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewEngagingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewEngagingFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                    if (vInterstitialAdEngagingFactActivity.isLoaded()) {
                        vInterstitialAdEngagingFactActivity.show();
                    } else {
                        Log.i("TAG", "The interstitial wasn't loaded yet.");
                    }
                }else{
                    EngagingFactsCounter--;
                    vFactsTextViewEngagingFactActivity.setText(EngagingFactsEngagingFactActivity[EngagingFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewEngagingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewEngagingFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                }

            }
        });

        vNextImageViewEngagingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (EngagingFactsCounter == 0) {
                    EngagingFactsCounter++;
                    vPreviousImageViewEngagingFactActivity.setImageResource(R.drawable.ic_previous);
                    vFactsTextViewEngagingFactActivity.setText(EngagingFactsEngagingFactActivity[EngagingFactsCounter]);
                    if (vTextToSpeech.isSpeaking()) {
                        vTextToSpeech.stop();
                        vPlayImageViewEngagingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewEngagingFactActivity.setVisibility(View.INVISIBLE);
                        mediaPlayer.start();
                    }
                } else if (EngagingFactsCounter % 7 == 6) {
                    EngagingFactsCounter++;
                    vFactsTextViewEngagingFactActivity.setText(EngagingFactsEngagingFactActivity[EngagingFactsCounter]);
                    if (vTextToSpeech.isSpeaking()) {
                        vTextToSpeech.stop();
                        vPlayImageViewEngagingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewEngagingFactActivity.setVisibility(View.INVISIBLE);
                        mediaPlayer.start();
                    }
                    if (vInterstitialAdEngagingFactActivity.isLoaded()) {
                        vInterstitialAdEngagingFactActivity.show();
                    } else {
                        Log.i("TAG", "The interstitial wasn't loaded yet.");
                    }
                } else if (EngagingFactsCounter % 7 != 6 && EngagingFactsCounter <= 997) {
                    EngagingFactsCounter++;
                    vFactsTextViewEngagingFactActivity.setText(EngagingFactsEngagingFactActivity[EngagingFactsCounter]);
                    if (vTextToSpeech.isSpeaking()) {
                        vTextToSpeech.stop();
                        vPlayImageViewEngagingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewEngagingFactActivity.setVisibility(View.INVISIBLE);
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

            MusicComponent.BackgroundMusic(EngagingFact.this, R.raw.bg_music);

        }

        if(mediaPlayer.isPlaying()){

            mediaPlayer.pause();

        }

        if(MusicComponent.shouldPlayEngagingFactActivity){
            MusicComponent.shouldPlayEngagingFactActivity = false;
        }

        mediaPlayer.start();
        MusicComponent.onCompletion(mediaPlayer);

        SharedPreferences sharedPreferencesEngagingFactActivity = getSharedPreferences("Background", Context.MODE_PRIVATE);
        int vSelectedColorEngagingFactActivity = sharedPreferencesEngagingFactActivity.getInt("integerSelectedColor", 0);
        ConstraintLayout vConstraintLayoutEngagingFactActivity = findViewById(R.id.constraintLayoutEngagingFactActivity);
        if (sharedPreferencesEngagingFactActivity.getInt("integerSelectedColor", Color.WHITE) == vSelectedColorEngagingFactActivity)
        {vConstraintLayoutEngagingFactActivity.setBackgroundColor(vSelectedColorEngagingFactActivity);}
        else
        {vConstraintLayoutEngagingFactActivity.setBackgroundColor(Color.parseColor("#FDD935"));}

    }

    @Override
    protected void onPause() {
        super.onPause();

        if(vTextToSpeech.isSpeaking()){
            vTextToSpeech.stop();
            vPlayImageViewEngagingFactActivity.setVisibility(View.VISIBLE);
            vPauseImageViewEngagingFactActivity.setVisibility(View.INVISIBLE);

        }

        if (!MusicComponent.shouldPlayEngagingFactActivity){

            mediaPlayer.pause();
            MusicComponent.shouldPlayEngagingFactListActivity = false;
            MusicComponent.shouldPlayEngagingFactActivity = true;

        }
    }

}