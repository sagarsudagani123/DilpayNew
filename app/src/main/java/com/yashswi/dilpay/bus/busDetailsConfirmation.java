package com.yashswi.dilpay.bus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cashfree.pg.CFPaymentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yashswi.dilpay.Api_interface.Api_interface;
import com.yashswi.dilpay.R;
import com.yashswi.dilpay.adapters.passDetailsAdapter;
import com.yashswi.dilpay.models.userDetails;
import com.yashswi.dilpay.payment.paymentStart;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.cashfree.pg.CFPaymentService.PARAM_APP_ID;
import static com.cashfree.pg.CFPaymentService.PARAM_CARD_CVV;
import static com.cashfree.pg.CFPaymentService.PARAM_CARD_HOLDER;
import static com.cashfree.pg.CFPaymentService.PARAM_CARD_MM;
import static com.cashfree.pg.CFPaymentService.PARAM_CARD_NUMBER;
import static com.cashfree.pg.CFPaymentService.PARAM_CARD_YYYY;
import static com.cashfree.pg.CFPaymentService.PARAM_CUSTOMER_EMAIL;
import static com.cashfree.pg.CFPaymentService.PARAM_CUSTOMER_NAME;
import static com.cashfree.pg.CFPaymentService.PARAM_CUSTOMER_PHONE;
import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_AMOUNT;
import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_CURRENCY;
import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_ID;
import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_NOTE;
import static com.cashfree.pg.CFPaymentService.PARAM_PAYMENT_OPTION;
import static com.cashfree.pg.CFPaymentService.PARAM_UPI_VPA;
import static retrofit2.converter.gson.GsonConverterFactory.create;

public class busDetailsConfirmation extends AppCompatActivity {
    TextView pickup_location,drop_location,pickup_time,drop_time,duration1,journey_date,travels,bus_type,boarding_point,drop_point,base_fare,taxes;
    ImageView back;
    AppCompatButton proceed_payment;
    RecyclerView passDetails;
    JSONObject bookingDetails =null;
    JSONObject main=null;
    JSONObject user=null;
    Float taxAmount=0f;
    RelativeLayout progress;
    userDetails userDetails;

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

        //finding view
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
        passDetails=findViewById(R.id.passDetails);
        progress=findViewById(R.id.progress_layout);


        userDetails=new userDetails(busDetailsConfirmation.this);
        //getting intet data from previous activity
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

        //CONVERTING LIST ITEMS INTO SINGLE STRING FORMAT EX: "M~M~F"
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

        //CONVERTING BOOKING DETAILS INTO JSON FORMAT
        bookingDetails =new JSONObject();
        try{
            bookingDetails.put("TripId",tripId);
            bookingDetails.put("BoardingId",boardingPointID);
            bookingDetails.put("DroppingId",dropingPointID);
            bookingDetails.put("NoofSeats",""+selectedSeats.size());
            bookingDetails.put("Fares",fares);
            bookingDetails.put("Servicetax",serviceTax);
//            data2.put("Servicetax","0.00");
            bookingDetails.put("ServiceCharge",serviceCharges);
//            data2.put("ServiceCharge","0.00");
            bookingDetails.put("SeatNos",seats);
            bookingDetails.put("Seatcodes",""); // sent seat numbers for present
            bookingDetails.put("Titles",titles);
            bookingDetails.put("Names",names);
            bookingDetails.put("Ages",ages);
            bookingDetails.put("Genders",genders);
            bookingDetails.put("Address","Miyapur"); //present static
            bookingDetails.put("PostalCode","500090");  //present static
            bookingDetails.put("IdCardType","PAN");  //present static
            bookingDetails.put("IdCardNo","323");  //present static
            bookingDetails.put("IdCardIssuedBy","");// present static
            bookingDetails.put("MobileNo",number);
            bookingDetails.put("EmailId",email);
            bookingDetails.put("SourceId",source_id);
            bookingDetails.put("DestinationId",destination_id);
            bookingDetails.put("JourneyDate",date);
            bookingDetails.put("TripType","1");
            bookingDetails.put("SourceName",sourceName);
            bookingDetails.put("DestinationName",destinationName);
            bookingDetails.put("Provider",providerCode);
            bookingDetails.put("Operator",travelsName);
            bookingDetails.put("DisplayName",travelsName);
            bookingDetails.put("BusTypeName",type);
            bookingDetails.put("BusType","4");/////////////////
            bookingDetails.put("BoardingPointDetails",boardingPoint);
            bookingDetails.put("DroppingPointDetails",dropingPoint);
            bookingDetails.put("DepartureTime",departureTime);
            bookingDetails.put("ArrivalTime",arrivalTime);
            bookingDetails.put("CancellationPolicy",CancellationPolicy);
            bookingDetails.put("PartialCancellationAllowed",PartialCancellationAllowed);//boolean
            bookingDetails.put("ConvenienceFee",convienceFee);
            bookingDetails.put("UserType","5");
            bookingDetails.put("IsIdProofRequried",false);
        }catch (JSONException e){

        }
        //==================================
        user=new JSONObject();
        try{
        user.put("username",userDetails.getNumber());
        user.put("amount",amount);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        main=new JSONObject();
        try {
            main.put("passengerDetails",bookingDetails);
            main.put("userDetails",user);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("MainData",main.toString());

        //==================================
        //SETTING DATA TO VIEW'S
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
//        taxes.setText(String.valueOf(decimalFormat.format(taxAmount)));
        taxes.setText("0.00");

        //SETTING PASSENGER'S LIST
        LinearLayoutManager layoutManager=new LinearLayoutManager(busDetailsConfirmation.this,RecyclerView.VERTICAL, false);
        passDetails.setLayoutManager(layoutManager);
        passDetailsAdapter adapter=new passDetailsAdapter(passengerNames,passengerGenders,passengerAges,selectedSeats,busDetailsConfirmation.this);
        passDetails.setAdapter(adapter);

        back.setOnClickListener(v -> finish());

        //send data to server and if response is positive take to payment page
        proceed_payment.setOnClickListener(v -> {
            if(bookingDetails !=null){
                progress.setVisibility(View.VISIBLE);
//                sendBookingDetails(bookingDetails.toString());
                sendBookingDetails(main.toString());
            }else{
                Toast.makeText(busDetailsConfirmation.this,"Something went wrong! Try again",Toast.LENGTH_SHORT).show();
                finish();
            }

        });


    }
    private void sendBookingDetails(String jsonData) {
        progress.setVisibility(View.VISIBLE);
        Log.e("jsonDatafinal",jsonData);
        Gson gson=new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);

        Call<String> call = api.bookingDetails(jsonData);
//        Toast.makeText(paymentTest.this,"called..."+jsonData,Toast.LENGTH_SHORT).show();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                progress.setVisibility(View.GONE);
                Log.e("testFinal",response.body());
                if(response.body()!=null){
                    try {
//                        Toast.makeText(busDetailsConfirmation.this,"inside  "+response.body(),Toast.LENGTH_LONG).show();

                        JSONObject obj=new JSONObject(response.body());
                        //IF TOKEN GENERATED INITIATE PAYMENT
                        if(obj.getString("Status").equalsIgnoreCase("OK")){
                            String token,orderID,amount,name,number;
                            orderID=obj.getString("Orderid");
                            token=obj.getString("Token");
                            Log.e("Tokencheck",token);
                            amount=obj.getString("Amount");
//                            Intent intent=new Intent(busDetailsConfirmation.this,paymentStart.class);
//                            intent.putExtra("orderID",orderID);
//                            intent.putExtra("Token",token);
//                            intent.putExtra("Amount",amount);
//                            startActivity(intent);
//                            userDetails=new userDetails(paymentStart.this);
                            name=userDetails.getName();
                            number=userDetails.getNumber();
                            payment(token,orderID,amount,name,number);
                        }else{
                            Toast.makeText(busDetailsConfirmation.this, "Seat is no longer available", Toast.LENGTH_SHORT).show();
                        }
//                        Intent intent=new Intent(busDetailsConfirmation.this, paymentStart.class);
                        //PASS ORDER ID, TOKEN, AMOUNT, NAME, NUMBER TO PROCEED TO PAYMENT
//                        intent.putExtra("data", bookingDetails.toString());
//                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(busDetailsConfirmation.this,e.toString(),Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(busDetailsConfirmation.this,"Something went wrong! Try again",Toast.LENGTH_LONG).show();
                }
                //get token and initiate payment
                Log.e("BlockCheck",response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(busDetailsConfirmation.this,"failed to send data!!"+t.toString(),Toast.LENGTH_SHORT).show();
                progress.setVisibility(View.GONE);
            }
        });
    }
    void payment(String token,String orderID,String amount,String name,String number)
    {
        String token1="9c9JCN4MzUIJiOicGbhJCLiQ1VKJiOiAXe0Jye.t5QfiIzMxImM2YzNykTMwYjI6ICdsF2cfJCLyIjMzUDO0EjNxojIwhXZiwiIS5USiojI5NmblJnc1NkclRmcvJCLwIjOiQnb19WbBJXZkJ3biwiI2UjN1YTNiojIklkclRmcvJye.Kt51e7bb3KJ5JmU39WfPhVmoYPIGbHNcj_m_lSbLqaQdcyFBud0qkXLNEclduZDno_";
        Map<String,String> params=new HashMap<>();
        params.put(PARAM_APP_ID, "4207d3b63a1ecc9a5d79a8687024");
        params.put(PARAM_ORDER_ID, orderID);
        params.put(PARAM_ORDER_AMOUNT, amount);
        params.put(PARAM_ORDER_NOTE, "Bus Ticket booking");
        params.put(PARAM_CUSTOMER_NAME,name);
        params.put(PARAM_CUSTOMER_PHONE, number);
        params.put(PARAM_CUSTOMER_EMAIL, "thottempudi22@gmail.com");
        params.put(PARAM_ORDER_CURRENCY, "INR");
        //////////////////////
//        params.put(PARAM_PAYMENT_OPTION, "card");
//        params.put(PARAM_CARD_NUMBER, "4111111111111111");//Replace Card number
//        params.put(PARAM_CARD_MM, "07"); // Card Expiry Month in MM
//        params.put(PARAM_CARD_YYYY, "2023"); // Card Expiry Year in YYYY
//        params.put(PARAM_CARD_HOLDER, "Test"); // Card Holder name
//        params.put(PARAM_CARD_CVV, "123"); // Card CVV
        //////////////////////

//        params.put(PARAM_PAYMENT_OPTION, "userVPA");
//        params.put(PARAM_UPI_VPA, "testtpv@gocash");
        try {
            CFPaymentService cfPaymentService = CFPaymentService.getCFPaymentServiceInstance();
            cfPaymentService.setOrientation(0);
            cfPaymentService.doPayment(busDetailsConfirmation.this, params, token, "TEST","#6dd5ed", "#FAFAFA", false);
//            cfPaymentService.upiPayment(busDetailsConfirmation.this,params,token,"TEST");
        }
        catch (Exception e){
            Toast.makeText(busDetailsConfirmation.this,"payment"+e.toString(),Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("paymentcheck", "ReqCode : " + CFPaymentService.REQ_CODE);
        if (data != null) {
            Bundle  bundle = data.getExtras();
            if (bundle != null) {
                for (String  key  :  bundle.keySet()) {
                    if (bundle.getString(key) != null) {
                        Log.d("paymentcheck", key + " : " + bundle.getString(key));
//
                    }
                }
                String status = bundle.getString("txStatus");
                String paymentMode = bundle.getString("paymentMode");
                String orderId = bundle.getString("orderId");
                String txTime = bundle.getString("txTime");
                String referenceId = bundle.getString("referenceId");
                String txMsg = bundle.getString("txMsg");
                String signature = bundle.getString("signature");
                String orderAmount = bundle.getString("orderAmount");
//                if (status.equalsIgnoreCase("success")) {
                    Intent intent = new Intent(busDetailsConfirmation.this, paymentStart.class);
                    intent.putExtra("status", status);
                    intent.putExtra("paymentMode", paymentMode);
                    intent.putExtra("orderId", orderId);
                    intent.putExtra("txTime", txTime);
                    intent.putExtra("referenceId", referenceId);
                    intent.putExtra("txMsg", txMsg);
                    intent.putExtra("signature", signature);
                    intent.putExtra("orderAmount",orderAmount);
//                    Log.e("sendingData",status+""+);
                    startActivity(intent);

//                }
            }
        }
    }
}