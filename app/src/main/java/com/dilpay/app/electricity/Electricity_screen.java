package com.dilpay.app.electricity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.dilpay.app.Api_interface.Api_interface;
import com.dilpay.app.R;
import com.dilpay.app.Security.SecurityPin;
import com.dilpay.app.adapters.items_list_adapter;
import com.dilpay.app.mobile.Mobile_recharge_successfull;
import com.dilpay.app.models.userDetails;
import com.dilpay.app.utils.CheckNetworkStatus;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Electricity_screen extends AppCompatActivity {
    AppCompatButton next;
    ImageView back;
    TextInputEditText e_mobile, e_amount;
    AutoCompleteTextView operator_spin, circle_spin;

    ArrayList<String> operatorName = new ArrayList<>();
    ArrayList<String> operatorCode = new ArrayList<>();

    ArrayList<String> circleCode = new ArrayList<>();
    ArrayList<String> circleName = new ArrayList<>();

    String username, password, circle_code, operator_code, number, amount, order_id, format = "json", operator_name, circle_name, status, txid, orderid;
    RelativeLayout progress_layout;
    RecyclerView electricity_items;
    ArrayList<Integer> itemImg = new ArrayList<>();
    ArrayList<String> itemName = new ArrayList<>();
    RelativeLayout progress;
    String fromCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity_screen);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        fromCategory = getIntent().getStringExtra("FromCategory");

        if (!CheckNetworkStatus.getConnectivityStatusString(Electricity_screen.this)) {
            Toast.makeText(Electricity_screen.this, "No internet connection", Toast.LENGTH_SHORT).show();
            finish();
        }

        operator_spin = findViewById(R.id.operator_spin);
        circle_spin = findViewById(R.id.circle_spin);
        e_mobile = findViewById(R.id.service_number);
        e_amount = findViewById(R.id.amount);
        next = findViewById(R.id.next);
        electricity_items = findViewById(R.id.electricity_items);
        progress = findViewById(R.id.progress);

        operatorName.add("North Bihar Electricity");
        operatorName.add("JBVNL - JHARKHAND");
        operatorName.add("Assam Power Distribution Company Ltd (RAPDR) ");
        operatorName.add("APDCL (Non-RAPDR) - ASSAM");
        operatorName.add("BSES Rajdhani Power Limited - Delhi");
        operatorName.add("BSES Yamuna Power Limited - Delhi");
        operatorName.add("Tata Power Delhi Limited - Delhi");
        operatorName.add("Tata Power - MUMBAI");
        operatorName.add("HESCOM - KARNATAKA");
        operatorName.add("South Bihar Electricity");
        operatorName.add("BEST Electricity");
        operatorName.add("Ajmer Vidyut Vitran Nigam - RAJASTHAN");
        operatorName.add("Bangalore ElectricitySupply Company");
        operatorName.add("CESC - WEST BENGAL");
        operatorName.add("CSEB - CHHATTISGARH");
        operatorName.add("Jaipur Vidyut Vitran Nigam - RAJASTHAN");
        operatorName.add("Jodhpur Vidyut Vitran Nigam - RAJASTHAN");
        operatorName.add("Madhya Kshetra Vitaran - MADHYA PRADESH");
        operatorName.add("MSEDC - MAHARASHTRA");
        operatorName.add("Noida Power - NOIDA");
        operatorName.add("Paschim Kshetra Vitaran - MADHYA PRADESH");
        operatorName.add("Southern Power - ANDHRA PRADESH");
        operatorName.add("Southern Power - TELANGANA");
        operatorName.add("Torrent Power agra");
        operatorName.add("WESCO - ODISHA");
        operatorName.add("PGVCL - GUJARAT");
        operatorName.add("BharatpurElectricityServicesLtd");
        operatorName.add("Muzaffarpur Vidyut Vitran");
        operatorName.add("MGVCL - GUJARAT");
        operatorName.add("MEPDCL - MEGHALAYA");
        operatorName.add("KEDL - KOTA");
        operatorName.add("Dakshin Gujarat Vij Company Ltd");
        operatorName.add("WBSEDCL - WEST BENGAL");
        operatorName.add("SNDL Power - NAGPUR");
        operatorName.add("BikanerElectricitySupplyLimited");
        operatorName.add("India Power - WEST BENGAL");
        operatorName.add("BrihanMumbaiElectricSupplyandTransportUndertaking");
        operatorName.add("APEPDCL - ANDHRA PRADESH");
        operatorName.add("TNEB - TAMIL NADU");
        operatorName.add("UPPCL (URBAN) - UTTAR PRADESH");
        operatorName.add("Uttar Pradesh Power Corporation Limited(Rular)");
        operatorName.add("DakshinHaryanaBijliVitranNigam");
        operatorName.add("DNHPowerDistributionCompanyLimited");
        operatorName.add("GulbargaElectricitySupplyCompanyLimited");
        operatorName.add("IndiaPowerCorporationLimited");
        operatorName.add("JamshedpurUtilitiesandServicesCompanyLimited");
        operatorName.add("NorthDelhiPowerLimited");
        operatorName.add("MUNICIPALCORPORATIONOFGURUGRAM");
        operatorName.add("PunjabStatePowerCorporationLtd");
        operatorName.add("TripuraStateElectricityCorporationLtd");
        operatorName.add("UttarHaryanaBijliVitranNigam");
        operatorName.add("UttarakhandPowerCorporationLimited");


        operatorCode.add("NBE");
        operatorCode.add("JBVNL");
        operatorCode.add("APDCLR");
        operatorCode.add("APDCLN");
        operatorCode.add("BSES");
        operatorCode.add("BSESY");
        operatorCode.add("TPD");
        operatorCode.add("TPDM");
        operatorCode.add("HESCOM");
        operatorCode.add("SBE");
        operatorCode.add("BEST");
        operatorCode.add("AJV");
        operatorCode.add("BESCOM");
        operatorCode.add("CESC");
        operatorCode.add("CSEB");
        operatorCode.add("JVV");
        operatorCode.add("JDVV");
        operatorCode.add("MKV");
        operatorCode.add("MSEDC");
        operatorCode.add("NP");
        operatorCode.add("PKV");
        operatorCode.add("SPA");
        operatorCode.add("SPT");
        operatorCode.add("TRP");
        operatorCode.add("WESCO");
        operatorCode.add("PGVCL");
        operatorCode.add("BHES");
        operatorCode.add("MVV");
        operatorCode.add("MGVCL");
        operatorCode.add("MEPDCL");
        operatorCode.add("KEDL");
        operatorCode.add("DGVCL");
        operatorCode.add("WBSEDCL");
        operatorCode.add("SNDL");
        operatorCode.add("BESL");
        operatorCode.add("IPWB");
        operatorCode.add("BMESTU");
        operatorCode.add("APEPDCL");
        operatorCode.add("TNEB");
        operatorCode.add("UPPCLU");
        operatorCode.add("UPPCLR");
        operatorCode.add("DHBVN");
        operatorCode.add("DDCL");
        operatorCode.add("GESCL");
        operatorCode.add("IPCL");
        operatorCode.add("JUSCL");
        operatorCode.add("NDPL");
        operatorCode.add("MCG");
        operatorCode.add("PSPCL");
        operatorCode.add("TSECL");
        operatorCode.add("UHBV");
        operatorCode.add("UKPCL");


        circleName.add("Andaman And Nicobar");
        circleName.add("Andhra Pradesh");
        circleName.add("Arunachal Pradesh");
        circleName.add("Assam");
        circleName.add("Bihar");
        circleName.add("Chandigarh");
        circleName.add("CHENNAI");
        circleName.add("Chhattisgarh ");
        circleName.add("Dadra Nager Haveli ");
        circleName.add("Daman And Div ");
        circleName.add("Delhi");
        circleName.add("Goa");
        circleName.add("Gujarat");
        circleName.add("Haryana ");
        circleName.add("Himachal Pradesh");
        circleName.add("Jammu And Kashmir");
        circleName.add("Jharkhand");
        circleName.add("Karnataka");
        circleName.add("Kerala");
        circleName.add("Kolkata");
        circleName.add("Lakshadweep");
        circleName.add("Madhya Pradesh");
        circleName.add("Maharashtra ");
        circleName.add("Manipur");
        circleName.add("Meghalaya");
        circleName.add("Mizoram");
        circleName.add("Mumbai");
        circleName.add("Nagaland");
        circleName.add("New Delhi ");
        circleName.add("NORTH EAST");
        circleName.add("Orissa ");
        circleName.add("Pondicherry");
        circleName.add("Punjab");
        circleName.add("Rajasthan");
        circleName.add("Sikkim");
        circleName.add("Tamil Nadu");
        circleName.add("Telangana");
        circleName.add("Tripura");
        circleName.add("Uttar Pradesh East");
        circleName.add("Uttaranchal");
        circleName.add("Uttar Pradesh West");
        circleName.add("West Bengal");


        circleCode.add("41");
        circleCode.add("13");
        circleCode.add("41");
        circleCode.add("24");
        circleCode.add("17");
        circleCode.add("43");
        circleCode.add("7");
        circleCode.add("27");
        circleCode.add("44");
        circleCode.add("45");
        circleCode.add("5");
        circleCode.add("46");
        circleCode.add("12");
        circleCode.add("20");
        circleCode.add("21");
        circleCode.add("25");
        circleCode.add("22");
        circleCode.add("9");
        circleCode.add("14");
        circleCode.add("6");
        circleCode.add("47");
        circleCode.add("16");
        circleCode.add("4");
        circleCode.add("48");
        circleCode.add("49");
        circleCode.add("50");
        circleCode.add("3");
        circleCode.add("51");
        circleCode.add("52");
        circleCode.add("26");
        circleCode.add("23");
        circleCode.add("53");
        circleCode.add("1");
        circleCode.add("18");
        circleCode.add("54");
        circleCode.add("8");
        circleCode.add("TG");
        circleCode.add("55");
        circleCode.add("10");
        circleCode.add("56");
        circleCode.add("2");
        circleCode.add("11");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.menu_popup, operatorName);
        operator_spin.setAdapter(adapter);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getApplicationContext(), R.layout.menu_popup, circleName);
        circle_spin.setAdapter(adapter1);


        itemImg.add(R.drawable.history);
        itemImg.add(R.drawable.offers);
//        itemImg.add(R.drawable.dth1);
//        itemImg.add(R.drawable.datacard1);

        itemName.add("Payments History");//
        itemName.add("Offers");
//        itemName.add("cancelled Tickets");
//        itemName.add("Offers");

        items_list_adapter adapter2 = new items_list_adapter(itemImg, itemName, "electricity", this);
        electricity_items.setAdapter(adapter2);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        electricity_items.setLayoutManager(manager);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = "500594";
                password = "5jfbpbe5";
                amount = e_amount.getText().toString();
                operator_name = operator_spin.getText().toString();
                for (int i = 0; i < operatorName.size(); i++) {
                    if (operator_name.equalsIgnoreCase(operatorName.get(i))) {
                        operator_code = operatorCode.get(i);
                    }
                }
                circle_name = circle_spin.getText().toString();
                for (int i = 0; i < circleName.size(); i++) {
                    if (circle_name.equalsIgnoreCase(circleName.get(i))) {
                        circle_code = circleCode.get(i);
                    }
                }
                String number = e_mobile.getText().toString();
                Random rand = new Random();
                int rand_int1 = rand.nextInt(1000000);
                int year = Calendar.getInstance().get(Calendar.YEAR);
                progress.setVisibility(View.GONE);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//                getResponse(number, year + "" + rand_int1, username, password, amount, operator_code, circle_code);
                Intent intent =new Intent(Electricity_screen.this, SecurityPin.class);
                intent.putExtra("number",number);
                intent.putExtra("amount",amount);
                intent.putExtra("operatorCode",operator_code);
                intent.putExtra("circleCode",circle_code);
                startActivity(intent);
            }
        });
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getResponse(String number, String orderid, String username, String password, String amount, String operator_code, String circle_code) {
        JSONObject dataObj=new JSONObject();
        try {
            dataObj.put("username", new userDetails(Electricity_screen.this).getNumber());
            dataObj.put("number", number);
            dataObj.put("operatorCode", operator_code);
            dataObj.put("circleCode", circle_code);
            dataObj.put("amount", amount);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("RechargeTest",dataObj.toString());
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .callTimeout(2, TimeUnit.MINUTES)
                .connectTimeout(90, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .client(httpClient.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);
        Call<String> call = api.MobileRecharge(dataObj.toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("testRecharge","success  "+response.body());
                progress.setVisibility(View.GONE);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                try {
                    JSONObject obj = new JSONObject(response.body());
                    String status=obj.getString("Status");
                    if(status.equalsIgnoreCase("True")){
                        String Service=obj.getString("Service");
                        String txid=obj.getString("txid");
                        String rechargeStatus=obj.getString("status");
                        String rechargeNumber=obj.getString("number");
                        String amount=obj.getString("amount");
                        String orderid=obj.getString("orderid");

                        if(!(rechargeStatus.equalsIgnoreCase("null"))){
                            Intent i = new Intent(Electricity_screen.this, Mobile_recharge_successfull.class);
                            i.putExtra("status", rechargeStatus);
                            i.putExtra("txid", txid);
//                          i.putExtra("opid",obj.getInt("opid"));
                            i.putExtra("number", rechargeNumber);
                            i.putExtra("amount", amount);
                            i.putExtra("orderid", orderid);
                            startActivity(i);
                            finish();
                        }else {
                            Toast.makeText(Electricity_screen.this, "Enter valid details!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Electricity_screen.this, obj.getString("Data"), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (Exception e) {
                    Toast.makeText(Electricity_screen.this, e.toString(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("testRecharge","failed  "+t.toString());
                progress.setVisibility(View.GONE);
                String message = "";
                if (t instanceof UnknownHostException) {
                    message = "No internet connection!";
                } else {
                    message = "Something went wrong! try again";
                }
                Toast.makeText(Electricity_screen.this, message + ""+t.toString(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}