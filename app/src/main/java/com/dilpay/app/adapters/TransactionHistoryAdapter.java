package com.dilpay.app.adapters;

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

import com.dilpay.app.R;


import java.util.ArrayList;

public class TransactionHistoryAdapter extends RecyclerView.Adapter<TransactionHistoryAdapter.MyViewHolder> {
    ArrayList<String> transactionId;
    ArrayList<String> creditAmount;
    ArrayList<String> debitAmount;
    ArrayList<String> dateTime;
    ArrayList<String> message;
    Context context;

    public TransactionHistoryAdapter(ArrayList<String> transactionId, ArrayList<String> creditAmount, ArrayList<String> debitAmount, ArrayList<String> dateTime, ArrayList<String> message, Context context) {
        this.transactionId = transactionId;
        this.creditAmount = creditAmount;
        this.debitAmount = debitAmount;
        this.dateTime = dateTime;
        this.message = message;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View a = LayoutInflater.from(parent.getContext()).inflate(R.layout.for_transactions_list, parent, false);
        MyViewHolder holder = new MyViewHolder(a);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.transactionId.setText(transactionId.get(i));
        holder.creditAmount.setText(creditAmount.get(i));
        holder.debitAmount.setText(debitAmount.get(i));
//        if(Integer.parseInt(debitAmount.get(i))>=0 || Integer.parseInt(debitAmount.get(i))<=0){
//            holder.debitLayout.setVisibility(View.VISIBLE);
//        }
//        if(Integer.parseInt(creditAmount.get(i))>=0 || Integer.parseInt(creditAmount.get(i))<=0){
//            holder.creditLayout.setVisibility(View.VISIBLE);
//        }
        if (!debitAmount.get(i).equalsIgnoreCase("null")) {
            holder.debitLayout.setVisibility(View.VISIBLE);
            holder.creditLayout.setVisibility(View.GONE);
        }
        else if(!creditAmount.get(i).equalsIgnoreCase("null")){
            holder.debitLayout.setVisibility(View.GONE);
            holder.creditLayout.setVisibility(View.VISIBLE);
        }
        holder.dateTime.setText(dateTime.get(i));
        holder.message.setText(message.get(i));
    }

    @Override
    public int getItemCount() {
        return transactionId.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView transactionId, creditAmount, debitAmount, dateTime, message;
        ImageView rupeeDebit,ruperCredit;
        CardView cardView;
        LinearLayout creditLayout,debitLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            transactionId = itemView.findViewById(R.id.trans_id);
            creditAmount = itemView.findViewById(R.id.credit_amount);
            debitAmount = itemView.findViewById(R.id.debit_amount);
            dateTime = itemView.findViewById(R.id.date_time);
            message = itemView.findViewById(R.id.message);
            cardView = itemView.findViewById(R.id.transaction_card);
            rupeeDebit = itemView.findViewById(R.id.rupee_debit);
            ruperCredit=itemView.findViewById(R.id.rupee_credit);
            creditLayout=itemView.findViewById(R.id.creditLayout);
            debitLayout=itemView.findViewById(R.id.debitLayout);
        }
    }
}