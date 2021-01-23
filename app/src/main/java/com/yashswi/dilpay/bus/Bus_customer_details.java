package com.yashswi.dilpay.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.yashswi.dilpay.R;

import java.util.ArrayList;

public class Bus_customer_details extends AppCompatActivity {
    TextInputEditText e_email,e_name,e_number;
    TextView seatsSelected,amount;
    AutoCompleteTextView board_spin,drop_spin;
    ArrayList<String> bordingPoints =new ArrayList<>();
    ArrayList<String> dropingPoints=new ArrayList<>();
    ArrayList<String> selectedSeats=new ArrayList<>();
    double totalAmount;
    AppCompatButton proceed;
    String boardingPoint,dropingPoint,email,number,seats="";

    String tripId, providerCode,operator_name,source_id,destination_id,date,type,sourceName,destinationName,arrivalTime,departureTime,duration,travelsName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_customer_details);
        board_spin=findViewById(R.id.board_spin);
        drop_spin=findViewById(R.id.drop_spin);
        e_email=findViewById(R.id.e_email);
        e_name=findViewById(R.id.e_name);
        e_number=findViewById(R.id.e_number);
        seatsSelected=findViewById(R.id.seatsSelectedBookDetails);
        amount=findViewById(R.id.amountBookDetails);
        proceed=findViewById(R.id.proceed);

        tripId = (String) this.getIntent().getSerializableExtra("tripID");
        providerCode = (String) this.getIntent().getSerializableExtra("providercode");
        operator_name= (String) this.getIntent().getSerializableExtra("operatorname");
        source_id= (String) this.getIntent().getSerializableExtra("sourceid");
        destination_id= (String) this.getIntent().getSerializableExtra("destinationid");
        date= (String) this.getIntent().getSerializableExtra("journeydate");
        type=getIntent().getStringExtra("type");
        bordingPoints =getIntent().getStringArrayListExtra("bordingPoints");
        dropingPoints=getIntent().getStringArrayListExtra("dropingPints");
        sourceName=getIntent().getStringExtra("sourceName");
        destinationName=getIntent().getStringExtra("destinationName");
        arrivalTime=getIntent().getStringExtra("arrivalTime");
        departureTime=getIntent().getStringExtra("departureTime");
        duration =getIntent().getStringExtra("duration");
        travelsName=getIntent().getStringExtra("travelsName");

        selectedSeats=getIntent().getStringArrayListExtra("selectedSeats");
        totalAmount=getIntent().getFloatExtra("totalAmt",0.0f);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boardingPoint=board_spin.getText().toString();
                dropingPoint=drop_spin.getText().toString();
                email=e_email.getText().toString();
                number=e_number.getText().toString();
                Intent intent=new Intent(Bus_customer_details.this,passengerDetails.class);
                intent.putExtra("boardingPoint",boardingPoint);
                intent.putExtra("dropingPoint",dropingPoint);
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
                startActivity(intent);
            }
        });
        for(int i=0;i<selectedSeats.size();i++){
            if(i==0){
                seatsSelected.append(""+selectedSeats.get(i));
                seats.concat(""+selectedSeats.get(i));
            }else{
                seatsSelected.append(","+selectedSeats.get(i));
                seats.concat(","+selectedSeats.get(i));
            }
        }
        amount.setText(""+totalAmount);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.menu_popup, bordingPoints);
        board_spin.setAdapter(adapter);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getApplicationContext(), R.layout.menu_popup, dropingPoints);
        drop_spin.setAdapter(adapter1);
    }
}