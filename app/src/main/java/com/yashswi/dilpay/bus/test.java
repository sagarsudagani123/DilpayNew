package com.yashswi.dilpay.bus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.cashfree.pg.CFPaymentService;
import com.yashswi.dilpay.R;
import com.yashswi.dilpay.payment.paymentStart;

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
import static com.cashfree.pg.CFPaymentService.PARAM_PAYMENT_OPTION;
import static com.cashfree.pg.CFPaymentService.PARAM_UPI_VPA;

public class test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        String token1="yk9JCN4MzUIJiOicGbhJCLiQ1VKJiOiAXe0Jye.Kk9JyMkVWN5IGNkJTOxAjNiojI0xWYz9lIsEzMwMjNyITM2EjOiAHelJCLiIlTJJiOik3YuVmcyV3QyVGZy9mIsEjOiQnb19WbBJXZkJ3biwiI3UjN1YTN2UjN1YTNiojIklkclRmcvJye.D8S5oWDvI_OPoR1eZ4gokpI_YJaVwBlPI2G6tKgLMEQAiMig30xzDKZ0ttSnPK8dkr";
        Map<String,String> params=new HashMap<>();
        params.put(PARAM_APP_ID, "112692af3484e0119eafbb96096211");
        params.put(PARAM_ORDER_ID, "565656565657");
        params.put(PARAM_ORDER_AMOUNT, "1");
        params.put(PARAM_ORDER_NOTE, "Bus Ticket booking");
        params.put(PARAM_CUSTOMER_NAME,"test");
        params.put(PARAM_CUSTOMER_PHONE, "9121382727");
        params.put(PARAM_CUSTOMER_EMAIL, "thottempudi22@gmail.com");
        params.put(PARAM_ORDER_CURRENCY, "INR");
        //////////////////////
//        params.put(PARAM_PAYMENT_OPTION, "card");
//        params.put(PARAM_CARD_NUMBER, "4111111111111111");//Replace Card number
//        params.put(PARAM_CARD_MM, "07"); // Card Expiry Month in MM
//        params.put(PARAM_CARD_YYYY, "2023"); // Card Expiry Year in YYYY
//        params.put(PARAM_CARD_HOLDER, "Test"); // Card Holder name
//        params.put(PARAM_CARD_CVV, "123"); // Card CVV
        //////////////////////

        params.put(PARAM_PAYMENT_OPTION, "userVPA");
        params.put(PARAM_UPI_VPA, "8498959480@ybl");
        try {
            CFPaymentService cfPaymentService = CFPaymentService.getCFPaymentServiceInstance();
            cfPaymentService.setOrientation(0);
//            cfPaymentService.doPayment(busDetailsConfirmation.this, params, token, "TEST","#6dd5ed", "#FAFAFA", false);
            cfPaymentService.upiPayment(test.this,params,token1,"PROD");
        }
        catch (Exception e){
            Toast.makeText(test.this,"payment"+e.toString(),Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Toast.makeText(test.this,"got result",Toast.LENGTH_LONG).show();
        Log.d("paymentcheck", "ReqCode : " + CFPaymentService.REQ_CODE);
        if (data != null) {
            Bundle  bundle = data.getExtras();
            if (bundle != null) {
                for (String  key  :  bundle.keySet()) {
                    if (bundle.getString(key) != null) {
                        Log.d("paymentcheck", key + " : " + bundle.getString(key));
//
                    }
                }
                String status = bundle.getString("txStatus");
                String paymentMode = bundle.getString("paymentMode");
                String orderId = bundle.getString("orderId");
                String txTime = bundle.getString("txTime");
                String referenceId = bundle.getString("referenceId");
                String txMsg = bundle.getString("txMsg");
                String signature = bundle.getString("signature");
                String orderAmount = bundle.getString("orderAmount");
                Toast.makeText(test.this,status+" "+paymentMode+" "+orderAmount+"  "+orderId+" "+txMsg+" "+txTime,Toast.LENGTH_LONG).show();
//                if (status.equalsIgnoreCase("success")) {
//                Intent intent = new Intent(busDetailsConfirmation.this, paymentStart.class);
//                intent.putExtra("status", status);
//                intent.putExtra("paymentMode", paymentMode);
//                intent.putExtra("orderId", orderId);
//                intent.putExtra("txTime", txTime);
//                intent.putExtra("referenceId", referenceId);
//                intent.putExtra("txMsg", txMsg);
//                intent.putExtra("signature", signature);
//                intent.putExtra("orderAmount",orderAmount);
////                    Log.e("sendingData",status+""+);
//                startActivity(intent);

//                }
            }
        }
    }
}