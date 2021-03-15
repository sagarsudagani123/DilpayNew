package com.yashswi.dilpay.payment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.yashswi.dilpay.Api_interface.Api_interface;
import com.yashswi.dilpay.Home_screen;
import com.yashswi.dilpay.Profile;
import com.yashswi.dilpay.R;
import com.yashswi.dilpay.Upgrade_membership;
import com.yashswi.dilpay.models.userDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static retrofit2.converter.gson.GsonConverterFactory.create;


public class paymentStart extends AppCompatActivity {
    String status1, paymentMode1, orderId1, txTime1, referenceId1, txMsg1, signature1, orderAmount1;

    TextView status, paymentMode, orderId, pnrNumber, refId, txMsg, sign, orderAmount,success;
    ImageView success1, failure,back;
    ProgressBar ProgressRecharge;
    com.yashswi.dilpay.models.userDetails userDetails;
    String FromPage, RefCode;
    JSONObject finalData;
    MediaPlayer mediaPlayerSuccess,mediaPlayerFailure;
    String jsonData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_start);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        status = findViewById(R.id.statusSub);
        paymentMode = findViewById(R.id.paymentmode1);
        orderId = findViewById(R.id.order1);
        pnrNumber = findViewById(R.id.pnrNumber);
        refId = findViewById(R.id.refrenceID);
        txMsg = findViewById(R.id.success);
//        sign=findViewById(R.id.status);
        orderAmount = findViewById(R.id.amount1);
        success1 = findViewById(R.id.success1);
        failure = findViewById(R.id.failure);
        back=findViewById(R.id.back);
        ProgressRecharge = findViewById(R.id.ProgressRecharge);
        mediaPlayerSuccess=MediaPlayer.create(this,R.raw.success_tone1);
        mediaPlayerFailure=MediaPlayer.create(this,R.raw.success_tone);

        status1 = getIntent().getStringExtra("status");
        paymentMode1 = getIntent().getStringExtra("paymentMode");
        orderId1 = getIntent().getStringExtra("orderId");
//        txTime1 = getIntent().getStringExtra("txTime");
        referenceId1 = getIntent().getStringExtra("referenceId");
        txMsg1 = getIntent().getStringExtra("txMsg");
//        signature1 = getIntent().getStringExtra("signature");
        orderAmount1 = getIntent().getStringExtra("orderAmount");
        FromPage = getIntent().getStringExtra("FromPage");
        RefCode = getIntent().getStringExtra("RefCode");
        Log.e("confirmTest",status1);
        Log.e("confirmTest",paymentMode1);
        Log.e("confirmTest",orderId1);
//        Log.e("confirmTest",txTime1);
        Log.e("confirmTest",referenceId1);
        Log.e("confirmTest",txMsg1);
//        Log.e("confirmTest",signature1);
        Log.e("confirmTest",orderAmount1);
        Log.e("confirmTest",FromPage);
//        Log.e("confirmTest",RefCode);

        finalData = new JSONObject();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (FromPage.equalsIgnoreCase("BusConfirm")) {
            if (status1.equalsIgnoreCase("SUCCESS")) {

                try {
                    finalData.put("Status", status1);
                    finalData.put("Mode", paymentMode1);
                    finalData.put("Amount", orderAmount1);
                    finalData.put("OrderID", orderId1);
                    finalData.put("RefID", referenceId1);
                    finalData.put("TxTime", txTime1);
                    finalData.put("Message", txMsg1);
                    finalData.put("Signature", signature1);
                } catch (Exception e) {
                }
                confirmTicket(finalData.toString());
            } else if (status1.equalsIgnoreCase("FAILED")) {
                try {
                    finalData.put("Status", status1);
                    finalData.put("Message", txMsg1);
                } catch (Exception e) {
                }
                confirmTicket(finalData.toString());
            } else {
                try {
                    finalData.put("Status", status1);
                    finalData.put("Mode", paymentMode1);
                    finalData.put("Amount", orderAmount1);
                    finalData.put("OrderID", orderId1);
                    finalData.put("RefID", referenceId1);
                    finalData.put("TxTime", txTime1);
                    finalData.put("Message", txMsg1);
                    finalData.put("Signature", signature1);
                } catch (Exception e) {
                    Toast.makeText(paymentStart.this, "PENDING PAYMENT" + finalData.toString(), Toast.LENGTH_LONG).show();
                }
                confirmTicket(finalData.toString());
            }
        }else if(FromPage.equalsIgnoreCase("AddWalletAmount")){

            if (status1.equalsIgnoreCase("SUCCESS")) {
                try {

                    finalData.put("username",new userDetails(paymentStart.this).getNumber());
                    finalData.put("Status", status1);
                    finalData.put("PaymentFor", "Wallet Amount Added");
                    finalData.put("Mode", paymentMode1);
                    finalData.put("Amount", orderAmount1);
                    finalData.put("OrderID", orderId1);
                    finalData.put("RefID", referenceId1);
//                    finalData.put("TxTime", txTime1);
                    finalData.put("Message", txMsg1);
                    finalData.put("Signature", signature1);
                    updateWalletAmount(finalData.toString());
                } catch (Exception e) {
                }
            } else {
                Toast.makeText(paymentStart.this, "Payment Failed!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        else {
            if (status1.equalsIgnoreCase("SUCCESS")) {
                try {
                    finalData.put("Status", status1);
                    finalData.put("PaymentFor", "Upgrade Membership");
                    finalData.put("Mode", paymentMode1);
                    finalData.put("Amount", orderAmount1);
                    finalData.put("OrderID", orderId1);
                    finalData.put("RefID", referenceId1);
                    finalData.put("TxTime", txTime1);
                    finalData.put("Message", txMsg1);
                    finalData.put("Signature", signature1);
                    String number = new userDetails(paymentStart.this).getNumber();
                    upgradeMember(number, RefCode);
                } catch (Exception e) {
                }
            } else {
                Toast.makeText(paymentStart.this, "Payment Failed!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
        Log.e("finalDetails", finalData.toString());
    }

    private void updateWalletAmount(String data) {
        Log.e("WalletAmount",data);
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
        Api_interface api = retrofit.create(Api_interface.class);
        Call<String> call = api.addWalletAmount(data);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("WalletAmount",response.body());
                try {
                    JSONObject responseObj=new JSONObject(response.body());
                    Toast.makeText(paymentStart.this,responseObj.getString("Data"),Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(paymentStart.this,e.toString(),Toast.LENGTH_SHORT).show();
                }
                finish();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                String message = "";
                if (t instanceof UnknownHostException) {
                    message = "No internet connection";
                } else {
                    message = "Something went wrong! Please try again";
                }
                finish();
                Toast.makeText(paymentStart.this, message + t.toString(), Toast.LENGTH_SHORT).show();
//                progress.setVisibility(View.GONE);
            }
        });
    }

    private void confirmTicket(String finalData) {

        Log.e("confirmTest",finalData);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .callTimeout(2, TimeUnit.MINUTES)
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(90, TimeUnit.SECONDS)
                .writeTimeout(90, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .client(httpClient.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);

        Call<String> call = api.confirmTicket(finalData);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body()!=null){
                    try {
                        JSONObject dataObj=new JSONObject(response.body());
                        if(dataObj.getString("Status").equalsIgnoreCase("SUCCESS")){
                            ProgressRecharge.setVisibility(View.GONE);
                            success1.setVisibility(View.VISIBLE);
                            txMsg.setText(dataObj.getString("Message"));
                            paymentMode.setText(paymentMode1);
                            pnrNumber.setText(dataObj.getString("PNR"));
                            orderAmount.setText(orderAmount1);
                            orderId.setText(orderId1);
                            refId.setText(referenceId1);
                            status.setText(dataObj.getString("Status"));
                            mediaPlayerSuccess.start();
                        }
                        else{
                            txMsg.setText(dataObj.getString("Message"));
                            failure.setVisibility(View.VISIBLE);
                            mediaPlayerFailure.start();
//                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(paymentStart.this, "Failed", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                String message = "";
                if (t instanceof UnknownHostException) {
                    message = "No internet connection";
                } else {
                    message = "Something went wrong! try again ";

                }
                Toast.makeText(paymentStart.this, message + t.toString(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void upgradeMember(String userNumber, String refCode) {
        Log.e("upgradePAyment", finalData.toString());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);

        Call<String> call = api.upgradeUser(userNumber, refCode);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                progress.setVisibility(View.GONE);
                String message = "";
                try {
//                    Toast.makeText(paymentStart.this, response.body(), Toast.LENGTH_SHORT).show();
                    JSONObject object = new JSONObject(response.body());
                    message = object.getString("Message");
                    if (object.getString("Status").equalsIgnoreCase("True")) {
                        userDetails = new userDetails(paymentStart.this);
                        userDetails.setMembership("Paid");
                        Intent intent = new Intent(paymentStart.this, Profile.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        finish();
                    }
                    Toast.makeText(paymentStart.this, message, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(paymentStart.this, e.toString(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
//                progress.setVisibility(View.GONE);
                Toast.makeText(paymentStart.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(paymentStart.this, Home_screen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}