package com.yashswi.dilpay.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.yashswi.dilpay.R;
import com.yashswi.dilpay.adapters.Bookings_list_adapter;
import com.yashswi.dilpay.adapters.buses_list_adapter;
import com.yashswi.dilpay.bus.Available_buses;

import java.util.ArrayList;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);
        rv = findViewById(R.id.bookings_list__recyclerview);
        rv.setHasFixedSize(true);
        back = findViewById(R.id.back);

        from.add("Hyderabad");
        to.add("Bangalore");
        from_time.add("06:30AM");
        to_time.add("09:30PM");
        status.add("Completed");
        journey_date.add("15-01-2021");
        travels.add("Sagar Tavels");

        from.add("Vijayawada");
        to.add("Hyderabad");
        from_time.add("07:30PM");
        to_time.add("05:00AM");
        status.add("Upcoming");
        journey_date.add("26-01-2021");
        travels.add("Aditya Tavels");

        from.add("Hyderabad");
        to.add("Chennai");
        from_time.add("09:30PM");
        to_time.add("04:30AM");
        status.add("Completed");
        journey_date.add("20-01-2021");
        travels.add("Sai Tavels");

        from.add("Hyderabad");
        to.add("Bangalore");
        from_time.add("06:30AM");
        to_time.add("09:30PM");
        status.add("Completed");
        journey_date.add("15-01-2021");
        travels.add("Jay Tavels");

        from.add("Vijayawada");
        to.add("Hyderabad");
        from_time.add("07:30PM");
        to_time.add("05:00AM");
        status.add("Upcoming");
        journey_date.add("26-01-2021");
        travels.add("Ajay Tavels");

        from.add("Hyderabad");
        to.add("Chennai");
        from_time.add("09:30PM");
        to_time.add("04:30AM");
        status.add("Completed");
        journey_date.add("20-01-2021");
        travels.add("Sushant Tavels");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bookings_list_adapter adapter = new Bookings_list_adapter(from, to, status, from_time, to_time, journey_date, travels, My_bookings.this);
        rv.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(My_bookings.this, RecyclerView.VERTICAL, false);
        rv.setLayoutManager(manager);


    }
}