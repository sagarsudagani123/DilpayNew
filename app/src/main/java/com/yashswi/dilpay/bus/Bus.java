package com.yashswi.dilpay.bus;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import com.yashswi.dilpay.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class Bus extends AppCompatActivity {
    DatePickerDialog.OnDateSetListener setListener;
    MaterialTextView date1;
    boolean dateChecked=false;
    TextView fetch, depart, arrival,error;
    AppCompatButton search;
    ImageView back;
    TextInputEditText from;
    TextInputEditText to;
    String source_id, destination_id, journey_date;
    RelativeLayout progress_layout;
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
                        String date = day + "/" + month + "/" + year;
                        date1.setText(date);
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
    }
}

