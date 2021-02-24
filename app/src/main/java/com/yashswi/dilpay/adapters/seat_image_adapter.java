package com.yashswi.dilpay.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yashswi.dilpay.R;
import com.yashswi.dilpay.bus.Bus_seating;
import com.yashswi.dilpay.models.Seats_details_model;

import java.util.ArrayList;
import java.util.List;

public class seat_image_adapter extends RecyclerView.Adapter<seat_image_adapter.MyViewHolder> {
    List<Boolean> isAvailable;
    List<String> seatType;
    List<Boolean> isLadies;
    List<Float> NetFare;
    List<Float> Servicetax;
    List<Float> OperatorServiceCharge;
    List<Boolean> isSelected;
    List<String> seatNumber;
    Context context;

    public seat_image_adapter(Context context, List<Boolean> isAvailable, List<Boolean> isLadies, List<String> seatType, List<Float> NetFare, List<Float> Servicetax, List<Float> OperatorServiceCharge, List<Boolean> isSelected, List<String> seatNumber) {
        this.isAvailable = isAvailable;
        this.seatType = seatType;
        this.isLadies = isLadies;
        this.context = context;
        this.NetFare = NetFare;
        this.Servicetax = Servicetax;
        this.isSelected = isSelected;
        this.seatNumber = seatNumber;
        this.OperatorServiceCharge = OperatorServiceCharge;
    }

    @NonNull
    @Override
    public seat_image_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View a = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_seat, parent, false);
        MyViewHolder holder = new MyViewHolder(a);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull seat_image_adapter.MyViewHolder holder, int position) {
        if (seatType.get(position).equalsIgnoreCase("seater")) {
            if (isLadies.get(position)) {
                if (isAvailable.get(position)) {
                    //available ladies seat
                    holder.image1.setImageResource(R.drawable.seat_sitting_economy_class);
                } else {
                    // booked ladies seat
                    holder.image1.setImageResource(R.drawable.seat_sitting_ladies);
                }
            } else {
                if (isAvailable.get(position)) {
                    //available  seat
                    holder.image1.setImageResource(R.drawable.seat_sitting_economy_class);
                } else {
                    // booked seat
                    holder.image1.setImageResource(R.drawable.seat_sitting_first_class);
                }
            }
        } else if (seatType.get(position).equalsIgnoreCase("horizontalSleeper")) {
            if (isLadies.get(position)) {
                if (isAvailable.get(position)) {
                    //available ladies horizontial sleeper
                    holder.image1.setImageResource(R.drawable.v_emty_sleeper);
                } else {
                    // booked ladies horizontial sleeper
                    holder.image1.setImageResource(R.drawable.v_ladies_sleeper);
                }
            } else {
                if (isAvailable.get(position)) {
                    //available  horizontial sleeper
                    holder.image1.setImageResource(R.drawable.v_emty_sleeper);
                } else {
                    // booked horizontial sleeper
                    holder.image1.setImageResource(R.drawable.v_booked_sleeper);
                }
            }
        } else if (seatType.get(position).equalsIgnoreCase("verticalSleeper")) {
            if (isLadies.get(position)) {
                if (isAvailable.get(position)) {
                    //available ladies vertical sleeper
                    holder.image1.setImageResource(R.drawable.h_emty);
                } else {
                    // booked ladies vertical sleeper
                    holder.image1.setImageResource(R.drawable.h_ladies);
                }
            } else {
                if (isAvailable.get(position)) {
                    //available  vertical sleeper
                    holder.image1.setImageResource(R.drawable.h_emty);
                } else {
                    // booked vertical
                    holder.image1.setImageResource(R.drawable.h_booked);
                }
            }
        } else {
            holder.image1.setImageResource(R.drawable.arrowdown);
        }
        holder.image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = ((Bus_seating) context).numOfSeatsSelected();

                //calculate total amount including service tax etc..
                float amt = NetFare.get(position);

                //check if seat is available
                if (isAvailable.get(position)) {
                    //if selected already delete selected seat
                    if (isSelected.get(position)) {
                        isSelected.set(position, false);
                        ((Bus_seating) context).deleteSeatSelected(count - 1, seatNumber.get(position), amt);
                        if (seatType.get(position).equalsIgnoreCase("seater")) {
                            if (isLadies.get(position)) {
                                holder.image1.setImageResource(R.drawable.seat_sitting_economy_class);
                            } else {
                                holder.image1.setImageResource(R.drawable.seat_sitting_economy_class);
                            }
                        } else if (seatType.get(position).equalsIgnoreCase("verticalSleeper")) {
                            if (isLadies.get(position)) {
                                holder.image1.setImageResource(R.drawable.h_ladies);
                            } else {
                                holder.image1.setImageResource(R.drawable.h_emty);
                            }
                        } else if (seatType.get(position).equalsIgnoreCase("horizontalSleeper")) {
                            if (isLadies.get(position)) {
                                holder.image1.setImageResource(R.drawable.v_ladies_sleeper);
                            } else {
                                holder.image1.setImageResource(R.drawable.v_emty_sleeper);
                            }
                        }
                    }
                    //if not selected add seat
                    else {
                        if (count <= 5 && count >= 0) {
                            isSelected.set(position, true);
                            ((Bus_seating) context).seatSelected(position, seatNumber.get(position), amt, NetFare.get(position), Servicetax.get(position), OperatorServiceCharge.get(position));
                            if (seatType.get(position).equalsIgnoreCase("seater")) {
                                holder.image1.setImageResource(R.drawable.seat_sitting_choose);
                            } else if (seatType.get(position).equalsIgnoreCase("verticalSleeper")) {
                                holder.image1.setImageResource(R.drawable.h_booking);
                            } else if (seatType.get(position).equalsIgnoreCase("horizontalSleeper")) {
                                holder.image1.setImageResource(R.drawable.v_booking_sleeper);
                            }
                        } else {
                            Toast.makeText(context, "Max 6 seats can be selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    //do nothing
                }
            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return isAvailable.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image1, seat_selected;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image1 = itemView.findViewById(R.id.seat_available);
            seat_selected = itemView.findViewById(R.id.seat_select);

        }
    }
}
