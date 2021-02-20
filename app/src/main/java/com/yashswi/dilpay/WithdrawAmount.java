package com.yashswi.dilpay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.yashswi.dilpay.Api_interface.Api_interface;
import com.yashswi.dilpay.Api_interface.cashFree;
import com.yashswi.dilpay.bank.BankAccounts;
import com.yashswi.dilpay.models.userDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class WithdrawAmount extends AppCompatActivity {
    TextInputEditText amount;
    AppCompatButton submit, cancle;
    String tokenFinal, beneficiaryID, email, acntNumber, ifse, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_amount);
        amount = findViewById(R.id.amountWithDraw);
        submit = findViewById(R.id.buttonSubmit);
        cancle = findViewById(R.id.buttonCancel);

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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);
//        Call<String> call=api.verifyAmount(number,amt);
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                if(response.body()!=null){
//                    try {
//                        JSONObject data=new JSONObject(response.body());
//                          if(success){
//                              getToken(amt);
//                          }
//                          else{
//                              amount cant withdraw
//                          }
//                        Toast.makeText(BankAccounts.this,data.toString(),Toast.LENGTH_LONG).show();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        Toast.makeText(BankAccounts.this,e.toString(),Toast.LENGTH_LONG).show();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Toast.makeText(BankAccounts.this,t.toString(),Toast.LENGTH_LONG).show();
//                String message="";
//                if(t instanceof UnknownHostException)
//                {
//                    message = "No internet connection!";
//                }
//                else{
//                    message = "Something went wrong! try again";
//                }
//                Toast.makeText(TransactionsHistory.this, message+"", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    void getToken(String amount) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://payout-gamma.cashfree.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        cashFree api = retrofit.create(cashFree.class);
        Call<String> call = api.getToken("CF4207C0D8VIUK9404JSBD00DG", "51207f8c3ee789bc77cf7d3c54c0bd59d106b9fa");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    JSONObject data = new JSONObject(response.body());
                    if (data.getString("status").equalsIgnoreCase("SUCCESS")) {
                        JSONObject tokenObj = data.getJSONObject("data");
                        tokenFinal = tokenObj.getString("token");
                        initiateWithdraw(tokenFinal, beneficiaryID, amount, "9879879879");
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
                Log.e("transferAmt", response.body());
//                progress.setVisibility(View.GONE);
                try {
                    JSONObject data = new JSONObject(response.body());
                    Log.e("bankAdd", data.toString());
                    if (data.getString("status").equalsIgnoreCase("SUCCESS")) {
                        //add bank details to database

//                        JSONObject createData=new JSONObject();
//                        createData.put("beneficiaryID",beneficiaryID);
//                        createData.put("username",userDetails.getNumber());
//                        createData.put("Name",name1);
//                        createData.put("emial",email1);
//                        createData.put("phone",phone1);
//                        createData.put("accountNumber",bank_account1);
//                        createData.put("IFSC",ifsc1);
//                        createData.put("address",address1);
//                        addUserBankAccount(createData.toString());
                        Toast.makeText(WithdrawAmount.this, "success" + data.getString("message"), Toast.LENGTH_SHORT).show();
//                        finish();
                    } else {
                        Toast.makeText(WithdrawAmount.this, "failed" + data.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
//                progress.setVisibility(View.GONE);
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
}