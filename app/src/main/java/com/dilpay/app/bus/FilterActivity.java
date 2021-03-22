package com.dilpay.app.bus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.slider.RangeSlider;
import com.google.android.material.textfield.TextInputEditText;
import com.dilpay.app.R;

public class FilterActivity extends AppCompatActivity {
    RangeSlider rangeSlider;
    TextView amountFrom, amountTo;
    AppCompatButton applyFilter;
    TextInputEditText travelsName;
    RadioGroup busTypeRadio;
    String travels, bus = "", fromAmt = "0", toAmt = "10000";
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        rangeSlider = findViewById(R.id.amountSlider);
        amountFrom = findViewById(R.id.amountFrom);
        amountTo = findViewById(R.id.amountTo);
        applyFilter = findViewById(R.id.applyFilter);
        travelsName = findViewById(R.id.travelsName);
        busTypeRadio = findViewById(R.id.radioGroupBusType);
        back = findViewById(R.id.back);

        travelsName.setText(getIntent().getStringExtra("name"));

        rangeSlider.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                fromAmt = String.valueOf(slider.getValues().get(0));
                toAmt = String.valueOf(slider.getValues().get(1));
                amountTo.setText(String.valueOf(slider.getValues().get(1)));
                amountFrom.setText(String.valueOf(slider.getValues().get(0)));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        applyFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                travels = travelsName.getText().toString();
                int checkBusType = busTypeRadio.getCheckedRadioButtonId();
                if (checkBusType == -1) {
                } else if (checkBusType == R.id.seater) {
                    bus = "Seater";
                } else if (checkBusType == R.id.sleeper) {
                    bus = "Sleeper";
                } else if (checkBusType == R.id.semiSleeper) {
                    bus = "SemiSleeper";
                }
                Intent resultIntent = new Intent();
                resultIntent.putExtra("TravelsName", travels);
                resultIntent.putExtra("BusType", bus);
                resultIntent.putExtra("AmountFrom", fromAmt);
                resultIntent.putExtra("AmountTo", toAmt);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }

}