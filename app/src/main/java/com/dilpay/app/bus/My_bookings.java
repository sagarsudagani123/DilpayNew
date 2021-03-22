package com.dilpay.app.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.dilpay.app.Api_interface.Api_interface;
import com.dilpay.app.R;
import com.dilpay.app.adapters.Bookings_list_adapter;
import com.dilpay.app.models.MyBookingsModel;
import com.dilpay.app.models.userDetails;

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

public class My_bookings extends AppCompatActivity {
    RecyclerView rv;
    ImageView back;
    ArrayList<String> from = new ArrayList<>();
    ArrayList<String> to = new ArrayList<>();
    ArrayList<String> from_time = new ArrayList<>();
    ArrayList<String> to_time = new ArrayList<>();
    ArrayList<String> status = new ArrayList<>();
    ArrayList<String> journey_date = new ArrayList<>();
    ArrayList<String> travels = new ArrayList<>();
    ArrayList<MyBookingsModel> model = new ArrayList<>();
    com.dilpay.app.models.userDetails userDetails;

    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        userDetails = new userDetails(My_bookings.this);
        rv = findViewById(R.id.bookings_list__recyclerview);
        rv.setHasFixedSize(true);
        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        myBookings();
    }

    private void myBookings() {
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
//        Call<String> call = api.myBookings("6302840341");
        Call<String> call = api.myBookings(userDetails.getNumber());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body() != null) {
                    try {
                        JSONObject obj = new JSONObject(response.body());

                        if (obj.getString("Status").equalsIgnoreCase("True")) {
                            JSONArray jsonArray = obj.getJSONArray("Data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject dataObject = jsonArray.getJSONObject(i);
                                from.add(dataObject.getString("SourceName"));
                                to.add(dataObject.getString("DestinationName"));
                                from_time.add(dataObject.getString("DepartureTime"));
                                to_time.add(dataObject.getString("ArrivalTime"));
                                status.add(dataObject.getString("Message"));
                                journey_date.add(dataObject.getString("JourneyDate"));
                                travels.add(dataObject.getString("Operator"));
                            }
                            Bookings_list_adapter adapter = new Bookings_list_adapter(from, to, status, from_time, to_time, journey_date, travels, model, response.body(), My_bookings.this);
                            rv.setAdapter(adapter);
                            LinearLayoutManager manager = new LinearLayoutManager(My_bookings.this, RecyclerView.VERTICAL, false);
                            rv.setLayoutManager(manager);
                        } else {
                                Toast.makeText(My_bookings.this, "No Details Availble", Toast.LENGTH_SHORT).show();
                                finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                String message = "";
                if (t instanceof UnknownHostException) {
                    message = "No internet connection!";
                } else {
                    message = "Something went wrong! try again";
                }
                Toast.makeText(My_bookings.this, message, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}