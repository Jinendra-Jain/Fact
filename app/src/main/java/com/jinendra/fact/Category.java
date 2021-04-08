package com.jinendra.fact;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.jinendra.fact.Facts.General.Compelling.CompellingFactList;
import com.jinendra.fact.Facts.General.Engaging.EngagingFactList;
import com.jinendra.fact.Facts.General.Engrossing.EngrossingFactList;
import com.jinendra.fact.Facts.General.Entertaining.EntertainingFactList;
import com.jinendra.fact.Facts.General.Enthralling.EnthrallingFactList;
import com.jinendra.fact.Facts.General.Fascinating.FascinatingFactList;
import com.jinendra.fact.Facts.General.Spellbinding.SpellbindingFactList;
import com.jinendra.fact.Facts.Psychological.Animals.AnimalsFactList;
import com.jinendra.fact.Categories.Anger;
import com.jinendra.fact.Facts.General.Appealing.AppealingFactList;
import com.jinendra.fact.Facts.General.Captivating.CaptivatingFactList;
import com.jinendra.fact.Facts.General.Amusing.AmusingFactList;

import static com.jinendra.fact.MusicComponent.mediaPlayer;
import static com.jinendra.fact.NetworkStatus.isNetworkStatusAvailable;


public class Category extends AppCompatActivity {

    ImageView vSettingsImageViewCategoryActivity;

    AdView vBannerAdViewCategoryActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        if(isNetworkStatusAvailable (getApplicationContext())) {
            Toast.makeText(getApplicationContext(), "Internet connection available try changing background wallpaper!", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection some features of app might not work properly!", Toast.LENGTH_LONG).show();

        }

        vSettingsImageViewCategoryActivity = findViewById(R.id.settingsImageViewCategoryActivity);
        vSettingsImageViewCategoryActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsActivityIntentCategoryActivity = new Intent(Category.this, Settings.class);
                startActivity(settingsActivityIntentCategoryActivity);
                MusicComponent.shouldPlayCategoryActivity = true;
                MusicComponent.shouldPlaySettingsActivity = false;
            }
        });


        Button amusingFactListActivityButtonCategoryActivity = findViewById(R.id.amusingFactListButtonCategoryActivity);
        Button appealingFactListActivityButtonCategoryActivity = findViewById(R.id.appealingFactListButtonCategoryActivity);
        Button captivatingFactListActivityButtonCategoryActivity = findViewById(R.id.captivatingFactListButtonCategoryActivity);
        Button compellingFactListActivityButtonCategoryActivity = findViewById(R.id.compellingFactListButtonCategoryActivity);
        Button engagingFactListActivityButtonCategoryActivity = findViewById(R.id.engagingFactListButtonCategoryActivity);
        Button engrossingFactListActivityButtonCategoryActivity = findViewById(R.id.engrossingFactListButtonCategoryActivity);
        Button entertainingFactListActivityButtonCategoryActivity = findViewById(R.id.entertainingFactListButtonCategoryActivity);
        Button enthrallingFactListActivityButtonCategoryActivity = findViewById(R.id.enthrallingFactListButtonCategoryActivity);
        Button fascinatingFactListActivityButtonCategoryActivity = findViewById(R.id.fascinatingFactListButtonCategoryActivity);
        Button spellbindingFactListActivityButtonCategoryActivity = findViewById(R.id.spellbindingFactListButtonCategoryActivity);
        Button angerActivityButtonCategoryActivity = findViewById(R.id.angerButtonCategoryActivity);
        Button animalsFactListActivityButtonCategoryActivity = findViewById(R.id.animalsFactListButtonCategoryActivity);

        Button[] categoryButtonsCategoryActivity = {amusingFactListActivityButtonCategoryActivity,appealingFactListActivityButtonCategoryActivity,captivatingFactListActivityButtonCategoryActivity,compellingFactListActivityButtonCategoryActivity,engagingFactListActivityButtonCategoryActivity,engrossingFactListActivityButtonCategoryActivity,entertainingFactListActivityButtonCategoryActivity,enthrallingFactListActivityButtonCategoryActivity,fascinatingFactListActivityButtonCategoryActivity,spellbindingFactListActivityButtonCategoryActivity,angerActivityButtonCategoryActivity, animalsFactListActivityButtonCategoryActivity};
        final Class[] categoryClassesCategoryActivity = {AmusingFactList.class, AppealingFactList.class, CaptivatingFactList.class, CompellingFactList.class, EngagingFactList.class, EngrossingFactList.class, EntertainingFactList.class, EnthrallingFactList.class, FascinatingFactList.class, SpellbindingFactList.class, Anger.class, AnimalsFactList.class};

        for(int i = 0; i < categoryButtonsCategoryActivity.length; i++){
            final int x = i;
            categoryButtonsCategoryActivity[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent amusingFactListActivityIntentCategoryActivity = new Intent(Category.this, categoryClassesCategoryActivity[0] );
                    Intent appealingFactListActivityIntentCategoryActivity = new Intent(Category.this, categoryClassesCategoryActivity[1]);
                    Intent captivatingFactListActivityIntentCategoryActivity = new Intent(Category.this, categoryClassesCategoryActivity[2]);
                    Intent compellingFactListActivityIntentCategoryActivity = new Intent(Category.this, categoryClassesCategoryActivity[3]);
                    Intent engagingFactListActivityIntentCategoryActivity = new Intent(Category.this, categoryClassesCategoryActivity[4]);
                    Intent engrossingFactListActivityIntentCategoryActivity = new Intent(Category.this, categoryClassesCategoryActivity[5]);
                    Intent entertainingFactListActivityIntentCategoryActivity = new Intent(Category.this, categoryClassesCategoryActivity[6]);
                    Intent enthrallingFactListActivityIntentCategoryActivity = new Intent(Category.this, categoryClassesCategoryActivity[7]);
                    Intent fascinatingFactListActivityIntentCategoryActivity = new Intent(Category.this, categoryClassesCategoryActivity[8]);
                    Intent spellbindingFactListActivityIntentCategoryActivity = new Intent(Category.this, categoryClassesCategoryActivity[9]);
                    Intent angerActivityIntentCategoryActivity = new Intent(Category.this, categoryClassesCategoryActivity[10]);
                    Intent animalsFactListActivityIntentCategoryActivity = new Intent(Category.this, categoryClassesCategoryActivity[11]);
                    Intent[] categoriesIntentCategoryActivity = {amusingFactListActivityIntentCategoryActivity,appealingFactListActivityIntentCategoryActivity,captivatingFactListActivityIntentCategoryActivity,compellingFactListActivityIntentCategoryActivity,engagingFactListActivityIntentCategoryActivity,engrossingFactListActivityIntentCategoryActivity,entertainingFactListActivityIntentCategoryActivity,enthrallingFactListActivityIntentCategoryActivity,fascinatingFactListActivityIntentCategoryActivity,spellbindingFactListActivityIntentCategoryActivity,angerActivityIntentCategoryActivity, animalsFactListActivityIntentCategoryActivity};
                    categoriesIntentCategoryActivity[x] = new Intent(Category.this, categoryClassesCategoryActivity[x]);

                    startActivity(categoriesIntentCategoryActivity[x]);
                    MusicComponent.shouldPlayCategoryActivity = true;
                    MusicComponent.shouldPlayAmusingFactListActivity = false;
                    MusicComponent.shouldPlayAppealingFactListActivity =false;
                    MusicComponent.shouldPlayCaptivatingFactListActivity = false;
                    MusicComponent.shouldPlayCompellingFactListActivity = false;
                    MusicComponent.shouldPlayEngagingFactListActivity = false;
                    MusicComponent.shouldPlayEngrossingFactListActivity = false;
                    MusicComponent.shouldPlayEntertainingFactListActivity = false;
                    MusicComponent.shouldPlayEnthrallingFactListActivity = false;
                    MusicComponent.shouldPlayFascinatingFactListActivity = false;
                    MusicComponent.shouldPlaySpellbindingFactListActivity = false;
                    MusicComponent.shouldPlayAngerActivity = false;
                    MusicComponent.shouldPlayAnimalsFactListActivity = false;

                }
            });

        }

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        vBannerAdViewCategoryActivity = findViewById(R.id.bannerAdViewCategoryActivity);
        AdRequest vBannerAdRequestCategoryActivity = new AdRequest.Builder().build();
        vBannerAdViewCategoryActivity.loadAd(vBannerAdRequestCategoryActivity);



    }



    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferencesCategoryActivity = getSharedPreferences("Background", Context.MODE_PRIVATE);
        int vSelectedColor = sharedPreferencesCategoryActivity.getInt("integerSelectedColor", 0);
        ConstraintLayout vConstraintLayoutCategoryActivity = findViewById(R.id.constraintLayoutCategoryActivity);
        if (sharedPreferencesCategoryActivity.getInt("integerSelectedColor", Color.WHITE) == vSelectedColor)
        {vConstraintLayoutCategoryActivity.setBackgroundColor(vSelectedColor);}
        else
        {vConstraintLayoutCategoryActivity.setBackgroundColor(Color.parseColor("#FDD935"));}

        if(mediaPlayer == null) {
            MusicComponent.BackgroundMusic(Category.this, R.raw.bg_music);
        }
        mediaPlayer.start();
        MusicComponent.onCompletion(mediaPlayer);

    }

    @Override
    protected void onPause() {
        super.onPause();

        if (!MusicComponent.shouldPlayCategoryActivity) {

            mediaPlayer.pause();
        }
    }

}