package com.yashswi.dilpay;

import androidx.appcompat.app.AppCompatActivity;
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
        rv=findViewById(R.id.recyclerview_more);
        back=findViewById(R.id.back);
        rv.setHasFixedSize(true);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buton_img.add(R.drawable.bus1);
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

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rv.setLayoutManager(gridLayoutManager);
        items_list_adapter adapter = new items_list_adapter(buton_img, buton_names, this);
        rv.setAdapter(adapter);


    }
}