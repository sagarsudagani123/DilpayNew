package com.dilpay.app.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dilpay.app.Datacard_screen;
import com.dilpay.app.Recharge_history;
import com.dilpay.app.bus.My_bookings;
import com.dilpay.app.gas.Gas_screen;
import com.dilpay.app.postpaid.Postpaid_screen;
import com.dilpay.app.dth.Dth_screen;
import com.dilpay.app.electricity.Electricity_screen;
import com.dilpay.app.mobile.Mobile;
import com.dilpay.app.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class items_list_adapter extends RecyclerView.Adapter<items_list_adapter.MyViewHolder> {
    ArrayList image;
    ArrayList name;
    Context context;
    String fromCategory;

    public items_list_adapter(ArrayList image, ArrayList name, String fromCategory, Context context) {
        this.image = image;
        this.name = name;
        this.context = context;
        this.fromCategory = fromCategory;
    }

    @NonNull
    @Override
    public items_list_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View a = LayoutInflater.from(parent.getContext()).inflate(R.layout.for_categories_recyclerview, parent, false);
        MyViewHolder holder = new MyViewHolder(a);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull items_list_adapter.MyViewHolder holder, int position) {
        Glide.with(context).load(image.get(position)).into(holder.image);
        holder.name.setText((CharSequence) name.get(position));

        final String name_value = (String) name.get(position);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fromCategory.equalsIgnoreCase("Main")) {
                    if (name_value.equalsIgnoreCase("Mobile")) {
                        Intent i = new Intent(context, Mobile.class);
                        context.startActivity(i);
                    } else if (name_value.equalsIgnoreCase("dth")) {
                        Intent i = new Intent(context, Dth_screen.class);
                        context.startActivity(i);
                    } else if (name_value.equalsIgnoreCase("datacard")) {
                        Intent i = new Intent(context, Datacard_screen.class);
                        context.startActivity(i);
                    } else if (name_value.equalsIgnoreCase("postpaid")) {
                        Intent i = new Intent(context, Postpaid_screen.class);
                        context.startActivity(i);
                    } else if (name_value.equalsIgnoreCase("electricity")) {
                        Intent i = new Intent(context, Electricity_screen.class);
                        context.startActivity(i);
                    } else if (name_value.equalsIgnoreCase("gas")) {
                        Intent i = new Intent(context, Gas_screen.class);
                        context.startActivity(i);
                    } else if (name_value.equalsIgnoreCase("Money Transfer")) {
//                        Intent i = new Intent(context, SecurityPin.class);
//                        i.putExtra("category","Money Transfer");
//                        i.putExtra("title", "Select Bank");
//                        context.startActivity(i);
                        Toast.makeText(context, "Available in next update.", Toast.LENGTH_SHORT).show();
                    }
                }
                if (fromCategory.equalsIgnoreCase("Bus")) {
                    if (name_value.equalsIgnoreCase("My Bookings")) {
                        Intent i = new Intent(context, My_bookings.class);
                        context.startActivity(i);
                    } else if (("No Offers Available").contains(name_value)) {
                        Toast.makeText(context, "No Offers Available", Toast.LENGTH_SHORT).show();
                    }
                }
                if (fromCategory.equalsIgnoreCase("Mobile")) {
                    if (name_value.equalsIgnoreCase("Recharge History")) {
                        Intent intent=new Intent(context, Recharge_history.class);
                        intent.putExtra("Service","Mobile");
                        context.startActivity(intent);
                    } else if (("No Offers Available").contains(name_value)) {
                        Toast.makeText(context, "No Offers Available", Toast.LENGTH_SHORT).show();
                    }
                }
                if (fromCategory.equalsIgnoreCase("DTH")) {
                    if (name_value.equalsIgnoreCase("Recharge History")) {
                        Intent intent=new Intent(context, Recharge_history.class);
                        intent.putExtra("Service","DTH");
                        context.startActivity(intent);
                    } else if (("No Offers Available").contains(name_value)) {
                        Toast.makeText(context, "No Offers Available", Toast.LENGTH_SHORT).show();
                    }
                }
                if (fromCategory.equalsIgnoreCase("Datacard")) {
                    if (name_value.equalsIgnoreCase("Recharge History")) {
                        Intent intent=new Intent(context, Recharge_history.class);
                        intent.putExtra("Service","Data Card");
                        context.startActivity(intent);
                    } else if (("No Offers Available").contains(name_value)) {
                        Toast.makeText(context, "No Offers Available", Toast.LENGTH_SHORT).show();
                    }
                }
                 if (fromCategory.equalsIgnoreCase("postpaid")) {
                        if (name_value.equalsIgnoreCase("Recharge History")) {
                            Intent intent=new Intent(context, Recharge_history.class);
                            intent.putExtra("Service","PostPaid");
                            context.startActivity(intent);
                        }else if (("No Offers Available").contains(name_value)) {
                        Toast.makeText(context, "No Offers Available", Toast.LENGTH_SHORT).show();
                    }
                }
                if (fromCategory.equalsIgnoreCase("electricity")) {
                    if (name_value.equalsIgnoreCase("Payments History")) {
                        Intent intent=new Intent(context, Recharge_history.class);
                        intent.putExtra("Service","Electricity");
                        context.startActivity(intent);
                    } else if (("No Offers Available").contains(name_value)) {
                        Toast.makeText(context, "No Offers Available", Toast.LENGTH_SHORT).show();
                    }
                }
                if (fromCategory.equalsIgnoreCase("gas")) {
                    if (name_value.equalsIgnoreCase("My Bookings")) {
                        Intent intent=new Intent(context, Recharge_history.class);
                        intent.putExtra("Service","Gas");
                        context.startActivity(intent);
                    } else if (("No Offers Available").contains(name_value)) {
                        Toast.makeText(context, "No Offers Available", Toast.LENGTH_SHORT).show();
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
        ImageView image;
        MaterialTextView name;
        RelativeLayout item;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.buton_img);
            name = itemView.findViewById(R.id.buton_name);
            item=itemView.findViewById(R.id.item);
        }
    }
}
