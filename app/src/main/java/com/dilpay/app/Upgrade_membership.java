  package com.dilpay.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dilpay.app.utils.RetroFitClient;
import com.google.android.material.textfield.TextInputEditText;
import com.dilpay.app.Api_interface.Api_interface;
import com.dilpay.app.payment.SelectPayment;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Upgrade_membership extends AppCompatActivity {
    AppCompatButton skip, upgrade;
    TextInputEditText e_refferalcode;
    String userNumber;
    RelativeLayout progress;
    com.dilpay.app.models.userDetails userDetails;
    String orderID = "", token = "";
    public static final Pattern numberPattern = Pattern.compile("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}", Pattern.CASE_INSENSITIVE);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_membership);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //FINDING VIEW'S
        skip = findViewById(R.id.sign_up);
        upgrade = findViewById(R.id.upgrade);
        e_refferalcode = findViewById(R.id.e_refferalcode);
        progress = findViewById(R.id.progress_layout);

//        e_refferalcode.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
//
//            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
//                return false;
//            }
//
//            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
//                return false;
//            }
//
//            public boolean onActionItemClicked(ActionMode actionMode, MenuItem item) {
//                return false;
//            }
//
//            public void onDestroyActionMode(ActionMode actionMode) {
//            }
//        });

        userNumber = getIntent().getStringExtra("number");

        upgrade.setOnClickListener(v -> {
            String refCode = e_refferalcode.getText().toString();
            if (refCode.equalsIgnoreCase("")) {
                upgradeMember(userNumber,"NOREFFRAL");
                progress.setVisibility(View.VISIBLE);
//                generateToken("NOREFFRAL");

            } else {
                if (numberValidate(refCode) && !refCode.equalsIgnoreCase("0000000000")) {
//                    generateToken(refCode);
                    upgradeMember(userNumber,refCode);
                    progress.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(Upgrade_membership.this, "Enter valid referral  number", Toast.LENGTH_SHORT).show();
                }
            }
        });
        skip.setOnClickListener(v -> finish());
    }

    private void upgradeMember(String userNumber, String refCode) {
        JSONObject dataObj=new JSONObject();
        try {
            dataObj.put("username",userNumber);
            dataObj.put("refCode",refCode);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RetroFitClient retroFitClient=new RetroFitClient();
        Retrofit retrofit=retroFitClient.getRetroFitClientScalar();
        Api_interface api=retrofit.create(Api_interface.class);
        Call<String> call=api.validateRefral(dataObj.toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body()!=null){
                    if(!response.body().isEmpty()){
                        try {
                            JSONObject dataObj=new JSONObject(response.body());
                            String status=dataObj.getString("Status");
                            String message=dataObj.getString("Message");
                            if(status.equalsIgnoreCase("True")){
                                generateToken(refCode);
                            }
                            else{
                                Toast.makeText(Upgrade_membership.this, message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
                Toast.makeText(Upgrade_membership.this, message + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateToken(String code) {
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
        orderID = "UPGRADE" + userNumber;
        Call<String> call = api.generateToken(orderID, "500");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body() != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());
                        if (jsonObject.getString("status").equalsIgnoreCase("OK")) {
                            token = jsonObject.getString("cftoken");
                            Intent intent = new Intent(Upgrade_membership.this, SelectPayment.class);
                            intent.putExtra("FromPage", "Upgrade");
                            intent.putExtra("orderID", orderID);
                            intent.putExtra("Token", token);
                            intent.putExtra("Amount", "500");
                            intent.putExtra("Name", "Chandra Thottempudi");
                            intent.putExtra("Number", userNumber);
                            intent.putExtra("RefCode", code);
                            startActivity(intent);
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
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
                Toast.makeText(Upgrade_membership.this, message + "", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public static boolean numberValidate(String mobileNumber) {
        Matcher matcher = numberPattern.matcher(mobileNumber);
        return matcher.find();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}