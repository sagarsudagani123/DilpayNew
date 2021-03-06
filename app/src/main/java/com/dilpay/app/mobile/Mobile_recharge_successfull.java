package com.dilpay.app.mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dilpay.app.Api_interface.Api_interface;
import com.dilpay.app.R;
import com.dilpay.app.models.userDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static retrofit2.converter.scalars.ScalarsConverterFactory.create;

public class Mobile_recharge_successfull extends AppCompatActivity {
    TextView status, transaction_id, number, amount, order,statusSub,opid1;
    ImageView  success,failure,back;
    ProgressBar progressRecharge;
    String status1, number1, amount1, order1, opid;
    LinearLayout details,opidLayout;
    String transaction1;
    MediaPlayer mediaPlayerSuccess,mediaPlayerFailure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_recharge_successfull);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        status = findViewById(R.id.success);
        transaction_id = findViewById(R.id.transaction_id1);
        number = findViewById(R.id.number1);
        amount = findViewById(R.id.amount1);
        progressRecharge=findViewById(R.id.ProgressRecharge);
        order = findViewById(R.id.order1);
        success=findViewById(R.id.success1);
        failure=findViewById(R.id.failure);
        back = findViewById(R.id.back);
        details = findViewById(R.id.tran_details);
        statusSub=findViewById(R.id.statusSub);
        opid1=findViewById(R.id.opid1);
        opidLayout=findViewById(R.id.opidLayout);
        status1 = (String) this.getIntent().getSerializableExtra("status");
        transaction1 = this.getIntent().getStringExtra("txid");
        number1 = (String) this.getIntent().getSerializableExtra("number");
        amount1 = (String) this.getIntent().getSerializableExtra("amount");
        order1 = (String) this.getIntent().getSerializableExtra("orderid");
        opid = this.getIntent().getStringExtra("opid");
        mediaPlayerSuccess=MediaPlayer.create(this,R.raw.success_tone1);
        mediaPlayerFailure=MediaPlayer.create(this,R.raw.success_tone);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (status1.equalsIgnoreCase("failure")) {
            progressRecharge.setVisibility(View.GONE);
            failure.setVisibility(View.VISIBLE);
            status.setText(status1);
            statusSub.setText("Recharge Failed");
            statusSub.setTextColor(getResources().getColor(R.color.error));
            details.setVisibility(View.VISIBLE);
            transaction_id.setText(String.valueOf(transaction1));
            number.setText(String.valueOf(number1));
            amount.setText(amount1);
            opid1.setText(opid);
            order.setText(order1);
            mediaPlayerFailure.start();
//            opid.setText(String.valueOf(opID));
        } else if (status1.equalsIgnoreCase("Pending")) {
            progressRecharge.setVisibility(View.VISIBLE);
            status.setText("ACCEPTED");
            statusSub.setText("Recharge Processing..");
            statusSub.setTextColor(getResources().getColor(R.color.orange));
            transaction_id.setText(String.valueOf(transaction1));
            number.setText(String.valueOf(number1));
            amount.setText(amount1);
            order.setText(order1);
            getStatusUpdate(new userDetails(Mobile_recharge_successfull.this).getNumber(),order1);
        } else {
            progressRecharge.setVisibility(View.GONE);
            success.setVisibility(View.VISIBLE);
            status.setText(status1);
            opidLayout.setVisibility(View.VISIBLE);
            opid1.setText(opid);
            statusSub.setText("Recharge Success");
            statusSub.setTextColor(getResources().getColor(R.color.green));
            transaction_id.setText(String.valueOf(transaction1));
            number.setText(String.valueOf(number1));
            amount.setText(amount1);
            order.setText(order1);
            mediaPlayerSuccess.start();
        }


    }

    private void getStatusUpdate(String username, String order1) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .callTimeout(2, TimeUnit.MINUTES)
                .connectTimeout(90, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .client(httpClient.build())
                .addConverterFactory(create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);
        JSONObject data=new JSONObject();
        try {
            data.put("username",username);
            data.put("orderid",order1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("StatusResponse==",data.toString());
        Call<String> call = api.RechargeReportCheck(data.toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("StatusResponse",response.body());
                try {
                    JSONObject dataObj=new JSONObject(response.body());
                    if(dataObj.getString("status").equalsIgnoreCase("Success")){
                        statusSub.setText("Recharge Success");
                        status.setText("Success");
                        statusSub.setTextColor(getResources().getColor(R.color.green));
                        progressRecharge.setVisibility(View.GONE);
                        success.setVisibility(View.VISIBLE);
                        opidLayout.setVisibility(View.VISIBLE);
                        opid1.setText(opid);
                        mediaPlayerSuccess.start();
                    }
                    else if(dataObj.getString("status").equalsIgnoreCase("Failure")){
                        statusSub.setText("Recharge Failed");
                        statusSub.setTextColor(getResources().getColor(R.color.error));
                        status.setText("Failed");
                        progressRecharge.setVisibility(View.GONE);
                        failure.setVisibility(View.VISIBLE);
                        mediaPlayerFailure.start();
                    }
                    else{
                        getStatusUpdate(new userDetails(Mobile_recharge_successfull.this).getNumber(),order1);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("StatusResponse",e.toString());
                    Toast.makeText(Mobile_recharge_successfull.this,"Something went wrong!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                String message = "";
                if (t instanceof UnknownHostException) {
                    message = "No internet connection!";
                } else {
                    message = "Something went wrong!";
                }
                Toast.makeText(Mobile_recharge_successfull.this, message, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}