package com.yashswi.dilpay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Gas_booking_successfull extends AppCompatActivity {
    TextView status, transaction_id, number, amount, order, opid;
    ImageView success, failure, back, pending;
    String status1, number1, amount1, order1;
    Integer transaction1;
    RelativeLayout details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas_booking_successfull);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        status = findViewById(R.id.success);
        transaction_id = findViewById(R.id.transaction_id1);
        number = findViewById(R.id.number1);
        amount = findViewById(R.id.amount1);
        success = findViewById(R.id.success1);
        failure = findViewById(R.id.failure);
        order = findViewById(R.id.order1);
        back = findViewById(R.id.back);
        details = findViewById(R.id.details);
        opid = findViewById(R.id.opid);
        pending = findViewById(R.id.pending);
        status1 = (String) this.getIntent().getSerializableExtra("status");
        transaction1 = this.getIntent().getIntExtra("txid", 0);
        number1 = (String) this.getIntent().getSerializableExtra("number");
        amount1 = (String) this.getIntent().getSerializableExtra("amount");
        order1 = (String) this.getIntent().getSerializableExtra("orderid");
//        int opID = this.getIntent().getIntExtra("opid", 0);

        if (status1.equalsIgnoreCase("failure")) {
            failure.setVisibility(View.VISIBLE);
            status.setText(status1);
            details.setVisibility(View.GONE);
            transaction_id.setText("");
            number.setText("");
            amount.setText("");
            order.setText("");
//            opid.setText(String.valueOf(opID));
        } else if (status1.equalsIgnoreCase("pending")) {
            pending.setVisibility(View.VISIBLE);
            status.setText(status1);
            transaction_id.setText(String.valueOf(transaction1));
            number.setText(String.valueOf(number1));
            amount.setText(amount1);
            order.setText(order1);
        } else {
            success.setVisibility(View.VISIBLE);
            status.setText(status1);
            transaction_id.setText(String.valueOf(transaction1));
            number.setText(String.valueOf(number1));
            amount.setText(amount1);
            order.setText(order1);
        }
    }
}