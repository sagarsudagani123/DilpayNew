package com.yashswi.dilpay.electricity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yashswi.dilpay.R;
import com.yashswi.dilpay.dth.Dth;

public class Electricity extends AppCompatActivity {
    ImageView back;
    LinearLayout tsspdcl,tsnpdcl;
    String airtel1,dish1,tata1,sun1,d2h1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity);
        back=findViewById(R.id.back);
        tsspdcl=findViewById(R.id.linear1);
        tsnpdcl=findViewById(R.id.linear2);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tsspdcl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Electricity.this, Electricity_recharge.class);
                i.putExtra("tsspdcl", "TSSPDCL");
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(Electricity.this, findViewById(R.id.tsspdcl1), "trans1");
                startActivity(i, compat.toBundle());
            }
        });
        tsnpdcl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Electricity.this, Electricity_recharge.class);
                i.putExtra("tsnpdcl", "TSNPDCL");
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(Electricity.this, findViewById(R.id.tsnpdcl1), "trans2");
                startActivity(i, compat.toBundle());
            }
        });
    }
}