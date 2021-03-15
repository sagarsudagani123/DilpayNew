package com.yashswi.dilpay;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.mobsandgeeks.saripaar.Validator;
import com.yashswi.dilpay.Api_interface.Api_interface;
import com.yashswi.dilpay.Broadcast.SmsBroadcastReceiver;
import com.yashswi.dilpay.models.userDetails;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Login_screen extends AppCompatActivity {
    AppCompatButton getOTP, login;
    Button signup;
    TextView readOtp;
    TextInputLayout passLayout;
    TextInputEditText mobile_number;
    TextInputEditText password;
    RelativeLayout progress_layout;
    String number;
    String token;
    com.yashswi.dilpay.models.userDetails userDetails;
    SmsBroadcastReceiver smsBroadcastReceiver;
    private static final int REQ_USER_CONSENT = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        // FOR AUTO SMS READER
        startSmsUserConsent();
        //FINDING VIEW'S
        passLayout = findViewById(R.id.passLayout);
        getOTP = findViewById(R.id.getOTP);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup_btn);
        mobile_number = findViewById(R.id.e_mobile);
        password = findViewById(R.id.e_password);
        progress_layout = findViewById(R.id.progress_layout);
        readOtp = findViewById(R.id.readOtp);
        readOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //SIGNUP ACTION
        signup.setOnClickListener(v -> {
            Intent i = new Intent(Login_screen.this, Register_screen.class);
            startActivity(i);
            finish();
        });

        //LOGIN ACTION
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredOTP = password.getText().toString();
                if (enteredOTP.equalsIgnoreCase("")) {
                    Toast.makeText(Login_screen.this, "Enter your OTP", Toast.LENGTH_SHORT).show();
                } else {
                    verifyOTP(number, enteredOTP, "0");

                }

            }
        });

        //GENERATE OTP ACTION
        getOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = mobile_number.getText().toString();
                String password = "0";
                if (number.equalsIgnoreCase("")) {
                    Toast.makeText(Login_screen.this, "Enter your mobile number", Toast.LENGTH_SHORT).show();
                } else if (number.length() < 10) {
                    Toast.makeText(Login_screen.this, "Enter valid mobile number", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseInstanceId.getInstance().getInstanceId()
                            .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                @Override
                                public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                    if (!task.isSuccessful()) {
                                        return;
                                    }
                                    // Get new Instance ID token
                                    token = task.getResult().getToken();
                                    if (token != null) {
                                        Log.e("testToken", token);
//                                        Intent intent=new Intent(Login_screen.this,Home_screen.class);
//                                        startActivity(intent);
                                        sendOtp(number, password, token);
                                    }
                                }
                            });
                }
            }
        });
    }

    //VERIFY USER ENTERED OTP
    private void verifyOTP(String number, String otp, String token) {

        progress_layout.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);
        Call<String> call = api.login(number, otp, token);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body() != null) {
                    progress_layout.setVisibility(View.GONE);

                    try {
                        JSONObject obj = new JSONObject(response.body());
                        String status = obj.optString("Status");
                        String message = obj.optString("Message");
                        JSONArray dataArray = obj.getJSONArray("Data");
                        Log.e("userDetails", dataArray.toString());
                        JSONObject details = dataArray.getJSONObject(0);
                        Log.e("userDetails", details.toString());
                        if (status.equalsIgnoreCase("true")) {
                            progress_layout.setVisibility(View.GONE);
                            Toast.makeText(Login_screen.this, message, Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Login_screen.this, Home_screen.class);
                            userDetails = new userDetails(Login_screen.this);
                            userDetails.setLoged(true);
                            userDetails.setName(details.getString("fname1"));
                            userDetails.setNumber(details.getString("MobileNo"));
                            userDetails.setWallet(details.getString("Wallet"));
                            userDetails.setMembership(details.getString("userstatus"));
                            userDetails.setComission(details.getString("Comission"));
                            userDetails.setProfilePic("http://www.dilbus.in/api/uploads/"+details.getString("UserImage"));
                            if(details.getString("userstatus").equalsIgnoreCase("Free")){
                                FirebaseMessaging.getInstance().subscribeToTopic("FreeMember")
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                String msg = "Subscribed";
                                                if (!task.isSuccessful()) {
                                                    Log.e("Subscription",msg);
                                                }
                                            }
                                        });
                                FirebaseMessaging.getInstance().subscribeToTopic("Offers")
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                String msg = "Subscribed";
                                                if (!task.isSuccessful()) {
                                                    Log.e("Subscription",msg);
                                                }
                                            }
                                        });
                            }else if(details.getString("userstatus").equalsIgnoreCase("Paid")){
                                FirebaseMessaging.getInstance().subscribeToTopic("Offers")
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                String msg = "Subscribed";
                                                if (!task.isSuccessful()) {
                                                    Log.e("Subscription",msg);
                                                }
                                            }
                                        });
                            }
                            startActivity(i);
                            finish();

                        } else if (status.equalsIgnoreCase("false")) {
                            progress_layout.setVisibility(View.GONE);
                            Toast.makeText(Login_screen.this, message, Toast.LENGTH_SHORT).show();
                        } else {
                            progress_layout.setVisibility(View.GONE);
                            Toast.makeText(Login_screen.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(Login_screen.this, "Something went wrong! please try again", Toast.LENGTH_SHORT).show();

                    }

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progress_layout.setVisibility(View.GONE);
                String message;
                if (t instanceof NetworkError) {
                    message = "Nointernet connection!";
                    Toast.makeText(Login_screen.this, message, Toast.LENGTH_SHORT).show();
                } else {
                    message = "Something went wrong! Please try again after some time!!";
                    Toast.makeText(Login_screen.this, message, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //SEND OTP
    private void sendOtp(String user, String pass, String token) {
        Log.e("TokenGenerated", "" + token);
        progress_layout.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);
        Call<String> call = api.login(user, pass, token);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, @NotNull Response<String> response) {
                if (response.body() != null) {
                    try {
                        JSONObject obj = new JSONObject(response.body());
                        String status = obj.optString("Status");
                        String message = obj.optString("Message");
                        String number = obj.optString("MobileNo");

                        if (status.equalsIgnoreCase("true")) {
                            mobile_number.setEnabled(false);
                            progress_layout.setVisibility(View.GONE);
                            passLayout.setVisibility(View.VISIBLE);
                            login.setVisibility(View.VISIBLE);
                            getOTP.setVisibility(View.GONE);
                            Toast.makeText(Login_screen.this, "OTP sent to " + number, Toast.LENGTH_SHORT).show();
                        } else if (status.equalsIgnoreCase("false")) {
                            progress_layout.setVisibility(View.GONE);
                            Toast.makeText(Login_screen.this, message, Toast.LENGTH_SHORT).show();
                        } else {
                            progress_layout.setVisibility(View.GONE);
                            Toast.makeText(Login_screen.this, "Error in server", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        Toast.makeText(Login_screen.this, "Something went wrong! please try again", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progress_layout.setVisibility(View.GONE);
                String message;
                if (t instanceof UnknownHostException) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else {
                    message = "Something went wrong! Please try again after some time!!";
                }
                Toast.makeText(Login_screen.this, message, Toast.LENGTH_SHORT).show();
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
        //We can add sender phone number or leave it blank
        // I'm adding null here
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
                //That gives all message to us.
                // We need to get the code from inside with regex
                String message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE);
//                textViewMessage.setText(
//                        String.format("%s - %s", getString(R.string.received_message), message));
                getOtpFromMessage(message);
            }
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(Login_screen.this, "permission Required. Try again", Toast.LENGTH_SHORT).show();
//                startActivityForResult(data, REQ_USER_CONSENT);
            }
        }
    }

    //GETTING OTP FROM MESSAGE
    private void getOtpFromMessage(String message) {
        // This will match any 6 digit number in the message
        Pattern pattern = Pattern.compile("(|^)\\d{6}");
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
//            if(matcher.group(0).con)
//            otpText.setText(matcher.group(0));
            password.setText(matcher.group(0));
            verifyOTP(number, matcher.group(0), "0");
        }
    }


}