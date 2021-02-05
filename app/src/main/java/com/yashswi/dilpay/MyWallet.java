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
    com.yashswi.dilpay.models.userDetails userDetails;
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
        userDetails=new userDetails(MyWallet.this);
        total_amount.setText(userDetails.getWallet());
        getWalletAmount(userDetails.getNumber());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    void getWalletAmount(String number){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);

        Call<String> call = api.WalletUpdate(number);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body()!=null || !response.body().isEmpty()){
                    try {
                        JSONObject data=new JSONObject(response.body());
                        if(data.getString("Status").equalsIgnoreCase("SUCCESS")){
                            total_amount.setText(".."+data.getString("WALLET"));
                            commision_amt.setText(".."+data.getString("COMISSION"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}