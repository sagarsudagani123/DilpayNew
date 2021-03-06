package com.yashswi.dilpay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.yashswi.dilpay.Api_interface.Api_interface;
import com.yashswi.dilpay.bank.WithdrawAmount;
import com.yashswi.dilpay.models.userDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ConvertRewards extends AppCompatActivity {
ImageView back;
TextView availableRewards,eligibilityPercentage,eligibilityRewards;
AppCompatButton convert_rewards;
com.yashswi.dilpay.models.userDetails userDetails;
RelativeLayout progress;
    String totalPoints;
    String percentage;
    String eligiblePoints;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_rewards);
        userDetails=new userDetails(ConvertRewards.this);
        back=findViewById(R.id.back);
        availableRewards=findViewById(R.id.availableRewardsAmount);
        eligibilityPercentage=findViewById(R.id.percentage);
        eligibilityRewards=findViewById(R.id.eligibleRewardsAmount);
        convert_rewards=findViewById(R.id.buttonConvert);
        progress=findViewById(R.id.progress);
        getRewardDetails();
        progress.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ConvertRewards.this,Profile.class);
                startActivity(intent);
                finish();
            }
        });
        convert_rewards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int total_points=Integer.parseInt(totalPoints);
                if (total_points<=0){
                    Toast.makeText(ConvertRewards.this,"Not eligible to convert",Toast.LENGTH_SHORT).show();
                }else {
                    convertRewards();
                }

            }
        });

    }

    private void convertRewards() {
        progress.setVisibility(View.VISIBLE);
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("username",userDetails.getNumber());
            jsonObject.put("Totalpoints",totalPoints);
            jsonObject.put("Percent",percentage);
            jsonObject.put("Eligibility",eligiblePoints);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("rewardsCheck",jsonObject.toString());
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api_interface api=retrofit.create(Api_interface.class);
        Call<String> call=api.convertRewards(jsonObject.toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                progress.setVisibility(View.INVISIBLE);
                Toast.makeText(ConvertRewards.this,response.body(),Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject1=new JSONObject(response.body());
//                    if (jsonObject1.getString("Status").equalsIgnoreCase("True")){
                        Intent intent=new Intent(ConvertRewards.this,Profile.class);
                        startActivity(intent);
                        finish();

//                    } else {

//                    }
                } catch (JSONException e) {
//                    Toast.makeText(ConvertRewards.this,e.toString(),Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progress.setVisibility(View.GONE);
                String message="";
                if(t instanceof UnknownHostException)
                {
                    message = "No internet connection!";
                }
                else{
                    message = "Something went wrong! try again";
                }
                Toast.makeText(ConvertRewards.this, message+""+t.toString(), Toast.LENGTH_SHORT).show();            }
        });
    }


    private void getRewardDetails() {

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api_interface api=retrofit.create(Api_interface.class);
        Call<String> call=api.getRewardDetails(userDetails.getNumber());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                   progress.setVisibility(View.INVISIBLE);
                try {
                    JSONObject dataObj=new JSONObject(response.body());
                    if (dataObj.getString("Status").equalsIgnoreCase("True")){
                         totalPoints=dataObj.getString("Totalpoints");
                         percentage=dataObj.getString("Percent");
                         eligiblePoints=dataObj.getString("Eligibility");

                        availableRewards.setText(totalPoints);
                        eligibilityPercentage.setText(percentage+"%");
                        eligibilityRewards.setText(eligiblePoints);
                    }
                } catch (JSONException e) {
                    Toast.makeText(ConvertRewards.this,e.toString(),Toast.LENGTH_SHORT).show();
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
                Toast.makeText(ConvertRewards.this, message + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(ConvertRewards.this,Profile.class);
        startActivity(intent);
        finish();
    }
}