package com.yashswi.dilpay.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.yashswi.dilpay.R;
import com.yashswi.dilpay.WithdrawAmount;
import com.yashswi.dilpay.bank.BankAccounts;
import com.yashswi.dilpay.models.bankDetails;
import com.yashswi.dilpay.models.userDetails;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class BankAccountsAdapter extends RecyclerView.Adapter<BankAccountsAdapter.MyViewHolder> {
    ArrayList<bankDetails> bankDetails;
    ArrayList<String> IFSC;
    String title;
    Context context;

    public BankAccountsAdapter(String title, ArrayList<bankDetails> bankDetails, Context context) {
        this.bankDetails = bankDetails;
        this.title = title;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.for_bank_accounts, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.acntNumber.setText(bankDetails.get(position).getAcntNumber());
        holder.IFSC.setText(bankDetails.get(position).getIfscCode());
        if (title.equalsIgnoreCase("Select Bank")) {
            holder.delete.setVisibility(View.GONE);
            holder.edit.setVisibility(View.GONE);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, WithdrawAmount.class);
                    intent.putExtra("beneficiaryID", bankDetails.get(position).getBeneficiaryID());
                    intent.putExtra("emial", bankDetails.get(position).getEmial());
                    intent.putExtra("accountNumber", bankDetails.get(position).getAcntNumber());
                    intent.putExtra("IFSC", bankDetails.get(position).getIfscCode());
                    intent.putExtra("address", bankDetails.get(position).getAddress());
                    context.startActivity(intent);
                }
            });
        }
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject test = new JSONObject();
                try {
                    test.put("beneficiaryID", bankDetails.get(position).getBeneficiaryID());
                    test.put("username", new userDetails(context).getNumber());
                    test.put("Name", new userDetails(context).getName());
                    test.put("emial", bankDetails.get(position).getEmial());
                    test.put("phone", new userDetails(context).getNumber());
                    test.put("accountNumber", bankDetails.get(position).getAcntNumber());
                    test.put("IFSC", bankDetails.get(position).getIfscCode());
                    test.put("address", bankDetails.get(position).getAddress());
                    test.put("Method", "Delete");
                } catch (Exception e) {
                }
                ((BankAccounts) context).deleteAccount(test.toString());
//                deleteAccount(test.toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return bankDetails.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView acntNumber, IFSC;
        ImageView delete;
        AppCompatButton edit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            acntNumber = itemView.findViewById(R.id.acntNum);
            IFSC = itemView.findViewById(R.id.ifscNum);
            delete = itemView.findViewById(R.id.delete);
            edit = itemView.findViewById(R.id.edit);
        }
    }
}
