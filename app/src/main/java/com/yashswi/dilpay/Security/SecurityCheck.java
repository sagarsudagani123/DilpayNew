package com.yashswi.dilpay.Security;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.yashswi.dilpay.R;
import com.yashswi.dilpay.Validations;
import com.yashswi.dilpay.models.userDetails;

import java.util.Calendar;

public class SecurityCheck extends AppCompatActivity {

    TextInputEditText userEnteredNumber;
    TextView dateOfBirth;
    AppCompatButton submit;
    boolean dateChecked = false;
    String userEnteredDOB = "";
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_check);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        back = findViewById(R.id.back);
        userEnteredNumber = findViewById(R.id.number);
        dateOfBirth = findViewById(R.id.dob);
        submit = findViewById(R.id.submit);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEntered = userEnteredNumber.getText().toString();
                if (!(userEnteredNumber.length() < 10)) {
                    if (Validations.numberValidate(userEntered)) {
                        validateDetails(userEntered, userEnteredDOB);
                    } else {
                        Toast.makeText(SecurityCheck.this, "Enter valid mobile number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SecurityCheck.this, "Enter valid mobile number", Toast.LENGTH_SHORT).show();
                }

            }
        });

        dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                final int month = calendar.get(Calendar.MONTH);
                final int year = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(SecurityCheck.this, (view, year1, month1, day1) -> {
                    month1 = month1 + 1;
                    String formattedMonth = "" + month1;
                    String formattedDayOfMonth = "" + day1;
                    String date = day1 + "-" + month1 + "-" + year1;
                    if (month1 < 10) {
                        formattedMonth = "0" + month1;
                    }
                    if (day1 < 10) {
                        formattedDayOfMonth = "0" + day1;
                    }

                    dateOfBirth.setText(formattedDayOfMonth + "-" + formattedMonth + "-" + year1);
                    dateChecked = true;
                    userEnteredDOB = dateOfBirth.getText().toString();

                    if (!dateChecked) {
                        Toast.makeText(SecurityCheck.this, "Fill in all details", Toast.LENGTH_SHORT).show();
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });


    }

    private void validateDetails(String userEntered, String userEnteredDOB) {
        userDetails userDetails = new userDetails(SecurityCheck.this);
        String userNumber = userDetails.getNumber();
        String userDOB = userDetails.getDOB();
        if (userNumber.equalsIgnoreCase(userEntered) && userDOB.equalsIgnoreCase(userEnteredDOB)) {
            Intent intent = new Intent(SecurityCheck.this, GeneratePin.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(SecurityCheck.this, "invalid details", Toast.LENGTH_SHORT).show();
        }

    }
}