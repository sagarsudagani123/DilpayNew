package com.yashswi.dilpay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.smarteist.autoimageslider.Transformations.TossTransformation;
import com.yashswi.dilpay.Api_interface.Api_interface;
import com.yashswi.dilpay.bus.Available_buses;
import com.yashswi.dilpay.models.userDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.UnknownHostException;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class EditProfile extends AppCompatActivity {
    TextInputEditText e_address, e_city, e_state, e_country, e_pinCode,e_dob;
    AppCompatButton submit;
    String address, city, state, country, pinCode,dateOfBirth;
    RelativeLayout progress;
    boolean dateChecked = false;
    MaterialTextView dob;
    String otp,dob1;
    com.yashswi.dilpay.models.userDetails userDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        e_address = findViewById(R.id.e_address);
        e_city = findViewById(R.id.e_city);
        e_state = findViewById(R.id.e_state);
        e_country = findViewById(R.id.e_country);
        e_pinCode = findViewById(R.id.e_pinCode);
        dob=findViewById(R.id.dob);
        submit = findViewById(R.id.submit);
        progress = findViewById(R.id.progress_layout);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                address = e_address.getText().toString();
                city = e_city.getText().toString();
                state = e_state.getText().toString();
                country = e_country.getText().toString();
                pinCode = e_pinCode.getText().toString();
                dateOfBirth=dob.getText().toString();
                if (address.equalsIgnoreCase("")  || city.equalsIgnoreCase("") || state.equalsIgnoreCase("") || country.equalsIgnoreCase("") || pinCode.equalsIgnoreCase("") || !dateChecked) {
                    Toast.makeText(EditProfile.this, "Fill in all details", Toast.LENGTH_SHORT).show();
                } else {
                    updateDetails(address,dateOfBirth, city, state, country, pinCode);
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    progress.setVisibility(View.VISIBLE);
                }
            }
        });
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                final int month = calendar.get(Calendar.MONTH);
                final int year = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditProfile.this, (view, year1, month1, day1) -> {
                    month1 = month1 + 1;
                    String formattedMonth = "" + month1;
                    String formattedDayOfMonth = "" + day1;
                    String date = day1 + "-" + month1 + "-" + year1;
                    if (month1 < 10) {
                        formattedMonth = "0" + month1;
                    }
                    if (day1 < 10) {
                        formattedDayOfMonth = "0" + day1;
                    }

                    dob.setText(formattedDayOfMonth + "-" + formattedMonth + "-" + year1);
                    dateChecked = true;
                    dob1 = dob.getText().toString();

                    if (!dateChecked) {
                        Toast.makeText(EditProfile.this, "Fill in all details", Toast.LENGTH_SHORT).show();
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

    }

    private void updateDetails(String address,String dateOfBirth, String city, String state, String country, String pinCode) {
        JSONObject createData = new JSONObject();
        try {
            createData.put("username", new userDetails(EditProfile.this).getNumber());
            createData.put("Address", address);
            createData.put("DateOfBirth", dateOfBirth);
            createData.put("City", city);
            createData.put("State", state);
            createData.put("Country", country);
            createData.put("Pincode", pinCode);
            createData.put("Method", "Edit");
            Log.e("EditProfile", createData.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);
        Call<String> call = api.updateDetails(createData.toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("EditProfile", response.body());
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                progress.setVisibility(View.GONE);
                try {
                    JSONObject data = new JSONObject(response.body());
                    if (data.getString("Status").equalsIgnoreCase("True")) {
                        Toast.makeText(EditProfile.this, data.getString("Message"), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EditProfile.this, Profile.class);
                        intent.putExtra("toProfile", "fromEditProfile");
                        startActivity(intent);
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(EditProfile.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                progress.setVisibility(View.GONE);
                String message = "";
                if (t instanceof UnknownHostException) {
                    message = "No internet connection!";
                } else {
                    message = "Something went wrong! try again";
                }
                Toast.makeText(EditProfile.this, message + "", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}