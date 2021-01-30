package com.yashswi.dilpay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mobsandgeeks.saripaar.Validator;
import com.yashswi.dilpay.Api_interface.Api_interface;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Register_screen extends AppCompatActivity{
    AppCompatButton getOTP, signUp;
    Button login;
    TextView forgot_pwd;
    TextInputLayout passLayout,nameLayout;
    TextInputEditText mobile_number;
    TextInputEditText password,name;
    String e_mobile,e_password;
    RelativeLayout progress_layout;
    Validator validator;
    String number;
    public static final Pattern mailPattern=Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern numberPattern=Pattern.compile("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}", Pattern.CASE_INSENSITIVE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        String name1="jayachadnra";
        String OTP1="837488";
        String number1="9121382727";
        JSONObject test=new JSONObject();
        try {
            test.put("name",name1);
            test.put("number",number1);
            test.put("OTP",OTP1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("testdetails",test.toString());

        passLayout=findViewById(R.id.passLayout);
        nameLayout=findViewById(R.id.nameLayout);
        getOTP = findViewById(R.id.getOTP);
        signUp =findViewById(R.id.signup);
        login = findViewById(R.id.login);
//        forgot_pwd = findViewById(R.id.forgot_pwd);
        mobile_number=findViewById(R.id.e_mobile);
        password=findViewById(R.id.e_password);
        name=findViewById(R.id.name);
        progress_layout=findViewById(R.id.progress_layout);
        //======================================
//        SmsReceiver.bindListener(new SmsListener() {
//            @Override
//            public void messageReceived(String messageText) {
//                password.setText(messageText);
//                Toast.makeText(Register_screen.this,"message recieved",Toast.LENGTH_SHORT).show();
//            }
//        });
        //======================================

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register_screen.this, Login_screen.class);
                startActivity(i);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredOTP=password.getText().toString();
                String name1=name.getText().toString();
                if(enteredOTP.equalsIgnoreCase("") || name1.equalsIgnoreCase("")){
                    Toast.makeText(Register_screen.this,"Fill in all details",Toast.LENGTH_SHORT).show();
                }else{
                    JSONObject dataObj=new JSONObject();
                    try {
                    dataObj.put("number",number);
                    dataObj.put("name",name1);
                    dataObj.put("OTP",enteredOTP);
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
                number=mobile_number.getText().toString();
                if(number.equalsIgnoreCase("")){
                    Toast.makeText(Register_screen.this,"Enter your mobile number",Toast.LENGTH_SHORT).show();
                }else if(number.length()<10){
                    Toast.makeText(Register_screen.this,"Enter valid mobile number",Toast.LENGTH_SHORT).show();
                }
                else{
                    sendOtp(number);
                }

            }
        });
    }
    private void verifyOTP(String data) {
        progress_layout.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);
        Call<String> call = api.register1(data);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body()!=null){
                    progress_layout.setVisibility(View.GONE);

                    try {
                        JSONObject obj = new JSONObject(response.body());
                        String status=obj.optString("Status");
                        String message=obj.optString("Message");
                        if (status.equalsIgnoreCase("true")) {
                            progress_layout.setVisibility(View.GONE);
                            Toast.makeText(Register_screen.this, message, Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Register_screen.this, Login_screen.class);
                            startActivity(i);

                        } else if (status.equalsIgnoreCase("false")) {
                            progress_layout.setVisibility(View.GONE);
                            Toast.makeText(Register_screen.this, message, Toast.LENGTH_SHORT).show();
                        } else {
                            progress_layout.setVisibility(View.GONE);
                            Toast.makeText(Register_screen.this, "Error in server", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        progress_layout.setVisibility(View.GONE);
                        Toast.makeText(Register_screen.this, "Something went wrong! please try again", Toast.LENGTH_SHORT).show();

                    }

                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progress_layout.setVisibility(View.GONE);
                String message="";
                if(t instanceof NetworkError)
                {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(Register_screen.this, message, Toast.LENGTH_SHORT).show();
                }
                else if(t instanceof ServerError)
                {
                    message = "The server could not be found. Please try again after some time!!";
                    Toast.makeText(Register_screen.this, message, Toast.LENGTH_SHORT).show();
                }
                else if (t instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                    Toast.makeText(Register_screen.this, message, Toast.LENGTH_SHORT).show();
                }
                else{
                    message = "Somethiing went wrong! Please try again after some time!!"+t.toString();
                    Toast.makeText(Register_screen.this, message, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void sendOtp(String number) {
        progress_layout.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);
        Call<String> call = api.register(number);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body()!=null){
                    try {
                        JSONObject obj = new JSONObject(response.body());
                        String status=obj.optString("Status");
                        String message=obj.optString("Message");
//                        String number=obj.optString("MobileNo");

                        if (status.equalsIgnoreCase("true")) {
                            mobile_number.setEnabled(false);
                            progress_layout.setVisibility(View.GONE);
                            passLayout.setVisibility(View.VISIBLE);
                            nameLayout.setVisibility(View.VISIBLE);
                            signUp.setVisibility(View.VISIBLE);
                            getOTP.setVisibility(View.GONE);
                            Toast.makeText(Register_screen.this,"OTP sent to "+number,Toast.LENGTH_SHORT).show();
                        }else if (status.equalsIgnoreCase("false")) {
                            progress_layout.setVisibility(View.GONE);
                            Toast.makeText(Register_screen.this, message, Toast.LENGTH_SHORT).show();
                        } else {
                            progress_layout.setVisibility(View.GONE);
                            Toast.makeText(Register_screen.this, "Error in server", Toast.LENGTH_SHORT).show();
                        }

                    }catch (Exception e){
                        Toast.makeText(Register_screen.this, "Something went wrong! please try again"+e.toString(), Toast.LENGTH_SHORT).show();
                        progress_layout.setVisibility(View.GONE);

                    }



                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progress_layout.setVisibility(View.GONE);
                String message="";
                if(t instanceof NetworkError)
                {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(Register_screen.this, message, Toast.LENGTH_SHORT).show();
                }
                else if(t instanceof ServerError)
                {
                    message = "The server could not be found. Please try again after some time!!";
                    Toast.makeText(Register_screen.this, message, Toast.LENGTH_SHORT).show();
                }
                else if (t instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                    Toast.makeText(Register_screen.this, message, Toast.LENGTH_SHORT).show();
                }
                else{
                    message = "Something went wrong! Please try again after some time!!"+t.toString();
                    Toast.makeText(Register_screen.this, message, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public static boolean mailValidate(String emailStr) {
        Matcher matcher = mailPattern.matcher(emailStr);
        return matcher.find();
    }
    public static boolean numberValidate(String emailStr) {
        Matcher matcher = numberPattern.matcher(emailStr);
        return matcher.find();
    }



}