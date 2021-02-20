package com.yashswi.dilpay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.yashswi.dilpay.models.userDetails;

public class Splash_screen extends AppCompatActivity {
    userDetails userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //GETTING USER DETAILS IF USER IS LOGED OR NOT FROM SHAREDPREFERENCE
        userDetails = new userDetails(Splash_screen.this);

        //HANDLER TO DELAY NEXT ACTION FOR 2 SEC
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent;
            //IF USER IS ALREADY LOGED TAKE TO HOME SCREEN ELSE TAKE TO LOGIN
            if (userDetails.getLoged()) {
                intent = new Intent(Splash_screen.this, Home_screen.class);
            } else {
                intent = new Intent(Splash_screen.this, Welcome_screen.class);
            }
            startActivity(intent);
            finish();
        }, 2000);
    }
}