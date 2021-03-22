package com.dilpay.app.adapters;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.dilpay.app.R;
import com.dilpay.app.bus.Bus;
import com.dilpay.app.bus.My_bookings;

import java.util.ArrayList;

public class Transport_items_adapter extends RecyclerView.Adapter<Transport_items_adapter.MyViewHolder> {
    ArrayList image;
    ArrayList name;
    Context context;
    String fromCategory;

    public Transport_items_adapter(ArrayList image, ArrayList name, Context context, String fromCategory) {
        this.image = image;
        this.name = name;
        this.context = context;
        this.fromCategory = fromCategory;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View a = LayoutInflater.from(parent.getContext()).inflate(R.layout.for_transport_recyclerview, parent, false);
        MyViewHolder holder = new MyViewHolder(a);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(image.get(position)).into(holder.image);
        holder.name.setText((CharSequence) name.get(position));

        final String name_value = (String) name.get(position);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fromCategory.equalsIgnoreCase("Main")) {
                    if (name_value.equalsIgnoreCase("bus")) {
                        Intent i = new Intent(context, Bus.class);
                        context.startActivity(i);
                    } else if (name_value.equalsIgnoreCase("tourism")){
                        Toast.makeText(context, "Available in next update", Toast.LENGTH_SHORT).show();
                    } else if (name_value.equalsIgnoreCase("transport")){
                        Toast.makeText(context, "Available in next update", Toast.LENGTH_SHORT).show();
                    } else if (name_value.equalsIgnoreCase("hire bus")){
                        Toast.makeText(context, "Available in next update", Toast.LENGTH_SHORT).show();
                    }
                }
                if (fromCategory.equalsIgnoreCase("Bus")) {
                    if (name_value.equalsIgnoreCase("My Bookings")) {
                        Intent i = new Intent(context, My_bookings.class);
                        context.startActivity(i);
                    } else if (name_value.equalsIgnoreCase("No Offers Available")) {
                        Toast.makeText(context, name_value, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CircularImageView image;
        TextView name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.circular);
            name = itemView.findViewById(R.id.buton_name);
        }
    }
}
