package com.yashswi.dilpay.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class buses_list_adapter extends RecyclerView.Adapter<buses_list_adapter.MyViewHolder> {

    ArrayList<available_buses_model> retroModelArray_list;
    Context context;
    String sourceName, destinationName, journey_date;
    //   SharedPrefs_model spm;
    List<Seats_details_model> seats_model;

    public buses_list_adapter(String source_id,String destination_id,String journey_date,ArrayList<available_buses_model> retroModelArray_list, Context context) {
        this.retroModelArray_list = retroModelArray_list;
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
        holder.depart.setText(retroModelArray_list.get(i).getDepartureTime());
        holder.arrival.setText(retroModelArray_list.get(i).getArrivalTime());
        String string = retroModelArray_list.get(i).getFares();
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
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        holder.charge.setText(""+decimalFormat.format(min));
        holder.number.setText(retroModelArray_list.get(i).getDisplayName());
        holder.seats.setText(retroModelArray_list.get(i).getAvailableSeats());
        holder.bus_type.setText(retroModelArray_list.get(i).getBusType());
        holder.trip_id.setText(retroModelArray_list.get(i).getId());
        holder.duration.setText(retroModelArray_list.get(i).getDuration());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String trip_id = retroModelArray_list.get(i).getId();
                final String provider = retroModelArray_list.get(i).getProvider();
                final String operator_name = retroModelArray_list.get(i).getTravels();
                final String source_id = retroModelArray_list.get(i).getSourceId();
                final String destination_id = retroModelArray_list.get(i).getDestinationId();
//                final String date = retromodearray_list.get(i).getJourneydate();
                final String type = retroModelArray_list.get(i).getBusType();
                final String arrivalTime= retroModelArray_list.get(i).getArrivalTime();
                final String departureTime= retroModelArray_list.get(i).getDepartureTime();
                final String duration= retroModelArray_list.get(i).getDuration();
                final String travelsName= retroModelArray_list.get(i).getTravels();
                final ArrayList<String> bordingPoints= retroModelArray_list.get(i).getNameBoard();
                final ArrayList<String> dropingPoints= retroModelArray_list.get(i).getNameDrop();
                 ArrayList<String> bordingID= retroModelArray_list.get(i).getPointIdboard();
                 ArrayList<String> dropingID= retroModelArray_list.get(i).getPointIdDrop();
                String operatorID=retroModelArray_list.get(i).getOperatorId();//operator code
                String CancellationPolicy=retroModelArray_list.get(i).getCancellationPolicy();
                String PartialCancellationAllowed=retroModelArray_list.get(i).getPartialCancellationAllowed();
                String IdproofRequried=retroModelArray_list.get(i).getIdProofRequired();
                String convienceFee = retroModelArray_list.get(i).getConvenienceFee();
                Log.e("convianceFee",bordingID+"   "+dropingID);

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

                //NEW ITEMS ADDED
                intent.putExtra("operatorID",operatorID);//new
                intent.putExtra("CancellationPolicy",CancellationPolicy);
                intent.putExtra("PartialCancellationAllowed",PartialCancellationAllowed);
                intent.putExtra("IdproofRequried",IdproofRequried);
                intent.putExtra("convienceFee",convienceFee);



                intent.putStringArrayListExtra("dropingPints",dropingPoints);
                intent.putStringArrayListExtra("bordingPoints",bordingPoints);
                intent.putStringArrayListExtra("dropingID",dropingID);
                intent.putStringArrayListExtra("bordingID",bordingID);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return retroModelArray_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView cardView;
        TextView depart, arrival, charge, number, seats, bus_type, trip_id,duration;

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
            duration=itemView.findViewById(R.id.duration);

        }
    }
}
