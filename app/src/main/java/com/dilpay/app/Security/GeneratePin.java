package com.dilpay.app.Security;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
//import com.goodiebag.pinview.Pinview;
import com.dilpay.app.Api_interface.Api_interface;
import com.dilpay.app.R;
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

public class GeneratePin extends AppCompatActivity {
    PinEntryEditText newPin, verifyPin;
    String pinOne, pinTwo;
    AppCompatButton submit;
    ImageView back;
    userDetails userDetails;
    RelativeLayout progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_pin);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        back = findViewById(R.id.back);
        newPin = findViewById(R.id.newPin);
        verifyPin = findViewById(R.id.verifyPin);
        submit = findViewById(R.id.submit);
        userDetails = new userDetails(GeneratePin.this);
        progress = findViewById(R.id.progress_layout);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        newPin.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
            @Override
            public void onPinEntered(CharSequence str) {
                pinOne = str.toString();

            }
        });
        verifyPin.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
            @Override
            public void onPinEntered(CharSequence str) {
                pinTwo = str.toString();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pinOne.equalsIgnoreCase(pinTwo)) {
                    progress.setVisibility(View.VISIBLE);
                    updatePin(pinOne);
                } else {
                    Toast.makeText(GeneratePin.this, "Pin Mismatch", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void updatePin(String pinOne) {
        JSONObject dataObj = new JSONObject();
        try {
            dataObj.put("username", userDetails.getNumber());
            dataObj.put("SecurityPin", pinOne);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("TEstPIN", dataObj.toString());
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
        Call<String> call = api.updatePin(dataObj.toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                progress.setVisibility(View.GONE);
                try {
                    JSONObject dataObj = new JSONObject(response.body());
                    if (dataObj.getString("Status").equalsIgnoreCase("True")) {
                        userDetails.setSPin(pinOne);
                        finish();
                    }
                    Toast.makeText(GeneratePin.this, dataObj.getString("Message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(GeneratePin.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progress.setVisibility(View.GONE);
                String message = "";
                if (t instanceof UnknownHostException) {
                    message = "No internet connection";
                } else {
                    message = "Something went wrong! Please try again!!";

                }
                Toast.makeText(GeneratePin.this, message + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}