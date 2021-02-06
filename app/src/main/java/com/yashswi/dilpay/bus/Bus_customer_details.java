package com.yashswi.dilpay.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.yashswi.dilpay.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Bus_customer_details extends AppCompatActivity {
    TextInputEditText e_email,e_name,e_number;
    TextView seatsSelected,amount;
    ImageView back;
    AutoCompleteTextView board_spin,drop_spin;
    ArrayList<String> bordingPoints =new ArrayList<>();
    ArrayList<String> dropingPoints=new ArrayList<>();
    ArrayList<String> bordingID =new ArrayList<>();
    ArrayList<String> dropingID=new ArrayList<>();
    ArrayList<String> selectedSeats=new ArrayList<>();
    ArrayList<String> amountsList=new ArrayList<>();
    ArrayList<String> serviceTaxList =new ArrayList<>();
    ArrayList<String> serviceChargeList=new ArrayList<>();
    double totalAmount;
    AppCompatButton proceed;
    String boardingPoint,dropingPoint,email,number,seats="";

    String tripId, providerCode,operator_name,source_id,destination_id,date,type,sourceName,destinationName,arrivalTime,departureTime,duration,travelsName,
            operatorID, CancellationPolicy, PartialCancellationAllowed, convienceFee, IdproofRequried;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_customer_details);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //FINDING VIEWS
        board_spin=findViewById(R.id.board_spin);
        drop_spin=findViewById(R.id.drop_spin);
        e_email=findViewById(R.id.e_email);
        e_name=findViewById(R.id.e_name);
        e_number=findViewById(R.id.e_number);
        seatsSelected=findViewById(R.id.seatsSelectedBookDetails);
        amount=findViewById(R.id.amountBookDetails);
        proceed=findViewById(R.id.proceed);
        back=findViewById(R.id.back);

        //GETTING INTENT DATA
        tripId = (String) this.getIntent().getSerializableExtra("tripID");
        providerCode = (String) this.getIntent().getSerializableExtra("providercode");
        operator_name= (String) this.getIntent().getSerializableExtra("operatorname");
        source_id= (String) this.getIntent().getSerializableExtra("sourceid");
        destination_id= (String) this.getIntent().getSerializableExtra("destinationid");
        date= (String) this.getIntent().getSerializableExtra("journeydate");
        type=getIntent().getStringExtra("type");
        bordingPoints =getIntent().getStringArrayListExtra("bordingPoints");
        dropingPoints=getIntent().getStringArrayListExtra("dropingPints");
        bordingID=getIntent().getStringArrayListExtra("bordingID");
        dropingID=getIntent().getStringArrayListExtra("dropingID");
        sourceName=getIntent().getStringExtra("sourceName");
        destinationName=getIntent().getStringExtra("destinationName");
        arrivalTime=getIntent().getStringExtra("arrivalTime");
        departureTime=getIntent().getStringExtra("departureTime");
        duration =getIntent().getStringExtra("duration");
        travelsName=getIntent().getStringExtra("travelsName");
        selectedSeats=getIntent().getStringArrayListExtra("selectedSeats");
        totalAmount=getIntent().getFloatExtra("totalAmt",0.0f);
        amountsList=getIntent().getStringArrayListExtra("amountsList");
        serviceTaxList=getIntent().getStringArrayListExtra("serviceTaxList");
        serviceChargeList=getIntent().getStringArrayListExtra("serviceChargeList");
        operatorID = (String) this.getIntent().getSerializableExtra("operatorID");
        CancellationPolicy = (String) this.getIntent().getSerializableExtra("CancellationPolicy");
        PartialCancellationAllowed = (String) this.getIntent().getSerializableExtra("PartialCancellationAllowed");
        convienceFee = (String) this.getIntent().getSerializableExtra("convienceFee");
        IdproofRequried = (String) this.getIntent().getSerializableExtra("IdproofRequried");

        //SETTING DATA TO SPINNERS
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.menu_popup, bordingPoints);
        board_spin.setAdapter(adapter);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getApplicationContext(), R.layout.menu_popup, dropingPoints);
        drop_spin.setAdapter(adapter1);

        back.setOnClickListener(v -> finish());

        //GOING TO ACTIVITY TO ENTER PASSENEGR DETAILS
        proceed.setOnClickListener(view -> {
            boardingPoint=board_spin.getText().toString();
            dropingPoint=drop_spin.getText().toString();
            email=e_email.getText().toString();
            number=e_number.getText().toString();
            String bordingPointID="",dropingPointID="";
            for(int i=0;i<bordingPoints.size();i++){
                if(boardingPoint.equalsIgnoreCase(bordingPoints.get(i))){
                    bordingPointID=bordingID.get(i);
                }
            }
            for(int j=0;j<dropingPoints.size();j++){
                if(dropingPoint.equalsIgnoreCase(dropingPoints.get(j))){
                    dropingPointID=dropingID.get(j);
                }
            }

            Intent intent=new Intent(Bus_customer_details.this,passengerDetails.class);
            intent.putExtra("tripID", tripId);//new
            intent.putExtra("boardingPoint",boardingPoint);
            intent.putExtra("dropingPoint",dropingPoint);
            intent.putExtra("boardingPointID",bordingPointID);
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
            intent.putExtra("amount",amount.getText());
            intent.putExtra("arrivalTime", arrivalTime);
            intent.putExtra("departureTime", departureTime);
            intent.putExtra("duration",duration);
            intent.putExtra("travelsName",travelsName);
            intent.putStringArrayListExtra("amountsList",amountsList);
            intent.putStringArrayListExtra("serviceTaxList",serviceTaxList);
            intent.putStringArrayListExtra("serviceChargeList",serviceChargeList);
            intent.putExtra("operatorID",operatorID);//new
            intent.putExtra("operatorname", operator_name);//new
            intent.putExtra("providercode", providerCode);//new
            intent.putExtra("CancellationPolicy",CancellationPolicy);
            intent.putExtra("PartialCancellationAllowed",PartialCancellationAllowed);
            intent.putExtra("IdproofRequried",IdproofRequried);
            intent.putExtra("convienceFee","0.00");
            if(boardingPoint.equalsIgnoreCase("") || dropingPoint.equalsIgnoreCase("") || email.equalsIgnoreCase("") || number.equalsIgnoreCase("")){
                Toast.makeText(Bus_customer_details.this,"Fill in all details",Toast.LENGTH_SHORT).show();
            }else{
                startActivity(intent);
            }
        });

        //APPENDING THE SELECTED SEAT TO TEXT VIEW
        for(int i=0;i<selectedSeats.size();i++){
            if(i==0){
                seatsSelected.append(""+selectedSeats.get(i));
                seats.concat(""+selectedSeats.get(i));
            }else{
                seatsSelected.append(","+selectedSeats.get(i));
                seats.concat(","+selectedSeats.get(i));
            }
        }

        //CONVERTING FLOAT TO 2 DECIMAL VALUE
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        amount.setText(decimalFormat.format(totalAmount));


    }
}