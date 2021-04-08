package com.jinendra.fact.Facts.General.Entertaining;

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
import com.jinendra.fact.Facts.General.Entertaining.EntertainingFactList;
import com.jinendra.fact.MusicComponent;
import com.jinendra.fact.R;

import java.util.Locale;

import static com.jinendra.fact.MusicComponent.mediaPlayer;


public class EntertainingFact extends AppCompatActivity {


    ImageView vPreviousMenuImageViewEntertainingFactActivity, vShareImageViewEntertainingFactActivity;
    TextView vFactsTextViewEntertainingFactActivity;
    ImageView vPreviousImageViewEntertainingFactActivity;
    ImageView vPlayImageViewEntertainingFactActivity, vPauseImageViewEntertainingFactActivity;
    ImageView vNextImageViewEntertainingFactActivity;
    private TextToSpeech vTextToSpeech;

    String[] EntertainingFactsEntertainingFactActivity = new String[999];
    int EntertainingFactsCounter,j,k;

    private InterstitialAd vInterstitialAdEntertainingFactActivity;
    AdView vBannerAdViewEntertainingFactActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertaining_fact);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        vBannerAdViewEntertainingFactActivity = findViewById(R.id.bannerAdViewEntertainingFactActivity);
        AdRequest vBannerAdRequestEntertainingFactActivity = new AdRequest.Builder().build();
        vBannerAdViewEntertainingFactActivity.loadAd(vBannerAdRequestEntertainingFactActivity);

        vInterstitialAdEntertainingFactActivity = new InterstitialAd(this);
        vInterstitialAdEntertainingFactActivity.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        vInterstitialAdEntertainingFactActivity.loadAd(new AdRequest.Builder().build());

        vInterstitialAdEntertainingFactActivity.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                vInterstitialAdEntertainingFactActivity.loadAd(new AdRequest.Builder().build());
            }

        });

        vPreviousMenuImageViewEntertainingFactActivity = findViewById(R.id.previousMenuImageViewEntertainingFactActivity);
        vPreviousMenuImageViewEntertainingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vPreviousMenuIntentEntertainingFactActivity = new Intent(EntertainingFact.this, EntertainingFactList.class);
                MusicComponent.shouldPlayEntertainingFactListActivity = false;
                MusicComponent.shouldPlayEntertainingFactActivity = true;
                startActivity(vPreviousMenuIntentEntertainingFactActivity);

                if(vTextToSpeech.isSpeaking()){
                    vTextToSpeech.stop();
                }
            }
        });

        vShareImageViewEntertainingFactActivity = findViewById(R.id.shareImageViewEntertainingFactActivity);
        vShareImageViewEntertainingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "I found a amazing fact " + vFactsTextViewEntertainingFactActivity.getText().toString();
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, s);
                startActivity(Intent.createChooser(shareIntent, "Share text via"));

                if (vInterstitialAdEntertainingFactActivity.isLoaded()) {
                    vInterstitialAdEntertainingFactActivity.show();
                } else {
                    Log.i("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        });

        vPreviousImageViewEntertainingFactActivity = findViewById(R.id.previousImageViewEntertainingFactActivity);
        vNextImageViewEntertainingFactActivity = findViewById(R.id.nextImageViewEntertainingFactActivity);
        vFactsTextViewEntertainingFactActivity = findViewById(R.id.factsTextViewEntertainingFactActivity);
        vPlayImageViewEntertainingFactActivity = findViewById(R.id.playImageViewEntertainingFactActivity);
        vPauseImageViewEntertainingFactActivity = findViewById(R.id.pauseImageViewEntertainingFactActivity);

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
                                    vPauseImageViewEntertainingFactActivity.setVisibility(View.INVISIBLE);
                                    vPlayImageViewEntertainingFactActivity.setVisibility(View.VISIBLE);
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
            vTextToSpeech.speak(vFactsTextViewEntertainingFactActivity.getText().toString(),TextToSpeech.QUEUE_FLUSH,null,TextToSpeech.ACTION_TTS_QUEUE_PROCESSING_COMPLETED);
        }

        vPlayImageViewEntertainingFactActivity.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                vPauseImageViewEntertainingFactActivity.setVisibility(View.VISIBLE);
                vPlayImageViewEntertainingFactActivity.setVisibility(View.INVISIBLE);
                mediaPlayer.pause();
                String s = vFactsTextViewEntertainingFactActivity.getText().toString();
                vTextToSpeech.speak(s, TextToSpeech.QUEUE_ADD, null,"si");
            }
        });


        vPauseImageViewEntertainingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vPlayImageViewEntertainingFactActivity.setVisibility(View.VISIBLE);
                vPauseImageViewEntertainingFactActivity.setVisibility(View.INVISIBLE);
                vTextToSpeech.stop();

                mediaPlayer.start();
            }
        });

        for (k = 0; k <= 998; k++){
            EntertainingFactsEntertainingFactActivity[k] = "factEntertainingFactActivity" + k;
        }

        Intent fetchDataIntentEntertainingFactActivity = getIntent();

        for(j = 0; j <= 998; j++){
            EntertainingFactsEntertainingFactActivity[j] = fetchDataIntentEntertainingFactActivity.getStringExtra(EntertainingFactList.extraTextEntertainingFactListActivity[j]);
        }

        EntertainingFactsCounter = fetchDataIntentEntertainingFactActivity.getIntExtra(EntertainingFactList.EXTRA_NUMBER_ENTERTAINING_FACT_LIST_ACTIVITY, 0);

        vFactsTextViewEntertainingFactActivity.setText(EntertainingFactsEntertainingFactActivity[EntertainingFactsCounter]);
        if (EntertainingFactsCounter == 0) {
            vPreviousImageViewEntertainingFactActivity.setImageResource(R.drawable.ic_previous_fade);
        }

        vPreviousImageViewEntertainingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (EntertainingFactsCounter == 0 ){
                    vFactsTextViewEntertainingFactActivity.setText(EntertainingFactsEntertainingFactActivity[EntertainingFactsCounter]);
                }else if (EntertainingFactsCounter == 1){
                    EntertainingFactsCounter--;
                    vPreviousImageViewEntertainingFactActivity.setImageResource(R.drawable.ic_previous_fade);
                    vFactsTextViewEntertainingFactActivity.setText(EntertainingFactsEntertainingFactActivity[EntertainingFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewEntertainingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewEntertainingFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                }else if (EntertainingFactsCounter % 7 == 6){
                    EntertainingFactsCounter--;
                    vFactsTextViewEntertainingFactActivity.setText(EntertainingFactsEntertainingFactActivity[EntertainingFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewEntertainingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewEntertainingFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                    if (vInterstitialAdEntertainingFactActivity.isLoaded()) {
                        vInterstitialAdEntertainingFactActivity.show();
                    } else {
                        Log.i("TAG", "The interstitial wasn't loaded yet.");
                    }
                }else{
                    EntertainingFactsCounter--;
                    vFactsTextViewEntertainingFactActivity.setText(EntertainingFactsEntertainingFactActivity[EntertainingFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewEntertainingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewEntertainingFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                }

            }
        });

        vNextImageViewEntertainingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (EntertainingFactsCounter == 0) {
                    EntertainingFactsCounter++;
                    vPreviousImageViewEntertainingFactActivity.setImageResource(R.drawable.ic_previous);
                    vFactsTextViewEntertainingFactActivity.setText(EntertainingFactsEntertainingFactActivity[EntertainingFactsCounter]);
                    if (vTextToSpeech.isSpeaking()) {
                        vTextToSpeech.stop();
                        vPlayImageViewEntertainingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewEntertainingFactActivity.setVisibility(View.INVISIBLE);
                        mediaPlayer.start();
                    }
                } else if (EntertainingFactsCounter % 7 == 6) {
                    EntertainingFactsCounter++;
                    vFactsTextViewEntertainingFactActivity.setText(EntertainingFactsEntertainingFactActivity[EntertainingFactsCounter]);
                    if (vTextToSpeech.isSpeaking()) {
                        vTextToSpeech.stop();
                        vPlayImageViewEntertainingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewEntertainingFactActivity.setVisibility(View.INVISIBLE);
                        mediaPlayer.start();
                    }
                    if (vInterstitialAdEntertainingFactActivity.isLoaded()) {
                        vInterstitialAdEntertainingFactActivity.show();
                    } else {
                        Log.i("TAG", "The interstitial wasn't loaded yet.");
                    }
                } else if (EntertainingFactsCounter % 7 != 6 && EntertainingFactsCounter <= 997) {
                    EntertainingFactsCounter++;
                    vFactsTextViewEntertainingFactActivity.setText(EntertainingFactsEntertainingFactActivity[EntertainingFactsCounter]);
                    if (vTextToSpeech.isSpeaking()) {
                        vTextToSpeech.stop();
                        vPlayImageViewEntertainingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewEntertainingFactActivity.setVisibility(View.INVISIBLE);
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

            MusicComponent.BackgroundMusic(EntertainingFact.this, R.raw.bg_music);

        }

        if(mediaPlayer.isPlaying()){

            mediaPlayer.pause();

        }

        if(MusicComponent.shouldPlayEntertainingFactActivity){
            MusicComponent.shouldPlayEntertainingFactActivity = false;
        }

        mediaPlayer.start();
        MusicComponent.onCompletion(mediaPlayer);

        SharedPreferences sharedPreferencesEntertainingFactActivity = getSharedPreferences("Background", Context.MODE_PRIVATE);
        int vSelectedColorEntertainingFactActivity = sharedPreferencesEntertainingFactActivity.getInt("integerSelectedColor", 0);
        ConstraintLayout vConstraintLayoutEntertainingFactActivity = findViewById(R.id.constraintLayoutEntertainingFactActivity);
        if (sharedPreferencesEntertainingFactActivity.getInt("integerSelectedColor", Color.WHITE) == vSelectedColorEntertainingFactActivity)
        {vConstraintLayoutEntertainingFactActivity.setBackgroundColor(vSelectedColorEntertainingFactActivity);}
        else
        {vConstraintLayoutEntertainingFactActivity.setBackgroundColor(Color.parseColor("#FDD935"));}

    }

    @Override
    protected void onPause() {
        super.onPause();

        if(vTextToSpeech.isSpeaking()){
            vTextToSpeech.stop();
            vPlayImageViewEntertainingFactActivity.setVisibility(View.VISIBLE);
            vPauseImageViewEntertainingFactActivity.setVisibility(View.INVISIBLE);

        }

        if (!MusicComponent.shouldPlayEntertainingFactActivity){

            mediaPlayer.pause();
            MusicComponent.shouldPlayEntertainingFactListActivity = false;
            MusicComponent.shouldPlayEntertainingFactActivity = true;

        }
    }

}