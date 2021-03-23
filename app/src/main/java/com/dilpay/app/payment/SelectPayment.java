package com.dilpay.app.payment;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cashfree.pg.CFPaymentService;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.dilpay.app.R;

import java.util.HashMap;
import java.util.Map;

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

public class SelectPayment extends AppCompatActivity {
    ImageView back;
    MaterialCardView credit_card, upi_card;
    RelativeLayout card_layout, upi_layout;
    AppCompatButton cardSubmit;
    TextInputEditText card_number, holder_name, card_month, card_year, cvv;
    String token, orderID, amount, name, number, cardNum, holderName, cardMonth, cardYear, cardCvv;
    String FromPage, RefCode = "",paymentMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_payment);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        back = findViewById(R.id.back);
        credit_card = findViewById(R.id.card_details);
        upi_card = findViewById(R.id.upi_details_card);
        card_number = findViewById(R.id.e_number);
        holder_name = findViewById(R.id.e_name);
        card_month = findViewById(R.id.e_month);
        card_year = findViewById(R.id.e_year);
        cvv = findViewById(R.id.cvv);
        card_layout = findViewById(R.id.card_layout);
        cardSubmit = findViewById(R.id.cardSubmit);

        token = getIntent().getStringExtra("Token");
        orderID = getIntent().getStringExtra("orderID");
        amount = getIntent().getStringExtra("Amount");
        name = getIntent().getStringExtra("Name");
        number = getIntent().getStringExtra("Number");
        FromPage = getIntent().getStringExtra("FromPage");
        RefCode = getIntent().getStringExtra("RefCode");

        cardSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardNum = card_number.getText().toString();
                holderName = holder_name.getText().toString();
                cardMonth = card_month.getText().toString();
                cardYear = card_year.getText().toString();
                cardCvv = cvv.getText().toString();
                if (cardNum.length() < 16 || cardCvv.length() < 3 || cardNum.equalsIgnoreCase("") || holderName.equalsIgnoreCase("") || cardMonth.equalsIgnoreCase("") || cardYear.equalsIgnoreCase("") || cardCvv.equalsIgnoreCase("")) {
                    Toast.makeText(SelectPayment.this, "Invalid card details", Toast.LENGTH_SHORT).show();
                } else {
                    paymentMode="CARD";
                    payment(token, orderID, amount, name, number, "card");
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        upi_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paymentMode="UPI";
                payment(token, orderID, amount, name, number, "upi");
            }
        });
    }

    void payment(String token, String orderID, String amount, String name, String number, String type) {
        Map<String, String> params = new HashMap<>();
        params.put(PARAM_APP_ID, "112692af3484e0119eafbb96096211");
        params.put(PARAM_ORDER_ID, orderID);
        params.put(PARAM_ORDER_AMOUNT, amount);
        params.put(PARAM_ORDER_NOTE, "Bus Ticket booking");
        params.put(PARAM_CUSTOMER_NAME, name);
        params.put(PARAM_CUSTOMER_PHONE, number);
        params.put(PARAM_CUSTOMER_EMAIL, "dilpay.inpay@gmail.com");
        params.put(PARAM_ORDER_CURRENCY, "INR");

        try {
            CFPaymentService cfPaymentService = CFPaymentService.getCFPaymentServiceInstance();
            cfPaymentService.setOrientation(0);
            if (type.equalsIgnoreCase("card")) {
                params.put(PARAM_PAYMENT_OPTION, "card");
                params.put(PARAM_CARD_NUMBER, cardNum);//Replace Card number
                params.put(PARAM_CARD_MM, cardMonth); // Card Expiry Month in MM
                params.put(PARAM_CARD_YYYY, cardYear); // Card Expiry Year in YYYY
                params.put(PARAM_CARD_HOLDER, holderName); // Card Holder name
                params.put(PARAM_CARD_CVV, cardCvv);
                cfPaymentService.doPayment(SelectPayment.this, params, token, "PROD", "#822659", "#a82069", false);
            }
            if (type.equalsIgnoreCase("upi")) {
                cfPaymentService.upiPayment(SelectPayment.this, params, token, "PROD");
            }
        } catch (Exception e) {
            Toast.makeText(SelectPayment.this, "payment" + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data!=null) {
            if (((data.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION) != 0) && ((data.getFlags() & Intent.FLAG_GRANT_WRITE_URI_PERMISSION) != 0)) {
                data.removeFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                data.removeFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
            parseIntentData(data);
        }


    }

    void parseIntentData(Intent data){
        Log.e("paymentcheck", "ReqCode : " + CFPaymentService.REQ_CODE);
        if (data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                for (String key : bundle.keySet()) {
                    if (bundle.getString(key) != null) {
                        Log.e("paymentcheck", key + " : " + bundle.getString(key));
                    }
                }
                String status = bundle.getString("txStatus");
//                String paymentMode = bundle.getString("paymentMode");
                String orderId = bundle.getString("orderId");
                String txTime = bundle.getString("txTime");
                String referenceId = bundle.getString("referenceId");
                String txMsg = bundle.getString("txMsg");
//                String signature = bundle.getString("signature");
                String orderAmount = bundle.getString("orderAmount");
                if (status.equalsIgnoreCase("success")) {
                    Intent intent = new Intent(SelectPayment.this, paymentStart.class);
                    intent.putExtra("FromPage", FromPage);
                    intent.putExtra("status", status);
                    intent.putExtra("paymentMode", paymentMode);
                    intent.putExtra("orderId", orderId);
//                    intent.putExtra("txTime", txTime);
                    intent.putExtra("referenceId", referenceId);
                    intent.putExtra("txMsg", txMsg);
//                    intent.putExtra("signature", signature);
                    intent.putExtra("orderAmount", orderAmount);
                    intent.putExtra("RefCode", RefCode);
//                    Log.e("sendingData",);
                    startActivity(intent);
                    finish();

                }
            }
        }
    }
}