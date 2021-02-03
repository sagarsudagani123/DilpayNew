package com.yashswi.dilpay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;

public class Upgrade_membership extends AppCompatActivity {
    AppCompatButton skip,submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_membership);

        //FINDING VIEW'S
        skip=findViewById(R.id.sign_up);
        submit=findViewById(R.id.submit);

        submit.setOnClickListener(v -> {
            //RETROFIT CODE FOR MEMBERSHIP UPGRADE
        });
        skip.setOnClickListener(v -> finish());
    }
}