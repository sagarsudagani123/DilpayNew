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
import com.yashswi.dilpay.adapters.NotificationsListAdapter;
import com.yashswi.dilpay.bus.Available_buses;
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
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Notification extends AppCompatActivity {
    RecyclerView notificationsList;
    ArrayList<String> title = new ArrayList<>();
    ArrayList<String> body = new ArrayList<>();
    ArrayList<String> date = new ArrayList<>();
    ImageView back;
    RelativeLayout progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        notificationsList = findViewById(R.id.notifications__recyclerview);
        notificationsList.setHasFixedSize(true);
        back = findViewById(R.id.back);
        progress = findViewById(R.id.progress);

        if (!CheckNetworkStatus.getConnectivityStatusString(Notification.this)) {
            Toast.makeText(Notification.this, "No internet connection", Toast.LENGTH_SHORT).show();
            finish();
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notification.this, Home_screen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
//                finish();
            }
        });

        getNotifications();
    }

    private void getNotifications() {
        progress.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);
        Call<String> call = api.getNotifications(new userDetails(Notification.this).getNumber());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                progress.setVisibility(View.GONE);
                Log.e("Transactions", response.body());
                if (response.body() != null) {
                    try {
                        JSONObject object = new JSONObject(response.body());
                        if (object.getString("Status").equalsIgnoreCase("True")) {
                            JSONArray jsonArray = object.getJSONArray("Data");
                            for (int data = 0; data < jsonArray.length(); data++) {
                                JSONObject dataObj = jsonArray.getJSONObject(data);
                                title.add(dataObj.getString("Title"));
                                body.add(dataObj.getString("Message"));
                                date.add(dataObj.getString("DateTime"));

                            }
                            NotificationsListAdapter adapter = new NotificationsListAdapter(title, body, date, Notification.this);
                            notificationsList.setAdapter(adapter);
                            LinearLayoutManager manager = new LinearLayoutManager(Notification.this, RecyclerView.VERTICAL, false);
                            notificationsList.setLayoutManager(manager);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
                Toast.makeText(Notification.this, message + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Notification.this, Home_screen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
//        finish();
    }
}