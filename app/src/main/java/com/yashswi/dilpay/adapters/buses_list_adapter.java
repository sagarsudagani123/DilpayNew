package com.yashswi.dilpay.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import com.yashswi.dilpay.bus.Bus_seating;
import com.yashswi.dilpay.R;
import com.yashswi.dilpay.models.Seats_details_model;
import com.yashswi.dilpay.models.available_buses_model;
import java.util.ArrayList;
import java.util.List;

public class buses_list_adapter extends RecyclerView.Adapter<buses_list_adapter.MyViewHolder> {

    ArrayList<available_buses_model> retromodearray_list;
    Context context;
    String sourceName, destinationName, journey_date;
    //   SharedPrefs_model spm;
    List<Seats_details_model> seats_model;

    public buses_list_adapter(String source_id,String destination_id,String journey_date,ArrayList<available_buses_model> retromodearray_list, Context context) {
        this.retromodearray_list = retromodearray_list;
        this.context = context;
        this.journey_date=journey_date;
        this.sourceName=source_id;
        this.destinationName=destination_id;
    }

    @NonNull
    @Override
    public buses_list_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View a = LayoutInflater.from(parent.getContext()).inflate(R.layout.for_buses_list_recycler, parent, false);
        MyViewHolder holder = new MyViewHolder(a);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull buses_list_adapter.MyViewHolder holder, int i) {
        holder.depart.setText(retromodearray_list.get(i).getDepartureTime());
        holder.arrival.setText(retromodearray_list.get(i).getArrivalTime());
        String string = retromodearray_list.get(i).getFares();
        String[] parts = string.split("/");
        double min=Double.parseDouble(parts[0]);
        double max=Double.parseDouble(parts[0]);
        for(int x=0;x<parts.length;x++){
            if(Double.parseDouble(parts[x])<=min){
                min=Double.parseDouble(parts[x]);
            }
            else if(Double.parseDouble(parts[x])>=max){
                max=Double.parseDouble(parts[x]);
            }
        }
        holder.charge.setText(""+min);
        holder.number.setText(retromodearray_list.get(i).getDisplayName());
        holder.seats.setText(retromodearray_list.get(i).getAvailableSeats());
        holder.bus_type.setText(retromodearray_list.get(i).getBusType());
        holder.trip_id.setText(retromodearray_list.get(i).getId());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String trip_id = retromodearray_list.get(i).getId();
                final String provider = retromodearray_list.get(i).getProvider();
                final String operator_name = retromodearray_list.get(i).getTravels();
                final String source_id = retromodearray_list.get(i).getSourceId();
                final String destination_id = retromodearray_list.get(i).getDestinationId();
//                final String date = retromodearray_list.get(i).getJourneydate();
                final String type = retromodearray_list.get(i).getBusType();
                final String arrivalTime=retromodearray_list.get(i).getArrivalTime();
                final String departureTime=retromodearray_list.get(i).getDepartureTime();
                final String duration=retromodearray_list.get(i).getDuration();
                final String travelsName=retromodearray_list.get(i).getTravels();
                final ArrayList<String> bordingPoints=retromodearray_list.get(i).getNameBoard();
                final ArrayList<String> dropingPoints=retromodearray_list.get(i).getNameDrop();


                Intent intent = new Intent(context, Bus_seating.class);
                intent.putExtra("tripID", trip_id);
                intent.putExtra("providercode", provider);
                intent.putExtra("operatorname", operator_name);
                intent.putExtra("sourceid", source_id);
                intent.putExtra("destinationid", destination_id);
                intent.putExtra("destinationName", destinationName);
                intent.putExtra("sourceName", sourceName);
                intent.putExtra("journeydate",journey_date);
                intent.putExtra("type",type);
                intent.putExtra("arrivalTime", arrivalTime);
                intent.putExtra("departureTime", departureTime);
                intent.putExtra("duration",duration);
                intent.putExtra("travelsName",travelsName);


//                intent.putStringArrayListExtra("bordingPoints",bordingPoints);
                intent.putStringArrayListExtra("dropingPints",dropingPoints);
                intent.putStringArrayListExtra("bordingPoints",bordingPoints);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return retromodearray_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView cardView;
        TextView depart, arrival, charge, number, seats, bus_type, trip_id;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            depart = itemView.findViewById(R.id.depart_time);
            arrival = itemView.findViewById(R.id.arrival_time);
            charge = itemView.findViewById(R.id.amount);
            number = itemView.findViewById(R.id.ts);
            seats = itemView.findViewById(R.id.seats);
            bus_type = itemView.findViewById(R.id.bus_type);
            cardView = itemView.findViewById(R.id.card);
            trip_id = itemView.findViewById(R.id.trip_id);

        }
    }
}
