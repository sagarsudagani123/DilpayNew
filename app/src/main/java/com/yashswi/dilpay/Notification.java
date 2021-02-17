package com.yashswi.dilpay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.yashswi.dilpay.Api_interface.Api_interface;
import com.yashswi.dilpay.adapters.NotificationsListAdapter;
import com.yashswi.dilpay.models.userDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Notification extends AppCompatActivity {
    RecyclerView notificationsList;
    ArrayList<String> title=new ArrayList<>();
    ArrayList<String> body=new ArrayList<>();
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        notificationsList=findViewById(R.id.notifications__recyclerview);
        notificationsList.setHasFixedSize(true);
        back=findViewById(R.id.back);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getNotifications();
    }

    private void getNotifications() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);
        Call<String> call = api.getNotifications(new userDetails(Notification.this).getNumber());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body()!=null){
                    try {
                        JSONObject object=new JSONObject(response.body());
                        if(object.getString("Status").equalsIgnoreCase("True")){
                            JSONArray jsonArray=object.getJSONArray("Data");
                            for(int data=0;data<jsonArray.length();data++){
                                JSONObject dataObj=jsonArray.getJSONObject(data);
                                title.add(dataObj.getString("Title"));
                                body.add(dataObj.getString("Message"));
                            }
                            NotificationsListAdapter adapter = new NotificationsListAdapter(title,body, Notification.this);
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

            }
        });
    }


}