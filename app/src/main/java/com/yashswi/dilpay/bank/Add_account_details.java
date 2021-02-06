package com.yashswi.dilpay.bank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.yashswi.dilpay.Api_interface.cashFree;
import com.yashswi.dilpay.R;
import com.yashswi.dilpay.models.Add_account_model;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Add_account_details extends AppCompatActivity {
    ImageView back;
    TextInputEditText name,email,phone,bank_account,ifsc,vpa,address;
    String name1,email1,phone1,bank_account1,ifsc1,vpa1,address1;
    AppCompatButton submit;
    String tokenFinal="";
    RelativeLayout progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account_details);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //FINDING VIEWS
        back=findViewById(R.id.back);
        name=findViewById(R.id.e_name1);
        email=findViewById(R.id.e_email1);
        phone=findViewById(R.id.e_phone1);
        bank_account=findViewById(R.id.e_bankaccount1);
        ifsc=findViewById(R.id.e_ifsc1);
        vpa=findViewById(R.id.e_vpa1);
        address=findViewById(R.id.e_address1);
        submit=findViewById(R.id.submit);
        progress=findViewById(R.id.progress);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name1=name.getText().toString();
                email1=email.getText().toString();
                phone1=phone.getText().toString();
                bank_account1=bank_account.getText().toString();
                ifsc1=ifsc.getText().toString();
                vpa1=vpa.getText().toString();
                address1=address.getText().toString();
                if(phone1.equalsIgnoreCase("")||bank_account1.equalsIgnoreCase("")||ifsc1.equalsIgnoreCase("")||address1.equalsIgnoreCase("")||name1.equalsIgnoreCase("")||email1.equalsIgnoreCase("")){

                    Toast.makeText(Add_account_details.this,"fill in all details",Toast.LENGTH_LONG).show();

//                    Toast.makeText(Add_account_details.this,token,Toast.LENGTH_SHORT).show();
                }
                else {
                    progress.setVisibility(View.VISIBLE);
//                    Toast.makeText(Add_account_details.this,""+name1+"/"+email1+"/"+phone1+"/"+bank_account1+"/"+ifsc1+"/"+address1,Toast.LENGTH_LONG).show();
                    getToken();


                }
            }
        });
    }

    //ADD BENEFICIARY TO CASHFREE
    private void addBeneficiary(String token,String name1, String email1, String phone1, String bank_account1, String ifsc1, String vpa1, String address1) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://payout-gamma.cashfree.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        cashFree api = retrofit.create(cashFree.class);
        Add_account_model add_account_model=new Add_account_model();
        add_account_model.setBeneId("20213234"+phone1);
        add_account_model.setName(name1);
        add_account_model.setEmail(email1);
        add_account_model.setPhone(phone1);
        add_account_model.setPhone(bank_account1);
        add_account_model.setIfsc(ifsc1);
        add_account_model.setAddress1(address1);
        JSONObject test=new JSONObject();
        try {
            test.put("beneId", "2021" + phone1);
            test.put("name", name1);
            test.put("email", email1);
            test.put("phone", phone1);
            test.put("bankAccount", bank_account1);
            test.put("ifsc", ifsc1);
            test.put("address1", address1);
        }catch (Exception e){

        }

        Call<String> call = api.addBeneficiary("Bearer "+token,test.toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                progress.setVisibility(View.GONE);
                try {
                    JSONObject data=new JSONObject(response.body());
                    if(data.getString("status").equalsIgnoreCase("SUCCESS")){
                        //add bank details to database
                        Toast.makeText(Add_account_details.this,data.getString("message"),Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else{
                        Toast.makeText(Add_account_details.this,data.getString("message"),Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progress.setVisibility(View.GONE);
                Toast.makeText(Add_account_details.this,"Failed to connect..."+t.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    void getToken(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://payout-gamma.cashfree.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        cashFree api = retrofit.create(cashFree.class);
        Call<String> call = api.getToken("CF4207C0D8VIUK9404JSBD00DG","51207f8c3ee789bc77cf7d3c54c0bd59d106b9fa");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(Add_account_details.this,response.body(),Toast.LENGTH_SHORT).show();
                try {
                    JSONObject data=new JSONObject(response.body());
                    if (data.getString("status").equalsIgnoreCase("SUCCESS")) {
                        JSONObject tokenObj=data.getJSONObject("data");
                        tokenFinal=tokenObj.getString("token");
                        addBeneficiary(tokenFinal,name1,email1,phone1,bank_account1,ifsc1,vpa1,address1);
//                        Toast.makeText(Add_account_details.this,tokenFinal,Toast.LENGTH_SHORT).show();
                    }
                    else{
                        tokenFinal="";
                    }
                }
                catch (Exception e){
                    Toast.makeText(Add_account_details.this,e.toString(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(Add_account_details.this,"Failed to connect..."+t.toString(),Toast.LENGTH_SHORT).show();

            }
        });

    }
}