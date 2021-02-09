package com.yashswi.dilpay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.yashswi.dilpay.adapters.items_list_adapter;

import java.util.ArrayList;

public class View_more extends AppCompatActivity {
    ArrayList<Integer> buton_img = new ArrayList<>();
    ArrayList<String> buton_names = new ArrayList<>();
    RecyclerView rv;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_more);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        rv=findViewById(R.id.recyclerview_more);
        back=findViewById(R.id.back);
        rv.setHasFixedSize(true);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rv.setLayoutManager(gridLayoutManager);
        items_list_adapter adapter = new items_list_adapter(buton_img, buton_names,"Main", this);
        rv.setAdapter(adapter);


    }
}