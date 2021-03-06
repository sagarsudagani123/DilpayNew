package com.dilpay.app;

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

import com.dilpay.app.Api_interface.Api_interface;
import com.dilpay.app.adapters.TransactionHistoryAdapter;
import com.dilpay.app.models.userDetails;
import com.dilpay.app.utils.CheckNetworkStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static retrofit2.converter.gson.GsonConverterFactory.create;

public class TransactionsHistory extends AppCompatActivity {

    RecyclerView transactionsList;
    ArrayList<String> transactionId = new ArrayList<>();
    ArrayList<String> creditAmount = new ArrayList<>();
    ArrayList<String> debitAmount = new ArrayList<>();
    ArrayList<String> date = new ArrayList<>();
    ArrayList<String> message = new ArrayList<>();
    String number;
    RelativeLayout progress;
    com.dilpay.app.models.userDetails userDetails;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions_history);

        if (!CheckNetworkStatus.getConnectivityStatusString(TransactionsHistory.this)) {
            Toast.makeText(TransactionsHistory.this, "No internet connection", Toast.LENGTH_SHORT).show();
            finish();
        }

        transactionsList = findViewById(R.id.transactions__recyclerview);
        transactionsList.setHasFixedSize(true);
        userDetails = new userDetails(TransactionsHistory.this);
        back = findViewById(R.id.back);
        progress = findViewById(R.id.progress_layout);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransactionsHistory.this, Home_screen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
//                finish();
            }
        });

        progress.setVisibility(View.VISIBLE);
        transactionHistory();
    }

    private void transactionHistory() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .callTimeout(2, TimeUnit.MINUTES)
                .connectTimeout(90, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .client(httpClient.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);
        Call<String> call = api.getTransactions(userDetails.getNumber());
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
                            if(jsonArray.length()<=0){
                                Toast.makeText(TransactionsHistory.this,"No Details Available",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject dataObject = jsonArray.getJSONObject(i);
                                    transactionId.add(dataObject.getString("Trid"));
                                    creditAmount.add(dataObject.getString("Credit"));
                                    debitAmount.add(dataObject.getString("Debit"));

                                    message.add(dataObject.getString("Message"));
                                    String dateTime=dataObject.getString("DateTime");
                                    String[] parts = dateTime.split(" ");
                                    String dateFinal=parts[0];
                                    String time=parts[1];

                                    String[] timeSeperate=time.split(":");
                                    int finalTime=Integer.parseInt(timeSeperate[0]);
                                    if(Integer.parseInt(timeSeperate[0])>12){
                                        finalTime=finalTime-12;
                                        time=finalTime+":"+timeSeperate[1]+":"+timeSeperate[2]+" PM";
                                    }
                                    else if(Integer.parseInt(timeSeperate[0])==0){
                                        finalTime=12;
                                        time=finalTime+":"+timeSeperate[1]+":"+timeSeperate[2]+" AM";
                                    }
                                    else if(Integer.parseInt(timeSeperate[0])==12){
                                        time=finalTime+":"+timeSeperate[1]+":"+timeSeperate[2]+" PM";
                                    }
                                    else{
                                        time=finalTime+":"+timeSeperate[1]+":"+timeSeperate[2]+" AM";
                                    }
                                    date.add(dateFinal+"  "+time);

                                }
                            }

                            TransactionHistoryAdapter adapter = new TransactionHistoryAdapter(transactionId, creditAmount, debitAmount, date, message, TransactionsHistory.this);
                            transactionsList.setAdapter(adapter);
                            LinearLayoutManager manager = new LinearLayoutManager(TransactionsHistory.this, RecyclerView.VERTICAL, false);
                            transactionsList.setLayoutManager(manager);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(TransactionsHistory.this, "Something went wrong! Try again" + e.toString(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(TransactionsHistory.this, message + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TransactionsHistory.this, Home_screen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}