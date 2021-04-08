package com.jinendra.fact.Facts.General.Captivating;

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


public class CaptivatingFact extends AppCompatActivity {


    ImageView vPreviousMenuImageViewCaptivatingFactActivity, vShareImageViewCaptivatingFactActivity;
    TextView vFactsTextViewCaptivatingFactActivity;
    ImageView vPreviousImageViewCaptivatingFactActivity;
    ImageView vPlayImageViewCaptivatingFactActivity, vPauseImageViewCaptivatingFactActivity;
    ImageView vNextImageViewCaptivatingFactActivity;
    private TextToSpeech vTextToSpeech;

    String[] captivatingFactsCaptivatingFactActivity = new String[999];
    int captivatingFactsCounter,j,k;

    private InterstitialAd vInterstitialAdCaptivatingFactActivity;
    AdView vBannerAdViewCaptivatingFactActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captivating_fact);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        vBannerAdViewCaptivatingFactActivity = findViewById(R.id.bannerAdViewCaptivatingFactActivity);
        AdRequest vBannerAdRequestCaptivatingFactActivity = new AdRequest.Builder().build();
        vBannerAdViewCaptivatingFactActivity.loadAd(vBannerAdRequestCaptivatingFactActivity);

        vInterstitialAdCaptivatingFactActivity = new InterstitialAd(this);
        vInterstitialAdCaptivatingFactActivity.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        vInterstitialAdCaptivatingFactActivity.loadAd(new AdRequest.Builder().build());

        vInterstitialAdCaptivatingFactActivity.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                vInterstitialAdCaptivatingFactActivity.loadAd(new AdRequest.Builder().build());
            }

        });

        vPreviousMenuImageViewCaptivatingFactActivity = findViewById(R.id.previousMenuImageViewCaptivatingFactActivity);
        vPreviousMenuImageViewCaptivatingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vPreviousMenuIntentCaptivatingFactActivity = new Intent(CaptivatingFact.this, CaptivatingFactList.class);
                MusicComponent.shouldPlayCaptivatingFactListActivity = false;
                MusicComponent.shouldPlayCaptivatingFactActivity = true;
                startActivity(vPreviousMenuIntentCaptivatingFactActivity);

                if(vTextToSpeech.isSpeaking()){
                    vTextToSpeech.stop();
                }
            }
        });

        vShareImageViewCaptivatingFactActivity = findViewById(R.id.shareImageViewCaptivatingFactActivity);
        vShareImageViewCaptivatingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "I found a amazing fact " + vFactsTextViewCaptivatingFactActivity.getText().toString();
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, s);
                startActivity(Intent.createChooser(shareIntent, "Share text via"));

                if (vInterstitialAdCaptivatingFactActivity.isLoaded()) {
                    vInterstitialAdCaptivatingFactActivity.show();
                } else {
                    Log.i("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        });

        vPreviousImageViewCaptivatingFactActivity = findViewById(R.id.previousImageViewCaptivatingFactActivity);
        vNextImageViewCaptivatingFactActivity = findViewById(R.id.nextImageViewCaptivatingFactActivity);
        vFactsTextViewCaptivatingFactActivity = findViewById(R.id.factsTextViewCaptivatingFactActivity);
        vPlayImageViewCaptivatingFactActivity = findViewById(R.id.playImageViewCaptivatingFactActivity);
        vPauseImageViewCaptivatingFactActivity = findViewById(R.id.pauseImageViewCaptivatingFactActivity);

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
                                    vPauseImageViewCaptivatingFactActivity.setVisibility(View.INVISIBLE);
                                    vPlayImageViewCaptivatingFactActivity.setVisibility(View.VISIBLE);
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
            vTextToSpeech.speak(vFactsTextViewCaptivatingFactActivity.getText().toString(),TextToSpeech.QUEUE_FLUSH,null,TextToSpeech.ACTION_TTS_QUEUE_PROCESSING_COMPLETED);
        }

        vPlayImageViewCaptivatingFactActivity.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                vPauseImageViewCaptivatingFactActivity.setVisibility(View.VISIBLE);
                vPlayImageViewCaptivatingFactActivity.setVisibility(View.INVISIBLE);
                mediaPlayer.pause();
                String s = vFactsTextViewCaptivatingFactActivity.getText().toString();
                vTextToSpeech.speak(s, TextToSpeech.QUEUE_ADD, null,"si");
            }
        });


        vPauseImageViewCaptivatingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vPlayImageViewCaptivatingFactActivity.setVisibility(View.VISIBLE);
                vPauseImageViewCaptivatingFactActivity.setVisibility(View.INVISIBLE);
                vTextToSpeech.stop();

                mediaPlayer.start();
            }
        });

        for (k = 0; k <= 998; k++){
            captivatingFactsCaptivatingFactActivity[k] = "factCaptivatingFactActivity" + k;
        }

        Intent fetchDataIntentCaptivatingFactActivity = getIntent();

        for(j = 0; j <= 998; j++){
            captivatingFactsCaptivatingFactActivity[j] = fetchDataIntentCaptivatingFactActivity.getStringExtra(CaptivatingFactList.extraTextCaptivatingFactListActivity[j]);
        }

        captivatingFactsCounter = fetchDataIntentCaptivatingFactActivity.getIntExtra(CaptivatingFactList.EXTRA_NUMBER_CAPTIVATING_FACT_LIST_ACTIVITY, 0);

        vFactsTextViewCaptivatingFactActivity.setText(captivatingFactsCaptivatingFactActivity[captivatingFactsCounter]);
        if (captivatingFactsCounter == 0) {
            vPreviousImageViewCaptivatingFactActivity.setImageResource(R.drawable.ic_previous_fade);
        }

        vPreviousImageViewCaptivatingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (captivatingFactsCounter == 0 ){
                    vFactsTextViewCaptivatingFactActivity.setText(captivatingFactsCaptivatingFactActivity[captivatingFactsCounter]);
                }else if (captivatingFactsCounter == 1){
                    captivatingFactsCounter--;
                    vPreviousImageViewCaptivatingFactActivity.setImageResource(R.drawable.ic_previous_fade);
                    vFactsTextViewCaptivatingFactActivity.setText(captivatingFactsCaptivatingFactActivity[captivatingFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewCaptivatingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewCaptivatingFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                }else if (captivatingFactsCounter % 7 == 6){
                    captivatingFactsCounter--;
                    vFactsTextViewCaptivatingFactActivity.setText(captivatingFactsCaptivatingFactActivity[captivatingFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewCaptivatingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewCaptivatingFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                    if (vInterstitialAdCaptivatingFactActivity.isLoaded()) {
                        vInterstitialAdCaptivatingFactActivity.show();
                    } else {
                        Log.i("TAG", "The interstitial wasn't loaded yet.");
                    }
                }else{
                    captivatingFactsCounter--;
                    vFactsTextViewCaptivatingFactActivity.setText(captivatingFactsCaptivatingFactActivity[captivatingFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewCaptivatingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewCaptivatingFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                }

            }
        });

        vNextImageViewCaptivatingFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (captivatingFactsCounter == 0) {
                    captivatingFactsCounter++;
                    vPreviousImageViewCaptivatingFactActivity.setImageResource(R.drawable.ic_previous);
                    vFactsTextViewCaptivatingFactActivity.setText(captivatingFactsCaptivatingFactActivity[captivatingFactsCounter]);
                    if (vTextToSpeech.isSpeaking()) {
                        vTextToSpeech.stop();
                        vPlayImageViewCaptivatingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewCaptivatingFactActivity.setVisibility(View.INVISIBLE);
                        mediaPlayer.start();
                    }
                } else if (captivatingFactsCounter % 7 == 6) {
                    captivatingFactsCounter++;
                    vFactsTextViewCaptivatingFactActivity.setText(captivatingFactsCaptivatingFactActivity[captivatingFactsCounter]);
                    if (vTextToSpeech.isSpeaking()) {
                        vTextToSpeech.stop();
                        vPlayImageViewCaptivatingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewCaptivatingFactActivity.setVisibility(View.INVISIBLE);
                        mediaPlayer.start();
                    }
                    if (vInterstitialAdCaptivatingFactActivity.isLoaded()) {
                        vInterstitialAdCaptivatingFactActivity.show();
                    } else {
                        Log.i("TAG", "The interstitial wasn't loaded yet.");
                    }
                } else if (captivatingFactsCounter % 7 != 6 && captivatingFactsCounter <= 997) {
                    captivatingFactsCounter++;
                    vFactsTextViewCaptivatingFactActivity.setText(captivatingFactsCaptivatingFactActivity[captivatingFactsCounter]);
                    if (vTextToSpeech.isSpeaking()) {
                        vTextToSpeech.stop();
                        vPlayImageViewCaptivatingFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewCaptivatingFactActivity.setVisibility(View.INVISIBLE);
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

            MusicComponent.BackgroundMusic(CaptivatingFact.this, R.raw.bg_music);

        }

        if(mediaPlayer.isPlaying()){

            mediaPlayer.pause();

        }

        if(MusicComponent.shouldPlayCaptivatingFactActivity){
            MusicComponent.shouldPlayCaptivatingFactActivity = false;
        }

        mediaPlayer.start();
        MusicComponent.onCompletion(mediaPlayer);

        SharedPreferences sharedPreferencesCaptivatingFactActivity = getSharedPreferences("Background", Context.MODE_PRIVATE);
        int vSelectedColorCaptivatingFactActivity = sharedPreferencesCaptivatingFactActivity.getInt("integerSelectedColor", 0);
        ConstraintLayout vConstraintLayoutCaptivatingFactActivity = findViewById(R.id.constraintLayoutCaptivatingFactActivity);
        if (sharedPreferencesCaptivatingFactActivity.getInt("integerSelectedColor", Color.WHITE) == vSelectedColorCaptivatingFactActivity)
        {vConstraintLayoutCaptivatingFactActivity.setBackgroundColor(vSelectedColorCaptivatingFactActivity);}
        else
        {vConstraintLayoutCaptivatingFactActivity.setBackgroundColor(Color.parseColor("#FDD935"));}

    }

    @Override
    protected void onPause() {
        super.onPause();

        if(vTextToSpeech.isSpeaking()){
            vTextToSpeech.stop();
            vPlayImageViewCaptivatingFactActivity.setVisibility(View.VISIBLE);
            vPauseImageViewCaptivatingFactActivity.setVisibility(View.INVISIBLE);

        }

        if (!MusicComponent.shouldPlayCaptivatingFactActivity){

            mediaPlayer.pause();
            MusicComponent.shouldPlayCaptivatingFactListActivity = false;
            MusicComponent.shouldPlayCaptivatingFactActivity = true;

        }
    }

}