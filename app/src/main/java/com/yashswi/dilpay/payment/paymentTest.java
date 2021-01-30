package com.yashswi.dilpay.payment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.cashfree.pg.CFPaymentService;
import com.cashfree.pg.ui.gpay.GooglePayStatusListener;
import com.google.gson.Gson;
import com.yashswi.dilpay.Api_interface.Api_interface;
import com.yashswi.dilpay.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.cashfree.pg.CFPaymentService.PARAM_APP_ID;
import static com.cashfree.pg.CFPaymentService.PARAM_CARD_CVV;
import static com.cashfree.pg.CFPaymentService.PARAM_CARD_HOLDER;
import static com.cashfree.pg.CFPaymentService.PARAM_CARD_MM;
import static com.cashfree.pg.CFPaymentService.PARAM_CARD_NUMBER;
import static com.cashfree.pg.CFPaymentService.PARAM_CARD_YYYY;
import static com.cashfree.pg.CFPaymentService.PARAM_CUSTOMER_EMAIL;
import static com.cashfree.pg.CFPaymentService.PARAM_CUSTOMER_NAME;
import static com.cashfree.pg.CFPaymentService.PARAM_CUSTOMER_PHONE;
import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_AMOUNT;
import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_CURRENCY;
import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_ID;
import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_NOTE;
import static com.cashfree.pg.CFPaymentService.PARAM_PAYMENT_OPTION;

import static retrofit2.converter.gson.GsonConverterFactory.create;


public class paymentTest extends AppCompatActivity {
    EditText orderID,orderAmount;
    Button getToken;
    TextView dataText;
    String from,to,date,travelsName,time,duration,passName1,passAge1,passGender1,passName2,passAge2,passGender2;
String jsonData=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_test);
        dataText=findViewById(R.id.data);
//        jsonData=getIntent().getStringExtra("data");
        getToken=findViewById(R.id.getToken);
        getToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payment();
            }
        });



//        Log.e("jsonDatafinal",jsonData);
//            generateToken(jsonData);




    }

    private void generateToken(String jsonData) {

        Log.e("jsonDatafinal",jsonData);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);

        Call<String> call = api.generateToken(jsonData);
//        Toast.makeText(paymentTest.this,"called..."+jsonData,Toast.LENGTH_SHORT).show();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                dataText.setText(response.body());

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(paymentTest.this,"failed to send!!"+t.toString(),Toast.LENGTH_SHORT).show();

            }
        });
    }
    void payment()
    {
        String token="uA9JCN4MzUIJiOicGbhJCLiQ1VKJiOiAXe0Jye.BQQfiIGZwMjYmNzMyUTMwYjI6ICdsF2cfJCL1EDMwkTN0EjNxojIwhXZiwiIS5USiojI5NmblJnc1NkclRmcvJCLwIjOiQnb19WbBJXZkJ3biwiIyIjMyIjMyIjMiojIklkclRmcvJye.TJp7boayLiUDY2d_LccBn4IB1Ij6cxSzJ4NlYaMm-q30d7-IY5XN07_fl8YpHVfo4f";
        Map<String,String> params=new HashMap<>();
//        params.put(PARAM_PAYMENT_OPTION, "card");
//        params.put(PARAM_CARD_NUMBER, "4591150004359659");//Replace Card number
//        params.put(PARAM_CARD_MM, "12"); // Card Expiry Month in MM
//        params.put(PARAM_CARD_YYYY, "21"); // Card Expiry Year in YYYY
//        params.put(PARAM_CARD_HOLDER, "Jay"); // Card Holder name
//        params.put(PARAM_CARD_CVV, "503"); // Card CVV
        /////////////////////////////
        params.put(PARAM_APP_ID, "4207d3b63a1ecc9a5d79a8687024");
        params.put(PARAM_ORDER_ID, "222222222");
        params.put(PARAM_ORDER_AMOUNT, "20");
        params.put(PARAM_ORDER_NOTE, "Order for food");
        params.put(PARAM_CUSTOMER_NAME,"Rajeev");
        params.put(PARAM_CUSTOMER_PHONE, "9798379822");
        params.put(PARAM_CUSTOMER_EMAIL, "thottempudi52@gmail.com");
        params.put(PARAM_ORDER_CURRENCY, "INR");
        //////////////////////
        try {
            CFPaymentService cfPaymentService = CFPaymentService.getCFPaymentServiceInstance();
//                    cfPaymentService.getUpiClients(paymentTest.this);
            cfPaymentService.setOrientation(0);
//            cfPaymentService.setConfirmOnExit(true);
//            cfPaymentService.upiPayment(paymentTest.this, params, token, "TEST");
            cfPaymentService.doPayment(paymentTest.this, params, token, "TEST","#6dd5ed", "#FAFAFA", false);
//            CFPaymentService.getCFPaymentServiceInstance().isGPayReadyForPayment(paymentTest.this, new GooglePayStatusListener() {
//                @Override
//                public void isReady() {
//                    CFPaymentService cfPaymentService = CFPaymentService.getCFPaymentServiceInstance();
//                    cfPaymentService.setOrientation(0);
//                    cfPaymentService.gPayPayment(paymentTest.this, getInputParams(), token, "TEST");
//                    Toast.makeText(paymentTest.this,"readyyy",Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void isNotReady() {
//                    Toast.makeText(paymentTest.this,"readyyy",Toast.LENGTH_SHORT).show();
//
//                }
//            });
        }
        catch (Exception e){
            Toast.makeText(paymentTest.this,e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("paymentcheck", "ReqCode : " + CFPaymentService.REQ_CODE);
        if (data != null) {
            Bundle  bundle = data.getExtras();
            if (bundle != null)
                for (String  key  :  bundle.keySet()) {
                    if (bundle.getString(key) != null) {
                        Log.d("paymentcheck", key + " : " + bundle.getString(key));
                        if(key.equalsIgnoreCase("txStatus")){
                            String status=bundle.getString(key);
                            dataText.setText(status);
                        }
                    }
                }
        }
    }
}