package com.dilpay.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.KeyguardManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class KeyGaurd extends AppCompatActivity {
    private static final int LOCK_REQUEST_CODE = 221;
    private static final int SECURITY_SETTING_REQUEST_CODE = 233;
    RelativeLayout progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_gaurd);

        progress=findViewById(R.id.progress_layout);
        authenticateApp();
    }

    private void authenticateApp() {
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

        //Check if the device version is greater than or equal to Lollipop(21)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //Create an intent to open device screen lock screen to authenticate
            //Pass the Screen Lock screen Title and Description
            Intent i = keyguardManager.createConfirmDeviceCredentialIntent(getResources().getString(R.string.unlock), getResources().getString(R.string.confirm_pattern));
            try {
                //Start activity for result
                startActivityForResult(i, LOCK_REQUEST_CODE);
                progress.setVisibility(View.VISIBLE);
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
        else{
            Intent intent= new Intent(KeyGaurd.this, Home_screen.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data!=null) {
            if (((data.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION) == 0) && ((data.getFlags() & Intent.FLAG_GRANT_WRITE_URI_PERMISSION) == 0)) {
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case LOCK_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    //If screen lock authentication is success update text
//                    textView.setText(getResources().getString(R.string.unlock_success));
                    Intent intent= new Intent(KeyGaurd.this, Home_screen.class);
                    startActivity(intent);
                }
                else
                 {
                    progress.setVisibility(View.GONE);
                    Toast.makeText(this, getResources().getString(R.string.unlock_failed), Toast.LENGTH_SHORT).show();
                 }
                finish();
                break;
            case SECURITY_SETTING_REQUEST_CODE:
                if (isDeviceSecure()) {
                    Toast.makeText(this, getResources().getString(R.string.device_is_secure), Toast.LENGTH_SHORT).show();
                    authenticateApp();
                } else {
                    Toast.makeText(this, getResources().getString(R.string.security_device_cancelled), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private boolean isDeviceSecure() {
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

        //this method only work whose api level is greater than or equal to Jelly_Bean (16)
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && keyguardManager.isKeyguardSecure();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}