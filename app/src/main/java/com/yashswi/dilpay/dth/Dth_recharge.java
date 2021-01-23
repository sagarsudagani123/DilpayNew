package com.yashswi.dilpay.dth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yashswi.dilpay.R;

public class Dth_recharge extends AppCompatActivity {
    ImageView back;
    TextView name,name1,name2,name3,name4;
    AppCompatButton confirm;
    String airtel1,dish1,tata1,sun1,d2h1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dth_recharge);
        back=findViewById(R.id.back);
        confirm=findViewById(R.id.confirm);
        name=findViewById(R.id.provider);
        name1=findViewById(R.id.provider1);
        name2=findViewById(R.id.provider3);
        name3=findViewById(R.id.provider4);
        name4=findViewById(R.id.provider5);
        Intent intent =getIntent();
        airtel1=intent.getStringExtra("airtel");
        dish1=intent.getStringExtra("dish");
        tata1=intent.getStringExtra("tata");
        sun1=intent.getStringExtra("sun");
        d2h1=intent.getStringExtra("d2h");
        name1.setText(dish1);
        name2.setText(tata1);
        name3.setText(sun1);
        name4.setText(d2h1);
        name.setText(airtel1);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dth_recharge.this, Dth_recharge_screen.class);
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