package com.yashswi.dilpay.bus;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import com.yashswi.dilpay.R;
import com.yashswi.dilpay.adapters.items_list_adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Bus extends AppCompatActivity {
    DatePickerDialog.OnDateSetListener setListener;
    MaterialTextView date1;
    boolean dateChecked=false;
    TextView fetch, depart, arrival,error;
    AppCompatButton search;
    ImageView back;
//    TextInputEditText from;
//    TextInputEditText to;
    String source_id, destination_id, journey_date;
    RelativeLayout progress_layout;
    RecyclerView bus_items;
    ArrayList<Integer> itemImg = new ArrayList<>();
    ArrayList<String> itemName = new ArrayList<>();
    MaterialAutoCompleteTextView from,to;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);
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

        //===============

        //================

        String[] names=new String[]{"Hyderabad","Vijayawada","Chennai","Bangalore"};
        ArrayAdapter<String> adapter1=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,names);
        from.setAdapter(adapter1);
        to.setAdapter(adapter1);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                final int month = calendar.get(Calendar.MONTH);
                final int year = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Bus.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String formattedMonth = "" + month;
                        String formattedDayOfMonth = "" + day;
                        String date = day + "-" + month + "-" + year;
                        if(month < 10){

                            formattedMonth = "0" + month;
                        }
                        if(day < 10){

                            formattedDayOfMonth = "0" + day;
                        }
                        date1.setText(formattedDayOfMonth + "-" + formattedMonth + "-" + year);
                        dateChecked=true;
                    }
                }, year, month, day);
                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
                datePickerDialog.show();
            }
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
                    Intent i = new Intent(Bus.this,Available_buses.class);
                    i.putExtra("from",source_id);
                    i.putExtra("to",destination_id);
                    i.putExtra("date",journey_date);
                    startActivity(i);
                }
            }
        });
        itemImg.add(R.drawable.bus);
        itemImg.add(R.drawable.mobile1);
        itemImg.add(R.drawable.dth1);
        itemImg.add(R.drawable.datacard1);

        itemName.add("My Bookings");//
        itemName.add("Upcoming Trips");
        itemName.add("cancelled Tickets");
        itemName.add("Offers");

        items_list_adapter adapter = new items_list_adapter(itemImg, itemName, this);
        bus_items.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(this,3);
        bus_items.setLayoutManager(manager);
    }
}

