package com.yashswi.dilpay.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yashswi.dilpay.Datacard_screen;
import com.yashswi.dilpay.Recharge_history;
import com.yashswi.dilpay.Security.SecurityPin;
import com.yashswi.dilpay.bank.BankAccounts;
import com.yashswi.dilpay.bus.My_bookings;
import com.yashswi.dilpay.gas.Gas_screen;
import com.yashswi.dilpay.postpaid.Postpaid_screen;
import com.yashswi.dilpay.dth.Dth_screen;
import com.yashswi.dilpay.bus.Bus;
import com.yashswi.dilpay.electricity.Electricity_screen;
import com.yashswi.dilpay.mobile.Mobile;
import com.yashswi.dilpay.R;

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
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fromCategory.equalsIgnoreCase("Main")) {
                    if (name_value.equalsIgnoreCase("bus")) {
                        Intent i = new Intent(context, Bus.class);
                        context.startActivity(i);
                    } else if (name_value.equalsIgnoreCase("Mobile")) {
                        Intent i = new Intent(context, SecurityPin.class);
                        i.putExtra("category","Mobile");
                        context.startActivity(i);
                    } else if (name_value.equalsIgnoreCase("dth")) {
                        Intent i = new Intent(context, SecurityPin.class);
                        i.putExtra("category","dth");
                        context.startActivity(i);
                    } else if (name_value.equalsIgnoreCase("datacard")) {
                        Intent i = new Intent(context, SecurityPin.class);
                        i.putExtra("category","datacard");
                        context.startActivity(i);
                    } else if (name_value.equalsIgnoreCase("postpaid")) {
                        Intent i = new Intent(context, SecurityPin.class);
                        i.putExtra("category","postpaid");
                        context.startActivity(i);
                    } else if (name_value.equalsIgnoreCase("electricity")) {
                        Intent i = new Intent(context, SecurityPin.class);
                        i.putExtra("category","electricity");
                        context.startActivity(i);
                    } else if (name_value.equalsIgnoreCase("gas")) {
                        Intent i = new Intent(context, SecurityPin.class);
                        i.putExtra("category","gas");
                        context.startActivity(i);
                    } else if (name_value.equalsIgnoreCase("Money Transfer")) {
                        Intent i = new Intent(context, SecurityPin.class);
                        i.putExtra("category","Money Transfer");
                        i.putExtra("title", "Select Bank");
                        context.startActivity(i);
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
                if (fromCategory.equalsIgnoreCase("Mobile")) {
                    if (name_value.equalsIgnoreCase("Recharge History")) {
                        Intent intent=new Intent(context, Recharge_history.class);
                        intent.putExtra("Service","Mobile");
                        context.startActivity(intent);
                    } else if (name_value.equalsIgnoreCase("No Offers Available")) {
                        Toast.makeText(context, name_value, Toast.LENGTH_SHORT).show();
                    }
                }
                if (fromCategory.equalsIgnoreCase("DTH")) {
                    if (name_value.equalsIgnoreCase("Recharge History")) {
                        Intent intent=new Intent(context, Recharge_history.class);
                        intent.putExtra("Service","DTH");
                        context.startActivity(intent);
                    } else if (name_value.equalsIgnoreCase("No Offers Available")) {
                        Toast.makeText(context, name_value, Toast.LENGTH_SHORT).show();
                    }
                }
                if (fromCategory.equalsIgnoreCase("Datacard")) {
                    if (name_value.equalsIgnoreCase("Recharge History")) {
                        Intent intent=new Intent(context, Recharge_history.class);
                        intent.putExtra("Service","Data Card");
                        context.startActivity(intent);
                    } else if (name_value.equalsIgnoreCase("No Offers Available")) {
                        Toast.makeText(context, name_value, Toast.LENGTH_SHORT).show();
                    }
                }
                 if (fromCategory.equalsIgnoreCase("postpaid")) {
                        if (name_value.equalsIgnoreCase("Recharge History")) {
                            Intent intent=new Intent(context, Recharge_history.class);
                            intent.putExtra("Service","PostPaid");
                            context.startActivity(intent);
                        }else if (name_value.equalsIgnoreCase("No Offers Available")) {
                        Toast.makeText(context, name_value, Toast.LENGTH_SHORT).show();
                    }
                }
                if (fromCategory.equalsIgnoreCase("electricity")) {
                    if (name_value.equalsIgnoreCase("Payments History")) {
                        Intent intent=new Intent(context, Recharge_history.class);
                        intent.putExtra("Service","Electricity");
                        context.startActivity(intent);
                    } else if (name_value.equalsIgnoreCase("No Offers Available")) {
                        Toast.makeText(context, name_value, Toast.LENGTH_SHORT).show();
                    }
                }
                if (fromCategory.equalsIgnoreCase("gas")) {
                    if (name_value.equalsIgnoreCase("My Bookings")) {
                        Intent intent=new Intent(context, Recharge_history.class);
                        intent.putExtra("Service","Gas");
                        context.startActivity(intent);
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
        ImageView image;
        TextView name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.buton_img);
            name = itemView.findViewById(R.id.buton_name);
        }
    }
}
