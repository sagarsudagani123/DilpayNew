package com.dilpay.app.Security;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
//import com.goodiebag.pinview.Pinview;
import com.dilpay.app.Api_interface.Api_interface;
import com.dilpay.app.EditProfile;
import com.dilpay.app.R;
import com.dilpay.app.mobile.Mobile_recharge_successfull;
import com.dilpay.app.models.userDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SecurityPin extends AppCompatActivity {
    TextView generate_pin, errorMessage;
    PinEntryEditText pinEntryEditText;
    userDetails userDetails;
    String pinCode;
    ImageView back;
    String FromService;
    String amount, number, operator_code, circle_code;
    RelativeLayout progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_pin);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        back = findViewById(R.id.back);
        userDetails = new userDetails(SecurityPin.this);
        generate_pin = findViewById(R.id.generate_pin);
        errorMessage = findViewById(R.id.errorMessage);
        pinEntryEditText = findViewById(R.id.pin);
        progress = findViewById(R.id.progress);

        pinEntryEditText.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        //===============================================================================
        amount = getIntent().getStringExtra("amount");
        number = getIntent().getStringExtra("number");
        operator_code = getIntent().getStringExtra("operatorCode");
        circle_code = getIntent().getStringExtra("circleCode");
//===============================================================================

        FromService = getIntent().getStringExtra("category");

        pinCode = userDetails.getSPin();
        if (pinCode.equalsIgnoreCase("") || pinCode.equalsIgnoreCase("null")) {
            Toast.makeText(SecurityPin.this, "Pin not generated.", Toast.LENGTH_SHORT).show();
            errorMessage.setText("Pin not generated. \nUpdate details in your profile and generate pin.");
        } else {
            errorMessage.setText("");
        }

        pinEntryEditText.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
            @Override
            public void onPinEntered(CharSequence str) {
                String userEnteredPin = str.toString();
                if (userEnteredPin.equalsIgnoreCase(pinCode)) {
                    progress.setVisibility(View.VISIBLE);
                    getResponse(number, amount, operator_code, circle_code);
//                    if(FromService.equalsIgnoreCase("Mobile")){
//                        Intent intent = new Intent(SecurityPin.this, Mobile.class);
//                        startActivity(intent);
//                        finish();
//                    }else if(FromService.equalsIgnoreCase("dth")){
//                        Intent intent = new Intent(SecurityPin.this, Dth_screen.class);
//                        startActivity(intent);
//                        finish();
//                    }else if(FromService.equalsIgnoreCase("datacard")){
//                        Intent intent = new Intent(SecurityPin.this, Datacard_screen.class);
//                        startActivity(intent);
//                        finish();
//                    }else if(FromService.equalsIgnoreCase("postpaid")){
//                        Intent intent = new Intent(SecurityPin.this, Postpaid_screen.class);
//                        startActivity(intent);
//                        finish();
//                    }else if(FromService.equalsIgnoreCase("electricity")){
//                        Intent intent = new Intent(SecurityPin.this, Electricity_screen.class);
//                        startActivity(intent);
//                        finish();
//                    }else if(FromService.equalsIgnoreCase("gas")){
//                        Intent intent = new Intent(SecurityPin.this, Gas_screen.class);
//                        startActivity(intent);
//                        finish();
//                    }else if(FromService.equalsIgnoreCase("Money Transfer")){
////                        Intent intent = new Intent(SecurityPin.this, .class);
////                        startActivity(intent);
////                        finish();
//                    }
                } else {
                    Toast.makeText(SecurityPin.this, "Invalid Pin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        generate_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new userDetails(SecurityPin.this).getDOB().equalsIgnoreCase("null") ||
                        new userDetails(SecurityPin.this).getDOB().equalsIgnoreCase("")) {
                    Toast.makeText(SecurityPin.this, "To generate pin, Please update your details in your profile", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SecurityPin.this, EditProfile.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(SecurityPin.this, SecurityCheck.class);
                    startActivity(intent);
                }
                finish();
            }
        });
    }

    private void getResponse(String number, String amount, String operator_code, String circle_code) {
        JSONObject dataObj = new JSONObject();
        try {
            dataObj.put("username", new userDetails(SecurityPin.this).getNumber());
            dataObj.put("number", number);
            dataObj.put("operatorCode", operator_code);
            dataObj.put("circleCode", circle_code);
            dataObj.put("amount", amount);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("RechargeTest", dataObj.toString());
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .callTimeout(2, TimeUnit.MINUTES)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .client(httpClient.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);
        Call<String> call = api.MobileRecharge(dataObj.toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("StatusResponse", "success  " + response.body());
                progress.setVisibility(View.GONE);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                try {
                    JSONObject obj = new JSONObject(response.body());
                    String status = obj.getString("Status");
                    if (status.equalsIgnoreCase("True")) {
                        String Service = obj.getString("Service");
                        String txid = obj.getString("txid");
                        String rechargeStatus = obj.getString("status");
                        String rechargeNumber = obj.getString("number");
                        String amount = obj.getString("amount");
                        String orderid = obj.getString("orderid");

                        if (!(rechargeStatus.equalsIgnoreCase("null"))) {
                            Intent i = new Intent(SecurityPin.this, Mobile_recharge_successfull.class);
                            i.putExtra("status", rechargeStatus);
                            i.putExtra("txid", txid);
                            i.putExtra("opid", obj.getString("opid"));
                            i.putExtra("number", rechargeNumber);
                            i.putExtra("amount", amount);
                            i.putExtra("orderid", orderid);
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(SecurityPin.this, "Enter valid details!" + response.body(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SecurityPin.this, obj.getString("Data"), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (Exception e) {
                    Log.e("StatusResponse", e.toString());
                    Toast.makeText(SecurityPin.this, e.toString(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("StatusResponse", "failed  " + t.toString());
                progress.setVisibility(View.GONE);
                String message = "";
                if (t instanceof UnknownHostException) {
                    message = "No internet connection!";
                } else {
                    message = "Something went wrong! try again";
                }
                Toast.makeText(SecurityPin.this, message + "" + t.toString(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}