package com.dilpay.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.KeyguardManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.widget.Toast;

import com.dilpay.app.models.userDetails;

public class Splash_screen extends AppCompatActivity {
    userDetails userDetails;
    private static final int LOCK_REQUEST_CODE = 221;
    private static final int SECURITY_SETTING_REQUEST_CODE = 233;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        userDetails = new userDetails(Splash_screen.this);

        //HANDLER TO DELAY NEXT ACTION FOR 2 SEC
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent;
            if (userDetails.getLoged()) {
                if(!userDetails.getPhoneLockEnabled()){
                    intent = new Intent(Splash_screen.this, Home_screen.class);
                    startActivity(intent);
                }else{
                    intent = new Intent(Splash_screen.this, KeyGaurd.class);
                }
            } else {
                intent = new Intent(Splash_screen.this, Welcome_screen.class);
            }
            startActivity(intent);
            finish();
        }, 2000);
    }
}