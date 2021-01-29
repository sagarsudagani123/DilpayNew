package com.yashswi.dilpay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register_screen extends AppCompatActivity{
    AppCompatButton register;
    @NotEmpty(message = "Please enter your name")
    TextInputEditText full_name;
    TextInputEditText mobile_number;
    TextInputEditText email;
    TextInputEditText refercode;
    String e_name,e_mobile,e_password,e_confirm_pwd;
    RelativeLayout progress_layout;
    Validator validator;
    public static final Pattern mailPattern=Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern numberPattern=Pattern.compile("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}", Pattern.CASE_INSENSITIVE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        register = findViewById(R.id.register);
        full_name=findViewById(R.id.e_name);
        mobile_number=findViewById(R.id.e_mobile);
        progress_layout=findViewById(R.id.progress_layout);
        email=findViewById(R.id.email);
        refercode=findViewById(R.id.refercode);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=full_name.getText().toString();
                String number=mobile_number.getText().toString();
                String emailF=email.getText().toString();
                String referCode=refercode.getText().toString();

                if(name.equalsIgnoreCase("") || number.equalsIgnoreCase("") || emailF.equalsIgnoreCase("")){
                    Toast.makeText(Register_screen.this,"Fill in all details",Toast.LENGTH_SHORT).show();
                }else if(number.length()<10 && numberValidate(number)) {
                    Toast.makeText(Register_screen.this,"Enter valid number",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(mailValidate(emailF)){
                        Toast.makeText(Register_screen.this,"success",Toast.LENGTH_SHORT).show();
                        makeRegistration(name,number,emailF,referCode);
                    }else{
                        Toast.makeText(Register_screen.this,"invalid email address",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void makeRegistration(String name, String number, String emailF, String referCode) {
        //Registration process
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