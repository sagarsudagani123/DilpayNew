package com.yashswi.dilpay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.yashswi.dilpay.adapters.SliderAdapter;
import com.yashswi.dilpay.adapters.items_list_adapter;
import java.util.ArrayList;

public class Home_screen extends AppCompatActivity {
    ArrayList<Integer> buton_img = new ArrayList<>();
    ArrayList<String> buton_names = new ArrayList<>();
    TextView view_more;
    RecyclerView rv;
    ImageView menu,profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
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

        buton_img.add(R.drawable.bus);
        buton_img.add(R.drawable.mobile1);
        buton_img.add(R.drawable.dth1);
        buton_img.add(R.drawable.datacard1);
        buton_img.add(R.drawable.post1);
        buton_img.add(R.drawable.ele);
        buton_img.add(R.drawable.gas);

        buton_names.add("Bus");
        buton_names.add("Mobile");
        buton_names.add("DTH");
        buton_names.add("Datacard");
        buton_names.add("Postpaid");
        buton_names.add("Electricity");
        buton_names.add("Gas");

        items_list_adapter adapter = new items_list_adapter(buton_img, buton_names, this);
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
}