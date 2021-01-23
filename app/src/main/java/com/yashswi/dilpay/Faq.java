package com.yashswi.dilpay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Faq extends AppCompatActivity {
    ImageView back;
    TextView qn1,qn2,qn3,qn4,qn5,qn6,qn7,qn8,qn9,qn10,qn11,qn12,qn13,qn14,qn15,qn16,qn17,qn18,qn19;
    TextView ans1,ans2,ans3,ans4,ans5,ans6,ans7,ans8,ans9,ans10,ans11,ans12,ans13,ans14,ans15,ans16,ans17,ans18,ans19;
    Boolean click1=true;
    Boolean click2=true;
    Boolean click3=true;
    Boolean click4=true;
    Boolean click5=true;
    Boolean click6=true;
    Boolean click7=true;
    Boolean click8=true;
    Boolean click9=true;
    Boolean click10=true;
    Boolean click11=true;
    Boolean click12=true;
    Boolean click13=true;
    Boolean click14=true;
    Boolean click15=true;
    Boolean click16=true;
    Boolean click17=true;
    Boolean click18=true;
    Boolean click19=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        back=findViewById(R.id.back);
        qn1=findViewById(R.id.qn1);
        qn2=findViewById(R.id.qn2);
        qn3=findViewById(R.id.qn3);
        qn4=findViewById(R.id.qn4);
        qn5=findViewById(R.id.qn5);
        qn6=findViewById(R.id.qn6);
        qn7=findViewById(R.id.qn7);
        qn8=findViewById(R.id.qn8);
        qn9=findViewById(R.id.qn9);
        qn10=findViewById(R.id.qn10);
        qn11=findViewById(R.id.qn11);
        qn12=findViewById(R.id.qn12);
        qn13=findViewById(R.id.qn13);
        qn14=findViewById(R.id.qn14);
        qn15=findViewById(R.id.qn15);
        qn16=findViewById(R.id.qn16);
        qn17=findViewById(R.id.qn17);
        qn18=findViewById(R.id.qn18);
        qn19=findViewById(R.id.qn19);
        ans1=findViewById(R.id.ans1);
        ans2=findViewById(R.id.ans2);
        ans3=findViewById(R.id.ans3);
        ans4=findViewById(R.id.ans4);
        ans5=findViewById(R.id.ans5);
        ans6=findViewById(R.id.ans6);
        ans7=findViewById(R.id.ans7);
        ans8=findViewById(R.id.ans8);
        ans9=findViewById(R.id.ans9);
        ans10=findViewById(R.id.ans10);
        ans11=findViewById(R.id.ans11);
        ans12=findViewById(R.id.ans12);
        ans13=findViewById(R.id.ans13);
        ans14=findViewById(R.id.ans14);
        ans15=findViewById(R.id.ans15);
        ans16=findViewById(R.id.ans16);
        ans17=findViewById(R.id.ans17);
        ans18=findViewById(R.id.ans18);
        ans19=findViewById(R.id.ans19);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        qn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click1){
                    ans1.setVisibility(View.VISIBLE);
                    click1=false;
                }else {
                    ans1.setVisibility(View.GONE);
                    click1=true;
                }
            }
        });
        qn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click2){
                    ans2.setVisibility(View.VISIBLE);
                    click2=false;
                }else {
                    ans2.setVisibility(View.GONE);
                    click2=true;
                }
            }
        });
        qn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click3){
                    ans3.setVisibility(View.VISIBLE);
                    click3=false;
                }else {
                    ans3.setVisibility(View.GONE);
                    click3=true;
                }
            }
        });
        qn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click4){
                    ans4.setVisibility(View.VISIBLE);
                    click4=false;
                }else {
                    ans4.setVisibility(View.GONE);
                    click4=true;
                }
            }
        });
        qn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click5){
                    ans5.setVisibility(View.VISIBLE);
                    click5=false;
                }else {
                    ans5.setVisibility(View.GONE);
                    click5=true;
                }
            }
        });
        qn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click6){
                    ans6.setVisibility(View.VISIBLE);
                    click6=false;
                }else {
                    ans6.setVisibility(View.GONE);
                    click6=true;
                }
            }
        });
        qn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click7){
                    ans7.setVisibility(View.VISIBLE);
                    click7=false;
                }else {
                    ans7.setVisibility(View.GONE);
                    click7=true;
                }
            }
        });
        qn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click8){
                    ans8.setVisibility(View.VISIBLE);
                    click8=false;
                }else {
                    ans8.setVisibility(View.GONE);
                    click8=true;
                }
            }
        });
        qn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click9){
                    ans9.setVisibility(View.VISIBLE);
                    click9=false;
                }else {
                    ans9.setVisibility(View.GONE);
                    click9=true;
                }
            }
        });
        qn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click10){
                    ans10.setVisibility(View.VISIBLE);
                    click10=false;
                }else {
                    ans10.setVisibility(View.GONE);
                    click10=true;
                }
            }
        });
        qn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click11){
                    ans11.setVisibility(View.VISIBLE);
                    click11=false;
                }else {
                    ans11.setVisibility(View.GONE);
                    click11=true;
                }
            }
        });
        qn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click12){
                    ans12.setVisibility(View.VISIBLE);
                    click12=false;
                }else {
                    ans12.setVisibility(View.GONE);
                    click12=true;
                }
            }
        });
        qn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click13){
                    ans13.setVisibility(View.VISIBLE);
                    click13=false;
                }else {
                    ans13.setVisibility(View.GONE);
                    click13=true;
                }
            }
        });
        qn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click14){
                    ans14.setVisibility(View.VISIBLE);
                    click14=false;
                }else {
                    ans14.setVisibility(View.GONE);
                    click14=true;
                }
            }
        });
        qn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click15){
                    ans15.setVisibility(View.VISIBLE);
                    click15=false;
                }else {
                    ans15.setVisibility(View.GONE);
                    click15=true;
                }
            }
        });
        qn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click16){
                    ans16.setVisibility(View.VISIBLE);
                    click16=false;
                }else {
                    ans16.setVisibility(View.GONE);
                    click16=true;
                }
            }
        });
        qn17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click17){
                    ans17.setVisibility(View.VISIBLE);
                    click17=false;
                }else {
                    ans17.setVisibility(View.GONE);
                    click17=true;
                }
            }
        });
        qn18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click18){
                    ans18.setVisibility(View.VISIBLE);
                    click18=false;
                }else {
                    ans18.setVisibility(View.GONE);
                    click18=true;
                }
            }
        });
        qn19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click19){
                    ans19.setVisibility(View.VISIBLE);
                    click19=false;
                }else {
                    ans19.setVisibility(View.GONE);
                    click19=true;
                }
            }
        });
    }
}