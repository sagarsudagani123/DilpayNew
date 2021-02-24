package com.yashswi.dilpay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.yashswi.dilpay.Api_interface.Api_interface;
import com.yashswi.dilpay.Api_interface.cashFree;
import com.yashswi.dilpay.bank.BankAccounts;
import com.yashswi.dilpay.models.userDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.UnknownHostException;
import java.sql.Timestamp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class WithdrawAmount extends AppCompatActivity {
    TextInputEditText amount;
    AppCompatButton submit, cancle;
    RelativeLayout progress;
    String transferId;
    String tokenFinal, beneficiaryID, email, acntNumber, ifse, address;
    userDetails userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_amount);
        amount = findViewById(R.id.amountWithDraw);
        submit = findViewById(R.id.buttonSubmit);
        cancle = findViewById(R.id.buttonCancel);
        progress=findViewById(R.id.progress);

        beneficiaryID = getIntent().getStringExtra("beneficiaryID");
        email = getIntent().getStringExtra("emial");
        acntNumber = getIntent().getStringExtra("accountNumber");
        ifse = getIntent().getStringExtra("IFSC");
        address = getIntent().getStringExtra("address");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amt = amount.getText().toString();
                verifyPayment(amt, new userDetails(WithdrawAmount.this).getNumber());
            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void verifyPayment(String amt, String number) {
        progress.setVisibility(View.VISIBLE);
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("username",number);
            jsonObject.put("amount",amt);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("amountCheck",jsonObject.toString());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);
        Call<String> call=api.verifyAmount(number,amt);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body()!=null){
                    try {
                        JSONObject data=new JSONObject(response.body());
                          if(data.getString("Status").equalsIgnoreCase("True")){
                              getToken(amt);
                          }
                          else{
                              Toast.makeText(WithdrawAmount.this,data.getString("Message"),Toast.LENGTH_LONG).show();
                          }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(WithdrawAmount.this,e.toString(),Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(WithdrawAmount.this,t.toString(),Toast.LENGTH_LONG).show();
                String message="";
                if(t instanceof UnknownHostException)
                {
                    message = "No internet connection!";
                }
                else{
                    message = "Something went wrong! try again";
                }
                Toast.makeText(WithdrawAmount.this, message+"", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void getToken(String amount) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        transferId="DILPAY"+timestamp.getTime();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://payout-gamma.cashfree.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        cashFree api = retrofit.create(cashFree.class);
        Call<String> call = api.getToken("CF11269C0R2C47A4DSU6O8FMHPG", "fec741789bb768c847a3a4f1f600a7fea64277a4");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    JSONObject data = new JSONObject(response.body());
                    if (data.getString("status").equalsIgnoreCase("SUCCESS")) {
                        JSONObject tokenObj = data.getJSONObject("data");
                        tokenFinal = tokenObj.getString("token");
                        initiateWithdraw(tokenFinal, beneficiaryID, amount, transferId);
//                        Toast.makeText(Add_account_details.this,tokenFinal,Toast.LENGTH_SHORT).show();
                    } else {
                        tokenFinal = "";
                    }
                } catch (Exception e) {
                    Toast.makeText(WithdrawAmount.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                String message = "";
                if (t instanceof UnknownHostException) {
                    message = "No internet connection";
                } else {
                    message = "Something went wrong! try again";
                }
                Toast.makeText(WithdrawAmount.this, message + "", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initiateWithdraw(String tokenFinal, String beneficiaryID, String amount, String transfer_id) {
//        progress.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://payout-gamma.cashfree.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        cashFree api = retrofit.create(cashFree.class);
        JSONObject test = new JSONObject();
        try {
            test.put("beneId", beneficiaryID);
            test.put("amount", amount);
            test.put("transferId", transfer_id);
        } catch (Exception e) {
        }

        Call<String> call = api.walletWithdraw("Bearer " + tokenFinal, test.toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                progress.setVisibility(View.GONE);
                Log.e("transferAmt", response.body());
//                progress.setVisibility(View.GONE);
                try {
                    JSONObject data = new JSONObject(response.body());
                    Log.e("bankAdd", data.toString());
                    if (data.getString("status").equalsIgnoreCase("ACCEPTED")) {
                        //add bank details to database

                        JSONObject createData=new JSONObject();
                        userDetails=new userDetails(WithdrawAmount.this);
                        createData.put("username",userDetails.getNumber());
                        createData.put("Amount",amount);
                        createData.put("TransferID",transferId);
                        JSONObject jsonObject=data.getJSONObject("data");
                        createData.put("refrenceID",jsonObject.getString("referenceId"));
                        createData.put("subCode",data.getString("subCode"));

                        updateTransaction(createData.toString());
                        Toast.makeText(WithdrawAmount.this,  data.getString("message"), Toast.LENGTH_SHORT).show();
//                        finish();
                    } else {
                        Toast.makeText(WithdrawAmount.this,  data.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progress.setVisibility(View.GONE);
                String message = "";
                if (t instanceof UnknownHostException) {
                    message = "No internet connection!";
                } else {
                    message = "Something went wrong! try again";
                }
                Toast.makeText(WithdrawAmount.this, message + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateTransaction(String data) {
        Log.e("transferAmt==", data);
        Toast.makeText(WithdrawAmount.this,data,Toast.LENGTH_SHORT).show();
        progress.setVisibility(View.GONE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);
//        Call<String> call=api.updateTransaction(data);
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                if(response.body()==null){
//                }
//                else if(response.body().isEmpty()){
//                    Toast.makeText(WithdrawAmount.this,"Empty response",Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(WithdrawAmount.this,response.body(),Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                progress.setVisibility(View.GONE);
//                String message = "";
//                if (t instanceof UnknownHostException) {
//                    message = "No internet connection!";
//                } else {
//                    message = "Something went wrong! try again";
//                }
//                Toast.makeText(WithdrawAmount.this, message + "", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}