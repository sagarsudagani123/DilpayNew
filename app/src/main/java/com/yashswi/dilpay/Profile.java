package com.yashswi.dilpay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yashswi.dilpay.bank.BankAccounts;
import com.yashswi.dilpay.bus.My_bookings;
import com.yashswi.dilpay.models.userDetails;

public class Profile extends AppCompatActivity {
    ImageView back;
    LinearLayout logout,my_bookings,withdraw;
    AppCompatButton upgrade;
    AppCompatButton membership;
    RelativeLayout my_wallet,bank_accounts;
    TextView customer_name,customer_mobile,amountWallet;
    com.yashswi.dilpay.models.userDetails userDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        back=findViewById(R.id.back);
        logout=findViewById(R.id.logout);
        my_bookings=findViewById(R.id.lin3);
        my_wallet=findViewById(R.id.lin4);
        bank_accounts=findViewById(R.id.lin5);
        customer_name=findViewById(R.id.customer_name);
        customer_mobile=findViewById(R.id.customer_mobile);
        upgrade=findViewById(R.id.membership);
        withdraw=findViewById(R.id.withdraw);
        membership=findViewById(R.id.membership);
//        amountWallet=findViewById(R.id.amount);


        userDetails=new userDetails(Profile.this);
        customer_name.setText(userDetails.getName());
        customer_mobile.setText(userDetails.getNumber());
//        amountWallet.setText(userDetails.getWallet());
//        Toast.makeText(Profile.this,userDetails.getName()+" "+userDetails.getNumber()+" "+" "+userDetails.getWallet(),Toast.LENGTH_SHORT).show();

        if(userDetails.getMembership().equalsIgnoreCase("Paid")){
            membership.setVisibility(View.GONE);
        }
        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, BankAccounts.class);
                i.putExtra("title","Select Bank");
                startActivity(i);
            }
        });
        upgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Profile.this,Upgrade_membership.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this,Login_screen.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                userDetails.setLoged(false);
                startActivity(i);
                finish();
            }
        });

        my_bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, My_bookings.class);
                startActivity(i);
            }
        });

        my_wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, MyWallet.class);
                startActivity(i);
            }
        });

        bank_accounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, BankAccounts.class);
                i.putExtra("title","My Accounts");
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}