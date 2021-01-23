package com.yashswi.dilpay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.google.android.material.card.MaterialCardView;
import com.yashswi.dilpay.electricity.Electricity_recharge_screen;

public class Datacard extends AppCompatActivity {
    ImageView back;
    MaterialCardView upi_card,net_card,credit_card,debit_card;
    String[] operators = new String[] {"Tata Photon+", "Reliance Netconnect 3G", "Reliance Netconnect+", "Tata Photon whiz","Mts Mbrowse","Mts Mblaze"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datacard);
        back=findViewById(R.id.back);
        upi_card=findViewById(R.id.card1);
        net_card=findViewById(R.id.card2);
        credit_card=findViewById(R.id.card3);
        debit_card=findViewById(R.id.card4);
        upi_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Datacard.this, UPI_payments_screen.class);
                i.putExtra("upi", "UPI Payments");
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(Datacard.this, findViewById(R.id.upi), "trans1");
                startActivity(i, compat.toBundle());
            }
        });
        net_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Datacard.this, UPI_payments_screen.class);
                i.putExtra("net", "Net Banking");
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(Datacard.this, findViewById(R.id.net_banking), "trans2");
                startActivity(i, compat.toBundle());
            }
        });

        credit_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Datacard.this, UPI_payments_screen.class);
                i.putExtra("credit", "Credit Card");
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(Datacard.this, findViewById(R.id.credit), "trans3");
                startActivity(i, compat.toBundle());
            }
        });
        debit_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Datacard.this, UPI_payments_screen.class);
                i.putExtra("debit", "Debit Card");
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(Datacard.this, findViewById(R.id.debit), "trans4");
                startActivity(i, compat.toBundle());
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(
                        getApplicationContext(),
                        R.layout.menu_popup,
                        operators);

        AutoCompleteTextView editTextFilledExposedDropdown =
                findViewById(R.id.filled_exposed_dropdown);
        editTextFilledExposedDropdown.setAdapter(adapter);
    }
}