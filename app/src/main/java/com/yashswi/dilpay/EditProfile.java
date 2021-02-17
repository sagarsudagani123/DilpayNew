package com.yashswi.dilpay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.smarteist.autoimageslider.Transformations.TossTransformation;
import com.yashswi.dilpay.Api_interface.Api_interface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class EditProfile extends AppCompatActivity {
    TextInputEditText e_address,e_city,e_state,e_country,e_pinCode;
    AppCompatButton submit;
    String address,city,state,country,pinCode;
    RelativeLayout progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        e_address=findViewById(R.id.e_address);
        e_city=findViewById(R.id.e_city);
        e_state=findViewById(R.id.e_state);
        e_country=findViewById(R.id.e_country);
        e_pinCode=findViewById(R.id.e_pinCode);
        submit=findViewById(R.id.submit);
        progress=findViewById(R.id.progress_layout);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                address=e_address.getText().toString();
                city=e_city.getText().toString();
                state=e_state.getText().toString();
                country=e_country.getText().toString();
                pinCode=e_pinCode.getText().toString();
                if(address.equalsIgnoreCase("") || city.equalsIgnoreCase("") || state.equalsIgnoreCase("") || country.equalsIgnoreCase("") || pinCode.equalsIgnoreCase("")){
                    Toast.makeText(EditProfile.this,"Fill in all details",Toast.LENGTH_SHORT).show();
                }
                else{
                    updateDetails(address,city,state,country,pinCode);
                    progress.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void updateDetails(String address, String city, String state, String country, String pinCode) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);
//        Call<String> call = api.updateDetails();
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                progress.setVisibility(View.GONE);
//                Toast.makeText(EditProfile.this,response.body(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                progress.setVisibility(View.GONE);
//                Toast.makeText(EditProfile.this,t.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}