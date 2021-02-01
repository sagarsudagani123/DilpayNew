package com.yashswi.dilpay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.bumptech.glide.Glide;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.google.android.material.button.MaterialButton;

public class Welcome_screen extends AppCompatActivity {
    KenBurnsView kenBurnsView;
    MaterialButton login,skip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        kenBurnsView = findViewById(R.id.kenburnsview);
        login=findViewById(R.id.login_btn);
        skip=findViewById(R.id.skip_btn);
        Glide.with(this).load(R.drawable.welcome).into(kenBurnsView);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Welcome_screen.this,Login_screen.class);
                startActivity(i);
                finish();
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Welcome_screen.this,Register_screen.class);
                startActivity(i);
                finish();
            }
        });
    }
}