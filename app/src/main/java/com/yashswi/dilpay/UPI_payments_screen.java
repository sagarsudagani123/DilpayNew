package com.yashswi.dilpay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

public class UPI_payments_screen extends AppCompatActivity {
    MaterialCardView add_account;
    LinearLayout li1,li2,li3,li4,li5,li6;
    TextView name,name1,name2,name3;
    String upi,net,credit,debit;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_p_i_payments_screen);
        add_account=findViewById(R.id.card1);
        li1=findViewById(R.id.li1);
        li2=findViewById(R.id.lin1);
        li3=findViewById(R.id.lin2);
        li4=findViewById(R.id.lin3);
        li5=findViewById(R.id.lin4);
        li6=findViewById(R.id.lin5);
        back=findViewById(R.id.back);
        name=findViewById(R.id.provider_name);
        name1=findViewById(R.id.provider_name1);
        name2=findViewById(R.id.provider_name2);
        name3=findViewById(R.id.provider_name3);
        Intent intent =getIntent();
        upi=intent.getStringExtra("upi");
        net=intent.getStringExtra("net");
        credit=intent.getStringExtra("credit");
        debit=intent.getStringExtra("debit");
        name.setText(upi);
        name1.setText(net);
        name2.setText(credit);
        name3.setText(debit);


        li2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UPI_payments_screen.this, Add_account_details.class);
                i.putExtra("andhra", "Andhra Bank");
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(UPI_payments_screen.this, findViewById(R.id.andhra), "trans1");
                startActivity(i, compat.toBundle());
            }
        });
        li3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UPI_payments_screen.this, Add_account_details.class);
                i.putExtra("sbi", "State Bank Of India");
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(UPI_payments_screen.this, findViewById(R.id.sbi), "trans2");
                startActivity(i, compat.toBundle());
            }
        });
        li4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UPI_payments_screen.this, Add_account_details.class);
                i.putExtra("icici", "ICICI Bank");
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(UPI_payments_screen.this, findViewById(R.id.icici), "trans3");
                startActivity(i, compat.toBundle());
            }
        });
        li5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UPI_payments_screen.this, Add_account_details.class);
                i.putExtra("axis", "Axis Bank");
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(UPI_payments_screen.this, findViewById(R.id.axis), "trans4");
                startActivity(i, compat.toBundle());
            }
        });
        li6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UPI_payments_screen.this, Add_account_details.class);
                i.putExtra("union", "Union Bank");
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(UPI_payments_screen.this, findViewById(R.id.union), "trans5");
                startActivity(i, compat.toBundle());
            }
        });
        add_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                li1.setVisibility(View.VISIBLE);
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