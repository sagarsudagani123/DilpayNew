package com.yashswi.dilpay.bank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.yashswi.dilpay.Api_interface.Api_interface;
import com.yashswi.dilpay.Api_interface.bankAddDelete;
import com.yashswi.dilpay.Api_interface.cashFree;
import com.yashswi.dilpay.R;
import com.yashswi.dilpay.TransactionsHistory;
import com.yashswi.dilpay.adapters.BankAccountsAdapter;
import com.yashswi.dilpay.bus.Available_buses;
import com.yashswi.dilpay.models.bankDetails;
import com.yashswi.dilpay.models.userDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.UnknownHostException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class BankAccounts extends AppCompatActivity implements bankAddDelete {
    RelativeLayout progress;
    TextView available, bank_name;
    ImageView addAccount, back;
    userDetails userDetail;
    String tokenFinal;
    String title;
    RecyclerView accountsList;

    ArrayList<String> acntNumber = new ArrayList<>();
    ArrayList<String> IFSC = new ArrayList<>();
    ArrayList<String> BenID = new ArrayList<>();
    ArrayList<String> EMAIL = new ArrayList<>();
    ArrayList<String> ADDRESS = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_accounts);

        available = findViewById(R.id.available);
        addAccount = findViewById(R.id.addAccount);
        back = findViewById(R.id.back);
        bank_name = findViewById(R.id.bank_name);
        progress = findViewById(R.id.progress);
        accountsList = findViewById(R.id.accountsList);

        title = getIntent().getStringExtra("title");
        if (title.equalsIgnoreCase("Select Bank")) {
            addAccount.setVisibility(View.GONE);
        }

        userDetail = new userDetails(BankAccounts.this);

        bank_name.setText(title);

        getBankDetails(userDetail.getNumber());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BankAccounts.this, Add_account_details.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void getBankDetails(String number) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);
        Call<String> call = api.getBanks(number);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                progress.setVisibility(View.GONE);
                Log.e("bankdetails", response.body());
                if (response.body() != null) {
                    try {
                        String message;
                        JSONObject data = new JSONObject(response.body());
                        if (data.getString("Status").equalsIgnoreCase("True")) {
                            JSONArray jsonArray = data.getJSONArray("Data");


                            ArrayList<bankDetails> bankAccountsData = new ArrayList<>();
                            bankAccountsData.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject data1 = jsonArray.getJSONObject(i);
                                bankDetails bankModal = new bankDetails();
                                bankModal.setAcntNumber(data1.getString("AccountNumber"));
                                bankModal.setIfscCode(data1.getString("IFSC"));
                                bankModal.setBeneficiaryID(data1.getString("BeneficiaryID"));
                                bankModal.setEmial(data1.getString("Emial"));
                                bankModal.setAddress(data1.getString("Address"));
                                bankAccountsData.add(bankModal);
                            }

                            if (bankAccountsData.size() >= 1) {
                                accountsList.setVisibility(View.VISIBLE);
                                available.setVisibility(View.GONE);
                                LinearLayoutManager manager = new LinearLayoutManager(BankAccounts.this, RecyclerView.VERTICAL, false);
                                accountsList.setLayoutManager(manager);
                                BankAccountsAdapter adapter = new BankAccountsAdapter(title, bankAccountsData, BankAccounts.this);
                                accountsList.setAdapter(adapter);
                            }
                        } else {
                            Toast.makeText(BankAccounts.this, data.getString("Data"), Toast.LENGTH_SHORT).show();
                            available.setVisibility(View.VISIBLE);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(BankAccounts.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    available.setVisibility(View.VISIBLE);
                    Toast.makeText(BankAccounts.this, "null response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progress.setVisibility(View.GONE);
                available.setVisibility(View.VISIBLE);
                String message = "";
                if (t instanceof UnknownHostException) {
                    message = "No internet connection!";
                } else {
                    message = "Something went wrong! try again";
                }
                Toast.makeText(BankAccounts.this, message + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void deleteAccount(String data) {
        Log.e("bankDetails", data);
        progress.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);
        Call<String> call = api.addBankDetails(data);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("bankDetails", response.body());
                progress.setVisibility(View.GONE);
                if (response.body() != null) {
                    try {
                        JSONObject data = new JSONObject(response.body());
                        String status = data.getString("Status");
                        String message = data.getString("Message");
                        if (status.equalsIgnoreCase("True")) {
                            Toast.makeText(BankAccounts.this, message, Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(BankAccounts.this, message, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progress.setVisibility(View.GONE);
                String message = "";
                if (t instanceof UnknownHostException) {
                    message = "No internet connection!";
                } else {
                    message = "Something went wrong! try again";
                }
                Toast.makeText(BankAccounts.this, message + "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}