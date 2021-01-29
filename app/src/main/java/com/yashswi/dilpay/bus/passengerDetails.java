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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
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
    ArrayList<String> amountsList=new ArrayList<>();
    ArrayList<String> serviceTaxList =new ArrayList<>();
    ArrayList<String> serviceChargeList=new ArrayList<>();
    ArrayList<String> titlesList=new ArrayList<>();

    ImageView back;
    RecyclerView passenger;
    TextView seatNumberOne,seatNumberTwo,seatNumberThree,seatNumberFour,seatNumberFive,seatNumberSix;
    TextInputEditText passNameOne,passNameTwo,passNameThree,passNameFour,passNameFive,passNameSix,
            passAgeOne,passAgeTwo,passAgeThree,passAgeFour,passAgeFive,passAgeSix;
    RadioGroup radioGroupOne,radioGroupTwo,radioGroupThree,radioGroupFour,radioGroupFive,radioGroupSix;
    RadioButton maleOne,femaleOne,maleTwo,femaleTwo,maleThree,femaleThree,maleFour,femaleFour,maleFive,femaleFive,maleSix,femaleSix;
    AppCompatButton next;
    AutoCompleteTextView titleSpinOne,titleSpinTwo,titleSpinThree,titleSpinFour,titleSpinFive,titleSpinSix;
    CardView cardOne,cardTwo,cardThree,cardFour,cardFive,cardSix;
    String tripId, providerCode,operator_name,source_id,destination_id,date,type,sourceName,destinationName,arrivalTime,departureTime,duration,travelsName,
            operatorID, CancellationPolicy, PartialCancellationAllowed, convienceFee, IdproofRequried,boardingPointID,dropingPointID;
    ArrayList<String> titles=new ArrayList<>();
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
        back=findViewById(R.id.back);

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

        titleSpinOne=findViewById(R.id.title_spin1);
        titleSpinTwo=findViewById(R.id.title_spin2);
        titleSpinThree=findViewById(R.id.title_spin3);
        titleSpinFour=findViewById(R.id.title_spin4);
        titleSpinFive=findViewById(R.id.title_spin5);
        titleSpinSix=findViewById(R.id.title_spin6);

        tripId = (String) this.getIntent().getSerializableExtra("tripID");
        providerCode = (String) this.getIntent().getSerializableExtra("providercode");
        operator_name= (String) this.getIntent().getSerializableExtra("operatorname");
        boardingPoint=getIntent().getStringExtra("boardingPoint");
        dropingPoint=getIntent().getStringExtra("dropingPoint");
        boardingPointID=getIntent().getStringExtra("boardingPointID");
        dropingPointID=getIntent().getStringExtra("dropingPointID");
        email=getIntent().getStringExtra("email");
        number=getIntent().getStringExtra("number");
        amount=getIntent().getStringExtra("amount");//amount with out taxes
        arrivalTime=getIntent().getStringExtra("arrivalTime");
        departureTime=getIntent().getStringExtra("departureTime");
        duration =getIntent().getStringExtra("duration");
        travelsName=getIntent().getStringExtra("travelsName");
        date= (String) this.getIntent().getSerializableExtra("journeydate");
        type=getIntent().getStringExtra("type");
        sourceName=getIntent().getStringExtra("sourceName");
        destinationName=getIntent().getStringExtra("destinationName");
        selectedSeats=getIntent().getStringArrayListExtra("selectedSeats");
        source_id= (String) this.getIntent().getSerializableExtra("sourceid");
        destination_id= (String) this.getIntent().getSerializableExtra("destinationid");
        operatorID = (String) this.getIntent().getSerializableExtra("operatorID");
        CancellationPolicy = (String) this.getIntent().getSerializableExtra("CancellationPolicy");
        PartialCancellationAllowed = (String) this.getIntent().getSerializableExtra("PartialCancellationAllowed");
        convienceFee = (String) this.getIntent().getSerializableExtra("convienceFee");
        IdproofRequried = (String) this.getIntent().getSerializableExtra("IdproofRequried");
        amountsList=getIntent().getStringArrayListExtra("amountsList");
        serviceTaxList=getIntent().getStringArrayListExtra("serviceTaxList");
        serviceChargeList=getIntent().getStringArrayListExtra("serviceChargeList");

        titles.add("Mr");
        titles.add("Ms");
        titles.add("Mrs");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.menu_popup, titles);
        titleSpinOne.setAdapter(adapter);
        titleSpinTwo.setAdapter(adapter);
        titleSpinThree.setAdapter(adapter);
        titleSpinFour.setAdapter(adapter);
        titleSpinFive.setAdapter(adapter);
        titleSpinSix.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


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
            if(titleSpinOne.getText().toString().equalsIgnoreCase("")){
            }
            else{
                titlesList.add(titleSpinOne.getText().toString());
            }
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
            if(titleSpinTwo.getText().toString().equalsIgnoreCase("")){
            }
            else{
                titlesList.add(titleSpinTwo.getText().toString());
            }
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
            if(titleSpinThree.getText().toString().equalsIgnoreCase("")){
            }
            else{
                titlesList.add(titleSpinThree.getText().toString());
            }
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
            if(titleSpinFour.getText().toString().equalsIgnoreCase("")){
            }
            else{
                titlesList.add(titleSpinFour.getText().toString());
            }
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
            if(titleSpinFive.getText().toString().equalsIgnoreCase("")){
            }
            else{
                titlesList.add(titleSpinFive.getText().toString());
            }
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
            if(titleSpinSix.getText().toString().equalsIgnoreCase("")){
            }
            else{
                titlesList.add(titleSpinSix.getText().toString());
            }
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
        if(passengerNames.size()==selectedSeats.size() && passengerAge.size()==selectedSeats.size() && gender.size()==selectedSeats.size() && titlesList.size()==selectedSeats.size()){
            nextPage();
        }else{
            Toast.makeText(passengerDetails.this,"Please enter all details",Toast.LENGTH_SHORT).show();
            passengerNames.clear();
            passengerAge.clear();
            gender.clear();
            titlesList.clear();
        }
    }
});
    }

    private void nextPage() {
        Intent intent=new Intent(passengerDetails.this,busDetailsConfirmation.class);
        intent.putExtra("tripID", tripId);//new
        intent.putExtra("boardingPoint",boardingPoint);
        intent.putExtra("dropingPoint",dropingPoint);
        intent.putExtra("boardingPointID",boardingPointID);
        intent.putExtra("dropingPointID",dropingPointID);
        intent.putExtra("sourceid", source_id);//new
        intent.putExtra("destinationid", destination_id);//new
        intent.putExtra("email",email);
        intent.putExtra("number",number);
        intent.putExtra("sourceName", sourceName);
        intent.putExtra("journeydate",date);
        intent.putExtra("type",type);
        intent.putExtra("destinationName", destinationName);
        intent.putStringArrayListExtra("selectedSeats",selectedSeats);
        intent.putExtra("amount",amount);
        intent.putExtra("arrivalTime", arrivalTime);
        intent.putExtra("departureTime", departureTime);
        intent.putExtra("duration",duration);
        intent.putExtra("travelsName",travelsName);
        //NEW ITEMS ADDED
        intent.putExtra("operatorID",operatorID);//new
        intent.putExtra("operatorname", operator_name);//new
        intent.putExtra("providercode", providerCode);//new
        intent.putExtra("CancellationPolicy",CancellationPolicy);
        intent.putExtra("PartialCancellationAllowed",PartialCancellationAllowed);
        intent.putExtra("IdproofRequried",IdproofRequried);
        intent.putExtra("convienceFee",convienceFee);
        intent.putStringArrayListExtra("selectedSeats",selectedSeats);
        intent.putStringArrayListExtra("passengerNames",passengerNames);
        intent.putStringArrayListExtra("passengerAges",passengerAge);
        intent.putStringArrayListExtra("passengerGenders",gender);
        intent.putStringArrayListExtra("Titles",titlesList);
        intent.putStringArrayListExtra("amountsList",amountsList);
        intent.putStringArrayListExtra("serviceTaxList",serviceTaxList);
        intent.putStringArrayListExtra("serviceChargeList",serviceChargeList);
        startActivity(intent);
        passengerNames.clear();
        passengerAge.clear();
        gender.clear();
        titlesList.clear();
    }
}