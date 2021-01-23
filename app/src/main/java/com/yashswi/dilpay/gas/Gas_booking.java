package com.yashswi.dilpay.gas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yashswi.dilpay.R;

public class Gas_booking extends AppCompatActivity {
    ImageView back;
    TextView name,name1,name2;
    AppCompatButton confirm;
    String hp,bharat,indane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas_booking);
        back=findViewById(R.id.back);
        confirm=findViewById(R.id.confirm);
        name=findViewById(R.id.provide);
        name1=findViewById(R.id.provide1);
        name2=findViewById(R.id.provide2);
        Intent intent =getIntent();
        hp=intent.getStringExtra("hp");
        bharat=intent.getStringExtra("bharat");
        indane=intent.getStringExtra("indane");
        name.setText(hp);
        name1.setText(bharat);
        name2.setText(indane);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Gas_booking.this, Gas_booking_screen.class);
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