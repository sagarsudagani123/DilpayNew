package com.yashswi.dilpay.bus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.NetworkError;
import com.google.android.material.card.MaterialCardView;
import com.yashswi.dilpay.Api_interface.Api_interface;
import com.yashswi.dilpay.R;
import com.yashswi.dilpay.adapters.buses_list_adapter;
import com.yashswi.dilpay.models.available_buses_model;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static retrofit2.converter.scalars.ScalarsConverterFactory.create;


public class Available_buses extends AppCompatActivity {
    RelativeLayout progress;
    RecyclerView rv;
    ArrayList<available_buses_model> BusesListNoFilter = new ArrayList<>();
    ArrayList<available_buses_model> BusesListWithFilter = new ArrayList<>();
    ImageView back;
    TextView from, to, date;
    String source_id, destination_id, journey_date;
    RelativeLayout progress_layout;
    MaterialCardView card;
    TextView t1, depart, arrive, amount, travel, avail_seats, bus_type;
    TextView filter,sort,clearFilter;
    available_buses_model dataNoFilter=null;
    available_buses_model dataWithFilter=null;
    boolean isFiltered=false;
    String filterName="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_buses);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

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
        filter=findViewById(R.id.filter);
        sort=findViewById(R.id.sort);
        clearFilter=findViewById(R.id.clearFilter);

        //GETTING INTENTS DATA
        source_id = this.getIntent().getStringExtra("from");
        destination_id = this.getIntent().getStringExtra("to");
        journey_date = this.getIntent().getStringExtra("date");

        //SETTING DATA TO VIEW'S
        from.setText(source_id);
        to.setText(destination_id);
        date.setText(journey_date);

        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(isFiltered){
//                    sortData(BusesListWithFilter);
//                }
//                else {
//                    sortData(BusesListNoFilter);
//                }

            }
        });
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Available_buses.this,FilterActivity.class);
                intent.putExtra("name",filterName);
                startActivityForResult(intent,101);
            }
        });

        clearFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterName="";
                clearAllFilter(BusesListNoFilter);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getAvailableBusses();
    }

//    private void sortData(ArrayList<available_buses_model> busesList) {
//        Collections.sort(al);
//        buses_list_adapter adapter = new buses_list_adapter(source_id, destination_id,journey_date, busesList, Available_buses.this);
//        rv.setAdapter(adapter);
//        LinearLayoutManager manager = new LinearLayoutManager(Available_buses.this, RecyclerView.VERTICAL, false);
//        rv.setLayoutManager(manager);
//    }

    private void clearAllFilter(ArrayList<available_buses_model> busesListNoFilter) {
        buses_list_adapter adapter = new buses_list_adapter(source_id, destination_id,journey_date, busesListNoFilter, Available_buses.this);
        rv.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(Available_buses.this, RecyclerView.VERTICAL, false);
        rv.setLayoutManager(manager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101){

            if (data != null) {
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    String TravelsName = bundle.getString("TravelsName");
                    String BusType = bundle.getString("BusType");
                    String AmountFrom = bundle.getString("AmountFrom");
                    String AmountTo = bundle.getString("AmountTo");
                    filterName=TravelsName;
                    filterData(TravelsName,BusType,AmountFrom,AmountTo,BusesListNoFilter);
                }
            }
        }
    }

    //FILTERING DATA FOR USER NEED
    private void filterData(String travelsName, String busType, String amountFrom, String amountTo,ArrayList<available_buses_model> busesListNoFilter) {
        BusesListWithFilter.clear();
        isFiltered=true;
        for(int x=0;x<busesListNoFilter.size();x++) {
            Float fromAmt=Float.parseFloat(amountFrom);
            Float toAmt=Float.parseFloat(amountTo);
            String string = busesListNoFilter.get(x).getFares();
            String[] parts = string.split("/");
            double min=Double.parseDouble(parts[0]);
            double max=Double.parseDouble(parts[0]);
            for(int y=0;y<parts.length;y++){
                if(Double.parseDouble(parts[y])<=min){
                    min=Double.parseDouble(parts[y]);
                }
                else if(Double.parseDouble(parts[y])>=max){
                    max=Double.parseDouble(parts[y]);
                }
            }
            if(busesListNoFilter.get(x).getBusType().toUpperCase().contains(busType.toUpperCase()) &&  busesListNoFilter.get(x).getDisplayName().toUpperCase().contains(travelsName.toUpperCase()) && min>=fromAmt && min<=toAmt){
//            if(busesListNoFilter.get(x).getDisplayName().contains(travelsName)){
                dataWithFilter = new available_buses_model();
                dataWithFilter.setDisplayName(busesListNoFilter.get(x).getDisplayName());
                dataWithFilter.setAvailableSeats(busesListNoFilter.get(x).getAvailableSeats());
                dataWithFilter.setArrivalTime(busesListNoFilter.get(x).getArrivalTime());
                dataWithFilter.setIdProofRequired(busesListNoFilter.get(x).getIdProofRequired());//new
                dataWithFilter.setAddressBoard(busesListNoFilter.get(x).getAddressBoard());
                dataWithFilter.setContactNumbersBoard(busesListNoFilter.get(x).getContactNumbersBoard());
                dataWithFilter.setContactPersonsBoard(busesListNoFilter.get(x).getContactPersonsBoard());
                dataWithFilter.setPointIdboard(busesListNoFilter.get(x).getPointIdboard());
                dataWithFilter.setLandmarkBoard(busesListNoFilter.get(x).getLandmarkBoard());
                dataWithFilter.setLocationBoard(busesListNoFilter.get(x).getLocationBoard());
                dataWithFilter.setNameBoard(busesListNoFilter.get(x).getNameBoard());
                dataWithFilter.setTimeBoard(busesListNoFilter.get(x).getTimeBoard());
                dataWithFilter.setBusType(busesListNoFilter.get(x).getBusType());
                dataWithFilter.setCancellationPolicy(busesListNoFilter.get(x).getCancellationPolicy());
                dataWithFilter.setDuration(busesListNoFilter.get(x).getDuration());
                dataWithFilter.setDepartureTime(busesListNoFilter.get(x).getDepartureTime());
                dataWithFilter.setDestinationId(busesListNoFilter.get(x).getDestinationId());
                dataWithFilter.setAddressDrop(busesListNoFilter.get(x).getAddressDrop());
                dataWithFilter.setContactNumbersDrop(busesListNoFilter.get(x).getContactNumbersDrop());
                dataWithFilter.setContactPersonsDrop(busesListNoFilter.get(x).getContactPersonsDrop());
                dataWithFilter.setPointIdDrop(busesListNoFilter.get(x).getPointIdDrop());
                dataWithFilter.setLandmarkDrop(busesListNoFilter.get(x).getLandmarkDrop());
                dataWithFilter.setLocationDrop(busesListNoFilter.get(x).getLocationDrop());
                dataWithFilter.setNameDrop(busesListNoFilter.get(x).getNameDrop());
                dataWithFilter.setTimeDrop(busesListNoFilter.get(x).getTimeDrop());
                //CONVERTING LIST OF AMOUNTS TO SINGLE AMOUNT

                dataWithFilter.setFares(String.valueOf(min));
                dataWithFilter.setOperatorId(busesListNoFilter.get(x).getOperatorId());
                dataWithFilter.setId(busesListNoFilter.get(x).getId());
                dataWithFilter.setProvider(busesListNoFilter.get(x).getProvider());
                dataWithFilter.setPartialCancellationAllowed(busesListNoFilter.get(x).getPartialCancellationAllowed());
                dataWithFilter.setSourceId(busesListNoFilter.get(x).getSourceId());
                dataWithFilter.setConvenienceFee(busesListNoFilter.get(x).getConvenienceFee());
                dataWithFilter.setTravels(busesListNoFilter.get(x).getTravels());
                dataWithFilter.setSourceId(busesListNoFilter.get(x).getSourceId());
                dataWithFilter.setJourneydate(busesListNoFilter.get(x).getJourneydate());
                BusesListWithFilter.add(dataWithFilter);
            }
        }
        buses_list_adapter adapter = new buses_list_adapter(source_id, destination_id,journey_date, BusesListWithFilter, Available_buses.this);
        rv.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(Available_buses.this, RecyclerView.VERTICAL, false);
        rv.setLayoutManager(manager);
    }

    //GETTING AVAILABLE BUSES DATA
    private void getAvailableBusses() {
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
                            dataNoFilter = new available_buses_model();
                            dataNoFilter.setDisplayName(dataObject.getString("DisplayName"));
                            dataNoFilter.setAvailableSeats(dataObject.getString("AvailableSeats"));
                            dataNoFilter.setArrivalTime(dataObject.getString("ArrivalTime"));
                            dataNoFilter.setIdProofRequired(String.valueOf(dataObject.getBoolean("IdProofRequired")));//new
                            JSONArray jsonArray1 = dataObject.getJSONArray("BoardingTimes");
                            for (int  j= 0; j < jsonArray1.length(); j++) {
                                JSONObject dataObject1 = jsonArray1.getJSONObject(j);
                                dataNoFilter.setAddressBoard(dataObject1.getString("Address"));
                                dataNoFilter.setContactNumbersBoard(dataObject1.getString("ContactNumbers"));
                                dataNoFilter.setContactPersonsBoard(dataObject1.getString("ContactPersons"));
                                dataNoFilter.setPointIdboard(dataObject1.getString("PointId"));
                                dataNoFilter.setLandmarkBoard(dataObject1.getString("Landmark"));
                                dataNoFilter.setLocationBoard(dataObject1.getString("Location"));
                                dataNoFilter.setNameBoard(dataObject1.getString("Name"));
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
                                dataNoFilter.setTimeBoard(time);
                            }
                            dataNoFilter.setBusType(dataObject.getString("BusType"));
                            dataNoFilter.setCancellationPolicy(dataObject.getString("CancellationPolicy"));//new
                            dataNoFilter.setDuration(dataObject.getString("Duration"));
                            dataNoFilter.setDepartureTime(dataObject.getString("DepartureTime"));
                            dataNoFilter.setDestinationId(dataObject.getString("DestinationId"));
                            JSONArray jsonArray2 = dataObject.getJSONArray("DroppingTimes");
                            for (int  k= 0; k < jsonArray2.length(); k++) {
                                JSONObject dataObject2 = jsonArray2.getJSONObject(k);
                                dataNoFilter.setAddressDrop(dataObject2.getString("Address"));
                                dataNoFilter.setContactNumbersDrop(dataObject2.getString("ContactNumbers"));
                                dataNoFilter.setContactPersonsDrop(dataObject2.getString("ContactPersons"));
                                dataNoFilter.setPointIdDrop(dataObject2.getString("PointId"));
                                dataNoFilter.setLandmarkDrop(dataObject2.getString("Landmark"));
                                dataNoFilter.setLocationDrop(dataObject2.getString("Location"));
                                dataNoFilter.setNameDrop(dataObject2.getString("Name"));
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
                                dataNoFilter.setTimeDrop(time);
                            }

                            String string = dataObject.getString("Fares");
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
                            dataNoFilter.setFares(String.valueOf(min));
                            dataNoFilter.setOperatorId(dataObject.getString("OperatorId"));
                            dataNoFilter.setId(dataObject.getString("Id"));//trip id
                            dataNoFilter.setProvider(dataObject.getString("Provider"));
                            dataNoFilter.setPartialCancellationAllowed(dataObject.getString("PartialCancellationAllowed"));
                            dataNoFilter.setSourceId("SourceId");
                            dataNoFilter.setConvenienceFee(dataObject.getString("ConvenienceFee"));
                            dataNoFilter.setTravels(dataObject.getString("Travels"));
                            dataNoFilter.setSourceId(dataObject.getString("SourceId"));
                            dataNoFilter.setJourneydate(dataObject.getString("Journeydate"));
                            BusesListNoFilter.add(dataNoFilter);

                        }

                        //SETTING AVAILABLE BUSSES DATA TO RECYCLERVIEW
                        buses_list_adapter adapter = new buses_list_adapter(source_id, destination_id,journey_date, BusesListNoFilter, Available_buses.this);
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
