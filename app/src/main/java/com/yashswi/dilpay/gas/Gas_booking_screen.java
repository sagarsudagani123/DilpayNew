package com.yashswi.dilpay.gas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.card.MaterialCardView;
import com.yashswi.dilpay.R;
import com.yashswi.dilpay.UPI_payments_screen;
import com.yashswi.dilpay.mobile.Mobile_recharge;

public class Gas_booking_screen extends AppCompatActivity {
    ImageView back;
    MaterialCardView upi_card,net_card,credit_card,debit_card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas_booking_screen);
        back=findViewById(R.id.back);
        upi_card=findViewById(R.id.card1);
        net_card=findViewById(R.id.card2);
        credit_card=findViewById(R.id.card3);
        debit_card=findViewById(R.id.card4);
        upi_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Gas_booking_screen.this, UPI_payments_screen.class);
                i.putExtra("upi", "UPI Payments");
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(Gas_booking_screen.this, findViewById(R.id.upi), "trans1");
                startActivity(i, compat.toBundle());
            }
        });
        net_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Gas_booking_screen.this, UPI_payments_screen.class);
                i.putExtra("net", "Net Banking");
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(Gas_booking_screen.this, findViewById(R.id.net_banking), "trans2");
                startActivity(i, compat.toBundle());
            }
        });

        credit_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Gas_booking_screen.this, UPI_payments_screen.class);
                i.putExtra("credit", "Credit Card");
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(Gas_booking_screen.this, findViewById(R.id.credit), "trans3");
                startActivity(i, compat.toBundle());
            }
        });
        debit_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Gas_booking_screen.this, UPI_payments_screen.class);
                i.putExtra("debit", "Debit Card");
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(Gas_booking_screen.this, findViewById(R.id.debit), "trans4");
                startActivity(i, compat.toBundle());
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