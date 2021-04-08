package com.jinendra.fact;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Timer;
import java.util.TimerTask;

public class FlashScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);

        Timer vTimerFlashScreenActivity = new Timer();
        vTimerFlashScreenActivity.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent vCategoryIntentFlashScreenActivity = new Intent(FlashScreen.this, Category.class);
                startActivity(vCategoryIntentFlashScreenActivity);
                finish();
            }
        }, 1000);



    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferencesFlashScreenActivity = getSharedPreferences("Background", Context.MODE_PRIVATE);
        int vSelectedColor = sharedPreferencesFlashScreenActivity.getInt("integerSelectedColor", 0);
        ConstraintLayout vConstraintLayoutFlashScreenActivity = findViewById(R.id.constraintLayoutFlashScreenActivity);
        if (sharedPreferencesFlashScreenActivity.getInt("integerSelectedColor", Color.WHITE) == vSelectedColor)
        {vConstraintLayoutFlashScreenActivity.setBackgroundColor(vSelectedColor);}
        else
        {vConstraintLayoutFlashScreenActivity.setBackgroundColor(Color.parseColor("#FDD935"));}

    }

}