package com.yashswi.dilpay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.yashswi.dilpay.bus.My_bookings;
import com.yashswi.dilpay.models.userDetails;

public class Profile extends AppCompatActivity {
    ImageView back;
    LinearLayout logout,my_bookings;
    TextView customer_name,customer_mobile,amountWallet;
    AppCompatButton membership;
    com.yashswi.dilpay.models.userDetails userDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //FINDING VIEWS
        back=findViewById(R.id.back);
        logout=findViewById(R.id.logout);
        my_bookings=findViewById(R.id.lin3);
        customer_name=findViewById(R.id.customer_name);
        customer_mobile=findViewById(R.id.customer_mobile);
        amountWallet=findViewById(R.id.amount);
        membership=findViewById(R.id.membership);

        //GETTING USER DETAILS FROM SHAREDPREFERENCE
        userDetails=new userDetails(Profile.this);
        customer_name.setText(userDetails.getName());
        customer_mobile.setText(userDetails.getNumber());
        amountWallet.setText(userDetails.getWallet());

        if(userDetails.getMembership().equalsIgnoreCase("Paid")){
            membership.setVisibility(View.GONE);
        }

        membership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Profile.this,Upgrade_membership.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(v -> {
            Intent i = new Intent(Profile.this,Login_screen.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            userDetails.setLoged(false);
            userDetails.clearData();
            startActivity(i);
            finish();
        });

        my_bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, My_bookings.class);
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