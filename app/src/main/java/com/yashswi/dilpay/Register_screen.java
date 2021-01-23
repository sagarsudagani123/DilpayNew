package com.yashswi.dilpay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

public class Register_screen extends AppCompatActivity{
    AppCompatButton register;
    TextInputEditText full_name;
    TextInputEditText mobile_number;
    TextInputEditText password;
    TextInputEditText confirm_password;
    String e_name,e_mobile,e_password,e_confirm_pwd;
    RelativeLayout progress_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        register = findViewById(R.id.register);
        full_name=findViewById(R.id.e_name);
        confirm_password=findViewById(R.id.e_confirm_pwd);
        mobile_number=findViewById(R.id.e_mobile);
        password=findViewById(R.id.e_pwd);
        progress_layout=findViewById(R.id.progress_layout);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                validator.validate();
                Intent i = new Intent(Register_screen.this, Login_screen.class);
                startActivity(i);
                finish();
            }
        });
    }
}