package com.yashswi.dilpay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.google.android.material.button.MaterialButton;

public class Welcome_screen extends AppCompatActivity {
    KenBurnsView kenBurnsView;
    MaterialButton login, signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        //FINDING VIEW'S
        kenBurnsView = findViewById(R.id.kenburnsview);
        login=findViewById(R.id.login_btn);
        signUp =findViewById(R.id.sign_up);

        //SETTING BACKGROUND IMAGE TO KENBURNS VIEW
        Glide.with(this).load(R.drawable.welcome).into(kenBurnsView);

        //LOGIN BUTTON ACTION
        login.setOnClickListener(v -> {
            Intent i= new Intent(Welcome_screen.this,Login_screen.class);
            startActivity(i);
            finish();
        });

        //SIGNUP BUTTON ACTION
        signUp.setOnClickListener(v -> {
            Intent i = new Intent(Welcome_screen.this,Register_screen.class);
            startActivity(i);
            finish();
        });
    }
}