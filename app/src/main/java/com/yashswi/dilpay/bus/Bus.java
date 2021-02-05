package com.yashswi.dilpay.bus;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import com.yashswi.dilpay.Api_interface.Api_interface;
import com.yashswi.dilpay.R;
import com.yashswi.dilpay.adapters.items_list_adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static retrofit2.converter.scalars.ScalarsConverterFactory.create;

public class Bus extends AppCompatActivity {
    MaterialTextView date1;
    boolean dateChecked=false;
    TextView fetch, depart, arrival,error;
    AppCompatButton search;
    ImageView back;
    String source_id, destination_id, journey_date;
    RelativeLayout progress_layout;
    RecyclerView bus_items;
    ArrayList<Integer> itemImg = new ArrayList<>();
    ArrayList<String> itemName = new ArrayList<>();
    MaterialAutoCompleteTextView from,to;
//    String fromCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //GETTING INTENT FROM CATEGORY
//        fromCategory=getIntent().getStringExtra("FromCategory");

        //FINDING VIEWS
        date1 = findViewById(R.id.date1);
        search = findViewById(R.id.search);
        back = findViewById(R.id.back);
        fetch = findViewById(R.id.t1);
        from = findViewById(R.id.e_from);
        depart = findViewById(R.id.depart_time);
        arrival = findViewById(R.id.arrival_time);
        error=findViewById(R.id.error);
        to = findViewById(R.id.e_to);
        progress_layout = findViewById(R.id.progress_layout);
        bus_items=findViewById(R.id.bus_items);


        progress_layout.setVisibility(View.VISIBLE);

        //SETTING SOURCE AND DESTINATIONS LIST TO SPINNERS
//        String[] names=new String[]{"","Vijayawada","",""};
//        ArrayList<String> names=new ArrayList<>();
//        names.add("Hyderabad");
//        names.add("Vijayawada");
//        names.add("Chennai");
//        names.add("Bangalore");


        ArrayAdapter<String> adapter1=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,getPlaceNames());
        from.setAdapter(adapter1);
        to.setAdapter(adapter1);

        back.setOnClickListener(v -> finish());

        //DATE PICKER
        date1.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            final int day = calendar.get(Calendar.DAY_OF_MONTH);
            final int month = calendar.get(Calendar.MONTH);
            final int year = calendar.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(Bus.this, (view, year1, month1, day1) -> {
                month1 = month1 + 1;
                String formattedMonth = "" + month1;
                String formattedDayOfMonth = "" + day1;
                String date = day1 + "-" + month1 + "-" + year1;
                if(month1 < 10){
                    formattedMonth = "0" + month1;
                }
                if(day1 < 10){

                    formattedDayOfMonth = "0" + day1;
                }
                date1.setText(formattedDayOfMonth + "-" + formattedMonth + "-" + year1);
                dateChecked=true;
            }, year, month, day);
            datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
            datePickerDialog.show();
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                source_id = from.getText().toString();
                destination_id = to.getText().toString();
                journey_date=date1.getText().toString();
                if(source_id.equals("") ||source_id==null || destination_id.equals("") ||destination_id==null || !dateChecked){
                    error.setText("Please fill in all details");
                }else{
                    Intent intent = new Intent(Bus.this,Available_buses.class);
                    intent.putExtra("from",source_id);
                    intent.putExtra("to",destination_id);
                    intent.putExtra("date",journey_date);
                    startActivity(intent);
                }
            }
        });

        // BUS RELATED MENU IN BUS ACTIVITY
        itemImg.add(R.drawable.bus);
//        itemImg.add(R.drawable.mobile1);
//        itemImg.add(R.drawable.dth1);
        itemImg.add(R.drawable.datacard1);
        itemName.add("My Bookings");//
//        itemName.add("Upcoming Trips");
//        itemName.add("cancelled Tickets");
        itemName.add("Offers");

        items_list_adapter adapter = new items_list_adapter(itemImg, itemName,"Bus", this);
        bus_items.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(this,3);
        bus_items.setLayoutManager(manager);
    }

    public ArrayList<String> getPlaceNames(){
        ArrayList<String> names=new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);
        Call<String> call=api.getPlaces();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body()!=null){
                    Log.e("check",response.body());
                    try {
                        JSONArray data=new JSONArray(response.body());
                        for(int i=0;i<data.length();i++){
                            names.add(data.get(i).toString());
                        }
//                        Toast.makeText(Bus.this,names.toString(),Toast.LENGTH_SHORT).show();
                        progress_layout.setVisibility(View.GONE);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(Bus.this,"Failed to connect! Try again",Toast.LENGTH_SHORT).show();
            }
        });

        return names;
    }
}

