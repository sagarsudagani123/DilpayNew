package com.yashswi.dilpay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Add_account_details extends AppCompatActivity {
    TextView name,name1,name2,name3,name4;
    String andhra,sbi,icici,axis,union;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account_details);
        name=findViewById(R.id.bank_name);
        name1=findViewById(R.id.bank_name1);
        name2=findViewById(R.id.bank_name2);
        name3=findViewById(R.id.bank_name3);
        name4=findViewById(R.id.bank_name4);
        back=findViewById(R.id.back);
        Intent intent =getIntent();
        andhra=intent.getStringExtra("andhra");
        sbi=intent.getStringExtra("sbi");
        icici=intent.getStringExtra("icici");
        axis=intent.getStringExtra("axis");
        union=intent.getStringExtra("union");
        name.setText(andhra);
        name1.setText(sbi);
        name2.setText(icici);
        name3.setText(axis);
        name4.setText(union);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}