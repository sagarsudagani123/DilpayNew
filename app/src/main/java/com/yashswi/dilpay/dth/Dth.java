package com.yashswi.dilpay.dth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yashswi.dilpay.R;

public class Dth extends AppCompatActivity {
    ImageView back;
    LinearLayout airtel, dish, tata, sun, d2h;
    String airtel1, dish1, tata1, sun1, d2h1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dth);
        back = findViewById(R.id.back);
        airtel = findViewById(R.id.lin1);
        dish = findViewById(R.id.lin2);
        tata = findViewById(R.id.lin3);
        sun = findViewById(R.id.lin4);
        d2h = findViewById(R.id.lin5);

        airtel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dth.this, Dth_recharge.class);
                i.putExtra("airtel", "Airtel Tv");
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(Dth.this, findViewById(R.id.airtel1), "trans1");
                startActivity(i, compat.toBundle());
            }
        });
        dish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dth.this, Dth_recharge.class);
                i.putExtra("dish", "Dish Tv");
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(Dth.this, findViewById(R.id.dish1), "trans2");
                startActivity(i, compat.toBundle());
            }
        });
        tata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dth.this, Dth_recharge.class);
                i.putExtra("tata", "Tata Sky");
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(Dth.this, findViewById(R.id.tata1), "trans3");
                startActivity(i, compat.toBundle());
            }
        });
        sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dth.this, Dth_recharge.class);
                i.putExtra("sun", "Sun Direct");
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(Dth.this, findViewById(R.id.sun1), "trans4");
                startActivity(i, compat.toBundle());
            }
        });
        d2h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dth.this, Dth_recharge.class);
                i.putExtra("d2h", "D2H");
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(Dth.this, findViewById(R.id.d2h1), "trans5");
                startActivity(i, compat.toBundle());
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