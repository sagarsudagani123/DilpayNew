package com.dilpay.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dilpay.app.utils.RetroFitClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.dilpay.app.Api_interface.Api_interface;
import com.dilpay.app.adapters.SliderAdapter;
import com.dilpay.app.adapters.items_list_adapter;
import com.dilpay.app.bus.Bus;
import com.dilpay.app.models.cityNames;
import com.dilpay.app.utils.CheckNetworkStatus;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static retrofit2.converter.scalars.ScalarsConverterFactory.create;

public class Home_screen extends AppCompatActivity {
    ArrayList<Integer> buton_img = new ArrayList<>();
    ArrayList<String> buton_names = new ArrayList<>();
    RecyclerView rv;
    RelativeLayout bus,transport,hire,tourism;
    ImageView menu, profile;
    private static final int REQUEST_CODE = 101;
    BoomMenuButton boomMenuButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        sliderviewWork();

        if (!CheckNetworkStatus.getConnectivityStatusString(Home_screen.this)) {
            Toast.makeText(Home_screen.this, "No internet connection", Toast.LENGTH_SHORT).show();
        } else {
            new Thread(runnable).start();
        }

        rv = findViewById(R.id.recyclerview_dashboard);
        rv.setHasFixedSize(true);
        profile = findViewById(R.id.profile_buton_dashboard);
        bus=findViewById(R.id.busLayout);
        transport=findViewById(R.id.transportLayout);
        hire=findViewById(R.id.hireLayout);
        tourism=findViewById(R.id.tourismLayout);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home_screen.this, Profile.class);
                i.putExtra("toProfile", "fromLogin");
                startActivity(i);

            }
        });

        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home_screen.this, Bus.class);
                startActivity(i);
            }
        });
        transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home_screen.this, "Available in next update", Toast.LENGTH_SHORT).show();
            }
        });
        hire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home_screen.this, "Available in next update", Toast.LENGTH_SHORT).show();
            }
        });
        tourism.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home_screen.this, "Available in next update", Toast.LENGTH_SHORT).show();
            }
        });

        ArrayList<String> headings = new ArrayList<>();
        ArrayList<Integer> imagesnew = new ArrayList<>();
        headings.add("About Us");
        headings.add("Contact Us");
        headings.add("Privacy Policy");
        headings.add("Terms and Conditions");
        headings.add("FAQ");
        headings.add("Support");

        imagesnew.add(R.drawable.about);
        imagesnew.add(R.drawable.contact);
        imagesnew.add(R.drawable.privacy);
        imagesnew.add(R.drawable.terms);
        imagesnew.add(R.drawable.faq);
        imagesnew.add(R.drawable.customer);

//        buton_im.add(R.drawable.bus_new);
//        buton_im.add(R.drawable.tour_bus);
//        buton_im.add(R.drawable.trans);
//        buton_im.add(R.drawable.hire_bu);
//        buton_im.add(R.drawable.trans);
//
//        buton_name.add("Bus");
//        buton_name.add("Tourism");
//        buton_name.add("Transport");
//        buton_name.add("Hire Bus");
//        buton_name.add("Money Transfer");
//
//        Transport_items_adapter adapter1 = new Transport_items_adapter(buton_im, buton_name, Home_screen.this,"Main" );
//        transport_recycler.setAdapter(adapter1);
//        LinearLayoutManager manager1 = new LinearLayoutManager(Home_screen.this, RecyclerView.HORIZONTAL, false);
//        transport_recycler.setLayoutManager(manager1);


        boomMenuButton = findViewById(R.id.bmb);
        boomMenuButton.setDimColor(Color.parseColor("#90000000"));
        for (int i = 0; i < boomMenuButton.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .normalImageRes(imagesnew.get(i))//use array list

                    .textSize(14)
                    .listener(index -> {
                                Intent intent = null;
                                switch (index) {
                                    case 0:
                                        intent = new Intent(Home_screen.this, About_us.class);
                                        startActivity(intent);
                                        break;
                                    case 1:
                                        intent = new Intent(Home_screen.this, Contact_us.class);
                                        startActivity(intent);
                                        break;
                                    case 2:
                                        intent = new Intent(Home_screen.this, Privacy_policy.class);
                                        startActivity(intent);
                                        break;
                                    case 3:
                                        intent = new Intent(Home_screen.this, Terms_conditions.class);
                                        startActivity(intent);
                                        break;
                                    case 4:
                                        intent = new Intent(Home_screen.this, Faq.class);
                                        startActivity(intent);
                                        break;
                                    case 5:
                                        intent = new Intent(Home_screen.this, Support_screen.class);
                                        startActivity(intent);
                                        break;
                                }
                            }
                    )
                    .rotateText(true)
                    .normalText(headings.get(i));//use array list
            boomMenuButton.addBuilder(builder);
        }


        buton_img.add(R.drawable.mobile);
        buton_img.add(R.drawable.dth);
        buton_img.add(R.drawable.datacard);
        buton_img.add(R.drawable.postpaid);
        buton_img.add(R.drawable.electricity);
        buton_img.add(R.drawable.gas);
        buton_img.add(R.drawable.money_transfer);


        buton_names.add("Mobile");
        buton_names.add("DTH");
        buton_names.add("Datacard");
        buton_names.add("Postpaid");
        buton_names.add("Electricity");
        buton_names.add("Gas");
        buton_names.add("Money Transfer");

        items_list_adapter adapter = new items_list_adapter(buton_img, buton_names, "Main", this);
        rv.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        rv.setLayoutManager(manager);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = null;
                switch (item.getItemId()) {
                    case R.id.action_home:
                        intent = new Intent(Home_screen.this, Home_screen.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.notification:
                        intent = new Intent(Home_screen.this, Notification.class);
                        startActivity(intent);
                        break;
                    case R.id.action_transaction:
                        intent = new Intent(Home_screen.this, TransactionsHistory.class);
                        startActivity(intent);
                        break;
                    case R.id.action_share:
                        intent = new Intent();
                        intent.setAction(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_TEXT,
                                "Hey check out the Best Payments app at:" + "\n" + " https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName());
                        intent.setType("text/plain");
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }

    private void TriggarNotification() {
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
        Call<String> call = api.Notification();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                Toast.makeText(Home_screen.this,"Notification Triggered",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
//                Toast.makeText(Home_screen.this,"Notification Triggered Failed"+t.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void sliderviewWork() {
        SliderView sliderView = findViewById(R.id.imageSlider);
        SliderAdapter sliderAdapter = new SliderAdapter(this);
        sliderAdapter.addItem(R.drawable.banner3);
        sliderAdapter.addItem(R.drawable.banner4);
        sliderAdapter.addItem(R.drawable.banner5);
        sliderAdapter.addItem(R.drawable.banner1);  // slide1,slide2,slide3,slide4
        sliderAdapter.addItem(R.drawable.pin);
        sliderView.setSliderAdapter(sliderAdapter);

        sliderView.startAutoCycle();
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
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

    public ArrayList<String> getPlaceNames() {

        ArrayList<String> names = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);
        Call<String> call = api.getPlaces();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body() != null) {
                    Log.e("check", response.body());
                    cityNames obj = cityNames.getInstance();
                    try {
                        JSONArray data = new JSONArray(response.body());
                        for (int i = 0; i < data.length(); i++) {
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
                getPlaceNames();
            }
        });
        return names;
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            getPlaceNames();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        TriggarNotification();
    }
}