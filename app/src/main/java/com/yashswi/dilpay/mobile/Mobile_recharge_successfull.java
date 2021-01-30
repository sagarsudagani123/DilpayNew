package com.yashswi.dilpay.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yashswi.dilpay.R;

public class Mobile_recharge_successfull extends AppCompatActivity {
TextView status,transaction_id,number,amount,order,opid;
ImageView success,failure,pending,back;
String status1,number1,amount1,order1;
RelativeLayout details;
int opID;
Integer transaction1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_recharge_successfull);
        status=findViewById(R.id.success);
        transaction_id=findViewById(R.id.transaction_id1);
        number=findViewById(R.id.number1);
        amount=findViewById(R.id.amount1);
        success=findViewById(R.id.success1);
        failure=findViewById(R.id.failure);
        pending=findViewById(R.id.pending);
        order=findViewById(R.id.order1);
        back=findViewById(R.id.back);
        opid=findViewById(R.id.opid);
        details=findViewById(R.id.tran_details);
        status1= (String) this.getIntent().getSerializableExtra("status");
        transaction1=this.getIntent().getIntExtra("txid",0);
        number1= (String) this.getIntent().getSerializableExtra("number");
        amount1= (String) this.getIntent().getSerializableExtra("amount");
        order1= (String) this.getIntent().getSerializableExtra("orderid");
        opID= this.getIntent().getIntExtra("opid",0);

        if (status1.equalsIgnoreCase("failure")){
            failure.setVisibility(View.VISIBLE);
            status.setText(status1);
            details.setVisibility(View.GONE);
            transaction_id.setText(String.valueOf(""));
            number.setText(String.valueOf(""));
            amount.setText("");
            order.setText("");
//            opid.setText(String.valueOf(opID));
        } else if(status1.equalsIgnoreCase("pending")) {
            pending.setVisibility(View.VISIBLE);
            status.setText(status1);
            transaction_id.setText(String.valueOf(transaction1));
            number.setText(String.valueOf(number1));
            amount.setText(amount1);
            order.setText(order1);
        }
        else {
            success.setVisibility(View.VISIBLE);
            status.setText(status1);
            transaction_id.setText(String.valueOf(transaction1));
            number.setText(String.valueOf(number1));
            amount.setText(amount1);
            order.setText(order1);
        }



    }
}