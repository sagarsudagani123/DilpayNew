package com.yashswi.dilpay.gas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yashswi.dilpay.R;
import com.yashswi.dilpay.dth.Dth;
import com.yashswi.dilpay.dth.Dth_recharge;

public class Gas extends AppCompatActivity {
    ImageView back;
    LinearLayout hp,bharat,indane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas);
        back=findViewById(R.id.back);
        hp=findViewById(R.id.lin1);
        bharat=findViewById(R.id.lin2);
        indane=findViewById(R.id.lin3);
        hp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Gas.this, Gas_booking.class);
                i.putExtra("hp", "HP Gas");
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(Gas.this, findViewById(R.id.hp1), "tran1");
                startActivity(i, compat.toBundle());
            }
        });
        bharat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Gas.this, Gas_booking.class);
                i.putExtra("bharat", "Bharat Gas");
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(Gas.this, findViewById(R.id.bharat1), "tran2");
                startActivity(i, compat.toBundle());
            }
        });
        indane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Gas.this, Gas_booking.class);
                i.putExtra("indane", "Indane Gas");
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(Gas.this, findViewById(R.id.indian1), "tran3");
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