package com.yashswi.dilpay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.KeyguardManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.widget.Toast;

import com.yashswi.dilpay.models.userDetails;

public class Splash_screen extends AppCompatActivity {
    userDetails userDetails;
    private static final int LOCK_REQUEST_CODE = 221;
    private static final int SECURITY_SETTING_REQUEST_CODE = 233;

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
                intent = new Intent(Splash_screen.this, KeyGaurd.class);
                startActivity(intent);
//                authenticateApp();
            } else {
                intent = new Intent(Splash_screen.this, Welcome_screen.class);
                startActivity(intent);
            }

            finish();
        }, 2000);
    }
    //method to authenticate app
    private void authenticateApp() {
        //Get the instance of KeyGuardManager
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

        //Check if the device version is greater than or equal to Lollipop(21)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //Create an intent to open device screen lock screen to authenticate
            //Pass the Screen Lock screen Title and Description
            Intent i = keyguardManager.createConfirmDeviceCredentialIntent(getResources().getString(R.string.unlock), getResources().getString(R.string.confirm_pattern));
            try {
                //Start activity for result
                startActivityForResult(i, LOCK_REQUEST_CODE);
            } catch (Exception e) {

                //If some exception occurs means Screen lock is not set up please set screen lock
                //Open Security screen directly to enable patter lock
                Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
                try {

                    //Start activity for result
                    startActivityForResult(intent, SECURITY_SETTING_REQUEST_CODE);
                } catch (Exception ex) {

                    //If app is unable to find any Security settings then user has to set screen lock manually
//                    textView.setText(getResources().getString(R.string.setting_label));
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case LOCK_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    //If screen lock authentication is success update text
//                    textView.setText(getResources().getString(R.string.unlock_success));
                    Intent intent= new Intent(Splash_screen.this, Home_screen.class);
                    startActivity(intent);
                    finish();
                } else {
                    //If screen lock authentication is failed update text
                    Toast.makeText(Splash_screen.this,R.string.unlock_failed,Toast.LENGTH_SHORT).show();
                    // textView.setText(getResources().getString(R.string.unlock_failed));
                }
                break;
            case SECURITY_SETTING_REQUEST_CODE:
                //When user is enabled Security settings then we don't get any kind of RESULT_OK
                //So we need to check whether device has enabled screen lock or not
                if (isDeviceSecure()) {
                    //If screen lock enabled show toast and start intent to authenticate user
                    Toast.makeText(this, getResources().getString(R.string.device_is_secure), Toast.LENGTH_SHORT).show();
                    authenticateApp();
                } else {
                    //If screen lock is not enabled just update text
                    Toast.makeText(this, getResources().getString(R.string.security_device_cancelled), Toast.LENGTH_SHORT).show();
                    //textView.setText(getResources().getString(R.string.security_device_cancelled));
                }

                break;
        }
    }
    private boolean isDeviceSecure() {
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

        //this method only work whose api level is greater than or equal to Jelly_Bean (16)
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && keyguardManager.isKeyguardSecure();

        //You can also use keyguardManager.isDeviceSecure(); but it requires API Level 23

    }
}