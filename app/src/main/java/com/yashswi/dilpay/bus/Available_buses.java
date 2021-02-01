package com.yashswi.dilpay.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.google.android.material.card.MaterialCardView;
import com.yashswi.dilpay.Api_interface.Api_interface;
import com.yashswi.dilpay.R;
import com.yashswi.dilpay.adapters.buses_list_adapter;
import com.yashswi.dilpay.models.available_buses_model;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static retrofit2.converter.scalars.ScalarsConverterFactory.create;


public class Available_buses extends AppCompatActivity {
    RelativeLayout progress;
    RecyclerView rv;
    ArrayList<available_buses_model> modalBusesList = new ArrayList<>();
    ImageView back;
    TextView from, to, date;
    String source_id, destination_id, journey_date;
    RelativeLayout progress_layout;
    MaterialCardView card;
    TextView t1, depart, arrive, amount, travel, avail_seats, bus_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_buses);

        //FINDING VIEWS
        progress = findViewById(R.id.progress);
        progress.setVisibility(View.VISIBLE);
        rv = findViewById(R.id.buses_list_recyclerview);
        rv.setHasFixedSize(true);
        t1 = findViewById(R.id.t1);
        card = findViewById(R.id.card);
        depart = findViewById(R.id.depart_time);
        arrive = findViewById(R.id.arrival_time);
        amount = findViewById(R.id.amount);
        travel = findViewById(R.id.ts);
        avail_seats = findViewById(R.id.seats);
        bus_type = findViewById(R.id.bus_type);
        back = findViewById(R.id.back);
        from = findViewById(R.id.from);
        to = findViewById(R.id.to);
        date = findViewById(R.id.date1);
        progress_layout = findViewById(R.id.progress_layout);

        //GETTING INTENTS DATA
        source_id = this.getIntent().getStringExtra("from");
        destination_id = this.getIntent().getStringExtra("to");
        journey_date = this.getIntent().getStringExtra("date");

        //SETTING DATA TO VIEW'S
        from.setText(source_id);
        to.setText(destination_id);
        date.setText(journey_date);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        jsonParse();
    }

    //GETTING AVAILABLE BUSES DATA
    private void jsonParse() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);
        Call<String> call = api.available_buses(source_id, destination_id, journey_date);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body()==null){
                    Toast.makeText(Available_buses.this,"No Data Available! Try again", Toast.LENGTH_SHORT).show();
                }else {
                    progress.setVisibility(View.GONE);
                    try {
                        JSONObject obj = new JSONObject(response.body());
                        //GETTING DATA FROM API
                        JSONArray jsonArray = obj.getJSONArray("AvailableTrips");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject dataObject = jsonArray.getJSONObject(i);
                            available_buses_model model = new available_buses_model();
                            model.setDisplayName(dataObject.getString("DisplayName"));
                            model.setAvailableSeats(dataObject.getString("AvailableSeats"));
                            model.setArrivalTime(dataObject.getString("ArrivalTime"));
                            model.setIdProofRequired(String.valueOf(dataObject.getBoolean("IdProofRequired")));//new
                            JSONArray jsonArray1 = dataObject.getJSONArray("BoardingTimes");
                            for (int  j= 0; j < jsonArray1.length(); j++) {
                                JSONObject dataObject1 = jsonArray1.getJSONObject(j);
                                model.setAddressBoard(dataObject1.getString("Address"));
                                model.setContactNumbersBoard(dataObject1.getString("ContactNumbers"));
                                model.setContactPersonsBoard(dataObject1.getString("ContactPersons"));
                                model.setPointIdboard(dataObject1.getString("PointId"));
                                model.setLandmarkBoard(dataObject1.getString("Landmark"));
                                model.setLocationBoard(dataObject1.getString("Location"));
                                model.setNameBoard(dataObject1.getString("Name"));
                                //CONVERTING TIME TIME TO 12HRS FORMAT
                                int hours=Integer.parseInt(dataObject1.getString("Time"))/60;
                                int min=Integer.parseInt(dataObject1.getString("Time"))%60;
                                String time;
                                if(hours>=24){
                                    hours=hours%24;
                                }
                                if(hours>=12 && hours <24){
                                    hours=hours%12;
                                    if(hours==0){
                                        time=12+":"+min+" PM";
                                    }
                                    else{
                                        time=hours%12+":"+min+" PM";
                                    }
                                }
                                else{
                                    if(hours==0){
                                        time=12+":"+min+" AM";
                                    }else{
                                        time=hours+"::"+min+" AM";
                                    }
                                }
                                model.setTimeBoard(time);
                            }
                            model.setBusType(dataObject.getString("BusType"));
                            model.setCancellationPolicy(dataObject.getString("CancellationPolicy"));//new
                            model.setDuration(dataObject.getString("Duration"));
                            model.setDepartureTime(dataObject.getString("DepartureTime"));
                            model.setDestinationId(dataObject.getString("DestinationId"));
                            JSONArray jsonArray2 = dataObject.getJSONArray("DroppingTimes");
                            for (int  k= 0; k < jsonArray2.length(); k++) {
                                JSONObject dataObject2 = jsonArray2.getJSONObject(k);
                                model.setAddressDrop(dataObject2.getString("Address"));
                                model.setContactNumbersDrop(dataObject2.getString("ContactNumbers"));
                                model.setContactPersonsDrop(dataObject2.getString("ContactPersons"));
                                model.setPointIdDrop(dataObject2.getString("PointId"));
                                model.setLandmarkDrop(dataObject2.getString("Landmark"));
                                model.setLocationDrop(dataObject2.getString("Location"));
                                model.setNameDrop(dataObject2.getString("Name"));
                                //CONVERTING TIME TIME TO 12HRS FORMAT
                                int hours=Integer.parseInt(dataObject2.getString("Time"))/60;
                                int min=Integer.parseInt(dataObject2.getString("Time"))%60;
                                String time;
                                if(hours>=24){
                                    hours=hours%24;
                                }
                                if(hours>=12 && hours <24){
                                    hours=hours%12;
                                    if(hours==0){
                                        time=12+":"+min+" PM";
                                    }
                                    else{
                                        time=hours%12+":"+min+" PM";
                                    }
                                }
                                else{
                                    if(hours==0){
                                        time=12+":"+min+" AM";
                                    }else{
                                        time=hours+":"+min+" AM";
                                    }
                                }
                                model.setTimeDrop(time);
                            }
                            model.setFares(dataObject.getString("Fares"));
                            model.setOperatorId(dataObject.getString("OperatorId"));
                            model.setId(dataObject.getString("Id"));//trip id
                            model.setProvider(dataObject.getString("Provider"));
                            model.setPartialCancellationAllowed(dataObject.getString("PartialCancellationAllowed"));
                            model.setSourceId("SourceId");
                            model.setConvenienceFee(dataObject.getString("ConvenienceFee"));
                            model.setTravels(dataObject.getString("Travels"));
                            model.setSourceId(dataObject.getString("SourceId"));
                            model.setJourneydate(dataObject.getString("Journeydate"));
                            modalBusesList.add(model);

                        }

                        //SETTING AVAILABLE BUSSES DATA TO RECYCLERVIEW
                        buses_list_adapter adapter = new buses_list_adapter(source_id, destination_id,journey_date, modalBusesList, Available_buses.this);
                        rv.setAdapter(adapter);
                        LinearLayoutManager manager = new LinearLayoutManager(Available_buses.this, RecyclerView.VERTICAL, false);
                        rv.setLayoutManager(manager);
                    }catch (Exception e){
                        Toast.makeText(Available_buses.this,"No Data Available", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progress.setVisibility(View.GONE);
                String message="";
                if(t instanceof NetworkError)
                {
                    message = "Cannot connect to Internet...Please check your connection!";
                }
                else{
                    message = "Something went wrong! Please try again after some time!!"+t.toString();
                }
                Toast.makeText(Available_buses.this, message, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
