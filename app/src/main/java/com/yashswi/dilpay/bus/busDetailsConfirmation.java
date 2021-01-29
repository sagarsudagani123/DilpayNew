package com.yashswi.dilpay.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yashswi.dilpay.R;
import com.yashswi.dilpay.adapters.passDetailsAdapter;
import com.yashswi.dilpay.payment.paymentTest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class busDetailsConfirmation extends AppCompatActivity {
    TextView pickup_location,drop_location,pickup_time,drop_time,duration1,journey_date,travels,bus_type,boarding_point,drop_point,base_fare,taxes;
    ImageView back;
    AppCompatButton proceed_payment;
    RecyclerView passDetails;
    JSONObject data=null;
    Float taxAmount=0f;

    String boardingPoint,dropingPoint,email,number,amount,date,type,sourceName,destinationName,arrivalTime,departureTime,duration,travelsName,
    tripId, providerCode,operator_name,source_id,destination_id,
    operatorID, CancellationPolicy, PartialCancellationAllowed, convienceFee, IdproofRequried,boardingPointID,dropingPointID;
    ArrayList<String> selectedSeats=new ArrayList<>();
    ArrayList<String> passengerNames=new ArrayList<>();
    ArrayList<String> passengerAges=new ArrayList<>();
    ArrayList<String> passengerGenders=new ArrayList<>();
    ArrayList<String> amountsList=new ArrayList<>();
    ArrayList<String> serviceTaxList =new ArrayList<>();
    ArrayList<String> serviceChargeList=new ArrayList<>();
    ArrayList<String> titlesList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_details_confirmation);

        back=findViewById(R.id.back);
        pickup_location=findViewById(R.id.pickup);
        drop_location=findViewById(R.id.drop);
        pickup_time=findViewById(R.id.pickup_time);
        drop_time=findViewById(R.id.drop_time);
        duration1=findViewById(R.id.duration);
        journey_date=findViewById(R.id.journey_date);
        travels=findViewById(R.id.travels);
        bus_type=findViewById(R.id.bus_type);
        boarding_point=findViewById(R.id.board_point);
        drop_point=findViewById(R.id.drop_point);
        base_fare=findViewById(R.id.amount);
        taxes=findViewById(R.id.taxes);
        proceed_payment=findViewById(R.id.proceed_payment);

        tripId = (String) this.getIntent().getSerializableExtra("tripID");
        providerCode = (String) this.getIntent().getSerializableExtra("providercode");
        operator_name= (String) this.getIntent().getSerializableExtra("operatorname");
        boardingPoint=getIntent().getStringExtra("boardingPoint");
        dropingPoint=getIntent().getStringExtra("dropingPoint");
        boardingPointID=getIntent().getStringExtra("boardingPointID");
        dropingPointID=getIntent().getStringExtra("dropingPointID");
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
        source_id= (String) this.getIntent().getSerializableExtra("sourceid");
        destination_id= (String) this.getIntent().getSerializableExtra("destinationid");
        operatorID = (String) this.getIntent().getSerializableExtra("operatorID");
        CancellationPolicy = (String) this.getIntent().getSerializableExtra("CancellationPolicy");
        PartialCancellationAllowed = (String) this.getIntent().getSerializableExtra("PartialCancellationAllowed");
        convienceFee = (String) this.getIntent().getSerializableExtra("convienceFee");
        IdproofRequried = (String) this.getIntent().getSerializableExtra("IdproofRequried");
        selectedSeats=getIntent().getStringArrayListExtra("selectedSeats");
        passengerNames=getIntent().getStringArrayListExtra("passengerNames");
        passengerAges=getIntent().getStringArrayListExtra("passengerAges");
        passengerGenders=getIntent().getStringArrayListExtra("passengerGenders");
        amountsList=getIntent().getStringArrayListExtra("amountsList");
        serviceTaxList=getIntent().getStringArrayListExtra("serviceTaxList");
        serviceChargeList=getIntent().getStringArrayListExtra("serviceChargeList");
        titlesList=getIntent().getStringArrayListExtra("Titles");

        //==================================================================================

        StringBuilder names= new StringBuilder();
        StringBuilder ages= new StringBuilder();
        StringBuilder genders= new StringBuilder();
        StringBuilder seats= new StringBuilder();
        StringBuilder titles= new StringBuilder();
        StringBuilder fares= new StringBuilder();
        StringBuilder serviceTax= new StringBuilder();
        StringBuilder serviceCharges= new StringBuilder();

        for(int i=0;i<passengerAges.size();i++){
            if(ages.toString().equalsIgnoreCase("")){
                ages = new StringBuilder(passengerAges.get(i));
                names=new StringBuilder(passengerNames.get(i));
                genders=new StringBuilder(passengerGenders.get(i));
                seats=new StringBuilder(selectedSeats.get(i));
                titles=new StringBuilder(titlesList.get(i));
                fares=new StringBuilder(amountsList.get(i));
                serviceTax=new StringBuilder(serviceTaxList.get(i));
                serviceCharges=new StringBuilder(serviceChargeList.get(i));
            }else {
                ages.append("~").append(passengerAges.get(i));
                names.append("~").append(passengerNames.get(i));
                genders.append("~").append(passengerGenders.get(i));
                seats.append("~").append(selectedSeats.get(i));
                titles.append("~").append(titlesList.get(i));
                fares.append("~").append(amountsList.get(i));
                serviceTax.append("~").append(serviceTaxList.get(i));
                serviceCharges.append("~").append(serviceChargeList.get(i));

            }
        }
        JSONObject data2=new JSONObject();
        try{
            data2.put("Address","Miyapur"); //present static
            data2.put("Ages",ages);
            data2.put("BoardingId",boardingPointID);
            data2.put("BoardingPointDetails",boardingPoint);
            data2.put("BusTypeName",type);
            data2.put("CancellationPolicy",CancellationPolicy);
            data2.put("City","Hyderabad");  //present static
            data2.put("ConvenienceFee",convienceFee);
            data2.put("DepartureTime",departureTime);
            data2.put("DestinationId",destination_id);
            data2.put("DestinationName",destinationName);
            data2.put("DisplayName",travelsName);
            data2.put("DroppingId",dropingPointID);
            data2.put("DroppingPointDetails",dropingPoint);
            data2.put("EmailId",email);
            data2.put("EmergencyMobileNo",number);
            data2.put("Fares",fares);
            data2.put("Genders",genders);
            data2.put("IdCardIssuedBy","Gov");// present static
            data2.put("IdCardNo","323");  //present static
            data2.put("IdCardType","PAN");  //present static
            data2.put("JourneyDate",date);
            data2.put("MobileNo",number);
            data2.put("Names",names);
            data2.put("NoofSeats",selectedSeats.size());
            data2.put("Operator",travelsName);
            data2.put("PartialCancellationAllowed",PartialCancellationAllowed);//boolean
            data2.put("PostalCode","500090");  //present static
            data2.put("Provider",providerCode);
            data2.put("ReturnDate",null);
            data2.put("Seatcodes",seats); // sent seat numbers for present
            data2.put("SeatNos",seats);
            data2.put("ServiceCharge",serviceCharges);
            data2.put("Servicetax",serviceTax);
            data2.put("SourceId",source_id);
            data2.put("SourceName",sourceName);
            data2.put("State","Telangana");  //present static
            data2.put("Titles",titles);
            data2.put("TripId",tripId);
            data2.put("TripType",1);
            data2.put("UserType",5);

            Log.e("detailsJSON",data2.toString());
        }catch (JSONException e){

        }
        //==================================================================================





        pickup_location.setText(sourceName);
        drop_location.setText(destinationName);
        pickup_time.setText(arrivalTime);
        drop_time.setText(departureTime);
        duration1.setText(duration);
        journey_date.setText(date);
        travels.setText(travelsName);
        bus_type.setText(type);
        boarding_point.setText(boardingPoint);
        drop_point.setText(dropingPoint);
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        base_fare.setText(decimalFormat.format(Float.parseFloat(amount)));
        for(int i=0;i<serviceTaxList.size();i++){
            taxAmount=taxAmount+Float.parseFloat(serviceTaxList.get(i));
            taxAmount=taxAmount+Float.parseFloat(serviceChargeList.get(i));
        }
        taxes.setText(String.valueOf(decimalFormat.format(taxAmount)));

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
                Intent i=new Intent(busDetailsConfirmation.this, paymentTest.class);
                i.putExtra("data",data2.toString());
                startActivity(i);
            }
        });


    }
}