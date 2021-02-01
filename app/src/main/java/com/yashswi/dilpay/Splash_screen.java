package com.yashswi.dilpay;

import androidx.appcompat.app.AppCompatActivity;

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
        userDetails=new userDetails(Splash_screen.this);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if(userDetails.getLoged()){
                    intent=new Intent(Splash_screen.this,Home_screen.class);
                }
                else{
                    intent = new Intent(Splash_screen.this, Welcome_screen.class);
                }
                startActivity(intent);
                finish();
//                }
            }
        }, 2000);
    }
}