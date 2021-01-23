package com.yashswi.dilpay.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.yashswi.dilpay.R;
import com.yashswi.dilpay.adapters.passengerDetailsAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class passengerDetails extends AppCompatActivity {
    String boardingPoint,dropingPoint,email,number,amount;
    ArrayList<String> selectedSeats=new ArrayList<>();
    ArrayList<String> passengerNames=new ArrayList<>();
    ArrayList<String> passengerAge=new ArrayList<>();
    ArrayList<String> gender=new ArrayList<>();

    RecyclerView passenger;
    TextView seatNumberOne,seatNumberTwo,seatNumberThree,seatNumberFour,seatNumberFive,seatNumberSix;
    TextInputEditText passNameOne,passNameTwo,passNameThree,passNameFour,passNameFive,passNameSix,
            passAgeOne,passAgeTwo,passAgeThree,passAgeFour,passAgeFive,passAgeSix;
    RadioGroup radioGroupOne,radioGroupTwo,radioGroupThree,radioGroupFour,radioGroupFive,radioGroupSix;
    RadioButton maleOne,femaleOne,maleTwo,femaleTwo,maleThree,femaleThree,maleFour,femaleFour,maleFive,femaleFive,maleSix,femaleSix;
    AppCompatButton next;
    CardView cardOne,cardTwo,cardThree,cardFour,cardFive,cardSix;
    String tripId, providerCode,operator_name,source_id,destination_id,date,type,sourceName,destinationName,arrivalTime,departureTime,duration,travelsName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_details);
//        passenger=findViewById(R.id.passenger);
//        passenger.setHasFixedSize(true);
        cardOne=findViewById(R.id.cardOne);
        cardTwo=findViewById(R.id.cardTwo);
        cardThree=findViewById(R.id.cardThree);
        cardFour=findViewById(R.id.cardFour);
        cardFive=findViewById(R.id.cardFive);
        cardSix=findViewById(R.id.cardSix);
        next=findViewById(R.id.next);

        passNameOne=findViewById(R.id.passNameOne);
        passNameTwo=findViewById(R.id.passNameTwo);
        passNameThree=findViewById(R.id.passNameThree);
        passNameFour=findViewById(R.id.passNameFour);
        passNameFive=findViewById(R.id.passNameFive);
        passNameSix=findViewById(R.id.passNameSix);

        passAgeOne=findViewById(R.id.passAgeOne);
        passAgeTwo=findViewById(R.id.passAgeTwo);
        passAgeThree=findViewById(R.id.passAgeThree);
        passAgeFour=findViewById(R.id.passAgeFour);
        passAgeFive=findViewById(R.id.passAgeFive);
        passAgeSix=findViewById(R.id.passAgeSix);

        radioGroupOne=findViewById(R.id.radioGroupOne);
        radioGroupTwo=findViewById(R.id.radioGroupTwo);
        radioGroupThree=findViewById(R.id.radioGroupThree);
        radioGroupFour=findViewById(R.id.radioGroupFour);
        radioGroupFive=findViewById(R.id.radioGroupFive);
        radioGroupSix=findViewById(R.id.radioGroupSix);

        seatNumberOne=findViewById(R.id.seatNumberOne);
        seatNumberTwo=findViewById(R.id.seatNumberTwo);
        seatNumberThree=findViewById(R.id.seatNumberThree);
        seatNumberFour=findViewById(R.id.seatNumberFour);
        seatNumberFive=findViewById(R.id.seatNumberFive);
        seatNumberSix=findViewById(R.id.seatNumberSix);

        boardingPoint=getIntent().getStringExtra("boardingPoint");
        dropingPoint=getIntent().getStringExtra("dropingPoint");
        email=getIntent().getStringExtra("email");
        number=getIntent().getStringExtra("number");
        amount=getIntent().getStringExtra("amount");
        arrivalTime=getIntent().getStringExtra("arrivalTime");
        departureTime=getIntent().getStringExtra("departureTime");
        duration =getIntent().getStringExtra("duration");
        travelsName=getIntent().getStringExtra("travelsName");

        date= (String) this.getIntent().getSerializableExtra("journeydate");
        type=getIntent().getStringExtra("type");

        sourceName=getIntent().getStringExtra("sourceName");
        destinationName=getIntent().getStringExtra("destinationName");


        selectedSeats=getIntent().getStringArrayListExtra("selectedSeats");
switch (selectedSeats.size()){
    case 1:
    {
        cardOne.setVisibility(View.VISIBLE);
        if(selectedSeats.size()>=1){
            seatNumberOne.setText(selectedSeats.get(0));
        }
        break;
    }
    case 2:
    {
        cardOne.setVisibility(View.VISIBLE);
        cardTwo.setVisibility(View.VISIBLE);
        if(selectedSeats.size()>=2){
                seatNumberOne.setText(selectedSeats.get(0));
                seatNumberTwo.setText(selectedSeats.get(1));
        }

        break;
    }
    case 3:
    {
        cardOne.setVisibility(View.VISIBLE);
        cardTwo.setVisibility(View.VISIBLE);
        cardThree.setVisibility(View.VISIBLE);
        if(selectedSeats.size()>=3) {
            seatNumberOne.setText(selectedSeats.get(0));
            seatNumberTwo.setText(selectedSeats.get(1));
            seatNumberThree.setText(selectedSeats.get(2));
        }
        break;
    }
    case 4:
    {
        cardOne.setVisibility(View.VISIBLE);
        cardTwo.setVisibility(View.VISIBLE);
        cardThree.setVisibility(View.VISIBLE);
        cardFour.setVisibility(View.VISIBLE);
        if(selectedSeats.size()>=4) {
            seatNumberOne.setText(selectedSeats.get(0));
            seatNumberTwo.setText(selectedSeats.get(1));
            seatNumberThree.setText(selectedSeats.get(2));
            seatNumberFour.setText(selectedSeats.get(3));
        }

        break;
    }
    case 5:
    {
        cardOne.setVisibility(View.VISIBLE);
        cardTwo.setVisibility(View.VISIBLE);
        cardThree.setVisibility(View.VISIBLE);
        cardFour.setVisibility(View.VISIBLE);
        cardFive.setVisibility(View.VISIBLE);
        if(selectedSeats.size()>=5) {
            seatNumberOne.setText(selectedSeats.get(0));
            seatNumberTwo.setText(selectedSeats.get(1));
            seatNumberThree.setText(selectedSeats.get(2));
            seatNumberFour.setText(selectedSeats.get(3));
            seatNumberFive.setText(selectedSeats.get(4));
        }

        break;
    }
    case 6:
    {
        cardOne.setVisibility(View.VISIBLE);
        cardTwo.setVisibility(View.VISIBLE);
        cardThree.setVisibility(View.VISIBLE);
        cardFour.setVisibility(View.VISIBLE);
        cardFive.setVisibility(View.VISIBLE);
        cardSix.setVisibility(View.VISIBLE);
        if(selectedSeats.size()>=6) {
            seatNumberOne.setText(selectedSeats.get(0));
            seatNumberTwo.setText(selectedSeats.get(1));
            seatNumberThree.setText(selectedSeats.get(2));
            seatNumberFour.setText(selectedSeats.get(3));
            seatNumberFive.setText(selectedSeats.get(4));
            seatNumberSix.setText(selectedSeats.get(5));
        }

        break;
    }

}
next.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if(selectedSeats.size()>=1){

            if(passNameOne.getText().toString().equalsIgnoreCase("")){
            }else{
                passengerNames.add(passNameOne.getText().toString());
            }
            if(passAgeOne.getText().toString().equalsIgnoreCase("")){
            }else{
                passengerAge.add(passAgeOne.getText().toString());
            }
            int checkGender=radioGroupOne.getCheckedRadioButtonId();
            if(checkGender==-1){
            }else{
                if(checkGender==R.id.maleOne){
                    gender.add("male");
                }else{
                    gender.add("female");
                }
            }
        }
        if(selectedSeats.size()>=2){
            if(passNameTwo.getText().toString().equalsIgnoreCase("")){
            }else{
                passengerNames.add(passNameTwo.getText().toString());
            }
            if(passAgeTwo.getText().toString().equalsIgnoreCase("")){
            }else{
                passengerAge.add(passAgeTwo.getText().toString());
            }

            int checkGender=radioGroupTwo.getCheckedRadioButtonId();
            if(checkGender==-1){
            }else{
                if(checkGender==R.id.maleTwo){
                    gender.add("male");
                }else{
                    gender.add("female");
                }
            }
        }
        if(selectedSeats.size()>=3){
            if(passNameThree.getText().toString().equalsIgnoreCase("")){
            }else{
                passengerNames.add(passNameThree.getText().toString());
            }
            if(passAgeThree.getText().toString().equalsIgnoreCase("")){
            }else{
                passengerAge.add(passAgeThree.getText().toString());
            }

            int checkGender=radioGroupThree.getCheckedRadioButtonId();
            if(checkGender==-1){
            }else{
                if(checkGender==R.id.maleThree){
                    gender.add("male");
                }else{
                    gender.add("female");
                }
            }
        }
        if(selectedSeats.size()>=4){
            if(passNameFour.getText().toString().equalsIgnoreCase("")){
            }else{
                passengerNames.add(passNameFour.getText().toString());
            }
            if(passAgeFour.getText().toString().equalsIgnoreCase("")){
            }else{
                passengerAge.add(passAgeFour.getText().toString());
            }

            int checkGender=radioGroupFour.getCheckedRadioButtonId();
            if(checkGender==-1){
            }else{
                if(checkGender==R.id.maleFour){
                    gender.add("male");
                }else{
                    gender.add("female");
                }
            }
        }
        if(selectedSeats.size()>=5){
            if(passNameFive.getText().toString().equalsIgnoreCase("")){
            }else{
                passengerNames.add(passNameFive.getText().toString());
            }
            if(passAgeFive.getText().toString().equalsIgnoreCase("")){
            }else{
                passengerAge.add(passAgeFive.getText().toString());
            }
            int checkGender=radioGroupFive.getCheckedRadioButtonId();
            if(checkGender==-1){
            }else{
                if(checkGender==R.id.maleFive){
                    gender.add("male");
                }else{
                    gender.add("female");
                }
            }
        }
        if(selectedSeats.size()>=6){
            if(passNameSix.getText().toString().equalsIgnoreCase("")){
            }else{
                passengerNames.add(passNameSix.getText().toString());
            }
            if(passAgeSix.getText().toString().equalsIgnoreCase("")){
            }else{
                passengerAge.add(passAgeSix.getText().toString());
            }
            int checkGender=radioGroupSix.getCheckedRadioButtonId();
            if(checkGender==-1){
            }else{
                if(checkGender==R.id.maleSix){
                    gender.add("male");
                }else{
                    gender.add("female");
                }
            }
        }
        if(passengerNames.size()==selectedSeats.size() && passengerAge.size()==selectedSeats.size() && gender.size()==selectedSeats.size()){
            nextPage();
        }else{
            Toast.makeText(passengerDetails.this,"Please enter all details names size"+passengerNames.size()+"="+passengerAge.size()+"="+gender.size(),Toast.LENGTH_SHORT).show();
            passengerNames.clear();
            passengerAge.clear();
            gender.clear();
        }



    }
});
    }

    private void nextPage() {
        Intent intent=new Intent(passengerDetails.this,busDetailsConfirmation.class);
        intent.putExtra("boardingPoint",boardingPoint);
        intent.putExtra("dropingPoint",dropingPoint);
        intent.putExtra("email",email);
        intent.putExtra("number",number);
        intent.putStringArrayListExtra("selectedSeats",selectedSeats);
        intent.putStringArrayListExtra("passengerNames",passengerNames);
        intent.putStringArrayListExtra("passengerAges",passengerAge);
        intent.putStringArrayListExtra("passengerGenders",gender);
        intent.putExtra("amount",amount);
        intent.putExtra("arrivalTime", arrivalTime);
        intent.putExtra("departureTime", departureTime);
        intent.putExtra("duration",duration);
        intent.putExtra("travelsName",travelsName);
        intent.putExtra("sourceName", sourceName);
        intent.putExtra("destinationName", destinationName);
        intent.putExtra("journeydate",date);
        intent.putExtra("type",type);
        startActivity(intent);
        passengerNames.clear();
        passengerAge.clear();
        gender.clear();
    }
}