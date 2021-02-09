package com.yashswi.dilpay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.installations.FirebaseInstallations;
import com.google.firebase.messaging.FirebaseMessaging;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.yashswi.dilpay.adapters.SliderAdapter;
import com.yashswi.dilpay.adapters.items_list_adapter;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Home_screen extends AppCompatActivity {
    ArrayList<Integer> buton_img = new ArrayList<>();
    ArrayList<String> buton_names = new ArrayList<>();
    TextView view_more;
    RecyclerView rv;
    ImageView menu,profile;
    private static final int REQUEST_CODE = 101;
//    String fromCategory="Main";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //====================
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("t", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
//                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d("tokenfirebase", token);
//                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
        //====================
        sliderviewWork();
        rv=findViewById(R.id.recyclerview_dashboard);
        rv.setHasFixedSize(true);
        view_more=findViewById(R.id.view_more);
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
                Intent i = new Intent(Home_screen.this,View_more.class);
                startActivity(i);
            }
        });

        view_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home_screen.this,View_more.class);
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
                            Intent i = new Intent(Home_screen.this, Home_screen.class);
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

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case REQUEST_CODE: {
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(this, "Permission granted.", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(this, "Permission denied.", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    }
}