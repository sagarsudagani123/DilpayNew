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
import java.util.zip.Inflater;

public class BankAccountsAdapter extends RecyclerView.Adapter<BankAccountsAdapter.MyViewHolder> {
    ArrayList<String> acntNumber;
    ArrayList<String> IFSC;
    Context context;

    public BankAccountsAdapter(ArrayList<String> acntNumber, ArrayList<String> IFSC, Context context) {
        this.acntNumber = acntNumber;
        this.IFSC = IFSC;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.for_bank_accounts, parent, false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return acntNumber.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder{
        TextView acntNumber,IFSC;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            acntNumber=itemView.findViewById(R.id.acntNum);
            IFSC=itemView.findViewById(R.id.ifscNum);
        }
    }
}
