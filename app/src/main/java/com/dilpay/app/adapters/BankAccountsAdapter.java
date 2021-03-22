package com.dilpay.app.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dilpay.app.Api_interface.Api_interface;
import com.dilpay.app.R;
import com.dilpay.app.bank.WithdrawAmount;
import com.dilpay.app.bank.BankAccounts;
import com.dilpay.app.models.bankDetails;
import com.dilpay.app.models.userDetails;

import org.json.JSONObject;

import java.net.UnknownHostException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class BankAccountsAdapter extends RecyclerView.Adapter<BankAccountsAdapter.MyViewHolder> {
    ArrayList<bankDetails> bankDetails;
    ArrayList<String> IFSC;
    String title;
    Context context;
    static String tokenFinal;
    RelativeLayout progress;
    JSONObject dataObj =null;

    public BankAccountsAdapter(String title, ArrayList<bankDetails> bankDetails, RelativeLayout progress, Context context) {
        this.bankDetails = bankDetails;
        this.title = title;
        this.context = context;
        this.progress=progress;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.for_bank_accounts, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.acntNumber.setText(bankDetails.get(position).getAcntNumber());
        holder.IFSC.setText(bankDetails.get(position).getIfscCode());
        if (title.equalsIgnoreCase("Select Bank")) {
            holder.delete.setVisibility(View.GONE);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, WithdrawAmount.class);
                    intent.putExtra("beneficiaryID", bankDetails.get(position).getBeneficiaryID());
                    intent.putExtra("emial", bankDetails.get(position).getEmial());
                    intent.putExtra("accountNumber", bankDetails.get(position).getAcntNumber());
                    intent.putExtra("IFSC", bankDetails.get(position).getIfscCode());
                    intent.putExtra("address", bankDetails.get(position).getAddress());
                    context.startActivity(intent);
                    ((BankAccounts)context).finish();
                }
            });
        }
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.VISIBLE);
                getToken(bankDetails.get(position).getBeneficiaryID());

                dataObj = new JSONObject();
                try {
                    dataObj.put("beneficiaryID", bankDetails.get(position).getBeneficiaryID());
                    dataObj.put("username", new userDetails(context).getNumber());
                    dataObj.put("Name", new userDetails(context).getName());
                    dataObj.put("emial", bankDetails.get(position).getEmial());
                    dataObj.put("phone", new userDetails(context).getNumber());
                    dataObj.put("accountNumber", bankDetails.get(position).getAcntNumber());
                    dataObj.put("IFSC", bankDetails.get(position).getIfscCode());
                    dataObj.put("address", bankDetails.get(position).getAddress());
                    dataObj.put("Method", "Delete");
                } catch (Exception e) {
                    Log.e("GetToken",e.toString());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return bankDetails.size();
    }

    void getToken(String benId){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_interface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api_interface api = retrofit.create(Api_interface.class);
        Call<String> call = api.generatePayoutToken();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    JSONObject data = new JSONObject(response.body());
                    if (data.getString("status").equalsIgnoreCase("SUCCESS")) {
                        JSONObject tokenObj = data.getJSONObject("data");
                        tokenFinal = tokenObj.getString("token");
                        ((BankAccounts)context).removeBeneficiary(tokenFinal,benId, dataObj.toString());
                    } else {
                        tokenFinal = "";
                        Toast.makeText(context,data.toString(),Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                String message = "";
                if (t instanceof UnknownHostException) {
                    message = "No internet connection";
                } else {
                    message = "Something went wrong! try again";
                }
                Toast.makeText(context, message + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView acntNumber, IFSC;
        ImageView delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            acntNumber = itemView.findViewById(R.id.acntNum);
            IFSC = itemView.findViewById(R.id.ifscNum);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}
