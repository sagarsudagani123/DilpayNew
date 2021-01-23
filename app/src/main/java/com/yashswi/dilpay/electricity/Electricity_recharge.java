package com.yashswi.dilpay.electricity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yashswi.dilpay.R;

public class Electricity_recharge extends AppCompatActivity {
    ImageView back;
    TextView name,name1;
    AppCompatButton confirm;
    String tsspdcl,tsnpdcl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity_recharge);
        back=findViewById(R.id.back);
        confirm=findViewById(R.id.confirm);
        name=findViewById(R.id.provider);
        name1=findViewById(R.id.provider1);
        Intent intent =getIntent();
        tsspdcl=intent.getStringExtra("tsspdcl");
        tsnpdcl=intent.getStringExtra("tsnpdcl");
        name.setText(tsspdcl);
        name1.setText(tsnpdcl);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Electricity_recharge.this, Electricity_recharge_screen.class);
                startActivity(i);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}