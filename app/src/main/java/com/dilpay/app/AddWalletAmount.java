package com.dilpay.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.dilpay.app.Api_interface.Api_interface;
import com.dilpay.app.models.userDetails;
import com.dilpay.app.payment.SelectPayment;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AddWalletAmount extends AppCompatActivity {
    TextInputEditText amountEdit;
    AppCompatButton submit, cancle;
    RelativeLayout progress;
    ImageView back;
    String addAmount,orderID,token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wallet_amount);

        amountEdit=findViewById(R.id.amountAddWallet);
        submit=findViewById(R.id.buttonSubmit);
        cancle =findViewById(R.id.buttonCancel);
        progress=findViewById(R.id.progress);
        back=findViewById(R.id.back);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAmount=amountEdit.getText().toString();
            if (!addAmount.equalsIgnoreCase("")){
                int amountAdd=Integer.parseInt(addAmount);
                if(amountAdd>=400){
                    progress.setVisibility(View.VISIBLE);
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    generateToken(addAmount);
                }else{
                    Toast.makeText(AddWalletAmount.this,"Minimum amount 500",Toast.LENGTH_SHORT).show();
                }
            }else{
                progress.setVisibility(View.GONE);
                Toast.makeText(AddWalletAmount.this,"Enter valid amount",Toast.LENGTH_SHORT).show();
            }
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void generateToken(String amount) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .callTimeout(2, TimeUnit.MINUTES)
                .connectTimeout(90, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .client(httpClient.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Timestamp stamp=new Timestamp(System.currentTimeMillis());
        int year = Calendar.getInstance().get(Calendar.YEAR);
        orderID="DILPAY"+year+stamp.getTime();
        Api_interface api = retrofit.create(Api_interface.class);
        Call<String> call = api.generateToken(orderID, amount);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body() != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());
                        if (jsonObject.getString("status").equalsIgnoreCase("OK")) {
                            token = jsonObject.getString("cftoken");
                            Intent intent = new Intent(AddWalletAmount.this, SelectPayment.class);
                            intent.putExtra("FromPage", "AddWalletAmount");
                            intent.putExtra("orderID", orderID);
                            intent.putExtra("Token", token);
                            intent.putExtra("Amount", amount);
                            intent.putExtra("Name", "Add Wallet Amount");
                            intent.putExtra("Number", new userDetails(AddWalletAmount.this).getNumber());
                            intent.putExtra("Number","9121382727");
                            intent.putExtra("RefCode", "");
                            startActivity(intent);
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(AddWalletAmount.this,e.toString(),Toast.LENGTH_SHORT).show();
                        progress.setVisibility(View.GONE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                String message = "";
                if (t instanceof UnknownHostException) {
                    message = "No internet connection!";
                } else {
                    message = "Something went wrong! try again";
                }
                finish();
                Toast.makeText(AddWalletAmount.this, message + ""+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}