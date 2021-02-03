package com.yashswi.dilpay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class Add_account_details extends AppCompatActivity {
    ImageView back;
    TextInputEditText name,email,phone,bank_account,ifsc,vpa,address;
    String name1,email1,phone1,bank_account1,ifsc1,vpa1,address1;
    AppCompatButton submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account_details);
        back=findViewById(R.id.back);
        name=findViewById(R.id.e_name1);
        email=findViewById(R.id.e_email1);
        phone=findViewById(R.id.e_phone1);
        bank_account=findViewById(R.id.e_bankaccount1);
        ifsc=findViewById(R.id.e_ifsc1);
        vpa=findViewById(R.id.e_vpa1);
        address=findViewById(R.id.e_address1);
        submit=findViewById(R.id.submit);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name1=name.getText().toString();
                email1=email.getText().toString();
                phone1=phone.getText().toString();
                bank_account1=bank_account.getText().toString();
                ifsc1=ifsc.getText().toString();
                vpa1=vpa.getText().toString();
                address1=address.getText().toString();
            }
        });
    }
}