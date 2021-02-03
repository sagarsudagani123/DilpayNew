package com.yashswi.dilpay.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.yashswi.dilpay.R;

public class SelectPayment extends AppCompatActivity {
ImageView back;
MaterialCardView credit_card,upi_card;
RelativeLayout card_layout,upi_layout;
TextInputEditText card_number,holder_name,card_month,card_year,cvv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_payment);
        back=findViewById(R.id.back);
        credit_card=findViewById(R.id.card_details);
        upi_card=findViewById(R.id.upi_details_card);
        card_number=findViewById(R.id.e_number);
        holder_name=findViewById(R.id.e_name);
        card_month=findViewById(R.id.e_month);
        card_year=findViewById(R.id.e_year);
        cvv=findViewById(R.id.cvv);
        card_layout=findViewById(R.id.card_layout);
    }
}