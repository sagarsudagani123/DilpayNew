package com.yashswi.dilpay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yashswi.dilpay.Api_interface.Api_interface;
import com.yashswi.dilpay.models.userDetails;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MyWallet extends AppCompatActivity {
  ImageView back;
  TextView tds_amount,gst_amount,total_amount,commision_amt;
    String walletAmount,commissionAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        back=findViewById(R.id.back);
        tds_amount=findViewById(R.id.tds_amount);
        gst_amount=findViewById(R.id.gst_amount);
        total_amount=findViewById(R.id.total_amount);
        commision_amt=findViewById(R.id.commision_amount);

        walletAmount=getIntent().getStringExtra("walletAmt");
        commissionAmount=getIntent().getStringExtra("commissionAmt");

        total_amount.setText(walletAmount);
        commision_amt.setText(commissionAmount);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}