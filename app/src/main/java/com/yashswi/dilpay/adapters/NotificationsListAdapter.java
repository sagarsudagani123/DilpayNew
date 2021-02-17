package com.yashswi.dilpay.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.yashswi.dilpay.R;

import java.util.ArrayList;

public class NotificationsListAdapter extends RecyclerView.Adapter<NotificationsListAdapter.MyViewHolder> {
    ArrayList<String> title;
    ArrayList<String> body;
    Context context;

    public NotificationsListAdapter(ArrayList<String> title, ArrayList<String> body, Context context) {
        this.title = title;
        this.body = body;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.for_notifications_recyclerview, parent, false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(title.get(position));
        holder.body.setText(body.get(position));
    }

    @Override
    public int getItemCount()
    {
        return title.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder{
        TextView title,body;
        MaterialCardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            body=itemView.findViewById(R.id.body);
            cardView=itemView.findViewById(R.id.card);
        }
    }
}