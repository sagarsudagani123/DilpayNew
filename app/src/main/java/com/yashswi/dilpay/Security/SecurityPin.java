package com.yashswi.dilpay.Security;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.goodiebag.pinview.Pinview;
import com.google.android.material.textfield.TextInputEditText;
import com.yashswi.dilpay.R;
import com.yashswi.dilpay.Security.SecurityCheck;
import com.yashswi.dilpay.mobile.Mobile;
import com.yashswi.dilpay.models.userDetails;

public class SecurityPin extends AppCompatActivity {
    TextView generate_pin,errorMessage;
    Pinview pin;
    userDetails userDetails;
    String pinCode;

//    EditText otpOne,otpTwo,otpThree,otpFour,otpFive,otpSix;
//    String userEnteredPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_pin);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        userDetails=new userDetails(SecurityPin.this);
        generate_pin=findViewById(R.id.generate_pin);
        errorMessage=findViewById(R.id.errorMessage);


        //================================================================================================
//        otpOne=findViewById(R.id.otpOne);
//        otpTwo=findViewById(R.id.otpTwo);
//        otpThree=findViewById(R.id.otpThree);
//        otpFour=findViewById(R.id.otpFour);
//        otpFive=findViewById(R.id.otpFive);
//        otpSix=findViewById(R.id.otpSix);
//
//        otpOne.setFocusable(true);
//        otpOne.setBackground(ContextCompat.getDrawable(SecurityPin.this,R.drawable.button_bg));
//
//        otpOne.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        otpOne.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                userEnteredPass=otpOne.getText().toString();
//                otpTwo.setText("");
//                otpTwo.requestFocus();
//                otpOne.setBackground(ContextCompat.getDrawable(SecurityPin.this,R.drawable.edit_background_inactive));
//                otpTwo.setBackground(ContextCompat.getDrawable(SecurityPin.this,R.drawable.button_bg));
//            }
//        });
//        otpTwo.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                otpThree.setText("");
//                userEnteredPass=userEnteredPass.concat(otpTwo.getText().toString());
//                otpThree.requestFocus();
//                otpTwo.setBackground(ContextCompat.getDrawable(SecurityPin.this,R.drawable.edit_background_inactive));
//                otpThree.setBackground(ContextCompat.getDrawable(SecurityPin.this,R.drawable.button_bg));
//            }
//        });
//        otpThree.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                otpFour.setText("");
//                userEnteredPass=userEnteredPass.concat(otpThree.getText().toString());
//                otpFour.requestFocus();
//                otpThree.setBackground(ContextCompat.getDrawable(SecurityPin.this,R.drawable.edit_background_inactive));
//                otpFour.setBackground(ContextCompat.getDrawable(SecurityPin.this,R.drawable.button_bg));
//            }
//        });
//        otpFour.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                otpFive.setText("");
//                userEnteredPass=userEnteredPass.concat(otpFour.getText().toString());
//                otpFive.requestFocus();
//                otpFour.setBackground(ContextCompat.getDrawable(SecurityPin.this,R.drawable.edit_background_inactive));
//                otpFive.setBackground(ContextCompat.getDrawable(SecurityPin.this,R.drawable.button_bg));
//            }
//        });
//        otpFive.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                otpSix.setText("");
//                userEnteredPass=userEnteredPass.concat(otpFive.getText().toString());
//                otpSix.requestFocus();
//                otpFive.setBackground(ContextCompat.getDrawable(SecurityPin.this,R.drawable.edit_background_inactive));
//                otpSix.setBackground(ContextCompat.getDrawable(SecurityPin.this,R.drawable.button_bg));
//            }
//        });
//
//        otpSix.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                userEnteredPass=userEnteredPass.concat(otpSix.getText().toString());
//                Toast.makeText(SecurityPin.this,"Invalid Pin: "+userEnteredPass,Toast.LENGTH_SHORT).show();
//
////                String userEnteredPin=otpOne.getText().toString();
////                char[] userEnteredPass={otpOne.getText().charAt(0),otpTwo.getText().charAt(0),otpThree.getText().charAt(0)
////                ,otpFour.getText().charAt(0),otpFive.getText().charAt(0),otpSix.getText().charAt(0)};
//
////                if(userEnteredPin.equalsIgnoreCase(pinCode)){
////                    Intent intent=new Intent(SecurityPin.this, Mobile.class);
////                    startActivity(intent);
////                    finish();
////                }
////                else{
////                    Toast.makeText(SecurityPin.this,"Invalid Pin",Toast.LENGTH_SHORT).show();
////                }
//            }
//        });

//        ======================================================================================================


        pinCode=userDetails.getSPin();
        if(pinCode.equalsIgnoreCase("") || pinCode.equalsIgnoreCase("null")){
            Toast.makeText(SecurityPin.this,"Pin not generated.",Toast.LENGTH_SHORT).show();
            errorMessage.setText("Pin not generated. \nUpdate details in your profile and generate pin.");
        }
        else{
            errorMessage.setText("");
        }
        Toast.makeText(SecurityPin.this,pinCode,Toast.LENGTH_SHORT).show();
        pin=findViewById(R.id.pin);
        pin.setPinViewEventListener(new Pinview.PinViewEventListener() {
            @Override
            public void onDataEntered(Pinview pinview, boolean fromUser) {

                String userEnteredPin=pinview.getValue();
                    if(userEnteredPin.equalsIgnoreCase(pinCode)){
                    Intent intent=new Intent(SecurityPin.this, Mobile.class);
                    startActivity(intent);
                    finish();
                    }
                    else{
                        Toast.makeText(SecurityPin.this,"Invalid Pin",Toast.LENGTH_SHORT).show();
                    }
            }
        });

        generate_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecurityPin.this, SecurityCheck.class);
                startActivity(intent);
                finish();
            }
        });
    }
}