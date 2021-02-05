package com.yashswi.dilpay.payment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.cashfree.pg.CFPaymentService;
import com.yashswi.dilpay.Api_interface.Api_interface;
import com.yashswi.dilpay.Home_screen;
import com.yashswi.dilpay.R;
import com.yashswi.dilpay.models.userDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

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
    String status1,paymentMode1,orderId1,txTime1,referenceId1,txMsg1,signature1,orderAmount1;
    TextView status,paymentMode,orderId,txTime,refId,txMsg,sign,orderAmount;
    ImageView success,failure,pending;
    LinearLayout detailsLayout;
    RelativeLayout progress,mainLayout;
    com.yashswi.dilpay.models.userDetails userDetails;

String jsonData=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_test);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

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
        pending=findViewById(R.id.pending);
        detailsLayout=findViewById(R.id.detailsLayout);
        progress=findViewById(R.id.progress);
        mainLayout=findViewById(R.id.mainLayout);

         status1 = getIntent().getStringExtra("status");
         paymentMode1 = getIntent().getStringExtra("paymentMode");
         orderId1 = getIntent().getStringExtra("orderId");
         txTime1 = getIntent().getStringExtra("txTime");
         referenceId1 = getIntent().getStringExtra("referenceId");
         txMsg1 = getIntent().getStringExtra("txMsg");
         signature1 = getIntent().getStringExtra("signature");
         orderAmount1 = getIntent().getStringExtra("orderAmount");

        JSONObject finalData=new JSONObject();


        if(status1.equalsIgnoreCase("SUCCESS")){

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
            confirmTicket(finalData.toString());
        }else if(status1.equalsIgnoreCase("FAILED")){
            try{
                finalData.put("Status", status1);
                finalData.put("Message",txMsg1);
            }
            catch (Exception e){
            }
            confirmTicket(finalData.toString());
        }
        else{
            try{
                finalData.put("Status", status1);
                finalData.put("Message",txMsg1);
            }
            catch (Exception e){

            }
            confirmTicket(finalData.toString());
        }
        Log.e("finalDetails",finalData.toString());
    }

    private void confirmTicket(String finalData) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);

        Call<String> call = api.confirmTicket(finalData);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                mainLayout.setVisibility(View.VISIBLE);
                progress.setVisibility(View.GONE);
                Toast.makeText(paymentStart.this,response.body(),Toast.LENGTH_SHORT).show();
                Log.e("confirmTest",response.body());
                JSONObject dataObj=new JSONObject();
                try {
                    if(dataObj.getString("Status").equalsIgnoreCase("SUCCESS")){
                        success.setVisibility(View.VISIBLE);
                        failure.setVisibility(View.GONE);
                        pending.setVisibility(View.GONE);
                        detailsLayout.setVisibility(View.VISIBLE);
                        status.setText(status1);
                        paymentMode.setText(paymentMode1);
                        orderAmount.setText(orderAmount1);
                        orderId.setText(orderId1);
                        txTime.setText(txTime1);
                        txMsg.setText(txMsg1);
                        refId.setText(referenceId1);
                    }else if(dataObj.getString("Status").equalsIgnoreCase("CANCELLED")){
                        success.setVisibility(View.GONE);
                        pending.setVisibility(View.GONE);
                        failure.setVisibility(View.VISIBLE);
//                        detailsLayout.setVisibility(View.GONE);
                        status.setText("Cancelled");
                        txMsg.setText(dataObj.getString("Message"));
                    }
                    else{
                        success.setVisibility(View.GONE);
                        pending.setVisibility(View.VISIBLE);
                        failure.setVisibility(View.GONE);
                        status.setText("Pending");
//                        detailsLayout.setVisibility(View.GONE);
                        txMsg.setText(dataObj.getString("Message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(paymentStart.this,t.toString(),Toast.LENGTH_SHORT).show();
                Log.e("confirmTest",t.toString());
                progress.setVisibility(View.GONE);
                mainLayout.setVisibility(View.VISIBLE);
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(paymentStart.this, Home_screen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}