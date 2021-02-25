package com.yashswi.dilpay.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yashswi.dilpay.R;

import org.json.JSONObject;

import java.util.ArrayList;

public class Booking_details extends AppCompatActivity {
    ImageView back;
    String status;
    TextView pickup_location, drop_location, pickup_time, drop_time, duration1, journey_date, travels,
            bus_type, boarding_point, drop_point, base_fare, taxes, passengerName, passengerAge, passengerGender, seatNumber;
    AppCompatButton proceed_payment;
    RecyclerView passDetails;
    JSONObject bookingDetails = null;
    JSONObject main = null;
    JSONObject user = null;
    Float taxAmount = 0f;
    RelativeLayout progress;
    com.yashswi.dilpay.models.userDetails userDetails;
    String boardingPoint, dropingPoint, name, age, gender, email, number, amount, date, type, sourceName, destinationName, arrivalTime, departureTime, duration, travelsName,
            tripId, providerCode, operator_name, source_id, destination_id, seat_number,
            operatorID, CancellationPolicy, PartialCancellationAllowed, convienceFee, IdproofRequried, boardingPointID, dropingPointID;
    ArrayList<String> selectedSeats = new ArrayList<>();
    ArrayList<String> passengerNames = new ArrayList<>();
    ArrayList<String> passengerAges = new ArrayList<>();
    ArrayList<String> passengerGenders = new ArrayList<>();
    ArrayList<String> amountsList = new ArrayList<>();
    ArrayList<String> serviceTaxList = new ArrayList<>();
    ArrayList<String> serviceChargeList = new ArrayList<>();
    ArrayList<String> titlesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        back = findViewById(R.id.back);
        pickup_location = findViewById(R.id.pickup);
        drop_location = findViewById(R.id.drop);
        pickup_time = findViewById(R.id.pickup_time);
        drop_time = findViewById(R.id.drop_time);
        duration1 = findViewById(R.id.duration);
        journey_date = findViewById(R.id.journey_date);
        travels = findViewById(R.id.travels);
        bus_type = findViewById(R.id.bus_type);
        boarding_point = findViewById(R.id.board_point);
        drop_point = findViewById(R.id.drop_point);
        base_fare = findViewById(R.id.amount);
        taxes = findViewById(R.id.taxes);
        proceed_payment = findViewById(R.id.proceed_payment);
        passDetails = findViewById(R.id.passDetails);
        passengerName = findViewById(R.id.passengerName);
        passengerAge = findViewById(R.id.passengerAge);
        passengerGender = findViewById(R.id.passengerGender);
        seatNumber = findViewById(R.id.seatNumber);
        back = findViewById(R.id.back);

        String tata=getIntent().getStringExtra("JSONDATA");
//        Toast.makeText(Booking_details.this,tata,Toast.LENGTH_SHORT).show();

        sourceName = getIntent().getStringExtra("SourceName");
        destinationName = getIntent().getStringExtra("DestinationName");
        departureTime = getIntent().getStringExtra("DepartureTime");
        arrivalTime = getIntent().getStringExtra("ArrivalTime");
        date = getIntent().getStringExtra("JourneyDate");
        travelsName = getIntent().getStringExtra("DisplayName");
        boardingPoint = getIntent().getStringExtra("BoardingPointDetails");
        dropingPoint = getIntent().getStringExtra("DroppingPointDetails");
        type = getIntent().getStringExtra("BusTypeName");
//        selectedSeats=getIntent().getStringArrayListExtra("selectedSeats");
//        passengerNames=getIntent().getStringArrayListExtra("passengerNames");
//        passengerAges=getIntent().getStringArrayListExtra("passengerAges");
//        passengerGenders=getIntent().getStringArrayListExtra("passengerGenders");

        amount = getIntent().getStringExtra("Fares");
        name = getIntent().getStringExtra("Names");
        age = getIntent().getStringExtra("Ages");
        gender = getIntent().getStringExtra("Genders");
        seat_number = getIntent().getStringExtra("SeatNos");


        pickup_location.setText(sourceName);
        drop_location.setText(destinationName);
        pickup_time.setText(departureTime);
        drop_time.setText(arrivalTime);
        journey_date.setText(date);
        travels.setText(travelsName);
        bus_type.setText(type);
        boarding_point.setText(boardingPoint);
        drop_point.setText(dropingPoint);
        passengerName.setText(name);
        passengerAge.setText(age);
        passengerGender.setText(gender);
        seatNumber.setText(seat_number);
        base_fare.setText(amount);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        status = getIntent().getStringExtra("status");
    }
}