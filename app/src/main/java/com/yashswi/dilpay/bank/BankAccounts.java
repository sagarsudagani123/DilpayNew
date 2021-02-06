package com.yashswi.dilpay.bank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yashswi.dilpay.R;
import com.yashswi.dilpay.adapters.BankAccountsAdapter;

import java.util.ArrayList;

public class BankAccounts extends AppCompatActivity {
    RecyclerView accountsList;
    TextView available,bank_name;
    ImageView addAccount,back;
    ArrayList<String> acntNumber=new ArrayList<>();
    ArrayList<String> IFSC=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_accounts);
        accountsList=findViewById(R.id.accountsList);
        available=findViewById(R.id.available);
        addAccount=findViewById(R.id.addAccount);
        back=findViewById(R.id.back);
        bank_name=findViewById(R.id.bank_name);

        bank_name.setText(getIntent().getStringExtra("title"));
        if(getIntent().getStringExtra("title").equalsIgnoreCase("Select Bank")){
//            addAccount.setVisibility(View.GONE);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BankAccounts.this, Add_account_details.class);
                startActivity(intent);
            }
        });
        if(acntNumber.size()==0){
            accountsList.setVisibility(View.GONE);
            available.setVisibility(View.VISIBLE);

        }
        BankAccountsAdapter adapter =new BankAccountsAdapter(acntNumber,IFSC,this);
        accountsList.setAdapter(adapter);
    }
}