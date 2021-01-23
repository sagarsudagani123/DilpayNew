package com.yashswi.dilpay.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.yashswi.dilpay.bus.Booking_details;
import com.yashswi.dilpay.R;
import com.yashswi.dilpay.bus.Bus_seating;

import java.util.ArrayList;

public class Bookings_list_adapter extends RecyclerView.Adapter<Bookings_list_adapter.MyViewHolder> {
    ArrayList<String> from;
    ArrayList<String> to;
    ArrayList<String> status;
    ArrayList<String> from_time;
    ArrayList<String> to_time;
    ArrayList<String> date;
    ArrayList<String> travels;
    Context context;

    public Bookings_list_adapter(ArrayList<String> from, ArrayList<String> to, ArrayList<String> status, ArrayList<String> from_time, ArrayList<String> to_time, ArrayList<String> date,ArrayList<String> travels, Context context) {
        this.from = from;
        this.to = to;
        this.status = status;
        this.from_time = from_time;
        this.to_time = to_time;
        this.date = date;
        this.travels=travels;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View a = LayoutInflater.from(parent.getContext()).inflate(R.layout.for_my_bookings_list, parent, false);
        MyViewHolder holder = new MyViewHolder(a);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.pickup.setText((CharSequence) from.get(position));
        holder.drop.setText((CharSequence) to.get(position));
        holder.status1.setText((CharSequence) status.get(position));
        holder.pickup_time.setText((CharSequence) from_time.get(position));
        holder.drop_time.setText((CharSequence) to_time.get(position));
        holder.journey_date.setText((CharSequence) date.get(position));
        holder.travels.setText((CharSequence) travels.get(position));



        final String status_type= status.get(position);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Booking_details.class);
                intent.putExtra("status", status_type);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return from.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView pickup,drop,status1,pickup_time,drop_time,journey_date,travels;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pickup=itemView.findViewById(R.id.pickup);
            drop=itemView.findViewById(R.id.drop);
            status1=itemView.findViewById(R.id.status);
            pickup_time=itemView.findViewById(R.id.pickup_time);
            drop_time=itemView.findViewById(R.id.drop_time);
            journey_date=itemView.findViewById(R.id.journey_date);
            cardView=itemView.findViewById(R.id.bookings_card);
            travels=itemView.findViewById(R.id.travels);
        }
    }
}
