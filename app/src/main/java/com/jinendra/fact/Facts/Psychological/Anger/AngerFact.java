package com.jinendra.fact.Facts.Psychological.Anger;

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


public class AngerFact extends AppCompatActivity {


    ImageView vPreviousMenuImageViewAngerFactActivity, vShareImageViewAngerFactActivity;
    TextView vFactsTextViewAngerFactActivity;
    ImageView vPreviousImageViewAngerFactActivity;
    ImageView vPlayImageViewAngerFactActivity, vPauseImageViewAngerFactActivity;
    ImageView vNextImageViewAngerFactActivity;
    private TextToSpeech vTextToSpeech;

    String[] angerFactsAngerFactActivity = new String[49];
    int angerFactsCounter,j,k;

    private InterstitialAd vInterstitialAdAngerFactActivity;
    AdView vBannerAdViewAngerFactActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anger_fact);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        vBannerAdViewAngerFactActivity = findViewById(R.id.bannerAdViewAngerFactActivity);
        AdRequest vBannerAdRequestAngerFactActivity = new AdRequest.Builder().build();
        vBannerAdViewAngerFactActivity.loadAd(vBannerAdRequestAngerFactActivity);

        vInterstitialAdAngerFactActivity = new InterstitialAd(this);
        vInterstitialAdAngerFactActivity.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        vInterstitialAdAngerFactActivity.loadAd(new AdRequest.Builder().build());

        vInterstitialAdAngerFactActivity.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                vInterstitialAdAngerFactActivity.loadAd(new AdRequest.Builder().build());
            }

        });

        vPreviousMenuImageViewAngerFactActivity = findViewById(R.id.previousMenuImageViewAngerFactActivity);
        vPreviousMenuImageViewAngerFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vPreviousMenuIntentAngerFactActivity = new Intent(AngerFact.this, AngerFactList.class);
                MusicComponent.shouldPlayAngerFactListActivity = false;
                MusicComponent.shouldPlayAngerFactActivity = true;
                startActivity(vPreviousMenuIntentAngerFactActivity);

                if(vTextToSpeech.isSpeaking()){
                    vTextToSpeech.stop();
                }
            }
        });

        vShareImageViewAngerFactActivity = findViewById(R.id.shareImageViewAngerFactActivity);
        vShareImageViewAngerFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "I found a amazing fact " + vFactsTextViewAngerFactActivity.getText().toString();
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, s);
                startActivity(Intent.createChooser(shareIntent, "Share text via"));

                if (vInterstitialAdAngerFactActivity.isLoaded()) {
                   vInterstitialAdAngerFactActivity.show();
                } else {
                    Log.i("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        });

        vPreviousImageViewAngerFactActivity = findViewById(R.id.previousImageViewAngerFactActivity);
        vNextImageViewAngerFactActivity = findViewById(R.id.nextImageViewAngerFactActivity);
        vFactsTextViewAngerFactActivity = findViewById(R.id.factsTextViewAngerFactActivity);
        vPlayImageViewAngerFactActivity = findViewById(R.id.playImageViewAngerFactActivity);
        vPauseImageViewAngerFactActivity = findViewById(R.id.pauseImageViewAngerFactActivity);

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
                                    vPauseImageViewAngerFactActivity.setVisibility(View.INVISIBLE);
                                    vPlayImageViewAngerFactActivity.setVisibility(View.VISIBLE);
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
            vTextToSpeech.speak(vFactsTextViewAngerFactActivity.getText().toString(),TextToSpeech.QUEUE_FLUSH,null,TextToSpeech.ACTION_TTS_QUEUE_PROCESSING_COMPLETED);
        }

        vPlayImageViewAngerFactActivity.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                vPauseImageViewAngerFactActivity.setVisibility(View.VISIBLE);
                vPlayImageViewAngerFactActivity.setVisibility(View.INVISIBLE);
                mediaPlayer.pause();
                String s = vFactsTextViewAngerFactActivity.getText().toString();
                vTextToSpeech.speak(s, TextToSpeech.QUEUE_ADD, null,"si");
            }
        });


        vPauseImageViewAngerFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vPlayImageViewAngerFactActivity.setVisibility(View.VISIBLE);
                vPauseImageViewAngerFactActivity.setVisibility(View.INVISIBLE);
                vTextToSpeech.stop();

                mediaPlayer.start();
            }
        });

        for (k = 0; k <= 48; k++){
            angerFactsAngerFactActivity[k] = "factAngerFactActivity" + k;
        }

        Intent fetchDataIntentAngerFactActivity = getIntent();

        for(j = 0; j <= 48; j++){
            angerFactsAngerFactActivity[j] = fetchDataIntentAngerFactActivity.getStringExtra(AngerFactList.extraTextAngerFactListActivity[j]);
        }

        angerFactsCounter = fetchDataIntentAngerFactActivity.getIntExtra(AngerFactList.EXTRA_NUMBER_ANGER_FACT_LIST_ACTIVITY, 0);

        vFactsTextViewAngerFactActivity.setText(angerFactsAngerFactActivity[angerFactsCounter]);
        if (angerFactsCounter == 0) {
            vPreviousImageViewAngerFactActivity.setImageResource(R.drawable.ic_previous_fade);
        }

        vPreviousImageViewAngerFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                switch (angerFactsCounter) {
//                    case 0:
//                        vFactsTextViewAngerFactActivity.setText(angerFactsAngerFactActivity[angerFactsCounter]);
//                        break;
//                    case 1:
//                        angerFactsCounter--;
//                        vPreviousImageViewAngerFactActivity.setImageResource(R.drawable.ic_previous_fade);
//                        vFactsTextViewAngerFactActivity.setText(angerFactsAngerFactActivity[angerFactsCounter]);
//                        if(vTextToSpeech.isSpeaking()){
//                            vPlayImageViewAngerFactActivity.setVisibility(View.VISIBLE);
//                            vPauseImageViewAngerFactActivity.setVisibility(View.INVISIBLE);
//                            vTextToSpeech.stop();
//                            mediaPlayer.start();
//                        }
//                        break;
//                    case 2:
//                    case 3:
//                    case 4:
//                    case 5:
//                    case 7:
//                    case 8:
//                    case 9:
//                    case 10:
//                    case 11:
//                    case 12:
//                    case 14:
//                    case 15:
//                    case 16:
//                    case 17:
//                    case 18:
//                    case 19:
//                    case 21:
//                    case 22:
//                    case 23:
//                    case 24:
//                    case 25:
//                    case 26:
//                    case 28:
//                    case 29:
//                    case 30:
//                    case 31:
//                    case 32:
//                    case 33:
//                    case 35:
//                    case 36:
//                    case 37:
//                    case 38:
//                    case 39:
//                    case 40:
//                    case 42:
//                    case 43:
//                    case 44:
//                    case 45:
//                    case 46:
//                    case 47:
//                        angerFactsCounter--;
//                        vFactsTextViewAngerFactActivity.setText(angerFactsAngerFactActivity[angerFactsCounter]);
//                        if(vTextToSpeech.isSpeaking()){
//                            vPlayImageViewAngerFactActivity.setVisibility(View.VISIBLE);
//                            vPauseImageViewAngerFactActivity.setVisibility(View.INVISIBLE);
//                            vTextToSpeech.stop();
//                            mediaPlayer.start();
//                        }
//                        break;
//                    case 6:
//                    case 13:
//                    case 20:
//                    case 27:
//                    case 34:
//                    case 41:
//                    case 48:
//                        angerFactsCounter--;
//                        vFactsTextViewAngerFactActivity.setText(angerFactsAngerFactActivity[angerFactsCounter]);
//                        if(vTextToSpeech.isSpeaking()){
//                            vPlayImageViewAngerFactActivity.setVisibility(View.VISIBLE);
//                            vPauseImageViewAngerFactActivity.setVisibility(View.INVISIBLE);
//                            vTextToSpeech.stop();
//                            mediaPlayer.start();
//                        }
//                        if (vInterstitialAdAngerFactActivity.isLoaded()) {
//                            vInterstitialAdAngerFactActivity.show();
//                        } else {
//                            Log.i("TAG", "The interstitial wasn't loaded yet.");
//                        }
//                        break;
//                }

                if (angerFactsCounter == 0){
                    vFactsTextViewAngerFactActivity.setText(angerFactsAngerFactActivity[angerFactsCounter]);
                }else if (angerFactsCounter == 1){
                    angerFactsCounter--;
                    vPreviousImageViewAngerFactActivity.setImageResource(R.drawable.ic_previous_fade);
                    vFactsTextViewAngerFactActivity.setText(angerFactsAngerFactActivity[angerFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewAngerFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewAngerFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                }else if (angerFactsCounter % 7 == 6){
                    angerFactsCounter--;
                    vFactsTextViewAngerFactActivity.setText(angerFactsAngerFactActivity[angerFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewAngerFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewAngerFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                    if (vInterstitialAdAngerFactActivity.isLoaded()) {
                        vInterstitialAdAngerFactActivity.show();
                    }else {
                        Log.i("TAG", "The interstitial wasn't loaded yet.");
                    }
                }else if (angerFactsCounter % 7 != 6 && angerFactsCounter <= 48){
                    angerFactsCounter--;
                    vFactsTextViewAngerFactActivity.setText(angerFactsAngerFactActivity[angerFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vPlayImageViewAngerFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewAngerFactActivity.setVisibility(View.INVISIBLE);
                        vTextToSpeech.stop();
                        mediaPlayer.start();
                    }
                }

            }
        });

        vNextImageViewAngerFactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (angerFactsCounter == 0){
                    angerFactsCounter++;
                    vPreviousImageViewAngerFactActivity.setImageResource(R.drawable.ic_previous);
                    vFactsTextViewAngerFactActivity.setText(angerFactsAngerFactActivity[angerFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vTextToSpeech.stop();
                        vPlayImageViewAngerFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewAngerFactActivity.setVisibility(View.INVISIBLE);
                        mediaPlayer.start();
                    }
                }else if (angerFactsCounter % 7 == 6 && angerFactsCounter != 48){
                    angerFactsCounter++;
                    vFactsTextViewAngerFactActivity.setText(angerFactsAngerFactActivity[angerFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vTextToSpeech.stop();
                        vPlayImageViewAngerFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewAngerFactActivity.setVisibility(View.INVISIBLE);
                        mediaPlayer.start();
                    }
                    if (vInterstitialAdAngerFactActivity.isLoaded()) {
                        vInterstitialAdAngerFactActivity.show();
                    } else {
                        Log.i("TAG", "The interstitial wasn't loaded yet.");
                    }
                }else if (angerFactsCounter % 7 != 6 && angerFactsCounter <= 47){
                    angerFactsCounter++;
                    vFactsTextViewAngerFactActivity.setText(angerFactsAngerFactActivity[angerFactsCounter]);
                    if(vTextToSpeech.isSpeaking()){
                        vTextToSpeech.stop();
                        vPlayImageViewAngerFactActivity.setVisibility(View.VISIBLE);
                        vPauseImageViewAngerFactActivity.setVisibility(View.INVISIBLE);
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

            MusicComponent.BackgroundMusic(AngerFact.this, R.raw.bg_music);

        }

        if(mediaPlayer.isPlaying()){

            mediaPlayer.pause();

        }

        if(MusicComponent.shouldPlayAngerFactActivity){
            MusicComponent.shouldPlayAngerFactActivity = false;
        }

        mediaPlayer.start();
        MusicComponent.onCompletion(mediaPlayer);

        SharedPreferences sharedPreferencesAngerFactActivity = getSharedPreferences("Background", Context.MODE_PRIVATE);
        int vSelectedColorAngerFactActivity = sharedPreferencesAngerFactActivity.getInt("integerSelectedColor", 0);
        ConstraintLayout vConstraintLayoutAngerFactActivity = findViewById(R.id.constraintLayoutAngerFactActivity);
        if (sharedPreferencesAngerFactActivity.getInt("integerSelectedColor", Color.WHITE) == vSelectedColorAngerFactActivity)
        {vConstraintLayoutAngerFactActivity.setBackgroundColor(vSelectedColorAngerFactActivity);}
        else
        {vConstraintLayoutAngerFactActivity.setBackgroundColor(Color.parseColor("#FDD935"));}

    }

    @Override
    protected void onPause() {
        super.onPause();

        if(vTextToSpeech.isSpeaking()){
            vTextToSpeech.stop();
            vPlayImageViewAngerFactActivity.setVisibility(View.VISIBLE);
            vPauseImageViewAngerFactActivity.setVisibility(View.INVISIBLE);

        }

        if (!MusicComponent.shouldPlayAngerFactActivity){

            mediaPlayer.pause();
            MusicComponent.shouldPlayAngerFactListActivity = false;
            MusicComponent.shouldPlayAngerFactActivity = true;

        }
    }

}


