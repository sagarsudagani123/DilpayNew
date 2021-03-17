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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
//import com.goodiebag.pinview.Pinview;
import com.google.android.material.textfield.TextInputEditText;
import com.yashswi.dilpay.Datacard_screen;
import com.yashswi.dilpay.R;
import com.yashswi.dilpay.Security.SecurityCheck;
import com.yashswi.dilpay.dth.Dth_screen;
import com.yashswi.dilpay.electricity.Electricity_screen;
import com.yashswi.dilpay.gas.Gas_screen;
import com.yashswi.dilpay.mobile.Mobile;
import com.yashswi.dilpay.models.userDetails;
import com.yashswi.dilpay.postpaid.Postpaid_screen;

public class SecurityPin extends AppCompatActivity {
    TextView generate_pin, errorMessage;
    PinEntryEditText pinEntryEditText;
    userDetails userDetails;
    String pinCode;
    ImageView back;
    String FromService;

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

        FromService=getIntent().getStringExtra("category");

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
                    if(FromService.equalsIgnoreCase("Mobile")){
                        Intent intent = new Intent(SecurityPin.this, Mobile.class);
                        startActivity(intent);
                        finish();
                    }else if(FromService.equalsIgnoreCase("dth")){
                        Intent intent = new Intent(SecurityPin.this, Dth_screen.class);
                        startActivity(intent);
                        finish();
                    }else if(FromService.equalsIgnoreCase("datacard")){
                        Intent intent = new Intent(SecurityPin.this, Datacard_screen.class);
                        startActivity(intent);
                        finish();
                    }else if(FromService.equalsIgnoreCase("postpaid")){
                        Intent intent = new Intent(SecurityPin.this, Postpaid_screen.class);
                        startActivity(intent);
                        finish();
                    }else if(FromService.equalsIgnoreCase("electricity")){
                        Intent intent = new Intent(SecurityPin.this, Electricity_screen.class);
                        startActivity(intent);
                        finish();
                    }else if(FromService.equalsIgnoreCase("gas")){
                        Intent intent = new Intent(SecurityPin.this, Gas_screen.class);
                        startActivity(intent);
                        finish();
                    }else if(FromService.equalsIgnoreCase("Money Transfer")){
//                        Intent intent = new Intent(SecurityPin.this, .class);
//                        startActivity(intent);
//                        finish();
                    }
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
                Intent intent = new Intent(SecurityPin.this, SecurityCheck.class);
                startActivity(intent);
                finish();
            }
        });
    }
}