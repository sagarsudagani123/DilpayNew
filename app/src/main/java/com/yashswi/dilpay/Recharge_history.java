package com.yashswi.dilpay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.yashswi.dilpay.Api_interface.Api_interface;
import com.yashswi.dilpay.Api_interface.Mobile_interface;
import com.yashswi.dilpay.adapters.RechargeHistoryAdapter;
import com.yashswi.dilpay.adapters.TransactionHistoryAdapter;
import com.yashswi.dilpay.gas.Gas_screen;
import com.yashswi.dilpay.models.userDetails;
import com.yashswi.dilpay.utils.CheckNetworkStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.UnknownHostException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static retrofit2.converter.scalars.ScalarsConverterFactory.create;

public class Recharge_history extends AppCompatActivity {
RecyclerView recharge_recyclerview;
    ArrayList<String> transactionId = new ArrayList<>();
    ArrayList<String> amount = new ArrayList<>();
    ArrayList<String> mobileNumber = new ArrayList<>();
    ArrayList<String> service = new ArrayList<>();

    ArrayList<String> dateTime = new ArrayList<>();
    ArrayList<String> message = new ArrayList<>();
    String serviceType;
    RelativeLayout progress;
    com.yashswi.dilpay.models.userDetails userDetails;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_history);
        if (!CheckNetworkStatus.getConnectivityStatusString(Recharge_history.this)) {
            Toast.makeText(Recharge_history.this, "No internet connection", Toast.LENGTH_SHORT).show();
            finish();
        }
        serviceType = (String) this.getIntent().getSerializableExtra("Service");

        recharge_recyclerview=findViewById(R.id.recharges__recyclerview);
        recharge_recyclerview.setHasFixedSize(true);
        userDetails = new userDetails(Recharge_history.this);
        back = findViewById(R.id.back);
        progress = findViewById(R.id.progress_layout);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Recharge_history.this, Home_screen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
//                finish();
            }
        });
        progress.setVisibility(View.VISIBLE);
        rechargeHistory();
    }

    private void rechargeHistory() {
        JSONObject createData = new JSONObject();
        try {
            createData.put("username", new userDetails(Recharge_history.this).getNumber());
            createData.put("Service", serviceType);
            Log.e("mobile", createData.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);
        Call<String> call = api.rechargeHistory(createData.toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                progress.setVisibility(View.GONE);
                Log.e("Transactions", response.body());
                if (response.body() != null) {
                    try {
                        JSONObject obj = new JSONObject(response.body());
                        if (obj.getString("Status").equalsIgnoreCase("True")) {
                            JSONArray jsonArray = obj.getJSONArray("Data");
                            if (jsonArray.length() <= 0) {
                                Toast.makeText(Recharge_history.this, "No Details Available", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject dataObject = jsonArray.getJSONObject(i);
                                    transactionId.add(dataObject.getString("TRId"));
                                    amount.add(dataObject.getString("Amount"));
                                    mobileNumber.add(dataObject.getString("Mobilenumber"));
                                    service.add(dataObject.getString("Service"));
                                    dateTime.add(dataObject.getString("Datetime"));
                                    message.add(dataObject.getString("Status"));

                                }
                            }

                            RechargeHistoryAdapter adapter = new RechargeHistoryAdapter(transactionId, amount, mobileNumber, dateTime,  message, Recharge_history.this);
                            recharge_recyclerview.setAdapter(adapter);
                            LinearLayoutManager manager = new LinearLayoutManager(Recharge_history.this, RecyclerView.VERTICAL, false);
                            recharge_recyclerview.setLayoutManager(manager);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(Recharge_history.this, "Something went wrong!!!!!!!!!! Try again" + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                    //GETTING DATA FROM API

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
                Toast.makeText(Recharge_history.this, message + "", Toast.LENGTH_SHORT).show();
            }
        });

    }
}