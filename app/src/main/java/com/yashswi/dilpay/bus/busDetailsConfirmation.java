package com.yashswi.dilpay.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yashswi.dilpay.R;
import com.yashswi.dilpay.adapters.passDetailsAdapter;

import java.util.ArrayList;

public class busDetailsConfirmation extends AppCompatActivity {
    TextView pickup_location,drop_location,pickup_time,drop_time,duration,journey_date,travels,bus_type,boarding_point,drop_point,base_fare,taxes
            ,name1,gender1,age1,seat_number1,name2,gender2,age2,seat_number2,name3,gender3,age3,seat_number3,name4,gender4,age4,seat_number4,
            name5,gender5,age5,seat_number5,name6,gender6,age6,seat_number6;
    ImageView back;
    AppCompatButton proceed_payment;
    RecyclerView passDetails;

    String boardingPoint,dropingPoint,email,number,amount,date,type,sourceName,destinationName,bordingPoints,dropingPoints,totalAmount,arrivalTime,departureTime,duration1,travelsName;
    ArrayList<String> selectedSeats=new ArrayList<>();
    ArrayList<String> passengerNames=new ArrayList<>();
    ArrayList<String> passengerAges=new ArrayList<>();
    ArrayList<String> passengerGenders=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_details_confirmation);

        boardingPoint=getIntent().getStringExtra("boardingPoint");
        dropingPoint=getIntent().getStringExtra("dropingPoint");
        email=getIntent().getStringExtra("email");
        number=getIntent().getStringExtra("number");
        amount=getIntent().getStringExtra("amount");
        selectedSeats=getIntent().getStringArrayListExtra("selectedSeats");
        passengerNames=getIntent().getStringArrayListExtra("passengerNames");
        passengerAges=getIntent().getStringArrayListExtra("passengerAges");
        passengerGenders=getIntent().getStringArrayListExtra("passengerGenders");
        arrivalTime=getIntent().getStringExtra("arrivalTime");
        departureTime=getIntent().getStringExtra("departureTime");
        duration1 =getIntent().getStringExtra("duration");
        travelsName=getIntent().getStringExtra("travelsName");

        date= (String) this.getIntent().getSerializableExtra("journeydate");
        type=getIntent().getStringExtra("type");

        sourceName=getIntent().getStringExtra("sourceName");
        destinationName=getIntent().getStringExtra("destinationName");

        Log.e("Finaldetails",passengerNames.toString());
        Log.e("Finaldetails",passengerAges.toString());
        Log.e("Finaldetails",passengerGenders.toString());



        back=findViewById(R.id.back);
        pickup_location=findViewById(R.id.pickup);
        drop_location=findViewById(R.id.drop);
        pickup_time=findViewById(R.id.pickup_time);
        drop_time=findViewById(R.id.drop_time);
        duration=findViewById(R.id.duration);
        journey_date=findViewById(R.id.journey_date);
        travels=findViewById(R.id.travels);
        bus_type=findViewById(R.id.bus_type);
        boarding_point=findViewById(R.id.board_point);
        drop_point=findViewById(R.id.drop_point);
        base_fare=findViewById(R.id.amount);
        taxes=findViewById(R.id.amount1);
        proceed_payment=findViewById(R.id.proceed_payment);


        seat_number1=findViewById(R.id.seat_num);
        seat_number2=findViewById(R.id.seat_num1);
        seat_number3=findViewById(R.id.seat_num2);
        seat_number4=findViewById(R.id.seat_num3);
        seat_number5=findViewById(R.id.seat_num4);
        seat_number6=findViewById(R.id.seat_num5);

        pickup_location.setText(sourceName);
        drop_location.setText(destinationName);
        pickup_time.setText(arrivalTime);
        drop_time.setText(departureTime);
        duration.setText(duration1);
        journey_date.setText(date);
        travels.setText(travelsName);
        bus_type.setText(type);
        boarding_point.setText(boardingPoint);
        drop_point.setText(dropingPoint);
        base_fare.setText(amount);

        passDetails=findViewById(R.id.passDetails);
        LinearLayoutManager layoutManager=new LinearLayoutManager(busDetailsConfirmation.this,RecyclerView.VERTICAL, false);
        passDetails.setLayoutManager(layoutManager);
        passDetailsAdapter adapter=new passDetailsAdapter(passengerNames,passengerGenders,passengerAges,selectedSeats,busDetailsConfirmation.this);
        passDetails.setAdapter(adapter);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        proceed_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(busDetailsConfirmation.this,Bus_payment_options.class);
                startActivity(i);
            }
        });


    }
}