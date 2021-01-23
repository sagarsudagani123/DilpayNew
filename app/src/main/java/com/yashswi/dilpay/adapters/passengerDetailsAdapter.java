package com.yashswi.dilpay.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class passengerDetailsAdapter extends RecyclerView.Adapter<passengerDetailsAdapter.MyViewHolder> {
    ArrayList<String> selectedSeats;
    Context context;

    public passengerDetailsAdapter(Context context,ArrayList<String> selectedSeats) {
        this.selectedSeats = selectedSeats;
        this.context = context;
    }

    @NonNull
    @Override
    public passengerDetailsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull passengerDetailsAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView seatNum;
        EditText name,age;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
