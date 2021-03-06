package com.yashswi.dilpay.bank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.yashswi.dilpay.Api_interface.Api_interface;
import com.yashswi.dilpay.Api_interface.cashFree;
import com.yashswi.dilpay.Profile;
import com.yashswi.dilpay.R;
import com.yashswi.dilpay.Validations;
import com.yashswi.dilpay.models.userDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.Calendar;

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
    String finalAmt;

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
                try {
                    int amt = Integer.parseInt(amount.getText().toString());
                    if (amt >= 100 && !(amt <= 0)) {
                        finalAmt=String.valueOf(amt);
                        verifyPayment(String.valueOf(amt), new userDetails(WithdrawAmount.this).getNumber());
                    } else {
                        Toast.makeText(WithdrawAmount.this, "minimum withdraw amount is 100", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(WithdrawAmount.this, "invalid amount", Toast.LENGTH_SHORT).show();

                }
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
        Call<String> call=api.verifyAmount(jsonObject.toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                Toast.makeText(WithdrawAmount.this,response.body(),Toast.LENGTH_LONG).show();
                if(response.body()!=null){
                    try {
                        JSONObject data=new JSONObject(response.body());
                          if(data.getString("Status").equalsIgnoreCase("True")){
                              getToken(amt);
                          }
                          else{
                              Toast.makeText(WithdrawAmount.this,data.getString("Message"),Toast.LENGTH_LONG).show();
                              progress.setVisibility(View.GONE);
                          }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(WithdrawAmount.this,e.toString(),Toast.LENGTH_LONG).show();
                        progress.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
//                Toast.makeText(WithdrawAmount.this,t.toString(),Toast.LENGTH_LONG).show();
                progress.setVisibility(View.GONE);
                String message="";
                if(t instanceof UnknownHostException)
                {
                    message = "No internet connection!";
                }
                else{
                    message = "Something went wrong! try again";
                }
                Toast.makeText(WithdrawAmount.this, message+""+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    void getToken(String amount) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int year= Calendar.getInstance().get(Calendar.YEAR);
        transferId="DILPAY"+year+timestamp.getTime();
        userDetails=new userDetails(WithdrawAmount.this);
        String totalAmt=userDetails.getWallet();
        JSONObject dataObj=new JSONObject();
        try {
            dataObj.put("benId",beneficiaryID);
            dataObj.put("username",userDetails.getNumber());
            dataObj.put("transactionId",transferId);
            dataObj.put("totalAmount",totalAmt);
            dataObj.put("withdrawAmount",amount);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("dataObj",dataObj.toString());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);
        Call<String> call = api.generatePayoutWithdrawToken(dataObj.toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                Toast.makeText(WithdrawAmount.this,response.body(),Toast.LENGTH_SHORT).show();
                try {
                    JSONObject data = new JSONObject(response.body());
                    if (data.getString("status").equalsIgnoreCase("SUCCESS")) {
                        JSONObject tokenObj = data.getJSONObject("data");
                        tokenFinal = tokenObj.getString("token");
//                        initiateWithdraw(tokenFinal, beneficiaryID, amount, transferId);
                        updateTransaction("");
                    } else {
                        Toast.makeText(WithdrawAmount.this,"Token generation failed!"+response.body(),Toast.LENGTH_SHORT).show();
                        tokenFinal = "";
                        progress.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    Toast.makeText(WithdrawAmount.this, "token catch"+e.toString(), Toast.LENGTH_SHORT).show();
                    progress.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progress.setVisibility(View.GONE);
                String message = "";
                if (t instanceof UnknownHostException) {
                    message = "No internet connection";
                } else {
                    message = "Something went wrong! try again";
                }
                Toast.makeText(WithdrawAmount.this, message + ""+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initiateWithdraw(String tokenFinal, String beneficiaryID, String amount, String transfer_id) {
//        progress.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(cashFree.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        cashFree api = retrofit.create(cashFree.class);
        JSONObject test = new JSONObject();
        try {
//            test.put("beneId", beneficiaryID);
            test.put("beneId",beneficiaryID);
            test.put("amount", amount);
            test.put("transferId", transfer_id);
            test.put("transferMode","banktransfer");
        } catch (Exception e) {
        }

        Call<String> call = api.walletWithdraw("Bearer " + tokenFinal, test.toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//Toast.makeText(WithdrawAmount.this,response.body(),Toast.LENGTH_SHORT).show();
//                progress.setVisibility(View.GONE);
                Log.e("transferAmt", response.body());
//                progress.setVisibility(View.GONE);
                try {
                    JSONObject data = new JSONObject(response.body());
                    Log.e("bankAdd", data.toString());
                    if (data.getString("status").equalsIgnoreCase("SUCCESS")) {
                        //add bank details to database

                        JSONObject createData=new JSONObject();
                        userDetails=new userDetails(WithdrawAmount.this);
                        createData.put("username",userDetails.getNumber());
                        createData.put("Amount",amount);
                        createData.put("subCode",data.getString("subCode"));
                        createData.put("message",data.getString("message"));
                        createData.put("TransferID",transferId);
                        JSONObject jsonObject=data.getJSONObject("data");
                        createData.put("refrenceID",jsonObject.getString("referenceId"));
                        Toast.makeText(WithdrawAmount.this, data.getString("message"), Toast.LENGTH_SHORT).show();

                        updateTransaction(createData.toString());
//                        finish();
                    } else {
                        Toast.makeText(WithdrawAmount.this, data.getString("message"), Toast.LENGTH_SHORT).show();
                        progress.setVisibility(View.GONE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(WithdrawAmount.this,  e.toString(), Toast.LENGTH_SHORT).show();
                    progress.setVisibility(View.GONE);
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
                Toast.makeText(WithdrawAmount.this, message + ""+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateTransaction(String data) {

        //=====================================================
        JSONObject dummyData=new JSONObject();
        userDetails=new userDetails(WithdrawAmount.this);
        try {
            dummyData.put("username",userDetails.getNumber());
            dummyData.put("Amount",finalAmt);
            dummyData.put("subCode","401");
            dummyData.put("message","Payment Completed");
            dummyData.put("TransferID",transferId);
            dummyData.put("refrenceID","test586448773298"+finalAmt);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //=====================================================

        Log.e("transferAmt==update>>>", dummyData.toString());
//        Toast.makeText(WithdrawAmount.this,data,Toast.LENGTH_SHORT).show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);
        Call<String> call=api.updateTransaction(dummyData.toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                progress.setVisibility(View.GONE);
                if(response.body()==null){
                }
                else if(response.body().isEmpty()){
                    Toast.makeText(WithdrawAmount.this,"Empty response",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(WithdrawAmount.this,response.body(),Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(WithdrawAmount.this, Profile.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progress.setVisibility(View.GONE);
                String message = "";
                if (t instanceof UnknownHostException) {
                    message = "No internet connection!";
                } else {
                    message = "Something went wrong! Contact us";
                }
                Toast.makeText(WithdrawAmount.this, message + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(WithdrawAmount.this, Profile.class);
        startActivity(intent);
        finish();
    }
}