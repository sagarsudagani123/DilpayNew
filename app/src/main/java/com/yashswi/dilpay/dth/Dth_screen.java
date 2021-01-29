package com.yashswi.dilpay.dth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.yashswi.dilpay.Api_interface.Mobile_interface;
import com.yashswi.dilpay.R;
import com.yashswi.dilpay.adapters.items_list_adapter;
import com.yashswi.dilpay.mobile.Mobile_recharge_successfull;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static retrofit2.converter.scalars.ScalarsConverterFactory.create;

public class Dth_screen extends AppCompatActivity {
    AppCompatButton next;
    ImageView back;
    TextInputEditText e_mobile,e_amount;
    AutoCompleteTextView operator_spin,circle_spin;

    ArrayList<String> operatorName=new ArrayList<>();
    ArrayList<String> operatorCode=new ArrayList<>();

    ArrayList<String> circleCode=new ArrayList<>();
    ArrayList<String> circleName=new ArrayList<>();

    String username,password,circle_code,operator_code,number,amount,order_id,format="json",operator_name,circle_name,status,txid,orderid;
    RelativeLayout progress_layout;
    RecyclerView dth_items;
    ArrayList<Integer> itemImg = new ArrayList<>();
    ArrayList<String> itemName = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dth_screen);
        operator_spin=findViewById(R.id.operator_spin);
        circle_spin=findViewById(R.id.circle_spin);
        e_mobile=findViewById(R.id.dth_number);
        e_amount=findViewById(R.id.amount);
        next=findViewById(R.id.next);
        dth_items=findViewById(R.id.dth_items);

        operatorName.add("Airtel Digital DTH TV");
        operatorName.add("SUNDIRECT DTH TV");
        operatorName.add("TATASKY DTH TV");
        operatorName.add("VIDEOCON DTH TV");
        operatorName.add("DISH TV");

        operatorCode.add("ATV");
        operatorCode.add("STV");
        operatorCode.add("TTV");
        operatorCode.add("VTV");
        operatorCode.add("DTV");

        circleName.add("Andaman And Nicobar");
        circleName.add("Andhra Pradesh");
        circleName.add("Arunachal Pradesh");
        circleName.add("Assam");
        circleName.add("Bihar");
        circleName.add("Chandigarh");
        circleName.add("Chhattisgarh ");
        circleName.add("Dadra Nager Haveli ");
        circleName.add("Daman And Div ");
        circleName.add("Goa");
        circleName.add("Gujarat");
        circleName.add("Haryana ");
        circleName.add("Himachal Pradesh");
        circleName.add("Jammu And Kashmir");
        circleName.add("Jharkhand");
        circleName.add("Karnataka");
        circleName.add("Kerala");
        circleName.add("Lakshadweep");
        circleName.add("Madhya Pradesh");
        circleName.add("Maharashtra ");
        circleName.add("Manipur");
        circleName.add("Meghalaya");
        circleName.add("Mizoram");
        circleName.add("Nagaland");
        circleName.add("New Delhi ");
        circleName.add("Orissa ");
        circleName.add("Pondicherry");
        circleName.add("Punjab");
        circleName.add("Rajasthan");
        circleName.add("Sikkim");
        circleName.add("Tamil Nadu");
        circleName.add("Tripura");
        circleName.add("Uttar Pradesh East");
        circleName.add("Uttaranchal");
        circleName.add("West Bengal");
        circleName.add("Uttar Pradesh West");
        circleName.add("Mumbai");
        circleName.add("Delhi");
        circleName.add("CHENNAI");
        circleName.add("NORTH EAST");
        circleName.add("Kolkata");
        circleName.add("Telangana");


        circleCode.add("41");
        circleCode.add("13");
        circleCode.add("41");
        circleCode.add("24");
        circleCode.add("17");
        circleCode.add("43");
        circleCode.add("27");
        circleCode.add("44");
        circleCode.add("45");
        circleCode.add("46");
        circleCode.add("12");
        circleCode.add("20");
        circleCode.add("21");
        circleCode.add("25");
        circleCode.add("22");
        circleCode.add("9");
        circleCode.add("14");
        circleCode.add("47");
        circleCode.add("16");
        circleCode.add("4");
        circleCode.add("48");
        circleCode.add("49");
        circleCode.add("50");
        circleCode.add("51");
        circleCode.add("52");
        circleCode.add("23");
        circleCode.add("53");
        circleCode.add("1");
        circleCode.add("18");
        circleCode.add("54");
        circleCode.add("8");
        circleCode.add("55");
        circleCode.add("10");
        circleCode.add("56");
        circleCode.add("2");
        circleCode.add("11");
        circleCode.add("3");
        circleCode.add("5");
        circleCode.add("7");
        circleCode.add("26");
        circleCode.add("6");
        circleCode.add("TG");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.menu_popup, operatorName);
        operator_spin.setAdapter(adapter);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getApplicationContext(), R.layout.menu_popup, circleName);
        circle_spin.setAdapter(adapter1);

        itemImg.add(R.drawable.bus);
        itemImg.add(R.drawable.mobile1);
//        itemImg.add(R.drawable.dth1);
//        itemImg.add(R.drawable.datacard1);

        itemName.add("Recharge History");//
        itemName.add("Offers");
//        itemName.add("cancelled Tickets");
//        itemName.add("Offers");

        items_list_adapter adapter2 = new items_list_adapter(itemImg, itemName, this);
        dth_items.setAdapter(adapter2);
        GridLayoutManager manager = new GridLayoutManager(this,3);
        dth_items.setLayoutManager(manager);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username="500594";
                password="5jfbpbe5";
//                number=e_mobile.getText().toString();
                amount=e_amount.getText().toString();
                operator_name=operator_spin.getText().toString();
                for(int i=0;i<operatorName.size();i++){
                    if(operator_name.equalsIgnoreCase(operatorName.get(i))){
                        operator_code=operatorCode.get(i);
                    }
                }
                circle_name=circle_spin.getText().toString();
                for(int i=0;i<circleName.size();i++){
                    if(circle_name.equalsIgnoreCase(circleName.get(i))){
                        circle_code=circleCode.get(i);
                    }
                }
                String number=e_mobile.getText().toString();
                Random rand = new Random();
                int rand_int1 = rand.nextInt(1000000);
                int year = Calendar.getInstance().get(Calendar.YEAR);
                getResponse(number,year+""+rand_int1,username,password,amount,operator_code,circle_code);
            }
        });

        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getResponse(String number, String s, String username, String password, String amount, String operator_code, String circle_code) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Mobile_interface.BASEURL)
                .addConverterFactory(create())
                .build();
        Mobile_interface api = retrofit.create(Mobile_interface.class);
        Call<String> call = api.mobile_recharge(username,password,circle_code,operator_code,number,amount,"2021"+orderid,format);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    JSONObject obj = new JSONObject(response.body());

                    Intent i = new Intent(Dth_screen.this, Mobile_recharge_successfull.class);
                    i.putExtra("status",obj.getString("status"));
                    i.putExtra("txid",obj.getInt("txid"));
                    i.putExtra("number",obj.getString("number"));
                    i.putExtra("amount",obj.getString("amount"));
                    i.putExtra("orderid",obj.getString("orderid"));
                    startActivity(i);

                } catch (Exception e) {
                    Toast.makeText(Dth_screen.this,e.toString(),Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(Dth_screen.this,t.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}