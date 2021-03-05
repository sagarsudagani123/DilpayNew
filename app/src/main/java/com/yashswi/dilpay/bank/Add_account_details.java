package com.yashswi.dilpay.bank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.yashswi.dilpay.Api_interface.Api_interface;
import com.yashswi.dilpay.Api_interface.cashFree;
import com.yashswi.dilpay.R;
import com.yashswi.dilpay.TransactionsHistory;
import com.yashswi.dilpay.bus.Bus;
import com.yashswi.dilpay.models.Add_account_model;
import com.yashswi.dilpay.models.userDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Add_account_details extends AppCompatActivity {
    ImageView back;
    TextInputEditText name, email, phone, bank_account, ifsc, vpa, address;
    String beneficiaryID, name1, email1, phone1, bank_account1, ifsc1, vpa1, address1;
    AppCompatButton submit;
    String tokenFinal = "";
    RelativeLayout progress;
    com.yashswi.dilpay.models.userDetails userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account_details);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //FINDING VIEWS
        back = findViewById(R.id.back);
        name = findViewById(R.id.e_name1);
        email = findViewById(R.id.e_email1);
        phone = findViewById(R.id.e_phone1);
        bank_account = findViewById(R.id.e_bankaccount1);
        ifsc = findViewById(R.id.e_ifsc1);
        vpa = findViewById(R.id.e_vpa1);
        address = findViewById(R.id.e_address1);
        submit = findViewById(R.id.submit);
        progress = findViewById(R.id.progress);
        userDetails = new userDetails(Add_account_details.this);
        phone.setText(userDetails.getNumber());
        name.setText(userDetails.getName());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name1 = name.getText().toString();
                email1 = email.getText().toString();
                phone1 = phone.getText().toString();
                bank_account1 = bank_account.getText().toString();
                ifsc1 = ifsc.getText().toString();
                vpa1 = vpa.getText().toString();
                address1 = address.getText().toString();
                if (phone1.equalsIgnoreCase("") || bank_account1.equalsIgnoreCase("") || ifsc1.equalsIgnoreCase("") || address1.equalsIgnoreCase("") || name1.equalsIgnoreCase("") || email1.equalsIgnoreCase("")) {

                    Toast.makeText(Add_account_details.this, "fill in all details", Toast.LENGTH_LONG).show();

//                    Toast.makeText(Add_account_details.this,token,Toast.LENGTH_SHORT).show();
                } else {
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    progress.setVisibility(View.VISIBLE);
//                    Toast.makeText(Add_account_details.this,""+name1+"/"+email1+"/"+phone1+"/"+bank_account1+"/"+ifsc1+"/"+address1,Toast.LENGTH_LONG).show();
                    getToken();
                }
            }
        });
    }

    //ADD BENEFICIARY TO CASHFREE
    private void addBeneficiary(String token, String name1, String email1, String phone1, String bank_account1, String ifsc1, String vpa1, String address1) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(cashFree.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        cashFree api = retrofit.create(cashFree.class);
        beneficiaryID = "DILPAY" + bank_account1+ifsc1;
        JSONObject test = new JSONObject();
        try {
            test.put("beneId", beneficiaryID);
            test.put("name", name1);
            test.put("email", email1);
            test.put("phone", phone1);
            test.put("bankAccount", bank_account1);
            test.put("ifsc", ifsc1);
            test.put("address1", address1);

        } catch (Exception e) {

        }

        Call<String> call = api.addBeneficiary("Bearer " + token, test.toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                Toast.makeText(Add_account_details.this,"got response from cashfree"+response.body(),Toast.LENGTH_SHORT).show();
                try {
                    JSONObject data = new JSONObject(response.body());
                    Log.e("bankAdd", data.toString());
                    if (data.getString("status").equalsIgnoreCase("SUCCESS")) {
                        //add bank details to database

                        JSONObject createData = new JSONObject();
                        createData.put("beneficiaryID", beneficiaryID);
                        createData.put("username", userDetails.getNumber());
                        createData.put("Name", name1);
                        createData.put("emial", email1);
                        createData.put("phone", phone1);
                        createData.put("accountNumber", bank_account1);
                        createData.put("IFSC", ifsc1);
                        createData.put("address", address1);
                        createData.put("Method", "Add");
                        addUserBankAccount(createData.toString());
                    } else {
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progress.setVisibility(View.GONE);
                        Toast.makeText(Add_account_details.this, data.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
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
                Toast.makeText(Add_account_details.this, message + ""+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addUserBankAccount(String data) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Log.e("bankDetails", data);
        Api_interface api = retrofit.create(Api_interface.class);
        Call<String> call = api.addBankDetails(data);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                progress.setVisibility(View.GONE);
                Log.e("bankDetails", response.body());
                if (response.body() != null) {
                    try {
                        JSONObject data = new JSONObject(response.body());
                        String status = data.getString("Status");
                        String message = data.getString("Message");
                        if (status.equalsIgnoreCase("True")) {
                            Toast.makeText(Add_account_details.this, message, Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(Add_account_details.this, message, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                progress.setVisibility(View.GONE);
                Toast.makeText(Add_account_details.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    void getToken() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);
        Call<String> call = api.generatePayoutToken();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    JSONObject data = new JSONObject(response.body());
                    if (data.getString("status").equalsIgnoreCase("SUCCESS")) {
                        JSONObject tokenObj = data.getJSONObject("data");
                        tokenFinal = tokenObj.getString("token");
                        addBeneficiary(tokenFinal, name1, email1, phone1, bank_account1, ifsc1, vpa1, address1);
//                        Toast.makeText(Add_account_details.this,tokenFinal,Toast.LENGTH_SHORT).show();
                    } else {
                        tokenFinal = "";
                    }
                } catch (Exception e) {
                    Toast.makeText(Add_account_details.this, e.toString(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Add_account_details.this, message + "", Toast.LENGTH_SHORT).show();
            }
        });

    }
}