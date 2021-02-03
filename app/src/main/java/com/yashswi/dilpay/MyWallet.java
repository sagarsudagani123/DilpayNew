package com.yashswi.dilpay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yashswi.dilpay.models.userDetails;

public class MyWallet extends AppCompatActivity {
  ImageView back;
  TextView tds_amount,gst_amount,total_amount;
    com.yashswi.dilpay.models.userDetails userDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        back=findViewById(R.id.back);
        tds_amount=findViewById(R.id.tds_amount);
        gst_amount=findViewById(R.id.gst_amount);
        total_amount=findViewById(R.id.total_amount);
        userDetails=new userDetails(MyWallet.this);
        total_amount.setText(userDetails.getWallet());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}