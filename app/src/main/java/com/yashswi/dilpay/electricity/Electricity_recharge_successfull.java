package com.yashswi.dilpay.electricity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.yashswi.dilpay.R;

public class Electricity_recharge_successfull extends AppCompatActivity {
    TextView status,transaction_id,number,amount,order;
    ImageView success,failure,back;
    String status1,number1,amount1,order1;
    Integer transaction1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity_recharge_successfull);
        status=findViewById(R.id.success);
        transaction_id=findViewById(R.id.transaction_id1);
        number=findViewById(R.id.number1);
        amount=findViewById(R.id.amount1);
        success=findViewById(R.id.success1);
        failure=findViewById(R.id.failure);
        order=findViewById(R.id.order1);
        back=findViewById(R.id.back);
        status1= (String) this.getIntent().getSerializableExtra("status");
        transaction1=this.getIntent().getIntExtra("txid",0);
        number1= (String) this.getIntent().getSerializableExtra("number");
        amount1= (String) this.getIntent().getSerializableExtra("amount");
        order1= (String) this.getIntent().getSerializableExtra("orderid");
        status.setText(status1);
        transaction_id.setText(""+transaction1);
        number.setText(""+number1);
        amount.setText(amount1);
        order.setText(order1);
        if (status1.equalsIgnoreCase("failure")){
            failure.setVisibility(View.VISIBLE);
            success.setVisibility(View.GONE);
        } else {
            failure.setVisibility(View.GONE);
            success.setVisibility(View.VISIBLE);
        }
    }
}