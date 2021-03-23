package com.dilpay.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mobsandgeeks.saripaar.Validator;
import com.dilpay.app.Api_interface.Api_interface;
import com.dilpay.app.Broadcast.SmsBroadcastReceiver;


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

public class Register_screen extends AppCompatActivity {
    private static final int REQ_USER_CONSENT = 200;
    SmsBroadcastReceiver smsBroadcastReceiver;
    AppCompatButton getOTP, signUp;
    Button login;
    TextView forgot_pwd;
    TextInputLayout passLayout, nameLayout;
    TextInputEditText mobile_number;
    TextInputEditText password, name;
    String e_mobile, e_password;
    RelativeLayout progress_layout;
    Validator validator;
    String number;
    String name1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        passLayout = findViewById(R.id.passLayout);
        nameLayout = findViewById(R.id.nameLayout);
        getOTP = findViewById(R.id.getOTP);
        signUp = findViewById(R.id.signup);
        login = findViewById(R.id.login);
        mobile_number = findViewById(R.id.e_mobile);
        password = findViewById(R.id.e_password);
        name = findViewById(R.id.name);
        progress_layout = findViewById(R.id.progress_layout);

        startSmsUserConsent();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register_screen.this, Login_screen.class);
                startActivity(i);
                finish();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredOTP = password.getText().toString();
                name1 = name.getText().toString();
                if (enteredOTP.equalsIgnoreCase("") || name1.equalsIgnoreCase("")) {
                    Toast.makeText(Register_screen.this, "Fill in all details", Toast.LENGTH_SHORT).show();
                } else {
                    JSONObject dataObj = new JSONObject();
                    try {
                        dataObj.put("number", number);
                        dataObj.put("name", name1);
                        dataObj.put("OTP", enteredOTP);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    verifyOTP(dataObj.toString());
                }
            }
        });

        getOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = mobile_number.getText().toString();
                if (number.equalsIgnoreCase("")) {
                    Toast.makeText(Register_screen.this, "Enter your mobile number", Toast.LENGTH_SHORT).show();
                } else if (number.length() < 10) {
                    Toast.makeText(Register_screen.this, "Enter valid mobile number", Toast.LENGTH_SHORT).show();
                } else {
                    sendOtp(number);
                }

            }
        });
    }

    private void verifyOTP(String data) {
        progress_layout.setVisibility(View.VISIBLE);
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
        Call<String> call = api.register1(data);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body() != null) {
                    progress_layout.setVisibility(View.GONE);

                    try {
                        JSONObject obj = new JSONObject(response.body());
                        String status = obj.optString("Status");
                        String message = obj.optString("Message");
                        if (status.equalsIgnoreCase("true")) {
                            progress_layout.setVisibility(View.GONE);
                            Intent i = new Intent(Register_screen.this, Login_screen.class);
                            startActivity(i);
                            finish();

                        } else if (status.equalsIgnoreCase("false")) {
                            progress_layout.setVisibility(View.GONE);
                            Toast.makeText(Register_screen.this, message, Toast.LENGTH_SHORT).show();
                        } else {
                            progress_layout.setVisibility(View.GONE);
                            Toast.makeText(Register_screen.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        progress_layout.setVisibility(View.GONE);
                        Toast.makeText(Register_screen.this, "Something went wrong!", Toast.LENGTH_SHORT).show();

                    }

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progress_layout.setVisibility(View.GONE);
                String message = "";
                if (t instanceof UnknownHostException) {
                    message = "No internet connection!";
                } else {
                    message = "Something went wrong!";
                }
                Toast.makeText(Register_screen.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendOtp(String number) {
        progress_layout.setVisibility(View.VISIBLE);
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
        Call<String> call = api.register(number);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body() != null) {
                    try {
                        JSONObject obj = new JSONObject(response.body());
                        String status = obj.optString("Status");
                        String message = obj.optString("Message");
//                        String number=obj.optString("MobileNo");

                        if (status.equalsIgnoreCase("true")) {
                            mobile_number.setEnabled(false);
                            password.setEnabled(false);
                            progress_layout.setVisibility(View.GONE);
                            passLayout.setVisibility(View.VISIBLE);
                            nameLayout.setVisibility(View.VISIBLE);
                            signUp.setVisibility(View.VISIBLE);
                            getOTP.setVisibility(View.GONE);
                            Toast.makeText(Register_screen.this, "OTP sent to " + number, Toast.LENGTH_SHORT).show();
                        } else if (status.equalsIgnoreCase("false")) {
                            progress_layout.setVisibility(View.GONE);
                            Toast.makeText(Register_screen.this, message, Toast.LENGTH_SHORT).show();
                        } else {
                            progress_layout.setVisibility(View.GONE);
                            Toast.makeText(Register_screen.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        Toast.makeText(Register_screen.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        progress_layout.setVisibility(View.GONE);

                    }


                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progress_layout.setVisibility(View.GONE);
                String message = "";
                if (t instanceof UnknownHostException) {
                    message = "No internet connection!";
                } else {
                    message = "Something went wrong!";
                }
                Toast.makeText(Register_screen.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }


    //REGISTERING BROADCAST RECIEVER IN onStart METHOD
    @Override
    protected void onStart() {
        super.onStart();
        registerBroadcastReceiver();
    }

    //BROAADCAST RECIEVER REGISTER
    private void registerBroadcastReceiver() {
        smsBroadcastReceiver = new SmsBroadcastReceiver();
        smsBroadcastReceiver.smsBroadcastReceiverListener =
                new SmsBroadcastReceiver.SmsBroadcastReceiverListener() {
                    @Override
                    public void onSuccess(Intent intent) {
                        startActivityForResult(intent, REQ_USER_CONSENT);
                    }

                    @Override
                    public void onFailure() {
                    }
                };
        IntentFilter intentFilter = new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION);
        registerReceiver(smsBroadcastReceiver, intentFilter);
    }

    //UNREGISTER BROADCAST REGISTER
    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(smsBroadcastReceiver);
    }

    //STARTING SMSM BROADCAST REGISTER PROCESS
    private void startSmsUserConsent() {
        SmsRetrieverClient client = SmsRetriever.getClient(this);
        client.startSmsUserConsent(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    }

    //GETTING RESULT FROM BROADCAST RECIEVER
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_USER_CONSENT) {
            if ((resultCode == RESULT_OK) && (data != null)) {
                String message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE);
                getOtpFromMessage(message);
            }
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(Register_screen.this, "permission Canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //GETTING OTP FROM MESSAGE
    private void getOtpFromMessage(String message) {
        // This will match any 6 digit number in the message
        Pattern pattern = Pattern.compile("(|^)\\d{6}");
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            password.setText(matcher.group(0));

        }
    }


}