package com.yashswi.dilpay.payment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.cashfree.pg.CFPaymentService;
import com.yashswi.dilpay.Home_screen;
import com.yashswi.dilpay.R;
import com.yashswi.dilpay.models.userDetails;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.cashfree.pg.CFPaymentService.PARAM_APP_ID;
import static com.cashfree.pg.CFPaymentService.PARAM_CUSTOMER_EMAIL;
import static com.cashfree.pg.CFPaymentService.PARAM_CUSTOMER_NAME;
import static com.cashfree.pg.CFPaymentService.PARAM_CUSTOMER_PHONE;
import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_AMOUNT;
import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_CURRENCY;
import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_ID;
import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_NOTE;

import static retrofit2.converter.gson.GsonConverterFactory.create;


public class paymentStart extends AppCompatActivity {
    String token,orderID,amount,name,number;
    TextView status,paymentMode,orderId,txTime,refId,txMsg,sign,orderAmount;
    ImageView success,failure;
    com.yashswi.dilpay.models.userDetails userDetails;

String jsonData=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_test);
        status=findViewById(R.id.txStatus1);
        paymentMode=findViewById(R.id.paymentMode1);
        orderId=findViewById(R.id.orderId1);
        txTime=findViewById(R.id.txTime1);
        refId=findViewById(R.id.refId1);
        txMsg=findViewById(R.id.txMessage);
//        sign=findViewById(R.id.status);
        orderAmount=findViewById(R.id.orderAmount1);
        success=findViewById(R.id.success1);
        failure=findViewById(R.id.failure);

        String status1 = getIntent().getStringExtra("status");
        String paymentMode1 = getIntent().getStringExtra("paymentMode");
        String orderId1 = getIntent().getStringExtra("orderId");
        String txTime1 = getIntent().getStringExtra("txTime");
        String referenceId1 = getIntent().getStringExtra("referenceId");
        String txMsg1 = getIntent().getStringExtra("txMsg");
        String signature1 = getIntent().getStringExtra("signature");
        String orderAmount1 = getIntent().getStringExtra("orderAmount");

        JSONObject finalData=new JSONObject();


        if(status1.equalsIgnoreCase("SUCCESS")){
            success.setVisibility(View.VISIBLE);
            failure.setVisibility(View.GONE);
            status.setText(status1);
            paymentMode.setText(paymentMode1);
            orderAmount.setText(orderAmount1);
            orderId.setText(orderId1);
            txTime.setText(txTime1);
            txMsg.setText(txMsg1);
            refId.setText(referenceId1);
            try {
                finalData.put("Status", status1);
                finalData.put("Mode",paymentMode1);
                finalData.put("Amount",orderAmount1);
                finalData.put("OrderID",orderId1);
                finalData.put("RefID",referenceId1);
                finalData.put("TxTime",txTime1);
                finalData.put("Message",txMsg1);
                finalData.put("Signature",signature1);

            }
            catch (Exception e){

            }

        }else if(status1.equalsIgnoreCase("FAILED")){
            success.setVisibility(View.GONE);
            failure.setVisibility(View.VISIBLE);
            status.setText(status1);
            txMsg.setText(txMsg1);
            try{
                finalData.put("Status", status1);
                finalData.put("Message",txMsg1);
            }
            catch (Exception e){

            }
        }
        else{
            try{
                finalData.put("Status", status1);
                finalData.put("Message","Your transaction has cancelled.");
            }
            catch (Exception e){

            }
        }
        Log.e("finalDetails",finalData.toString());
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(paymentStart.this, Home_screen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}