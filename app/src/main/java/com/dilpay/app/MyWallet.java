package com.dilpay.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyWallet extends AppCompatActivity {
    ImageView back;
    TextView tds_amount, gst_amount, total_amount, commision_amt;
    String walletAmount, commissionAmount;
    AppCompatButton convert,addWalletamount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        back = findViewById(R.id.back);
        tds_amount = findViewById(R.id.tds_amount);
        gst_amount = findViewById(R.id.gst_amount);
        total_amount = findViewById(R.id.total_amount);
        commision_amt = findViewById(R.id.commision_amount);
        convert=findViewById(R.id.buttonConvert);
        addWalletamount=findViewById(R.id.addWalletAmount);

        walletAmount = getIntent().getStringExtra("walletAmt");
        commissionAmount = getIntent().getStringExtra("commissionAmt");

        total_amount.setText(walletAmount);
        commision_amt.setText(commissionAmount);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyWallet.this,Profile.class);
                startActivity(intent);
                finish();
            }
        });

        addWalletamount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyWallet.this, AddWalletAmount.class);
                startActivity(i);
                finish();
            }
        });

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyWallet.this,ConvertRewards.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(MyWallet.this,Profile.class);
        startActivity(intent);
        finish();
    }
}