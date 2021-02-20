package com.yashswi.dilpay.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yashswi.dilpay.R;

import java.util.ArrayList;

public class passDetailsAdapter extends RecyclerView.Adapter<passDetailsAdapter.MyViewHolder> {

    ArrayList<String> passengerNames;
    ArrayList<String> passengerGenders;
    ArrayList<String> passengerAges;
    ArrayList<String> selectedSeats;
    Context context;

    public passDetailsAdapter(ArrayList<String> passengerNames, ArrayList<String> passengerGenders, ArrayList<String> passengerAges, ArrayList<String> selectedSeats, Context context) {
        this.passengerNames = passengerNames;
        this.passengerGenders = passengerGenders;
        this.passengerAges = passengerAges;
        this.selectedSeats = selectedSeats;
        this.context = context;
    }

    @NonNull
    @Override
    public passDetailsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View a = LayoutInflater.from(parent.getContext()).inflate(R.layout.for_passenger_details, parent, false);
        MyViewHolder holder = new MyViewHolder(a);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull passDetailsAdapter.MyViewHolder holder, int position) {
        holder.name.setText(passengerNames.get(position));
        holder.gender.setText(passengerGenders.get(position));
        holder.age.setText(passengerAges.get(position));
        holder.seatNum.setText(selectedSeats.get(position));
    }

    @Override
    public int getItemCount() {
        return selectedSeats.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, age, gender, seatNum;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            gender = itemView.findViewById(R.id.gender);
            seatNum = itemView.findViewById(R.id.seat_num);
            age = itemView.findViewById(R.id.age);
        }
    }
}
