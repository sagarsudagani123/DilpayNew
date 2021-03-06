package com.dilpay.app.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dilpay.app.Api_interface.seatSelection;
import com.dilpay.app.R;
import com.dilpay.app.adapters.seat_image_adapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.IDN;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static retrofit2.converter.gson.GsonConverterFactory.create;

public class Bus_seating extends AppCompatActivity implements seatSelection {

    RelativeLayout progress, mainLayout;
    LinearLayout selectIndex;
    ImageView back;
    TextView upperText, lowerText, seatsSelected, amountText, travels;
    AppCompatButton done;
    List<Boolean> isAvailable = new ArrayList<>();
    List<String> seatType = new ArrayList<>();
    List<Boolean> isLadies = new ArrayList<>();
    List<Float> NetFare = new ArrayList<>();
    List<Float> Servicetax = new ArrayList<>();
    List<Float> OperatorServiceCharge = new ArrayList<>();
    List<Boolean> isSelected = new ArrayList<>();
    List<String> seatNumber = new ArrayList<>();
    List<Boolean> zindexIsAvailable = new ArrayList<>();
    List<String> zindexSeatType = new ArrayList<>();
    List<Boolean> zindexIsLadies = new ArrayList<>();
    List<Float> zindexNetFare = new ArrayList<>();
    List<Float> zindexServicetax = new ArrayList<>();
    List<Float> zindexOperatorServiceCharge = new ArrayList<>();
    List<Boolean> zindexisSelected = new ArrayList<>();
    List<String> zindexseatNumber = new ArrayList<>();
    ArrayList<String> selectedSeats = new ArrayList<>();
    ArrayList<String> bordingPoints = new ArrayList<>();
    ArrayList<String> dropingPoints = new ArrayList<>();
    ArrayList<String> bordingID = new ArrayList<>();
    ArrayList<String> dropingID = new ArrayList<>();
    ArrayList<String> amountsList = new ArrayList<>();
    ArrayList<String> serviceTaxList = new ArrayList<>();
    ArrayList<String> serviceChargeList = new ArrayList<>();
    float total_amount = 0f;
    String tripId, providerCode, operator_name, source_id, destination_id, date, type, sourceName, destinationName, arrivalTime, departureTime, duration, travelsName, operatorID, CancellationPolicy, PartialCancellationAllowed, convienceFee, IdproofRequried;
    RequestQueue mqueue;
    RecyclerView upper, seater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_seating);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //FINDING VIEW,S
        progress = findViewById(R.id.progress);
        mainLayout = findViewById(R.id.mainLayout);
        mainLayout.setVisibility(View.GONE);
        progress.setVisibility(View.VISIBLE);
        back = findViewById(R.id.back);
        upperText = findViewById(R.id.upperText);
        lowerText = findViewById(R.id.lowerText);
        upper = findViewById(R.id.upper);
        selectIndex = findViewById(R.id.selectIndex);
        seater = findViewById(R.id.seater);
        seatsSelected = findViewById(R.id.seatsSelected);
        done = findViewById(R.id.selectDrop);
        amountText = findViewById(R.id.amount1);
        travels = findViewById(R.id.travel_name);

        seater.setAdapter(null);
        seater.setHasFixedSize(true);
        upper.setAdapter(null);
        upper.setHasFixedSize(true);

        //GETTING INTENT DATA
        tripId = (String) this.getIntent().getSerializableExtra("tripID");
        providerCode = (String) this.getIntent().getSerializableExtra("providercode");
        operator_name = (String) this.getIntent().getSerializableExtra("operatorname");
        source_id = (String) this.getIntent().getSerializableExtra("sourceid");
        destination_id = (String) this.getIntent().getSerializableExtra("destinationid");
        sourceName = getIntent().getStringExtra("sourceName");
        destinationName = getIntent().getStringExtra("destinationName");
        date = (String) this.getIntent().getSerializableExtra("journeydate");
        type = getIntent().getStringExtra("type");
        bordingPoints = getIntent().getStringArrayListExtra("bordingPoints");
        dropingPoints = getIntent().getStringArrayListExtra("dropingPints");
        bordingID = getIntent().getStringArrayListExtra("bordingID");
        dropingID = getIntent().getStringArrayListExtra("dropingID");
        arrivalTime = getIntent().getStringExtra("arrivalTime");
        departureTime = getIntent().getStringExtra("departureTime");
        duration = getIntent().getStringExtra("duration");
        travelsName = getIntent().getStringExtra("travelsName");
        operatorID = (String) this.getIntent().getSerializableExtra("operatorID");
        CancellationPolicy = (String) this.getIntent().getSerializableExtra("CancellationPolicy");
        PartialCancellationAllowed = (String) this.getIntent().getSerializableExtra("PartialCancellationAllowed");
        convienceFee = (String) this.getIntent().getSerializableExtra("convienceFee");
        IdproofRequried = (String) this.getIntent().getSerializableExtra("IdproofRequried");

        //SETTING TRAVELS NAME TO HEADER TOOLBAR
        travels.setText(operator_name);

        upper.setVisibility(View.GONE);
        seater.setVisibility(View.VISIBLE);

        //HIDING UPPER LOWER BUTTONS FOR SEATER AND SEMI SLEEPER BUS
        if ((type.contains("sleeper") || type.contains("Sleeper"))) {
            selectIndex.setVisibility(View.VISIBLE);
        }
        if ((type.contains("semi sleeper") || type.contains("Semi sleeper") || type.contains("semi Sleeper") || type.contains("Semi Sleeper"))) {
            selectIndex.setVisibility(View.GONE);
        }
//        if(type.contains("sleeper")||type.contains("Sleeper")  && (!type.contains("semi sleeper")||!type.contains("Semi sleeper")||!type.contains("semi Sleeper")||!type.contains("Semi Sleeper"))){
//            selectIndex.setVisibility(View.VISIBLE);
//        }

        //UPPER BUTTON ACTION
        upperText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upperText.setBackground(ContextCompat.getDrawable(Bus_seating.this, R.drawable.btn_right_active));
                upperText.setTextColor(getResources().getColor(R.color.white));
                lowerText.setBackground(ContextCompat.getDrawable(Bus_seating.this, R.drawable.btn_left));
                lowerText.setTextColor(getResources().getColor(R.color.gradientStart));
                seater.setVisibility(View.GONE);
                upper.setVisibility(View.VISIBLE);
            }
        });

        //LOWER BUTTON ACTION
        lowerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upperText.setBackground(ContextCompat.getDrawable(Bus_seating.this, R.drawable.btn_right));
                upperText.setTextColor(getResources().getColor(R.color.gradientStart));
                lowerText.setBackground(ContextCompat.getDrawable(Bus_seating.this, R.drawable.btn_left_active));
                lowerText.setTextColor(getResources().getColor(R.color.white));
                upper.setVisibility(View.GONE);
                seater.setVisibility(View.VISIBLE);
            }
        });

        //PROCEEDING TO NEXT SCREEN
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedSeats.size()<=0){
                    Toast.makeText(Bus_seating.this,"Please select seat",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(Bus_seating.this, Bus_customer_details.class);
                    intent.putExtra("tripID", tripId);
                    intent.putExtra("providercode", providerCode);
                    intent.putExtra("operatorname", operator_name);
                    intent.putExtra("sourceid", source_id);
                    intent.putExtra("destinationid", destination_id);
                    intent.putExtra("journeydate", date);
                    intent.putExtra("type", type);
                    intent.putExtra("sourceName", sourceName);
                    intent.putExtra("destinationName", destinationName);
                    intent.putStringArrayListExtra("dropingPints", dropingPoints);
                    intent.putStringArrayListExtra("bordingPoints", bordingPoints);
                    intent.putStringArrayListExtra("dropingID", dropingID);
                    intent.putStringArrayListExtra("bordingID", bordingID);
                    intent.putStringArrayListExtra("selectedSeats", selectedSeats);
                    intent.putStringArrayListExtra("amountsList", amountsList);
                    intent.putStringArrayListExtra("serviceTaxList", serviceTaxList);
                    intent.putStringArrayListExtra("serviceChargeList", serviceChargeList);
                    intent.putExtra("totalAmt", total_amount);//with out taxes
                    intent.putExtra("arrivalTime", arrivalTime);
                    intent.putExtra("departureTime", departureTime);
                    intent.putExtra("duration", duration);
                    intent.putExtra("travelsName", travelsName);
                    intent.putExtra("operatorID", operatorID);//new
                    intent.putExtra("CancellationPolicy", CancellationPolicy);
                    intent.putExtra("PartialCancellationAllowed", PartialCancellationAllowed);
                    intent.putExtra("IdproofRequried", IdproofRequried);
                    intent.putExtra("convienceFee", convienceFee);
                    startActivity(intent);
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //GETTING SEATING DATA
        loadSeats();
    }

    //
    private void loadSeats() {
        String finalURL = "";
        String url1 = "http://dilbus.in/api/seatbooking.php?id=" + tripId + "&OperatorCode=" + providerCode + "&OperatorName=" + operator_name + "&SourceIDJ=" + source_id + "&DestinationIDJ=" + destination_id + "&DateDOJ=" + date;
        //ENCODING URL
        URL url = null;
        try {
            url = new URL(url1);
            URI uri = new URI(url.getProtocol(), url.getUserInfo(), IDN.toASCII(url.getHost()), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
            finalURL = uri.toASCIIString();
            Log.e("testAgain",finalURL);
        } catch (MalformedURLException e) {
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        mqueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, finalURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mainLayout.setVisibility(View.VISIBLE);
                Log.e("testAgain", response);
                progress.setVisibility(View.GONE);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Seats");
                    int maxColumn = 0;
                    int maxRow=0;
                    //to get no of columns present
                    for (int k = 0; k < jsonArray.length(); k++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(k);

                    }
                    for (int k = 0; k < jsonArray.length(); k++) {

                        JSONObject jsonObject1 = jsonArray.getJSONObject(k);
                        int index = Integer.parseInt(jsonObject1.getString("Zindex"));
                        int num1=0,num2=0;
                        if(index==0){
                            num1 = Integer.parseInt(jsonObject1.getString("Row"));
                            if (num1 > maxRow) {
                                maxRow = num1;
                            }
                            num2 = Integer.parseInt(jsonObject1.getString("Column"));
                            if (num2 > maxColumn) {
                                maxColumn = num2;
                            }
                        }

//                        Log.e("seatSetting","column="+num2+" row="+num1);
                    }
//                    Toast.makeText(Bus_seating.this,"column="+maxColumn+" row="+maxRow,Toast.LENGTH_SHORT).show();
                    //checking column from 0 to max column
                    for (int l = 0; l <= maxColumn; l++) {
                        //check rows from 0 to 5 for every column
                        for (int m = 0; m <= 4; m++) {
                            int counterLower = 50;
                            int counterUpper = 50;
                            //checking eah and every json object weather currrent row and column element is present r not
                            for (int n = 0; n < jsonArray.length(); n++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(n);
                                String length, width;
                                length = jsonObject1.getString("Length");
                                width = jsonObject1.getString("Width");
                                int column = Integer.parseInt(jsonObject1.getString("Column"));
                                int row = Integer.parseInt(jsonObject1.getString("Row"));
                                boolean isaval = Boolean.parseBoolean(jsonObject1.getString("IsAvailableSeat"));
                                boolean idLads = Boolean.parseBoolean(jsonObject1.getString("IsLadiesSeat"));
                                int index = Integer.parseInt(jsonObject1.getString("Zindex"));
                                float fare = Float.parseFloat(jsonObject1.getString("NetFare"));
                                float serviceTax = Float.parseFloat(jsonObject1.getString("Servicetax"));
                                float operatorServiceCharge = Float.parseFloat(jsonObject1.getString("OperatorServiceCharge"));
                                String seatNum = jsonObject1.getString("Number");
                                if (index == 0) {
                                    if (row == m) {
                                        if(column ==l){
                                            Log.e("seatSetting",index+" "+column+" "+row);
                                            NetFare.add(fare);
                                            Servicetax.add(serviceTax);
                                            OperatorServiceCharge.add(operatorServiceCharge);
                                            isSelected.add(false);
                                            seatNumber.add(seatNum);
                                            counterLower = m;
                                            isAvailable.add(isaval);
                                            isLadies.add(idLads);
                                            if (length.equalsIgnoreCase("1") && width.equalsIgnoreCase("1")) {
                                                //seater
                                                seatType.add("seater");
                                            } else if (length.equalsIgnoreCase("2") && width.equalsIgnoreCase("1")) {
                                                //Horizontial sleeper
                                                seatType.add("horizontalSleeper");
                                            } else if (length.equalsIgnoreCase("1") && width.equalsIgnoreCase("2")) {
                                                //vertical sleeper
                                                seatType.add("verticalSleeper");
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                            //index 1
                            for (int x = 0; x < jsonArray.length(); x++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(x);
                                String length, width;
                                length = jsonObject1.getString("Length");
                                width = jsonObject1.getString("Width");
                                int column = Integer.parseInt(jsonObject1.getString("Column"));
                                int row = Integer.parseInt(jsonObject1.getString("Row"));
                                boolean isaval = Boolean.parseBoolean(jsonObject1.getString("IsAvailableSeat"));
                                boolean idLads = Boolean.parseBoolean(jsonObject1.getString("IsLadiesSeat"));
                                int index = Integer.parseInt(jsonObject1.getString("Zindex"));
                                float fare = Float.parseFloat(jsonObject1.getString("NetFare"));
                                float serviceTax = Float.parseFloat(jsonObject1.getString("Servicetax"));
                                float operatorServiceCharge = Float.parseFloat(jsonObject1.getString("OperatorServiceCharge"));
                                String seatNum = jsonObject1.getString("Number");
                                if (index == 1) {
                                    if (column == l && row == m) {
                                        Log.e("seatSetting",index+" "+column+" "+row);
                                        zindexNetFare.add(fare);
                                        zindexServicetax.add(serviceTax);
                                        zindexOperatorServiceCharge.add(operatorServiceCharge);
                                        zindexisSelected.add(false);
                                        zindexseatNumber.add(seatNum);
                                        counterUpper = m;
                                        zindexIsAvailable.add(isaval);
                                        zindexIsLadies.add(idLads);
                                        //seater
                                        if (length.equalsIgnoreCase("1") && width.equalsIgnoreCase("1")) {
                                            zindexSeatType.add("seater");
                                        }
                                        //Horizontial sleeper
                                        else if (length.equalsIgnoreCase("2") && width.equalsIgnoreCase("1")) {
                                            zindexSeatType.add("horizontalSleeper");
                                        }
                                        //vertical sleeper
                                        else if (length.equalsIgnoreCase("1") && width.equalsIgnoreCase("2")) {
                                            zindexSeatType.add("verticalSleeper");
                                        }
                                        break;
                                    }
                                }
                            }
                            if (counterLower == m) {
                            } else {
                                isAvailable.add(false);
                                isLadies.add(false);
                                seatType.add("none");
                                NetFare.add(0f);
                                Servicetax.add(0f);
                                OperatorServiceCharge.add(0f);
                                isSelected.add(false);
                                seatNumber.add("");
                            }
                            if (counterUpper == m) {

                            } else {
                                zindexIsAvailable.add(false);
                                zindexIsLadies.add(false);
                                zindexSeatType.add("none");
                                zindexNetFare.add(0f);
                                zindexServicetax.add(0f);
                                zindexOperatorServiceCharge.add(0f);
                                zindexisSelected.add(false);
                                zindexseatNumber.add("");
                            }
                        }
                    }

                    setRecycler(isAvailable, seatType, isLadies, NetFare, Servicetax, OperatorServiceCharge, isSelected, seatNumber, zindexIsAvailable, zindexSeatType, zindexIsLadies, zindexNetFare, zindexServicetax, zindexOperatorServiceCharge, zindexisSelected, zindexseatNumber);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Something went wrong try again!"+e.toString(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progress.setVisibility(View.GONE);
                String message = "";
                if (error instanceof NetworkError) {
                    message = "No internet connection";
                } else {
                    message = "Something went wrong! Please try again!!";

                }
                Toast.makeText(Bus_seating.this, message, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        mqueue.add(stringRequest);
    }

    private void setRecycler(List<Boolean> isAvailable, List<String> seatType, List<Boolean> isLadies, List<Float> NetFare, List<Float> Servicetax, List<Float> OperatorServiceCharge, List<Boolean> isSelected, List<String> seatNumber, List<Boolean> zindexIsAvailable, List<String> zindexSeatType, List<Boolean> zindexIsLadies, List<Float> zindexNetFare, List<Float> zindexServicetax, List<Float> zindexOperatorServiceCharge, List<Boolean> zindexisSelected, List<String> zindexseatNumber) {
//        Toast.makeText(Bus_seating.this,isAvailable.size())
        //lower or seater

        GridLayoutManager layoutManager = new GridLayoutManager(Bus_seating.this, 5);
        seater.setLayoutManager(layoutManager);
        seat_image_adapter adapter = new seat_image_adapter(Bus_seating.this, isAvailable, isLadies, seatType, NetFare, Servicetax, OperatorServiceCharge, isSelected, seatNumber);
        seater.setAdapter(adapter);

        //upper
        GridLayoutManager layoutManager1 = new GridLayoutManager(Bus_seating.this, 5);
        upper.setLayoutManager(layoutManager1);
        seat_image_adapter adapter1 = new seat_image_adapter(Bus_seating.this, zindexIsAvailable, zindexIsLadies, zindexSeatType, zindexNetFare, zindexServicetax, zindexOperatorServiceCharge, zindexisSelected, zindexseatNumber);
        upper.setAdapter(adapter1);
    }

    //RUNS WHEN SEAT IS SELECTED
    @Override
    public void seatSelected(float position, String seatNumber, Float amount, Float seatAmount, Float serviceTax, Float serviceCharge) {
        seatsSelected.setText("");
//        Toast.makeText(Bus_seating.this, "Price : " + String.valueOf(amount), Toast.LENGTH_SHORT).show();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        selectedSeats.add(seatNumber);
        amountsList.add(String.valueOf(decimalFormat.format(seatAmount)));
        serviceTaxList.add(String.valueOf(decimalFormat.format(serviceTax)));
        serviceChargeList.add(String.valueOf(decimalFormat.format(serviceCharge)));
        total_amount = total_amount + amount;
        for (int i = 0; i < selectedSeats.size(); i++) {
            if (i == 0) {
                seatsSelected.append("" + selectedSeats.get(i)+"("+amountsList.get(i)+")");
            } else {
                seatsSelected.append("," + selectedSeats.get(i)+"("+amountsList.get(i)+")");
            }
        }
//        amountText.setText("" + decimalFormat.format(total_amount));
    }

    //RUNS WHEN EVER SEAT IS CLICKED TO GET NUM OF SEATS SELECTED
    @Override
    public int numOfSeatsSelected() {
        return selectedSeats.size();
    }

    //RUNS WHEN EVER SELECTED SEAT IS AGAIN CLICKED
    @Override
    public void deleteSeatSelected(int position, String seatNumber, Float amount) {
        try {
            DecimalFormat df = new DecimalFormat("0.00");
            total_amount = total_amount - amount;
            for (int j = 0; j < selectedSeats.size(); j++) {
                if (seatNumber.equalsIgnoreCase(selectedSeats.get(j))) {
                    selectedSeats.remove(j);
                    amountsList.remove(j);
                    serviceTaxList.remove(j);
                    serviceChargeList.remove(j);
                    break;
                }
            }
            seatsSelected.setText("");
            for (int i = 0; i < selectedSeats.size(); i++) {
                if (i == 0) {
                    seatsSelected.append("" + selectedSeats.get(i)+" ("+amountsList.get(i)+")");
                } else {
                    seatsSelected.append(", " + selectedSeats.get(i)+"  ("+amountsList.get(i)+")");
                }
            }
//            amountText.setText("" + df.format(total_amount));

        } catch (Exception e) {
            Toast.makeText(Bus_seating.this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }
}