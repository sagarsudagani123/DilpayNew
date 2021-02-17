package com.yashswi.dilpay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.yashswi.dilpay.Api_interface.Api_interface;
import com.yashswi.dilpay.adapters.SliderAdapter;
import com.yashswi.dilpay.adapters.items_list_adapter;
import com.yashswi.dilpay.bus.Bus;
import com.yashswi.dilpay.models.cityNames;

import org.json.JSONArray;
import org.json.JSONException;

import java.net.UnknownHostException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static retrofit2.converter.scalars.ScalarsConverterFactory.create;

public class Home_screen extends AppCompatActivity {
    ArrayList<Integer> buton_img = new ArrayList<>();
    ArrayList<String> buton_names = new ArrayList<>();
    RecyclerView rv;
    ImageView menu,profile;
    private static final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        new Thread(runnable).start();
        sliderviewWork();
        rv=findViewById(R.id.recyclerview_dashboard);
        rv.setHasFixedSize(true);
        menu=findViewById(R.id.menu);
        profile=findViewById(R.id.profile_buton_dashboard);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home_screen.this,Profile.class);
                startActivity(i);

            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home_screen.this,More.class);
                startActivity(i);
            }
        });


        buton_img.add(R.drawable.bus_new);
        buton_img.add(R.drawable.mobile_new);
        buton_img.add(R.drawable.dth_new);
        buton_img.add(R.drawable.datacard_new);
        buton_img.add(R.drawable.postpaid_new);
        buton_img.add(R.drawable.elc_new);
        buton_img.add(R.drawable.gas_new);
        buton_img.add(R.drawable.money_trans_new);

        buton_names.add("Bus");
        buton_names.add("Mobile");
        buton_names.add("DTH");
        buton_names.add("Datacard");
        buton_names.add("Postpaid");
        buton_names.add("Electricity");
        buton_names.add("Gas");
        buton_names.add("Money Transfer");

        items_list_adapter adapter = new items_list_adapter(buton_img, buton_names,"Main", this);
        rv.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(this,3);
        rv.setLayoutManager(manager);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                            Intent i = new Intent(Home_screen.this, Notification.class);
                            startActivity(i);
                            finish();
                        break;
                    case R.id.action_more:
                            Intent j = new Intent(Home_screen.this, More.class);
                            startActivity(j);
                        break;
                    case R.id.action_share:
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT,
                                "Hey check out the Best Payments app at:" + "\n" + " https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName());
                        sendIntent.setType("text/plain");
                        startActivity(sendIntent);
//                                Intent j = new Intent(Home.this, Payment_details.class);
//                                startActivity(j)
                        break;
                   }
                return true;
            }
        });
    }

    public void sliderviewWork() {
        SliderView sliderView = findViewById(R.id.imageSlider);
        SliderAdapter sliderAdapter = new SliderAdapter(this);
        sliderAdapter.addItem(R.drawable.slider1);  // slide1,slide2,slide3,slide4
        sliderAdapter.addItem(R.drawable.slider2);
        sliderAdapter.addItem(R.drawable.slider3);
        sliderAdapter.addItem(R.drawable.slider4);
        sliderView.setSliderAdapter(sliderAdapter);

        sliderView.startAutoCycle();
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(this, "Permission granted.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Try again!! Please accept permission to login", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    public ArrayList<String> getPlaceNames(){
        ArrayList<String> names=new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);
        Call<String> call=api.getPlaces();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body()!=null){
                    Log.e("check",response.body());
                    cityNames obj=cityNames.getInstance();
                    try {
                        JSONArray data=new JSONArray(response.body());
                        for(int i=0;i<data.length();i++){
                            names.add(data.get(i).toString());
                            obj.setNames(data.get(i).toString());
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                String message="";
                if(t instanceof UnknownHostException)
                {
                    message = "No internet connection";
                }
                else{
                    message = "Something went wrong! try again";
                }
                Toast.makeText(Home_screen.this, message+"", Toast.LENGTH_SHORT).show();
            }
        });
        return names;
    }
    Runnable runnable = new Runnable()
    {
        @Override
        public void run()
        {
//            Toast.makeText(Available_buses.this,"Running in background",Toast.LENGTH_SHORT).show();
            getPlaceNames();
        }
    };
}