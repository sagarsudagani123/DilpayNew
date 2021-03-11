package com.yashswi.dilpay.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.yashswi.dilpay.R;

import java.util.ArrayList;

public class RechargeHistoryAdapter extends RecyclerView.Adapter<RechargeHistoryAdapter.MyViewHolder> {
    ArrayList<String> transactionId;
    ArrayList<String> amount;
    ArrayList<String> mobileNumber;
    ArrayList<String> service;
    ArrayList<String> dateTime;
    ArrayList<String> orderId;
    ArrayList<String> message;
    Context context;

    public RechargeHistoryAdapter(ArrayList<String> transactionId, ArrayList<String> amount, ArrayList<String> mobileNumber, ArrayList<String> service, ArrayList<String> dateTime, ArrayList<String> orderId, ArrayList<String> message, Context context) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.mobileNumber = mobileNumber;
        this.service = service;
        this.dateTime = dateTime;
        this.orderId = orderId;
        this.message = message;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View a = LayoutInflater.from(parent.getContext()).inflate(R.layout.for_recharge_transactions_list, parent, false);
        MyViewHolder holder = new MyViewHolder(a);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.transactionId.setText(transactionId.get(i));
        holder.amount.setText(amount.get(i));
        holder.mobileNumber.setText(mobileNumber.get(i));
        holder.service.setText(service.get(i));
        holder.dateTime.setText(dateTime.get(i));
        holder.orderId.setText(orderId.get(i));
        holder.message.setText(message.get(i));
    }

    @Override
    public int getItemCount() {
        return transactionId.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView transactionId, amount, dateTime, message,mobileNumber,service,orderId;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            transactionId = itemView.findViewById(R.id.trans_id);
            amount = itemView.findViewById(R.id.amount);
            dateTime = itemView.findViewById(R.id.date_time);
            message = itemView.findViewById(R.id.status);
            mobileNumber = itemView.findViewById(R.id.mobile_number);
            service = itemView.findViewById(R.id.service);
            orderId = itemView.findViewById(R.id.orderId);

        }
    }
}